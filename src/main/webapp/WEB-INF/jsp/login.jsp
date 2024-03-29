<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">
        <c:if test="${param.error}">
            <div class="error">${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</div>
        </c:if>
        <c:if test="${not empty param.message}">
            <div class="message"><spring:message code="${param.message}"/></div>
        </c:if>
        <sec:authorize access="isAnonymous()">
            <div class="pt-4">
                <a class="btn btn-success" href="profile/register">Register</a>
                <button type="submit" class="btn btn-primary" onclick="login('admin@gmail.com', 'admin')">
                    Login as Admin
                </button>
                <button type="submit" class="btn btn-secondary" onclick="login('user@yandex.ru', 'password')">
                    Login as User
                </button>
            </div>
        </sec:authorize>
    </div>
</div>
<div class="container lead">User Web Application Example</div>
<jsp:include page="fragments/footer.jsp"/>
<script>
    <c:if test="${not empty param.username}">
        setCredentials("${param.username}", " ");
    </c:if>

    function login(username, password){
        setCredentials(username, password);
        $("#login_form").submit();
    }
    function setCredentials(username, password){
        $('input[name="username"]').val(username);
        $('input[name="password"]').val(password);
    }
</script>
</body>
</html>
