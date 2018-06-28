/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdev.mavenproject2.model;

/**
 *
 * @author cuongvm
 */
public class Location {

    private String formatted_address;
    private LatLng latlng;
    private String place_id;

    public Location(String formatted_address, LatLng latlng, String place_id) {
        this.formatted_address = formatted_address;
        this.latlng = latlng;
        this.place_id = place_id;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public LatLng getLatlng() {
        return latlng;
    }

    public String getPlace_id() {
        return place_id;
    }

}
