package dao.Service;

/* 预订房间 */

public interface BookRoomDao {

	/* 预订房间 */
	public void bookRoom(int hostelID, int memberID, String roomCategory, String bookTime, 
			             String startDate, String endDate, int periodSpan, boolean isPromotion, int advanceTime);
	
}
