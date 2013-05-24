<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="stylesheet" href="<c:url value="/resources/assets/bootstrap/css/bootstrap.min.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/resources/assets/css/style.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/resources/assets/css/menu/style-menu.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/resources/assets/bootstrap/css/bootstrap-responsive.min.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/resources/assets/css/style_responsive.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/resources/assets/css/animate.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/resources/assets/css/effects.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/resources/assets/fancybox/jquery.fancybox.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/resources/assets/flexslider/css/flexslider.css"/>" type="text/css" media="screen" />
    <link rel="stylesheet" href="<c:url value="/resources/assets/parallax-slider/css/parallax-slider.css"/>" type="text/css" />
    <link rel="stylesheet" href="<c:url value="/resources/assets/font-awesome/css/font-awesome.css"/>" type="text/css">
</head>	

<body>
<tiles:insertTemplate template="header.jsp" />
<tiles:insertAttribute name="content" />
<tiles:insertTemplate template="copyright.jsp" />

<!-- JS and jQuery -->			
<script type="text/javascript" src="<c:url value="/resources/assets/js/jquery-1.8.2.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/assets/js/modernizr.custom.17475.js"/>"></script>		
<script type="text/javascript" src="<c:url value="/resources/assets/bootstrap/js/bootstrap.min.js"/>"></script>	
<script type="text/javascript" src="<c:url value="/resources/assets/fancybox/jquery.fancybox.pack.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/assets/flexslider/js/jquery.flexslider-min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/assets/parallax-slider/js/modernizr.js"/>"></script> 
<script type="text/javascript" src="<c:url value="/resources/assets/parallax-slider/js/jquery.cslider.js"/>"></script> 
<script type="text/javascript" src="<c:url value="/resources/assets/js/back-to-top.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/assets/js/app.js"/>"></script>
<!--[if lt IE 9]>
	<script src="src="<c:url value="/resources/assets/js/respond.js"/>"></script>
<![endif]-->

<tiles:insertAttribute name="javscrtipt" ignore="true" />
	
</body>
</html>	
