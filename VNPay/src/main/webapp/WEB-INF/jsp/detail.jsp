<%-- 
    Document   : newjsp1
    Created on : Jun 19, 2018, 4:47:22 PM
    Author     : cuongvm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <!-- Mirrored from sandbox.vnpayment.vn/tryitnow/ by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 18 Jun 2018 01:38:15 GMT -->
    <!-- Added by HTTrack --><meta http-equiv="content-type" content="text/html;charset=utf-8" /><!-- /Added by HTTrack -->
    <head>
       <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Chi tiết đơn hàng</title>
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


            <h3>Chi tiết</h3>
            <div class="table-responsive">

                <div class="form-group">
                    <label for="OrderId">Mã đơn hàng</label>
                    ${order.id}
                </div>
                <div class="form-group">
                    <label for="Amount">Số tiền</label>
                    ${order.totalPrice}
                </div>
                <div class="form-group">
                    <label for="OrderDescription">Nội dung thanh toán</label>
                    ${order.content}

                </div>
                <div class="form-group">
                    <label >Trạng thái</label>
                    <span class="pay-unsuccess">
                        ${order.property}
                    </span>

                </div>
            </div>
            <p>
                <a href="index.jsp">Về danh s&#225;ch</a>
            </p>

            <footer class="footer">
                <p>&copy; VNPAY 2018</p>
            </footer>

    </body>
</html>
