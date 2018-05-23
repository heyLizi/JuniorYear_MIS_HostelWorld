package PersistenceModel;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/* 优惠计划信息 */

@Entity
@Table(name="promotion_plan")
public class PromotionPlanBean implements Serializable{

	private static final long serialVersionUID = 3365383535948046650L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int promotionID;		// 优惠编号
	
	private String promotionName;	// 优惠名称
	private int hostelID;			// 酒店编号
	private double singleRoomFee;	// 单人间优惠价格
	private double standardRoomFee;	// 标准间优惠价格
	private double suiteRoomFee;	// 套间优惠价格
	private String startDate;		// 开始日期
	private String endDate;			// 结束日期
	
	public int getPromotionID() {
		return promotionID;
	}
	public void setPromotionID(int promotionID) {
		this.promotionID = promotionID;
	}
	public String getPromotionName() {
		return promotionName;
	}
	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}
	public int getHostelID() {
		return hostelID;
	}
	public void setHostelID(int hostelID) {
		this.hostelID = hostelID;
	}
	public double getSingleRoomFee() {
		return singleRoomFee;
	}
	public void setSingleRoomFee(double singleRoomFee) {
		this.singleRoomFee = singleRoomFee;
	}
	public double getStandardRoomFee() {
		return standardRoomFee;
	}
	public void setStandardRoomFee(double standardRoomFee) {
		this.standardRoomFee = standardRoomFee;
	}
	public double getSuiteRoomFee() {
		return suiteRoomFee;
	}
	public void setSuiteRoomFee(double suiteRoomFee) {
		this.suiteRoomFee = suiteRoomFee;
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
	
}
