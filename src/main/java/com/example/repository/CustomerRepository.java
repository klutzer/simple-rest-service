package com.example.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.bean.Address;
import com.example.bean.Customer;
import com.example.bean.Phone;

public class CustomerRepository {

	private static Integer sequence;
	private static final Map<Integer, Customer> DATA = new HashMap<>();

	static {
		resetAndCreateData();
	}

	public List<Customer> listAll() {
		return new ArrayList<>(DATA.values());
	}

	public Customer add(Customer customer) {
		++sequence;
		customer.setId(sequence);
		DATA.put(sequence, customer);
		return customer;
	}

	public Customer update(Customer customer) {
		Customer customerBD = get(customer.getId());
		if (customerBD != null) {
			return customerBD;
		}
		throw new IllegalArgumentException("Customer with id '" + customer.getId() + "' not found!");
	}

	public Customer remove(Integer customerId) {
		return DATA.remove(customerId);
	}

	public Customer get(Integer customerId) {
		return DATA.get(customerId);
	}
	
	public static final void resetAndCreateData() {
		DATA.clear();
		sequence = 0;
		DATA.put(1, new Customer(++sequence, "Érico", LocalDate.of(1991, 1, 31), true, 99999.99, null, null));
		DATA.put(2, new Customer(++sequence, "Sílvio Santos", LocalDate.of(1933, 12, 22), false, 5000000.0, 
				new Address("São Paulo", "Ma oe", "55555-333"), Arrays.asList(
						new Phone("Celular", "(11)3333-3333"), 
						new Phone("Fixo", "(11)96969-0000"))));
	}
}
