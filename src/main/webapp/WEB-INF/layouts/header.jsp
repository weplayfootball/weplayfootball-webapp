<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--=== Top ===-->    
<div id="top">
    <div class="container">         
        <ul id="loginbar" class="pull-right">
        	<c:if test="${sessionScope.MEMBER != null}" >
        		<li>Welcome, <c:out value="${sessionScope.MEMBER.mname}"/>!</li>
				<li class="devider">&nbsp;</li>
			</c:if>
        	
        	<c:choose>
			    <c:when test="${sessionScope.MEMBER != null}">
			        <li><a href="<c:url value="/signout"/>" class="login-btn">Logout</a></li>
			    </c:when>
			    <c:otherwise>
			        <li><a href="<c:url value="/signin"/>" class="login-btn">Login</a></li>
			        <li class="devider">&nbsp;</li>
			        <li><a href="<c:url value="/signup"/>" class="login-btn">Register</a></li>
			    </c:otherwise>
			</c:choose>
        	
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
        	<a href="<c:url value="/home"/>"><img src="<c:url value="/resources/assets/img/logo.png"/>" alt="weplayfootball"></a>    
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
                    <li><a href="<c:url value="/home"/>" >2013 Summer League</a></li>
                    <c:choose>
			    		<c:when test="${sessionScope.MEMBER != null}">
                    	</c:when>   
			    		<c:otherwise>
                    		<li><a href="<c:url value="/signin"/>">Login</a></li>
			    		</c:otherwise>
			    	</c:choose>  
                    <li><a href="<c:url value="/public/contact"/>">Contact</a></li>     
                </ul>
                </div><!-- /nav-collapse -->                                
            </div><!-- /navbar-inner -->
        </div><!-- /navbar -->                          

    </div><!-- /container -->               
</div><!-- /header -->      

