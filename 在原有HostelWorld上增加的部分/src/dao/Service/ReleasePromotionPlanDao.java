package dao.Service;

/* 发布酒店促销计划 */

public interface ReleasePromotionPlanDao {

	/* 判断和上次计划时间上是否冲突 */
	public boolean isConflict(String startDate,int hostelID);
	/* 发布促销计划 */
	public void releasePlan(String promotionName, int hostelID, double singleRoomFee,
			double standardRoomFee, double suiteRoomFee, String startDate, String endDate);
	
}
