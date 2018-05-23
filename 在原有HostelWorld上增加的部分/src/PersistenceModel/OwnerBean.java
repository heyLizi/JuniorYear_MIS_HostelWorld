package PersistenceModel;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/* 店家 */

@Entity
@Table(name="owner")
public class OwnerBean implements Serializable{

	private static final long serialVersionUID = -8503051901292263169L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private int ownerID;		// 店家编号
	
	private String password;	// 店家密码
	private String ownerName;	// 店家姓名
	private String ownerSex;	// 店家性别
	private String ownerPhone;	// 店家电话
	
	public int getOwnerID() {
		return ownerID;
	}
	public void setOwnerID(int ownerID) {
		this.ownerID = ownerID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getOwnerSex() {
		return ownerSex;
	}
	public void setOwnerSex(String ownerSex) {
		this.ownerSex = ownerSex;
	}
	public String getOwnerPhone() {
		return ownerPhone;
	}
	public void setOwnerPhone(String ownerPhone) {
		this.ownerPhone = ownerPhone;
	}
	
}
