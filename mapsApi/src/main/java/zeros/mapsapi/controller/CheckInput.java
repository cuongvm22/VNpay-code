/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zeros.mapsapi.controller;

/**
 *
 * @author cuongvm
 */
public class CheckInput {

    public boolean back;
    public String mes = "";
    public double radius;

    public CheckInput(String address, String radiusString) {
        this.back = true;
        if (address == "" || radiusString == "") {
            this.mes += "Input is null. Input again.<br>";
            this.back = false;
        } else {
            try {
                radius = Double.parseDouble(radiusString);
                if (this.radius < 0) {
                    this.mes = "radius > 0<br>";
                    this.back = false;
                }
            } catch (NumberFormatException e) {
                this.mes += "Radius is number";
                this.back = false;
            }
        }
    }

}
