<%@ page import="org.springframework.security.core.Authentication" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>

    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Future</title>

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

<body>
<header class="main_menu home_menu">
    <div class="container">
        <div class="row align-items-center">
            <div class="col-lg-12">
                <nav class="navbar navbar-expand-lg navbar-light">
                    <a class="navbar-brand" href="index.html"> <img src="<c:url value="/assets/img/logo.png"/>" alt="logo"> </a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse"
                            data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                            aria-expanded="false" aria-label="Toggle navigation">
                        <span class="menu_icon"><i class="fas fa-bars"></i></span>
                    </button>

                    <div class="collapse navbar-collapse main-menu-item" id="navbarSupportedContent">
                        <ul class="navbar-nav">
                            <li class="nav-item">
                                <a class="nav-link" href="/shopfuniture/home-page">Home</a>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="blog.html" id="navbarDropdown_1"
                                   role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    Shop
                                </a>
                                <div class="dropdown-menu" aria-labelledby="navbarDropdown_1">
                                    <a class="dropdown-item" href="/shopfuniture/category"> shop category</a>
                                    <a class="dropdown-item" href="single-product.html">product details</a>

                                </div>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="blog.html" id="navbarDropdown_3"
                                   role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    pages
                                </a>
                                <div class="dropdown-menu" aria-labelledby="navbarDropdown_2">
                                    <c:if test="${requestScope.user!=null}">
                                        <a class="dropdown-item" href="/shopfuniture/logout"> logout</a>
                                    </c:if>
                                    <c:if test="${requestScope.user==null}">
                                        <a class="dropdown-item" href="/shopfuniture/login"> login</a>
                                    </c:if>
                                    <a class="dropdown-item" href="tracking.html">tracking</a>
                                    <a class="dropdown-item" href="checkout.html">product checkout</a>
                                    <a class="dropdown-item" href="cart.html">shopping cart</a>
                                    <a class="dropdown-item" href="confirmation.html">confirmation</a>
                                    <a class="dropdown-item" href="elements.html">elements</a>
                                </div>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="blog.html" id="navbarDropdown_2"
                                   role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    blog
                                </a>
                                <div class="dropdown-menu" aria-labelledby="navbarDropdown_2">
                                    <a class="dropdown-item" href="blog.html"> blog</a>
                                    <a class="dropdown-item" href="single-blog.html">Single blog</a>
                                </div>
                            </li>

                            <li class="nav-item">
                                <a class="nav-link" href="contact.html">Contact</a>
                            </li>
                            <li class="nav-item">
                                <a class ="nav-link" href="/shopfuniture/home-page"> Manager </a>
                            </li>
                        </ul>
                    </div>
                    <div class="hearer_icon d-flex">
                        <a id="search_1" href="javascript:void(0)"><i class="ti-search"></i></a>
                        <a href=""><i class="ti-heart"></i></a>
                        <div class="dropdown cart">
                            <a class="dropdown-toggle" href="#" id="navbarDropdown3" role="button"
                               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-cart-plus"></i>
                            </a>
                            <!--   Chỗ này thêm sản phẩm vào giỏ hàng dạng drop menu-->
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <div class="single_product">

                                </div>
                            </div>
                            <!--   Chỗ này của profile user-->
                        </div>
                        <div class="dropdown user">
                            <a class="dropdown-toggle" href="#" id="navbarDropdown4" role="button"
                               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fa fa-user-circle"></i>

                            </a>
                            <!--   Chỗ này thêm vào profile user dạng drop menu-->
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <div class="single_product">

                                </div>
                            </div>
                        </div>
                    </div>
                </nav>
            </div>
        </div>
    </div>
    <div class="search_input" id="search_input_box">
        <div class="container ">
            <form class="d-flex justify-content-between search-inner">
                <input type="text" class="form-control" id="search_input" placeholder="Search Here">
                <button type="submit" class="btn"></button>
                <span class="ti-close" id="close_search" title="Close Search"></span>
            </form>
        </div>
    </div>
</header>
<!--================Home Banner Area =================-->
<!-- banner part start-->
<section class="banner_part">
    <div class="container">
        <div class="row align-items-center">
            <div class="col-lg-12">
                <div class="banner_slider owl-carousel">
                    <c:forEach var="l" items="${list}">
                        <div class="single_banner_slider">
                            <div class="row">
                                <div class="col-lg-5 col-md-8">
                                    <div class="banner_text">
                                        <div class="banner_text_iner">
                                            <h1>${l.caption}</h1>
                                            <p>${l.content}</p>
                                            <a href="#" class="btn_2">buy now</a>
                                        </div>
                                    </div>
                                </div>
                                <div class="banner_img d-none d-lg-block">
                                    <img src="<c:url value="${l.img}"/>" alt="">
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <div class="slider-counter"></div>
            </div>
        </div>
    </div>
</section>
<!-- banner part start-->

