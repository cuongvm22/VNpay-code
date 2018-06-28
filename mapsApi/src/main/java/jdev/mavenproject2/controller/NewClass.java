/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdev.mavenproject2.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import jdev.mavenproject2.model.Location;
import jdev.mavenproject2.model.Place;
import org.json.JSONArray;
import org.json.JSONObject;

import sun.misc.IOUtils;

/**
 *
 * @author cuongvm
 */
public class NewClass {

    public static void main(String[] args) throws IOException {
       //GetData location =  new
        Location loc = new GetInputAddress("22 lang ha", "AIzaSyBukfuUVjFaeUeKDP0QBIyih4sAiENYlg8").getLocation();
        System.out.println(loc.getFormatted_address());
        List<Place> places = new GetNearPlace(loc, "AIzaSyBukfuUVjFaeUeKDP0QBIyih4sAiENYlg8", 1500, "").getNearPlace();
        for (Place e: places)
            System.out.println(e.getName() + "\t" + e.getFormatted_address());
    }
}
