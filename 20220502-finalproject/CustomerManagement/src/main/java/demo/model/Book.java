package demo.model;

//import javax.persistence.*;
import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Book
{
	@Id
	//defining id as column name
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int isbn;
	@Column
	private String title;
	//defining age as column name
	@Column
	private String published_date;
	//defining email as column name
	@Column
	private int totalCopies;
	@Column
	private int issuedCopies;
	@Column
	private String author;

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublished_date() {
		return published_date;
	}

	public void setPublished_date(String published_date) {
		this.published_date = published_date;
	}

	public int getTotalCopies() {
		return totalCopies;
	}

	public void setTotalCopies(int totalCopies) {
		this.totalCopies = totalCopies;
	}

	public int getIssuedCopies() {
		return issuedCopies;
	}

	public void setIssuedCopies(int issuedCopies) {
		this.issuedCopies = issuedCopies;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
}
