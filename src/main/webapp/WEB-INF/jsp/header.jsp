<%@ page import="net.stelmaszak.tweedit.entity.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<header class="app-header navbar navbar-expand-lg" style="background-color: #f4f4f4;">
  <div class="container">
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav mr-auto">
        <c:if test="${user.getLogged() eq 1}">
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">All categories</a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
              <c:forEach var="cat" items="${categories}">
              <div class="dropdown-item">
                <div class="row" style="width: 200px">
                  <div class="col-sm-2 d-flex justify-content-start">
                    <a class="nav-link catelink" href="#" style="padding-top: 3px;margin-left: -20px;padding-left: -20px">
                        ${cat.getSubbedUsers().size()}<i class="icon-user" style="padding-left: 10px"></i>
                    </a>
                  </div>
                  <div class="col-sm-5 d-flex justify-content-start">
                    <a class="nav-link catelink" href="<%=request.getContextPath()%>/app/posts/${cat.getId()}" style="padding-top: 3px;margin-left: -20px;padding-left: -20px">${cat.getName()}</a>
                  </div>
                  <div class="col-sm-5">
                    <div class="col-sm-12">
                      <div class="catfollow" data-cid="${cat.getId()}">
                        <c:choose>
                          <c:when test="${cat.getSubbedUsers().contains(user)}">
                            <div class="d-flex justify-content-center">
                              <a href="<%=request.getContextPath()%>/app/unfollowcat/${cat.getId()}">
                                <button type="button" class="btn btn-outline-dark btn-sm" style="padding-top: 3px" data-contextpath="<%=request.getContextPath()%>">unfollow</button>
                              </a>
                            </div>
                          </c:when>
                          <c:otherwise>
                            <div class="d-flex justify-content-center">
                              <a href="<%=request.getContextPath()%>/app/followcat/${cat.getId()}">
                                <button type="button" class="btn btn-outline-dark btn-sm" style="padding-top: 3px" data-contextpath="<%=request.getContextPath()%>">follow</button>
                              </a>
                            </div>
                          </c:otherwise>
                        </c:choose>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              </c:forEach>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/app/showrankings">User rankings</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/">Your wall</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/app/addpost">Add post</a>
          </li>
        </c:if>
      </ul>
      <form class="form-inline my-2 my-lg-0" action="<%=request.getContextPath()%>/login" method="post">
        <c:choose>
          <c:when test="${user.getLogged() eq 1}">
            <ul class="nav navbar-nav ml-auto">
              <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/app/send">Send message</a></li>
                <%--MESSAGES--%>
              <li class="nav-item dropdown d-md-down-none">
                <a class="nav-link" data-toggle="dropdown" href="<%=request.getContextPath()%>/app/messages" role="button" aria-haspopup="true" aria-expanded="false">
                  <i class="icon-envelope-letter"></i>
                  <span class="badge badge-pill badge-info">${unread}</span>
                </a>
                <div class="dropdown-menu dropdown-menu-right dropdown-menu-lg">
                  <div class="dropdown-header text-center">
                    <strong>${unread} unread messages</strong>
                  </div>
                  <a class="dropdown-item text-center" href="<%=request.getContextPath()%>/app/send">
                    <strong>View messages</strong>
                  </a>
                  <a class="dropdown-item text-center" href="<%=request.getContextPath()%>/app/send">
                    <strong>Send message</strong>
                  </a>
                </div>
              </li>
                <%--USER--%>
              <li class="nav-item dropdown d-md-down-none">
                <a class="nav-link" data-toggle="dropdown" href="<%=request.getContextPath()%>/messages" role="button" aria-haspopup="true" aria-expanded="false">
                  <i class="icon-user"></i>
                </a>
                <div class="dropdown-menu dropdown-menu-right dropdown-menu-lg">
                  <div class="dropdown-header text-center">
                    <strong>${user.getUsername()} <i class="icon-trophy"></i>${user.getPoints()}</strong>
                  </div>
                  <a class="dropdown-item text-center" href="<%=request.getContextPath()%>/app/userprofile">
                    User profile
                  </a>
                  <a class="dropdown-item text-center" href="#">
                    Review your votes
                  </a>
                  <% pageContext.setAttribute("admin", Role.ADMIN); %>
                  <c:if test="${user.getRoles().contains(admin)}">
                    <a class="dropdown-item text-center" href="<%=request.getContextPath()%>/app/logs">
                      Logs
                    </a>
                  </c:if>
                  <a class="dropdown-item text-center" href="<%=request.getContextPath()%>/login?logout"><strong>logout</strong></a>
                </div>
              </li>
            </ul>
          </c:when>
          <c:otherwise>
            <input class="form-control mr-sm-2" type="email" placeholder="Email" name="username">
            <input class="form-control mr-sm-2" type="password" placeholder="Password" name="password">
            <button class="btn btn-primary my-2 my-sm-0" type="submit">Login</button>
            <ul class="nav navbar-nav ml-auto">
              <li class="nav-item"><a href="<%=request.getContextPath()%>/register" class="nav-link">Register</a></li>
            </ul>
          </c:otherwise>
        </c:choose>
      </form>
    </div>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
  </div>
</header>
