/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdev.mavenproject2;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cuongvm
 */
public class NewClass extends HttpServlet{

    public static void main(String[] args) throws ApiException, InterruptedException, IOException {
        GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyBs5tL2NKzpKEQ60wTCzyBLDjVfq3_hJ6I").build();
        GeocodingResult[] results = GeocodingApi.geocode(context, "22 lang ha").await();
        String result = new GsonBuilder().setPrettyPrinting().create().toJson(results[0].addressComponents);
        JsonArray ja = (JsonArray) new JsonParser().parse(result);
        Map data = new HashMap<String, String>();
        //System.out.println(gson.toJson(results[0].addressComponents));
        Long start = System.currentTimeMillis();
        for (int i = 0; i < ja.size(); i++) {
            JsonObject jo = (JsonObject) ja.get(i);
            String value = jo.get("longName").getAsString();
            JsonArray keys = (JsonArray) jo.get("types");
            System.out.println(keys.get(0).getAsString() + " \t" + value );
            data.put(keys.get(0).getAsString(), value);
        }
        Long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        try {
            Map data = new HashMap<String, String>();
            
            GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyBs5tL2NKzpKEQ60wTCzyBLDjVfq3_hJ6I").build();
            GeocodingResult[] results = GeocodingApi.geocode(context, (String) req.getParameter("address")).await();
            //cover results to json
            String result = new GsonBuilder().setPrettyPrinting().create().toJson(results[0].addressComponents);
            JsonArray ja = (JsonArray) new JsonParser().parse(result);
            
            for (int i = 0; i < ja.size(); i++) {
                JsonObject jo = (JsonObject) ja.get(i);
                String value = jo.get("longName").getAsString();
                // a array without element which is jsonObject
                JsonArray keys = (JsonArray) jo.get("types");
                data.put(keys.get(0).getAsString(), value);
            }
            String address = data.get("STREET_NUMBER")+ " " + data.get("ROUTE") +", " + data.get("ADMINISTRATIVE_AREA_LEVEL_2") +", " + data.get("ADMINISTRATIVE_AREA_LEVEL_1") + ", " + data.get("COUNTRY") ; 
            req.setAttribute("address", address );
            req.setAttribute("lat", results[0].geometry.location.lat );
            req.setAttribute("lng", results[0].geometry.location.lng );
            req.setAttribute("json", result);
            
            RequestDispatcher rd = req.getRequestDispatcher("result.jsp");
            rd.forward(req, resp);
            
            
        } catch (ApiException ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

}
