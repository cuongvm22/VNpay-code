/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdev.mavenproject2.controller;

import jdev.mavenproject2.model.*;
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

/**
 *
 * @author cuongvm
 */
public class GetJson {

    public JSONObject getJsonLocation(String address, String apiKey) throws MalformedURLException, IOException {
        String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + URLEncoder.encode(address, "utf-8") + "&key=" + apiKey;
        Scanner s = new Scanner(new InputStreamReader(new URL(url).openStream(), Charset.forName("utf-8")));
        String jsonText = "";
        while (s.hasNextLine()) {
            jsonText += s.nextLine();
        }
        return new JSONObject(jsonText);
    }

    public JSONObject getJsonPlane(LatLng location, String apiKey, int radius ,String type) throws MalformedURLException, UnsupportedEncodingException, IOException {
        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + location.getLat() + "," + location.getLng() + "&radius=" + radius + "&type=" + type + "&keyword=cruise&key=" + apiKey;
        Scanner s = new Scanner(new InputStreamReader(new URL(url).openStream(), Charset.forName("utf-8")));
        String jsonText = "";
        while (s.hasNextLine()) {
            jsonText += s.nextLine();
        }
        return new JSONObject(jsonText);
    }

}
