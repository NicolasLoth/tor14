package ch.tor14.security.login;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.KeyGenerator;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

@Path("/users")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
//@Transactional
public class UserEndpoint {

    private KeyGenerator keyGenerator;

    public UserEndpoint() throws NoSuchAlgorithmException {
        keyGenerator = KeyGenerator.getInstance("AES");
    }

    @POST
    @Path("/login")
    @Consumes(value = APPLICATION_FORM_URLENCODED)
    public Response authenticateUser(@FormParam("login") String login,
                                     @FormParam("password") String password) {
        try {

            // Authenticate the user using the credentials provided
//            authenticate(login, password);

            // Issue a token for the user
            String token = issueToken(login);

            // Return the token on the response
            return Response.ok().header(AUTHORIZATION, "Bearer " + token).build();

        } catch (Exception e) {
            return Response.status(UNAUTHORIZED).build();
        }
    }

    private String issueToken(String login) {
        Key key = keyGenerator.generateKey();
        Calendar c = Calendar.getInstance();
        c.setTime(new Date()); // Now use today date.
        c.add(Calendar.MINUTE, 15); // Adds 15 minutes
        String jwtToken = Jwts.builder()
                .setSubject(login)
                .setIssuer("tor14.ch")
                .setIssuedAt(new Date())
                .setExpiration(c.getTime())
                .signWith(SignatureAlgorithm.HS512, "hallo")
                .compact();
        return jwtToken;
    }
}
