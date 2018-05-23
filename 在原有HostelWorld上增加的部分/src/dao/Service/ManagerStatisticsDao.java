package dao.Service;


/* 经理的统计分析 */

public interface ManagerStatisticsDao {

	/* 获得截止到当前各种分类下的具体销售金额  */
	public double[] getSaleMoney();
	
}
