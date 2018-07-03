/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zeros.mapsapi.controller;

import org.slf4j.Logger;
import java.io.IOException;

import zeros.mapsapi.model.Location;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import zeros.mapsapi.model.LatLng;

/**
 *
 * @author cuongvm
 */
public class GetDistance {

    private String distance;
    private String duration;
    private Logger log = LoggerFactory.getLogger("logback");
    
    public GetDistance(Location loc, LatLng p) throws IOException {
        JSONObject jo;
        JSONArray ja;
        try {
            jo = new GetJson().getJsonDistance(loc, p, "AIzaSyBs5tL2NKzpKEQ60wTCzyBLDjVfq3_hJ6I");
            ja = jo.getJSONArray("rows");
            jo = ja.getJSONObject(0);
            ja = jo.getJSONArray("elements");
            jo = ja.getJSONObject(0);
            if ((this.distance = jo.getJSONObject("distance").getString("text")) == null || this.distance == "" )
                this.distance = "cant get";
            if ((this.duration = jo.getJSONObject("duration").getString("text")) == null || this.duration == "" )
                this.duration = "cant get";
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public String getDistance() {
        return distance;
    }

    public String getDuration() {
        return duration;
    }

}
