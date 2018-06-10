package com.example;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.After;
import org.junit.Before;

import com.example.main.Server;

public abstract class RestTest {

	private HttpServer server;
	protected WebTarget target;

	@Before
	public void setUp() throws Exception {
		// deixar o SO escolher a porta, para evitar utilizar uma porta já em uso
		server = Server.startServer("http://localhost:0");
		// buscando os dados da porta que foi escolhida...
		NetworkListener networkListener = server.getListener("grizzly");
		String serverUrl = String.format("http://%s:%s", networkListener.getHost(), networkListener.getPort());
		target = createClientForTest().target(serverUrl);
	}

	@After
	public void tearDown() throws Exception {
		server.shutdownNow();
	}

	private Client createClientForTest() {
		Client client = ClientBuilder.newClient();
		client.register(JacksonFeature.class);
		client.register(JsonProvider.class);
		return client;
	}

}
