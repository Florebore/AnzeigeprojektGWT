/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flope.JAXRS;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 *
 * @author Florian
 */
@Path("file")
public class FileDataResource {

    
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
           
     
    public Response receicefile(InputStream inputStream) throws FileNotFoundException, IOException {    
    String outputFile = "/Users/peterkirchhoff/NetBeansProjects/Anzeigeprojekt_Server_ver2/target/Anzeigeprojekt_Server_ver2/imagedownload/output.jpg";
    Files.copy(inputStream, Paths.get(outputFile),StandardCopyOption.REPLACE_EXISTING);
    File file = new File(outputFile);
    System.out.println(inputStream.toString());
    inputStream.close();
    
     return Response.status(Response.Status.CREATED).build();
    
    }
}
/*
    private Response doReceicefile(InputStream uploadedInputStream) {
        Response res = null;
        
        System.out.println(uploadedInputStream.toString());
        return res;
    }
    
    */
    

