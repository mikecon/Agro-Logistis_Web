package com.agrologistis.controllers;

import com.agrologistis.models.CollectionModel;
import com.agrologistis.models.UidModel;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

@Controller
public class CollectionController {

    private String uid;

    @RequestMapping(value = "/collections", method = RequestMethod.GET)
    public String showCollectionsPage(ModelMap model) throws ExecutionException, InterruptedException {

        UidModel user = new UidModel();
        uid = user.getUid();

        Firestore db = FirestoreClient.getFirestore();

        CollectionReference sales = db.collection(uid).document("cloud").collection("collection");
        Query query = sales.orderBy("date", Query.Direction.DESCENDING);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        ArrayList<CollectionModel> collection = new ArrayList<CollectionModel>();
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            int DBdate = Objects.requireNonNull(document.getDate("date").getYear());
            if (DBdate >= 118 && DBdate < 119) {

                CollectionModel collectionModel = new CollectionModel();
                collectionModel.setCollectionID(Integer.valueOf(document.getId()));
                collectionModel.setShop(document.getString("shop"));
                collectionModel.setTotalAmount(document.getDouble("totalAmount"));
                collectionModel.setPaymentAmount(document.getDouble("paymentAmount"));
                collectionModel.setRestOfAmount(document.getDouble("restOfAmount"));
                collectionModel.setDate(document.getDate("date"));
                if (document.contains("receipt")) {
                    collectionModel.setReceipt(document.getString("receipt"));
                }
                collection.add(collectionModel);
            }
        }
        model.addAttribute("list", collection);
        Integer year = 2018;
        model.addAttribute("year", year);

        return "collections";
    }

    @RequestMapping(value = "/collections", method = RequestMethod.POST)
    public String handleCollectionsRequest(@RequestParam("inputYear") Integer year, ModelMap model) throws ParseException,
            ExecutionException, InterruptedException {

        int month = 1;
        int day = 1;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyy");
        String dateInString = day+"-"+month+"-"+year;
        Date dateYear = sdf.parse(dateInString);

        UidModel user = new UidModel();
        uid = user.getUid();

        Firestore db = FirestoreClient.getFirestore();

        CollectionReference collections = db.collection(uid).document("cloud").collection("collection");
        Query queryTest = collections.orderBy("date", Query.Direction.DESCENDING);
        ApiFuture<QuerySnapshot> querySnapshot = queryTest.get();
        ArrayList<CollectionModel> collection = new ArrayList<CollectionModel>();
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            int DByear = Objects.requireNonNull(document.getDate("date").getYear());
            if (DByear >= dateYear.getYear() && DByear < dateYear.getYear() + 1) {

                CollectionModel collectionModel = new CollectionModel();
                collectionModel.setCollectionID(Integer.valueOf(document.getId()));
                collectionModel.setShop(document.getString("shop"));
                collectionModel.setTotalAmount(document.getDouble("totalAmount"));
                collectionModel.setPaymentAmount(document.getDouble("paymentAmount"));
                collectionModel.setRestOfAmount(document.getDouble("restOfAmount"));
                collectionModel.setDate(document.getDate("date"));
                if (document.contains("receipt")) {
                    collectionModel.setReceipt(document.getString("receipt"));
                }
                collection.add(collectionModel);
                System.out.println("");
            }
            model.addAttribute("list", collection);
            model.addAttribute("year", year);
        }

        return "collections";
    }

    @RequestMapping(value = "/collections/delete/{collectionID}", method = RequestMethod.GET)
    public String handleDeletePaymentRequest(@PathVariable("collectionID") int collectionID) throws ExecutionException, InterruptedException {
        UidModel user = new UidModel();
        uid = user.getUid();
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = db.collection(uid).document("cloud").
                collection("collection").document(String.valueOf(collectionID)).delete();
        System.out.println("Update time : " + writeResult.get().getUpdateTime());
        return "redirect:/collections";
    }

}
