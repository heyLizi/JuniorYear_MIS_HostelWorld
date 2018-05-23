
package dao.Service;

import PersistenceModel.PromotionPlanBean;

/* 酒店的统计分析  */

public interface HostelPromotionPlanStatisticsDao {

	/* 获得上一次的促销计划  */
	public PromotionPlanBean getLastPromotion(int hostelID);
	/* 获得上一次促销计划的分析参数  */
	public double[] getLastPromotionAnalyse(int hostelID);
	
}
