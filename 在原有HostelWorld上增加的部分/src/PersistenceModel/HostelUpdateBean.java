package PersistenceModel;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*酒店信息更新 */

@Entity
@Table(name="hostel_update")
public class HostelUpdateBean implements Serializable{

	private static final long serialVersionUID = -8376906497273023508L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private int updateID;			// 更新编号
	
	private int checkingState;		// 审核状态
	private int applyerID;			// 申请者编号
	private String applyerName;		// 申请者姓名
	private String hostelName;		// 申请者电话
	private String briefIntro;		// 简要介绍
	
	public int getUpdateID() {
		return updateID;
	}
	public void setUpdateID(int updateID) {
		this.updateID = updateID;
	}
	public int getCheckingState() {
		return checkingState;
	}
	public void setCheckingState(int checkingState) {
		this.checkingState = checkingState;
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
	public String getHostelName() {
		return hostelName;
	}
	public void setHostelName(String hostelName) {
		this.hostelName = hostelName;
	}
	public String getBriefIntro() {
		return briefIntro;
	}
	public void setBriefIntro(String briefIntro) {
		this.briefIntro = briefIntro;
	}
	
}
