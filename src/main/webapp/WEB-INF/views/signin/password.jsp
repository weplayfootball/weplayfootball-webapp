<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- 

--%>
<div id="body">
	<div class="breadcrumbs margin-bottom-60">
    	<div class="container">
            <h1 class="color-green pull-left subtitle">암호 변경</h1>
            <ul class="pull-right breadcrumb">
                <li><a href="<c:url value="/home"/>">Home</a> <span class="divider">/</span></li>
                <li class="active">Login</li>
            </ul>
        </div><!--/container-->
    </div><!--/breadcrumbs-->

	<div class="container">		
		<div class="row-fluid margin-bottom-60">
		
		<c:if test="${not empty message}">
			<div class="alert alert-error">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <strong>${message.type.cssClass}</strong>&nbsp;&nbsp;${message.text}
            </div>
		</c:if>
		
			<c:url value="/signin/password" var="passwordUrl" />
			<form:form id="signinPassword" action="${passwordUrl}" method="post" modelAttribute="forgetPasswordForm" cssClass="log-page">
			        
			    <h4><strong>이메일</strong>로 로그인하기</h4>   
                 
                <div class="control-group">
                    <label class="control-label" for="inputEmail">Email</label>
                    <div class="controls"><h4><strong>${forgetPasswordForm.memail}</strong></h4></div>
                </div>
                
	            <div class="control-group">
	            	<form:label path="mpasswd" cssClass="control-label">암호 <form:errors path="mpasswd" cssClass="label label-important" /></form:label>
					<div class="controls"><form:password path="mpasswd"  cssClass="border-radius-none" /></div>
	            </div>
                <div class="control-group">
	            	<form:label path="mpasswdconfirm" cssClass="control-label">암호 <form:errors path="mpasswdconfirm" cssClass="label label-important" /></form:label>
					<div class="controls"><form:password path="mpasswdconfirm"  cssClass="border-radius-none" /></div>
	            </div>

                <div class="controls form-inline">
                    <button class="btn-u pull-right" type="submit">암호 변경</button>
                </div>
					
			<form:hidden path="memail" value="${forgetPasswordForm.memail}"/>
			<form:hidden path="authcd" value="${forgetPasswordForm.authcd}"/>
            </form:form>
          
        </div><!--/row-fluid-->
	</div><!--/container-->		
</div><!--/body-->

<!-- TWITTER SIGNIN -->
<form name="tw_signin" id="tw_signin" action="<c:url value="/signin/twitter"/>" method="POST">
</form>
<!-- FACEBOOK SIGNIN -->
<form name="fb_signin" id="fb_signin" action="<c:url value="/signin/facebook"/>" method="POST">
<input type="hidden" name="scope" value="email,publish_stream,user_photos,offline_access" />
</form>