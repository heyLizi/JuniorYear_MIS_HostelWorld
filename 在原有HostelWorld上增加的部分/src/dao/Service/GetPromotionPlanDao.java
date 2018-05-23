package dao.Service;

import java.util.List;

import PersistenceModel.PromotionPlanBean;

/* 获得促销计划信息 */

public interface GetPromotionPlanDao {

	/* 获得促销计划信息列表 */
	public List<PromotionPlanBean> getPromotionPlan(int hostelID);
	/* 获得地域和时间特定的促销计划信息列表 */
	public List<PromotionPlanBean> getPromotionPlanByAreaAndTime(String province, String city, String bookDate);
	/* 获得单个促销计划的详细信息 */
	public PromotionPlanBean getPromotionPlanByID(int planID);
	
}
