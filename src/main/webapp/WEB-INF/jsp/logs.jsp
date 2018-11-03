<%@ page import="net.stelmaszak.tweedit.entity.Role" %>
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
                <h4 class="card-title mb-0">Logs</h4>
                <div class="small text-muted"><fmt:formatDate pattern="dd-MM-yyyy" value="${now}"/></div>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-sm-12 col-md-12">
                <div class="card">
                    <div class="card-header">
                        <div class="float-left">
                            Req Info
                        </div>
                        <div class="float-right">

                        </div>
                    </div>
                    <div class="card-body">
                        <br>
                        <table class="table table-responsive-sm table-sm center" style="width: 90%;padding-left: 10px;padding-right: 10px">
                            <col style="width:5%">
                            <col style="width:30%">
                            <col style="width:30%">
                            <col style="width:5%">
                            <col style="width:30%">
                            <thead>
                            <tr>
                                <th style="text-align: center">Id</th>
                                <th style="text-align: center">Device/Browser</th>
                                <th style="text-align: center">Date</th>
                                <th style="text-align: center">Ping</th>
                                <th style="text-align: center">IP</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="log" items="${logslist}">
                                <tr>
                                    <td style="text-align: center">${log.getId()}</td>
                                    <td style="text-align: center">${log.getBrowser()}</td>
                                    <td style="text-align: center"><fmt:formatDate pattern="dd-MM-yyyy HH:mm:ss" value="${log.getDataCzas()}"/></td>
                                    <td style="text-align: center">${log.getReqTime()}</td>
                                    <td style="text-align: center">${log.getIpAdress()}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>