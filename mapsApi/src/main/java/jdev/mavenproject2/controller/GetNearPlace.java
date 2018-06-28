/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdev.mavenproject2.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import jdev.mavenproject2.model.*;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author cuongvm
 */
public class GetNearPlace {

    private List<Place> nearPlace;

    public GetNearPlace(Location loc, String apiKey, int radius, String type) throws UnsupportedEncodingException, IOException {
        this.nearPlace = new ArrayList<>();
        JSONObject jo = new GetJson().getJsonPlane(loc.getLatlng(), apiKey, radius, type);
        JSONArray ja = jo.getJSONArray("results");
        System.out.println(ja.length());
        for (int i = 0; i < ja.length(); i++) {
            jo = ja.getJSONObject(i);
            String[] types = new String[jo.getJSONArray("types").length()];
            for (int j = 0; j < types.length; j++) {
                types[j] = (jo.getJSONArray("types")).get(j).toString();
            }
            double rate = 0;
            try {
                rate = jo.getDouble("rating");
            } catch (Exception e){
                rate = 0;
            }
            nearPlace.add(new Place(rate, types, jo.getString("name"), jo.getString("vicinity"), new LatLng(jo.getJSONObject("geometry").getJSONObject("location").getDouble("lat"), jo.getJSONObject("geometry").getJSONObject("location").getDouble("lng")), jo.getString("place_id")));
        }

    }

    public List<Place> getNearPlace() {
        return nearPlace;
    }
    
}
