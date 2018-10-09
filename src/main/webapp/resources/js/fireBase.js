var app_fireBase = {};

(function() {

    // Initialize Firebase
    var config = {
        apiKey: "AIzaSyBMOXGXKKDCUjJFfCzsYEJ7RVj2ASfNQzo",
        authDomain: "agrologistis-2889d.firebaseapp.com",
        databaseURL: "https://agrologistis-2889d.firebaseio.com",
        projectId: "agrologistis-2889d",
        storageBucket: "agrologistis-2889d.appspot.com",
        messagingSenderId: "35879260227"
    };
    !firebase.apps.length ? firebase.initializeApp(config) : firebase.app();

    app_fireBase = firebase;
}())