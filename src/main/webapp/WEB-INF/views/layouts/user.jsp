<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html lang="en">


<!-- Head BEGIN -->
<head>
    <meta charset="utf-8">
    <title>Furnicom Shop FS</title>

    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <meta content="Metronic Shop UI description" name="description">
    <meta content="Metronic Shop UI keywords" name="keywords">
    <meta content="keenthemes" name="author">

    <meta property="og:site_name" content="-CUSTOMER VALUE-">
    <meta property="og:title" content="-CUSTOMER VALUE-">
    <meta property="og:description" content="-CUSTOMER VALUE-">
    <meta property="og:type" content="website">
    <meta property="og:image" content="-CUSTOMER VALUE-"><!-- link to image for socio -->
    <meta property="og:url" content="-CUSTOMER VALUE-">

    <link rel="shortcut icon" href="favicon.ico">

    <!-- Fonts START -->
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700|PT+Sans+Narrow|Source+Sans+Pro:200,300,400,600,700,900&amp;subset=all" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900&amp;subset=all" rel="stylesheet" type="text/css"><!--- fonts for slider on the index page -->
    <!-- Fonts END -->

    <!-- Global styles START -->
    <link href="<c:url value="/assets/user/plugins/font-awesome/css/font-awesome.min.css"/> " rel="stylesheet">
    <link href="<c:url value="/assets/user/plugins/bootstrap/css/bootstrap.min.css"/> " rel="stylesheet">
    <!-- Global styles END -->

    <!-- Page level plugin styles START -->
    <link href="<c:url value="/assets/user/pages/css/animate.css"/> " rel="stylesheet">
    <link href="<c:url value="/assets/user/plugins/fancybox/source/jquery.fancybox.css"/> " rel="stylesheet">
    <link href="<c:url value="/assets/user/plugins/owl.carousel/assets/owl.carousel.css"/>" rel="stylesheet">
    <!-- Page level plugin styles END -->

    <!-- Theme styles START -->
    <link href="<c:url value="/assets/user/pages/css/components.css"/>" rel="stylesheet">
    <link href="<c:url value="/assets/user/pages/css/slider.css"/>" rel="stylesheet">
    <link href="<c:url value="/assets/user/pages/css/style-shop.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/assets/user/corporate/css/style.css"/>" rel="stylesheet">
    <link href="<c:url value="/assets/user/corporate/css/style-responsive.css"/>" rel="stylesheet">
    <link href="<c:url value="/assets/user/corporate/css/themes/red.css"/>" rel="stylesheet" id="style-color">
    <link href="<c:url value="/assets/user/corporate/css/custom.css"/>" rel="stylesheet">
    <!-- Theme styles END -->
</head>
<!-- Head END -->

<!-- Body BEGIN -->
<body class="ecommerce">
<!-- BEGIN STYLE CUSTOMIZER -->
<div class="color-panel hidden-sm">
    <div class="color-mode-icons icon-color"></div>
    <div class="color-mode-icons icon-color-close"></div>
    <div class="color-mode">
        <p>THEME COLOR</p>
        <ul class="inline">
            <li class="color-red current color-default" data-style="red"></li>
            <li class="color-blue" data-style="blue"></li>
            <li class="color-green" data-style="green"></li>
            <li class="color-orange" data-style="orange"></li>
            <li class="color-gray" data-style="gray"></li>
            <li class="color-turquoise" data-style="turquoise"></li>
        </ul>
    </div>
</div>
<!-- END BEGIN STYLE CUSTOMIZER -->

