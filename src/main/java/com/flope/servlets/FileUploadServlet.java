/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flope.servlets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "FileUploadServlet1", urlPatterns = {"/upload"})

@MultipartConfig

public class FileUploadServlet extends HttpServlet {



    private final static Logger LOGGER = Logger.getLogger(FileUploadServlet.class.getCanonicalName());

    private static final long serialVersionUID = 7908187011456392847L;
    
    final String UPLOAD_DIRECTORY = "upload";

      


   
    
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

        resp.setContentType("text/html;charset=UTF-8");
        
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, OPTIONS");
        
   
//Http HeaderList Print
  /*  Enumeration headerNames = req.getHeaderNames();
    
    while(headerNames.hasMoreElements()) {
         String paramName = (String)headerNames.nextElement();
         out.print("<tr><td>" + paramName + "</td>\n");
         String paramValue = req.getHeader(paramName);
         out.println("<td> " + paramValue + "</td></tr>\n");
      }
      out.println("</table>\n</body></html>");*/
  
      String fileName = "";
      
      
  //For Each part in Parts do    
  /* Hier werden alle Parts des Multipart-Request nach einander geholt und in die Funktion getFileName gespielt*/ 
   //hier wird nun alles vorbereitet, um die Datei wirklich zu speichern. (z.B. Pfad und Ordner), zudem wird der fileInputputStream gelesen
  
String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
System.out.println(uploadPath);
File uploadDir = new File(uploadPath);
if (!uploadDir.exists()) uploadDir.mkdir();


OutputStream out = null;
InputStream filecontent = null;
final PrintWriter writer = resp.getWriter();
System.out.println(writer.toString());

try{
    
  fileName = "default.pdf";
 for (Part part : req.getParts()) {
  fileName = getFileName(part);
  System.out.println(fileName);}
 

  
 Part filePart = req.getPart("file");

  
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
     
   private void getParts(Part part){
       
      
       
       for (String content : part.getHeaderNames())
       {System.out.println(content);}
   }
  
    
    //enable CORS for HTTPServlet
     private void setAccessControlHeaders(HttpServletResponse resp) {
        resp.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        resp.addHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, OPTIONS");
        resp.addHeader("Access-Control-Headers", "Content-Type");
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    setAccessControlHeaders(resp);
}
}