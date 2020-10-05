<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script src="resources/js/common.js" defer></script>
<script src="resources/js/users.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron pt-4">
    <div class="container">
        <h3 class="text-center">Users</h3>
        <button class="btn btn-primary" onclick="add()">
            <span class="fa fa-plus"></span>
            Add
        </button>
        <table class="table table-striped" id="datatable">
            <thead>
            <tr>
                <th>Name</th>
                <th>Email</th>
                <th>Roles</th>
                <th>Enable</th>
                <th>Registered</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
        </table>
    </div>
</div>
<jsp:include page="fragments/userModal.jsp"/>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>