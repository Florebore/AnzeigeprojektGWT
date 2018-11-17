/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flope.converter;

import com.flope.entities.Userdata;
import com.google.gson.Gson;
import java.io.StringReader;
import java.util.List;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;

/**
 *
 * @author Florian
 */
@Stateless
public class DatabaseListtoJsonArray {
    
    
    public JsonArray ListtoJsonArray(List<Userdata> list){

        JsonArray array = null;
        Gson gson = new Gson();
    
        String userlist = gson.toJson(list);         
        
        try  {
            JsonReader jsonReader = Json.createReader(new StringReader(userlist));
            array = jsonReader.readArray();
            System.out.println(array.toString()+"Converter");
            return array;
        }
            
        
            
        catch(Exception e){System.out.println("Conversion from List<Userdata> to JsonArray failed!");
     
        return array;}
    
    
}}
