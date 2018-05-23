package dao.Service;

/* 入住办理 */

public interface CheckinDao {

	/* 入住办理 */
	public int checkin(int hostelID, String roomCategory, boolean isBook, int bookID, 
			           boolean isMember, int memberID, String checkinDate);
	
}
