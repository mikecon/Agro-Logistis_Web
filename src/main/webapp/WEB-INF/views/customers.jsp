<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="templates/header.jsp"/>
<body>
<spring:url value="${pageContext.request.contextPath}/home" var="homeURL"/>
<spring:url value="${pageContext.request.contextPath}/customers" var="customersURL"/>
<spring:url value="${pageContext.request.contextPath}/buys" var="buysURL"/>
<spring:url value="${pageContext.request.contextPath}/sales" var="salesURL"/>
<spring:url value="${pageContext.request.contextPath}/shops" var="shopsURL"/>
<spring:url value="${pageContext.request.contextPath}/addbuy" var="addBuy"/>
<spring:url value="${pageContext.request.contextPath}/addsale" var="addSale"/>
<spring:url value="${pageContext.request.contextPath}/addcustomer" var="addCustomer"/>
<spring:url value="${pageContext.request.contextPath}/addshop" var="addShop"/>
<spring:url value="${pageContext.request.contextPath}/payments" var="paymentsURL"/>
<spring:url value="${pageContext.request.contextPath}/addpayment" var="addPayment"/>
<spring:url value="${pageContext.request.contextPath}/collections" var="collectionsURL"/>
<spring:url value="${pageContext.request.contextPath}/addcollection" var="addCollection"/>
<spring:url value="${pageContext.request.contextPath}/saletypes" var="saleTypes"/>
<spring:url value="${pageContext.request.contextPath}/buytypes" var="buyTypes"/>

<nav class="navbar fixed-top navbar-expand-lg navbar-light bg-light">
    <div class="container">
        <a class="navbar-brand" href="${homeURL}">Αγρο Λογιστής</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="${homeURL}">Αρχική</a>
                </li>
                <li class="nav-item dropdown active">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown4" role="button" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">
                        Πωλήσεις
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown4">
                        <a class="dropdown-item" href="${salesURL}">Πίνακας</a>
                        <a class="dropdown-item" href="${addSale}">Προσθήκη</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="${saleTypes}">Είδη πώλησης</a>
                    </div>
                </li>
                <li class="nav-item dropdown active">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown3" role="button" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">
                        Αγορές
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown3">
                        <a class="dropdown-item" href="${buysURL}">Πίνακας</a>
                        <a class="dropdown-item" href="${addBuy}">Προσθήκη</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="${buyTypes}">Είδη αγοράς</a>
                    </div>
                </li>
                <li class="nav-item dropdown active">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown5" role="button" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">
                        Εισπράξεις
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown5">
                        <a class="dropdown-item" href="${collectionsURL}">Πίνακας</a>
                        <a class="dropdown-item" href="${addCollection}">Προσθήκη</a>
                    </div>
                </li>
                <li class="nav-item dropdown active">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown1" role="button" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">
                        Πληρωμές
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown1">
                        <a class="dropdown-item" href="${paymentsURL}">Πίνακας</a>
                        <a class="dropdown-item" href="${addPayment}">Προσθήκη</a>
                    </div>
                </li>
                <li class="nav-item dropdown active">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">
                        Αγοραστές
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="${customersURL}">Πίνακας</a>
                        <a class="dropdown-item" href="${addCustomer}">Προσθήκη</a>
                    </div>
                </li>
                <li class="nav-item dropdown active">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown2" role="button" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">
                        Καταστήματα
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown2">
                        <a class="dropdown-item" href="${shopsURL}">Πίνακας</a>
                        <a class="dropdown-item" href="${addShop}">Προσθήκη</a>
                    </div>
                </li>
            </ul>
            <form class="form-inline my-2 my-lg-0">
                <button class="btn btn-outline-danger my-2 my-sm-0" onclick="customersApp.logOut()">Αποσυνδέση</button>
            </form>
        </div>
    </div>
</nav>

<div class="container">

    <h2>Λίστα αγοραστών</h2>
    <hr>
    <div class="table-responsive">
        <table class="table table-bordered table-striped">
            <thead>
            <tr>
                <th>Όνομα</th>
                <th>Διεύθυνση</th>
                <th>Όνομα επαφής</th>
                <th>Τηλέφωνο</th>
                <th>ΑΦΜ</th>
                <th colspan="1"></th>
            </tr>
            </thead>
            <tbody>
                <c:forEach items="${list}" var="row">
                <tr>
                    <td>${row.name}</td>
                    <td>${row.address}</td>
                    <td>${row.contactName}</td>
                    <td>${row.telephone}</td>
                    <td>${row.vat}</td>
                    <spring:url value="${pageContext.request.contextPath}/customers/delete/${row.buyID}" var="deleteURL"/>
                    <td><button type="button" class="btn btn-danger" onclick="location.href='${deleteURL}'">Διαγραφή</button></td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
        <button class="btn btn-outline-info my-2 my-sm-0" id="reportCustomers" name="reportCustomers">Δημιουργία αναφοράς PDF</button>
    </div>

</div>
<jsp:include page="templates/footer.jsp"/>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/pdfmake.min.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/vfs_fonts.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/customers.js"></script>
</body>
</html>