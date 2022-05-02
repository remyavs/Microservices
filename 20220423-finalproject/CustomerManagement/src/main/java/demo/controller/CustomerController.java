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

	//static final String STUDENTFEE_URL="http://localhost:8083/";
	static final String STUDENTFEE_URL="http://book-service/book/";
	//http://localhost:8080/fetchstudentfee/id

	@GetMapping("/fetchBook/{id}")
	public String fetchBook(@PathVariable("id") int id)
	{
		//Book book =	restTemplate.exchange(STUDENTFEE_URL+"getbook/"+id, HttpMethod.GET, null, Book.class).getBody();
		//System.out.println("Student fee from fee management project : " + book);
		return restTemplate.exchange(STUDENTFEE_URL+"getBookById"+"/"+id, HttpMethod.GET, null, String.class).getBody();
	}

	//http://localhost:8080/fetchAllStudentFee

	@GetMapping("/fetchAllBooks")
	public String fetchAllBooks()
	{
		return restTemplate.exchange(STUDENTFEE_URL+"listbook",HttpMethod.GET,null,String.class).getBody();
	}

	//http://localhost:8080/addStudentFee
	@PostMapping("/addbook")
	public String addbook(@RequestBody Book book)
	{
		return restTemplate.postForObject(STUDENTFEE_URL+"createBook", book, String.class);
	}

	//http://localhost:8080/liststudent
	@GetMapping("/listCustomers")
	private List<Customer> getAllCustomers()
	{
		return customerService.getAllCustomer();
	}

	//http://localhost:8080/getstudent/id
	@GetMapping("/getCustomer/{id}")
	private Customer getCustomer(@PathVariable("id") int id)
	{
		return customerService.getCustomerById(id);
	}

	//http://localhost:8080/deletestudent/id
	@DeleteMapping("/deleteCustomer/{id}")
	private void deleteCustomer(@PathVariable("id") int id)
	{
		customerService.deleteCustomer(id);
	}

	//http://localhost:8080/createstudent
	@PostMapping("/createCustomer")
	private int createCustomer(@RequestBody Customer customer)
	{
		customerService.createCustomer(customer);
		return customer.getIsbn();
	}

	//http://localhost:8080/updatestudent
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
		Integer availableCopies = restTemplate.exchange(STUDENTFEE_URL+"getAvailableCopies/"+bookId,HttpMethod.GET,null,Integer.class).getBody();
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
			return restTemplate.exchange(STUDENTFEE_URL + "updateBookLib/" + bookId + "/" + issuesNoCopies, HttpMethod.PUT, null, String.class);

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

		restTemplate.exchange(STUDENTFEE_URL+"cancelBookIssued/"+bookId+"/"+issuesNoCopies,HttpMethod.PUT,null,String.class);
 	}

	}
