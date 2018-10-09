var customersApp = {};

(function () {
    var firebase = app_fireBase;
    const firestore = firebase.firestore();
    const settings = {/* your settings... */ timestampsInSnapshots: true};
    firestore.settings(settings);
    var db = firestore;
    var uid = null;
    var reportCustomers = document.getElementById("reportCustomers");
    var customersScore = 0;
    var months = ["Ιανουάριος", "Φεβρουάριος", "Μάρτιος", "Απρίλιος", "Μάης", "Ιούνιος", "Ιούλιος", "Αύγουστος",
        "Σεπτέμβριος", "Οκτώβριος", "Νοέμβριος", "Δεκέμβριος"];

    firebase.auth().onAuthStateChanged(function(user) {
        if (user) {
            //User is signed in.
            uid = user.uid;

            var columns = [ 'Όνομα', 'Όνομα Επαφής', 'Διεύθυνση', 'Τηλέφωνο', 'ΑΦΜ'];
            var rowsCustomers = [];
            rowsCustomers.push(columns);

            db.collection(uid).doc("cloud").collection("buyer")
                .get("buyID")
                .then(function(querySnapshot) {
                    querySnapshot.forEach(function(doc) {
                        db.doc(uid + "/cloud/buyer/" + doc.id).get().then(documentSnapshot => {
                            var dataRow = [];
                            dataRow.push(documentSnapshot.get('name'));
                            dataRow.push(documentSnapshot.get('contactName'));
                            dataRow.push(documentSnapshot.get('address'));
                            dataRow.push(documentSnapshot.get('telephone'));
                            dataRow.push(documentSnapshot.get('vat'));

                            rowsCustomers.push(dataRow)

                            customersScore+= 1;

                        });
                    });
                })
                .catch(function(error) {
                    console.log("Error getting documents: ", error);
                });

            reportCustomers.addEventListener('click', function (e) {
                var d = new Date();

                var docInfo = {
                    info: {
                        title: 'Αναφορά Αγοραστών',
                        author: 'Αγρο Λογιστής',
                        subject: 'Customers',
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
                            text: 'Αναφορά Αγοραστών',
                            fontSize: 20,
                            bold: true,
                            alignment: 'center',
                        },

                        {
                            text: 'Δημιουργήθηκε: ' + d.getDate() +'/'+ d.getMonth() + '/'+d.getFullYear(),
                            fontSize: 15,
                        },

                        {
                            text: 'Συνολο: ' + customersScore,
                            fontSize: 15,
                        },

                        {
                            text: '-',
                            fontSize: 15,
                        },

                        {
                            table:{
                                body: rowsCustomers
                            }
                        }

                    ]
                };
                pdfMake.createPdf(docInfo).download('Αναφορά Αγοραστών '+months[d.getMonth()]+ ' ' +d.getFullYear()+ '.pdf');
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

    customersApp.logOut = logOut;
})();