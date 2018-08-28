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


<h5></h5>

<%-- TODO Dodać wyświetlanie listy wiadomości w tabeli, a nie <p>. --%>

<form:form method="post" action="/send" modelAttribute="message">
    <form>

        <input type="hidden" name="fromUser.id" value="${user.getId()}">

        <div class="form-group">
            <label for="exampleFormControlInput1">Send to</label>
            <form:select items="${users}" itemValue="id" itemLabel="username" class="form-control"
                         id="exampleFormControlInput1" path="toUser.id"/>
            <form:errors path="toUser.id" cssClass="error"/>
        </div>

        <div class="form-group">
            <label for="exampleFormControlInput1a">Title</label>
            <form:textarea class="form-control" rows="1" id="exampleFormControlInput1a"
                           placeholder="title" path="title"/>
            <form:errors path="title" cssClass="error"/>
        </div>

        <div class="form-group">
            <label for="exampleFormControlInput2">Message</label>
            <form:textarea class="form-control" rows="4" id="exampleFormControlInput2"
                           placeholder="text" path="text"/>
            <form:errors path="text" cssClass="error"/>
        </div>

        <input type="submit" value="Send">
    </form>
</form:form>


