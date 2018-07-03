/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zeros.mapsapi.controller;

import java.io.IOException;
import java.util.List;
import zeros.mapsapi.model.LatLng;
import zeros.mapsapi.model.Location;
import zeros.mapsapi.model.Place;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author cuongvm
 */
public class GetInputAddress {

    private Location loca;
    private List<Place> nearbyPlace;

    public GetInputAddress(String address, String apiKey) throws IOException {
        //get Infomation of input address
        JSONObject jo = new GetJson().getJsonLocation(address, apiKey);
        JSONArray jarray = (JSONArray) jo.getJSONArray("results");
        JSONObject j = (JSONObject) jarray.get(0);
        JSONArray types = (JSONArray) j.get("types");
        String[] type = new String[types.length()];
        for (int i = 0; i < type.length; i++) {
            type[i] = (String) types.get(i);
        }
        JSONObject location = (JSONObject) (((JSONObject) j.get("geometry")).get("location"));
        loca = new Location(j.getString("formatted_address"), new LatLng(location.getDouble("lat"), location.getDouble("lng")), j.getString("place_id"));

        //find plance which near input address
    }

    public Location getLocation() {
        return loca;
    }

}
