package com.example.resources;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.BodyPartEntity;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.example.bean.BasicResponse;
import com.example.bean.Customer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("Upload de arquivos")
@Path("upload")
public class FileResource {

	@ApiImplicitParams({ @ApiImplicitParam(required = true, dataType = "file", paramType = "formData", name = "file") })
	@POST
	@Path("single")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public BasicResponse upload(@FormDataParam("file") InputStream is,
			@FormDataParam("file") FormDataContentDisposition fileDetails) {
		BasicResponse response = new BasicResponse();
		response.setMessage("Loaded " + fileDetails.getFileName() + " with content:\n" + readContent(is));
		response.setSuccess(true);
		return response;
	}

	@ApiImplicitParams({ @ApiImplicitParam(required = true, dataType = "file", paramType = "formData", name = "files"),
		@ApiImplicitParam(required = true, dataType = "string", paramType = "formData", name = "customer")})
	@ApiOperation("Exemplo de endpoint recebendo um arquivo e também um JSON utilizando multipart/form-data")
	@POST
	@Path("multipartWithJson")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public BasicResponse uploadWithJson(@ApiParam(hidden = true) FormDataMultiPart multiPart) {
		FormDataBodyPart customerPart = multiPart.getFields("customer").get(0);
		// Informando para o JAX-RS que este cara é um JSON (necessário caso não venha o Content-Type do client)
		customerPart.setMediaType(MediaType.APPLICATION_JSON_TYPE);
		// Agora posso ler o Customer como um JSON sem problemas
		Customer customer = customerPart.getEntityAs(Customer.class);
		List<FormDataBodyPart> files = multiPart.getFields("files");
		BasicResponse response = new BasicResponse();
		response.setMessage("Received " + files.size() + " file(s) and customer " + customer);
		response.setSuccess(true);
		return response;
	}

	@ApiImplicitParams({ @ApiImplicitParam(dataType = "file", paramType = "formData", name = "files") })
	@ApiOperation(value = "Envio de múltiplos arquivos (não suportado consumo múltiplo a partir da documentação)")
	@POST
	@Path("multiple")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public BasicResponse upload(@ApiParam(hidden = true) FormDataMultiPart multiPart) {
		List<FormDataBodyPart> parts = multiPart.getFields("files");
		for (FormDataBodyPart part : parts) {
			BodyPartEntity entity = (BodyPartEntity) part.getEntity();
			System.out.printf("=====================> Content for file '%s':%n%s%n", part.getContentDisposition().getFileName(), readContent(entity.getInputStream()));
			System.out.printf("=====================> End of file '%s'%n%n", part.getContentDisposition().getFileName());
		}
		BasicResponse response = new BasicResponse();
		response.setSuccess(true);
		response.setMessage("Received " + parts.size() + " files!");
		return response;
	}

	private String readContent(InputStream is) {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
			return reader.lines().collect(Collectors.joining("\n"));
		} catch (Exception e) {
			throw new WebApplicationException("Error reading file. Reason: " + e.getMessage(), e);
		}
	}
}
