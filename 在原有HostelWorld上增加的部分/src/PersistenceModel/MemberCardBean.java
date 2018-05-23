package PersistenceModel;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/* 会员卡 */

@Entity
@Table(name="member_card")
public class MemberCardBean implements Serializable{

	private static final long serialVersionUID = -3420358189504975572L;
	
	@Id
	private int memberID;		// 会员编号
	
	private int qualification;	// 会员卡状态
	private String bankAccount;	// 银行卡号
	private double balance;		// 会员卡余额
	private String startDate;	// 办理日期
	private String endDate;		// 失效日期
	private double totalPay;	// 总消费金额
	private int authority;		// 等级
	private int credits;		// 信用积分
	
	public int getMemberID() {
		return memberID;
	}
	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}
	public int getQualification() {
		return qualification;
	}
	public void setQualification(int qualification) {
		this.qualification = qualification;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double banlance) {
		this.balance = banlance;
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
	public double getTotalPay() {
		return totalPay;
	}
	public void setTotalPay(double totalPay) {
		this.totalPay = totalPay;
	}
	public int getAuthority() {
		return authority;
	}
	public void setAuthority(int authority) {
		this.authority = authority;
	}
	public int getCredits() {
		return credits;
	}
	public void setCredits(int credits){
		this.credits = credits;
	}
	
}
