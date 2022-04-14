package demo.model;

//import javax.persistence.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class StudentFee
{
	@Id

	@Column
	private int id;

	@Column
	private String student_name;

	@Column
	private int class_name;

	@Column
	private int totalFees;

	@Column
	private int feesPaid;

	@Column
	private int remainingFees;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStudent_name() {
		return student_name;
	}

	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}

	public int getClass_name() {
		return class_name;
	}

	public void setClass_name(int class_name) {
		this.class_name = class_name;
	}

	public int getTotalFees() {
		return totalFees;
	}

	public void setTotalFees(int totalFees) {
		this.totalFees = totalFees;
	}

	public int getFeesPaid() {
		return feesPaid;
	}

	public void setFeesPaid(int feesPaid) {
		this.feesPaid = feesPaid;
	}

	public int getRemainingFees() {
		return remainingFees;
	}

	public void setRemainingFees(int remainingFees) {
		this.remainingFees = remainingFees;
	}
}
