/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flope.servlets;

import com.flope.DatabaseServices.FileDataService;
import com.flope.entities.Datei;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Florian
 */
@WebServlet(name = "FileUploadServlet", urlPatterns = {"/upload"})
//Hier kann man auch die Dateigr��e beschr�nken
@MultipartConfig

public class FileUploadServlet extends HttpServlet {

    @Inject FileDataService fds;
    
        

    private final static Logger LOGGER = Logger.getLogger(FileUploadServlet.class.getCanonicalName());

    private static final long serialVersionUID = 7908187011456392847L;
    
    String UPLOAD_DIRECTORY = "upload";

      
    @Override
   public void init( ){
      // Get the file location where it would be stored.
      UPLOAD_DIRECTORY = getServletContext().getInitParameter("file-upload"); 
   }

   
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException{
        
  
    
    res.getWriter().println( "test" );
       
     Enumeration headerNames = req.getHeaderNames();
    
      while(headerNames.hasMoreElements()) {
         String paramName = (String)headerNames.nextElement();
         out.print("<tr><td>" + paramName + "</td>\n");
         String paramValue = req.getHeader(paramName);
         out.println("<td> " + paramValue + "</td></tr>\n");
      }
      out.println("</table>\n</body></html>");
   
    
}
             
             
 @Override
 protected void doPost(HttpServletRequest req,

            HttpServletResponse resp)

            throws ServletException, IOException {
     
     
     
           

         
//Http HeaderList Print
  Enumeration headerNames = req.getHeaderNames();
    
    while(headerNames.hasMoreElements()) {
         String paramName = (String)headerNames.nextElement();
         out.print("<tr><td>" + paramName + "</td>\n");
         String paramValue = req.getHeader(paramName);
         out.println("<td> " + paramValue + "</td></tr>\n");
      }
      out.println("</table>\n</body></html>");
  
      String fileName = "";
      String userName = "";
      String displayid ="";
      Datei datei = new Datei();
      
 //Get the Username and displayID in the Multipart-Request which is located in the Partname user
userName = req.getParameter("user");
System.out.println(userName);
displayid = req.getParameter("display");
System.out.println(displayid);
      
  //For Each part in Parts do    
  /* Hier werden alle Parts des Multipart-Request nach einander geholt und in die Funktion getFileName gespielt*/ 
   //hier wird nun alles vorbereitet, um die Datei wirklich zu speichern. (z.B. Pfad und Ordner), zudem wird der fileInputputStream gelesen
  
String uploadPath = UPLOAD_DIRECTORY +"/"+ userName ;

//String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY +"/"+ userName;
System.out.println(uploadPath);
File uploadDir = new File(uploadPath);
if (!uploadDir.exists()) uploadDir.mkdir();


OutputStream out = null;
InputStream filecontent = null;
final PrintWriter writer = resp.getWriter();
System.out.println(writer.toString());

try{

    //holen der Infos aus dem MultiPartRequest und schreiben der Datei ins Verzeichnis
Part filePart = req.getPart("file");
this.getParts(filePart);
fileName = getFileName(filePart);
System.out.println(fileName);
 

    
  out = new FileOutputStream(new File(uploadPath + File.separator + fileName));
  System.out.println(out.toString());
  
    filecontent = filePart.getInputStream();
  

        int read = 0;
        final byte[] bytes = new byte[1024];

        while ((read = filecontent.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }
        
        writer.println("New file " + fileName + " created at " + uploadPath);
        LOGGER.log(Level.INFO, "File {0} being uploaded to {1}",
                new Object[]{fileName, uploadPath});
        resp.sendError(200);
    } 


        catch (FileNotFoundException fne) {
            
        writer.println("You either did not specify a file to upload or are "
                + "trying to upload a file to a protected or nonexistent "
                + "location.");
        writer.println("<br/> ERROR: " + fne.getMessage());

        LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}",
                new Object[]{fne.getMessage()});
    } finally {
        if (out != null) {
            out.close();
        }
        if (filecontent != null) {
            filecontent.close();
        }
        if (writer != null) {
            writer.close();
        }
        
        //Object Datei wird erstellt und in die Datenbank geschrieben
        datei.setFilename(fileName);
        datei.setLocation(uploadPath + File.separator + fileName);
        datei.setUploadedby(userName);
        //Dateisuffix finden
        int suffix;
        suffix = fileName.indexOf(".");
        datei.setFiletype(fileName.substring((suffix)+1));
        fds.savefiletodb(datei);
        
        
        
    }

    

}

/* Der Header content-disposition kommt folgenderma�en aus dem Multipart-Request (INFORMATION:   form-data; name="pdf"; filename="eveline.pdf")
 */

     private String getFileName(Part part) {
         
         String defaultname = "default.file";
 //f�r jeden String content im header content-disposition 
 //public String[] split(String regex) Splits this string around matches of the given regular expression.
//Trailing empty strings are therefore not included in the resulting array.
//The string "boo:and:foo", for example, yields the following results with these expressions:
// Examples
//: { "boo", "and", "foo" }
//o{ "b", "", ":and:f" }
        for (String content : part.getHeader("content-disposition").split(";")) {
//trim() entfernt alle Spaces aus dem String
            if (content.trim().startsWith("filename"))
//scheidet alles weg und returned den filename ohne "" und andere Zeichen
                return content.substring(content.indexOf("=") + 2, content.length() - 1);

        }

        return defaultname ;

    }
     
 //Die Funktion getParts() druckt die Header der einzelnen MultiPart FileParts aus und zeigt somit, was alles vom Client geschickt wird
     
     private void getParts(Part part){
       
      
       
       for (String content : part.getHeaderNames())
       {System.out.println(content);}
   }
  
    
    //enable CORS for HTTPServlet
     //Update mittlerweile in CORSFilter-Klasse ausgelagert
     private void setAccessControlHeaders(HttpServletResponse resp) {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, OPTIONS");
        resp.addHeader("Access-Control-Headers", "Content-Type, Authorization, Display, User");
        resp.addHeader("Access-Control-Expose-Headers", "Content-Type, Display, User, Authorization");
        resp.addHeader("Access-Control-Request-Headers", "Content-Type, Display, User, Authorization");
        
                }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //setAccessControlHeaders(resp);
    //resp.setStatus(HttpServletResponse.SC_OK);
}
}
