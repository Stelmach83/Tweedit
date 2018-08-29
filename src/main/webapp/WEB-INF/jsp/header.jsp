<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--<nav class="navbar navbar-expand-lg navbar-dark bg-dark">--%>
<header class="app-header navbar navbar-expand-lg" style="background-color: #f4f4f4;">
    <div class="container">
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <c:if test="${not empty user}">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">All categories</a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <c:forEach var="cat" items="${categories}">
                            <a class="dropdown-item" href="#">${cat.getName()}</a>
                            </c:forEach>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<%=request.getContextPath()%>/app/wall">Your wall</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="<%=request.getContextPath()%>/app/post">Add Post</a>
                    </li>

                    <%--<li class="nav-item">--%>
                    <%--<a class="nav-link" href="#">Go to category</a>--%>
                    <%--</li>--%>


                    <%--<c:if test="${not empty user}">--%>
                    <%--<c:if test="${unread ne 0}">--%>
                    <%--<li class="nav-item">--%>
                    <%--<a class="nav-link" href="<%=request.getContextPath()%>/messages">You have ${unread} messages</a>--%>
                    <%--</li>--%>
                    <%--</c:if>--%>
                    <%--<c:if test="${unread eq 0}">--%>
                    <%--<li class="nav-item">--%>
                    <%--<a class="nav-link" href="<%=request.getContextPath()%>/messages">Your messages</a>--%>
                    <%--</li>--%>
                    <%--</c:if>--%>
                    <%--</c:if>--%>

                    <%--<li class="nav-item">--%>
                    <%--<a class="nav-link disabled" href="#">Disabled</a>--%>
                    <%--</li>--%>
                </c:if>
            </ul>

            <form class="form-inline my-2 my-lg-0" action="<%=request.getContextPath()%>/login" method="post">
                <c:choose>
                    <c:when test="${not empty user}">
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
                                        <strong>You have 4 messages</strong>
                                    </div>
                                    <a class="dropdown-item" href="#">
                                        <div class="message">
                                            <div class="py-3 mr-3 float-left">
                                                <div class="avatar">
                                                    <i class="fa fa-check"></i>
                                                    <span class="avatar-status badge-success"></span>
                                                </div>
                                            </div>
                                            <div>
                                                <small class="text-muted">John Doe</small>
                                                <small class="text-muted float-right mt-1">Just now</small>
                                            </div>
                                            <div class="text-truncate font-weight-bold">
                                                <span class="fa fa-exclamation text-danger"></span> Important message
                                            </div>
                                            <div class="small text-muted text-truncate">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt...</div>
                                        </div>
                                    </a>
                                    <a class="dropdown-item" href="#">
                                        <div class="message">
                                            <div class="py-3 mr-3 float-left">
                                                <div class="avatar">
                                                    <i class="fa fa-check"></i>
                                                    <span class="avatar-status badge-warning"></span>
                                                </div>
                                            </div>
                                            <div>
                                                <small class="text-muted">John Doe</small>
                                                <small class="text-muted float-right mt-1">5 minutes ago</small>
                                            </div>
                                            <div class="text-truncate font-weight-bold">Lorem ipsum dolor sit amet</div>
                                            <div class="small text-muted text-truncate">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt...</div>
                                        </div>
                                    </a>
                                    <a class="dropdown-item" href="#">
                                        <div class="message">
                                            <div class="py-3 mr-3 float-left">
                                                <div class="avatar">
                                                    <i class="fa fa-check"></i>
                                                    <span class="avatar-status badge-danger"></span>
                                                </div>
                                            </div>
                                            <div>
                                                <small class="text-muted">John Doe</small>
                                                <small class="text-muted float-right mt-1">1:52 PM</small>
                                            </div>
                                            <div class="text-truncate font-weight-bold">Lorem ipsum dolor sit amet</div>
                                            <div class="small text-muted text-truncate">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt...</div>
                                        </div>
                                    </a>
                                    <a class="dropdown-item" href="#">
                                        <div class="message">
                                            <div class="py-3 mr-3 float-left">
                                                <div class="avatar">
                                                    <i class="fa fa-check"></i>
                                                    <span class="avatar-status badge-info"></span>
                                                </div>
                                            </div>
                                            <div>
                                                <small class="text-muted">John Doe</small>
                                                <small class="text-muted float-right mt-1">4:03 PM</small>
                                            </div>
                                            <div class="text-truncate font-weight-bold">Lorem ipsum dolor sit amet</div>
                                            <div class="small text-muted text-truncate">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt...</div>
                                        </div>
                                    </a>
                                    <a class="dropdown-item text-center" href="#">
                                        <strong>View all messages</strong>
                                    </a>
                                </div>
                            </li>
                                <%--USER--%>
                            <li class="nav-item dropdown d-md-down-none">
                                <a class="nav-link" data-toggle="dropdown" href="<%=request.getContextPath()%>/messages" role="button" aria-haspopup="true" aria-expanded="false">
                                    <i class="icon-user"></i>
                                    <span class="badge badge-pill badge-success">${user.getPoints()}</span>
                                </a>
                                <div class="dropdown-menu dropdown-menu-right dropdown-menu-lg">
                                    <div class="dropdown-header text-center">
                                        <strong>You have 4 messages</strong>
                                    </div>
                                    <a class="dropdown-item" href="#">
                                        <div class="message">
                                            <div class="py-3 mr-3 float-left">
                                                <div class="avatar">
                                                    <i class="fa fa-check"></i>
                                                    <span class="avatar-status badge-success"></span>
                                                </div>
                                            </div>
                                            <div>
                                                <small class="text-muted">John Doe</small>
                                                <small class="text-muted float-right mt-1">Just now</small>
                                            </div>
                                            <div class="text-truncate font-weight-bold">
                                                <span class="fa fa-exclamation text-danger"></span> Important message
                                            </div>
                                            <div class="small text-muted text-truncate">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt...</div>
                                        </div>
                                    </a>
                                    <a class="dropdown-item" href="#">
                                        <div class="message">
                                            <div class="py-3 mr-3 float-left">
                                                <div class="avatar">
                                                    <i class="fa fa-check"></i>
                                                    <span class="avatar-status badge-warning"></span>
                                                </div>
                                            </div>
                                            <div>
                                                <small class="text-muted">John Doe</small>
                                                <small class="text-muted float-right mt-1">5 minutes ago</small>
                                            </div>
                                            <div class="text-truncate font-weight-bold">Lorem ipsum dolor sit amet</div>
                                            <div class="small text-muted text-truncate">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt...</div>
                                        </div>
                                    </a>
                                    <a class="dropdown-item" href="#">
                                        <div class="message">
                                            <div class="py-3 mr-3 float-left">
                                                <div class="avatar">
                                                    <i class="fa fa-check"></i>
                                                    <span class="avatar-status badge-danger"></span>
                                                </div>
                                            </div>
                                            <div>
                                                <small class="text-muted">John Doe</small>
                                                <small class="text-muted float-right mt-1">1:52 PM</small>
                                            </div>
                                            <div class="text-truncate font-weight-bold">Lorem ipsum dolor sit amet</div>
                                            <div class="small text-muted text-truncate">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt...</div>
                                        </div>
                                    </a>
                                    <a class="dropdown-item" href="#">
                                        <div class="message">
                                            <div class="py-3 mr-3 float-left">
                                                <div class="avatar">
                                                    <i class="fa fa-check"></i>
                                                    <span class="avatar-status badge-info"></span>
                                                </div>
                                            </div>
                                            <div>
                                                <small class="text-muted">John Doe</small>
                                                <small class="text-muted float-right mt-1">4:03 PM</small>
                                            </div>
                                            <div class="text-truncate font-weight-bold">Lorem ipsum dolor sit amet</div>
                                            <div class="small text-muted text-truncate">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt...</div>
                                        </div>
                                    </a>
                                    <a class="dropdown-item text-center" href="#">
                                        <strong>View all messages</strong>
                                    </a>
                                </div>
                            </li>
                            <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/login?logout">${user.getUsername()} logout</a></li>
                        </ul>
                    </c:when>
                    <c:otherwise>
                        <input class="form-control mr-sm-2" type="email" placeholder="Email" name="username">
                        <input class="form-control mr-sm-2" type="password" placeholder="Password" name="password">
                        <button class="btn btn-primary my-2 my-sm-0" type="submit">Login</button>
                        <ul class="nav navbar-nav ml-auto">
                            <li class="nav-item"><a href="#" class="nav-link">Register</a></li>
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
<%--</nav>--%>
