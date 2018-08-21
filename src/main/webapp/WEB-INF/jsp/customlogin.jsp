<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <link rel="stylesheet" href="https://unpkg.com/@coreui/coreui/dist/css/coreui.min.css">
    <script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="https://unpkg.com/@coreui/coreui/dist/js/coreui.min.js"></script>
    <title>Login</title>
    <style>
        html {
            overflow-y: scroll;
        }

        /* width */
        ::-webkit-scrollbar {
            width: 10px;
        }

        /* Track */
        ::-webkit-scrollbar-track {
            background: #f1f1f1;
        }

        /* Handle */
        ::-webkit-scrollbar-thumb {
            background: #34383C;
        }

        /* Handle on hover */
        ::-webkit-scrollbar-thumb:hover {
            background: #555;
        }

        .error {
            color: red;
        }
    </style>
</head>
<body class="app">

<%--<jsp:include page="/WEB-INF/jsp/header/header.jsp"/>--%>
<div class="app-body">
    <div class="container">
        <main class="main">
            <h4>Login page</h4>
            <form action="/login" method="post">
                <label>Email:<br>
                    <input type="text" name="username">
                </label>
                <br>
                <label>Password:<br>
                    <input type="password" name="password">
                </label>
                <br>
                <input type="submit" value="Proceed" name="submit">
            </form>
        </main>
    </div>
</div>
<%--<jsp:include page="/WEB-INF/jsp/footer/footer.jsp"/>--%>

</body>
</html>
