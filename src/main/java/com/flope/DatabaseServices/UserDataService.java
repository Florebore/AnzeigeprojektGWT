/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flope.DatabaseServices;

import com.flope.converter.JsonObjecttoPOJO;
import com.flope.entities.Userdata;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;

/**
 *
 * @author Florian
 */
//https://docs.oracle.com/javaee/6/tutorial/doc/gjbak.html
//https://docs.oracle.com/javaee/6/tutorial/doc/gjbbk.html 
@Stateless
public class UserDataService{
    
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
    
    public Userdata findbylogin(Userdata login) {
        
       try { Query query = em.createNamedQuery("Userdata.findbyLoginPassword");
        query.setParameter("username", login.getUsername());
        query.setParameter("password", login.getPassword());
        Userdata user = (Userdata) query.getSingleResult();
        return user;}
        
        catch(NoResultException e) {
        return null;
    }
       
      
        
        
        
        
    }
    
    
    public List<Userdata> findall(){
      
    List<Userdata> allusersdb = null;
        
    System.out.println(em);
        
    Query q1 = em.createNamedQuery("Userdata.findAll");
    allusersdb = q1.getResultList();
    System.out.println(allusersdb);
        
        
    return allusersdb; 
    }
    //https://hc.apache.org/httpcomponents-client-ga/httpclient/apidocs/org/apache/http/auth/UsernamePasswordCredentials.html
    //CredentialValidationResult return an error message or httpstatur message 
    public CredentialValidationResult validate(UsernamePasswordCredential unpc){
        
        
        return null;
}
    
}