<!--================Category Product Area =================-->
<section class="cat_product_area section_padding">
    <div class="container">
        <div class="row">
            <div class="col-lg-3">
                <div class="left_sidebar_area">
                    <aside class="left_widgets p_filter_widgets">

                        <div class="l_w_title">
                            <h3>Browse Categories</h3>
                        </div>

                        <div class="widgets_inner">
                            <ul class="list">
                                <c:forEach items="${category}" var="c">
                                    <li>
                                        <a href="<c:url value="${c.cid}"/>">${c.c_name}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </aside>

                    <aside class="left_widgets p_filter_widgets price_rangs_aside">
                        <div class="l_w_title">
                            <h3>Price Filter</h3>
                        </div>
                        <div class="widgets_inner">
                            <div class="range_item">
                                <!-- <div id="slider-range"></div> -->
                                <input type="text" class="js-range-slider" value="" />
                                <div class="d-flex">
                                    <div class="price_text">
                                        <p>Price :</p>
                                    </div>
                                    <div class="price_value d-flex justify-content-center">
                                        <%--                                        //amount => amount1 error duplicate--%>
                                        <input type="text" class="js-input-from" id="amount" readonly />
                                        <span>to</span>
                                        <input type="text" class="js-input-to" id="amount" readonly />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </aside>
                </div>
            </div>
            <div class="col-lg-9">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="product_top_bar d-flex justify-content-between align-items-center">
                            <div class="single_product_menu">
                                <p><span>(${CateIdProduct.size()})</span> Product Found</p>
                            </div>
                            <div class="single_product_menu d-flex">
                                <h5>short by : </h5>
                                <select >
                                    <option data-display="Select">name</option>
                                    <option value="1">price</option>
                                    <option value="2">product</option>
                                </select>
                            </div>
                            <div class="single_product_menu d-flex">
                                <h5>show :</h5>
                                <div class="top_pageniation">
                                    <ul>
                                        <li>1</li>
                                        <li>2</li>
                                        <li>3</li>
                                    </ul>
                                </div>
                            </div>
                            <div class="single_product_menu d-flex">
                                <div class="input-group">
                                    <input type="text" class="form-control" placeholder="search"
                                           aria-describedby="inputGroupPrepend">
                                    <div class="input-group-prepend">
                                            <span class="input-group-text" id="inputGroupPrepend"><i
                                                    class="ti-search"></i></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row align-items-center latest_product_inner">
                    <c:forEach items="${CateIdProduct}" var="c" varStatus="index">
                    <c:if test="${index.first}">
                    <div class="col-lg-4 col-sm-4 active">
                        </c:if>
                        <c:if test="${not index.first}">
                        <div class="col-lg-4 col-sm-4">
                            </c:if>
                            <div class="single_product_item">
                                <a href="/shopfuniture/single_product_item/${c.pid}">
                                    <img src="<c:url value="/assets/img/product/${c.p_image}"/>" alt="">
                                    <div class="single_product_text">
                                        <h4>${c.p_name}</h4>
                                        <h3>$${c.price}</h3>
                                        <a href="#" class="add_cart">+ add to cart<i class="ti-heart"></i></a>
                                    </div>
                                </a>
                            </div>
                        </div>
                        </c:forEach>
                    </div>
                    <div class="col-lg-12">
                        <div class="pageination">
                            <nav aria-label="Page navigation example">
                                <ul class="pagination justify-content-center">
                                    <li class="page-item">
                                        <a class="page-link" href="#" aria-label="Previous">
                                            <i class="ti-angle-double-left"></i>
                                        </a>
                                    </li>
                                    <li class="page-item"><a class="page-link" href="#">1</a></li>
                                    <li class="page-item"><a class="page-link" href="#">2</a></li>
                                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                                    <li class="page-item"><a class="page-link" href="#">4</a></li>
                                    <li class="page-item"><a class="page-link" href="#">5</a></li>
                                    <li class="page-item"><a class="page-link" href="#">6</a></li>
                                    <li class="page-item">
                                        <a class="page-link" href="#" aria-label="Next">
                                            <i class="ti-angle-double-right"></i>
                                        </a>
                                    </li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!--================End Category Product Area =================-->

<!-- product_list part start-->
<section class="product_list best_seller section_padding">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-lg-12">
                <div class="section_tittle text-center">
                    <h2>Best Sellers <span>shop</span></h2>
                </div>
            </div>
        </div>
        <div class="row align-items-center justify-content-between">
            <div class="col-lg-12">
                <div class="best_product_slider owl-carousel">
                    <c:forEach items="${productNew}" var="pns">
                        <div class="single_product_item">
                            <a href="/shopfuniture/single_product_item/${pns.pid}">
                                <div class="single_product_text">
                                    <h4>${pns.p_name}</h4>
                                    <h3>$${pns.price}</h3>
                                </div>
                            <img src="<c:url value="/assets/img/product/${pns.p_image}"/>" alt="">
                            </a>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- product_list part end-->

<!-- jquery plugins here-->
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