package PersistenceModel;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/* 经理 */

@Entity
@Table(name="manager")
public class ManagerBean implements Serializable{

	private static final long serialVersionUID = 1743550167115648656L;
	
	@Id
	private String managerID;		// 经理编号
	
	private String password;		// 经理密码
	private String managerName;		// 经理姓名
	private String managerSex;		// 经理性别
	private String managerPhone;	// 经理电话
	
	public String getManagerID() {
		return managerID;
	}
	public void setManagerID(String managerID) {
		this.managerID = managerID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerSex() {
		return managerSex;
	}
	public void setManagerSex(String managerSex) {
		this.managerSex = managerSex;
	}
	public String getManagerPhone() {
		return managerPhone;
	}
	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}
	
}
