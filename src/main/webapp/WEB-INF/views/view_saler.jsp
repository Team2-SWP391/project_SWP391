<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title> Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
    <link href="/view/css/styles.css" rel="stylesheet" />
    <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous">
    </script>
</head>
<style>
    table button{
        border: 1px solid rgba(85,94,100,0.59);
        margin-left: 7%;
    }
    table button:hover{
        background-color: rgba(246,148,102,0.59);
    }
    #pup{
        display: none;
        overflow: hidden;
    }
    .close:hover{
        background-color: rgba(155,42,28,0.59);
    }
    .table1 input{
        height: 40px;
        width: 300px;
        margin-bottom: 40px;
        margin-left: 15px;
    }
    .table1 p{
        margin-bottom: 40px;
    }
    .fl p{
        padding: 0;
        margin: 0px;
    }
    .fl{
      border-bottom: 1px solid rgba(96,108,60,0.59);
        background-color: rgba(255,235,107,0.59);
    }
    #noti{
        display: none;
    }
</style>
<body class="sb-nav-fixed">
<div style="width: 300px;height: 270px;background-color: white;border: 1px solid rgba(39,108,66,0.59);
position: fixed;top:7%;right: 1%;z-index: 2;overflow-y: scroll;" id="noti">
    <c:if test="${feed==null}">
        <p style="text-align: center;margin-top: 50px;">no has any notification</p>
    </c:if>
    <c:forEach items="${feed}" var="fe">
        <c:if test="${fe.status==false}">
            <div style="background-color: rgba(255,113,79,0.59);" class="fl">
                <p>from ${fe.fname}</p>
                <p>date:${fe.datetime}</p>
                <p>* ${fe.content}</p>
            </div>
        </c:if>
        <c:if test="${fe.status==true}">
    <div class="fl">
        <p>from ${fe.fname}</p>
        <p>date:${fe.datetime}</p>
        <p>* ${fe.content}</p>
    </div>
     </c:if>
    </c:forEach>
</div>
<div style="position: fixed;top: 2%;left: 1%;width: 300px;height: 200px;z-index: 1;" id="update"></div>
<div class="modal" id="myModal">
    <div class="modal-dialog">
        <div style="height: 720px;" class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Create Account for Employee</h4>
                <div class="btn-close" data-bs-dismiss="modal"></div>
            </div>
            <!-- Modal body -->
            <div class="modal-body">
                <table class="table1">
                    <tr>
                        <td><p>Your username</p></td>
                        <td><input type="text" name="employee"/></td>
                    </tr>
                    <tr>
                        <td><p>Your password</p></td>
                        <td><input type="password" name="pass1"/></td>
                    </tr>
                    <tr>
                        <td><p>Confirm password</p></td>
                        <td><input type="password" name="pass2"/></td>
                    </tr>
                    <tr>
                        <td><p>Enter full name</p></td>
                        <td><input type="text" name="name"/></td>
                    </tr>
                    <tr>
                        <td><p>Enter your phone</p></td>
                        <td><input type="text" name="phone"/></td>
                    </tr>
                    <tr>
                        <td><p>Enter your address</p></td>
                        <td><input type="text" name="address" placeholder="xã/phường,huyện/quận,tỉnh/thành phố"/></td>
                    </tr>
                </table>

                <p style="margin-bottom: 0;padding-bottom: 0;border-top: 1px solid #cccccc;">
                    Register for employee account is serious for shop so it's need time
                    to take confirm from administrator.</p>
            </div>
            <!-- Modal footer -->
            <div class="modal-footer">
                <p style="width: 60%;color: red;" id="content"></p>
                <div id="submit" style="background-color: #9999ff;border:none;padding: 8px 10px 8px 10px;border-radius: 5px;cursor: pointer;" >submit</div>
                <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
            </div>

        </div>
    </div>
</div>
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
                            location.reload();
                        }
                        else{
                            document.getElementById("content").innerHTML= this.responseText;
                        }
                    }
                }
            };
            xhttp.open("get", "/shopfuniture/admin/register?pass1="+document.getElementsByName("pass1")[0].value.trim()+
                "&pass2="+document.getElementsByName("pass2")[0].value.trim()+"&employee="+document.getElementsByName("employee")[0].value.trim()
                +"&name="+document.getElementsByName("name")[0].value.trim()+"&phone="+document.getElementsByName("phone")[0].value.trim()
                +"&address="+document.getElementsByName("address")[0].value.trim(), true);
            xhttp.send();
        }
    }
