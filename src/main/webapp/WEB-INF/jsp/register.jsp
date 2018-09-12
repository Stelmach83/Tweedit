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
        <h1>Register</h1>
        <p class="text-muted">Create your account</p>
        <div class="input-group mb-3">
          <div class="input-group-prepend">
            <span class="input-group-text"><i class="icon-user"></i></span>
          </div>
          <input class="form-control" type="text" placeholder="Username">
        </div>
        <div class="input-group mb-3">
          <div class="input-group-prepend">
            <span class="input-group-text">@</span>
          </div>
          <input class="form-control" type="text" placeholder="Email">
        </div>
        <div class="input-group mb-3">
          <div class="input-group-prepend">
            <span class="input-group-text"><i class="icon-lock"></i></span>
          </div>
          <input class="form-control" type="password" placeholder="Password">
        </div>
        <div class="input-group mb-4">
          <div class="input-group-prepend">
            <span class="input-group-text"><i class="icon-lock"></i></span>
          </div>
          <input class="form-control" type="password" placeholder="Repeat password">
        </div>
        <button class="btn btn-block btn-success" type="button">Create Account</button>
      </div>
    </div>
  </div>
</div>