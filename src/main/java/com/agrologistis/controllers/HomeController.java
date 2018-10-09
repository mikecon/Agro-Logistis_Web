package com.agrologistis.controllers;

import com.agrologistis.models.*;
import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
public class HomeController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String showHomePage() throws ExecutionException, InterruptedException {

        UidModel user = new UidModel();
        String uid = user.getUid();

        Firestore db = FirestoreClient.getFirestore();

        ApiFuture<QuerySnapshot> query = db.collection(uid).document("cloud").collection("saletypes").get();

        QuerySnapshot querySnapshot = query.get();

        if (querySnapshot.isEmpty()){
            // If is a new member Add Default Data
            CollectionReference sales = db.collection(uid).document("cloud").collection("saletypes");
            List<ApiFuture<WriteResult>> futures = new ArrayList<>();
            futures.add(sales.document("1").set(new SaleTypesModel(1, "Οπωροκηπευτικά")));
            futures.add(sales.document("2").set(new SaleTypesModel(2, "Λαχανικά")));
            futures.add(sales.document("3").set(new SaleTypesModel(3, "Φρούτα")));
            futures.add(sales.document("4").set(new SaleTypesModel(4, "¶λλο")));
            ApiFutures.allAsList(futures).get();
        }

        ApiFuture<QuerySnapshot> queryBuy = db.collection(uid).document("cloud").collection("buytypes").get();

        QuerySnapshot querySnapshotBuy = queryBuy.get();

        if (querySnapshotBuy.isEmpty()){
            // If is a new member Add Default Data
            CollectionReference types = db.collection(uid).document("cloud").collection("buytypes");
            List<ApiFuture<WriteResult>> futures = new ArrayList<>();
            futures.add(types.document("1").set(new BuyTypesModel(1, "Εργαλεία")));
            futures.add(types.document("2").set(new BuyTypesModel(2, "Λίπασμα")));
            futures.add(types.document("3").set(new BuyTypesModel(3, "Σπόροι")));
            futures.add(types.document("4").set(new BuyTypesModel(4, "Καύσιμα")));
            futures.add(types.document("5").set(new BuyTypesModel(5, "Διάφορα")));
            ApiFutures.allAsList(futures).get();
        }

        ApiFuture<QuerySnapshot> queryShop = db.collection(uid).document("cloud").collection("shop").get();

        QuerySnapshot querySnapshotShop = queryShop.get();

        if (querySnapshotShop.isEmpty()){
            // If is a new member Add Default Data
            CollectionReference shops = db.collection(uid).document("cloud").collection("shop");
            List<ApiFuture<WriteResult>> futures = new ArrayList<>();
            futures.add(shops.document("1").set(new ShopModel(1, "Ανώνυμο", "", "", "", "")));
            ApiFutures.allAsList(futures).get();
        }

        ApiFuture<QuerySnapshot> queryBuyer = db.collection(uid).document("cloud").collection("buyer").get();

        QuerySnapshot querySnapshotBuyer = queryBuyer.get();

        if (querySnapshotBuyer.isEmpty()){
            // If is a new member Add Default Data
            CollectionReference customers = db.collection(uid).document("cloud").collection("buyer");
            List<ApiFuture<WriteResult>> futures = new ArrayList<>();
            futures.add(customers.document("1").set(new CustomersModel(1, "Ανώνυμο", "", "", "", "")));
            ApiFutures.allAsList(futures).get();
        }

        return "home";
    }

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public String handleHomeRequest() {

        return "home";
    }

}
