package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import PersistenceModel.HostelBean;
import PersistenceModel.OrdinaryPlanBean;
import dao.Service.ReleaseOrdinaryPlanDao;

public class ReleaseOrdinaryPlanImpl implements ReleaseOrdinaryPlanDao{
	
	private SessionFactory sessionFactory;
	
	@Override
	public void releasePlan(double singleRoomFee, double standardRoomFee, double suiteRoomFee) {
		
		// 增加或更新更新OrdinaryPlan的信息
		Session sess = sessionFactory.openSession();
		Transaction tx = sess.beginTransaction();
		
		List<HostelBean> hosList = sess.createQuery("select h from HostelBean h").list();
		for(int i=0; i<hosList.size(); i++) {
			int hosID = hosList.get(i).getHostelID();
			List<OrdinaryPlanBean> opbList = sess.createQuery("select opb from OrdinaryPlanBean opb where opb.hostelID=?").setParameter(0, hosID).list();
			if(opbList.size() == 0){ // 该酒店没有对应的OrdinaryPlan，增加一条OrdinaryPlan的信息
				OrdinaryPlanBean ordinaryPlan = new OrdinaryPlanBean();
				ordinaryPlan.setHostelID(hosID);
				ordinaryPlan.setSingleRoomFee(singleRoomFee);
				ordinaryPlan.setStandardRoomFee(standardRoomFee);
				ordinaryPlan.setSuiteRoomFee(suiteRoomFee);
				sess.save(ordinaryPlan);
			}
			else{ // 该酒店已有对应的OrdinaryPlan，更新一条OrdinaryPlan的信息
				for(int j=0; j<opbList.size(); j++){
					OrdinaryPlanBean ordinaryPlan = opbList.get(i);
					ordinaryPlan.setSingleRoomFee(singleRoomFee);
					ordinaryPlan.setStandardRoomFee(standardRoomFee);
					ordinaryPlan.setSuiteRoomFee(suiteRoomFee);
					sess.update(ordinaryPlan);
				}
			}
		}
		
		tx.commit();
		sess.close();
		
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}
