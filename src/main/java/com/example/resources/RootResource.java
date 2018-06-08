package com.example.resources;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * @author erico.lutzer
 *
 */
@Path("/")
public class RootResource {

    @Context
    private UriInfo info;
    
    @GET
    public Response get() throws URISyntaxException {
        return Response.seeOther(new URI(info.getBaseUri().toString() + "docs/")).build();
    }
}
