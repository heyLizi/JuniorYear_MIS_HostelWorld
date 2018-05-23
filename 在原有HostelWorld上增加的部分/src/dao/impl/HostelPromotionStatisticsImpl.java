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
import PersistenceModel.PromotionPlanBean;
import PersistenceModel.RoomBean;
import dao.Service.HostelMoneyStatisticsDao;
import dao.Service.HostelPromotionPlanStatisticsDao;
import dao.Service.HostelStatisticsDao;

public class HostelPromotionStatisticsImpl implements HostelPromotionPlanStatisticsDao{

	private SessionFactory sessionFactory;
	
	@Override
	public PromotionPlanBean getLastPromotion(int hostelID) {

		// 获得酒店编号为hostelID的酒店的PromotionPlan列表
		Session sess = sessionFactory.openSession();
		
		Transaction tx = sess.beginTransaction();
		
		List<PromotionPlanBean> list = sess.createQuery("select ppb from PromotionPlanBean ppb where "+
		"ppb.hostelID = ? order by ppb.endDate desc").setParameter(0, hostelID).list();
		
		tx.commit();
		sess.close();
		
		PromotionPlanBean promotionPlan = list.get(list.size()-1);
		return promotionPlan;
	}

	@Override
	public double[] getLastPromotionAnalyse(int hostelID) {
		HostelStatisticsImpl hostelStatistics  = new HostelStatisticsImpl();
		HostelMoneyStatisticsImpl hostelMoneyStatics = new HostelMoneyStatisticsImpl();
		int[] num = hostelStatistics.getNowCheckin(hostelID);
		double[] money = hostelMoneyStatics.getSaleMoney(hostelID);
		double[] resultArr = new double[33];
		
		for(int i=0; i<12; i++) {
			resultArr[i] = num[i];
		}
		for(int i=0; i<12; i++) {
			resultArr[i+12] = money[i];
		}
		for(int i=0; i<6; i++) {
			resultArr[i+24] = money[i]-money[i+6];
		}
		for(int i=0; i<3; i++) {
			resultArr[i+30] = (resultArr[i+27] > 0.0) ? 1:0;
		}
		return resultArr;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
