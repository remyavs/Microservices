package demo.service;

import java.util.ArrayList;
import java.util.List;

import demo.model.Book;
import demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService
{
	@Autowired
	BookRepository bookRepository;
	
	public List<Book> getAllBooks()
	{
		List<Book> books = new ArrayList<Book>();
		bookRepository.findAll().forEach(book -> books.add(book));
		return books;
	}
	
	public Book getBookById(int id)
	{
		return bookRepository.findById(id).get();
	}
	
	public void createBook(Book book)
	{
		//book.setRemainingFees(book.getTotalFees() - studentFee.getFeesPaid());

		bookRepository.save(book);
	}

	public void deleteBook(int id)
	{
		bookRepository.deleteById(id);
	}

	public void updateBook(Book book) { bookRepository.save(book); }
	
}