</script>
<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
    <!-- Navbar Brand-->
    <a class="navbar-brand ps-3" href="#">Management Account</a>
    <!-- Sidebar Toggle-->
    <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
    <!-- Navbar Search-->
    <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
        <div class="input-group">
            <input class="form-control" type="text" placeholder="Search for..." aria-label="Search for..." aria-describedby="btnNavbarSearch" />
            <button class="btn btn-primary" id="btnNavbarSearch" type="button"><i class="fas fa-search"></i></button>
        </div>
    </form>
    <!-- Navbar-->
    <ul style="position: relative;" class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
        <c:if test="${news!=null}">
        <div style="position: absolute;color: rgba(255,226,2,0.59);" id="number">${news.size()}</div>
        </c:if>
        <li class="nav-item dropdown" id="drop">
            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
        </li>
    </ul>
</nav>
<div id="layoutSidenav">
    <div id="layoutSidenav_nav">
        <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
            <div class="sb-sidenav-menu">
                <div class="nav">
                    <div class="sb-sidenav-menu-heading">Core</div>
                    <a class="nav-link" href="#">
                        <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                        Dashboard
                    </a>
                    <div class="sb-sidenav-menu-heading">Interface</div>
                    <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages" aria-expanded="false" aria-controls="collapsePages">
                        <div class="sb-nav-link-icon"><i class="fas fa-book-open"></i></div>
                        Pages
                        <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                    </a>
                    <div class="collapse" id="collapsePages" aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
                        <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#pagesCollapseAuth" aria-expanded="false" aria-controls="pagesCollapseAuth">
                                Authentication
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="pagesCollapseAuth" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordionPages">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href="/shopfuniture/logout">logout</a>
                                    <a class="nav-link" href="password.html">Change Password</a>
                                </nav>
                            </div>
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#pagesCollapseError" aria-expanded="false" aria-controls="pagesCollapseError">
                                Error
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="pagesCollapseError" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordionPages">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href="#">401 Page</a>
                                    <a class="nav-link" href="#">404 Page</a>
                                    <a class="nav-link" href="#">500 Page</a>
                                </nav>
                            </div>
                        </nav>
                    </div>
                </div>
            </div>
            <div class="sb-sidenav-footer">
                <div class="small">Logged in as:</div>
                Admin
            </div>
        </nav>
    </div>
    <div id="layoutSidenav_content">
        <main>
            <div class="container-fluid px-4">
                <h1> </h1>
                <ol class="breadcrumb mb-4">
                    <li><button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myModal">
                        Add Employee
                    </button></li>
                </ol>
                <div class="card mb-4">
                    <div class="card-body">
                       password need to careful use on internet. For more information to decode password then let visit page
                        <a target="_blank" href="https://bcrypt-generator.com/">decode password hashmac256</a>

                    </div>
                </div>
                <div class="card mb-4">
                    <div class="card-header">
                        <i class="fas fa-table me-1"></i>
                        management saler
                    </div>
                    <div class="card-body">
                        <table id="datatablesSimple">
                            <thead>
                            <tr>
                                <th>Name</th>
                                <th>Address</th>
                                <th>Phone Number</th>
                            </tr>
                            </thead>
                            <tfoot>
                            <tr>
                                <th>Name</th>
                                <th>Address</th>
                                <th>Phone Number</th>
                                <th></th>

                            </tr>
                            </tfoot>
                            <tbody>
                           <c:if test="${requestScope.list!=null}">
                               <c:forEach items="${list}" var="saler">
                                   <tr id="${saler.aid}">
                                       <td>${saler.full_name}</td>
                                       <td>${saler.address}</td>
                                       <td>${saler.phone}</td>
                                       <td style="text-align: center;">
                                           <button onclick="my(${saler.aid})">delete</button>
                                      <c:if test="${saler.active==true}"><button id="${saler.aid}-h" onclick="ma(${saler.active},${saler.aid})" style="background-color: rgba(81,213,255,0.55);width: 62px">Inactive</button></c:if>
                                           <c:if test="${saler.active==false}"><button id="${saler.aid}-h" onclick="ma(${saler.active},${saler.aid})" style="background-color: rgba(81,213,255,0.55);width: 62px;">Active</button></c:if>
                                           <button onclick="run('${saler.user}','${saler.password}','${saler.full_name}')">
                                              View Account
                                           </button>
                                           <button onclick="mes(${saler.id},'${saler.full_name}')">Message</button>
                                       </td>
                                   </tr>
                               </c:forEach>
                           </c:if>
                           <c:if test="${requestScope.list==null}"><p>No Employee in list</p></c:if>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <script>
                document.getElementById("drop").onclick=function (){
                    document.getElementById("noti").style.display="block";
                    // var xml=new XMLHttpRequest();
                    // xml.onreadystatechange = function() {
                    //     if (this.readyState == 4 && this.status == 200) {
                    //         if(this.responseText!=null||this.responseText.trim()!=""){
                    //             if(this.responseText=="true")
                    //              document.getElementById("number").style.display="none";
                    //         }
                    //     }
                    //
                    // };
                    // xml.open("get","/shopfuniture/admin/update-feedback",true);
                    // xml.send();
                }
                document.getElementById("layoutSidenav").onmouseenter=function (){
                    document.getElementById("noti").style.display="none";
                }
                function mes(e,s){
                    var con=prompt("send to "+s+":","");
                     if(!(con==null||con.trim()=="")){
                     var xml=new XMLHttpRequest();
                         xml.onreadystatechange = function() {
                         if (this.readyState == 4 && this.status == 200) {
                             if(this.responseText!=null||this.responseText.trim()!=""){
                                 if(this.responseText=="false")
                                    alert("can't send message now");
                                 else if(this.responseText=="true"){
                                     alert("message was sent to "+s);
                                 }
                             }
                         }

                      };
                         xml.open("post","/shopfuniture/admin/feed-back?salerid="+e+"&content="+con,true);
                         xml.send();
                     }
                     else{
                      alert("Destroy message");
                     }

                }
                function run(e,e1,e2){
                    document.getElementById("pup").style.display="block";
                    document.getElementById("name").innerHTML="Account of "+e2;
                    document.getElementById("user").innerHTML="Username: "+e;
                    document.getElementById("pass").value=e1;
                }
                function ma(e,v){
                        var xhttp = new XMLHttpRequest();
                        xhttp.onreadystatechange = function() {
                            if (this.readyState == 4 && this.status == 200) {
                                if(this.responseText!=null||this.responseText.trim()!=""){
                                    if(this.responseText=="done"){
                                        if(e==true){
                                        alert("Inactive successful");
                                            document.getElementById(v+"-h").innerHTML="Active";
                                            location.reload();
                                        }
                                        else if(e==false){
                                            alert("Active successful");
                                            document.getElementById(v+"-h").innerHTML="Inactive";
                                            location.reload();
                                        }
                                    }else if(this.responseText!="done"){
                                        if(e==true){
                                            alert("Inactive failed");}
                                      else  if(e==false){
                                            alert("Active failed");}
                                    }
                                }
                            }
                        };
                        if(e==true)
                        xhttp.open("POST", "/shopfuniture/admin/change_active/"+v+"-1", true);
                        else if(e==false)
                            xhttp.open("POST", "/shopfuniture/admin/change_active/"+v+"-0", true);
                        xhttp.send();
                }
                function my(e){
                    var con=confirm("are you sure want to delete ?").valueOf();
                    if(con){
                        var xhttp = new XMLHttpRequest();
                        xhttp.onreadystatechange = function() {
                            if (this.readyState == 4 && this.status == 200) {
                                if(this.responseText!=null||this.responseText.trim()!=""){
                                   if(this.responseText=="done"){
                                       alert("delete successful");
                                       let child=document.getElementById(e);
                                       document.getElementsByTagName("tbody")[1].removeChild(child);
                                   }else if(this.responseText=="fail"){
                                       alert("delete failed");
                                   }
                                }
                            }
                        };
                        xhttp.open("POST", "/shopfuniture/admin/delete/"+e, true);
                        xhttp.send();
                    }
                }
            </script>

        </main>
        <footer class="py-4 bg-light mt-auto">
            <div class="container-fluid px-4">
                <div class="d-flex align-items-center justify-content-between small">
                    <div class="text-muted">Copyright &copy; Your Website 2022</div>
                    <div>
                        <a href="#">Privacy Policy</a>
                        &middot;
                        <a href="#">Terms &amp; Conditions</a>
                    </div>
                </div>
            </div>
        </footer>
    </div>
</div>
<div id="pup" style="position:absolute;top:0;left:0;height: 100vh;width: 100vw;background-color: rgba(136, 141, 154, 0.6);overflow: hidden;z-index: 1; ">
<div  style="height: 200px;width: 400px;background-color: white;position: absolute;top:100px;left: 600px;">
<button style="margin-left: 352px;border: none;" name="close" class="close">close</button>
    <p style="margin-left: 40px;margin-top: 20px;border-bottom: 1px solid rgba(147,155,149,0.59);" id="name"></p>
    <p style="margin-left: 40px;margin-top: 20px;" id="user"></p>
    <p style="margin-left: 40px;margin-top: 20px;">Password:<input style="border: none;" type="text"  id="pass"/></p>

</div>
</div>
<script>
    document.getElementsByName("close")[0].onclick=function (){
        document.getElementById("pup").style.display="none";
    }
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="/view/js/scripts.js"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
<script src="/view/js/datatables-simple-demo.js"></script>
</body>
</html>
