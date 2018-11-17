/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flope.DatabaseServices;

import com.flope.entities.Message;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Florian
 */
@Stateless
public class MessageDataService {
    
    @PersistenceContext(unitName="PU1")
    EntityManager em;
    
    public MessageDataService(){}
    
    public void savemsgtodb(Message message){
        
    em.persist(message);
    em.flush();
    em.getTransaction().commit();
        
        
    }
    
    
}
