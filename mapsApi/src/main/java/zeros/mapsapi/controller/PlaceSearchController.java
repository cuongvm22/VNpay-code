/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zeros.mapsapi.controller;

import java.io.IOException;
import java.util.List;
import zeros.mapsapi.model.Location;
import zeros.mapsapi.model.Place;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

/**
 *
 * @author cuongvm
 */
@Controller
public class PlaceSearchController {

    private static Logger log = LoggerFactory.getLogger("logback");

    @RequestMapping("/placeSearch")
    public String placeSearch(@RequestParam("address") String address, @RequestParam("radius") String radiusString, ModelMap m) throws IOException {

        CheckInput ci = new CheckInput(address, radiusString);
        if (!ci.back){
            m.addAttribute("error", ci.mes);
            log.error(ci.mes);
            return "../../index";  
        }
        try {
            Location loc = new GetInputAddress(address, "AIzaSyBwwvhuVC0HKxPvk9Ei5LwOwfZ1qq6oqAU").getLocation();
            List<Place> list = new GetNearPlace(loc, "AIzaSyBukfuUVjFaeUeKDP0QBIyih4sAiENYlg8", ci.radius, "").getNearPlace();
            m.addAttribute("address", loc.getFormatted_address());
            m.addAttribute("lat", loc.getLatlng().getLat());
            m.addAttribute("lng", loc.getLatlng().getLng());
            m.addAttribute("list", list);

        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            return "result";
        }
    }

    @RequestMapping("/hello")
    public String sayHello() {
        return "hello";
    }
}
