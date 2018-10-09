var receiptApp = {};

(function () {
    var firebase = app_fireBase;
    var uid = null;
    var receipt = document.getElementById("receipt");
    var imageUrlInput = document.getElementById("imageUrlInput");
    var upload = document.getElementById("upload");
    // Create a root reference
    var storageRef = firebase.storage().ref();

    firebase.auth().onAuthStateChanged(function(user) {
        if (user) {
            //User is signed in.
            uid = user.uid;

            upload.style.display = "none";

            receipt.addEventListener('change', function (e) {
                upload.style.display = "block";
                // Get file
                image = e.target.files[0];
                time = new Date().getTime();

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

    receiptApp.logOut = logOut;

    function uploadImage() {
        // Create a storage ref
        var imageRef = storageRef.child(uid +'/cloud/receipts/' + time.toString() + '/' + image.name);

        // Upload File
        var task = imageRef.put(image);

        //Update progress bar
        task.on('state_changed',

            function progress(snapshot) {
                var percentage = ( snapshot.bytesTransferred / snapshot.totalBytes ) * 100;
                //uploader.value = percentage;
                $(".progress-bar").width(percentage+'%');
                $(".progress-bar").html('<div id="progress-status">'+ percentage+' %</div>');
            },

            function error(err) {

            },

            function complete() {
                var imageKey = firebase.database().ref(uid +'/cloud/images/').push().key;
                imageRef.getDownloadURL().then(function(url) {
                    var downloadURL = url;
                    var updates = {};
                    var uploadData = {url: downloadURL};
                    updates[uid +'/cloud/images/' + imageKey] = uploadData;
                    firebase.database().ref().update(updates);
                    //upload.style.display = "none";
                    imageUrlInput.value = downloadURL;
                });

            });
    }

    receiptApp.uploadImage = uploadImage;

})();
