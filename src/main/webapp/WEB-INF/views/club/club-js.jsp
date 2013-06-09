<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<script>

	$(document).ready(function(){

		

		$.ajax({
			url: '<c:url value="/signup/glocaldo"/>',
			success: function(data){
				$('#clocal1').html('');
				$('#clocal1').append($('<option>', { 
			        value: '', text : '선택하세요' 
			    }));
				$.each(data, function(k, v){
					$('#clocal1').append($('<option>', { 
				        value: v, text : v 
				    }));
				});
			},
			error:function(){
			}   
		});

		$('#clocal1').change(function(){
			var glocaldo = $(this).find("option:selected").val();
			if(glocaldo.length > 0){
			    $.ajax({
					url: '<c:url value="/signup/glocalsi/"/>'+glocaldo,
					success: function(data){
						$('#clocal').html('');
						$.each(data, function(k, v){
							$('#clocal').append($('<option>', { 
						        value: v, text : v 
						    }));
						});
					},
					error:function(){
					}   
				});
			}else{
				$('#clocal').html('');
			}

		});
		

		var currentPageNumber = 1,
			searchConditions = {},
			isPreviousEventComplete = true, 
			isDataAvailable = false;

		var appendSearchList = function(data, divObj, isAppend){

			var r = '';
			$.each(data, function(k, v){
				
				if(k%2 == 0){
					r = r + '<div class="row-fluid">';
				}

				r = r + '<div class="span6 booking-blocks">';
				r = r + '	<div class="pull-left booking-img">';
				r = r + ' 		<img src="/resources/club/'+v.image+'">';
				r = r + '	</div>';
				r = r + '	<div style="overflow:hidden;">';
				r = r + '		<h3><a href="#">'+v.cname+'</a></h3>';
				r = r + '<ul class="unstyled inline blog-info">';
				r = r + '<li><i class="icon-calendar"></i> February 02, 2013</li>';
				r = r + '<li><i class="icon-pencil"></i> '+v.clocal+'</li>';
				r = r + '<li><i class="icon-group"></i> '+v.members+'</li>';
				r = r + '</ul>';
				r = r + '		<p>'+v.description+'</p>'; 
				r = r + '	</div>';
				r = r + '</div>';
				
				if(k%2 == 1 || k == data.length){
					r = r + '</div>';
				}
			});
			
			if(r.length == 0){
				r = '<h4 class="text-center margin-bottom-40">조회 결과가 없습니다.</h4>';
			}
			
			if(isAppend){
				divObj.append(r);
			}else{
				divObj.html(r);	
			}
		};
		
		$('#searchClubForm').submit(function(event) {
			
			event.preventDefault();
			
			$("#myClubList").hide();

			$("#searchResult").show();
			
			currentPageNumber 	= 1;
			isDataAvailable 	= true;
			searchConditions 	= {
				clocal: 	$("#clocal").val(),
				cname: 		$("#cname").val(),
				pageNum: 	currentPageNumber
			};
			
			$.ajax({
				url: '<c:url value="/club/list"/>',
				type: "get",
				data: searchConditions,
				success: function(data){
					appendSearchList(data, $("#searchResult"));
					
					if(data.length < 20) isDataAvailable = false;
					currentPageNumber = currentPageNumber + 1;
					
				},
				error:function(){
				}   
			});
		});
		
		$(window).scroll(function () {
			if ($(document).height() - 50 <= $(window).scrollTop() + $(window).height()) {
				if (isPreviousEventComplete && isDataAvailable) {
					isPreviousEventComplete = false;
					
			        $("#loadingBar").show();
	
			        searchConditions.pageNum = currentPageNumber;
			        
			        $.ajax({
			        	url: '<c:url value="/club/list"/>',
						type: "get",
						data: searchConditions,
						success: function(data){
							appendSearchList(data, $("#searchResult"), true);
							
							if(data.length < 20) {
								isDataAvailable = false;
							}else{
								currentPageNumber = currentPageNumber + 1;
							}
							isPreviousEventComplete = true;
					        $("#loadingBar").hide();
		          		},
		          		error: function (error) {
					        $("#loadingBar").hide();
		        	  	}
		        	});
				}
			}
		});
		
		
		// for   MY CLUB LIST
		$("#btnMyClubList").click(function() {
			
			$("#searchResult").hide();
			$("#myClubList").show();
			$("#myClubList").html('');
			
			$.ajax({
				url: '<c:url value="/club/me"/>',
				type: "get",
				data: searchConditions,
				success: function(data){
					appendSearchList(data, $("#myClubList"));
				},
				error:function(){
				}   
			});
			
		});
		

		// #btnMyClubList
	});
</script>