<!-- BEGIN TOP BAR -->
<div class="pre-header">
    <div class="container">
        <div class="row">
            <!-- BEGIN TOP BAR LEFT PART -->
            <div class="col-md-6 col-sm-6 additional-shop-info">
                <ul class="list-unstyled list-inline">
                    <li><i class="fa fa-phone"></i><span>0866880230</span></li>
                    <!-- BEGIN CURRENCIES -->
                    <li class="shop-currencies">
                        <a href="javascript:void(0);">VND</a>
                        <a href="javascript:void(0);">£</a>
                        <a href="javascript:void(0);" class="current">$</a>
                    </li>
                    <!-- END CURRENCIES -->
                    <!-- BEGIN LANGS -->
                    <li class="langs-block">
                        <a href="javascript:void(0);" class="current">English </a>
                        <div class="langs-block-others-wrapper"><div class="langs-block-others">
                            <a href="javascript:void(0);">French</a>
                            <a href="javascript:void(0);">Germany</a>
                            <a href="javascript:void(0);">VietNamese</a>
                        </div></div>
                    </li>
                    <!-- END LANGS -->
                </ul>
            </div>
            <!-- END TOP BAR LEFT PART -->
            <!-- BEGIN TOP BAR MENU -->
            <div class="col-md-6 col-sm-6 additional-nav">
                <ul class="list-unstyled list-inline pull-right">
                    <li><a href="shop-account.html">My Account</a></li>
                    <li><a href="shop-wishlist.html">My Wishlist</a></li>
                    <li><a href="shop-checkout.html">Checkout</a></li>
                    <li><a href="page-login.html">Log In</a></li>
                </ul>
            </div>
            <!-- END TOP BAR MENU -->
            <!-- kết thúc phần TOP BAR MENU cứng-->
        </div>
    </div>
</div>
<!-- END TOP BAR -->
<%@include file="/WEB-INF/views/layouts/user/header.jsp"%>

<decorator:body/>
<!-- BEGIN BRANDS -->
<div class="brands">
    <div class="container">
        <div class="owl-carousel owl-carousel6-brands">
            <a href="shop-product-list.html"><img src="<c:url value="/assets/user/pages/img/brands/canon.jpg" />"alt="canon" title="canon"></a>
            <a href="shop-product-list.html"><img src="<c:url value="/assets/user/pages/img/brands/esprit.jpg"/>" alt="esprit" title="esprit"></a>
            <a href="shop-product-list.html"><img src="<c:url value="/assets/user/pages/img/brands/gap.jpg"/>" alt="gap" title="gap"></a>
            <a href="shop-product-list.html"><img src="<c:url value="/assets/user/pages/img/brands/next.jpg" />"alt="next" title="next"></a>
            <a href="shop-product-list.html"><img src="<c:url value="/assets/user/pages/img/brands/puma.jpg" />"alt="puma" title="puma"></a>
            <a href="shop-product-list.html"><img src="<c:url value="/assets/user/pages/img/brands/zara.jpg"/>" alt="zara" title="zara"></a>
            <a href="shop-product-list.html"><img src="<c:url value="/assets/user/pages/img/brands/canon.jpg"/>" alt="canon" title="canon"></a>
            <a href="shop-product-list.html"><img src="<c:url value="/assets/user/pages/img/brands/esprit.jpg"/>" alt="esprit" title="esprit"></a>
            <a href="shop-product-list.html"><img src="<c:url value="/assets/user/pages/img/brands/gap.jpg"/>" alt="gap" title="gap"></a>
            <a href="shop-product-list.html"><img src="<c:url value="/assets/user/pages/img/brands/next.jpg"/>" alt="next" title="next"></a>
            <a href="shop-product-list.html"><img src="<c:url value="/assets/user/pages/img/brands/puma.jpg" />"alt="puma" title="puma"></a>
            <a href="shop-product-list.html"><img src="<c:url value="/assets/user/pages/img/brands/zara.jpg"/>" alt="zara" title="zara"></a>
        </div>
    </div>
</div>
<!-- END BRANDS -->

<!-- BEGIN STEPS -->
<div class="steps-block steps-block-red">
    <div class="container">
        <div class="row">
            <div class="col-md-4 steps-block-col">
                <i class="fa fa-truck"></i>
                <div>
                    <h2>Free shipping</h2>
                    <em>Express delivery withing 3 days</em>
                </div>
                <span>&nbsp;</span>
            </div>
            <div class="col-md-4 steps-block-col">
                <i class="fa fa-gift"></i>
                <div>
                    <h2>Daily Gifts</h2>
                    <em>3 Gifts daily for lucky customers</em>
                </div>
                <span>&nbsp;</span>
            </div>
            <div class="col-md-4 steps-block-col">
                <i class="fa fa-phone"></i>
                <div>
                    <h2>477 505 8877</h2>
                    <em>24/7 customer care available</em>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- END STEPS -->
<%@include file="/WEB-INF/views/layouts/user/footer.jsp"%>


