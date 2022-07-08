<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link href="https://fonts.gstatic,com" rel="preconnect"/>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2/family=Pacifico&display=swap"/>
    <script src="https://kit.fontawesome.com/c26cd2166c.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
</head>
<style>
    html{
        overflow-x: hidden;
    }
    *{
        padding: 0;
        margin: 0;
        box-sizing: border-box;
    }
    .row{
        background: white;
        border-radius: 30px;
        box-shadow: 12px 12px 22px;
    }
    .login img{
        border-top-left-radius: 30px;
        border-bottom-left-radius: 30px;
        border-bottom-right-radius: 300px;
    }
    .login h1{
        font-size: 4rem;
        font-weight: 700;
        font-family: 'pacifico',cursive;
    }
    .inp{
        height: 50px;
        width: 70%;
        border: none;
        outline: none;
        border-radius: 60px;
        box-shadow: -1px 1px 30px -11px rgba(0,0,0,0.75);

    }
    #content{
        display: none;
    }
    .btn1{
        height: 50px;
        width: 50%;
        border: none;
        outline: none;
        border-radius: 60px;
        font-weight: 600;
        background: rgb(223,56,56);
        color: white;
    }
    .btn1:hover{
        background: brown;
        transition: 0.5s;
    }
    .fa-google-plus{
        font-size: 2.5rem;
        color: rgb(224,22,72);
    }
    a{
        color: rgba(255,10,39,0.55);
    }
</style>
<body>
<section class="login py-5">
    <div class="container">
        <div class="row g-0" style="background-color: rgba(133,171,255,0.55);">
            <div class="col-lg-6">
                <img style="width: 100%;height: 100%;" class="img-fluid" alt="" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSUo_fRjgC-XkBBkTa7Ih7KMfuY0_Z948P3Ag&usqp=CAU">
            </div>
            <div class="col-lg-6 text-center py-5">
                <h1 class="animate__animated animate__heartBeat animate__infinite">Well Come</h1>
                <h4 class="animate__animated animate__heartBeat animate__infinite">furniture shop</h4>
                <c:if test="${requestScope.message!=null}">
                    <div id="van" style="width: 250px;height: 100px;position: absolute;border-radius: 10px;left:610px;top:10px;
            background-color: green;text-align: center;display: flex;align-items: center;justify-content: center;">
                        <strong>${message}</strong>
                    </div>
                </c:if>
                <script>
                    setTimeout(function (){
                        document.getElementById("van").style.display="none";
                    },3000);
                </script>
                <form action="/shopfuniture/login" method="POST">
                    <c:if test="${requestScope.notify!=null}">
                        <div style="color: red;font-size:20px;margin: 0;padding: 0;">${requestScope.notify}</div>
                    </c:if>
                    <c:if test="${requestScope.message==null&&param.error!=null&&requestScope.notify==null}">
                        <div style="color: red;font-size:20px;margin: 0;padding: 0;">invalid username and password</div>
                    </c:if>
                    <div class="form-row py-3 pt-5">
                        <div class="offset-1 col-lg-10">
                            <input type="text" name="username" class="inp px-3 f" placeholder="UserName"/>
                            <input style="display: none;" type="text" name="register" class="inp px-3 y" placeholder="register username">
                        </div>
                    </div>
                    <div class="form-row py-3">
                        <div class="offset-1 col-lg-10">
                            <input type="password" name="password"  class="inp px-3 " placeholder="PassWord"/>
                            <input style="display: none;margin-top: 30px;" type="password" name="password"  class="inp px-3 y" placeholder="Confirm PassWord"/>
                        </div>
                    </div>
                    <div class="form-row py-3">
                        <div class="offset-1 col-lg-10">
                            <%--                   <input type="checkbox" name="remember-me">keep login--%>
                            <button style="display: none;" class="btn1 y">Register</button>
                            <button class="btn1 f" type="submit">Login</button>
                        </div>
                    </div>

                    <p>Or Login With</p>
                    <span><a class="fab fa-google-plus" style="text-decoration: none;" href="/shopfuniture/user/email"></a></span>
                    <div class="form-row py-3 pt-4">
                        <div class="offset-1 col-lg-10">
                            <a style="padding: 15px 70px 15px 70px ;text-decoration: none;" class="btn1"  href="/shopfuniture/user/confirm" >New Account++</a>
                        </div>
                    </div>
                    <div  class="form-row py-3 pt-4">
                        <div style="display: flex;" class="offset-1 col-lg-10">
                            <a style="margin-left: 100px;" href="/shopfuniture/home-page" >back to home page</a>
                            <a style="margin-left: 100px;" href="/shopfuniture/user/reset-pass" >reset password</a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous">
</script>
<script>
    document.getElementById("submit").onclick=function (){
        if(document.getElementsByName("pass1")[0].value.trim()===""||
            document.getElementsByName("pass2")[0].value.trim()===""||
            document.getElementsByName("name")[0].value.trim()===""||
            document.getElementsByName("phone")[0].value.trim()===""||
            document.getElementsByName("address")[0].value.trim()===""||
            document.getElementsByName("employee")[0].value.trim()===""){
            document.getElementById("content").style.display="block";
            document.getElementById("content").innerHTML="fill up your information";
        }
        else if(document.getElementsByName("phone")[0].value.trim().length<10){
            document.getElementById("content").style.display="block";
            document.getElementById("content").innerHTML="phone format incorrect"; }
        else{
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                    if(this.responseText!=null||this.responseText.trim()!=""){
                        document.getElementById("content").style.display="block";
                        if(this.responseText.trim()==="successful"){
                            alert("an employee created ,wait for confirm by administrator");
                            document.getElementById("myModal").style.display="none";
                            document.getElementsByClassName("modal-backdrop")[0].style.display="none";
                        }
                        else{
                            document.getElementById("content").innerHTML= this.responseText;
                        }
                    }
                }
            };
            xhttp.open("get", "/shopfuniture/manager/register?pass1="+document.getElementsByName("pass1")[0].value.trim()+
                "&pass2="+document.getElementsByName("pass2")[0].value.trim()+"&employee="+document.getElementsByName("employee")[0].value.trim()
                +"&name="+document.getElementsByName("name")[0].value.trim()+"&phone="+document.getElementsByName("phone")[0].value.trim()
                +"&address="+document.getElementsByName("address")[0].value.trim(), true);
            xhttp.send();
        }
    }
</script>
</body>
</html>