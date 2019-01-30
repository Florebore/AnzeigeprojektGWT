/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flope.Authentication;

import com.flope.DatabaseServices.UserDataService;
import com.flope.converter.JsonObjecttoPOJO;
import com.flope.entities.Userdata;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Florian
 */
@Path("auth")
public class AuthEndpoint {
    
    //hier sollte noch ein Key eingefügt werden
    private final String secretKey = "my-secret-jwt-key";
 
    
    @Inject
    UserDataService uds;
    
    @Inject JsonObjecttoPOJO jop;
    
    @Context
    private UriInfo uriInfo;

    @POST
    public Response authenticateUser(JsonObject object) {
        
       if (object.isEmpty()==true){Response.status(UNAUTHORIZED).build();}
        
        
       Userdata clientuser = jop.convertJsonObjecttoPOJOUserdata(object);
       
         Boolean exists = null;
         
         exists = authenticate(clientuser);
        System.out.println(exists);
         
            // Authenticate the user using the credentials provided
         if  ( exists == true){
                System.out.println("in if schleife");
            // Issue a token for the user
            String token = issueToken(clientuser.getUsername());
                
 
            // Return the token on the response
            return Response.ok().header(AUTHORIZATION, "Bearer " + token).build();}
          else {
            return Response.status(UNAUTHORIZED).build();
        }
    }
    
 
    private String issueToken(String login) {
        String jwtToken = Jwts.builder()
                .setSubject(login)
                .setIssuer(uriInfo.getAbsolutePath().toString())
                .setIssuedAt(new Date())
                //15L bedeutet das 15 ein Long ist
                .setExpiration(toDate(LocalDateTime.now().plusMinutes(120L)))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
        
        System.out.println(jwtToken);
        return jwtToken;
    }
    
    private boolean authenticate(Userdata clientuser){
        
        Userdata dbuser = uds.findbylogin(clientuser);
        
        System.out.println("nach db vergleich");
        
        return dbuser != null;
        
    }
    
    private Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}