<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<script>

$(document).ready(function(){;

<c:choose>
<%-- signup STEP 1 --%>
<c:when test="${empty signupForm.memail}">

	$('#btnSigninFB').click(function (e) {
		$("#fb_signin").submit();     
		e.preventDefault();
	}); 
	$('#btnSigninTW').click(function (e) {
		$("#tw_signin").submit();
		e.preventDefault();
	}); 

	$('#authByEmailForm').submit(function(event) {
		
		event.preventDefault();
		
		if( !$('#name').val() ) {
			bootstrap_alert.warning('이름을 입력하세요.', 'error');
			$('#name').focus();
			return false;
		}
		if( !$('#email').val() ) {
			bootstrap_alert.warning('이메일 입력하세요.', 'error');
			$('#email').focus();
			return false;
		}
		
		$("#alert_placeholder").fadeOut(500);
		
		var values = $(this).serialize();
		$.ajax({
			url: '<c:url value="/signup/auth"/>',
			type: "post",
			data: values,
			success: function(data){
		          console.log(data)
			},
			error:function(){
		          bootstrap_alert.warning('시스템 문제로 인해 전송하지 못했습니다.', 'error');
			}   
		});
	});
	
	var bootstrap_alert = function() {};
	bootstrap_alert.warning = function(message, t) {
        $('#alert_placeholder').html('<div class="alert alert-'+t+'"><a class="close" data-dismiss="alert">×</a><span><strong>'+message+'</strong></span></div>')
    };
	
</c:when>

<%-- signup STEP 2 --%>
<c:otherwise>

</c:otherwise>
</c:choose>

});
</script>