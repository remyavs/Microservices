package demo.model;

//import javax.persistence.*;

import javax.persistence.*;

@Entity
@Table
public class Customer
{
	@Column
	private int isbn;
	@Column
	private String custName;
	@Id
	@Column
	private int custId;
	@Column
	private int noOfCopies;

	public Customer() {
	}

	public Customer(int isbn, String custName, int custId, int noOfCopies) {
		this.isbn = isbn;
		this.custName = custName;
		this.custId = custId;
		this.noOfCopies = noOfCopies;
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public int getNoOfCopies() {
		return noOfCopies;
	}

	public void setNoOfCopies(int noOfCopies) {
		this.noOfCopies = noOfCopies;
	}
}
