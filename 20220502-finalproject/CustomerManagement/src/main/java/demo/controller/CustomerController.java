package demo.controller;


import java.util.List;

import demo.model.Book;
import demo.model.Customer;
import demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/customer")
public class CustomerController
{
	@Autowired
	CustomerService customerService;

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate()
	{
		return new RestTemplate();
	}

	@Autowired
	private RestTemplate restTemplate;


	static final String BOOK_URL="http://book-service/book/";


	@GetMapping("/fetchBook/{id}")
	public String fetchBook(@PathVariable("id") int id)
	{
		//Book book =	restTemplate.exchange(STUDENTFEE_URL+"getbook/"+id, HttpMethod.GET, null, Book.class).getBody();
		//System.out.println("Student fee from fee management project : " + book);
		return restTemplate.exchange(BOOK_URL+"getBookById"+"/"+id, HttpMethod.GET, null, String.class).getBody();
	}



	@GetMapping("/fetchAllBooks")
	public String fetchAllBooks()
	{
		return restTemplate.exchange(BOOK_URL+"listbook",HttpMethod.GET,null,String.class).getBody();
	}


	@PostMapping("/addbook")
	public String addbook(@RequestBody Book book)
	{
		return restTemplate.postForObject(BOOK_URL+"createBook", book, String.class);
	}

	@GetMapping("/listCustomers")
	private List<Customer> getAllCustomers()
	{
		return customerService.getAllCustomer();
	}


	@GetMapping("/getCustomer/{id}")
	private Customer getCustomer(@PathVariable("id") int id)
	{
		return customerService.getCustomerById(id);
	}


	@DeleteMapping("/deleteCustomer/{id}")
	private void deleteCustomer(@PathVariable("id") int id)
	{
		customerService.deleteCustomer(id);
	}


	@PostMapping("/createCustomer")
	private int createCustomer(@RequestBody Customer customer)
	{
		customerService.createCustomer(customer);
		return customer.getIsbn();
	}


	@PostMapping("/updateCustomer")
	private int updateCustomer(@RequestBody Customer customer)
	{
		customerService.updateCustomer(customer);
		return customer.getIsbn();
	}

	@PostMapping("/issuebook")
	private ResponseEntity<String> issueBookToCustomer(@RequestBody Customer customer)
	{
		//customerService.createCustomer(customer);
		//createCustomer(customer);
		Integer custId = customer.getCustId();
		Integer bookId = customer.getIsbn();
		Integer issuesNoCopies = customer.getNoOfCopies();
		System.out.println("No of copies to issue = "+issuesNoCopies);
		Integer availableCopies = restTemplate.exchange(BOOK_URL+"getAvailableCopies/"+bookId,HttpMethod.GET,null,Integer.class).getBody();
		if(availableCopies >= issuesNoCopies) {

			if (customerService.isCustomerPresent(custId)) {
				System.out.println("Customer does not exists");
			} else {
				System.out.println("customer exist");
				Customer custExist = customerService.getCustomerById(custId);
				if (bookId == custExist.getIsbn()) {
					System.out.println("Customer having same book already");
					Integer custnoCopies = custExist.getNoOfCopies();
					customer.setNoOfCopies(custnoCopies+issuesNoCopies);
				}
			}
			createCustomer(customer);
		}
			return restTemplate.exchange(BOOK_URL + "updateBookLib/" + bookId + "/" + issuesNoCopies, HttpMethod.PUT, null, String.class);

	}

	@PostMapping("/cancelBookIssued/{id}")
	private void cancelBookIssued(@PathVariable("id") int id)
	{
		System.out.println("Inside Cancelbook");
		Customer customer = customerService.getCustomerById(id);
		Integer bookId = customer.getIsbn();
		Integer issuesNoCopies = customer.getNoOfCopies();
		customer.setNoOfCopies(0);
		customerService.updateCustomer(customer);
		System.out.println("customer updated");

		restTemplate.exchange(BOOK_URL+"cancelBookIssued/"+bookId+"/"+issuesNoCopies,HttpMethod.PUT,null,String.class);
 	}

	}
