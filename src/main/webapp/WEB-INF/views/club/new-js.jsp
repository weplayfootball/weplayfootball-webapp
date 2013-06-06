<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<script>

	$(document).ready(function(){

		

		$.ajax({
			url: '<c:url value="/signup/glocaldo"/>',
			success: function(data){
				$('#clocalDo').html('');
				$('#clocalDo').append($('<option>', { 
			        value: '', text : '선택하세요' 
			    }));
				$.each(data, function(k, v){
					$('#clocalDo').append($('<option>', { 
				        value: v, text : v 
				    }));
				});
			},
			error:function(){
			}   
		});

		$('#clocalDo').change(function(){
			var glocaldo = $(this).find("option:selected").val();
			if(glocaldo.length > 0){
			    $.ajax({
					url: '<c:url value="/signup/glocalsi/"/>'+glocaldo,
					success: function(data){
						$('#clocalSi').html('');
						$.each(data, function(k, v){
							$('#clocalSi').append($('<option>', { 
						        value: v, text : v 
						    }));
						});
					},
					error:function(){
					}   
				});
			}else{
				$('#clocalSi').html('');
			}

		});
		
		$("#btnMveClubInfo").click(function() {
			$(location).attr('href','<c:url value="/club"/>');
		});
	});
</script>