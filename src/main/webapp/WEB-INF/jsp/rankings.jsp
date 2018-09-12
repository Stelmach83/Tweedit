<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<br>
<div class="card" id="maincard">
  <div class="card-body" style="background-color: #f4f4f4;">
    <div class="row">
      <div class="col-sm-5">
        <h4 class="card-title mb-0">User rankings</h4>
        <div class="small text-muted"><fmt:formatDate pattern="dd-MM-yyyy" value="${now}"/></div>
      </div>
    </div>
    <br>
    <div class="row">
      <div class="col-sm-12 col-md-12">
        <div class="card">
          <div class="card-header">
            <div class="float-left">
              Top 10 users - Points for posts and comments
            </div>
            <div class="float-right">

            </div>
          </div>
          <div class="card-body">
            <br>
            <table class="table table-responsive-sm table-sm center" style="width: 90%;padding-left: 10px;padding-right: 10px">
              <col style="width:10%">
              <col style="width:14%">
              <col style="width:76%">
              <thead>
              <tr>
                <th style="text-align: left">Rank</th>
                <th style="text-align: center">Username</th>
                <th style="text-align: right">Points</th>
              </tr>
              </thead>
              <tbody>

              <c:forEach var="u" items="${rankings}" varStatus="loop">
                <c:if test="${loop.index + 1 eq 1}">
                  <tr style="background-color: #ffdc73">
                    <td style="text-align: left">Rank ${loop.index + 1}</td>
                    <td style="text-align: center">${u.getUsername()}</td>
                    <td style="text-align: right">${u.getPoints()} <i class="icon-trophy"></i></td>
                  </tr>
                </c:if>
                <c:if test="${loop.index + 1 eq 2}">
                  <tr style="background-color: #dadada">
                    <td style="text-align: left">Rank ${loop.index + 1}</td>
                    <td style="text-align: center">${u.getUsername()}</td>
                    <td style="text-align: right">${u.getPoints()} <i class="icon-trophy"></i></td>
                  </tr>
                </c:if>
                <c:if test="${loop.index + 1 eq 3}">
                  <tr style="background-color: #e8c4a0">
                    <td style="text-align: left">Rank ${loop.index + 1}</td>
                    <td style="text-align: center">${u.getUsername()}</td>
                    <td style="text-align: right">${u.getPoints()} <i class="icon-trophy"></i></td>
                  </tr>
                </c:if>
                <c:if test="${loop.index + 1 ne 3 && loop.index + 1 ne 1 && loop.index + 1 ne 2}">
                  <tr>
                    <td style="text-align: left">Rank ${loop.index + 1}</td>
                    <td style="text-align: center">${u.getUsername()}</td>
                    <td style="text-align: right">${u.getPoints()} <i class="icon-trophy"></i></td>
                  </tr>
                </c:if>
              </c:forEach>
              </tbody>
            </table>
            <br>
          </div>
        </div>
      </div>
    </div>
    <br>
    <div class="row">
      <div class="col-sm-12 col-md-12">
        <div class="card">
          <div class="card-header">
            <div class="float-left">
              Top 10 users - Most followers
            </div>
            <div class="float-right">

            </div>
          </div>
          <div class="card-body">
            <br>
            <table class="table table-responsive-sm table-sm center" style="width: 90%;padding-left: 10px;padding-right: 10px">
              <col style="width:10%">
              <col style="width:14%">
              <col style="width:76%">
              <thead>
              <tr>
                <th style="text-align: left">Rank</th>
                <th style="text-align: center">Username</th>
                <th style="text-align: right">Followers</th>
              </tr>
              </thead>
              <tbody>

              <c:forEach var="u" items="${rankings}" varStatus="loop">
                <c:if test="${loop.index + 1 eq 1}">
                  <tr style="background-color: #ffdc73">
                    <td style="text-align: left">Rank ${loop.index + 1}</td>
                    <td style="text-align: center">${u.getUsername()}</td>
                    <td style="text-align: right">${u.getPoints()} <i class="icon-user"></i></td>
                  </tr>
                </c:if>
                <c:if test="${loop.index + 1 eq 2}">
                  <tr style="background-color: #dadada">
                    <td style="text-align: left">Rank ${loop.index + 1}</td>
                    <td style="text-align: center">${u.getUsername()}</td>
                    <td style="text-align: right">${u.getPoints()} <i class="icon-user"></i></td>
                  </tr>
                </c:if>
                <c:if test="${loop.index + 1 eq 3}">
                  <tr style="background-color: #e8c4a0">
                    <td style="text-align: left">Rank ${loop.index + 1}</td>
                    <td style="text-align: center">${u.getUsername()}</td>
                    <td style="text-align: right">${u.getPoints()} <i class="icon-user"></i></td>
                  </tr>
                </c:if>
                <c:if test="${loop.index + 1 ne 3 && loop.index + 1 ne 1 && loop.index + 1 ne 2}">
                  <tr>
                    <td style="text-align: left">Rank ${loop.index + 1}</td>
                    <td style="text-align: center">${u.getUsername()}</td>
                    <td style="text-align: right">${u.getPoints()} <i class="icon-user"></i></td>
                  </tr>
                </c:if>
              </c:forEach>
              </tbody>
            </table>
            <br>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>