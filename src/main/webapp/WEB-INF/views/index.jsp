<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="zxx">

<head>

  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>aranoz</title>
  <link rel="icon" href="<c:url value="/assets/img/favicon.png"/> ">
  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="<c:url  value="/assets/css/bootstrap.min.css" />">
  <!-- animate CSS -->
  <link rel="stylesheet" href="<c:url  value="/assets/css/animate.css" />">
  <!-- owl carousel CSS -->
  <link rel="stylesheet" href="<c:url  value="/assets/css/owl.carousel.min.css" />">
  <!-- font awesome CSS -->
  <link rel="stylesheet" href="<c:url  value="/assets/css/all.css" />">
  <!-- flaticon CSS -->
  <link rel="stylesheet" href="<c:url  value="/assets/css/flaticon.css" />">
  <link rel="stylesheet" href="<c:url  value="/assets/css/themify-icons.css" />">
  <!-- font awesome CSS -->
  <link rel="stylesheet" href="<c:url  value="/assets/css/magnific-popup.css" />">
  <!-- swiper CSS -->
  <link rel="stylesheet" href="<c:url  value="/assets/css/slick.css" />">
  <!-- style CSS -->
  <link rel="stylesheet" href="<c:url  value="/assets/css/style.css" />">
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
                  <a class="dropdown-item" href="/shopfuniture/user/profile">My Account</a>
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
                <a class ="nav-link" href="/shopfuniture/manager/home"> Manager </a>
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
                <c:if test="${user!=null}">
                  ${user}
                </c:if>

              </a>
              <!--   Chỗ này thêm vào profile user dạng drop menu-->
              <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                <div class="single_product">
                 <a style="color: black;" href="/shopfuniture/user/profile">My Account</a>
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
<!-- Header part end-->

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
<!-- feature_part start-->
<section class="feature_part padding_top">
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-8">
        <div class="section_tittle text-center">
          <h2>Sản phẩm mới</h2>
        </div>
      </div>
    </div>
    <div class="row align-items-center justify-content-between">
      <c:forEach items="${productNew}" var="pn" varStatus="index">
      <c:if test="${index.first}">
      <div class="col-lg-6 col-sm-6 active">
        </c:if>
        <c:if test="${not index.first}">
        <div class="col-lg-6 col-sm-6">
          </c:if>
          <a href="/shopfuniture/single_product_item/${pn.pid}">
            <div class="single_feature_post_text" >
              <p>${pn.description}</p>
              <h3>${pn.p_name}</h3>
              <a href="#" class="feature_btn">EXPLORE NOW <i class="fas fa-play"></i></a>
              <img src="<c:url value="/assets/img/product/${pn.p_image}"/>"  alt="">
            </div>
          </a>
        </div>
        </c:forEach>
      </div>
</section>
<!-- upcoming_event part start-->

<!-- product_list start-->
<section class="product_list section_padding">
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-12">
        <div class="section_tittle text-center">
          <h2>awesome <span>shop</span></h2>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-lg-12">
        <div class="product_list_slider owl-carousel">
          <div class="single_product_list_slider">
            <div class="row align-items-center justify-content-between">
              <c:forEach items="${listPaging}" var="p" varStatus="index">
              <c:if test="${index.first}">
              <div class="col-lg-3 col-sm-3 active">
                </c:if>
                <c:if test="${not index.first}">
                <div class="col-lg-3 col-sm-3">
                  </c:if>
                  <a href="/shopfuniture/single_product_item/${p.pid}">
                    <div class="single_product_item" >
                      <img href="single-product.jsp" src="<c:url value="/assets/img/product/${p.p_image}"/>" alt="">
                      <div class="single_product_text">
                        <h4>${p.p_name}</h4>
                        <h3>$${p.price}</h3>
                        <a href="#" class="add_cart">+ add to cart<i class="ti-heart"></i></a>
                      </div>
                    </div>
                  </a>
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
                      <c:forEach begin="1" end="${endP}" var="i">
                        <li class="page-item">
                          <a class="page-link" href="/shopfuniture/home-page/${i}">
                            <i class="${tag==i?"active":""}"> ${i}</i>
                          </a>
                        </li>
                      </c:forEach>
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
    </div>
  </div>
</section>
<%--<!-- product_list part start-->--%>

