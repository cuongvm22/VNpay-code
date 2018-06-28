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
public class Place extends Location {
    private double rate;
    private String[] types;
    private String name;

    public Place(double rate, String[] types, String name, String formatted_address, LatLng latlng, String place_id) {
        super(formatted_address, latlng, place_id);
        this.rate = rate;
        this.types = types;
        this.name = name;
    }

    public double getRate() {
        return rate;
    }

    public String[] getTypes() {
        return types;
    }

    public String getName() {
        return name;
    }
    
    

}
