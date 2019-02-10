/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flope.Services;

import com.flope.DatabaseServices.DisplayDataService;
import com.flope.DatabaseServices.JobDataService;
import com.flope.entities.Job;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.DependsOn;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author peterkirchhoff
 */
//https://www.baeldung.com/java-ee-singleton-session-bean
// K�nnte in Zukunft auch noch interessant werden
//https://stackoverflow.com/questions/37746926/java-cdi-invalid-dependson-dependency
//http://theopentutorials.com/tutorials/java-ee/ejb3/session-beans/stsb/singleton-concurrency/


@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
//@DependsOn({"JobDataService"})
public class Scheduler implements Comparable<Job> {  
      
   @Inject JobDataService jds;
   @Inject DisplayDataService dds;
      
 //volatile makes sure that double check locking works and no half initialised object are accessed by threads
    
    private static volatile Scheduler soleScheduler = null;

   //Todo threadsicherheit des Singleton_patterns gewährleisten
    
    public Scheduler() throws CloneNotSupportedException{
        
        //Runtimeexception is created here to stop Reflection to create another instance
        
    if(soleScheduler != null){throw new RuntimeException("Cannot create Scheduler, use getInstance()...");}
    //creation
    System.out.println("Scheduler wird erzeugt...");
    }
  
   /*lazy creation of a Singleton / synchronized is added to the getInstance()-method to prevent multiple threads to create more Scheduler
    When a thread calls a synchronized method, another thread has to wait until the first one is fininshed....to boost performance the synchronized block is moved 
    to the if statement execution block*/    
  public static Scheduler getInstance() throws CloneNotSupportedException{
    // Check 1  
    if (soleScheduler== null){
          synchronized(Scheduler.class){
    //Double Check locking: Check 2
          if(soleScheduler == null){
          soleScheduler = new Scheduler();}}
      }
      return soleScheduler;
  }    

  
 //Attribute 
  
 private final LinkedList<Job> Jobswaitforexecution = new LinkedList<>();
 private final LinkedList<Job> Jobsrunning = new LinkedList<>();
 
 //Diese LinkedList ist wahrschreinlich nicht n�tig
 private final LinkedList<Job> ExecutedJobs = new LinkedList<>();
 
 long checkIntervall = 60000; //alle 60 Sekunden sollte gecheckt werden, da Zeit vom User auch minutenweise angegeben werden kann

     
  /*
   * zum Hinzufügen zu einer sortierten linkedList könnte auch besser der listIterator benutzt werden
   * (https://stackoverflow.com/questions/18144820/inserting-into-sorted-linkedlist-java) - da aber Kosten keine Rolle spielen,
   * ist es erstmal nicht notwendig
   */
 //Methoden

@PostConstruct
public void initialize(){
    
    System.out.println("PostConstruct done!");
     
   this.populateList();
}

 
 
 
 
 
 //Liste  Jobswaitforexecution muss bei jeder neuen Instanziierung des Schedulers aus der DB geholt werden durch populateList
      public void populateList(){
          
    List<Job> joblist = null;
    
    joblist = jds.getjobsnext2weekssorted();
    
    ListIterator<Job> litr = joblist.listIterator();
  int i = 0;
  while(litr.hasNext()){
  Jobswaitforexecution.add(i,litr.next());
  i++;
  }
  System.out.println(Jobswaitforexecution);
  threading();
  }
      
  
  public void addtowaitList(Job job){

         System.out.println(Jobswaitforexecution + "1");
         
         if (Jobswaitforexecution.isEmpty()) {
              Jobswaitforexecution.add(job);
          } else if (Jobswaitforexecution.get(0).getTimeStart () > job.getTimeStart ()) {
              Jobswaitforexecution.add(0, job);
          } else if (Jobswaitforexecution.get(Jobswaitforexecution.size() - 1).getTimeStart () < job.getTimeStart ()) {
              Jobswaitforexecution.add(Jobswaitforexecution.size(), job);
          } else {
              int i = 1;
              while (Jobswaitforexecution.get(i).getTimeStart () < job.getTimeStart ()) {
                  i++;
              }
              Jobswaitforexecution.add(i, job);
          }
         System.out.println(Jobswaitforexecution + "2");
          
      }
 
   
  

 
  

  
  public void addtorunList(Job e){
  
  
  Jobsrunning.add(e);
  
  
  }

/*
 * 
 * die Methode backgroundTask() prüft, ob die Zeit für eine Anzeige gekommen ist
 * über die Methode vergleich(), wenn die Zeit da ist, zeigt er den Job an
 * Platform.runLater() wird gebraucht um eine JavaFX-Veränderung aus dem Hintergrund zu starten,
 * da JavaFX-Elemente eigentlich nur vom Main-JavaFX-Thread gestartet werden können
 */
 
  public void backgroundTask() throws InterruptedException 
  {
     while (true) {
     boolean comparison = vergleich();
     if (comparison == true){
     System.out.println("Es ist Zeit!");
     int jobid = Jobswaitforexecution.getFirst().getJobID();
     int displayID = Jobswaitforexecution.getFirst().getAnzeigeID();
     String jobtype = Jobswaitforexecution.getFirst().getJobtype();
     dds.setCurrentJOBID(jobid, displayID); 
     dds.setCurrentJOBTYPE(jobtype, jobid);
     Jobswaitforexecution.removeFirst();
     System.out.println(Jobswaitforexecution + "ist gelöscht");
     Thread.sleep(checkIntervall);
}
     else {System.out.println("Es ist noch nicht Zeit! " + System.currentTimeMillis());
     //Jobswaitforexecution.getFirst().setFinished(true); //setzt Finished-Flag in der Job-Liste Jobswaitforexecution
     
     Thread.sleep(checkIntervall);}
     
     }


      
  }

          
   //jobid--> currentjob
  //Jobtype --> currenturl 
  
  //ToDo vergleich() mit einem Vergleich der aktuellen zeit mit der ersten Job.getstarttime() in der LinkedList coden
  //Methode compareTo (Job o) mit verwursten, sonst meckert er, da implemts comparable nicht verwendet wird
    
  public boolean vergleich(){
      boolean comparison = false;
      long nowms = System.currentTimeMillis();
      long nextJobStartms = Jobswaitforexecution.peekFirst ().getTimeStart ();
      
      if (nextJobStartms-(checkIntervall/2)<=nowms &&nowms<= nextJobStartms+(checkIntervall/2))
      {comparison = true;}
      return comparison;   
  }
  
  @ Override
  public int compareTo ( Job o )
  {
      
      return 0;
  }
      
  
    
              
         
  
  //Methode threading() erstellt einen Backgroundthread
  
  public void  threading()
  {
      // Create a Runnable
      Runnable task = new Runnable()
      {
          @Override
          public void run()
          {
              try {
                  backgroundTask();
              } catch (InterruptedException ex) {
                  Logger.getLogger(Scheduler.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
      };

      // Run the task in a background thread
      Thread backgroundThread = new Thread(task);
      // Terminate the running thread if the application exits
      backgroundThread.setDaemon(true);
      // Start the thread
      backgroundThread.start();
      
      
              
  }}