<!-- awesome_shop start-->
<section class="our_offer section_padding">
  <div class="container">
    <div class="row align-items-center justify-content-between">
      <div class="col-lg-6 col-md-6">
        <div class="offer_img">
          <img src="<c:url value="/assets/img/offer_img.png"/>" alt="">
        </div>
      </div>
      <div class="col-lg-6 col-md-6">
        <div class="offer_text">
          <h2>Weekly Sale on
            60% Off All Products</h2>
          <div class="date_countdown">
            <div id="timer">
              <div id="hours" class="date"></div>
              <div id="minutes" class="date"></div>
              <div id="seconds" class="date"></div>
            </div>
          </div>
          <div class="input-group">
            <input type="text" class="form-control" placeholder="enter email address"
                   aria-label="Recipient's username" aria-describedby="basic-addon2">
            <div class="input-group-append">
              <a href="#" class="input-group-text btn_2" id="basic-addon1">book now</a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
<!-- awesome_shop part start-->

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
            <div class="single_product_item" >
              <a href="/shopfuniture/single_product_item/${pns.pid}">
                <img src="<c:url value="/assets/img/product/${pns.p_image}"/>" alt="">
                <div class="single_product_text">
                  <h4>${pns.p_name}</h4>
                  <h3>$${pns.price}</h3>
                </div>
              </a>
            </div>
          </c:forEach>
        </div>
      </div>
    </div>
  </div>
</section>
<!-- product_list part end-->

<!-- subscribe_area part start-->
<section class="subscribe_area section_padding">
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-7">
        <div class="subscribe_area_text text-center">
          <h5>Join Our Newsletter</h5>
          <h2>Subscribe to get Updated
            with new offers</h2>
          <div class="input-group">
            <input type="text" class="form-control" placeholder="enter email address"
                   aria-label="Recipient's username" aria-describedby="basic-addon2">
            <div class="input-group-append">
              <a href="#" class="input-group-text btn_2" id="basic-addon2">subscribe now</a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
<!--::subscribe_area part end::-->

<!-- subscribe_area part start-->
<section class="client_logo padding_top">
  <div class="container">
    <div class="row align-items-center">
      <div class="col-lg-12">
        <div class="single_client_logo">
          <img src="<c:url value="/assets/img/client_logo/client_logo_1.png"/>" alt="">
        </div>
        <div class="single_client_logo">
          <img src="<c:url value="/assets/img/client_logo/client_logo_2.png"/>" alt="">
        </div>
        <div class="single_client_logo">
          <img src="<c:url value="/assets/img/client_logo/client_logo_3.png"/>" alt="">
        </div>
        <div class="single_client_logo">
          <img src="<c:url value="/assets/img/client_logo/client_logo_4.png"/>" alt="">
        </div>
        <div class="single_client_logo">
          <img src="<c:url value="/assets/img/client_logo/client_logo_5.png"/>" alt="">
        </div>
        <div class="single_client_logo">
          <img src="<c:url value="/assets/img/client_logo/client_logo_3.png"/>" alt="">
        </div>
        <div class="single_client_logo">
          <img src="<c:url value="/assets/img/client_logo/client_logo_1.png"/>" alt="">
        </div>
        <div class="single_client_logo">
          <img src="<c:url value="/assets/img/client_logo/client_logo_2.png"/>" alt="">
        </div>
        <div class="single_client_logo">
          <img src="<c:url value="/assets/img/client_logo/client_logo_3.png"/>" alt="">
        </div>
        <div class="single_client_logo">
          <img src="<c:url value="/assets/img/client_logo/client_logo_4.png"/>" alt="">
        </div>
      </div>
    </div>
  </div>
