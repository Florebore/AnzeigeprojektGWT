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
import com.flope.converter.DatabaseListtoJsonArray;
import com.flope.entities.Userdata;
import java.util.List;
import javax.inject.Inject;
import javax.json.JsonArray;

@Path("userdata")
public class UserDataResource {
    
@Inject UserDataService uds;
@Inject DatabaseListtoJsonArray ltja;
    
    @GET
    public JsonArray alluser()
    {   
    
    JsonArray array = null;
  
    List<Userdata> uddb = uds.findall();
    array = ltja.ListtoJsonArray(uddb);
    System.out.println(array.toString());
    return array;
    }
}