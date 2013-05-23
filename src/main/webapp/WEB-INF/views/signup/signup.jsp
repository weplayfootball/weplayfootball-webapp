<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<h3>Sign Up</h3>

<c:if test="${not empty message}">
<div class="${message.type.cssClass}">${message.text}</div>
</c:if>

<c:url value="/signup" var="signupUrl" />
<form:form id="signup" action="${signupUrl}" method="post" modelAttribute="signupForm">
	<div class="formInfo">
		<s:bind path="*">
			<c:choose>
				<c:when test="${status.error}">
					<div class="error">Unable to sign up. Please fix the errors below and resubmit.</div>
				</c:when>
			</c:choose>                     
		</s:bind>
	</div>
	
	<fieldset>
		<form:label path="useremail">First Name <form:errors path="useremail" cssClass="error" /></form:label>
		<form:input path="useremail" />
		<form:label path="username">Username <form:errors path="username" cssClass="error" /></form:label>
		<form:input path="username" />		
		<form:label path="passwd">Password (at least 6 characters) <form:errors path="passwd" cssClass="error" /></form:label>
		<form:password path="passwd" />
	</fieldset>
	<p><button type="submit">Sign Up</button></p>
</form:form>
