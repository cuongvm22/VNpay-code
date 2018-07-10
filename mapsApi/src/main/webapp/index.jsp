<%-- 
    Document   : index
    Created on : Jun 21, 2018, 10:04:49 AM
    Author     : cuongvm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> </title>
    </head>
    <body>
        

        <form action="placeSearch">
            <h1>${error}</h1>
            Hello! 
            </br>
            <label>
                Import your Address.
            </label>
            <br>
            <input type="text" name="address" value="" />
            <br>
            <label>
                Import radius (meters)
            </label>
            <br>
            <input type="text" name="radius" value="" />
            <br>
            <input type="submit" value="Submit" />


        </form>
        <a href="hello">a</a>
    </body>
</html>
