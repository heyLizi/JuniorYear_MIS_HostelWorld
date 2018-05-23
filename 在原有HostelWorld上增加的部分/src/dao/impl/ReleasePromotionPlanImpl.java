package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import PersistenceModel.PromotionPlanBean;
import dao.Service.ReleasePromotionPlanDao;

public class ReleasePromotionPlanImpl implements ReleasePromotionPlanDao{
	
	private SessionFactory sessionFactory;
	
	private PromotionPlanBean promotionPlan;

	@Override
	public boolean isConflict(String startDate, int hostelID) {
		
		// 获得酒店编号为hostelID的酒店最近的PromotionPlan
		Session sess = sessionFactory.openSession();
		Transaction tx = sess.beginTransaction();
		
		List<PromotionPlanBean> ppbList = sess.createQuery("select ppb from PromotionPlanBean ppb where "+
		"ppb.hostelID = ? order by ppb.endDate desc").setParameter(0, hostelID).list();

		if(ppbList.size() == 0){
			return false;
		}
		
		// 把申请的开始日期，和已经发布计划的最后结束日期比较
		String endDate = ppbList.get(0).getEndDate();
		int result = startDate.compareTo(endDate); 

		tx.commit();
		sess.close();
		
		if(result <= 0){ 
			return true; 
		}
		else{ 
			return false; 
		}

	}

	@Override
	public void releasePlan(String promotionName, int hostelID, double singleRoomFee, 
			double standardRoomFee, double suiteRoomFee, String startDate, String endDate) {
		
		// 增加一条PromotionPlan的信息
		Session sess = sessionFactory.openSession();
		Transaction tx = sess.beginTransaction();
		
		PromotionPlanBean promotionPlan = new PromotionPlanBean();
		promotionPlan.setPromotionName(promotionName);
		promotionPlan.setHostelID(hostelID);
		promotionPlan.setSingleRoomFee(singleRoomFee);
		promotionPlan.setStandardRoomFee(standardRoomFee);
		promotionPlan.setSuiteRoomFee(suiteRoomFee);
		promotionPlan.setStartDate(startDate);
		promotionPlan.setEndDate(endDate);
		
		sess.save(promotionPlan);
		tx.commit();
		sess.close();
		
		//sessionFactory.close();  //这个东西最好别关……关了就找不到了
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void setPromotionPlan(PromotionPlanBean promotionPlan) {
		this.promotionPlan = promotionPlan;
	}
	
}
