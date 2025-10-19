package com.ra2.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ra2.Model.Customer;
import com.ra2.Repository.CustomerRepository;

@RestController
@RequestMapping("/jdbctemplate")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRep;
	
	@GetMapping("/customersDB")
	public String create_insertDB() {
		customerRep.createTableCustomers();
		customerRep.insertSampleData();
		return "taula creada correctament i dades inserides";
	}
	
	@GetMapping("/findCustomers")
	public List<Customer> getAllCustomers(){
		return customerRep.findAll();
	}
}
