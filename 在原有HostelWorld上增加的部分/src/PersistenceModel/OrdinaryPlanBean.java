package PersistenceModel;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/* 正常营业计划信息 */

@Entity
@Table(name="ordinary_plan")
public class OrdinaryPlanBean implements Serializable{

	private static final long serialVersionUID = 3365383535948046650L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int hostelID;			// 旅馆编号
	
	private double singleRoomFee;	// 单人间正常价格
	private double standardRoomFee;	// 标准间正常价格
	private double suiteRoomFee;	// 套间正常价格

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
		
}
