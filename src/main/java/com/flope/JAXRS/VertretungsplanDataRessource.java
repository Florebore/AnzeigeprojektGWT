package com.flope.JAXRS;

import com.flope.DatabaseServices.UserDataService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Scanner;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonBuilderFactory;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

/**
 *
 * @author peterkirchhoff
 */
@Path("vertretungsplan")
public class VertretungsplanDataRessource {
    

    
    @GET
    public JsonArray vertretungsplan() throws FileNotFoundException
    {  
 
        //holt sich aus pathname eine txt-Datei, die ausgelesen wird und mit 
        //dem Separator ";" in einen String-Array eingelesen wird
        String pathname = "/Users/peterkirchhoff/Dropbox/Studien/Informatik/6SEP/SEPNeu/Projektunterlagen/PeterArbeit/hello1.txt";
        String lineArray [] = new String[0];
        File file = new File(pathname);
        StringBuilder fileContents = new StringBuilder((int) file.length());

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
               
                fileContents.append(scanner.nextLine() + System.lineSeparator());
                lineArray = fileContents.toString().split(";");

            }
    
   
    
    JsonArray array = null;
    JsonBuilderFactory factory = null;
    JsonArrayBuilder builder = Json.createArrayBuilder();
    JsonObjectBuilder obuilder = Json.createObjectBuilder();

    for (int x=0;(x+20)<lineArray.length;x+=10) {
        
        
   
        
        builder.add(obuilder
                
        .add("Kürzel",lineArray[x])
        .add("Kürzel", lineArray[x+1])
        .add("Datum", lineArray[x+2])
        .add("Stunde", lineArray[x+3])
        .add("Klasse", lineArray[x+4])
        .add("LehrerOut", lineArray[x+5])
        .add("LehrerIn", lineArray[x+6])
        .add("Raum", lineArray[x+7])
        .add("Kommentar", lineArray[x+8])
        .add("Fach", lineArray[x+9])); //works
        
                
    }
    array = builder.build();
    return array;

}


        }
        }

 
              
            
            
    
            
