package PersistenceModel;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/* 入住 */

@Entity
@Table(name="checkin")
public class CheckinBean implements Serializable{

	private static final long serialVersionUID = -1424580264799252986L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int checkinID;		// 入住编号
	
	private int hostelID;		// 酒店编号
	private String roomCategory;// 房间类型
	private int roomID;			// 房间编号
	private boolean isBook;		// 是否预订
	private int bookID;			// 预订编号
	private boolean isMember;	// 是否会员
	private int memberID;		// 会员编号
	private String checkinDate;	// 实际入住日期
	
	public int getCheckinID() {
		return checkinID;
	}
	public void setCheckinID(int checkinID) {
		this.checkinID = checkinID;
	}
	public int getHostelID() {
		return hostelID;
	}
	public void setHostelID(int hostelID) {
		this.hostelID = hostelID;
	}
	public String getRoomCategory() {
		return roomCategory;
	}
	public void setRoomCategory(String roomCategory) {
		this.roomCategory = roomCategory;
	}
	public int getRoomID() {
		return roomID;
	}
	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}
	public boolean isBook() {
		return isBook;
	}
	public void setIsBook(boolean isBook) {
		this.isBook = isBook;
	}
	public int getBookID() {
		return bookID;
	}
	public void setBookID(int bookID) {
		this.bookID = bookID;
	}
	public boolean isMember() {
		return isMember;
	}
	public void setIsMember(boolean isMember) {
		this.isMember = isMember;
	}
	public int getMemberID() {
		return memberID;
	}
	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}
	public String getCheckinDate() {
		return checkinDate;
	}
	public void setCheckinDate(String checkinDate) {
		this.checkinDate = checkinDate;
	}
	
}
