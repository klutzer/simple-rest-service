package com.example;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.main.Server;

import static org.junit.Assert.assertEquals;

public class ByteResourceTest {

    private HttpServer server;
    private WebTarget target;
    
    public static final String BASE_URI = "http://localhost:8888";

    @Before
    public void setUp() throws Exception {
        server = Server.startServer(BASE_URI);
        Client c = ClientBuilder.newClient();
        target = c.target(BASE_URI);
    }

    @After
    public void tearDown() throws Exception {
        server.shutdownNow();
    }

    @Test
    public void testStatus502() {
        Response response = target.path("byte").request().post(null);
        assertEquals(502, response.getStatus());
    }
}
