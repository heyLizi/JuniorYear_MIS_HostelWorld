package dao.Service;

import java.util.List;

import PersistenceModel.BookBean;

/* 获得会员预订信息 */

public interface GetMyBookDao {

	/* 获得会员当前预订信息 */
	public List<BookBean> getCurrentBook(int memberID, String currentDate);
	/* 获得会员历史预订信息 */
	public List<BookBean> getHistoryBook(int memberID, String currentDate);
	
}
