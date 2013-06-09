<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- 

--%>
<div id="body">
	<div class="breadcrumbs margin-bottom-40">
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
					<c:when test="${not empty club.cname}">
               			<img src="/resources/club/${club.image}" alt="">
               		</c:when>
					<c:otherwise>
						<img src="http://www.placehold.it/400x250/EFEFEF/AAAAAA&text=No+Emblem+Image" alt="">
		            </c:otherwise>
				</c:choose>    
            </div>
		
			<div class="span5">
				<button id="btnMyClubList" class="btn-u btn-u-orange" type="button" data-loading-text="Loading...">내 클럽 보기</button>
				<a href="<c:url value="/club/new"/>" class="btn-u btn-u-blue">클럽 생성하기</a>
			</div>
			<div class="span7">
				<form id="searchClubForm" class="form-search">
					<select class="span3" name='clocal1' id='clocal1'>
					</select>
					<select class="span3" name='clocal' id='clocal'>
					</select>
                    <input type="text" class="span4 border-radius-none input-medium search-query" id="cname">
                    <button type="submit" class="btn-u">Search</button>
                </form>
			</div>
		</div>
		
	    <div id="searchResult" class="row-fluid search-page margin-bottom-20"></div>
		<p id="loadingBar" class="text-success" style="display: none;">목록을 가져오고 있습니다 . . . . . . </p>
		
	    <div id="myClubList" class="row-fluid search-page margin-bottom-40"></div>
	    
	</div><!--/container-->		
</div><!--/body-->

