<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    Document   : newjsp
    Created on : Jun 8, 2022, 11:26:05 AM
    Author     : HOANG ANH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>profile user</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link href="<c:url value="/assets/css/bootstrap.min.css" />" rel="stylesheet">
    <script src="<c:url value="/assets/js/jquery-1.12.1.min.js" />" ></script>
    <script src="<c:url value="/assets/js/bootstrap.min.js" />" ></script>
    <link rel="icon" href="<c:url value="/assets/img/favicon.png"/> ">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<c:url value="/assets/css/bootstrap.min.css"/> ">
    <!-- animate CSS -->
    <link rel="stylesheet" href="<c:url value="/assets/css/animate.css"/>">
    <!-- owl carousel CSS -->
    <link rel="stylesheet" href="<c:url value="/assets/css/owl.carousel.min.css"/> ">
    <!-- nice select CSS -->
    <link rel="stylesheet" href="<c:url value="/assets/css/nice-select.css"/> ">
    <!-- font awesome CSS -->
    <link rel="stylesheet" href="<c:url value="/assets/css/all.css"/> ">
    <!-- flaticon CSS -->
    <link rel="stylesheet" href="<c:url value="/assets/css/flaticon.css"/> ">
    <link rel="stylesheet" href="<c:url value="/assets/css/themify-icons.css"/>">
    <!-- font awesome CSS -->
    <link rel="stylesheet" href="<c:url value="/assets/css/magnific-popup.css"/> ">
    <!-- swiper CSS -->
    <link rel="stylesheet" href="<c:url value="/assets/css/slick.css"/> ">
    <link rel="stylesheet" href="<c:url value="/assets/css/price_rangs.css"/> ">
    <!-- style CSS -->
    <link rel="stylesheet" href="<c:url value="/assets/css/style.css"/> ">
</head>
<style>
    body,html{
        margin: 0;
        padding: 0;
    }
    .col1{
        height: 600px;
    }
    table input{
        height: 40px;
        width: 500px;
        margin-bottom: 40px;
        margin-right: 40px;
        border: 1px solid #cccccc;
    }
    table td p{
        /*margin-bottom: 40px;*/
    }
    table button{
        margin-bottom: 40px;
        height: 40px;
        width: 90px;
        background-color:  #ff6666;
        border: none;

    }
    #up{
        display: none;
        color: red;
    }
</style>
<body>
<jsp:include page="header.jsp"/>
<script>
    setTimeout(function (){
        document.getElementById("van").style.display="none";
    },3000);
