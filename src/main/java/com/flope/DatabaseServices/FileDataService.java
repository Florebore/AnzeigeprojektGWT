/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flope.DatabaseServices;

import com.flope.entities.Datei;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    
    
}
