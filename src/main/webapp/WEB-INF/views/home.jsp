<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="el">
    <jsp:include page="templates/chart.jsp"/>
<body>
    <jsp:include page="templates/nav.jsp"/>

    <div class="container">

        <form id="uid_form" action="${pageContext.request.contextPath}/home" method="POST" accept-charset="utf-8">
            <input hidden id="user_uid" name="user_uid" type="text" title="">
        </form>

        <!-- Chart Report -->
        <div class="row" id="scoreReport">
            <a>Σύνολο 2018 </a>
            <div class="col-md-3" id="buySaleScoreDiv"></div>
            <div class="col-md-3" id="CollPayDiv"></div>
            <div class="col-md-3" id="totalScoreDiv"></div>
            <div id="chart_div" style="width: 100%; height: 500px;"></div>
        </div>

    </div>

    <footer class="footer">
        <div class="container">
            <span class="text-muted">Ο Αγρο Λογιστής αποτελεί προϊόν ελεύθερου λογισμικού και πνευματικής ιδιοκτησίας
                του Τμήματος Μηχανικών Πληροφορικής του ΤΕΙ Κεντρικής Μακεδονίας. Αναπτύχθηκε στα πλαίσια πτυχιακής
                εργασίας από τον σπουδαστή Μιχάλη Κωνσταντίνου κατόπιν εισήγησης και επιτήρησης του Δρ. Λάντζου Θεόδωρου
                Επιστημονικού Συνεργάτη του τμήματος Μηχανικών Πληροφορικής του ΤΕΙ Κεντρικής Μακεδονίας.</span>
        </div>
    </footer>
    <jsp:include page="templates/footer.jsp"/>
    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/main.js"></script>
</body>
</html>
