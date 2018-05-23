package PersistenceModel;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/* 房间 */

@Entity
@Table(name="room")
public class RoomBean implements Serializable{

	private static final long serialVersionUID = -37856550807554351L;
	
	@Id
	private int hostelID;			// 酒店编号
	@Id
	private int roomID;				// 房间编号
	
	private String roomCategory;	// 房间类型
	private boolean isCheckin;		// 是否入住
	private int latestCheckinID;	// 最近的入住编号
	
	public int getHostelID() {
		return hostelID;
	}
	public void setHostelID(int hostelID) {
		this.hostelID = hostelID;
	}
	public int getRoomID() {
		return roomID;
	}
	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}
	public String getRoomCategory() {
		return roomCategory;
	}
	public void setRoomCategory(String roomCategory) {
		this.roomCategory = roomCategory;
	}
	public boolean getIsCheckin() {
		return isCheckin;
	}
	public void setIsCheckin(boolean isCheckin) {
		this.isCheckin = isCheckin;
	}
	public int getLatestCheckinID() {
		return latestCheckinID;
	}
	public void setLatestCheckinID(int latestCheckinID) {
		this.latestCheckinID = latestCheckinID;
	}
	
}
