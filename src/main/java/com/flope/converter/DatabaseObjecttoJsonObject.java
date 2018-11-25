/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flope.converter;

import com.flope.entities.Message;
import com.google.gson.Gson;
import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author Florian
 */
public class DatabaseObjecttoJsonObject {
    
    
    public JsonObject messagetoJsonObject(Message message) {
    
        JsonObject messageJ = null;
        Gson gson = new Gson();
    
        String messageS = gson.toJson(message);         
        
        try  {
            JsonReader jsonReader = Json.createReader(new StringReader(messageS));
            messageJ = jsonReader.readObject();
            System.out.println(message+"Converter");
            return messageJ;
        }
            
        
            
        catch(Exception e){System.out.println("Conversion from MessagePOJO to JsonObject failed!");
     
        return messageJ;}
    
}
}
