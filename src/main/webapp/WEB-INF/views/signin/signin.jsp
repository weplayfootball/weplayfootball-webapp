<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%-- 

--%>
<form id="signin" action="<c:url value="/signin/authenticate" />" method="post">
	<div class="formInfo">
  		<c:if test="${param.error eq 'bad_credentials'}">
  		<div class="error">
  			Your sign in information was incorrect.
  			Please try again or <a href="<c:url value="/signup" />">sign up</a>.
  		</div>
 	 	</c:if>
  		<c:if test="${param.error eq 'multiple_users'}">
  		<div class="error">
  			Multiple local accounts are connected to the provider account.
  			Try again with a different provider or with your username and password.
  		</div>
 	 	</c:if>
	</div>
	<fieldset>
		<label for="login">Email</label>
		<input id="login" name="j_username" type="text" size="25" <c:if test="${not empty signinErrorMessage}">value="${SPRING_SECURITY_LAST_USERNAME}"</c:if> />
		<label for="passwd">Password</label>
		<input id="passwd" name="j_password" type="password" size="25" />	
	</fieldset>
	<button type="submit">Sign In</button>
	
	<p>Or you can <a href="<c:url value="/signup"/>">signup</a> with a new account.</p>
</form>

	<!-- TWITTER SIGNIN -->
	<form id="tw_signin" action="<c:url value="/signin/twitter"/>" method="POST">
		<button type="submit"><img src="<c:url value="/resources/social/twitter/sign-in-with-twitter-d.png"/>" /></button>
	</form>

	<!-- FACEBOOK SIGNIN -->
	<form name="fb_signin" id="fb_signin" action="<c:url value="/signin/facebook"/>" method="POST">
        <input type="hidden" name="scope" value="publish_stream,user_photos,offline_access" />
		<button type="submit"><img src="<c:url value="/resources/social/facebook/sign-in-with-facebook.png"/>" /></button>
	</form>

	