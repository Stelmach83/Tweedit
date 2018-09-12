<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<br>
<div class="row justify-content-center">
  <div class="col-md-6">
    <div class="card mx-4">
      <div class="card-body p-4">
        <form:form method="post" modelAttribute="user">
          <form>
            <h1>Register</h1>
            <p class="text-muted">Create your account</p>
            <div class="input-group mb-3">
              <div class="input-group-prepend">
                <span class="input-group-text"><i class="icon-user"></i></span>
              </div>
              <input class="form-control" type="text" placeholder="Username" name="username">
            </div>
            <div style="margin-bottom: -10px">
              <form:errors path="username" cssClass="errorreg"/>
            </div>
            <br>
            <br>
            <div class="input-group mb-3">
              <div class="input-group-prepend">
                <span class="input-group-text">@</span>
              </div>
              <input class="form-control" type="text" placeholder="Email" name="email">
            </div>
            <div style="margin-bottom: -10px">
              <form:errors path="email" cssClass="errorreg"/>
            </div>
            <br>
            <br>
            <div class="input-group mb-3">
              <div class="input-group-prepend">
                <span class="input-group-text"><i class="icon-lock"></i></span>
              </div>
              <input class="form-control" type="password" placeholder="Password" name="password">
            </div>
            <div style="margin-bottom: -10px">
              <form:errors path="password" cssClass="errorreg"/>
            </div>
            <br>
            <br>
            <div class="input-group mb-3">
              <div class="input-group-prepend">
                <span class="input-group-text"><i class="icon-lock"></i></span>
              </div>
              <input class="form-control" type="password" placeholder="Repeat password" name="password2">
            </div>
            <div style="margin-bottom: -10px">
              <form:errors path="password2" cssClass="errorreg"/>
            </div>
            <br>
            <br>
            <button class="btn btn-block btn-success" type="submit">Create Account</button>
          </form>
        </form:form>
      </div>
    </div>
  </div>
</div>