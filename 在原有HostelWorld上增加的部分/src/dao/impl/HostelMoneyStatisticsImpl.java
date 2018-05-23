package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import PersistenceModel.BookBean;
import PersistenceModel.CheckinBean;
import PersistenceModel.HostelAccountBean;
import PersistenceModel.HostelIncomeBean;
import PersistenceModel.MemberCardBean;
import PersistenceModel.RoomBean;
import dao.Service.HostelMoneyStatisticsDao;
import dao.Service.HostelStatisticsDao;

public class HostelMoneyStatisticsImpl implements HostelMoneyStatisticsDao{

	private SessionFactory sessionFactory;
	
	@Override
	public double[] getSaleMoney(int hostelID) {
		
		// 获得酒店编号为hostelID的酒店三种房间类型的现在的HostelIncome
		Session sess = sessionFactory.openSession();
		Transaction tx = sess.beginTransaction();
		
		List<HostelIncomeBean> list1 = sess.createQuery("select hib from HostelIncomeBean hib where "+
		"hib.roomCategory = ? and hib.hostelID = ?").setParameter(0, "singleRoom").setParameter(1, hostelID).list();
		List<HostelIncomeBean> list2 = sess.createQuery("select hib from HostelIncomeBean hib where "+
		"hib.roomCategory = ? and hib.hostelID = ?").setParameter(0, "standardRoom").setParameter(1, hostelID).list();
		List<HostelIncomeBean> list3 = sess.createQuery("select hib from HostelIncomeBean hib where "+
		"hib.roomCategory = ? and hib.hostelID = ?").setParameter(0, "suiteRoom").setParameter(1, hostelID).list();
				
		double[] arr = new double[24];
		double[] arr1 = new double[6], arr2 = new double[6], arr3 = new double[6], arr4 = new double[2], arr5 = new double[2], arr6 = new double[2];
		
		// 获得三种房型的每个checkin对应不同等级会员的人数，有无优惠的人数
		for(int i=0; i<list1.size(); i++) {
			HostelIncomeBean hib = list1.get(i);
			int authority = hib.getMemberAuthority();
			arr1[authority] += hib.getDiscountCost();
			boolean isPromotion = hib.getIsPromotion();
			if(isPromotion){ arr4[0] += hib.getDiscountCost();}
			else{ arr4[1] += hib.getDiscountCost(); }
		}
		for(int i=0; i<list2.size(); i++) {
			HostelIncomeBean hib = list2.get(i);
			int authority = hib.getMemberAuthority();
			arr1[authority] += hib.getDiscountCost();
			boolean isPromotion = hib.getIsPromotion();
			if(isPromotion){ arr5[0] += hib.getDiscountCost();}
			else{ arr5[1] += hib.getDiscountCost(); }
		}
		for(int i=0; i<list3.size(); i++) {
			HostelIncomeBean hib = list3.get(i);
			int authority = hib.getMemberAuthority();
			arr1[authority] += hib.getDiscountCost();
			boolean isPromotion = hib.getIsPromotion();
			if(isPromotion){ arr6[0] += hib.getDiscountCost();}
			else{ arr6[1] += hib.getDiscountCost(); }
		}
		
		tx.commit();
		sess.close();
		
		for(int i=0; i<6; i++){
			arr[i] = arr1[i];
		}
		for(int i=0; i<6; i++){
			arr[i+6] = arr2[i];
		}
		for(int i=0; i<6; i++){
			arr[i+12] = arr3[i];
		}
		for(int i=0; i<2; i++){
			arr[i+18] = arr4[i];
		}
		for(int i=0; i<2; i++){
			arr[i+20] = arr5[i];
		}
		for(int i=0; i<2; i++){
			arr[i+22] = arr6[i];
		}
		return arr;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
