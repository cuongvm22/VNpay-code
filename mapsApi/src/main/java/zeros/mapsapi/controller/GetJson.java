/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zeros.mapsapi.controller;

import zeros.mapsapi.model.Location;
import zeros.mapsapi.model.LatLng;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author cuongvm
 */
public class GetJson {

    static final Logger log = LoggerFactory.getLogger("logback");

    public JSONObject getJsonLocation(String address, String apiKey) throws MalformedURLException, IOException {
        String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + URLEncoder.encode(address, "utf-8") + "&key=" + apiKey;
        Scanner s = new Scanner(new InputStreamReader(new URL(url).openStream(), Charset.forName("utf-8")));
        String jsonText = "";
        while (s.hasNextLine()) {
            jsonText += s.nextLine();
        }
        return new JSONObject(jsonText);
    }

    public JSONObject getJsonPlane(LatLng location, String apiKey, double radius, String type) throws MalformedURLException, UnsupportedEncodingException, IOException {
        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + location.getLat() + "," + location.getLng() + "&radius=" + radius + "&type=" + type + "&keyword=cruise&key=" + apiKey;
        Scanner s = new Scanner(new InputStreamReader(new URL(url).openStream(), Charset.forName("utf-8")));
        String jsonText = "";
        while (s.hasNextLine()) {
            jsonText += s.nextLine();
        }
        return new JSONObject(jsonText);
    }

    public JSONObject getJsonDistance(Location loc, LatLng latlng, String apiKey) throws MalformedURLException, IOException {
        String jsonText = "";
        String url = null;
        try {
            url = "https://maps.googleapis.com/maps/api/distancematrix/json?units=metric&origins=" + loc.getLatlng().toString() + "&destinations=" + latlng.toString() + "&key=" + apiKey;
            Scanner s = new Scanner(new InputStreamReader(new URL(url).openStream(), Charset.forName("utf-8")));
            while (s.hasNextLine()) {
                jsonText += s.nextLine();
            }
            s.close();

        } catch (Exception e) {
            log.error(e.getMessage());
            log.info(url);
        } finally {
            return new JSONObject(jsonText);
        }
    }

}
