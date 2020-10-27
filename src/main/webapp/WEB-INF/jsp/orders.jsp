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
        <h3 class="text-center"><spring:message code="order.title"/></h3>
        <div class="card border-dark">
            <div class="card-body pb-0">
                <form id="filter">
                    <div class="row">
                        <div class="offset-1 col-2">
                            <label for="startDate"><spring:message code="order.startDate"/></label>
                            <input class="form-control" name="startDate" id="startDate" autocomplete="off">
                        </div>
                        <div class="col-2">
                            <label for="endDate"><spring:message code="order.endDate"/></label>
                            <input class="form-control" name="endDate" id="endDate" autocomplete="off">
                        </div>
                        <div class="offset-2 col-2">
                            <label for="startTime"><spring:message code="order.startTime"/></label>
                            <input class="form-control" name="startTime" id="startTime" autocomplete="off">
                        </div>
                        <div class="col-2">
                            <label for="endTime"><spring:message code="order.endTime"/></label>
                            <input class="form-control" name="endTime" id="endTime" autocomplete="off">
                        </div>
                    </div>
                </form>
            </div>
            <div class="card-footer text-right">
                <button class="btn btn-danger" onclick="clearFilter()">
                    <span class="fa fa-remove"></span>
                    <spring:message code="common.cancel"/>
                </button>
                <button class="btn btn-primary" onclick="updateFilteredTable()">
                    <span class="fa fa-filter"></span>
                    <spring:message code="order.filter"/>
                </button>
            </div>
        </div>
        <br>
        <button class="btn btn-primary" onclick="add()">
            <span class="fa fa-plus"></span>
            <spring:message code="common.add"/>
        </button>
        <div class="mt-3">
            <table class="table table-striped" id="datatable">
                <thead>
                <tr>
                    <th><spring:message code="order.dateTime"/></th>
                    <th><spring:message code="order.description"/></th>
                    <th><spring:message code="order.price"/></th>
                    <th><spring:message code="order.deadline"/></th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
</div>

<div class="modal fade" tabindex="-1" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="modalTitle"></h4>
                <button type="button" class="close" data-dismiss="modal" onclick="closeNoty()">&times;</button>
            </div>
            <div class="modal-body">
                <form id="detailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="dateTime" class="col-form-label"><spring:message code="order.dateTime"/></label>
                        <input class="form-control" id="dateTime" name="dateTime" autocomplete="off"
                               placeholder="<spring:message code="order.dateTime"/>">
                    </div>

                    <div class="form-group">
                        <label for="description" class="col-form-label"><spring:message
                                code="order.description"/></label>
                        <input type="text" class="form-control" id="description" name="description"
                               placeholder="<spring:message code="order.description"/>">
                    </div>

                    <div class="form-group">
                        <label for="price" class="col-form-label"><spring:message code="order.price"/></label>
                        <input type="number" class="form-control" id="price" name="price" placeholder="1000">
                    </div>

                    <div class="form-group">
                        <label for="deadline" class="col-form-label"><spring:message code="order.deadline"/></label>
                        <input class="form-control" id="deadline" name="deadline" autocomplete="off"
                               placeholder="<spring:message code="order.deadline"/>">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="closeNoty()">
                    <span class="fa fa-close"></span>
                    <spring:message code="common.cancel"/>
                </button>
                <button type="button" class="btn btn-primary" onclick="save()">
                    <span class="fa fa-check"></span>
                    <spring:message code="common.save"/>
                </button>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
<jsp:include page="fragments/i18n.jsp">
    <jsp:param name="page" value="order"/>
</jsp:include>
</html>
