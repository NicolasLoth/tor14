package ch.tor14.security.jwt;

import io.jsonwebtoken.Jwts;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Priority;
import javax.crypto.KeyGenerator;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

@Provider
@JWTTokenRequired
@Priority(Priorities.AUTHENTICATION)
public class JWTTokenFilter implements ContainerRequestFilter {

    private static final Logger logger = LogManager.getLogger(JWTTokenFilter.class);

    private KeyGenerator keyGenerator;

    public JWTTokenFilter() throws NoSuchAlgorithmException {
        keyGenerator = KeyGenerator.getInstance("AES");
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        // Get the HTTP Authorization header from the request
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        if(null == authorizationHeader){
            logger.warn("Access without authorization header");
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            return;
        }

        // Extract the token from the HTTP Authorization header
        String token = authorizationHeader.substring("Bearer".length()).trim();

        try {

            // Validate the token
            Key key = keyGenerator.generateKey();
            Jwts.parser().setSigningKey("hallo").parseClaimsJws(token);
            logger.info("#### valid token : " + token);

        } catch (Exception e) {
            logger.error("#### invalid token : " + token);
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}
