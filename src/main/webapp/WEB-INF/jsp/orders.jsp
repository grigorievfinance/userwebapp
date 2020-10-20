<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--<%@ taglib prefix="fn" tagdir="/WEB-INF/tld/functions.tld" %>--%>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script src="resources/js/common.js" defer></script>
<script src="resources/js/orders.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
        <h3 class="text-center">Orders</h3>
        <div class="card border-dark">
            <div class="card-body pb-0">
                <form id="filter">
                    <div class="row">
                        <div class="offset-1 col-2">
                            <label for="startDate">Start Date</label>
                            <input class="form-control" name="startDate" id="startDate" autocomplete="off">
                        </div>
                        <div class="col-2">
                            <label for="endDate">End Date</label>
                            <input class="form-control" name="endDate" id="endDate" autocomplete="off">
                        </div>
                        <div class="col-2">
                            <label for="startTime">Start Time</label>
                            <input class="form-control" name="startTime" id="startTime" autocomplete="off">
                        </div>
                        <div class="col-2">
                            <label for="endTime">End Time</label>
                            <input class="form-control" name="endTime" id="endTime" autocomplete="off">
                        </div>
                    </div>
                </form>
            </div>
            <div class="card-footer text-right">
                <button class="btn btn-danger" onclick="clearFilter()">
                    <span class="fa fa-remove"></span>
                    Cancel
                </button>
                <button class="btn btn-primary" onclick="updateFilteredTable()">
                    <span class="fa fa-filter"></span>
                    Filter
                </button>
            </div>
        </div>
        <br>
        <button class="btn btn-primary" onclick="add()">
            <span class="fa fa-plus"></span>
            Add
        </button>
        <table class="table table-striped" id="datatable">
            <thead>
            <tr>
                <th>DateTime</th>
                <th>Description</th>
                <th>Price</th>
                <th>Deadline</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<jsp:include page="fragments/orderModal.jsp"/>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
