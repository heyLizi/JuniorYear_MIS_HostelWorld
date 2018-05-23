package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import PersistenceModel.PromotionPlanBean;
import dao.Service.GetPromotionPlanDao;

public class GetPromotionPlanImpl implements GetPromotionPlanDao{
	
	private SessionFactory sessionFactory;

	private PromotionPlanBean promotionPlan;
	
	@Override
	public List<PromotionPlanBean> getPromotionPlan(int hostelID) {
		
		// 获得酒店编号为hostelID的酒店的PromotionPlan列表
		Session sess = sessionFactory.openSession();
		
		Transaction tx = sess.beginTransaction();
		
		List<PromotionPlanBean> list = sess.createQuery("select ppb from PromotionPlanBean ppb where "+
		"ppb.hostelID = ? order by ppb.endDate desc").setParameter(0, hostelID).list();
		
		tx.commit();
		sess.close();
		
		return list;
	}

	@Override
	public List<PromotionPlanBean> getPromotionPlanByAreaAndTime(String province, String city, String bookDate) {
		
		// 获得在province省city市的在bookDate中有效的PromotionPlan列表
		Session sess = sessionFactory.openSession();
		Transaction tx = sess.beginTransaction();
		
		List<PromotionPlanBean> list = sess.createQuery("select ppb from PromotionPlanBean ppb, HostelBean hb where "+
		"hb.hostelProvince = ? and hb.hostelCity = ? and hb.hostelID=ppb.hostelID and ppb.startDate <= ? and ppb.endDate >= ?")
		.setParameter(0, province).setParameter(1, city).setParameter(2, bookDate).setParameter(3, bookDate).list();
		
		tx.commit();
		sess.close();
		
		return list;
	}
	
	@Override
	public PromotionPlanBean getPromotionPlanByID(int planID) {
		
		// 查询一条PromotionPlan的信息
		Session sess = sessionFactory.openSession();
		Transaction tx = sess.beginTransaction();
		
		PromotionPlanBean ppb = (PromotionPlanBean) sess.load(PromotionPlanBean.class, planID);
		
		promotionPlan.setHostelID(ppb.getHostelID());
		promotionPlan.setPromotionID(ppb.getPromotionID());
		promotionPlan.setPromotionName(ppb.getPromotionName());
		promotionPlan.setSingleRoomFee(ppb.getSingleRoomFee());
		promotionPlan.setStandardRoomFee(ppb.getStandardRoomFee());
		promotionPlan.setSuiteRoomFee(ppb.getSuiteRoomFee());
		promotionPlan.setStartDate(ppb.getStartDate());
		promotionPlan.setEndDate(ppb.getEndDate());
		
		tx.commit();
		sess.close();
		
		return promotionPlan;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void setPromotionPlan(PromotionPlanBean promotionPlan) {
		this.promotionPlan = promotionPlan;
	}
	
}
