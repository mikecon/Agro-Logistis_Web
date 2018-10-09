<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                <button class="btn btn-outline-danger my-2 my-sm-0" onclick="receiptApp.logOut()">Αποσυνδέση</button>
            </form>
        </div>
    </div>
</nav>

<div class="container">

    <h2>Νέα είσπραξη</h2>
    <hr>
    <form:form>
        <div class="form-group">
            <label for="inputCustomer">Αγοραστής</label>
            <spring:url value="${pageContext.request.contextPath}/addcustomer" var="addCustomer"/>
            <a href="${addCustomer}">Νέο</a>
            <select id="inputCustomer" name="inputCustomer" class="form-control">
                <c:forEach items="${customerList}" var="buyer">
                    <option value="${buyer.name}" selected>${buyer.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group row">
            <label for="inputTotalAmount" class="col-sm-2 col-form-label">Συνολικό ποσό</label>
            <div class="col-sm-10 input-group">
                <input type="text" class="form-control" id="inputTotalAmount" name="inputTotalAmount" maxlength="20" placeholder="0.00" aria-label="0.00" value="0">
                <div class="input-group-append">
                    <span class="input-group-text">&euro;</span>
                </div>
            </div>
        </div>
        <div class="form-group row">
            <label for="inputPaymentAmount" class="col-sm-2 col-form-label">Ποσό πληρωμής</label>
            <div class="col-sm-10 input-group">
                <input type="text" class="form-control" id="inputPaymentAmount" name="inputPaymentAmount" maxlength="20" placeholder="0.00" aria-label="0.00" value="0">
                <div class="input-group-append">
                    <span class="input-group-text">&euro;</span>
                </div>
            </div>
        </div>
        <div class="form-group row">
            <label for="inputRestAmount" class="col-sm-2 col-form-label">Υπόλοιπο</label>
            <div class="col-sm-10 input-group">
                <input type="text" class="form-control" id="inputRestAmount" name="inputRestAmount" placeholder="0.00" maxlength="20" aria-label="0.00" value="0">
                <div class="input-group-append">
                    <span class="input-group-text">&euro;</span>
                </div>
            </div>
        </div>
        <div class="form-group row">
            <label for="inputDate" class="col-sm-2 col-form-label">Ημερομηνία πληρωμής</label>
            <div class="col-sm-10">
                <input type="date" class="form-control" id="inputDate" name="inputDate" value="2018-01-01" min="2000-01-01" max="3000-12-31" placeholder="Date">
            </div>
        </div>
        <div class="form-group">
            <label class="upload-group" for="receipt">Παραστατικό</label>
            <input type="file" class="form-control-file" id="receipt" />
            <div id="upload">
                <br>
                <div class="progress">
                    <div class="progress-bar progress-bar-striped progress-bar-animated active" role="progressbar"
                         aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width:0%">
                        0 %
                    </div>
                </div>
                <br>
                <button type="button" class="btn btn-primary btn-sm" id="uploadButton" onclick="receiptApp.uploadImage()">Upload</button>
            </div>
            <input hidden id="imageUrlInput" name="imageUrlInput" value="${pageContext.servletContext.contextPath}/resources/images/none.png" type="text">
        </div>
        <hr>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <input class="btn btn-primary" type="submit" value="Προσθήκη">
            </div>
        </div>
    </form:form>

</div>

<jsp:include page="templates/footer.jsp"/>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/receipt.js"></script>
</body>
</html>