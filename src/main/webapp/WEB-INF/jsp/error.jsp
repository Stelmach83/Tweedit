<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<br>
<h4>Error</h4>

<c:if test="${sessionScope[\"SPRING_SECURITY_LAST_EXCEPTION\"].message eq 'Bad credentials'}">
    <p class="error">Email/Password is incorrect.</p>
</c:if>
<c:if test="${sessionScope[\"SPRING_SECURITY_LAST_EXCEPTION\"].message eq 'User is disabled'}">
    <p>Your account is disabled, please contact administrator.</p>
</c:if>
<c:if test="${fn:containsIgnoreCase(sessionScope[\"SPRING_SECURITY_LAST_EXCEPTION\"].message,'A communications error has been detected')}">
    <p>Database connection is down, try again later.</p>
</c:if>