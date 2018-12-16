/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flope.DatabaseServices;

import com.flope.entities.Userdata;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Florian
 */
//https://docs.oracle.com/javaee/6/tutorial/doc/gjbak.html
//https://docs.oracle.com/javaee/6/tutorial/doc/gjbbk.html 
@Stateless
public class UserDataService {
    
     //use PU1 für lokale Datenbank PU2 = TUM SQL Server
    
    @PersistenceContext(unitName="PU2")
    EntityManager em;

    public UserDataService(){}
    
    
    public Userdata findbyusername(Userdata loginuser) {
        
    Userdata userdb = null;
        
    Query q1 = em.createNamedQuery("Userdatalogin").setParameter("username", loginuser.getUsername());
    Object o = q1.getSingleResult();
    System.out.println(o);
    //coverts Object ot target class
    userdb = Userdata.class.cast(o);
    System.out.println(userdb);
    
        
        
    return userdb; 
        
    }
    
    public List<Userdata> findall(){
      
    List<Userdata> allusersdb = null;
        
    System.out.println(em);
        
    Query q1 = em.createNamedQuery("Userdata.findAll");
    allusersdb = q1.getResultList();
    System.out.println(allusersdb);
        
        
    return allusersdb; 
    }
    
    
}