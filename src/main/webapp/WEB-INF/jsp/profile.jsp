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
                <h4 class="card-title mb-0">Your profile</h4>
                <div class="small text-muted"><fmt:formatDate pattern="dd-MM-yyyy" value="${now}"/></div>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-sm-12 col-md-12">
                <div class="card">
                    <div class="card-header">
                        <div class="float-left">
                            ${user.getUsername()}
                        </div>
                        <div class="float-right">

                        </div>
                    </div>
                    <div class="card-body">
                        Points: <i class="icon-trophy"></i>${user.getPoints()}<br>
                        Follower: <i class="icon-user"></i>${user.getFollowers()}
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>