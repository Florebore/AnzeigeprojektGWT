/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flope.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.flope.entities.Userdata;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Florian
 */
public class POJOtoJSONString {
    
    //Methods of the class convert different classes to JSON
    
    ObjectMapper mapper = new ObjectMapper().enable((SerializationFeature.INDENT_OUTPUT));
    
    
    public String covertUSERtoJSON (Userdata user){
        
        String JSONUSERString = null;
    try{
    JSONUSERString = mapper.writeValueAsString(user);}
    catch (JsonProcessingException ex){
        Logger.getLogger(POJOtoJSONString.class.getName()).log(Level.SEVERE,null,ex);
    }

    return JSONUSERString;
        
    }
    
}
//Convert to timestamp use following code 

//mapper.registerModule(new JavaTimeModule());
// mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);