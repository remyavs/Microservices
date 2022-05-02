package demo.service;

import demo.model.Customer;
import demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService
{
	@Autowired
	CustomerRepository customerRepository;
	
	public List<Customer> getAllCustomer()
	{
		List<Customer> customers = new ArrayList<Customer>();
		customerRepository.findAll().forEach(customer -> customers.add(customer));
		return customers;
	}
	
	public Customer getCustomerById(int id)
	{
		return customerRepository.findById(id).get();
	}

	public boolean isCustomerPresent(int id)
	{
		return customerRepository.findById(id).isEmpty();
	}
	public void createCustomer(Customer customer)
	{
		customerRepository.save(customer);
	}

	public void deleteCustomer(int id)
	{
		customerRepository.deleteById(id);
	}

	public void updateCustomer(Customer customer) { customerRepository.save(customer); }
	
}
