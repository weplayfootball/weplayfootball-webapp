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
	
	});
</script>