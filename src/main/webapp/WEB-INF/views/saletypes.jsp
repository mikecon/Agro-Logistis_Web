<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="templates/header.jsp"/>
<body>
<jsp:include page="templates/nav.jsp"/>
<input hidden id="user_uid" name="user_uid" type="text" title="">
<div class="container">

    <h2>Λίστα είδη πώλησης</h2>
    <hr>
    <div class="table-responsive">
        <table class="table table-bordered table-striped">
            <thead>
            <tr>
                <th>Όνομα</th>
                <th colspan="1"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="row">
                <tr>
                    <td>${row.name}</td>
                    <spring:url value="${pageContext.request.contextPath}/saletypes/delete/${row.saleTypeID}" var="deleteURL"/>
                    <td><button type="button" class="btn btn-danger" onclick="location.href='${deleteURL}'">Διαγραφή</button></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <h2>Νεό είδος πώλησης</h2>
    <hr>
    <form:form>
    <div class="form-group">
        <label for="nameInput">Όνομα</label>
        <input type="text" class="form-control" id="nameInput" name="nameInput" maxlength="20" minlength="1" placeholder="Name">
    </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <input class="btn btn-primary" type="submit" value="Προσθήκη">
            </div>
        </div>
    </form:form>

</div>

<jsp:include page="templates/footer.jsp"/>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/main.js"></script>
</body>
</html>