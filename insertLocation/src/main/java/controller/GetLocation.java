/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import java.io.IOException;

/**
 *
 * @author cuongvm
 */
public class GetLocation {

    public LatLng GetLocation(String apiKey, String address) throws ApiException, InterruptedException, IOException {
        GeoApiContext context = new GeoApiContext.Builder().apiKey(apiKey).build();
        GeocodingResult[] results = null;
        results = GeocodingApi.geocode(context, address).await();
        return results[0].geometry.location;
    }

}
