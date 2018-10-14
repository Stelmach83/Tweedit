<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<br>
<div class="card" id="maincard">
  <div class="card-body" style="background-color: #f4f4f4;">
    <c:if test="${not empty user}">
      <c:choose>
        <c:when test="${postdtos.size() eq 0}">
          <div class="row">
            <div class="col-sm-12">
              <c:choose>
                <c:when test="${noposts}">
                  <h4 class="card-title mb-0">Please customise your wall by following a category and/or user.</h4>
                </c:when>
                <c:otherwise>
                  <h4 class="card-title mb-0">There are no posts in this category.</h4
                </c:otherwise>
              </c:choose>
            </div>
          </div>
        </c:when>
        <c:otherwise>
          <div class="row">
            <div class="col-sm-12">
              <c:choose>
                <c:when test="${catview ne null}">
                  <h4 class="card-title mb-0">${catview.getName()}</h4>
                </c:when>
                <c:otherwise>
                  <h4 class="card-title mb-0">Your wall</h4>
                </c:otherwise>
              </c:choose>
              <div class="small text-muted"><fmt:formatDate pattern="dd-MM-yyyy" value="${now}"/></div>
            </div>
          </div>
          <br>
          <c:forEach var="pdto" items="${postdtos}">
            <c:set var="post" value="${pdto.getPost()}"></c:set>
            <c:set var="postid" value="${post.getId()}"></c:set>
            <c:set var="commentsdtos" value="${pdto.getComments()}"></c:set>
            <div class="row">
              <div class="col-sm-12 col-md-12">
                <div class="card">
                  <div class="card-header">
                    <div class="float-left">
                        ${post.getTitle()}
                      <em><i class="fa fa-at"></i> ${post.getCategory().getName()}</em>
                    </div>
                    <div class="float-right">
                        <%--
                              VOTING ON POSTS START
                        --%>
                      <c:if test="${not empty user}">
                        <c:if test="${pdto.getVote().getVoted() eq 2}">
                          <i class="fas fa-arrow-up greenarrow"></i>
                        </c:if>
                        <c:if test="${pdto.getVote().getVoted() eq 1}">
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
                        </c:if>
                        <c:if test="${pdto.getVote() eq null}">
                          <a href="#" id="arrowdown" data-pid="${postid}" data-contextpath="<%=request.getContextPath()%>"><i class="fas fa-arrow-down greyarrow"></i></a>
                        </c:if>
                      </c:if>
                        <%--
                              VOTING ON POSTS END
                        --%>
                    </div>
                  </div>
                  <div class="card-body"><p>${post.getText()}</p>
                    <footer class="blockquote-footer">
                      by <cite title="Show ${post.getUser().getUsername()}"><a href="<%=request.getContextPath()%>/app/user/${post.getUser().getId()}">${post.getUser().getUsername()}</a> <i
                            class="icon-trophy"></i>${post.getUser().getPoints()}, created: <fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${post.getCreated()}"/></cite>
                    </footer>
                    <div>
                      <div class="row">
                        <c:if test="${commentsdtos.size() ne 0}">
                          <table class="table table-responsive-sm table-sm" id="commenttable">
                            <col style="width:67%">
                            <col style="width:11%">
                            <col style="width:9%">
                            <col style="width:13%">
                            <thead>
                            <tr>
                              <th class="commentstitle">Comment</th>
                              <th class="commentstitle" style="text-align: center">Posted on</th>
                              <th class="commentstitle" style="text-align: center">Posted by</th>
                              <th class="commentstitle" style="text-align: center">Points</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${commentsdtos}" var="cdto">
                              <c:set var="comment" value="${cdto.getComment()}"></c:set>
                              <tr>
                                <td class="commentstext">${comment.getCommentText()}</td>
                                <td class="commentsdata" style="text-align: center"><fmt:formatDate pattern="dd-MMM HH:mm" value="${comment.getDate()}"/></td>
                                <td class="commentsdata" style="text-align: center">${comment.getUser().getUsername()}</td>
                                <td class="commentsdata" style="text-align: center">
                                    <%--
                                          VOTING ON COMMENTS START
                                    --%>
                                  <c:if test="${not empty user}">
                                    <c:if test="${cdto.getVote().getVoted() eq 2}">
                                      <i class="fas fa-arrow-up greenarrow"></i>
                                    </c:if>
                                    <c:if test="${cdto.getVote().getVoted() eq 1}">
                                    </c:if>
                                    <c:if test="${cdto.getVote() eq null}">
                                      <a href="#" class="arrowupcomment" data-pid="${comment.getId()}" data-contextpath="<%=request.getContextPath()%>"><i class="fas fa-arrow-up greyarrow"></i></a>
                                    </c:if>
                                  </c:if>

                                  <c:if test="${empty user}">
                                    <span class="badge badge-pill badge-secondary" id="commentpointsbadge" data-points="placeholder">${comment.getPoints()}</span>
                                  </c:if>
                                  <c:if test="${not empty user}">
                                    <c:if test="${cdto.getVote().getVoted() eq 2}">
                                      <span class="badge badge-pill badge-success" id="commentpointsbadge" data-points="placeholder">${comment.getPoints()}</span>
                                    </c:if>
                                    <c:if test="${cdto.getVote().getVoted() eq 1}">
                                      <span class="badge badge-pill badge-danger" id="commentpointsbadge" data-points="placeholder">${comment.getPoints()}</span>
                                    </c:if>
                                    <c:if test="${cdto.getVote() eq null}">
                                      <span class="badge badge-pill badge-secondary" id="commentpointsbadge" data-points="placeholder">${comment.getPoints()}</span>
                                    </c:if>
                                  </c:if>

                                  <c:if test="${not empty user}">
                                    <c:if test="${cdto.getVote().getVoted() eq 1}">
                                      <i class="fas fa-arrow-down redarrow"></i>
                                    </c:if>
                                    <c:if test="${cdto.getVote().getVoted() eq 2}">
                                    </c:if>
                                    <c:if test="${cdto.getVote() eq null}">
                                      <a href="#" class="arrowdowncomment" data-pid="${comment.getId()}" data-contextpath="<%=request.getContextPath()%>"><i class="fas fa-arrow-down greyarrow"></i></a>
                                    </c:if>
                                  </c:if>
                                    <%--
                                      VOTING ON COMMENTS END
                                    --%>
                                </td>
                              </tr>
                            </c:forEach>
                            <c:if test="${not empty user && empty addcomment}">
                              <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <c:choose>
                                  <c:when test="${user.getCategories().contains(post.getCategory())}">
                                    <td class="addcomment">
                                      <a href="<%=request.getContextPath()%>/app/addcomment/${post.getId()}" class="nav-link">add comment</a>
                                    </td>
                                  </c:when>
                                  <c:otherwise>
                                    <td class="addcomment"><a href="<%=request.getContextPath()%>/app/followcat/${post.getCategory().getId()}" class="nav-link">follow category</a></td>
                                  </c:otherwise>
                                </c:choose>
                              </tr>
                            </c:if>
                            </tbody>
                          </table>
                        </c:if>
                        <c:if test="${commentsdtos.size() eq 0}">
                          <c:if test="${not empty user && empty addcomment}">
                            <table class="table table-responsive-sm table-sm" id="commenttable" style="border: 0px; margin-top: 0px">
                              <col style="width:67%; border: 0px; margin-top: 0px">
                              <col style="width:11%; border: 0px; margin-top: 0px">
                              <col style="width:9%; border: 0px; margin-top: 0px">
                              <col style="width:13%; border: 0px; margin-top: 0px">
                              <tr style="border: 0px; margin-top: 0px">
                                <th class="commentstitle" style="border: 0px; margin-top: 0px"></th>
                                <th class="commentstitle" style="text-align: center; border: 0px; margin-top: 0px"></th>
                                <th class="commentstitle" style="text-align: center; border: 0px; margin-top: 0px"></th>
                                <th class="commentstitle" style="text-align: center; border: 0px; margin-top: 0px"></th>
                              </tr>
                              <tr style="border: 0px; margin-top: 0px">
                                <td style="border: 0px; margin-top: 0px"></td>
                                <td style="border: 0px; margin-top: 0px"></td>
                                <td style="border: 0px; margin-top: 0px"></td>
                                <c:choose>
                                  <c:when test="${user.getCategories().contains(post.getCategory())}">
                                    <td class="addcomment">
                                      <a href="<%=request.getContextPath()%>/app/addcomment/${post.getId()}" class="nav-link">add comment</a>
                                    </td>
                                  </c:when>
                                  <c:otherwise>
                                    <td class="addcomment"><a href="<%=request.getContextPath()%>/app/followcat/${post.getCategory().getId()}" class="nav-link">follow category</a></td>
                                  </c:otherwise>
                                </c:choose>
                              </tr>
                            </table>
                          </c:if>
                        </c:if>
                      </div>
                      <div class="col-sm-12">
                        <c:if test="${addcomment eq post.getId()}">
                          <br>
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
        </c:otherwise>
      </c:choose>
    </c:if>
    <c:if test="${empty user}">
      <div class="row">
        <div class="col-sm-5">
          <h4 class="card-title mb-0">Log in or register.</h4>
        </div>
      </div>
    </c:if>
  </div>
</div>