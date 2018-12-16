/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flope.DatabaseServices;

import com.flope.converter.DatabaseObjecttoJsonObject;
import com.flope.entities.Message;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Florian
 */
@Stateless
public class MessageDataService {
    
    @Inject DatabaseObjecttoJsonObject doto;
    
    @PersistenceContext(unitName="PU1")
    EntityManager em;
    
    public MessageDataService(){}
    
    public void savemsgtodb(Message message){
        
    em.persist(message);
    em.flush();
    em.getTransaction().commit();
        
        
    }
    
    public JsonObject getMessagebyID() {
        
        Message dbmessage = null;
        JsonObject message = null;
        
        Query messagebyid = em.createNamedQuery("Message.findByMessageID");
        messagebyid.setParameter("messageID", 5);
        dbmessage = (Message) messagebyid.getSingleResult();
        
       message = doto.messagetoJsonObject(dbmessage);
        
        return message;
    
    }
    
    
}
