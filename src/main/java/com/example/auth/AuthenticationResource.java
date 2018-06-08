package com.example.auth;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.BasicDefinition;
import com.example.BasicResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.Authorization;

@Api(value = "Authentication tests", authorizations = @Authorization(BasicDefinition.BASIC_AUTH_SCHEME))
@Path("hello")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthenticationResource {

    @Path("auth")
    @GET
    @Authenticated
    public BasicResponse helloAuth() {
        return new BasicResponse(true, "Hello Authenticated!");
    }

    @GET
    public BasicResponse hello() {
        return new BasicResponse(true, "Hello!");
    }
}
