package PersistenceModel;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/* 预订酒店 */

@Entity
@Table(name="book")
public class BookBean implements Serializable{

	private static final long serialVersionUID = 5342140882389324750L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int bookID;			// 预订编号

	private int hostelID;		// 酒店编号
	private int memberID;		// 会员编号
	private String roomCategory;// 房间类型
	private String bookTime;	// 预定日期
	private String startDate;	// 预计入住日期
	private String endDate;		// 预计离宿日期
	private int periodSpan;		// 预计消费日期跨度
	private boolean isPromotion;// 当前房间类型在预订时段内是否有优惠
	private int advanceTime;	// 预订提前时间
	private boolean isSettled;  // 是否被结算
	
	public int getBookID() {
		return bookID;
	}
	public void setBookID(int bookID) {
		this.bookID = bookID;
	}
	public int getHostelID() {
		return hostelID;
	}
	public void setHostelID(int hostelID) {
		this.hostelID = hostelID;
	}
	public int getMemberID() {
		return memberID;
	}
	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}
	public String getRoomCategory() {
		return roomCategory;
	}
	public void setRoomCategory(String roomCategory) {
		this.roomCategory = roomCategory;
	}
	public String getBookTime() {
		return bookTime;
	}
	public void setBookTime(String bookTime) {
		this.bookTime = bookTime;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getPeriodSpan() {
		return periodSpan;
	}
	public void setPeriodSpan(int periodSpan) {
		this.periodSpan = periodSpan;
	}
	public boolean isPromotion() {
		return isPromotion;
	}
	public void setIsPromotion(boolean isPromotion) {
		this.isPromotion = isPromotion;
	}
	public int getAdvanceTime() {
		return advanceTime;
	}
	public void setAdvanceTime(int advanceTime) {
		this.advanceTime = advanceTime;
	}
	public boolean getIsSettled() {
		return isSettled;
	}
	public void setIsSettled(boolean isSettled) {
		this.isSettled = isSettled;
	}
	
}
