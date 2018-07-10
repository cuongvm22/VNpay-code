<%-- 
    Document   : result
    Created on : Jun 21, 2018, 10:16:30 AM
    Author     : cuongvm
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="ISO-8859-1" %> 
<%@ page import="zeros.mapsapi.model.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html">
        <meta charset="utf-8">
        <title>JSP Page</title>
    </head>
    <body>

        <h2>Address: ${address}</h2>
        <h3>Lat, lng: ${lat}, ${lng}</h3>
        <h3>Place : ${listPlace}</br></h3>
        <table border="1">
            <tr>
                <td>Name
                <td>Address
                <td>Distance
                <td>Duration
            </tr>
            
            <c:forEach items="${list}" var="e">
                <tr>
                    <td><c:out value="${e.name}"></c:out></td>
                    <td><c:out value=" ${e.formatted_address}"></c:out></td>
                    <td><c:out value=" ${e.distance}"></c:out></td>
                    <td><c:out value=" ${e.duration}"></c:out></td>
                </tr> 
            </c:forEach>
        </table>
    </body>
</html>
