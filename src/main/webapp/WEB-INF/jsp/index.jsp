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
                      class="icon-trophy"></i>${post.getUser().getPoints()}, created: <fmt:formatDate pattern="dd-MM-yyyy HH:mm"
                                                                                                      value="${post.getCreated()}"/></cite>
              </footer>
              <div>
                <c:forEach items="${comments}" var="comment">
                  <div class="row">
                    <div class="col-sm-1"></div>
                    <div class="col-sm-10">${comment.getCommentText()}</div>
                    <div class="col-sm-1"></div>
                  </div>
                </c:forEach>
                <div class="row">


                  <table class="table table-responsive-sm table-sm" id="commenttable">
                    <thead>
                    <tr>
                      <th>Comment</th>
                      <th>Date</th>
                      <th>User</th>
                      <th>Points</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                      <td>ASgdr gsgser gfxg sdfgsgsefg sedfew afaewf dtgr.</td>
                      <td>2012/01/01</td>
                      <td>Lentus</td>
                      <td>
                          <%--<c:if test="${pdto.getVote().getVoted() eq 2}">--%>
                          <%--<i class="fas fa-arrow-up greenarrow"></i>--%>
                          <%--</c:if>--%>
                          <%--<c:if test="${pdto.getVote().getVoted() eq 1}">--%>

                          <%--</c:if>--%>
                          <%--<c:if test="${pdto.getVote() eq null}">--%>
                        <a href="#" id="arrowup" data-pid="placeholder" data-contextpath="<%=request.getContextPath()%>"><i class="fas fa-arrow-up greyarrow"></i></a>
                        <span class="badge badge-pill badge-secondary" id="commentpointsbadge" data-points="placeholder">345</span>
                        <a href="#" id="arrowdown" data-pid="placeholder" data-contextpath="<%=request.getContextPath()%>"><i class="fas fa-arrow-down greyarrow"></i></a>
                          <%--</c:if>--%>
                      </td>
                    </tr>
                    <tr>
                      <td>Gsdergfes fesaf awf rdsg segf gfxg cfxg segfe sgxfg serg ge.</td>
                      <td>2014/01/01</td>
                      <td>Pleb</td>
                      <td>
                        <a href="#" id="arrowup1" data-pid="placeholder" data-contextpath="<%=request.getContextPath()%>"><i class="fas fa-arrow-up greyarrow"></i></a>
                        <span class="badge badge-pill badge-secondary" id="commentpointsbadge1" data-points="placeholder">645</span>
                        <a href="#" id="arrowdown1" data-pid="placeholder" data-contextpath="<%=request.getContextPath()%>"><i class="fas fa-arrow-down greyarrow"></i></a>
                      </td>
                    </tr>
                    <tr>
                      <td>Oserges sef sef thfxdgser fgsezf SEF drzghrdgfdzgzxdffg zg drgdr sefg.</td>
                      <td>2012/02/01</td>
                      <td>Greg</td>
                      <td>
                        <a href="#" id="arrowup2" data-pid="placeholder" data-contextpath="<%=request.getContextPath()%>"><i class="fas fa-arrow-up greyarrow"></i></a>
                        <span class="badge badge-pill badge-secondary" id="commentpointsbadge2" data-points="placeholder">12</span>
                        <a href="#" id="arrowdown2" data-pid="placeholder" data-contextpath="<%=request.getContextPath()%>"><i class="fas fa-arrow-down greyarrow"></i></a>
                      </td>
                    </tr>
                    <tr>
                      <td>Asef fsedrgdg esfesf aesfsjnfes fs.</td>
                      <td>2012/03/01</td>
                      <td>Member</td>
                      <td>
                        <a href="#" id="arrowup3" data-pid="placeholder" data-contextpath="<%=request.getContextPath()%>"><i class="fas fa-arrow-up greyarrow"></i></a>
                        <span class="badge badge-pill badge-secondary" id="commentpointsbadge3" data-points="placeholder">6571</span>
                        <a href="#" id="arrowdown3" data-pid="placeholder" data-contextpath="<%=request.getContextPath()%>"><i class="fas fa-arrow-down greyarrow"></i></a>
                      </td>
                    </tr>
                    <tr>
                      <td>Ksejgfnesijkfn eskjfnakwnfkajw nkrsjnfesf.</td>
                      <td>2012/01/21</td>
                      <td>Staff</td>
                      <td>
                        <a href="#" id="arrowup4" data-pid="placeholder" data-contextpath="<%=request.getContextPath()%>"><i class="fas fa-arrow-up greyarrow"></i></a>
                        <span class="badge badge-pill badge-secondary" id="commentpointsbadge4" data-points="placeholder">4</span>
                        <a href="#" id="arrowdown4" data-pid="placeholder" data-contextpath="<%=request.getContextPath()%>"><i class="fas fa-arrow-down greyarrow"></i></a>
                      </td>
                    </tr>
                    </tbody>
                  </table>
                  <c:if test="${not empty user && empty addcomment}">
                    <div class="float-right"><a href="<%=request.getContextPath()%>/app/addcomment/${post.getId()}" class="nav-link">add comment</a></div>
                  </c:if>
                  <c:if test="${addcomment eq post.getId()}">
                    <form:form method="post" modelAttribute="comment">
                      <form>
                        <input type="hidden" name="user.id" value="${user.getId()}">
                        <input type="hidden" name="post.id" value="${post.getId()}">
                        <div class="form-group">
                          <label for="exampleFormControlInput1a">Text</label>
                          <form:textarea class="form-control" rows="3" id="exampleFormControlInput1a"
                                         placeholder="title" path="commentText"/>
                          <form:errors path="commentText" cssClass="error"/>
                        </div>
                        <input type="submit" value="Send">
                      </form>
                    </form:form>
                  </c:if>

                    <%--</c:if>--%>
                    <%--<c:if test="${empty user}">--%>
                    <%--&lt;%&ndash;<h6>${post.getComments().size()}</h6>&ndash;%&gt;--%>
                    <%--</c:if>--%>
                    <%--</div>--%>
                    <%--<div class="col-sm-12 col-md mb-sm-2 mb-0">--%>
                    <%--&lt;%&ndash;<h6>Comments</h6>&ndash;%&gt;--%>
                    <%--</div>--%>
                    <%--<div class="col-sm-12 col-md mb-sm-2 mb-0">--%>
                    <%--&lt;%&ndash;<h6>${post.getCategory().getName()}</h6>&ndash;%&gt;--%>
                    <%--</div>--%>
                    <%--<div class="col-sm-12 col-md mb-sm-2 mb-0">--%>
                    <%--&lt;%&ndash;<h6>${post.getCategory().getName()}</h6>&ndash;%&gt;--%>
                    <%--</div>--%>
                    <%--<div class="col-sm-12 col-md mb-sm-2 mb-0">--%>
                    <%--&lt;%&ndash;<h6>${post.getComments().size()}</h6>&ndash;%&gt;--%>

                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <br>
    </c:forEach>

    <div class="chart-wrapper" style="height:300px;margin-top:40px;">
      <div class="chartjs-size-monitor" style="position: absolute; left: 0px; top: 0px; right: 0px; bottom: 0px; overflow: hidden; pointer-events: none; visibility: hidden; z-index: -1;">
        <div class="chartjs-size-monitor-expand" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;">
          <div style="position:absolute;width:1000000px;height:1000000px;left:0;top:0"></div>
        </div>
        <div class="chartjs-size-monitor-shrink" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;">
          <div style="position:absolute;width:200%;height:200%;left:0; top:0"></div>
        </div>
      </div>
      <canvas id="main-chart" class="chart chartjs-render-monitor" height="600" width="3204" style="display: block; height: 300px; width: 1602px;"></canvas>
      <div id="main-chart-tooltip" class="chartjs-tooltip center" style="opacity: 0; left: 114.016px; top: 181.48px;">
        <div class="tooltip-header">
          <div class="tooltip-header-item">T</div>
        </div>
        <div class="tooltip-body">
          <div class="tooltip-body-item"><span class="tooltip-body-item-color" style="background-color: rgb(255, 255, 255);"></span><span class="tooltip-body-item-label">My First dataset</span><span
                  class="tooltip-body-item-value">180</span></div>
          <div class="tooltip-body-item"><span class="tooltip-body-item-color" style="background-color: rgb(255, 255, 255);"></span><span class="tooltip-body-item-label">My Second dataset</span><span
                  class="tooltip-body-item-value">97</span></div>
          <div class="tooltip-body-item"><span class="tooltip-body-item-color" style="background-color: rgb(255, 255, 255);"></span><span class="tooltip-body-item-label">My Third dataset</span><span
                  class="tooltip-body-item-value">65</span></div>
        </div>
      </div>
    </div>
  </div>
  <div class="card-footer">
    <div class="row text-center">
      <div class="col-sm-12 col-md mb-sm-2 mb-0">
        <div class="text-muted">Visits</div>
        <strong>29.703 Users (40%)</strong>
        <div class="progress progress-xs mt-2">
          <div class="progress-bar bg-success" role="progressbar" style="width: 40%" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"></div>
        </div>
      </div>
      <div class="col-sm-12 col-md mb-sm-2 mb-0">
        <div class="text-muted">Unique</div>
        <strong>24.093 Users (20%)</strong>
        <div class="progress progress-xs mt-2">
          <div class="progress-bar bg-info" role="progressbar" style="width: 20%" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100"></div>
        </div>
      </div>
      <div class="col-sm-12 col-md mb-sm-2 mb-0">
        <div class="text-muted">Pageviews</div>
        <strong>78.706 Views (60%)</strong>
        <div class="progress progress-xs mt-2">
          <div class="progress-bar bg-warning" role="progressbar" style="width: 60%" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"></div>
        </div>
      </div>
      <div class="col-sm-12 col-md mb-sm-2 mb-0">
        <div class="text-muted">New Users</div>
        <strong>22.123 Users (80%)</strong>
        <div class="progress progress-xs mt-2">
          <div class="progress-bar bg-danger" role="progressbar" style="width: 80%" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100"></div>
        </div>
      </div>
      <div class="col-sm-12 col-md mb-sm-2 mb-0">
        <div class="text-muted">Bounce Rate</div>
        <strong>40.15%</strong>
        <div class="progress progress-xs mt-2">
          <div class="progress-bar" role="progressbar" style="width: 40%" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"></div>
        </div>
      </div>
    </div>
  </div>
</div>