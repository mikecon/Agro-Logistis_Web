<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="el">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Αγρο Λογιστής</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/bootstrap.min.css"/>

    <!-- CSS -->
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/loading.css"/>

    <!-- Favicon -->
    <link rel="icon" type="image/x-icon" href="${pageContext.servletContext.contextPath}/resources/images/favicon.ico">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <!-- Firebase -->
    <script src="https://www.gstatic.com/firebasejs/5.3.0/firebase-app.js"></script>
    <script src="https://www.gstatic.com/firebasejs/5.3.0/firebase-auth.js"></script>
    <script src="https://www.gstatic.com/firebasejs/5.3.0/firebase-firestore.js"></script>
    <script src="https://cdn.firebase.com/libs/firebaseui/3.1.1/firebaseui.js"></script>
    <script src="https://www.gstatic.com/firebasejs/5.3.0/firebase-database.js"></script>
    <script src="https://www.gstatic.com/firebasejs/5.3.0/firebase-messaging.js"></script>
    <script src="https://www.gstatic.com/firebasejs/5.3.0/firebase-functions.js"></script>
    <script src="https://www.gstatic.com/firebasejs/5.3.0/firebase-storage.js"></script>

</head>
<body>

<div class="container">
    <!-- Get UID -->
    <form id="uid_form" name="uid_form" action="${pageContext.request.contextPath}/index" method="POST" accept-charset="utf-8">
        <input hidden id="user_uid" name="user_uid" type="text" title="">
        <input hidden type="submit" class="btn btn-secondary btn-lg btn-block" value="Φόρτωση δεδομένων" id="uid_sbm" name="uid_sbm" >
    </form>

</div>

<div class="row h-100 justify-content-center align-items-center">
    <i class="loading fa fa-spinner fa-spin" style="font-size:24px"></i>
</div>

<jsp:include page="templates/footer.jsp"/>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/main.js"></script>
<script type="text/javascript">
    window.onload=function(){
        window.setTimeout(function() { document.uid_form.submit(); }, 1000);
    };
</script>
</body>
</html>