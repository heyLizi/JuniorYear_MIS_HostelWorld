package PersistenceModel;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/* 酒店申请 */

@Entity
@Table(name="hostel_application")
public class HostelApplicationBean implements Serializable{

	private static final long serialVersionUID = -3611181999304739860L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int applicationID;		// 申请编号
	
	private int checkState;			// 审核状态
	private int applyerID;			// 申请者编号
	private String applyerName;		// 申请者姓名
	private String applyerPhone;	// 申请者电话
	private int hostelID;			// 酒店编号
	private String hostelName;		// 酒店名称
	private String hostelProvince;	// 酒店所在省
	private String hostelCity;		// 酒店所在市
	private String hostelAddress;	// 酒店地址
	private int singleRoomNum;		// 单人间数量
	private int standardRoomNum;	// 标准间数量
	private int suiteRoomNum;		// 套间数量
	private String briefIntro;		// 简要介绍
	
	public int getApplicationID() {
		return applicationID;
	}
	public void setApplicationID(int applicationID) {
		this.applicationID = applicationID;
	}
	public int getCheckState() {
		return checkState;
	}
	public void setCheckState(int checkState) {
		this.checkState = checkState;
	}
	public int getApplyerID() {
		return applyerID;
	}
	public void setApplyerID(int applyerID) {
		this.applyerID = applyerID;
	}
	public String getApplyerName() {
		return applyerName;
	}
	public void setApplyerName(String applyerName) {
		this.applyerName = applyerName;
	}
	public String getApplyerPhone() {
		return applyerPhone;
	}
	public void setApplyerPhone(String applyerPhone) {
		this.applyerPhone = applyerPhone;
	}
	public int getHostelID() {
		return hostelID;
	}
	public void setHostelID(int hostelID) {
		this.hostelID = hostelID;
	}
	public String getHostelName() {
		return hostelName;
	}
	public void setHostelName(String hostelName) {
		this.hostelName = hostelName;
	}
	public String getHostelProvince() {
		return hostelProvince;
	}
	public void setHostelProvince(String hostelProvince) {
		this.hostelProvince = hostelProvince;
	}
	public String getHostelCity() {
		return hostelCity;
	}
	public void setHostelCity(String hostelCity) {
		this.hostelCity = hostelCity;
	}
	public String getHostelAddress() {
		return hostelAddress;
	}
	public void setHostelAddress(String hostelAddress) {
		this.hostelAddress = hostelAddress;
	}
	public int getSingleRoomNum() {
		return singleRoomNum;
	}
	public void setSingleRoomNum(int singleRoomNum) {
		this.singleRoomNum = singleRoomNum;
	}
	public int getStandardRoomNum() {
		return standardRoomNum;
	}
	public void setStandardRoomNum(int standardRoomNum) {
		this.standardRoomNum = standardRoomNum;
	}
	public int getSuiteRoomNum() {
		return suiteRoomNum;
	}
	public void setSuiteRoomNum(int suiteRoomNum) {
		this.suiteRoomNum = suiteRoomNum;
	}
	public String getBriefIntro() {
		return briefIntro;
	}
	public void setBriefIntro(String briefIntro) {
		this.briefIntro = briefIntro;
	}
	
}
