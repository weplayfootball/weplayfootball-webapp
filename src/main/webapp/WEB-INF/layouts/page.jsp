<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page session="false" %>
<!DOCTYPE html>
<!--[if IE 7]> <html lang="en" class="ie7"> <![endif]-->  
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->  
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->  
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->  
<head>
    <title>WePlayFootball</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="stylesheet" href="<c:url value="/resources/assets/bootstrap/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/assets/css/style.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/assets/css/menu/style-menu.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/assets/bootstrap/css/bootstrap-responsive.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/assets/css/style_responsive.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/assets/css/animate.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/assets/css/effects.css"/>">		
    <link rel="stylesheet" href="<c:url value="/resources/assets/fancybox/jquery.fancybox.css"/>">	
    <link rel="stylesheet" href="<c:url value="/resources/assets/flexslider/css/flexslider.css"/>" type="text/css" media="screen" />
    <link rel="stylesheet" href="<c:url value="/resources/assets/parallax-slider/css/parallax-slider.css"/>" type="text/css" />
    <link rel="stylesheet" href="<c:url value="/resources/assets/font-awesome/css/font-awesome.css"/>">
</head>	

<body>

<!--=== Top ===-->    
<div id="top">
    <div class="container">         
        <ul id="loginbar" class="pull-right">
        	<c:if test="${not empty member}">
        		<li>Welcome, <c:out value="${member.username}"/>!</li>
				<li class="devider">&nbsp;</li>
			</c:if>
        	<li>
        	<c:choose>
			    <c:when test="${not empty member}">
			        <a href="<c:url value="/signout"/>" class="login-btn">Logout</a>
			    </c:when>
			    <c:otherwise>
			        <a href="<c:url value="/signin"/>" class="login-btn">Login</a>
			    </c:otherwise>
			</c:choose>
        	</li>
        	<li class="devider">&nbsp;</li>
            <li><a href="<c:url value="/help"/>" class="login-btn">Help</a></li>   
        </ul>
    </div>      
</div><!--/top-->

<!--=== Header ===-->
<div id="header">               
    <div class="container"> 
        <!-- Logo -->    
        <div id="logo">       
        <h4><a href="<c:url value="/"/>">"<strong>WePlayFootball</strong>".fm</a></h4>
        
        </div>        
                                    
        <!-- Menu -->       
        <div class="navbar">                                
            <div class="navbar-inner">                                  
                <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </a><!-- /nav-collapse -->                                  
                <div class="nav-collapse collapse">                                     
                <ul class="nav top-2">
                    <li class="active">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">2013 Seoul League</a>
                    </li>
                    <li><a href="contact.html">Contact</a></li>                               
                </ul>
                </div><!-- /nav-collapse -->                                
            </div><!-- /navbar-inner -->
        </div><!-- /navbar -->                          

    </div><!-- /container -->               
</div><!-- /header -->      

<tiles:insertAttribute name="content" />

<!--=== Copyright ===-->
<div class="copyright">
	<div class="container">

		<div class="row-fluid">
			<div class="span8">						
	            <p>2012 &copy; Unify. ALL Rights Reserved. <a href="#">Privacy Policy</a> | <a href="#">Terms of Service</a></p>
			</div>
			<div class="span4">	
				<a href="index.html"><img src="assets/img/logo1.png" class="pull-right" alt="" /></a>
			</div>
		</div><!--/row-fluid-->

	</div><!--/container-->	
</div><!--/copyright-->	

<!-- JS and jQuery -->			
<script type="text/javascript" src="assets/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="assets/js/modernizr.custom.17475.js"></script>		
<script type="text/javascript" src="assets/bootstrap/js/bootstrap.min.js"></script>	
<script type="text/javascript" src="assets/fancybox/source/jquery.fancybox.pack.js"></script>
<script type="text/javascript" src="assets/flexslider/js/jquery.flexslider-min.js"></script>
<script type="text/javascript" src="assets/parallax-slider/js/modernizr.js"></script> 
<script type="text/javascript" src="assets/parallax-slider/js/jquery.cslider.js"></script> 
<script type="text/javascript" src="assets/js/back-to-top.js"></script>
<script type="text/javascript" src="assets/js/app.js"></script>
<script>
	$(function() {
		$('#da-slider').cslider();
	});
</script>
<!--[if lt IE 9]>
	<script src="assets/js/respond.js"></script>
<![endif]-->
<script type="text/javascript">
jQuery(document).ready(function() {
  	App.init();
});
</script>				
</body>
</html>	
