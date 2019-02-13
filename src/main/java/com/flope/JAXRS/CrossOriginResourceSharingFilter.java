/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flope.JAXRS;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Florian
 */

// class manages CORS for access by clients outside of the domain, different methods can be added or removed for additional security or functionality
//Class can be added via new File/class in Netbeans
@Provider
public class CrossOriginResourceSharingFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext response) {
        response.getHeaders().add("Access-Control-Allow-Origin", "*");
        response.getHeaders().add("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, OPTIONS");
        response.getHeaders().add("Access-Control-Allow-Headers", "Content-Type, Authorization , display, user");
        //ohne diesen Zusatz werden die Header zwar mitgeschickt, sind f�r den Browser sichtbar, aber nicht f�r den Client        
        response.getHeaders().add("Access-Control-Expose-Headers", "Content-Type,Authorization,display,user");
        response.getHeaders().add("Access-Control-Expose-Headers", "Content-Type, Authorization, display, user, fileid");
       requestContext.getHeaders().putSingle("Access-Control-Allow-Headers", "Content-Type, Authorization, display, user");
       
    }
    
}
