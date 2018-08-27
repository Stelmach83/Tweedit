<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<br>
<h4>Your messages</h4>


<c:forEach var="m" items="${messages}">
    <p>
        <a href="<%=request.getContextPath()%>/message/${m.getId()}">
            <c:if test="${m.getMessageRead() eq 0}">
                <strong>${m.getTitle()} (unread)</strong>
            </c:if>
            <c:if test="${m.getMessageRead() eq 1}">
                ${m.getTitle()}
            </c:if>
        </a>
    </p>
</c:forEach>


<h5><a href="<%=request.getContextPath()%>/send">send message</a></h5>