<!-- BEGIN fast view of a product -->
<div id="product-pop-up" style="display: none; width: 700px;">
    <div class="product-page product-pop-up">
        <div class="row">
            <div class="col-md-6 col-sm-6 col-xs-3">
                <div class="product-main-image">
                    <img src="<c:url value="/assets/user/pages/img/products/model7.jpg"/>" alt="Cool green dress with red bell" class="img-responsive">
                </div>
                <div class="product-other-images">
                    <a href="javascript:;" class="active"><img alt="Berry Lace Dress" src="<c:url value="/assets/user/pages/img/products/model3.jpg"/>"></a>
                    <a href="javascript:;"><img alt="Berry Lace Dress" src="<c:url value="/assets/user/pages/img/products/model4.jpg"/>"></a>
                    <a href="javascript:;"><img alt="Berry Lace Dress" src="<c:url value="/assets/user/pages/img/products/model5.jpg"/>"></a>
                </div>
            </div>
            <div class="col-md-6 col-sm-6 col-xs-9">
                <h2>Cool green dress with red bell</h2>
                <div class="price-availability-block clearfix">
                    <div class="price">
                        <strong><span>$</span>47.00</strong>
                        <em>$<span>62.00</span></em>
                    </div>
                    <div class="availability">
                        Availability: <strong>In Stock</strong>
                    </div>
                </div>
                <div class="description">
                    <p>Lorem ipsum dolor ut sit ame dolore  adipiscing elit, sed nonumy nibh sed euismod laoreet dolore magna aliquarm erat volutpat Nostrud duis molestie at dolore.</p>
                </div>
                <div class="product-page-options">
                    <div class="pull-left">
                        <label class="control-label">Size:</label>
                        <select class="form-control input-sm">
                            <option>L</option>
                            <option>M</option>
                            <option>XL</option>
                        </select>
                    </div>
                    <div class="pull-left">
                        <label class="control-label">Color:</label>
                        <select class="form-control input-sm">
                            <option>Red</option>
                            <option>Blue</option>
                            <option>Black</option>
                        </select>
                    </div>
                </div>
                <div class="product-page-cart">
                    <div class="product-quantity">
                        <input id="product-quantity" type="text" value="1" readonly name="product-quantity" class="form-control input-sm">
                    </div>
                    <button class="btn btn-primary" type="submit">Add to cart</button>
                    <a href="shop-item.html" class="btn btn-default">More details</a>
                </div>
            </div>

            <div class="sticker sticker-sale"></div>
        </div>
    </div>
</div>
<!-- END fast view of a product -->

<!-- Load javascripts at bottom, this will reduce page load time -->
<!-- BEGIN CORE PLUGINS (REQUIRED FOR ALL PAGES) -->
<!--[if lt IE 9]>
<script src="<c:url value="/assets/user/plugins/respond.min.js"/>"></script>
<![endif]-->
<script src="<c:url value="/assets/user/plugins/jquery.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/assets/user/plugins/jquery-migrate.min.js" />"type="text/javascript"></script>
<script src="<c:url value="/assets/user/plugins/bootstrap/js/bootstrap.min.js" />"type="text/javascript"></script>
<script src="<c:url value="/assets/user/corporate/scripts/back-to-top.js"/>" type="text/javascript"></script>
<script src="<c:url value="/assets/user/plugins/jquery-slimscroll/jquery.slimscroll.min.js"/>" type="text/javascript"></script>
<!-- END CORE PLUGINS -->

<!-- BEGIN PAGE LEVEL JAVASCRIPTS (REQUIRED ONLY FOR CURRENT PAGE) -->
<script src="<c:url value="/assets/user/plugins/fancybox/source/jquery.fancybox.pack.js"/>" type="text/javascript"></script><!-- pop up -->
<script src="<c:url value="/assets/user/plugins/owl.carousel/owl.carousel.min.js"/>" type="text/javascript"></script><!-- slider for products -->
<script src="<c:url value="/assets/user/plugins/zoom/jquery.zoom.min.js"/>" type="text/javascript"></script><!-- product zoom -->
<script src="<c:url value="/assets/user/plugins/bootstrap-touchspin/bootstrap.touchspin.js"/>" type="text/javascript"></script><!-- Quantity -->

<script src="<c:url value="/assets/user/corporate/scripts/layout.js"/>" type="text/javascript"></script>
<script src="<c:url value="/assets/user/pages/scripts/bs-carousel.js"/>" type="text/javascript"></script>
<script type="text/javascript">
    jQuery(document).ready(function() {
        Layout.init();
        Layout.initOWL();
        Layout.initImageZoom();
        Layout.initTouchspin();
        Layout.initTwitter();
    });
</script>
<!-- END PAGE LEVEL JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>