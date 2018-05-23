package PersistenceModel;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/* 会员 */

@Entity
@Table(name="member")
public class MemberBean implements Serializable{
	
	private static final long serialVersionUID = 2973694753085145152L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int memberID;		// 会员编号
	
	private String password;	// 会员密码
	private String memberName;	// 会员姓名
	private String memberSex;	// 会员性别
	private String memberPhone;	// 会员电话
	
	public int getMemberID() {
		return memberID;
	}
	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberSex() {
		return memberSex;
	}
	public void setMemberSex(String memberSex) {
		this.memberSex = memberSex;
	}
	public String getMemberPhone() {
		return memberPhone;
	}
	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}
	
}
