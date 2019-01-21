/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flope.servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Florian
 */
  
@WebServlet("/imagedownload")

public class ImageDownloadServlet extends HttpServlet {
    private final int ARBITARY_SIZE = 1048;
 
    String image = "null";
    String username = "f.boettinger";
    String filename = "kein-tv-signal.jpg";
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
      throws ServletException, IOException {
        
        //Http HeaderList Print
    /*Enumeration headerNames = req.getHeaderNames();
    
    while(headerNames.hasMoreElements()) {
         String paramName = (String)headerNames.nextElement();
         out.print("<tr><td>" + paramName + "</td>\n");
         String paramValue = req.getHeader(paramName);
         out.println("<td> " + paramValue + "</td></tr>\n");
      }
      out.println("</table>\n</body></html>");*/
        
        
       
  
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Headers","Origin, Content-Type, Accept, Authorization, Display");      
        resp.addHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, OPTIONS");
        resp.setHeader("Content-disposition", "attachment; filename=sample.jpg");
        resp.setContentType("image/jpeg");
        
        
        
        String path = req.getServletContext().toString();
        System.out.println(path +"hjier");
 
        try(InputStream in = req.getServletContext().getResourceAsStream("/upload/"+ username + "/" + filename);
                
  
          ServletOutputStream out = resp.getOutputStream()) {
            byte[] buffer = new byte[ARBITARY_SIZE];
         
            int read=0;
            while ((read = in.read(buffer)) != -1) {
                
                         
                out.write(buffer, 0, read);
                System.out.println(read);
               
            }
         out.flush();
         out.close();
         in.close();
        }
        
      
        
        
    }
}
    
    
    
    
