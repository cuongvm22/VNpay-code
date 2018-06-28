/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdev.mavenproject2.controller;

import jdev.mavenproject2.model.*;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author cuongvm
 */
@Controller
@RequestMapping("/PlaceSearch")
public class PlaceSearch extends HttpServlet {
    static final Logger log = LoggerFactory.getLogger("logback");
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        Location loc = new GetInputAddress(request.getParameter("address"), "AIzaSyBwwvhuVC0HKxPvk9Ei5LwOwfZ1qq6oqAU").getLocation();
        request.setAttribute("address", loc.getFormatted_address());
        request.setAttribute("lat", loc.getLatlng().getLat());
        request.setAttribute("lng", loc.getLatlng().getLng());
        List<Place> listPlace = new GetNearPlace(loc, "AIzaSyBwwvhuVC0HKxPvk9Ei5LwOwfZ1qq6oqAU", 1500, "").getNearPlace();
        request.setAttribute("listPlace", listPlace );
        RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
        rd.forward(request, response);
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
