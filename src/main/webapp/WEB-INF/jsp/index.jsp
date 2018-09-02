<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <div class="row">
                <div class="col-sm-12 col-md-12">
                    <div class="card">
                        <div class="card-header">  <!--SHOW CATEGORY-->
                            <div class="float-left">
                                <i class="fa fa-check"></i>${post.getTitle()} <fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${post.getCreated()}"/>
                            </div>
                            <div class="float-right">

                                <c:if test="${not empty user}">
                                    <c:if test="${pdto.getVote().getVoted() eq 2}">
                                        <a href="#" id="arrowup" data-pid="${postid}"><i class="fas fa-arrow-up greenarrow"></i></a>
                                    </c:if>
                                    <c:if test="${pdto.getVote() eq null}">
                                        <a href="#" id="arrowup" data-pid="${postid}"><i class="fas fa-arrow-up greyarrow"></i></a>
                                    </c:if>
                                </c:if>

                                <span class="badge badge-pill badge-secondary">${post.getPoints()}</span>

                                <c:if test="${not empty user}">
                                    <a href="#" id="arrowdown${post.getId()}"><i class="fas fa-arrow-down greyarrow"></i></a>
                                </c:if>

                            </div>
                        </div>
                        <div class="card-body">
                                ${post.getText()}
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