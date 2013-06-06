<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%-- 

--%>
<div id="body">
	<div class="breadcrumbs margin-bottom-40">
    	<div class="container">
            <h1 class="color-green pull-left subtitle">클럽 생성</h1>
            <ul class="pull-right breadcrumb">
                <li><a href="<c:url value="/home"/>">Home</a> <span class="divider">/</span></li>
                <li class="active">Club</li>
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

			<c:url value="/club/new" var="clubNewUrl" />
			<form:form id="clubForm" action="${clubNewUrl}" method="post" modelAttribute="clubForm" enctype="multipart/form-data">
	            <div class="span8">
	                <div class="headline"><h3>클럽 기본 정보</h3></div>
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
					               		아래  정보를 입력하시면 신규 클럽이 생성됩니다.
					            </div>
				            </c:otherwise>
						</c:choose>                     
					</s:bind>

                    <div class="control-group">
                    	<form:label path="cname" cssClass="control-label">클럽 이름 <span class="color-red">*</span> 
                    		<form:errors path="cname" cssClass="label label-important" />
                    	</form:label>
						<div class="controls">
							<form:input path="cname"  cssClass="border-radius-none" />
						</div>
                    </div>
                    <div class="control-group">
	                    <form:label path="clocalSi" cssClass="control-label">지역 선택 <span class="color-red">*</span> 
	                    	<form:errors path="clocalSi" cssClass="label label-important" />
	                    </form:label>
	                    <div class="controls">
							<form:select cssClass="span4" path='clocalDo'/>
							<form:select cssClass="span4" path='clocalSi'/>
						</div>
					</div>
                    <div class="control-group">
						<form:label path="description" cssClass="control-label">클럽 소개</form:label>
						<div class="controls">
							<textarea class="span12 border-radius-none" rows="4" name='description' id='description' >매너있고 실력있는 멋진 클럽입니다~ 함께 뛰실 분들 언제든지 환영합니다!! 연락주세요 *^^*</textarea>
						</div>
					</div>
	            </div>
	
	            <div class="span4">
					<div class="headline"><h3>클럽 엠블럼 이미지</h3></div>
					
					<div class="fileupload fileupload-new" data-provides="fileupload">
					  <div class="fileupload-new thumbnail" style="width: 200px; height: 150px;">
					  	<img src="http://www.placehold.it/200x150/EFEFEF/AAAAAA&text=no+image" />
					  </div>
					  
					  <div class="fileupload-preview fileupload-exists thumbnail" style="max-width: 200px; max-height: 150px; line-height: 20px;">
					  </div>
					  
					  <div>
					    <span class="btn btn-file">
						    <span class="fileupload-new">이미지 등록</span>
						    <span class="fileupload-exists">이미지 변경</span>
						    <input type="file" name="emblemImage"/>
						</span>
					    <a href="#" class="btn fileupload-exists" data-dismiss="fileupload">이미지 삭제</a>
					  </div>
					</div>

	            </div>
				
				<div class="span12">
					<hr />
                    
					<button class="btn-u btn-u-sea" type="button" id="btnMveClubInfo">톨아가기</button>
					
                    <button class="btn-u btn-u-orange" type="submit">클럽 등록하기</button>
                    
				</div> 
            </form:form>
        </div><!--/row-fluid-->
	</div><!--/container-->		
	
		
</div><!--/body-->


