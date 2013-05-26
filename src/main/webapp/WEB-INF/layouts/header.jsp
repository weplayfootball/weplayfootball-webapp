<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!--=== Top ===-->    
<div id="top">
    <div class="container">         
        <ul id="loginbar" class="pull-right">
        	<c:if test="${not empty member}">
        		<li>Welcome, <c:out value="${member.mname}"/>!</li>
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
            <li><a href="<c:url value="/public/help"/>" class="login-btn">Help</a></li>   
        </ul>
    </div>      
</div><!--/top-->

<!--=== Header ===-->
<div id="header">               
    <div class="container"> 
        <!-- Logo -->    
        <div id="logo">       
        <h4><a href="<c:url value="/"/>">WePlayFootball</a></h4>
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
                    <li><a href="<c:url value="/public/contact"/>">Contact</a></li>                               
                </ul>
                </div><!-- /nav-collapse -->                                
            </div><!-- /navbar-inner -->
        </div><!-- /navbar -->                          

    </div><!-- /container -->               
</div><!-- /header -->      

