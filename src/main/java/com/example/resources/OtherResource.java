package com.example.resources;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.example.bean.BasicResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api("Outras situações")
@Path("other")
public class OtherResource {

	@ApiOperation("Operação que não irá retornar nada")
	@ApiResponses(@ApiResponse(code = 204, message = "Sem retorno"))
	@GET
	public void noReturn() {
		System.out.println("Recebida requisição, porém não será retornado nada");
	}

	@ApiOperation("Método POST porém sem corpo na requisição (utilizar o curl para forçar requisição sem corpo)")
	@POST
	public BasicResponse postWithoutBody(String request) {
		return new BasicResponse(true, "Recebi requisição: " + request);
	}

	@ApiOperation("Redireciona para um link com referências sobre os métodos HTTP")
	@Path("references")
	@GET
	@Produces("")
	public Response seeOther() throws URISyntaxException {
		return Response.seeOther(new URI(
				"https://assertible.com/blog/7-http-methods-every-web-developer-should-know-and-how-to-test-them"))
				.build();
	}
}
