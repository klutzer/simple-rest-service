package com.example.resources;

import java.time.LocalDate;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Test;

import com.example.bean.Customer;
import com.example.repository.CustomerRepository;

public class CustomerResourceTest extends RestTest {

	@After
	public void tearDown() {
		CustomerRepository.resetAndCreateData();
	}

	@Test
	public void testReturnAllCustomers() {
		List<Customer> customers = target.path("customer").request().get(new GenericType<List<Customer>>() {});
		MatcherAssert.assertThat(customers, Matchers.hasSize(2));
	}

	@Test
	public void testPostingNewCustomer() {
		Customer customer = new Customer(-1, "Sandra Rosa Madalena", LocalDate.of(1960, 1, 1), true, 5000.0, null, null);
		Customer returned = target.path("customer").request().post(Entity.json(customer), Customer.class);
		MatcherAssert.assertThat(returned.getId(), Matchers.greaterThan(2));
	}

	@Test
	public void testDeleteCustomer() {
		Customer customer = target.path("customer").path("1").request().delete(Customer.class);
		MatcherAssert.assertThat(customer.getName(), Matchers.equalTo("Érico"));
	}
}
