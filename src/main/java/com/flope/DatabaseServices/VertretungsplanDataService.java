/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flope.DatabaseServices;
import com.flope.converter.DatabaseObjecttoJsonObject;
import com.flope.entities.Vertretungsplan;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author peterkirchhoff
 */
@Stateless
public class VertretungsplanDataService {
    



    
    @Inject DatabaseObjecttoJsonObject doto;
    
     //use PU1 f�r lokale Datenbank PU2 = TUM SQL Server
    
    @PersistenceContext(unitName="PU2")
    EntityManager em;
    
    public VertretungsplanDataService(){}
    
    public void saveplantodb(Vertretungsplan vertretungsplan){
        
    em.persist(vertretungsplan);
    em.flush();
    em.getTransaction().commit();
        
      }  
    }
    
