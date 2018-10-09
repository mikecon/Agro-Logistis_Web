var salesApp = {};

(function () {
    var firebase = app_fireBase;
    const firestore = firebase.firestore();
    const settings = {/* your settings... */ timestampsInSnapshots: true};
    firestore.settings(settings);
    var db = firestore;
    var uid = null;
    var reportSale = document.getElementById("reportSale");
    var saleScore = 0;
    var months = ["Ιανουάριος", "Φεβρουάριος", "Μάρτιος", "Απρίλιος", "Μάης", "Ιούνιος", "Ιούλιος", "Αύγουστος",
        "Σεπτέμβριος", "Οκτώβριος", "Νοέμβριος", "Δεκέμβριος"];

    firebase.auth().onAuthStateChanged(function(user) {
        if (user) {
            //User is signed in.
            uid = user.uid;

            var columns = [ 'Αγοραστής', 'Ημερομηνία', 'Είδος', 'Ποσό', 'ΦΠΑ', 'Μέθοδος Πληρωμής'];

            var rowsSales = [];

            rowsSales.push(columns);

            db.collection(uid).doc("cloud").collection("sale")
                .get("date")
                .then(function(querySnapshot) {
                    querySnapshot.forEach(function(doc) {
                        db.doc(uid + "/cloud/sale/" + doc.id).get().then(documentSnapshot => {
                            var year = document.getElementById("inputYear");
                            var startDate = new Date(year.value, 1, 0, 0, 0, 0, 0);
                            var endYear = startDate.getFullYear() + 1;
                            var endDate = new Date(endYear, 1, 0, 0, 0, 0, 0);
                            if( documentSnapshot.get('date').toMillis() >= startDate.getTime() &&
                                documentSnapshot.get('date').toMillis() < endDate.getTime()){
                                var valueField = documentSnapshot.get('value');
                                var dateField = documentSnapshot.get('date').toDate();
                                var month = dateField.getMonth() + 1;
                                var dataRow = [];

                                dataRow.push(documentSnapshot.get('buyer'));
                                dataRow.push(dateField.getDate()+'/'+month+'/'+dateField.getFullYear());
                                dataRow.push(documentSnapshot.get('type'));
                                dataRow.push(documentSnapshot.get('value'));
                                dataRow.push(documentSnapshot.get('vat'));
                                dataRow.push(documentSnapshot.get('paymentMethod'));

                                rowsSales.push(dataRow)

                                saleScore+=valueField;
                            }
                        });
                    });
                })
                .catch(function(error) {
                    console.log("Error getting documents: ", error);
                });

            reportSale.addEventListener('click', function (e) {
                var d = new Date();

                var docInfo = {
                    info: {
                        title: 'Αναφορά Πωλήσεων',
                        author: 'Αγρο Λογιστής',
                        subject: 'Sale',
                        keywords: 'Αγρο Λογιστής'
                    },
                    pageSize: 'A4',
                    pageOrientation: 'portrait', // 'landscape'
                    pageMargins:[50,50,30,60],

                    header: function(currentPage, pageCount){
                        return {
                            text: currentPage.toString() + '/' + pageCount.toString(),
                            alignment: 'right',
                            margin: [0,30,10,50]
                        }
                    },

                    footer: [
                        {
                            text: 'Αγρο Λογιστής',
                            alignment: 'center', // left right
                        }
                    ],
                    content:[
                        {
                            text: 'Αναφορά Πωλήσεων',
                            fontSize: 20,
                            bold: true,
                            alignment: 'center',
                        },

                        {
                            text: 'Δημιουργήθηκε: ' + d.getDate() +'/'+ d.getMonth() + '/'+d.getFullYear(),
                            fontSize: 15,
                        },

                        {
                            text: 'Συνολο: ' + saleScore,
                            fontSize: 15,
                        },

                        {
                            text: '-',
                            fontSize: 15,
                        },

                        {
                            table:{
                                body: rowsSales
                            }
                        }

                    ]
                };
                pdfMake.createPdf(docInfo).download('Αναφορά Πωλήσεων '+months[d.getMonth()]+ ' ' +d.getFullYear()+ '.pdf');
            });

        }else{
            // redirect to login page
            uid = null;
            window.location.replace("/");
        }
    });

    function logOut() {
        firebase.auth().signOut();
    }

    salesApp.logOut = logOut;
})();