<%-- 
    Document   : result
    Created on : Jun 21, 2018, 10:16:30 AM
    Author     : cuongvm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            /* Always set the map height explicitly to define the size of the div
             * element that contains the map. */
            #map {
                height: 300px;
                width: 100%
               
            }
            /* Optional: Makes the sample page fill the window. */
            html, body {
                height: 100%;
                margin: 0;
                padding: 0;
            }
        </style>
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBu-916DdpKAjTmJNIgngS6HL_kDIKU0aU&callback=initMap"
        async defer></script>
        <script>
            var map;
            function initMap() {
                map = new google.maps.Map(document.getElementById('map'), {
                    center: new google.maps.LatLng(${lat}, ${lng}),
                    zoom: 10
                });
            }
        </script>
        
    </head>
    <body>
        <h2>Address: ${address}</h2>
        <h3>Lat, lng: ${lat}, ${lng}</h3>
        <div id="map"></div>


    </body>
</html>
