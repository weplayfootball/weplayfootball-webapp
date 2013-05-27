<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- 

--%>
<div id="body">
	<div class="breadcrumbs margin-bottom-40">
    	<div class="container">
            <h1 class="color-green pull-left subtitle">로그인</h1>
            <ul class="pull-right breadcrumb">
                <li><a href="<c:url value="/home"/>">Home</a> <span class="divider">/</span></li>
                <li class="active">Login</li>
            </ul>
        </div><!--/container-->
    </div><!--/breadcrumbs-->

	<div class="container">		
		<div class="row-fluid">
		
		<c:choose>
		<c:when test="${sessionScope.MEMBER != null}">
			<div class="alert alert-error">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
               	<h5>${sessionScope.MEMBER.mname} 님께서는 이미 로그인 하셨습니다.</h5>
               	<a href="<c:url value="/home" />" class="color-green"><strong>메인 화면</strong></a>
               	으로 가시거나, 
               	<a href="<c:url value="/signout"/>" class="color-green"><strong>로그아웃</strong></a> 하실 수 있습니다.  
            </div>
		</c:when>
		<c:otherwise>
		
			<c:if test="${param.error eq 'bad_credentials'}">
			<div class="alert alert-error">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
               	 <h5>로그인 정보가 잘못되었습니다.</h5>
                Facebook, Twitter 또는 이메일로 다시 로그인 하시거나, 
                <a href="<c:url value="/signup" />"><strong>신규 회원가입</strong></a> 하세요.
            </div>
	 	 	</c:if>
	  		<c:if test="${param.error eq 'multiple_users'}">
			<div class="alert alert-error">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
	  			Multiple local accounts are connected to the provider account.
	  			Try again with a different provider or with your email and password.
            </div>
	 	 	</c:if>
	 	 	
	 	 	
	 	 	
            <form id="signin" action="<c:url value="/signin/authenticate" />" method="post" class="log-page">
            
                <h4><strong>Facebook</strong> / <strong>Twitter</strong> 로 로그인하기</h4>
                <div class="input-prepend">
                	<a href="#" id="btnSigninFB"><img src="<c:url value="/resources/social/facebook/sign-in-with-facebook.png"/>" /></a>
                </div>
                <div class="input-prepend">
                	<a href="#" id="btnSigninTW"><img src="<c:url value="/resources/social/twitter/sign-in-with-twitter-d.png"/>" /></a>
                </div>
                <hr />
                <h4><strong>이메일</strong>로 로그인하기</h4>   
		                
				
                 
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
                <h5>암호를 잊으셨나요? </h5>
                <p><a href="<c:url value="/public/password"/>" class="color-green">여기</a> 에서 비밀번호를 초기화 할 수 있습니다.</p>
            </form>
            
        </c:otherwise>
		</c:choose>
        </div><!--/row-fluid-->
	</div><!--/container-->		
</div><!--/body-->

<!-- TWITTER SIGNIN -->
<form name="tw_signin" id="tw_signin" action="<c:url value="/signin/twitter"/>" method="POST"></form>
<!-- FACEBOOK SIGNIN -->
<form name="fb_signin" id="fb_signin" action="<c:url value="/signin/facebook"/>" method="POST">
<input type="hidden" name="scope" value="publish_stream,user_photos,offline_access" />
</form>