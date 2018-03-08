package ch.tor14.echo;

import ch.tor14.security.jwt.JWTTokenRequired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

@Path("/echo")
@Produces(TEXT_PLAIN)
public class EchoEndpoint {

    @GET
    public Response echo(@QueryParam("message") String message) {
        return Response.ok().entity(message == null ? "no message" : message).build();
    }

    @GET
    @Path("jwt")
    @JWTTokenRequired
    public Response echoWithJWTToken(@QueryParam("message") String message) {
        return Response.ok().entity(message == null ? "no message" : message).build();
    }
}
