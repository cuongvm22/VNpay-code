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

    private static Logger log = LoggerFactory.getLogger("logback");
    private Scanner s;
    private String jsonText = "";
    private String url = null;

    public JSONObject getJsonLocation(String address, String apiKey) throws MalformedURLException, IOException {

        try {
            log.info("start requet to Gooogle geocoding");
            url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + URLEncoder.encode(address, "utf-8") + "&key=" + apiKey;
            s = new Scanner(new InputStreamReader(new URL(url).openStream(), Charset.forName("utf-8")));
            while (s.hasNextLine()) {
                jsonText += s.nextLine();
            }
            s.close();
            log.info("End requet to Gooogle");

        } catch (Exception e) {
            log.error(e.getMessage());
            log.info(url);
        } finally {
            return new JSONObject(jsonText);
        }
    }

    public JSONObject getJsonPlane(LatLng location, String apiKey, double radius, String type) throws MalformedURLException, UnsupportedEncodingException, IOException {
        String jsonText = "";
        String url = null;
        try {
            log.info("start requet to Gooogle nearby search");
            url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + location.getLat() + "," + location.getLng() + "&radius=" + radius + "&type=" + type + "&keyword=cruise&key=" + apiKey;
            s = new Scanner(new InputStreamReader(new URL(url).openStream(), Charset.forName("utf-8")));
            while (s.hasNextLine()) {
                jsonText += s.nextLine();
            }
            s.close();
            log.info("End requet to Gooogle");

        } catch (Exception e) {
            log.error(e.getMessage());
            log.info(url);
        } finally {
            return new JSONObject(jsonText);
        }
    }

    public JSONObject getJsonDistance(Location loc, LatLng latlng, String apiKey) throws MalformedURLException, IOException {
        String jsonText = "";
        String url = null;
        try {
            log.info("start requet to Gooogle Distancematrix");
            url = "https://maps.googleapis.com/maps/api/distancematrix/json?units=metric&origins=" + loc.getLatlng().toString() + "&destinations=" + latlng.toString() + "&key=" + apiKey;
            s = new Scanner(new InputStreamReader(new URL(url).openStream(), Charset.forName("utf-8")));
            while (s.hasNextLine()) {
                jsonText += s.nextLine();
            }
            s.close();
            log.info("End requet to Gooogle");

        } catch (Exception e) {
            log.error(e.getMessage());
            log.info(url);
        } finally {
            return new JSONObject(jsonText);
        }
    }

}
