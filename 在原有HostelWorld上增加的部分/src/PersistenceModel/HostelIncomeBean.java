package PersistenceModel;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/* 酒店收入管理 */

@Entity
@Table(name="hostel_income")
public class HostelIncomeBean implements Serializable{
	
	private static final long serialVersionUID = -2148070979151832714L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int incomeID;			// 收入编号
	
	private int hostelID;			// 酒店编号
	private String roomCategory;	// 房间类型
	private String payDate;			// 结账日期
	private int memberAuthority;	// 会员等级
	private boolean isBook;         // 是否预订
	private boolean isPromotion;	// 当前房间类型在入住时段内是否有优惠
	private double originalCost;	// 原本价格
	private double discountCost;   	// 折扣后价格
	private boolean isSettled;      // 是否已被结算
	
	
	public int getIncomeID() {
		return incomeID;
	}
	public void setIncomeID(int incomeID) {
		this.incomeID= incomeID;
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
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	public int getMemberAuthority() {
		return memberAuthority;
	}
	public void setMemberAuthority(int memberAuthority) {
		this.memberAuthority = memberAuthority;
	}
	public boolean getIsBook() {
		return isBook;
	}
	public void setIsBook(boolean isBook) {
		this.isBook = isBook;
	}
	public boolean getIsPromotion() {
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
	public boolean getIsSettled() {
		return isSettled;
	}
	public void setIsSettled(boolean isSettled) {
		this.isSettled = isSettled;
	}
	
}
