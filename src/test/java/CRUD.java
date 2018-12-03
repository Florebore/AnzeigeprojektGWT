/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.flope.DatabaseServices.UserDataService;
import com.flope.entities.Userdata;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Florian
 */
public class CRUD {
    
    @Inject
    private UserDataService udsb;
    
    
    public CRUD() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
       @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
   @Test
   public void CRUD(){
   
 
   CRUD c = new CRUD();
  
   
  /*User a = em.find(User.class, 1);
   String pw  = a.getIdent();
   Query q =  em.createNamedQuery("User.findAll");
   List l =  q.getResultList();
   System.out.println(l);
   Query q1 = em.createNamedQuery("User.findByUsername");
   q1.setParameter("username","f.boettinger");
   List l1 =  q1.getResultList();
   System.out.println(l1);*/
 
/*EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU1");
EntityManager em = emf.createEntityManager();
 Query q1 = em.createNamedQuery("Userdatalogin").setParameter("username", "test");
 List l1 = q1.getResultList();
 System.out.println(l1);
Object o = q1.getResultList().get(0);
 System.out.println(o);
 //coverts Object ot target class
 Userdata dbuser = Userdata.class.cast(o);
 System.out.println(dbuser.getPassword());
  */

  
 /*System.out.println(c);
 System.out.println(udsb);
 
udsb = new UserDataServiceBean();
 
 udsb.findUser();*/

 
 
 
 }
    


   
   
   }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

;