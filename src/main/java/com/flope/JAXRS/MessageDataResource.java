/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flope.JAXRS;

import com.flope.DatabaseServices.MessageDataService;
import com.flope.converter.JsonObjecttoPOJO;
import com.flope.entities.Message;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

/**
 *
 * @author Florian
 */
@Path("message") //Test if message from angular Client is receives correctly
public class MessageDataResource {
    
    @Inject MessageDataService mds;
    @Inject JsonObjecttoPOJO jotp;
    
    @PUT
    public void putmessage(String message){
        System.out.println(message);
    }
    
    @GET
    public String sendmessage(){
        String test = "sendmessage works";
        
        return test;
    }
    
    @POST
    public void postmessage(JsonObject message){
        System.out.println(message.toString());
        
       Message ticker = new Message();  
       ticker = jotp.convertJsonObjecttoPOJOMessage(message);
        
        
        mds.savemsgtodb(ticker);
        
       
    }
           
}
