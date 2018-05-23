package dao.Service;

/* 发布正常计划 */

public interface ReleaseOrdinaryPlanDao {

	/* 发布正常计划（集团统一价） */
	public void releasePlan(double singleRoomFee, double standardRoomFee, double suiteRoomFee);
	
}
