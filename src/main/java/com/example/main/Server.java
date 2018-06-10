package com.example.main;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.CLStaticHttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.servlet.WebappContext;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.servlet.ServletContainer;

import com.example.RestApplication;

public class Server {
	private static final String DEFAULT_HOST = "localhost";
	private static final String DEFAULT_PORT = "8888";
	private static final String URL = "http://%s:%s";
	public static String host;
	public static String port;

	public static void main(String[] args) throws IOException {
		host = DEFAULT_HOST;
		port = DEFAULT_PORT;
		if (args != null && args.length > 0) {
			host = args[0];
			if (args.length > 1) {
				port = args[1];
			}
		}
		System.out.println("Iniciando servico...");
		String url = String.format(URL, host, port);

		final HttpServer server = startServer(url);
		System.out.println("=======================================================================================");
		System.out.println("Servico REST criado com sucesso!");
		System.out.printf("WADL disponivel em %s/application.wadl%n", url);
		System.out.printf("Documentação interativa (swagger) disponivel em %s%n", url);
		System.out.println("Pressine ENTER para parar...");
		System.in.read();
		server.shutdownNow();
	}

	public static HttpServer startServer(String url) {
		WebappContext context = new WebappContext("api");
		context.addServlet("ServletContainer", new ServletContainer(new RestApplication())).addMapping("/*");
		HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create(url));
		ClassLoader classLoader = Server.class.getClassLoader();
		server.getServerConfiguration().addHttpHandler(new CLStaticHttpHandler(classLoader, "webapp/docs/"), "/docs");
		context.deploy(server);
		return server;
	}
}
