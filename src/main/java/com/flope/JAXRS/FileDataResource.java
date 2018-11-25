/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flope.JAXRS;

import java.io.InputStream;
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
    public Response receicefile(InputStream uploadedInputStream){
        
        Response res = null;
        
        System.out.println(uploadedInputStream.toString());
        return res;
        
        
        
    
    
    }
    
    
    
}
