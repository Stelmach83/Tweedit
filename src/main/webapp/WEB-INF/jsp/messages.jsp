<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<br>
<h4>Your messages</h4>

<table class="table">
  <col style="width: 20%">
  <col style="width: 15%">
  <col style="width: 65%">
  <thead class="thead-dark">
  <tr>
    <th>Received</th>
    <th>From</th>
    <th>Title</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="m" items="${messages}">
    <tr>
      <td><fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${m.getDate()}"/></td>
      <td>${m.getFromUser().getUsername()}</td>
      <td>
        <a href="<%=request.getContextPath()%>/app/message/${m.getId()}">
          <c:if test="${m.getMessageRead() eq 0}">
            <strong>${m.getTitle()} (unread)</strong>
          </c:if>
          <c:if test="${m.getMessageRead() eq 1}">
            ${m.getTitle()}
          </c:if>
        </a>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>

<h5><a href="<%=request.getContextPath()%>/app/send">send message</a></h5>