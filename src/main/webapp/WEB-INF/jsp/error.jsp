<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<br>
<div class="row justify-content-center">
  <div class="col-md-6">
    <c:choose>
      <c:when test="${sessionScope[\"SPRING_SECURITY_LAST_EXCEPTION\"].message eq 'Bad credentials'}">
        <p class="error">Email/Password is incorrect.</p>
      </c:when>
      <c:when test="${sessionScope[\"SPRING_SECURITY_LAST_EXCEPTION\"].message eq 'User is disabled'}">
        <p>Your account is disabled, please contact administrator.</p>
      </c:when>
      <c:when test="${fn:containsIgnoreCase(sessionScope[\"SPRING_SECURITY_LAST_EXCEPTION\"].message,'A communications error has been detected')}">
        <p>Database connection is down, try again later.</p>
      </c:when>
      <c:otherwise>
        <div class="clearfix">
          <h1 class="float-left display-3 mr-4">500</h1>
          <h4 class="pt-3">Houston, we have a problem!</h4>
          <p class="text-muted">The page you are looking for is temporarily unavailable.</p>
        </div>
      </c:otherwise>
    </c:choose>
  </div>
</div>

