/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flope.JAXRS;

import com.flope.DatabaseServices.DisplayDataService;
import com.flope.converter.DatabaseListtoJsonArray;
import com.flope.entities.Display;
import com.flope.entities.Userdata;
import java.util.List;
import javax.inject.Inject;
import javax.json.JsonArray;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author Florian
 */
@Path("display")
public class DisplayDataResource {
    
    
    @Inject DisplayDataService dds;
    @Inject DatabaseListtoJsonArray dlta;
    
    
    @GET
    public JsonArray getdisplays(){
    JsonArray array = null;
  
    List<Display> ddb = dds.getalldisplays();
    array = dlta.displayListtoJsonArray(ddb);
    System.out.println(array.toString());
    return array;}
    
    
}