</section>
<!--::subscribe_area part end::-->
<footer class="footer_part">
  <div class="container">
    <div class="row justify-content-around">
      <div class="col-sm-6 col-lg-2">
        <div class="single_footer_part">
          <h4>Top Products</h4>
          <ul class="list-unstyled">
            <li><a href="">Managed Website</a></li>
            <li><a href="">Manage Reputation</a></li>
            <li><a href="">Power Tools</a></li>
            <li><a href="">Marketing Service</a></li>
          </ul>
        </div>
      </div>
      <div class="col-sm-6 col-lg-2">
        <div class="single_footer_part">
          <h4>Quick Links</h4>
          <ul class="list-unstyled">
            <li><a href="">Jobs</a></li>
            <li><a href="">Brand Assets</a></li>
            <li><a href="">Investor Relations</a></li>
            <li><a href="">Terms of Service</a></li>
          </ul>
        </div>
      </div>
      <div class="col-sm-6 col-lg-2">
        <div class="single_footer_part">
          <h4>Features</h4>
          <ul class="list-unstyled">
            <li><a href="">Jobs</a></li>
            <li><a href="">Brand Assets</a></li>
            <li><a href="">Investor Relations</a></li>
            <li><a href="">Terms of Service</a></li>
          </ul>
        </div>
      </div>
      <div class="col-sm-6 col-lg-2">
        <div class="single_footer_part">
          <h4>Resources</h4>
          <ul class="list-unstyled">
            <li><a href="">Guides</a></li>
            <li><a href="">Research</a></li>
            <li><a href="">Experts</a></li>
            <li><a href="">Agencies</a></li>
          </ul>
        </div>
      </div>
      <div class="col-sm-6 col-lg-4">
        <div class="single_footer_part">
          <h4>Newsletter</h4>
          <p>Heaven fruitful doesn't over lesser in days. Appear creeping
          </p>
          <div id="mc_embed_signup">
            <form target="_blank"
                  action="https://spondonit.us12.list-manage.com/subscribe/post?u=1462626880ade1ac87bd9c93a&amp;id=92a4423d01"
                  method="get" class="subscribe_form relative mail_part">
              <input type="email" name="email" id="newsletter-form-email" placeholder="Email Address"
                     class="placeholder hide-on-focus" onfocus="this.placeholder = ''"
                     onblur="this.placeholder = ' Email Address '">
              <button type="submit" name="submit" id="newsletter-submit"
                      class="email_icon newsletter-submit button-contactForm">subscribe</button>
              <div class="mt-10 info"></div>
            </form>
          </div>
        </div>
      </div>
    </div>

  </div>
  <div class="copyright_part">
    <div class="container">
      <div class="row">
        <div class="col-lg-8">
          <div class="copyright_text">
            <P><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
              Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="ti-heart" aria-hidden="true"></i> by <a href="https://facebook.com/kieuquang26" target="_blank">KwAng</a>
              <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></P>
          </div>
        </div>
        <div class="col-lg-4">
          <div class="footer_icon social_icon">
            <ul class="list-unstyled">
              <li><a href="#" class="single_social_icon"><i class="fab fa-facebook-f"></i></a></li>
              <li><a href="#" class="single_social_icon"><i class="fab fa-twitter"></i></a></li>
              <li><a href="#" class="single_social_icon"><i class="fas fa-globe"></i></a></li>
              <li><a href="#" class="single_social_icon"><i class="fab fa-behance"></i></a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</footer>
<!-- jquery plugins here-->
<script src="<c:url  value="/assets/js/jquery-1.12.1.min.js" />"></script>
<!-- popper js -->
<script src="<c:url  value="/assets/js/popper.min.js" />"></script>
<!-- bootstrap js -->
<script src="<c:url  value="/assets/js/bootstrap.min.js" />"></script>
<!-- easing js -->
<script src="<c:url  value="/assets/js/jquery.magnific-popup.js" />"></script>
<!-- swiper js -->
<script src="<c:url  value="/assets/js/swiper.min.js" />"></script>
<!-- swiper js -->
<script src="<c:url  value="/assets/js/masonry.pkgd.js" />"></script>
<!-- particles js -->
<script src="<c:url  value="/assets/js/owl.carousel.min.js" />"></script>
<script src="<c:url  value="/assets/js/jquery.nice-select.min.js" />"></script>
<!-- slick js -->
<script src="<c:url  value="/assets/js/slick.min.js" />"></script>
<script src="<c:url  value="/assets/js/jquery.counterup.min.js" />"></script>
<script src="<c:url  value="/assets/js/waypoints.min.js" />"></script>
<script src="<c:url  value="/assets/js/contact.js" />"></script>
<script src="<c:url  value="/assets/js/jquery.ajaxchimp.min.js" />"></script>
<script src="<c:url  value="/assets/js/jquery.form.js" />"></script>
<script src="<c:url  value="/assets/js/jquery.validate.min.js" />"></script>
<script src="<c:url  value="/assets/js/mail-script.js" />"></script>
<script src="<c:url  value="/assets/js/stellar.js" />"></script>
<script src="<c:url  value="/assets/js/RangePrice.js" />"></script>
<!-- custom js -->
<script src="<c:url  value="/assets/js/custom.js" />"></script>
</body>

</html>