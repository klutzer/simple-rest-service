package com.example;

import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import com.example.bean.Customer;

public class CustomerResourceTest extends RestTest {

	@Test
	public void testReturnAllCustomers() {
		List<Customer> customers = target.path("customer").request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Customer>>() {});
		MatcherAssert.assertThat(customers, Matchers.hasSize(2));
	}
}
