package com.example;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import com.example.main.Server;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

public class RestApplication extends ResourceConfig {

    public RestApplication() {
        register(JacksonFeature.class).packages(true, getClass().getPackage().getName());
        registerClasses(ApiListingResource.class, SwaggerSerializers.class);
        configureSwagger();
    }

    private void configureSwagger() {
        BeanConfig conf = new BeanConfig();
        conf.setTitle("API Exemplo para testes REST");
        conf.setDescription("Documentação interativa de exemplo da API");
        conf.setVersion("1");
        conf.setHost(Server.host + ":" + Server.port);
        conf.setBasePath("/");
        conf.setSchemes(new String[]{"http"});
        conf.setResourcePackage("com.example");
        conf.setScan(true);
    }
}
