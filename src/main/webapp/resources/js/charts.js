google.charts.load('current', {'packages': ['corechart']});
google.charts.setOnLoadCallback(readData);

function readData() {
    var firebase = app_fireBase;
    const firestore = firebase.firestore();
    const settings = {timestampsInSnapshots: true};
    firestore.settings(settings);
    var db = firestore;
    var uid = null;
    var saleScore = 0;
    var buyScore = 0;
    var paymentScore = 0;
    var collectionsScore = 0;
    firebase.auth().onAuthStateChanged(function(user) {
        if (user) {
            uid = user.uid;
            var outputArray = [
                ['\u039C\u03AE\u03BD\u03B5\u03C2', '\u03A0\u03C9\u03BB\u03AE\u03C3\u03B5\u03B9\u03C2',
                    '\u0388\u03BE\u03BF\u03B4\u03B1', '\u03A0\u03BB\u03B7\u03C1\u03C9\u03BC\u03AD\u03C2'
                    , '\u0395\u03B9\u03C3\u03C0\u03C1\u03AC\u03BE\u03B5\u03B9\u03C2'],
                ['1', 0, 0, 0, 0],
                ['2', 0, 0, 0, 0],
                ['3', 0, 0, 0, 0],
                ['4', 0, 0, 0, 0],
                ['5', 0, 0, 0, 0],
                ['6', 0, 0, 0, 0],
                ['7', 0, 0, 0, 0],
                ['8', 0, 0, 0, 0],
                ['9', 0, 0, 0, 0],
                ['10', 0, 0, 0, 0],
                ['11', 0, 0, 0, 0],
                ['12', 0, 0, 0, 0]
            ];
            db.collection(uid).doc("cloud").collection("sale")
                .get("date")
                .then(function(querySnapshot) {
                    querySnapshot.forEach(function(doc) {
                        db.doc(uid + "/cloud/sale/" + doc.id).get().then(documentSnapshot => {
                            if (documentSnapshot.get('date').toMillis() > 1514757500000) {
                                var valueField = documentSnapshot.get('value');
                                var dateField = documentSnapshot.get('date').toDate();
                                var month = dateField.getMonth() + 1;

                                if (month === 12) {
                                    outputArray[12][1] += valueField;
                                } else if (month === 11) {
                                    outputArray[11][1] += valueField;
                                } else if (month === 10) {
                                    outputArray[10][1] += valueField;
                                } else if (month === 9) {
                                    outputArray[9][1] += valueField;
                                } else if (month === 8) {
                                    outputArray[8][1] += valueField;
                                } else if (month === 7) {
                                    outputArray[7][1] += valueField;
                                } else if (month === 6) {
                                    outputArray[6][1] += valueField;
                                } else if (month === 5) {
                                    outputArray[5][1] += valueField;
                                } else if (month === 4) {
                                    outputArray[4][1] += valueField;
                                } else if (month === 3) {
                                    outputArray[3][1] += valueField;
                                } else if (month === 2) {
                                    outputArray[2][1] += valueField;
                                } else {
                                    outputArray[1][1] += valueField;
                                }
                                saleScore += valueField;
                            }
                            drawChart(outputArray);
                            var total = saleScore + collectionsScore - buyScore - paymentScore;
                            $("#buySaleScoreDiv").html("\u03A0\u03C9\u03BB\u03AE\u03C3\u03B5\u03B9\u03C2: " + saleScore.toFixed(2) + "<br>\u0391\u03B3\u03BF\u03C1\u03AD\u03C2: " + buyScore.toFixed(2));
                            $("#CollPayDiv").html("\u0395\u03B9\u03C3\u03C0\u03C1\u03AC\u03BE\u03B5\u03B9\u03C2: " + collectionsScore.toFixed(2) + "<br>\u03A0\u03BB\u03B7\u03C1\u03C9\u03BC\u03AD\u03C2: " + paymentScore.toFixed(2));
                            $("#totalScoreDiv").html("\u03A3\u03CD\u03BD\u03BF\u03BB\u03BF: " + total.toFixed(2));
                        });
                    });
                })
                .catch(function(error) {
                    console.log("Error getting documents: ", error);
                });
            db.collection(uid).doc("cloud").collection("buy")
                .get("date")
                .then(function(querySnapshot) {
                        querySnapshot.forEach(function(doc) {
                                db.doc(uid + "/cloud/buy/" + doc.id).get().then(documentSnapshot => {
                                    if (documentSnapshot.get('date').toMillis() > 1514757500000) {
                                        var valueBuyField = documentSnapshot.get('value');
                                        var dateBuyField = documentSnapshot.get('date').toDate();
                                        var monthBuy = dateBuyField.getMonth() + 1;

                                        if (monthBuy === 12) {
                                            outputArray[12][2] += valueBuyField;
                                        } else if (monthBuy === 11) {
                                            outputArray[11][2] += valueBuyField;
                                        } else if (monthBuy === 10) {
                                            outputArray[10][2] += valueBuyField;
                                        } else if (monthBuy === 9) {
                                            outputArray[9][2] += valueBuyField;
                                        } else if (monthBuy === 8) {
                                            outputArray[8][2] += valueBuyField;
                                        } else if (monthBuy === 7) {
                                            outputArray[7][2] += valueBuyField;
                                        } else if (monthBuy === 6) {
                                            outputArray[6][2] += valueBuyField;
                                        } else if (monthBuy === 5) {
                                            outputArray[5][2] += valueBuyField;
                                        } else if (monthBuy === 4) {
                                            outputArray[4][2] += valueBuyField;
                                        } else if (monthBuy === 3) {
                                            outputArray[3][2] += valueBuyField;
                                        } else if (monthBuy === 2) {
                                            outputArray[2][2] += valueBuyField;
                                        } else {
                                            outputArray[1][2] += valueBuyField;
                                        }
                                        buyScore += valueBuyField;
                                    }
                                    drawChart(outputArray);
                                    var total = saleScore + collectionsScore - buyScore - paymentScore;
                                    $("#buySaleScoreDiv").html("\u03A0\u03C9\u03BB\u03AE\u03C3\u03B5\u03B9\u03C2: " + saleScore.toFixed(2) + "<br>\u0391\u03B3\u03BF\u03C1\u03AD\u03C2: " + buyScore.toFixed(2));
                                    $("#CollPayDiv").html("\u0395\u03B9\u03C3\u03C0\u03C1\u03AC\u03BE\u03B5\u03B9\u03C2: " + collectionsScore.toFixed(2) + "<br>\u03A0\u03BB\u03B7\u03C1\u03C9\u03BC\u03AD\u03C2: " + paymentScore.toFixed(2));
                                    $("#totalScoreDiv").html("\u03A3\u03CD\u03BD\u03BF\u03BB\u03BF: " + total.toFixed(2));
                                });
                        });
                })
                .catch(function(error) {
                    console.log("Error getting documents: ", error);
                });
            db.collection(uid).doc("cloud").collection("payment")
                .get("date")
                .then(function(querySnapshot) {
                        querySnapshot.forEach(function(doc) {
                            db.doc(uid + "/cloud/payment/" + doc.id).get().then(documentSnapshot => {
                                    if (documentSnapshot.get('date').toMillis() > 1514757500000) {
                                        var valuePaymentField = documentSnapshot.get('paymentAmount');
                                        var datePaymentField = documentSnapshot.get('date').toDate();
                                        var monthBuy = datePaymentField.getMonth() + 1;

                                        if (monthBuy === 12) {
                                            outputArray[12][3] += valuePaymentField;
                                        } else if (monthBuy === 11) {
                                            outputArray[11][3] += valuePaymentField;
                                        } else if (monthBuy === 10) {
                                            outputArray[10][3] += valuePaymentField;
                                        } else if (monthBuy === 9) {
                                            outputArray[9][3] += valuePaymentField;
                                        } else if (monthBuy === 8) {
                                            outputArray[8][3] += valuePaymentField;
                                        } else if (monthBuy === 7) {
                                            outputArray[7][3] += valuePaymentField;
                                        } else if (monthBuy === 6) {
                                            outputArray[6][3] += valuePaymentField;
                                        } else if (monthBuy === 5) {
                                            outputArray[5][3] += valuePaymentField;
                                        } else if (monthBuy === 4) {
                                            outputArray[4][3] += valuePaymentField;
                                        } else if (monthBuy === 3) {
                                            outputArray[3][3] += valuePaymentField;
                                        } else if (monthBuy === 2) {
                                            outputArray[2][3] += valuePaymentField;
                                        } else {
                                            outputArray[1][3] += valuePaymentField;
                                        }
                                        paymentScore += valuePaymentField;
                                    }

                                drawChart(outputArray);
                                var total = saleScore + collectionsScore - buyScore - paymentScore;
                                $("#buySaleScoreDiv").html("\u03A0\u03C9\u03BB\u03AE\u03C3\u03B5\u03B9\u03C2: " + saleScore.toFixed(2) + "<br>\u0391\u03B3\u03BF\u03C1\u03AD\u03C2: " + buyScore.toFixed(2));
                                $("#CollPayDiv").html("\u0395\u03B9\u03C3\u03C0\u03C1\u03AC\u03BE\u03B5\u03B9\u03C2: " + collectionsScore.toFixed(2) + "<br>\u03A0\u03BB\u03B7\u03C1\u03C9\u03BC\u03AD\u03C2: " + paymentScore.toFixed(2));
                                $("#totalScoreDiv").html("\u03A3\u03CD\u03BD\u03BF\u03BB\u03BF: " + total.toFixed(2));
                            });
                        });
                })
                .catch(function(error) {
                    console.log("Error getting documents: ", error);
                });
            db.collection(uid).doc("cloud").collection("collection")
                .get("date")
                .then(function(querySnapshot) {
                    querySnapshot.forEach(function(doc) {
                        db.doc(uid + "/cloud/collection/" + doc.id).get().then(documentSnapshot => {
                            if (documentSnapshot.get('date').toMillis() > 1514757500000) {
                                var valueCollectionField = documentSnapshot.get('paymentAmount');
                                var dateCollectionField = documentSnapshot.get('date').toDate();
                                var monthBuy = dateCollectionField.getMonth() + 1;

                                if (monthBuy === 12) {
                                    outputArray[12][4] += valueCollectionField;
                                } else if (monthBuy === 11) {
                                    outputArray[11][4] += valueCollectionField;
                                } else if (monthBuy === 10) {
                                    outputArray[10][4] += valueCollectionField;
                                } else if (monthBuy === 9) {
                                    outputArray[9][4] += valueCollectionField;
                                } else if (monthBuy === 8) {
                                    outputArray[8][4] += valueCollectionField;
                                } else if (monthBuy === 7) {
                                    outputArray[7][4] += valueCollectionField;
                                } else if (monthBuy === 6) {
                                    outputArray[6][4] += valueCollectionField;
                                } else if (monthBuy === 5) {
                                    outputArray[5][4] += valueCollectionField;
                                } else if (monthBuy === 4) {
                                    outputArray[4][4] += valueCollectionField;
                                } else if (monthBuy === 3) {
                                    outputArray[3][4] += valueCollectionField;
                                } else if (monthBuy === 2) {
                                    outputArray[2][4] += valueCollectionField;
                                } else {
                                    outputArray[1][4] += valueCollectionField;
                                }
                                collectionsScore += valueCollectionField;
                            }
                            drawChart(outputArray);
                            var total = saleScore + collectionsScore - buyScore - paymentScore;
                            $("#buySaleScoreDiv").html("\u03A0\u03C9\u03BB\u03AE\u03C3\u03B5\u03B9\u03C2: " + saleScore.toFixed(2) + "<br>\u0391\u03B3\u03BF\u03C1\u03AD\u03C2: " + buyScore.toFixed(2));
                            $("#CollPayDiv").html("\u0395\u03B9\u03C3\u03C0\u03C1\u03AC\u03BE\u03B5\u03B9\u03C2: " + collectionsScore.toFixed(2) + "<br>\u03A0\u03BB\u03B7\u03C1\u03C9\u03BC\u03AD\u03C2: " + paymentScore.toFixed(2));
                            $("#totalScoreDiv").html("\u03A3\u03CD\u03BD\u03BF\u03BB\u03BF: " + total.toFixed(2));
                        });
                    });
                })
                .catch(function(error) {
                    console.log("Error getting documents: ", error);
                });
            drawChart(outputArray);
            var total = saleScore + collectionsScore - buyScore - paymentScore;
            $("#buySaleScoreDiv").html("\u03A0\u03C9\u03BB\u03AE\u03C3\u03B5\u03B9\u03C2: " + saleScore.toFixed(2) + "<br>\u0391\u03B3\u03BF\u03C1\u03AD\u03C2: " + buyScore.toFixed(2));
            $("#CollPayDiv").html("\u0395\u03B9\u03C3\u03C0\u03C1\u03AC\u03BE\u03B5\u03B9\u03C2: " + collectionsScore.toFixed(2) + "<br>\u03A0\u03BB\u03B7\u03C1\u03C9\u03BC\u03AD\u03C2: " + paymentScore.toFixed(2));
            $("#totalScoreDiv").html("\u03A3\u03CD\u03BD\u03BF\u03BB\u03BF: " + total.toFixed(2));
        }
    });
}

var drawChart = function(withData) {

        var data = google.visualization.arrayToDataTable(withData);

        var options = {
            title: '\u0391\u03C0\u03CC\u03B4\u03BF\u03C3\u03B7 \u0388\u03C4\u03BF\u03C5\u03C2',
            hAxis: {title: '\u039C\u03AE\u03BD\u03B5\u03C2', titleTextStyle: {color: '#333'}},
            vAxis: {minValue: 0}
        };

        var chart = new google.visualization.AreaChart(document.getElementById('chart_div'));
        chart.draw(data, options);
};