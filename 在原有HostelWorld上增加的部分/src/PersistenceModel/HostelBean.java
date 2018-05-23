package PersistenceModel;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/* 酒店 */

@Entity
@Table(name="hostel")
public class HostelBean implements Serializable{

	private static final long serialVersionUID = 1204535038965011933L;
	
	@Id
	private int hostelID;			// 酒店编号
	
	private String hostelName;		// 酒店名称
	private String hostelProvince;	// 酒店所在省
	private String hostelCity;		// 酒店所在市
	private String hostelAddress;	// 酒店地址
	private int singleRoomNum;		// 单人间数量
	private int standardRoomNum;	// 标准间数量
	private int suiteRoomNum;		// 套间数量
    private String briefIntro;		// 简要介绍
    
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
