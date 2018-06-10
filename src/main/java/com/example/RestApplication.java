package com.example;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;

import com.example.main.Server;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

public class RestApplication extends ResourceConfig {

	public RestApplication() {
		register(JacksonFeature.class).packages(true, getClass().getPackage().getName());
		registerClasses(ApiListingResource.class, SwaggerSerializers.class);
		register(new LoggingFeature(Logger.getLogger(getClass().getName()), Level.INFO, LoggingFeature.Verbosity.PAYLOAD_TEXT, 8192));
		configureSwagger();
	}

	private void configureSwagger() {
		BeanConfig conf = new BeanConfig();
		conf.setTitle("API Exemplo para testes REST");
		conf.setDescription("Documentação interativa de exemplo da API");
		conf.setVersion("1");
		conf.setHost(Server.host + ":" + Server.port);
		conf.setBasePath("/");
		conf.setSchemes(new String[] { "http" });
		conf.setResourcePackage("com.example");
		conf.setScan(true);
	}
}
