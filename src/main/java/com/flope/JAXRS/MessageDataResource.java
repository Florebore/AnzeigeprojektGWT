/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flope.JAXRS;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

/**
 *
 * @author Florian
 */
@Path("message") //Test if message from angular Client is receives correctly
public class MessageDataResource {
    
    @PUT
    public void putmessage(String message){
        System.out.println(message);
    }
    
    @GET
    public String sendmessage(){
        String test = "sendmessage works";
        
        return test;
    }
           
}