</script>
<div style="text-align: center;padding: 0;background-color:#cccccc;margin-top: 105px; " class="row container-fluid ">
    <div style="background-color: #cccccc;display: block;" class="col1 col-md-3 row">
        <div style="height: 120px;" class="row">
            <div class="col-md-2"></div>
            <div style="border-bottom: 1px solid #999999; padding-bottom: 20px;" class="col-md-10 row">
                <div style="padding-right: 0;margin-top: 50px;width: 80px;padding-left: 0;" class="col-md-5" >
                    <img class="img-circle" height="80px" width="80px" src="<c:if test="${profile.image.contains(\"https://lh3.googleusercontent.com\")}">${profile.image}</c:if><c:if test="${!profile.image.contains(\"https://lh3.googleusercontent.com\")}"><c:if test="${profile.image==null||empty profile.image}">/shopfuniture/source/profile.jpg</c:if><c:if test="${ not empty profile.image&&profile.image!=null}">/shopfuniture/source/${profile.image}</c:if></c:if>" />
                </div>
                <div style="margin-left: 0;padding-left: 0;margin-top: 70px;cursor: pointer;text-align: left;" class="col-md-7">
                    <strong style="cursor: text;">profile</strong>
                    <br>
                    <p style="width: 100px;cursor: text;"><span class="glyphicon glyphicon-pencil"></span>edit profile</p>
                </div>
            </div>
        </div>
        <div style="margin-top: 40px;" class="row">
            <div class="col-md-4"></div>
            <div style="padding-left: 28px;" class="col-md-8">
                <div style="color: #0000ff;cursor: pointer;" data-bs-toggle="collapse" data-bs-target="#demo">
                    <p style="text-align: left;width: 200px;"><span class="glyphicon glyphicon-user"></span>
                        your profile individual
                    </p>
                </div>
                <div id="demo" class="collapse" style="text-align: left;">
                    <a class="a" style="text-align: left;" href="/shopfuniture/user/profile">your profile</a>
                    <br>
                    <br>
                    <a class="a" style="text-align: left;"  href="#">your order</a>
                    <br>
                    <br>
                    <a class="a" style="text-align: left;"  href="/shopfuniture/user/change_password">change password</a>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-8" style="background-color: #ffffff;height: 100%;">

        <div style="font-size:25px;text-align: left;margin-top: 15px;margin-left: 20px;">Đổi Mật Khẩu</div>
        <p style="text-align: left;margin-left: 20px;border-bottom: 1px solid #cccccc;padding-bottom: 15px;">
            Để tạo mức độ bảo mật cho tài khoản, shopfuniture đề xuất thay đổi mật khẩu mỗi 3 tuần</p>
        <div style="display: flex;">
            <div style="margin-left: 20px;">
                <table style="border-right: 1px solid #cccccc;">
                    <tr>
                        <td><p>chú ý:</p></td>
                        <td><p style="text-align: left;color: black;">Để bảo mật tài khoản, vui lòng không chia sẻ mật khẩu cho người khác</p>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><p id="up" style="margin-bottom: 0;"></p></td>
                    </tr>
                    <tr>
                        <td><p>Mật Khẩu Cũ</p></td>
                        <td><input type="password" name="oldpass"  oninput="myfunction()"></td>
                    </tr>
                    <tr>
                        <td><p>Mật Khẩu Mới</p></td>
                        <td><input type="password" name="newpass" oninput="myfunction()"></td>
                    </tr>
                    <tr>
                        <td><p>Xác Nhận Mật Khẩu Mới</p></td>
                        <td><input type="password" name="newpass1" oninput="myfunction()"></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td style="display:flex;align-items: flex-start;"><button id="sub">Lưu</button><a style="text-decoration: none;color: black;margin-left: 260px;margin-top: 10px;" href="#">Quên Mật Khẩu?</a></td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
    <div class="col-md-1" style="background-color: #cccccc;height: 100%;"><h1></h1> </div>
</div>
<script>
    let old=document.getElementsByName("oldpass")[0].value.trim();
    let npass=document.getElementsByName("newpass")[0].value.trim();
    let npass1=document.getElementsByName("newpass1")[0].value.trim();
    if(npass==""||old==""||npass1==""||npass.length<8||npass1.length<8||old.length<8){
        document.getElementById("sub").style.cursor="not-allowed";
        document.getElementById("sub").style.backgroundColor="#ffcccc";
        document.getElementById("sub").style.color="#999999";
    }
    function myfunction(){
        if(document.getElementsByName("oldpass")[0].value.trim().length>7&&
            document.getElementsByName("newpass")[0].value.trim()!=""&&
            document.getElementsByName("oldpass")[0].value.trim()!=""&&
            document.getElementsByName("newpass1")[0].value.trim()!=""&&
            document.getElementsByName("newpass")[0].value.trim().length>7&&
            document.getElementsByName("newpass1")[0].value.trim().length>7){
            document.getElementById("sub").style.cursor="pointer";
            document.getElementById("sub").style.backgroundColor="#ff6666";
            document.getElementById("sub").style.color="black";
            document.getElementById("sub").onclick=function (){
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function() {
                    if (this.readyState == 4 && this.status == 200) {
                        if(this.responseText!=null||this.responseText.trim()!=""){
                            if(this.responseText=="success"){
                                document.getElementById("up").style.display="none";
                                alert("change password successful");
                                location.reload();
                            }
                           else if(this.responseText=="equal"){
                               document.getElementById("up").style.display="block";
                                document.getElementById("up").innerHTML="Mật khẩu mới phải khớp";
                            }
                            else if(this.responseText=="pass"){
                                document.getElementById("up").style.display="block";
                                document.getElementById("up").innerHTML="Mật khẩu không đúng";
                            }
                            else {
                                document.getElementById("up").style.display="block";
                                document.getElementById("up").innerHTML="Xảy ra lỗi vui lòng thử lại!";
                            }
                        }
                    }
                };
                xhttp.open("POST", "/shopfuniture/user/change_password?newpass="+document.getElementsByName("newpass")[0].value.trim()
                   + "&newpass1="+document.getElementsByName("newpass1")[0].value.trim()+
                    "&old="+document.getElementsByName("oldpass")[0].value.trim(), true);
                xhttp.send();
            }
        }
        else{
            document.getElementById("sub").style.cursor="not-allowed";
            document.getElementById("sub").style.backgroundColor="#ffcccc";
            document.getElementById("sub").style.color="#999999";
        }}
</script>
<jsp:include page="footer.jsp"/>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="<c:url value="/assets/js/jquery-1.12.1.min.js"/> "></script>
<!-- popper js -->
<script src="<c:url value="/assets/js/popper.min.js"/> "></script>
<!-- bootstrap js -->
<script src="<c:url value="/assets/js/bootstrap.min.js "/> "></script>
<!-- easing js -->
<script src="<c:url value="/assets/js/jquery.magnific-popup.js"/> "></script>
<!-- swiper js -->
<script src="<c:url value="/assets/js/swiper.min.js"/> "></script>
<!-- swiper js -->
<script src="<c:url value="/assets/js/masonry.pkgd.js "/> "></script>
<!-- particles js -->
<script src="<c:url value="/assets/js/owl.carousel.min.js"/> "></script>
<script src="<c:url value="/assets/js/jquery.nice-select.min.js"/>"></script>
<!-- slick js -->
<script src="<c:url value="/assets/js/slick.min.js"/> "></script>
<script src="<c:url value="/assets/js/jquery.counterup.min.js"/> "></script>
<script src="<c:url value="/assets/js/waypoints.min.js"/> "></script>
<script src="<c:url value="/assets/js/contact.js"/> "></script>
<script src="<c:url value="/assets/js/jquery.ajaxchimp.min.js"/> "></script>
<script src="<c:url value="/assets/js/jquery.form.js"/> "></script>
<script src="<c:url value="/assets/js/jquery.validate.min.js"/> "></script>
<script src="<c:url value="/assets/js/mail-script.js"/> "></script>
<script src="<c:url value="/assets/js/stellar.js"/> "></script>
<script src="<c:url value="/assets/js/price_rangs.js"/> "></script>
<!-- custom js -->
<script src="<c:url value="/assets/js/custom.js"/> "></script>
</html>
