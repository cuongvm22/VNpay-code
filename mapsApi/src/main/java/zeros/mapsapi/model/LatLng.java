/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zeros.mapsapi.model;

/**
 *
 * @author cuongvm
 */
public class LatLng {

    private double lat;
    private double lng;

    public LatLng(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }


    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    @Override
    public String toString() {
        return this.lat + "," + this.lng;
    }
    
}
