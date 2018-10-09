var shopsApp = {};

(function () {
    var firebase = app_fireBase;
    const firestore = firebase.firestore();
    const settings = {/* your settings... */ timestampsInSnapshots: true};
    firestore.settings(settings);
    var db = firestore;
    var uid = null;
    var reportShops = document.getElementById("reportShops");
    var shopsScore = 0;
    var months = ["Ιανουάριος", "Φεβρουάριος", "Μάρτιος", "Απρίλιος", "Μάης", "Ιούνιος", "Ιούλιος", "Αύγουστος",
        "Σεπτέμβριος", "Οκτώβριος", "Νοέμβριος", "Δεκέμβριος"];

    firebase.auth().onAuthStateChanged(function(user) {
        if (user) {
            //User is signed in.
            uid = user.uid;

            var columns = [ 'Όνομα', 'Όνομα Επαφής', 'Διεύθυνση', 'Τηλέφωνο', 'ΑΦΜ'];
            var rowsShops = [];
            rowsShops.push(columns);

            db.collection(uid).doc("cloud").collection("shop")
                .get("shopID")
                .then(function(querySnapshot) {
                    querySnapshot.forEach(function(doc) {
                        db.doc(uid + "/cloud/shop/" + doc.id).get().then(documentSnapshot => {
                            var dataRow = [];
                            dataRow.push(documentSnapshot.get('name'));
                            dataRow.push(documentSnapshot.get('contactName'));
                            dataRow.push(documentSnapshot.get('address'));
                            dataRow.push(documentSnapshot.get('telephone'));
                            dataRow.push(documentSnapshot.get('vat'));

                            rowsShops.push(dataRow)

                            shopsScore+= 1;

                        });
                    });
                })
                .catch(function(error) {
                    console.log("Error getting documents: ", error);
                });

            reportShops.addEventListener('click', function (e) {
                var d = new Date();
                var docInfo = {
                    info: {
                        title: 'Αναφορά Καταστημάτων',
                        author: 'Αγρο Λογιστής',
                        subject: 'Shops',
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
                            text: 'Αναφορά Καταστημάτων',
                            fontSize: 20,
                            bold: true,
                            alignment: 'center',
                        },

                        {
                            text: 'Δημιουργήθηκε: ' + d.getDate() +'/'+ d.getMonth() + '/'+d.getFullYear(),
                            fontSize: 15,
                        },

                        {
                            text: 'Συνολο: ' + shopsScore,
                            fontSize: 15,
                        },

                        {
                            text: '-',
                            fontSize: 15,
                        },

                        {
                            table:{
                                body: rowsShops
                            }
                        }

                    ]
                };

                pdfMake.createPdf(docInfo).download('Αναφορά Καταστημάτων '+months[d.getMonth()]+ ' ' +d.getFullYear()+ '.pdf');

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

    shopsApp.logOut = logOut;
})();