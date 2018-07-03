
import zeros.mapsapi.controller.GetNearPlace;
import zeros.mapsapi.controller.GetInputAddress;
import zeros.mapsapi.model.Place;
import zeros.mapsapi.model.Location;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cuongvm
 */
public class main {
    public static void main(String[] args) throws IOException {
//        Location loc = new GetInputAddress("22 láng hạ", "AIzaSyBukfuUVjFaeUeKDP0QBIyih4sAiENYlg8").getLocation();
//        System.out.println(loc.getFormatted_address());
//        List<Place> places = new ArrayList<>();
//        places = new GetNearPlace(loc, "AIzaSyBukfuUVjFaeUeKDP0QBIyih4sAiENYlg8", 1500, "").getNearPlace();
//        for (Place e : places) {
//            System.out.println(e.getName() + "\t" + e.getFormatted_address());
//        }
          Location loc = new GetInputAddress("22 láng hạ ", "AIzaSyBukfuUVjFaeUeKDP0QBIyih4sAiENYlg8").getLocation();
          List<Place> list = new GetNearPlace(loc, "AIzaSyBukfuUVjFaeUeKDP0QBIyih4sAiENYlg8", 1500, "").getNearPlace();
          for (Place e: list)
              System.out.println(e.getName());
    }
}
