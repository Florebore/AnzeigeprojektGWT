/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flope.JAXRS;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author Florian
 */
import com.flope.DatabaseServices.UserDataService;
import com.flope.converter.POJOtoJSONString;
import com.flope.entities.Userdata;
import com.google.gson.Gson;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonString;
import javax.ws.rs.Produces;
import java.applet.Applet;

@Path("userdata")
public class UserDataResource {
    
@Inject UserDataService uds;
    
    @GET
    public JsonArray alluser()
    {   
  
  
    List<Userdata> uddb = uds.findall();
       
    String allusers = null;
    
    Gson gson = new Gson();
    
    allusers = gson.toJson(uddb);
    
    System.out.println(allusers);

 JsonReader jsonReader = Json.createReader(new StringReader(allusers));
 JsonArray array = jsonReader.readArray();
 jsonReader.close();
 System.out.println(array);
    
    
   

   
    
    
        
        
    
    
      
         
        
 return array ;}
    
}
