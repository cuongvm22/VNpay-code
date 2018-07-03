/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author cuongvm
 */
public class TestLog {

    static Logger log = LoggerFactory.getLogger("logback");

    public static void main(String[] args) {
        String jsonText = "{\n"
                + "   \"results\" : [\n"
                + "      {\n"
                + "         \"address_components\" : [\n"
                + "            {\n"
                + "               \"long_name\" : \"22\",\n"
                + "               \"short_name\" : \"22\",\n"
                + "               \"types\" : [ \"street_number\" ]\n"
                + "            },\n"
                + "            {\n"
                + "               \"long_name\" : \"Láng Hạ\",\n"
                + "               \"short_name\" : \"Láng Hạ\",\n"
                + "               \"types\" : [ \"route\" ]\n"
                + "            },\n"
                + "            {\n"
                + "               \"long_name\" : \"Đống Đa\",\n"
                + "               \"short_name\" : \"Đống Đa\",\n"
                + "               \"types\" : [ \"administrative_area_level_2\", \"political\" ]\n"
                + "            },\n"
                + "            {\n"
                + "               \"long_name\" : \"Hà Nội\",\n"
                + "               \"short_name\" : \"Hà Nội\",\n"
                + "               \"types\" : [ \"administrative_area_level_1\", \"political\" ]\n"
                + "            },\n"
                + "            {\n"
                + "               \"long_name\" : \"Việt Nam\",\n"
                + "               \"short_name\" : \"VN\",\n"
                + "               \"types\" : [ \"country\", \"political\" ]\n"
                + "            }\n"
                + "         ],\n"
                + "         \"formatted_address\" : \"22 Láng Hạ, Đống Đa, Hà Nội, Việt Nam\",\n"
                + "         \"geometry\" : {\n"
                + "            \"location\" : {\n"
                + "               \"lat\" : 21.016005,\n"
                + "               \"lng\" : 105.8144499\n"
                + "            },\n"
                + "            \"location_type\" : \"ROOFTOP\",\n"
                + "            \"viewport\" : {\n"
                + "               \"northeast\" : {\n"
                + "                  \"lat\" : 21.0173539802915,\n"
                + "                  \"lng\" : 105.8157988802915\n"
                + "               },\n"
                + "               \"southwest\" : {\n"
                + "                  \"lat\" : 21.0146560197085,\n"
                + "                  \"lng\" : 105.8131009197085\n"
                + "               }\n"
                + "            }\n"
                + "         },\n"
                + "         \"place_id\" : \"ChIJmZ89eGOrNTERx0hFvSpswwQ\",\n"
                + "         \"types\" : [ \"street_address\" ]\n"
                + "      }\n"
                + "   ],\n"
                + "   \"status\" : \"OK\"\n"
                + "}";
        JSONObject jo = new JSONObject(jsonText);
        JSONArray ja = (JSONArray) jo.get("results");
        jo = (JSONObject) ja.get(0);
        JSONObject location = (JSONObject) jo.get("geometry");
        jo = (JSONObject) location.get("location");
        System.out.println(jo.get("lat"));
    }
}
