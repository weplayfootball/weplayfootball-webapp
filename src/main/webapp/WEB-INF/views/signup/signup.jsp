<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div id="body">
	<div class="breadcrumbs margin-bottom-20">
    	<div class="container">
            <h1 class="color-green pull-left subtitle">회원등록</h1>
            <ul class="pull-right breadcrumb">
                <li><a href="index.html">Home</a> <span class="divider">/</span></li>
                <li class="active">Signup</li>
            </ul>
        </div><!--/container-->
    </div><!--/breadcrumbs-->

	<div class="container">		
		<div class="row-fluid margin-bottom-20">
		
			<c:if test="${not empty message}">
			<div class="alert alert-error">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <strong>${message.type.cssClass}</strong>&nbsp;&nbsp;${message.text}
            </div>
			</c:if>
			
			<c:choose>
				<%-- signup STEP 1 --%>
				<c:when test="${empty signupForm.memail}">
	 	 	
		            <div class="log-page">
		            
		                <div id="bySNS">
			                <h4><strong>Facebook</strong>/<strong>Twitter</strong> 연결하여 가입하기. </h4>
			                <div class="input-prepend">
			                	<a href="#" id="btnSigninFB"><img src="<c:url value="/resources/social/facebook/sign-in-with-facebook.png"/>" /></a>
			                </div>
			                <div class="input-prepend">
			                	<a href="#" id="btnSigninTW"><img src="<c:url value="/resources/social/twitter/sign-in-with-twitter-d.png"/>" /></a>
			                </div>
			                
		                	<hr />
			            </div>
		                
		                
		                <div id="byEmail">
		                	<form id="authByEmailForm">
				                <h4><strong>이메일</strong>로 가입하기 </h4>   
				                <div class="input-prepend">
				                    <span class="add-on"><i class="icon-user"></i></span>
				                    <input id="name" name="name" type="text" class="input-xlarge" placeholder="Your full name" />
				                </div>
				                <div class="input-prepend">
				                    <span class="add-on"><i class="icon-envelope"></i></span>
				                    <input id="email" name="email" type="text" class="input-xlarge"  placeholder="Email Address" />
				                </div>
				                <div id = "alert_placeholder"></div>
				                <div class="controls form-inline">
				                    <button id="emailValid" class="btn btn-primary pull-right" type="submit">이메일 인증</button>
				                </div>
				                <p class="text-info" id="emailValidMsg">이름과 이메일 주소를 넣고 <code>이메일인증</code> 버튼을 클릭하세요.</p>
				            </form>
		                </div>
		                
		                <div id="doneEmailAuth" class="hide">
		                	<h4><strong>이메일 인증 메일이 발송되었습니다.</strong> </h4>
		                	<hr />
		                	<h5>이메일을 확인하시고, 회원가입을 진행해주세요.</h5>
		                	<h5>감사합니다.</h5>
		                </div>
		            </div>
				
				</c:when>
				
				
				<%-- signup STEP 2 --%>
				<c:otherwise>

					<c:url value="/signup" var="signupUrl" />
					<form:form id="signup" action="${signupUrl}" method="post" modelAttribute="signupForm" enctype="multipart/form-data">
			            <div class="span6">
			                <div class="headline"><h3>기본 정보</h3></div>
			                <s:bind path="*">
								<c:choose>
									<c:when test="${status.error}">
										<div class="alert alert-error">
							                <button type="button" class="close" data-dismiss="alert">&times;</button>
							               	<strong>등록할 수 없습니다.</strong> 입력이 잘못된 부분을 다시 작성하시고 등록해주세요.
							            </div>
									</c:when>
									<c:otherwise>
										<div class="alert alert-success">
										    <button type="button" class="close" data-dismiss="alert">&times;</button>
							               		아래  정보를 입력하시면 회원가입이 완료됩니다.
							            </div>
						            </c:otherwise>
								</c:choose>                     
							</s:bind>
		                    <div class="control-group">
		                        <label class="control-label" for="inputEmail">Email</label>
		                        <div class="controls"><h4><strong>${signupForm.memail}</strong></h4></div>
		                    </div>
		                    <div class="control-group">
		                    	<form:label path="mname" cssClass="control-label">이름  <span class="color-red">*</span> <form:errors path="mname" cssClass="label label-important" /></form:label>
								<div class="controls"><form:input path="mname"  cssClass="border-radius-none" /></div>
		                    </div>
		                    <div class="control-group">
								<form:label path="mpasswd" cssClass="control-label">패스워드 (6자리 이상) <span class="color-red">*</span> <form:errors path="mpasswd" cssClass="label label-important" /></form:label>
								<div class="controls">
									<form:password path="mpasswd" cssClass="border-radius-none" />
									<input type="password" id="mpasswd_confirm" class="border-radius-none"/>
								</div>
		                    </div>
		                    <div class="control-group">
		                        <label class="control-label" for="profileFile">사진</label>
		                        <div class="controls">
		                        	<input type="file" name="profileFile" class="border-radius-none" />
		                        </div>
		                    </div>
			            </div>
			
			            <div class="span6">
							<div class="headline"><h3>추가 정보</h3></div>
							
							<form:label path="mtel">연락처 <span class="color-red">*</span> <form:errors path="mtel" cssClass="label label-important" /></form:label>
							<form:input path="mtel"  cssClass="border-radius-none" />
							
							<form:label path="mposition">주력 포지션 <span class="color-red">*</span> <form:errors path="mposition" cssClass="label label-important" /></form:label>
							<select class="span6" name='mposition'>
								<option value='coach'>coach</option>
								<option value='FW'>FW</option>
								<option value='AMF'>AMF</option>
								<option value='DMF'>DMF</option>
								<option value='DF'>DF</option>
								<option value='GK'>GK</option>
							</select>
							
							<form:label path="mlocal">활동 지역<span class="color-red">*</span> <form:errors path="mlocal" cssClass="label label-important" /></form:label>
							<select class="span6" name='mlocal1' id='mlocal1'>
							</select>
							<select class="span6" name='mlocal' id='mlocal'>
							</select>
							
							<form:label path="mintro">자기 소개</form:label>
							<textarea class="span12 border-radius-none" rows="4" name='mintro' id='mintro' >매너있고 실력있는 멋진 축구인입니다~ 함께 뛰실 분들 언제든지 환영합니다!! 연락주세요 *^^*</textarea>
							
			            </div>
						
 						<div class="span12">
							<hr />
	                    	<!-- label class="checkbox">
	                    	<input type="checkbox" checked name="checkemail">이메일공개<br>
					        <input type="checkbox" checked name="checktel">전화번호공개<br>
					        <input type="checkbox" checked name="checkusermsg">유저로부터의 경기초청수신<br>
					        <input type="checkbox" checked name="checkaddmsg">weplayfootball 소식수신
	                    	</label -->
		                    <button class="btn-u pull-right" type="submit"><strong>등록하기</strong> </button>&nbsp;&nbsp;&nbsp;
	                    </div> 
 						<div class="span12">
	                    	  이미 가입하셨다면, <a href="<c:url value="/signin"/>" class="color-green"><strong>로그인 화면</strong></a>으로 이동하세요.
	                   	</div>
					<form:hidden path="memail" value="${signupForm.memail}"/>
					<form:hidden path="authcd" value="${signupForm.authcd}"/>
		            </form:form>
				</c:otherwise>
			</c:choose>
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

