package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import PersistenceModel.HostelBean;
import PersistenceModel.OrdinaryPlanBean;
import dao.Service.GetOrdinaryPlanDao;

public class GetOrdinaryPlanImpl implements GetOrdinaryPlanDao{
	
	private SessionFactory sessionFactory;

	private OrdinaryPlanBean ordinaryPlan;
	private HostelBean hostel;
	
	@Override
	public OrdinaryPlanBean getOrdinaryPlan(int hostelID) {
		
		// 查询一条OrdinaryPlan记录
		Session sess = sessionFactory.openSession();
		Transaction tx = sess.beginTransaction();
		
		OrdinaryPlanBean op = (OrdinaryPlanBean) sess.get(OrdinaryPlanBean.class, hostelID);
		
		ordinaryPlan.setHostelID(op.getHostelID());
		ordinaryPlan.setSingleRoomFee(op.getSingleRoomFee());
		ordinaryPlan.setStandardRoomFee(op.getStandardRoomFee());
		ordinaryPlan.setSuiteRoomFee(op.getSuiteRoomFee());

		tx.commit();
		sess.close();
		
		return ordinaryPlan;
	}

	@Override
	public List<HostelBean> getHostelByAreaAndTime(String province, String city) {
		
		// 取得在province省city市的Hostel列表
		Session sess = sessionFactory.openSession();
		Transaction tx = sess.beginTransaction();
		
		List<HostelBean> hostelList = sess.createQuery("select h from HostelBean h where "+
		"h.hostelProvince = ? and h.hostelCity = ?").setParameter(0, province).setParameter(1, city).list();
		
		tx.commit();
		sess.close();
		
		return hostelList;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void setOrdinaryPlan(OrdinaryPlanBean ordinaryPlan) {
		this.ordinaryPlan = ordinaryPlan;
	}
	
	public void setHostel(HostelBean hostel) {
		this.hostel = hostel;
	}
	
}
