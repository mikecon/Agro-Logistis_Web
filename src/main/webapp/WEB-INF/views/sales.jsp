<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                <button class="btn btn-outline-danger my-2 my-sm-0" onclick="salesApp.logOut()">Αποσυνδέση</button>
            </form>
        </div>
    </div>
</nav>

<div class="container">

    <h2>Λίστα πωλήσεων</h2>
    <hr>
    <form:form>
        <div class="form-row align-items-center">
            <div class="col-auto">
                <label class="my-1 mr-2" for="inputYear">Έτος</label>
                <input type="number" id="inputYear" name="inputYear" min="2000" max="2099" step="1" value="${year}"/>
            </div>
            <div class="col-auto">
                <input class="btn btn-outline-secondary" type="submit" value="Φίλτρο">
            </div>
        </div>
    </form:form>
    <br>
    <div class="table-responsive">
        <table class="table table-bordered table-striped">
            <thead>
            <tr>
                <th>Αγοραστής</th>
                <th>Σημείωση</th>
                <th>Είδος</th>
                <th>Ημερομηνία</th>
                <th>Ποσό</th>
                <th>Μέθοδος πληρωμής</th>
                <th>ΦΠΑ</th>
                <th>Παραστατικό</th>
                <th colspan="1"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="row">
                <tr>
                    <td>${row.buyer}</td>
                    <td>${row.product}</td>
                    <td>${row.type}</td>
                    <td>${row.date}</td>
                    <td>${row.value}</td>
                    <td>${row.paymentMethod}</td>
                    <td>${row.vat}</td>
                    <td><a href="${row.receipt}"><img src="${row.receipt}" style="width:65px;height:65px;"></a></td>
                    <spring:url value="${pageContext.request.contextPath}/sales/delete/${row.saleID}" var="deleteURL"/>
                    <td><button type="button" class="btn btn-danger" onclick="location.href='${deleteURL}'">Διαγραφή</button></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <button class="btn btn-outline-info my-2 my-sm-0" id="reportSale" name="reportSale">Δημιουργία αναφοράς PDF</button>
    </div>

</div>
<jsp:include page="templates/footer.jsp"/>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/pdfmake.min.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/vfs_fonts.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/sales.js"></script>
</body>
</html>