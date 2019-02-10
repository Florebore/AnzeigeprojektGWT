/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flope.DatabaseServices;

import com.flope.converter.DatabaseObjecttoJsonObject;
import com.flope.entities.Display;
import com.flope.entities.Job;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Florian
 */
@Stateless
public class DisplayDataService {
    
    
    @Inject DatabaseObjecttoJsonObject doto;
    
    @PersistenceContext(unitName="PU2")
    EntityManager em;
    
    
public DisplayDataService(){}


 public List<Display> getalldisplays(){
        
    List<Display> alldisplaydb = null;    
        
    Query q1 = em.createNamedQuery("Display.findAll");
    alldisplaydb = q1.getResultList();
    System.out.println(alldisplaydb);
        
        
    return alldisplaydb; 

 
    
}
 
 public Display getdisplaybyID(String id){
        
    Display displaydb = null;    
        
     Query displaybyid = em.createNamedQuery("Display.findByDisplayID");
        displaybyid.setParameter("messageID", id);
        displaydb = (Display) displaybyid.getSingleResult();
        
       displaydb = (Display) doto.displaytoJsonObject(displaydb);
        
        return displaydb;
 
    
}
 
 
 
      @TransactionAttribute(TransactionAttributeType.REQUIRED) //Transaction is managed by EJB Container
    public void setCurrentJOBID (int jobid, int displayID){
    System.out.println(jobid);
        List <Display> currentAnzeige;
    currentAnzeige = em.createQuery("SELECT d FROM Display d WHERE d.displayID = :displayID")
    .setParameter("displayID", displayID).getResultList();       
    currentAnzeige.get(0).setCurrentjob(jobid);
    
    em.persist(currentAnzeige.get(0));

    em.flush();
    }
    
          @TransactionAttribute(TransactionAttributeType.REQUIRED) //Transaction is managed by EJB Container
    public void setCurrentJOBTYPE (String jobtype, int displayID){
    
        List <Display> currentAnzeige;
    currentAnzeige = em.createQuery("SELECT d FROM Display d WHERE d.displayID = :displayID")
    .setParameter("displayID", displayID).getResultList();
    currentAnzeige.get(0).setCurrentURL(jobtype);
    
    
    em.persist(currentAnzeige.get(0));

    em.flush();
    }
}
 

