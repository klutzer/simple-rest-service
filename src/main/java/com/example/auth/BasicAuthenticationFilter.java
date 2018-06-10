package com.example.auth;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import com.example.bean.BasicResponse;

/**
 * @author erico.lutzer
 *
 */
@Authenticated
@Provider
public class BasicAuthenticationFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String authHeader = requestContext.getHeaderString("Authorization");
        // Usuario user, Senha 123456
        if (!"Basic dXNlcjoxMjM0NTY=".equals(authHeader)) {
            requestContext.abortWith(Response
                    .status(Status.UNAUTHORIZED)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(new BasicResponse(false, "Not Authorized!"))
                    .build());
        }
    }

}
