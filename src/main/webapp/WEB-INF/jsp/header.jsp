<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                       data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">
                        All categories
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <c:forEach var="cat" items="${categories}">
                        <a class="dropdown-item" href="#">${cat.getName()}</a>
                        </c:forEach>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                </li>

                <c:if test="${not empty user}">
                    <c:if test="${unread ne 0}">
                        <li class="nav-item">
                            <a class="nav-link" href="#">You have ${unread} messages</a>
                        </li>
                    </c:if>
                </c:if>

                <li class="nav-item">
                    <a class="nav-link" href="#">Link</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled" href="#">Disabled</a>
                </li>
            </ul>
            <form class="form-inline my-2 my-lg-0" action="<%=request.getContextPath()%>/login" method="post">
                <c:choose>
                    <c:when test="${not empty user}"><a
                            href="<%=request.getContextPath()%>/login?logout">${user.getUsername()} logout</a></c:when>
                    <c:otherwise>
                        <input class="form-control mr-sm-2" type="email" placeholder="Email" name="username">
                        <input class="form-control mr-sm-2" type="password" placeholder="Password" name="password">
                        <button class="btn btn-primary my-2 my-sm-0" type="submit">Login</button>
                    </c:otherwise>
                </c:choose>
            </form>
        </div>
    </div>
</nav>