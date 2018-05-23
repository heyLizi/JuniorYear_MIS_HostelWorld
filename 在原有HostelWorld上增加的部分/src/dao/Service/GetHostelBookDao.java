package dao.Service;

import java.util.List;

import PersistenceModel.BookBean;

/* 获得酒店预订信息 */

public interface GetHostelBookDao {

	/* 获得酒店预订信息 */
	public List<BookBean> getHostelBook(int hostelID);
	/* 获得某会员在某酒店的预订信息 */
	public List<BookBean> getHostelBook(int hostelID, int memberID);
	
}
