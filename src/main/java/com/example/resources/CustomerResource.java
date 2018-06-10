package com.example.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.bean.Customer;
import com.example.repository.CustomerRepository;

import io.swagger.annotations.Api;

@Api("Cliente")
@Path("customer")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {

	private CustomerRepository repository = new CustomerRepository();

	@GET
	public List<Customer> listAll() {
		return repository.listAll();
	}

	@GET
	@Path("{id}")
	public Customer get(@PathParam("id") Integer customerId) {
		return repository.get(customerId);
	}

	@POST
	public Customer add(Customer customer) {
		return repository.add(customer);
	}

	@PUT
	public Customer update(Customer customer) {
		return repository.update(customer);
	}

	@DELETE
	@Path("{id}")
	public Customer remove(@PathParam("id") Integer customerId) {
		return repository.remove(customerId);
	}
}
