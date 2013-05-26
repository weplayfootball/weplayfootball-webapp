<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div id="body">
	<div class="breadcrumbs margin-bottom-50">
    	<div class="container">
            <h1 class="color-green pull-left subtitle">회원등록</h1>
            <ul class="pull-right breadcrumb">
                <li><a href="index.html">Home</a> <span class="divider">/</span></li>
                <li class="active">Signup</li>
            </ul>
        </div><!--/container-->
    </div><!--/breadcrumbs-->

	<div class="container">		
		<div class="row-fluid margin-bottom-10">
		
			<c:if test="${not empty message}">
			<div class="alert alert-error">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <strong>${message.type.cssClass}</strong>${message.text}
            </div>
			</c:if>

			<c:url value="/signup" var="signupUrl" />
			<form:form id="signup" action="${signupUrl}" method="post" modelAttribute="signupForm" enctype="multipart/form-data" class="reg-page">
        	
	        	<s:bind path="*">
					<c:choose>
						<c:when test="${status.error}">
							<div class="alert alert-error">
				                <button type="button" class="close" data-dismiss="alert">&times;</button>
				                Unable to sign up. Please fix the errors below and resubmit.
				            </div>
						</c:when>
					</c:choose>                     
				</s:bind>
            
				<div class="alert alert-block">
				    <button type="button" class="close" data-dismiss="alert">&times;</button>
	                정보를 입력하시면 회원가입이 완료됩니다.
	            </div>
                <div class="controls">    
                    <div class="span6">
	                    <form:label path="mname">이름 <form:errors path="mname" cssClass="error" /><span class="color-red">*</span></form:label>
						<form:input path="mname"  cssClass="span12" />		
                    </div>
                    <div class="span6">
                    	<label>프로필 사진 </label>
                    	<input type="file" name="profileFile" class="span12" />
                    </div>
                </div>
                <div class="controls">
                    <div class="span12">
	               		<form:label path="memail">이메일 <form:errors path="memail" cssClass="error" /><span class="color-red">*</span></form:label>
						<form:input path="memail"  cssClass="span6" />
					</div>    
                </div>
                <div class="controls">
                    <div class="span6">
	               		<form:label path="mpasswd">패스워드 (6자리 이상) <form:errors path="mpasswd" cssClass="error" /><span class="color-red">*</span></form:label>
						<form:input path="mpasswd"  cssClass="span12" />
                    </div>
                    <div class="span6">
                    	<label>패스워드 확인 </label>
						<input type="password" id="mpasswd_confirm"  class="span12" />
                    </div>
                </div>
                
                <hr />
                
                <div class="controls">
                    <div class="span6">
						<form:label path="mposition">주력 포지션 </form:label>
						<select class="span6" name='mposition'>
							<option value='coach'>coach</option>
							<option value='FW'>FW</option>
							<option value='AMF'>AMF</option>
							<option value='DMF'>DMF</option>
							<option value='DF'>DF</option>
							<option value='GK'>GK</option>
						</select>
                    </div>
                    <div class="span6">
						<form:label path="mlocal">활동 지역</form:label>
						<select class="span6" name='mlocal1'>
							<option value='coach'>coach</option>
							<option value='FW'>FW</option>
							<option value='AMF'>AMF</option>
							<option value='DMF'>DMF</option>
							<option value='DF'>DF</option>
							<option value='GK'>GK</option>
						</select>
						<select class="span6" name='mlocal'>
							<option value='coach'>coach</option>
							<option value='FW'>FW</option>
							<option value='AMF'>AMF</option>
							<option value='DMF'>DMF</option>
							<option value='DF'>DF</option>
							<option value='GK'>GK</option>
						</select>
                    </div>
                </div>
                <div class="controls">
                    <div class="span12">
						<form:label path="mintro">자기 소개</form:label>
						<textarea class="span12 border-radius-none" rows="3" name='mintro' id='mintro' ></textarea>
					</div> 
                </div>
                <div class="controls form-inline">
                    <div class="span12">
                    	<label class="checkbox">
                    	<input type="checkbox" checked name="checkemail">이메일공개<br>
				        <input type="checkbox" checked name="checktel">전화번호공개<br>
				        <input type="checkbox" checked name="checkusermsg">유저로부터의 경기초청수신<br>
				        <input type="checkbox" checked name="checkaddmsg">weplayfootball 소식수신
                    	</label>
	                    <button class="btn-u pull-right" type="submit">등록하기 </button>
                    </div> 
                </div>
                <hr />
                <div class="controls">
                    <div class="span12">
						이미 가입하셨다면, <a href="<c:url value="/signin"/>"><strong>로그인 화면</strong></a>으로 이동하세요.
					</div>
				</div>
            </form:form>
        </div><!--/row-fluid-->
	</div><!--/container-->		
</div><!--/body-->

