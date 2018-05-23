package dao.Service;

import java.util.List;

import PersistenceModel.BookBean;

/* 酒店的统计分析  */

public interface HostelStatisticsDao {

	/* 获得预订信息列表  */
	public List<BookBean> getAllBooks(int hostelID);
	/* 获得当前各种分类下的具体入住数  */
	public int[] getNowCheckin(int hostelID);
	/* 获得当前各种分类下的具体预订数  */
	public int[] getNowBook(int hostelID);
	/* 获得当前酒店账户余额  */
	public double getNowBalance(int hostelID);
	
}
