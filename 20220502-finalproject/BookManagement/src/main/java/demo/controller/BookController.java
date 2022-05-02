package demo.controller;

import java.util.List;

import demo.model.Book;
import demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/book")
public class BookController
{
	@Autowired
	BookService bookService;

	@GetMapping("/listbook")
	private List<Book> getAllBooks()
	{
		return bookService.getAllBooks();
	}


	@GetMapping("/getBookById/{id}")
	private Book getBook(@PathVariable("id") int id)
	{
		return bookService.getBookById(id);
	}


	@DeleteMapping("/deleteBookById/{id}")
	private void deleteBook(@PathVariable("id") int id)
	{
		bookService.deleteBook(id);
	}


	@PostMapping("/createBook")
	private int createBook(@RequestBody Book book)
	{
		bookService.createBook(book);
		return book.getIsbn();
	}

	@PostMapping("/updateBook")
	private int updateBook(@RequestBody Book book)
	{
		bookService.updateBook(book);
		return book.getIsbn();
	}


	@GetMapping("getAvailableCopies/{bookId}")
	private Integer getAvailableCopies(@PathVariable("bookId")int bookId)
	{
		Book bookRet = bookService.getBookById(bookId);
		Integer availableCopies = bookRet.getTotalCopies() - bookRet.getIssuedCopies();
		return availableCopies;
	}

	@PutMapping("updateBookLib/{bookId}/{noOfCopies}")
	private String updateBookLib(@PathVariable("bookId")int bookId, @PathVariable("noOfCopies") int noOfCopies)
	{
		Book bookRet = bookService.getBookById(bookId);
		Integer availableCopies = bookRet.getTotalCopies() - bookRet.getIssuedCopies();
		if(availableCopies >= noOfCopies) {
			bookRet.setIssuedCopies(bookRet.getIssuedCopies() + noOfCopies);
			bookService.updateBook(bookRet);
			 return "Book Issued to customer, book id = " + bookRet.getIsbn();
		}
		else
		{
			return "Book is not issued to customer as there are no available copies of book , book id = " + bookId;
		}
	}

	@PutMapping("cancelBookIssued/{bookId}/{noOfCopies}")
	private String cancelBookIssued(@PathVariable("bookId")int bookId, @PathVariable("noOfCopies") int noOfCopies)
	{
		Book bookRet = bookService.getBookById(bookId);
		bookRet.setIssuedCopies(bookRet.getIssuedCopies() - noOfCopies);
		bookService.updateBook(bookRet);
		return "Book Issued to customer is cancelled, book id = " + bookRet.getIsbn();
	}
}
