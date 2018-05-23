package dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import PersistenceModel.CheckinBean;
import PersistenceModel.CheckoutBean;
import PersistenceModel.HostelAccountBean;
import PersistenceModel.HostelIncomeBean;
import PersistenceModel.MemberCardBean;
import PersistenceModel.OrdinaryPlanBean;
import PersistenceModel.PromotionPlanBean;
import PersistenceModel.RoomBean;
import dao.Service.CheckoutDao;

public class CheckoutImpl implements CheckoutDao{

	private SessionFactory sessionFactory;
	
	@Override
	public void checkout(int hostelID, int roomID, String checkoutDate) {
		
		// 更新Room入住状态，获得该房间对应的Checkin编号
		Session sess1 = sessionFactory.openSession();
		Transaction tx1 = sess1.beginTransaction();
		List<RoomBean> roomList = sess1.createQuery("select r from RoomBean r where "+
		"r.hostelID = ? and r.roomID = ?").setParameter(0, hostelID).setParameter(1, roomID).list();
		
		RoomBean room = roomList.get(0);
		int checkinID = room.getLatestCheckinID();
		System.out.println(checkinID);
		room.setIsCheckin(false);
		
		sess1.update(room);
		tx1.commit();
		sess1.close();
		
		// 根据Checkin编号获得预订编号、会员编号、入住日期等信息
		Session sess2 = sessionFactory.openSession();
		
		CheckinBean checkin = (CheckinBean) sess2.get(CheckinBean.class, checkinID);
		int bookID = checkin.getBookID();
		int memberID = checkin.getMemberID();
		System.out.println(memberID);
		String roomCategory = checkin.getRoomCategory();
		String checkinDate = checkin.getCheckinDate();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date checkinDateDate = new Date();
		Date checkoutDateDate = new Date();
		int period = -1;
		try {
			checkinDateDate = formatter.parse(checkinDate);
			checkoutDateDate = formatter.parse(checkoutDate);
			period = (int)((checkoutDateDate.getTime()-checkinDateDate.getTime())/ (3600*24*1000));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		sess2.close();
		
		// 根据入住日期和离宿日期来判断是否这段时间有优惠计划
		Session sess3 = sessionFactory.openSession();
		
		boolean isPromotion = false;
		double[] ppFee = {0.0, 0.0, 0.0}; // 分别对应singleRoom，standardRoom，suiteRoom的优惠费用

		List<PromotionPlanBean> ppList = sess3.createQuery("select ppb from PromotionPlanBean ppb where "+
		"ppb.hostelID = ?").setParameter(0, hostelID).list();
		for(int i=0; i<ppList.size(); i++){
			PromotionPlanBean pp = ppList.get(i);
			String ppStartDateStr = pp.getStartDate();
			String ppEndDateStr = pp.getEndDate();
			Date ppStartDate = new Date();
			Date ppEndDate = new Date();
			try {
				ppStartDate = formatter.parse(ppStartDateStr);
				ppEndDate = formatter.parse(ppEndDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			// 两种情况都算有优惠计划
			if((checkinDateDate.after(ppStartDate)) && (checkinDateDate.before(ppEndDate))) {
				// 第一种情况：入住日期在优惠开始日期之后，优惠结束日期之前
				isPromotion = true;
			}
			else if((checkoutDateDate.after(ppStartDate)) && (checkoutDateDate.before(ppEndDate))) {
				// 第二种情况：离宿日期在优惠开始日期之后，优惠结束日期之前
				isPromotion = true;
			}	
			if(isPromotion){
				ppFee[0] = pp.getSingleRoomFee();
				ppFee[1] = pp.getStandardRoomFee();
				ppFee[2] = pp.getSuiteRoomFee();
			}
		}
		System.out.println(isPromotion+"  "+ppFee[2]);
		sess3.close();
		
		// 查询没有优惠的统一价格
		double[] opFee = {0.0, 0.0, 0.0}; // 分别对应singleRoom，standardRoom，suiteRoom的统一费用
		
		Session sess4 = sessionFactory.openSession();
		
		List<OrdinaryPlanBean> opList = sess4.createQuery("select opb from OrdinaryPlanBean opb where "+
		"opb.hostelID = ?").setParameter(0, hostelID).list();
		OrdinaryPlanBean op = opList.get(0);
		opFee[0] = op.getSingleRoomFee();
		opFee[1] = op.getStandardRoomFee();
		opFee[2] = op.getSuiteRoomFee();
		System.out.println(opFee[2]);
		sess4.close();
		
		// 如果是会员，查询会员卡信息
		int authority = -1;
		if(memberID != -1){
			Session sess5 = sessionFactory.openSession();
			
			MemberCardBean memberCard = (MemberCardBean)sess5.get(MemberCardBean.class, memberID);
			authority = memberCard.getAuthority();
			
			sess5.close();
		}

		// 计算费用（原来费用、折扣后费用）
		int roomCategoryInt = -1;
		if(roomCategory.startsWith("single")){ roomCategoryInt = 0; }
		else if(roomCategory.startsWith("stand")){ roomCategoryInt = 1; }
		else{ roomCategoryInt = 2;}
		
		double originFee = opFee[roomCategoryInt] * period;
		double promotionFee = 0;
		if(isPromotion){
			promotionFee = ppFee[roomCategoryInt] * period;
		}
		if(memberID != -1){
			double discount = getDiscount(authority);
			promotionFee *= discount;
		}
		
		// 如果是会员，更新一条MemberCard信息
		if(memberID != -1){
			Session sess6 = sessionFactory.openSession();
			Transaction tx6 = sess6.beginTransaction();
			
			MemberCardBean memberCard = (MemberCardBean)sess6.load(MemberCardBean.class, memberID);
			double nowBalance = memberCard.getBalance();
			memberCard.setBalance(nowBalance-promotionFee);//这里有个Bug，没有比较剩余金额和花销的大小，只有余额大于花销时才能预订成功……
			double nowTotalPay = memberCard.getTotalPay();
			memberCard.setTotalPay(nowTotalPay+promotionFee);
			int nowCredits = memberCard.getCredits();
			memberCard.setCredits(nowCredits+((int)promotionFee/10));
			memberCard.setAuthority(getAuthority(memberCard.getTotalPay()));
			
			sess6.update(memberCard);
			tx6.commit();
			sess6.close();
			
		}
		
		// 更新一条HostelAccount信息
		Session sess7 = sessionFactory.openSession();
		Transaction tx7 = sess7.beginTransaction();
		
		System.out.println(hostelID);
		HostelAccountBean hostelAccount = (HostelAccountBean)sess7.get(HostelAccountBean.class, hostelID);
		double nowBalance = hostelAccount.getHostelBalance();
		hostelAccount.setHostelBalance(nowBalance+promotionFee);
		
		sess7.update(hostelAccount);
		tx7.commit();
		sess7.close();
		
		// 增加一条HostelIncome信息
		Session sess8 = sessionFactory.openSession();
		Transaction tx8 = sess8.beginTransaction();
		
		HostelIncomeBean hostelIncome = new HostelIncomeBean();
		
		hostelIncome.setHostelID(hostelID);
		hostelIncome.setRoomCategory(roomCategory);
		hostelIncome.setPayDate(checkoutDate);
		if(memberID == -1){
			authority = 0;
		}
		hostelIncome.setMemberAuthority(authority);
		boolean isBook = false;
		if(bookID != -1){
			isBook = true;
		}
		hostelIncome.setIsBook(isBook);
		hostelIncome.setIsPromotion(isPromotion);
		hostelIncome.setOriginalCost(originFee);
		hostelIncome.setDiscountCost(promotionFee);
		hostelIncome.setIsSettled(false);
		
		sess8.save(hostelIncome);
		tx8.commit();
		
		System.out.println(hostelIncome.getPayDate());
		sess8.close();
		
		// 增加一条Checkout信息
		Session sess9 = sessionFactory.openSession();
		Transaction tx9 = sess9.beginTransaction();
		
		CheckoutBean checkout = new CheckoutBean();
		
		checkout.setHostelID(hostelID);
		checkout.setRoomCategory(roomCategory);
		checkout.setRoomID(roomID);
		checkout.setCheckinID(checkinID);
		checkout.setCheckoutDate(checkoutDate);
		checkout.setPeriodSpan(period);
		checkout.setIsPromotion(isPromotion);
		checkout.setOriginalCost(originFee);
		checkout.setDiscountCost(promotionFee);
		
		sess9.save(checkout);
		tx9.commit();
		
		System.out.println(checkout.getCheckinID());
		sess9.close();
		
	}
	
	public int getAuthority(double pay){
		if(pay>=10000) { return 5; }
		else if(pay>=5000) { return 4; }
		else if(pay>=3000) { return 3; }
		else if(pay>=1000) { return 2; }
		else if(pay>=500) { return 1; }
		return 0;
	}
	
	public double getDiscount(int authority){
		switch (authority) {
		case 0: return 1;
		case 1: return 0.95;
		case 2: return 0.9;
		case 3: return 0.85;
		case 4: return 0.8;
		case 5: return 0.75;
		default: return 1;
		}
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}
