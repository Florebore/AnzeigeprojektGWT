/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flope.DatabaseServices;

import com.flope.entities.Datei;
import com.flope.entities.Message;
import javax.ejb.Stateless;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Florian
 */
@Stateless
public class FileDataService {
    
    @PersistenceContext(unitName="PU2")
    EntityManager em;
    
    public void savefiletodb(Datei datei){
        
        em.persist(datei);
        
        
    }
    
    public Datei getfileinfo(String jobname){
        
        
    //  Query dateibyjobname
        
         //needs more work       
                
                
             Message dbmessage = null;
        Datei message = null;
        
        Query messagebyid = em.createNamedQuery("Message.findByMessageID");
        messagebyid.setParameter("messageID", 13);
        dbmessage = (Message) messagebyid.getSingleResult();
        
      // message = doto.messagetoJsonObject(dbmessage);
        
       return message;    
                
    }
    
}
