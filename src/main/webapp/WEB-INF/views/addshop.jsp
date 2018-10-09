<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
    <jsp:include page="templates/header.jsp"/>
<body>
    <jsp:include page="templates/nav.jsp"/>
    <input hidden id="user_uid" name="user_uid" type="text" title="">
    <div class="container">

        <h2>Νέο κατάστημα</h2>
        <hr>
        <form:form>
            <div class="form-group">
                <label for="nameInput">Όνομα</label>
                <input type="text" class="form-control" id="nameInput" name="nameInput" maxlength="50" placeholder="Name">
            </div>
            <div class="form-group">
                <label for="addressInput">Διεύθυνση</label>
                <input type="text" class="form-control" id="addressInput" name="addressInput" maxlength="60" placeholder="Address">
            </div>
            <div class="form-group">
                <label for="contactNameInput">Όνομα επαφής</label>
                <input type="text" class="form-control" id="contactNameInput" name="contactNameInput" maxlength="20" placeholder="Contact name">
            </div>
            <div class="form-group">
                <label for="telephoneInput">Τηλέφωνο</label>
                <input type="text" class="form-control" id="telephoneInput" name="telephoneInput" minlength="7" maxlength="14" placeholder="Telephone">
            </div>
            <div class="form-group">
                <label for="vatInput">ΑΦΜ</label>
                <input type="text" class="form-control" id="vatInput" name="vatInput" minlength="9" maxlength="9" placeholder="VAT number">
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
    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/main.js"></script>
</body>
</html>
