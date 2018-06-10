package com.example.resources;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response;

import org.junit.Test;

public class ByteResourceTest extends RestTest {

	@Test
	public void testStatus502() {
		Response response = target.path("byte").request().post(null);
		assertEquals(502, response.getStatus());
	}
}
