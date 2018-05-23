package dao.Service;

import java.util.List;

import PersistenceModel.HostelBean;
import PersistenceModel.OrdinaryPlanBean;

/* 获得正常计划信息 */

public interface GetOrdinaryPlanDao {

	/* 获得正常计划信息 */
	public OrdinaryPlanBean getOrdinaryPlan(int hostelID);
	/* 获得地域和时间特定的酒店正常营业计划列表 */
	public List<HostelBean> getHostelByAreaAndTime(String province, String city);
	
}
