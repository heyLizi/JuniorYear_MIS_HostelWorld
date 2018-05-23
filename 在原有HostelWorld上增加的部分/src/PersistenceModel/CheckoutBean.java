package PersistenceModel;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/* 离宿 */

@Entity
@Table(name="checkout")
public class CheckoutBean implements Serializable{

	private static final long serialVersionUID = -1424580264799252986L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int checkoutID;		// 离宿编号
	
	private int hostelID;		// 酒店编号
	private String roomCategory;// 房间类型
	private int roomID;			// 房间编号
	private int checkinID;		// 入住编号
	private String checkoutDate;// 实际离宿日期
	private int periodSpan;		// 实际消费日期跨度
	private boolean isPromotion;// 当前房间类型在入住时段内是否有优惠
	private double originalCost;	// 原本价格
	private double discountCost;   // 折扣后价格
	
	public int getCheckoutID() {
		return checkoutID;
	}
	public void setCheckoutID(int checkoutID) {
		this.checkoutID = checkoutID;
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
	public int getCheckinID() {
		return checkinID;
	}
	public void setCheckinID(int checkinID) {
		this.checkinID = checkinID;
	}
	public String getCheckoutDate() {
		return checkoutDate;
	}
	public void setCheckoutDate(String checkoutDate) {
		this.checkoutDate = checkoutDate;
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
	public double getOriginalCost() {
		return originalCost;
	}
	public void setOriginalCost(double originalCost) {
		this.originalCost = originalCost;
	}
	public double getDiscountCost() {
		return discountCost;
	}
	public void setDiscountCost(double discountCost) {
		this.discountCost = discountCost;
	}
	
}
