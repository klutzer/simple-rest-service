package com.example.auth;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.BasicDefinition;
import com.example.bean.BasicResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@Api("Testes de autenticação")
@Path("hello")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthenticationResource {

    @Path("auth")
    @GET
    @Authenticated
    @ApiOperation(value = "Método autenticado. Necessário autorizar com o usuário 'user' e senha '123456'", authorizations = @Authorization(BasicDefinition.BASIC_AUTH_SCHEME))
    public BasicResponse helloAuth() {
        return new BasicResponse(true, "Hello Authenticated!");
    }

    @GET
    public BasicResponse hello() {
        return new BasicResponse(true, "Hello!");
    }
}
