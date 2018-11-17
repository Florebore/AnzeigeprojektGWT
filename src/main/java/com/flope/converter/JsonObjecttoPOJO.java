/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flope.converter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flope.entities.Message;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.json.JsonObject;

/**
 *
 * @author Florian
 */
@Stateless
public class JsonObjecttoPOJO {
    
    ObjectMapper mapper = new ObjectMapper();
    
/*    public Sperrbildschirmjob convertJSONStringtoPOJOSperrbildschirmjob(String JSON) {
        
     mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
     
     Sperrbildschirmjob job = null;
        try {
            job = mapper.readValue(JSON,Sperrbildschirmjob.class);
        } catch (IOException ex) {
            Logger.getLogger(JSONStringtoPOJO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    return job;
    
    }*/
    
    public Message convertJsonObjecttoPOJOMessage(JsonObject object){
        
        String JsonString;
        JsonString = object.toString();
        Message message = new Message();
        
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            message = mapper.readValue(JsonString,Message.class);
            System.out.println(message.toString()+"POJOconverter");
        }
        catch (IOException ex) {
            Logger.getLogger(JsonObjecttoPOJO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return message;
    }

    
}
