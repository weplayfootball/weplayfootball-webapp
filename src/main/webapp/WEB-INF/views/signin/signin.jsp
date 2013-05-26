<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%-- 

--%>
<div id="body">
	<div class="breadcrumbs margin-bottom-40">
    	<div class="container">
            <h1 class="color-green pull-left subtitle">로그인</h1>
            <ul class="pull-right breadcrumb">
                <li><a href="<c:url value="/"/>">Home</a> <span class="divider">/</span></li>
                <li class="active">Login</li>
            </ul>
        </div><!--/container-->
    </div><!--/breadcrumbs-->

	<div class="container">		
		<div class="row-fluid">
            <form id="signin" action="<c:url value="/signin/authenticate" />" method="post" class="log-page">
            
                <h4><strong>Facebook</strong> / <strong>Twitter</strong> 로 로그인하기</h4>
                <div class="input-prepend">
                	<img src="<c:url value="/resources/social/facebook/sign-in-with-facebook.png"/>" />
                </div>
                <div class="input-prepend">
                	<img src="<c:url value="/resources/social/twitter/sign-in-with-twitter-d.png"/>" />
                </div>
                <hr />
                <h4><strong>이메일</strong>로 로그인하기</h4>   
		                
				<c:if test="${param.error eq 'bad_credentials'}">
				<div class="alert alert-block">
	                <button type="button" class="close" data-dismiss="alert">&times;</button>
	                로그인 정보가 잘못되었습니다. <br>
	                Facebook, Twitter 또는 이메일로 다시 로그인 하시거나, 
	                <a href="<c:url value="/signup" />">신규 회원가입</a> 하세요.
	            </div>
		 	 	</c:if>
		  		<c:if test="${param.error eq 'multiple_users'}">
				<div class="alert alert-block">
	                <button type="button" class="close" data-dismiss="alert">&times;</button>
		  			Multiple local accounts are connected to the provider account.
		  			Try again with a different provider or with your email and password.
	            </div>
		 	 	</c:if>
                 
                <div class="input-prepend">
                    <span class="add-on"><i class="icon-envelope"></i></span>
                    <input id="memail" name="j_username" type="text" class="input-xlarge"  placeholder="Email Address" <c:if test="${not empty signinErrorMessage}">value="${SPRING_SECURITY_LAST_USERNAME}"</c:if> />
                </div>
                <div class="input-prepend">
                    <span class="add-on"><i class="icon-lock"></i></span>
                    <input id="mpasswd" name="j_password" type="password" class="input-xlarge" />
                </div>
                <div class="controls form-inline">
                    <button class="btn-u pull-right" type="submit">Login</button>
                </div>
                <br/>
                <h5>Forget your Password ?</h5>
                <p>no worries, <a href="<c:url value="/public/password"/>">click here</a> to reset your password.</p>
            </form>
        </div><!--/row-fluid-->
	</div><!--/container-->		
</div><!--/body-->

<!-- TWITTER SIGNIN -->
<form name="tw_signin" id="tw_signin" action="<c:url value="/signin/twitter"/>" method="POST"></form>
<!-- FACEBOOK SIGNIN -->
<form name="fb_signin" id="fb_signin" action="<c:url value="/signin/facebook"/>" method="POST">
<input type="hidden" name="scope" value="publish_stream,user_photos,offline_access" />
</form>