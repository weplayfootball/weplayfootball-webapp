<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" %>

<c:if test="${not empty member}">
<p>Welcome, <c:out value="${member.username}"/>! (<c:out value="${member.useremail}"/>)</p>
</c:if>
<a href="<c:url value="/signout" />">Sign Out</a>

