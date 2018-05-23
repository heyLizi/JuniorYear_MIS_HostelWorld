package dao.Service;


/* 酒店的统计分析  */

public interface HostelMoneyStatisticsDao {

	/* 获得截止到当前各种分类下的具体销售金额  */
	public double[] getSaleMoney(int hostelID);
	
}
