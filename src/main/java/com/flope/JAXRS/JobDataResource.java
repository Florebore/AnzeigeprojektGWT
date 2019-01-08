/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flope.JAXRS;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 *
 * @author Florian
 */
@Path("jobs")
public class JobDataResource {
    
    
   // @GET
    
   @POST
   
   public void receivejob(String job){
       System.out.println(job);
   }
    
}
