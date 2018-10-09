<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="el">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Αγρο Λογιστής</title>

    <!-- Login CSS -->
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/login-style.css"/>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/bootstrap.min.css"/>

    <!-- Favicon -->
    <link rel="icon" type="image/x-icon" href="${pageContext.servletContext.contextPath}/resources/images/favicon.ico">

    <!-- Firebase -->
    <script src="https://www.gstatic.com/firebasejs/5.3.0/firebase-app.js"></script>
    <script src="https://www.gstatic.com/firebasejs/5.3.0/firebase-auth.js"></script>
    <script src="https://cdn.firebase.com/libs/firebaseui/3.1.1/firebaseui.js"></script>
    <link type="text/css" rel="stylesheet" href="https://cdn.firebase.com/libs/firebaseui/3.1.1/firebaseui.css" />
</head>
<body>

<!-- FirebaseUI Authentication -->
    <section class="container-fluid bg">
        <section class="row justify-content-center">
            <section class="col-12 col-sm-6 col-md-3">
                <form action="/" method="post" class="form-container">
                    <h2 id="loginTitle">Αγρο Λογιστής</h2>
                    <img id="imgLogo" src="${pageContext.servletContext.contextPath}/resources/images/farmer_logo.png" width="50" height="50">
                    <br>
                    <div id="firebaseui-auth-container"></div>
                    <div id="loader">Loading...</div>
                </form>
            </section>
        </section>
    </section>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>

    <!-- Firebase JS -->
    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/fireBase.js"></script>
    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/login.js"></script>

</body>
</html>
