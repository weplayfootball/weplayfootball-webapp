<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- 

--%>
<div id="body">
	<div class="breadcrumbs margin-bottom-20">
    	<div class="container">
            <h1 class="color-green pull-left subtitle">클럽 정보</h1>
            <ul class="pull-right breadcrumb">
                <li><a href="<c:url value="/home"/>">Home</a> <span class="divider">/</span></li>
                <li class="active">Club</li>
            </ul>
        </div><!--/container-->
    </div><!--/breadcrumbs-->

	<div class="container">		
	
		<div class="row-fluid margin-bottom-20">
		
			<div class="span3">
	            <div class="margin-bottom-20" id="">
	            	<c:choose>
						<c:when test="${not empty club.image}">
	               			<img src="/resources/club/${club.image}" alt="">
	               		</c:when>
						<c:otherwise>
							<img src="http://www.placehold.it/400x250/EFEFEF/AAAAAA&text=No+Emblem+Image" alt="">
			            </c:otherwise>
					</c:choose>    
	            </div>
	            <ul class="unstyled">
                   <li><i>since</i> <strong>${club.ctime}</strong></li>
                   <li><i class="icon-map-marker"></i> ${club.clocal}</li>
                   <li><i class="icon-user"></i> ${club.cmakername}</li>
                </ul>
	            <p>${club.description}</p>
	        </div>
			<div class="span9">
            	<div class="blog margin-bottom-20">
					<h3>
						${club.cname}
						<a href="<c:url value="/club"/>" class="btn-u pull-right"><i class="icon-list-ul"></i>목록 </a>
					</h3>
					
					<hr />
                	<ul id="clubMenus" class="nav nav-tabs tabs">
		                <li><a href="#members" data-toggle="tab">선수목록</a></li>
		                <li><a href="#games" data-toggle="tab">경기이력</a></li>
		                <li><a href="#bbs" data-toggle="tab">게시판</a></li>
		                <li><a href="#guestbook" data-toggle="tab">방명록</a></li>
		            </ul>
		             <div id="clubMenuContents" class="tab-content">
		                <div class="tab-pane" id="members">
		                    <div class="media">
				                <a class="pull-left" href="#">
				                    <img class="media-object" src="http://graph.facebook.com/783155371/picture" alt="">
				                </a>
				                <div class="media-body">
				                    <h4 class="">김요한</h4>
				                    <p>Donec id elit non mi porta . </p>
				                </div>
				            </div><div class="media">
				                <a class="pull-left" href="#">
				                    <img class="media-object" src="http://graph.facebook.com/783155371/picture" alt="">
				                </a>
				                <div class="media-body">
				                    <h4 class="">김요한</h4>
				                    <p>Donec id elit non mi porta . </p>
				                </div>
				            </div><div class="media">
				                <a class="pull-left" href="#">
				                    <img class="media-object" src="http://graph.facebook.com/783155371/picture" alt="">
				                </a>
				                <div class="media-body">
				                    <h4 class="">김요한</h4>
				                    <p>Donec id elit non mi porta . </p>
				                </div>
				            </div><div class="media">
				                <a class="pull-left" href="#">
				                    <img class="media-object" src="http://graph.facebook.com/783155371/picture" alt="">
				                </a>
				                <div class="media-body">
				                    <h4 class="">김요한</h4>
				                    <p>Donec id elit non mi porta . </p>
				                </div>
				            </div>
		                </div>
		                <div class="tab-pane" id="games">
		                    <p>Vivamus imperdiet condimentum diam, eget placerat felis consectetur id. Donec eget orci metus, ac adipiscing nunc.</p>
		                </div>
		                <div class="tab-pane" id="bbs">
		                    <p>Vivamus imperdiet condimentum diam, eget placerat felis consectetur id. Donec eget orci metus, ac adipiscing nunc.</p>
		                </div>
		                <div class="tab-pane" id="guestbook">
		                    <p>Vivamus imperdiet condimentum diam, eget placerat felis consectetur id. Donec eget orci metus, ac adipiscing nunc.</p>
		                </div>
		            </div>
				</div>
			
			</div>
		</div>
	</div><!--/container-->		
</div><!--/body-->

