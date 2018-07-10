<%-- 
    Document   : index
    Created on : Jul 9, 2018, 8:45:05 AM
    Author     : cuongvm
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

    <meta http-equiv="content-type" content="text/html;charset=utf-8" /><!-- /Added by HTTrack -->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Danh s&#225;ch đơn h&#224;ng</title>
        <link href="styles/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="styless/css/jumbotron-narrow.css" rel="stylesheet" type="text/css"/>
        <script src="styles/js/jquery-1.12.4.min.js"></script>
    </head>

    <body>


        <div class="container">
            <div class="header clearfix">
                <nav>
                    <ul class="nav nav-pills pull-right">
                        <li role="presentation" class="active"><a href="index.jsp">Danh sách</a></li>
                        <li role="presentation"><a href="createOrder">Tạo mới</a></li>


                    </ul>
                </nav>
                <h3 class="text-muted">VNPAY DEMO</h3>
            </div>

            <h3>Danh s&#225;ch đơn h&#224;ng</h3>


            <div class="table-responsive">
                <!-- top pagination -->
                <div class="pager">
                    <ul class="pagination">                        
                        <li><a href="index?pages=${back}">Back &laquo;</a></li>  
                            <c:forEach items="${paginationNumber}" var="e">
                                <c:if test="${e != 0}">
                                <li><a href="index?pages=${e}">${e}</a></li> 
                                </c:if>
                                <c:if test="${e == 0}">
                                <li><a href="#">...</a></li> 
                                </c:if>
                            </c:forEach>
                        <li><a href="index?pages=${next}"> &raquo; Next</a></li>                    
                    </ul>
                </div>


                <!-- show data from DB-->
                <table class="table table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>
                                Mã
                            </th>
                            <th>
                                Số tiền
                            </th>
                            <th>
                                Nội dung
                            </th>
                            <th>
                                Ngày tạo
                            </th>

                            <th>
                                Tình trạng
                            </th>

                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listOrder}" var="e">
                            <tr>
                                <td>
                                    <c:out value="${e.id}"></c:out>                   
                                    </td>
                                    <td>
                                    <c:out value="${e.totalPrice}"></c:out> 
                                    </td>
                                    <td>
                                    <c:out value="${e.content}"></c:out> 
                                    </td>
                                    <td class="center">
                                    <c:out value="${e.time}"></c:out> 
                                    <c:out value="${e.date}"></c:out> 
                                    </td>
                                    <td class="center">
                                        <span class="pay-unsuccess">
                                        <c:out value="${e.property}"></c:out> 
                                        </span>
                                    </td>

                                    <td>
                                        <a href="detail?id=${e.id}"  >Chi tiết</a>                                 
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>


                <!--bottom pagination-->  



            </div>

            <footer class="footer">
                <p>&copy; VNPAY 2018</p>
            </footer>
        </div> <!-- /container -->
        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    </body>

    <!-- Mirrored from sandbox.vnpayment.vn/tryitnow/ by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 18 Jun 2018 01:38:24 GMT -->
</html>

