/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flope.DatabaseServices;

import com.flope.entities.Job;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


/**
 *
 * @author Florian
 */

@Stateless
public class JobDataService {
    
    @PersistenceContext(unitName="PU2")
    EntityManager em;
    
    
   @TransactionAttribute(TransactionAttributeType.REQUIRED) //Transaction is managed by EJB Container
    public void savejobtodb(Job job){
        
    
    
    em.persist(job);
 //   em.getTransaction().commit();
   
   /*

If you use container managed EntityManager then you're using JTA transactions. Hence, you don't need to (more precisely - you can not) interfere with EntityManager's transactions fetched using entityManager.getTransaction(). The JTA starts and commits your transaction.

If you use application managed EntityManager and you don't want to be in part of JTA transaction, then you need to manage them for yourself (it's called a resource-local entity manager).

Most typically, application managed EntityManager which works with EntityManager.getTransaction() is used in Java SE environment.*/
    em.flush();
    }
    
   /*public List<Job> findJob(){
      
    List<Job> alljobdb = null;
        
    System.out.println(em);
        
    Query q1 = em.createNamedQuery("Job.findAll");
    alljobdb = q1.getResultList();
    System.out.println(alljobdb);
        
        
    return alljobdb; 
    }
*/
        
        
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<Job> getalljobs(){
        
    List<Job> alljobsdb = null;    
        
    
        
    Query q1 = em.createNamedQuery("Job.findAll");
    alljobsdb = q1.getResultList();
    System.out.println(alljobsdb);
        
        
    return alljobsdb; 
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<Job> getjobsnext2weekssorted(){
    
       
    List<Job> twoweeksjobsdb = null;    
       
    
        
    
    //SQL-Abfrage soll Werte ausgeben, die innerhalb der nächsten zwei Wochen gestartet werden
    //sollen nach timeStart sortiert ausgegeben werden
    
    Query q1 = em.createQuery("SELECT j FROM Job j WHERE j.timeStart <= :timeStart ORDER BY j.timeStart")
    .setParameter("timeStart", System.currentTimeMillis() + 1209600000L);
    twoweeksjobsdb = q1.getResultList();

    return twoweeksjobsdb; 
    
    
    }
    
    
}
    

