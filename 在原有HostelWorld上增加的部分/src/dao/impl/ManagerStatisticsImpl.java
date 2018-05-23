package dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import PersistenceModel.HostelIncomeBean;
import dao.Service.ManagerStatisticsDao;

public class ManagerStatisticsImpl implements ManagerStatisticsDao{
	
	private SessionFactory sessionFactory;

	@Override
	public double[] getSaleMoney() {
		
		// 获得酒店编号为hostelID的酒店三种房间类型的现在的HostelIncome
		Session sess = sessionFactory.openSession();
		Transaction tx = sess.beginTransaction();
		
		List<Integer> hostelList = sess.createQuery("select hb.hostelID from HostelBean hb").list();
		
		double[] arr = new double[24*hostelList.size()];
		double[] arr1 = new double[6], arr2 = new double[6], arr3 = new double[6], arr4 = new double[2], arr5 = new double[2], arr6 = new double[2];
		
		for(int i=0; i<hostelList.size(); i++) {
		
			int hostelID = hostelList.get(i);
			
			List<HostelIncomeBean> list1 = sess.createQuery("select hib from HostelIncomeBean hib where "+
			"hib.roomCategory = ? and hib.hostelID = ?").setParameter(0, "singleRoom").setParameter(1, hostelID).list();
			List<HostelIncomeBean> list2 = sess.createQuery("select hib from HostelIncomeBean hib where "+
			"hib.roomCategory = ? and hib.hostelID = ?").setParameter(0, "standardRoom").setParameter(1, hostelID).list();
			List<HostelIncomeBean> list3 = sess.createQuery("select hib from HostelIncomeBean hib where "+
			"hib.roomCategory = ? and hib.hostelID = ?").setParameter(0, "suiteRoom").setParameter(1, hostelID).list();
			
			// 获得三种房型的每个checkin对应不同等级会员的人数，有无优惠的人数
			for(int j=0; j<list1.size(); j++) {
				HostelIncomeBean hib = list1.get(j);
				int authority = hib.getMemberAuthority();
				arr1[authority] += hib.getDiscountCost();
				boolean isPromotion = hib.getIsPromotion();
				if(isPromotion){ arr4[0] += hib.getDiscountCost();}
				else{ arr4[1] += hib.getDiscountCost(); }
			}
			for(int j=0; j<list2.size(); j++) {
				HostelIncomeBean hib = list2.get(j);
				int authority = hib.getMemberAuthority();
				arr1[authority] += hib.getDiscountCost();
				boolean isPromotion = hib.getIsPromotion();
				if(isPromotion){ arr5[0] += hib.getDiscountCost();}
				else{ arr5[1] += hib.getDiscountCost(); }
			}
			for(int j=0; j<list3.size(); j++) {
				HostelIncomeBean hib = list3.get(j);
				int authority = hib.getMemberAuthority();
				arr1[authority] += hib.getDiscountCost();
				boolean isPromotion = hib.getIsPromotion();
				if(isPromotion){ arr6[0] += hib.getDiscountCost();}
				else{ arr6[1] += hib.getDiscountCost(); }
			}
			
			for(int j=0; j<6; j++){
				arr[i*j] = arr1[j];
			}
			for(int j=0; j<6; j++){
				arr[i*j+6] = arr2[j];
			}
			for(int j=0; j<6; j++){
				arr[i*j+12] = arr3[j];
			}
			for(int j=0; j<2; j++){
				arr[i*j+18] = arr4[j];
			}
			for(int j=0; j<2; j++){
				arr[i*j+20] = arr5[j];
			}
			for(int j=0; j<2; j++){
				arr[i*j+22] = arr6[j];
			}
			
		}
		
		tx.commit();
		sess.close();
		
		return arr;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
