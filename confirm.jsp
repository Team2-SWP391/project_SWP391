<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Animated Login Form</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link href="https://fonts.gstatic,com" rel="preconnect"/>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2/family=Pacifico&display=swap"/>
    <script src="https://kit.fontawesome.com/c26cd2166c.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
</head>
<style>
    html,body{
        overflow: hidden;
        height: 99vh;
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
<section style="height: 100vh;" class="login py-5 bg-warning">
    <div style="margin-top: 70px;" class="container">
        <div class="row g-0 bg-success">
            <div class="col-lg-6">
                <img style="width: 100%;height: 100%;" class="img-fluid" alt="" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSUo_fRjgC-XkBBkTa7Ih7KMfuY0_Z948P3Ag&usqp=CAU">
            </div>
            <div class="col-lg-6 text-center py-5">
                <h2 class="animate__animated animate__heartBeat animate__infinite">Confirm email</h2>
                <h4 class="animate__animated animate__heartBeat animate__infinite">Done to continue--></h4>

                <form action="/shopfuniture/confirm" method="POST">
                   <span style="color: red;" class="pup">${message}</span>
                    <div class="form-row py-3 pt-5">
                        <div class="offset-1 col-lg-10">
                            <input type="text" name="email" class="inp px-3 y" placeholder="enter email">
                        </div>
                    </div>
                    <div class="form-row py-3" >
                        <div class="offset-1 col-lg-10">
                            <button class="btn1" id="register">Continue---></button>
                        </div>
                    </div>
                    <p>Or Login With</p>
                    <span><a class="fab fa-google-plus" style="text-decoration: none;" href="/shopfuniture/email"></a></span>
                    <div class="form-row py-3 pt-4">
                        <div class="offset-1 col-lg-10">
                            <a style="padding: 15px 115px 15px 115px ;text-decoration: none;" class="btn1" id="btn1" href="/shopfuniture/login" >Login</a>
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