package PersistenceModel;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/* 酒店账户  */

@Entity
@Table(name="hostel_account")
public class HostelAccountBean implements Serializable{

	private static final long serialVersionUID = -4330362626032835442L;
	
	@Id
	private int hostelID;			// 酒店编号
	
	private double hostelBalance;	// 酒店余额
	
	public int getHostelID() {
		return hostelID;
	}
	public void setHostelID(int hostelID) {
		this.hostelID = hostelID;
	}
	public double getHostelBalance() {
		return hostelBalance;
	}
	public void setHostelBalance(double hostelBalance) {
		this.hostelBalance = hostelBalance;
	}

}
