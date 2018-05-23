package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import PersistenceModel.BookBean;
import PersistenceModel.CheckinBean;
import PersistenceModel.HostelAccountBean;
import PersistenceModel.HostelBean;
import PersistenceModel.MemberCardBean;
import PersistenceModel.RoomBean;
import dao.Service.HostelStatisticsDao;

public class HostelStatisticsImpl implements HostelStatisticsDao{

	private SessionFactory sessionFactory;
	
	@Override
	public List<BookBean> getAllBooks(int hostelID) {
		
		// 获得酒店编号为hostelID的酒店的Book列表
		Session sess = sessionFactory.openSession();
		Transaction tx = sess.beginTransaction();
		
		List<BookBean> list = sess.createQuery("select b from BookBean b where "+
		"b.hostelID = ?").setParameter(0, hostelID).list();
		
		tx.commit();
		sess.close();
		
		return list;
	}

	@Override
	public int[] getNowCheckin(int hostelID) {
		
		// 获得酒店编号为hostelID的酒店三种房间类型的现在入住的checkinID
		Session sess = sessionFactory.openSession();
		Transaction tx = sess.beginTransaction();
		
		List<Integer> list1 = sess.createQuery("select r.latestCheckinID from RoomBean r where "+
		"r.roomCategory = ? and r.isCheckin <> ? and r.hostelID = ?").setParameter(0, "singleRoom").setParameter(1, 0).setParameter(2, hostelID).list();
		
		List<Integer> list2 = sess.createQuery("select r.latestCheckinID from RoomBean r where "+
		"r.roomCategory = ? and r.isCheckin <> ? and r.hostelID = ?").setParameter(0, "standardRoom").setParameter(1, 0).setParameter(2, hostelID).list();
		
		List<Integer> list3 = sess.createQuery("select r.latestCheckinID from RoomBean r where "+
		"r.roomCategory = ? and r.isCheckin <> ? and r.hostelID = ?").setParameter(0, "suiteRoom").setParameter(1, 0).setParameter(2, hostelID).list();
		
		int[] arr = new int[24];
		int[] arr1 = new int[6], arr2 = new int[6], arr3 = new int[6], arr4 = new int[2], arr5 = new int[2], arr6 = new int[2];
		
		// 获得三种房型的每个checkin对应不同等级会员的人数，有无优惠的人数
		for(int i=0; i<list1.size(); i++) {
			CheckinBean chk = (CheckinBean) sess.load(CheckinBean.class, list1.get(i));
			int memberID = chk.getMemberID();
			MemberCardBean member = (MemberCardBean) sess.load(MemberCardBean.class, memberID);
			int authority = member.getAuthority();
			arr1[authority] += 1;
			boolean isPromotion = chk.isBook();
			if(isPromotion){ arr4[0] += 1;}
			else{ arr4[1] += 1; }
		}
		for(int i=0; i<list2.size(); i++) {
			CheckinBean chk = (CheckinBean) sess.load(CheckinBean.class, list2.get(i));
			int memberID = chk.getMemberID();
			MemberCardBean member = (MemberCardBean) sess.load(MemberCardBean.class, memberID);
			int authority = member.getAuthority();
			arr2[authority] += 1;
			boolean isPromotion = chk.isBook();
			if(isPromotion){ arr5[0] += 1;}
			else{ arr5[1] += 1; }
		}
		for(int i=0; i<list3.size(); i++) {
			CheckinBean chk = (CheckinBean) sess.load(CheckinBean.class, list3.get(i));
			int memberID = chk.getMemberID();
			MemberCardBean member = (MemberCardBean) sess.load(MemberCardBean.class, memberID);
			int authority = member.getAuthority();
			arr3[authority] += 1;
			boolean isPromotion = chk.isBook();
			if(isPromotion){ arr6[0] += 1;}
			else{ arr6[1] += 1; }
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

	@Override
	public int[] getNowBook(int hostelID) {
		
		// 获得酒店编号为hostelID的酒店三种房间类型的现在入住的checkinID
		Session sess = sessionFactory.openSession();
		Transaction tx = sess.beginTransaction();
		
		List<Integer> list1 = sess.createQuery("select r.latestCheckinID from RoomBean r where "+
		"r.roomCategory = ? and r.isCheckin <> ? and r.hostelID = ?").setParameter(0, "singleRoom").setParameter(1, 0).setParameter(2, hostelID).list();
		
		List<Integer> list2 = sess.createQuery("select r.latestCheckinID from RoomBean r where "+
		"r.roomCategory = ? and r.isCheckin <> ? and r.hostelID = ?").setParameter(0, "standardRoom").setParameter(1, 0).setParameter(2, hostelID).list();
		
		List<Integer> list3 = sess.createQuery("select r.latestCheckinID from RoomBean r where "+
		"r.roomCategory = ? and r.isCheckin <> ? and r.hostelID = ?").setParameter(0, "suiteRoom").setParameter(1, 0).setParameter(2, hostelID).list();
		
		int[] arr = new int[24];
		int[] arr1 = new int[6], arr2 = new int[6], arr3 = new int[6], arr4 = new int[2], arr5 = new int[2], arr6 = new int[2];
		
		// 获得三种房型的每个book对应不同等级会员的人数，有无优惠的人数
		for(int i=0; i<list1.size(); i++) {
			BookBean chk = (BookBean) sess.load(BookBean.class, list1.get(i));
			int memberID = chk.getMemberID();
			MemberCardBean member = (MemberCardBean) sess.load(MemberCardBean.class, memberID);
			int authority = member.getAuthority();
			arr1[authority] += 1;
			boolean isPromotion = chk.isPromotion();
			if(isPromotion){ arr4[0] += 1;}
			else{ arr4[1] += 1; }
		}
		for(int i=0; i<list2.size(); i++) {
			BookBean chk = (BookBean) sess.load(BookBean.class, list2.get(i));
			int memberID = chk.getMemberID();
			MemberCardBean member = (MemberCardBean) sess.load(MemberCardBean.class, memberID);
			int authority = member.getAuthority();
			arr2[authority] += 1;
			boolean isPromotion = chk.isPromotion();
			if(isPromotion){ arr5[0] += 1;}
			else{ arr5[1] += 1; }
		}
		for(int i=0; i<list3.size(); i++) {
			BookBean chk = (BookBean) sess.load(BookBean.class, list3.get(i));
			int memberID = chk.getMemberID();
			MemberCardBean member = (MemberCardBean) sess.load(MemberCardBean.class, memberID);
			int authority = member.getAuthority();
			arr3[authority] += 1;
			boolean isPromotion = chk.isPromotion();
			if(isPromotion){ arr6[0] += 1;}
			else{ arr6[1] += 1; }
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
	
	@Override
	public double getNowBalance(int hostelID) {
		
		// 获得酒店编号为hostelID的酒店的现在账户余额
		Session sess = sessionFactory.openSession();
		Transaction tx = sess.beginTransaction();
		
		HostelAccountBean hos = (HostelAccountBean)sess.load(HostelAccountBean.class, hostelID);
		double result = hos.getHostelBalance();
		
		tx.commit();
		sess.close();
		
		return result;
	}

	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	

}
