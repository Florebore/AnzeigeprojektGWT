/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flope.JAXRS;

import com.flope.DatabaseServices.JobDataService;
import com.flope.converter.JsonObjecttoPOJO;
import com.flope.entities.Job;
import com.flope.entities.Message;
import com.flope.entities.Scheduler;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 *
 * @author Florian
 */
@Path("jobs")
public class JobDataResource {
    
    @Inject JsonObjecttoPOJO jotp;
    @Inject JobDataService jds;
    
   // @GET
    
   @POST
   public void receivejob(JsonObject object) //throws CloneNotSupportedException
   {
   System.out.println(object);
   
   Job job = new Job();
   job = jotp.convertJsonJobtoPOJOJob(object);
   jds.savejobtodb(job);
   //Scheduler sched = Scheduler.getInstance();
   
   }
    
}
