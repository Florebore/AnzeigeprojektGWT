/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flope.DatabaseServices;

import com.flope.converter.DatabaseObjecttoJsonObject;
import com.flope.entities.Message;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
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
    
    //use PU1 f�r lokale Datenbank PU2 = TUM SQL Server
    
    @PersistenceContext(unitName="PU2")
    EntityManager em;
    
    public MessageDataService(){}
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED) //Transaction is managed by EJB Container
    public void savemsgtodb(Message message){
    
    em.persist(message);
   // em.getTransaction().commit(); ->
   
   /*

If you use container managed EntityManager then you're using JTA transactions. Hence, you don't need to (more precisely - you can not) interfere with EntityManager's transactions fetched using entityManager.getTransaction(). The JTA starts and commits your transaction.

If you use application managed EntityManager and you don't want to be in part of JTA transaction, then you need to manage them for yourself (it's called a resource-local entity manager).

Most typically, application managed EntityManager which works with EntityManager.getTransaction() is used in Java SE environment.*/
    em.flush();
    
        
        
    }
    
    public JsonObject getMessagebyID(String id) {
        
        Message dbmessage = null;
        JsonObject message = null;
        
        Query messagebyid = em.createNamedQuery("Message.findByMessageID");
        messagebyid.setParameter("messageID", id);
        dbmessage = (Message) messagebyid.getSingleResult();
        
       message = doto.messagetoJsonObject(dbmessage);
        
        return message;
    
    }
    
    
}
