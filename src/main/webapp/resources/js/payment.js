var paymentApp = {};

(function () {
    var firebase = app_fireBase;
    const firestore = firebase.firestore();
    const settings = {/* your settings... */ timestampsInSnapshots: true};
    firestore.settings(settings);
    var db = firestore;
    var uid = null;
    var reportPayment = document.getElementById("reportPayment");
    var paymentScore = 0;
    var months = ["Ιανουάριος", "Φεβρουάριος", "Μάρτιος", "Απρίλιος", "Μάης", "Ιούνιος", "Ιούλιος", "Αύγουστος",
        "Σεπτέμβριος", "Οκτώβριος", "Νοέμβριος", "Δεκέμβριος"];

    firebase.auth().onAuthStateChanged(function(user) {
        if (user) {
            //User is signed in.
            uid = user.uid;

            var columns = [ 'Κατάστημα', 'Ημερομηνία', 'Συνολικό ποσό', 'Ποσό πληρωμής', 'Υπόλοιπο'];

            var rowsPayment = [];

            rowsPayment.push(columns);

            db.collection(uid).doc("cloud").collection("payment")
                .get("date")
                .then(function(querySnapshot) {
                    querySnapshot.forEach(function(doc) {
                        db.doc(uid + "/cloud/payment/" + doc.id).get().then(documentSnapshot => {

                            var year = document.getElementById("inputYear");
                            var startDate = new Date(year.value, 1, 0, 0, 0, 0, 0);
                            var endYear = startDate.getFullYear() + 1;
                            var endDate = new Date(endYear, 1, 0, 0, 0, 0, 0);
                            if( documentSnapshot.get('date').toMillis() >= startDate.getTime() &&
                                documentSnapshot.get('date').toMillis() < endDate.getTime()){
                                var valueField = documentSnapshot.get('restOfAmount');
                                var dateField = documentSnapshot.get('date').toDate();
                                var month = dateField.getMonth() + 1;
                                var dataRow = [];

                                dataRow.push(documentSnapshot.get('shop'));
                                dataRow.push(dateField.getDate()+'/'+month+'/'+dateField.getFullYear());
                                dataRow.push(documentSnapshot.get('totalAmount'));
                                dataRow.push(documentSnapshot.get('paymentAmount'));
                                dataRow.push(documentSnapshot.get('restOfAmount'));

                                rowsPayment.push(dataRow)

                                paymentScore+=valueField;
                            }
                        });
                    });
                })
                .catch(function(error) {
                    console.log("Error getting documents: ", error);
                });

            reportPayment.addEventListener('click', function (e) {
                var d = new Date();
                var docInfo = {
                    info: {
                        title: 'Αναφορά Πληρωμών',
                        author: 'Αγρο Λογιστής',
                        subject: 'Payments',
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
                            text: 'Αναφορά Πληρωμών',
                            fontSize: 20,
                            bold: true,
                            alignment: 'center',
                        },

                        {
                            text: 'Δημιουργήθηκε: ' + d.getDate() +'/'+ d.getMonth() + '/'+d.getFullYear(),
                            fontSize: 15,
                        },

                        {
                            text: 'Συνολο: ' + paymentScore,
                            fontSize: 15,
                        },

                        {
                            text: '-',
                            fontSize: 15,
                        },

                        {
                            table:{
                                body: rowsPayment
                            }
                        }

                    ]
                };
                pdfMake.createPdf(docInfo).download('Αναφορά Πληρωμών '+months[d.getMonth()]+ ' ' +d.getFullYear()+ '.pdf');
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

    paymentApp.logOut = logOut;
})();