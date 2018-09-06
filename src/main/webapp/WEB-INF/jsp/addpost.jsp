<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<br>
<h4>Add Post</h4>

<form:form method="post" action="/app/addpost" modelAttribute="post">
  <form>
    <input type="hidden" name="user.id" value="${user.getId()}">
    <div class="form-group">
      <label for="exampleFormControlInput1">Set category</label>
      <form:select items="${categories}" itemValue="id" itemLabel="name" class="form-control"
                   id="exampleFormControlInput1" path="category"/>
      <form:errors path="category" cssClass="error"/>
    </div>

    <div class="form-group">
      <label for="exampleFormControlInput1a">Title</label>
      <form:textarea class="form-control" rows="1" id="exampleFormControlInput1a"
                     placeholder="title" path="title"/>
      <form:errors path="title" cssClass="error"/>
    </div>

    <div class="form-group">
      <label for="exampleFormControlInput2">Content</label>
      <form:textarea class="form-control" rows="4" id="exampleFormControlInput2"
                     placeholder="content" path="text"/>
      <form:errors path="text" cssClass="error"/>
    </div>
    <input type="submit" value="Send">
  </form>
</form:form>