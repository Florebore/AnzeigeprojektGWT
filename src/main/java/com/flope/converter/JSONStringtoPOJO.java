/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flope.converter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flope.entities.Userdata;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Florian
 */
public class JSONStringtoPOJO {
    
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
    
    public Userdata convertJSONStringtoPOJOUSER(String JSON){
        
        Userdata user = null;
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            user = mapper.readValue(JSON,Userdata.class);
        }
        catch (IOException ex) {
            Logger.getLogger(JSONStringtoPOJO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    
}
