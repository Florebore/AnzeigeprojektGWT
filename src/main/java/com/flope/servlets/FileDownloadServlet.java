/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flope.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Florian
 */
  
@WebServlet("/download")

public class FileDownloadServlet extends HttpServlet {
    private final int ARBITARY_SIZE = 1048;
 
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
      throws ServletException, IOException {
     
        resp.setContentType("image/jpg");
        resp.setHeader("Content-disposition", "attachment; filename=sample.jpg");
        
        String path = req.getServletContext().toString();
        System.out.println(path +"hjier");
 
        try(InputStream in = req.getServletContext().getResourceAsStream("/WEB-INF/Anzeige1.jpeg");
                
  
          OutputStream out = resp.getOutputStream()) {
 
            byte[] buffer = new byte[ARBITARY_SIZE];
         
            int numBytesRead;
            while ((numBytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, numBytesRead);
            }
        }
    }
}
    
    
    
    
