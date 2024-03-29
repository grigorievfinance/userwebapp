<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <form method="post" action="users" class="form-inline">
            <label>Login</label>
            <select name="userId" class="form-control">
                <option value="100000" selected>User</option>
                <option value="100001">Admin</option>
            </select>
            <button type="submit" class="btn btn-primary">Select</button>
        </form>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
