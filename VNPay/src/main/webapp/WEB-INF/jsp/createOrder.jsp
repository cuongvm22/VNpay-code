<%-- 
    Document   : createOrder
    Created on : Jun 18, 2018, 8:53:41 AM
    Author     : cuongvm
--%>

<%@page import="java.util.*"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.sql.Date"%>
<%@page import="java.sql.Time"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <meta http-equiv="content-type" content="text/html;charset=utf-8" />
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Tạo mới đơn h&#224;ng</title>
        <link href="styles/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="styless/css/jumbotron-narrow.css" rel="stylesheet" type="text/css"/>
        <script src="styles/js/jquery-1.12.4.min.js"></script>


    </head>

    <body>

        <div class="container">
            <div class="header clearfix">
                <nav>
                    <ul class="nav nav-pills pull-right">
                        <li role="presentation" ><a href="index.jsp">Danh sách</a></li>
                        <li role="presentation" class="active"><a href="createOrder">Tạo mới</a></li>


                    </ul>
                </nav>
                <h3 class="text-muted">VNPAY DEMO</h3>
            </div>
            <% Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+7")); %>
            <% Date d = new Date(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)); %>
            <% Time t = new Time(cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND)); %>
            <h3>Tạo mới đơn h&#224;ng</h3>
            <div class="table-responsive">
                <form action="insert" id="CreateOrder" method="post">        
                    <div class="form-group">

                    </div>        
                    <div class="form-group">
                        <label for="Amount">Số tiền</label>
                        <input class="form-control" data-val="true" data-val-number="The field Amount must be a number." data-val-required="The Amount field is required." id="totalPrice" max="100000000" min="1" name="totalPrice" type="number" value="10000" />
                    </div>
                    <div class="form-group">
                        <label for="OrderDescription">Nội dung thanh toán</label>
                        <textarea class="form-control" cols="20" id="OrderDescription" name="content" rows="2"> Thanh toan don hang thoi gian: <%=d%> <%=t%></textarea>
                    </div>
                    <div class="form-group">
                        <label for="language">Tình trạng </label>
                        <select name="property" id="property" class="form-control">
                            <option value="Da thanh toan" >Đã thanh toán</option>
                            <option value="Chua thanh toan">Chưa thanh toán</option>
                        </select>
                    </div>
                    <input type="hidden" name="date" value="<%=d%>"/>
                    <input type="hidden" name="time" value="<%=t%>"/>

                    <input type="submit" class="btn btn-default navbar-btn" value="Thanh toán"/>

                </form>
            </div>
            <p>
                &nbsp;
            </p>

            <footer class="footer">
                <p>&copy; VNPAY 2018</p>
            </footer>
        </div> 
        <!-- /container -->
    </body>

    <!-- Mirrored from sandbox.vnpayment.vn/tryitnow/Home/CreateOrder by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 18 Jun 2018 01:38:26 GMT -->
</html>

