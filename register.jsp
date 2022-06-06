<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Animated Login Form</title>
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
</style>
<body>
<section class="login py-5 bg-warning">
    <div class="container">
        <div class="row g-0 bg-success">
            <div class="col-lg-6">
                <img style="width: 100%;height: 100%;" class="img-fluid" alt="" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSUo_fRjgC-XkBBkTa7Ih7KMfuY0_Z948P3Ag&usqp=CAU">
            </div>
            <div class="col-lg-6 text-center py-5">
                <h1 class="animate__animated animate__heartBeat animate__infinite">Well Come</h1>
                <h4 class="animate__animated animate__heartBeat animate__infinite">furniture shop</h4>

                <form action="/shopfuniture/register" method="POST">
                    <c:if test="${sessionScope.message!=null}"><div class="alert alert-sucess text-center">${sessionScope.message}</div></c:if>
                    <c:if test="${param.error!=null}"><div style="color: red;font-size:20px;margin: 0;padding: 0;">invalid username and password</div></c:if>
                    <div class="form-row py-3 pt-5">
                        <div class="offset-1 col-lg-10">
                            <input  type="text" name="register" class="inp px-3 y" placeholder="register username">
                        </div>
                    </div>
                    <div class="form-row py-3">
                        <div class="offset-1 col-lg-10">
                            <input type="password" name="password"  class="inp px-3 " placeholder="PassWord"/>
                            <input style="margin-top: 30px;" type="password" name="confirmpassword"  class="inp px-3 y" placeholder="Confirm PassWord"/>
                        </div>
                    </div>
                    <div class="form-row py-3">
                        <div class="offset-1 col-lg-10">
                            <%--                   <input type="checkbox" name="remember-me">keep login--%>
                            <button class="btn1 y">Register</button>

                        </div>
                    </div>

                    <p>Or Login With</p>
                    <span><a class="fab fa-google-plus" style="text-decoration: none;" href="/shopfuniture/email"></a></span>
                    <div class="form-row py-3 pt-4">
                        <div class="offset-1 col-lg-10">
                            <a style="padding: 15px 70px 15px 70px ;text-decoration: none;" class="btn1" id="btn1" href="/shopfuniture/login" >login</a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>