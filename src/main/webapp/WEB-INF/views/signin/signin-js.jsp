<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<script>

	$(document).ready(function(){

		$('#btnSigninFB').click(function (e) {
			$("#fb_signin").submit();     
			e.preventDefault();
		}); 
		
		$('#btnSigninTW').click(function (e) {
			$("#tw_signin").submit();
			e.preventDefault();
		});
		
		$('#btnMailfogetPassword').click(function (e) {
			$.ajax({
				url: '<c:url value="/p/forgetPassword"/>',
				type: "POST",
				data: {mail: $('#memailForPassword').val()},
				success: function(data){
					if(data.status == "ok"){
						$("#btnMailfogetPassword").fadeOut(1000);
						$("#forgetPassword_step01").hide();
						$("#forgetPassword_step02").show();

						$("#alert_placeholder").fadeOut(1000);
						
					}else{
						bootstrap_alert.warning(data.message, 'error');
						$('#memailForPassword').val('');
						
					}
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
	
	});
</script>