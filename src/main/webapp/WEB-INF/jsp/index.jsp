<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<br>
<div class="card">
  <div class="card-body" style="background-color: #f4f4f4;">
    <div class="row">
      <div class="col-sm-5">
        <h4 class="card-title mb-0">Your wall</h4>
        <div class="small text-muted"><fmt:formatDate pattern="dd-MM-yyyy" value="${now}"/></div>
      </div>
    </div>
    <br>

    <c:forEach var="pdto" items="${postdtos}">
      <c:set var="post" value="${pdto.getPost()}"></c:set>
      <c:set var="postid" value="${post.getId()}"></c:set>
      <c:set var="comments" value="${post.getComments()}"></c:set>
      <div class="row">
        <div class="col-sm-12 col-md-12">
          <div class="card">
            <div class="card-header">
              <div class="float-left">
                  ${post.getTitle()}
                <em><i class="fa fa-at"></i> ${post.getCategory().getName()}</em>
              </div>
              <div class="float-right">

                <c:if test="${not empty user}">
                  <c:if test="${pdto.getVote().getVoted() eq 2}">
                    <i class="fas fa-arrow-up greenarrow"></i>
                  </c:if>
                  <c:if test="${pdto.getVote().getVoted() eq 1}">
                    <%--<i class="fas fa-arrow-up greyarrow"></i>--%>
                  </c:if>
                  <c:if test="${pdto.getVote() eq null}">
                    <a href="#" id="arrowup" data-pid="${postid}" data-contextpath="<%=request.getContextPath()%>"><i class="fas fa-arrow-up greyarrow"></i></a>
                  </c:if>
                </c:if>

                <c:if test="${empty user}">
                  <span class="badge badge-pill badge-secondary" id="pointsbadge" data-points="${post.getPoints()}">${post.getPoints()}</span>
                </c:if>
                <c:if test="${not empty user}">
                  <c:if test="${pdto.getVote().getVoted() eq 2}">
                    <span class="badge badge-pill badge-success" id="pointsbadge" data-points="${post.getPoints()}">${post.getPoints()}</span>
                  </c:if>
                  <c:if test="${pdto.getVote().getVoted() eq 1}">
                    <span class="badge badge-pill badge-danger" id="pointsbadge" data-points="${post.getPoints()}">${post.getPoints()}</span>
                  </c:if>
                  <c:if test="${pdto.getVote() eq null}">
                    <span class="badge badge-pill badge-secondary" id="pointsbadge" data-points="${post.getPoints()}">${post.getPoints()}</span>
                  </c:if>
                </c:if>

                <c:if test="${not empty user}">
                  <c:if test="${pdto.getVote().getVoted() eq 1}">
                    <i class="fas fa-arrow-down redarrow"></i>
                  </c:if>
                  <c:if test="${pdto.getVote().getVoted() eq 2}">
                    <%--<i class="fas fa-arrow-down greyarrow"></i>--%>
                  </c:if>
                  <c:if test="${pdto.getVote() eq null}">
                    <a href="#" id="arrowdown" data-pid="${postid}" data-contextpath="<%=request.getContextPath()%>"><i class="fas fa-arrow-down greyarrow"></i></a>
                  </c:if>

                </c:if>

              </div>
            </div>
            <div class="card-body"><p>${post.getText()}</p>
              <footer class="blockquote-footer">
                by <cite title="Show ${post.getUser().getUsername()}"><a href="<%=request.getContextPath()%>/app/user/${post.getUser().getId()}">${post.getUser().getUsername()}</a> <i
                      class="icon-trophy"></i>${post.getUser().getPoints()}, created: <fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${post.getCreated()}"/></cite>
              </footer>
              <div>
                <div class="row">
                  <table class="table table-responsive-sm table-sm" id="commenttable">
                    <col style="width:67%">
                    <col style="width:11%">
                    <col style="width:11%">
                    <col style="width:11%">
                    <thead>
                    <tr>
                      <th>Comment</th>
                      <th>Date</th>
                      <th>User</th>
                      <th>Points</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${comments}" var="comment">
                      <tr>
                        <td class="commentstext">${comment.getCommentText()}</td>
                        <td class="commentsdata"><fmt:formatDate pattern="dd-MMM HH:mm" value="${comment.getDate()}"/></td>
                        <td class="commentsdata">${comment.getUser().getUsername()}</td>
                        <td class="commentsdata">
                          <a href="#" data-pid="placeholder" data-contextpath="<%=request.getContextPath()%>"><i class="fas fa-arrow-up greyarrow"></i></a>
                          <span class="badge badge-pill badge-secondary" id="commentpointsbadge${comment.getId()}" data-points="placeholder">${comment.getPoints()}</span>
                          <a href="#" data-pid="placeholder" data-contextpath="<%=request.getContextPath()%>"><i class="fas fa-arrow-down greyarrow"></i></a>
                        </td>
                      </tr>
                    </c:forEach>
                    </tbody>
                  </table>
                </div>
                <div class="row">
                  <c:if test="${not empty user && empty addcomment}">
                    <a href="<%=request.getContextPath()%>/app/addcomment/${post.getId()}" class="nav-link">add comment</a>
                  </c:if>
                </div>

                <div class="col-sm-12">
                  <c:if test="${addcomment eq post.getId()}">
                    <form:form method="post" modelAttribute="comment">
                      <form>
                        <input type="hidden" name="user.id" value="${user.getId()}">
                        <input type="hidden" name="post.id" value="${post.getId()}">
                        <div class="form-group">
                          <label for="exampleFormControlInput1a">Add comment</label>
                          <form:textarea class="form-control" rows="3" id="exampleFormControlInput1a" placeholder="Insert comment content" path="commentText"/>
                          <form:errors path="commentText" cssClass="error"/>
                        </div>
                        <input type="submit" value="Send">
                      </form>
                    </form:form>
                  </c:if>
                </div>

              </div>
            </div>
          </div>
        </div>
      </div>
      <br>
    </c:forEach>


  </div>
</div>