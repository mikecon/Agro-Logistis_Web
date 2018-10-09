package com.agrologistis.controllers;

import com.agrologistis.models.*;
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
import java.util.*;
import java.util.concurrent.ExecutionException;

@Controller
public class BuysController {

    private String uid;

    @RequestMapping(value = "/buys", method = RequestMethod.GET)
    public String showBuysPage(ModelMap model) throws ExecutionException, InterruptedException {

        UidModel user = new UidModel();
        uid = user.getUid();

        Firestore db = FirestoreClient.getFirestore();

        CollectionReference sales = db.collection(uid).document("cloud").collection("buy");
        Query query = sales.orderBy("date", Query.Direction.DESCENDING);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        ArrayList<BuyModel> buy = new ArrayList<BuyModel>();
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            int DBdate = document.getDate("date").getYear();
            if (DBdate >= 118 && DBdate < 119) {

                BuyModel buyModel = new BuyModel();
                buyModel.setBuyID(Integer.valueOf(document.getId()));
                buyModel.setShop(document.getString("shop"));
                buyModel.setProduct(document.getString("product"));
                buyModel.setType(document.getString("type"));
                buyModel.setDate(document.getDate("date"));
                buyModel.setValue(document.getDouble("value"));
                buyModel.setPaymentMethod(document.getString("paymentMethod"));
                buyModel.setVat(document.getString("vat"));
                if (document.contains("receipt")) {
                    buyModel.setReceipt(document.getString("receipt"));
                }
                buy.add(buyModel);
            }
        }
        model.addAttribute("list", buy);
        Integer year = 2018;
        model.addAttribute("year", year);

        return "buys";
    }

    @RequestMapping(value = "/buys", method = RequestMethod.POST)
    public String handleBuysRequest(@RequestParam("inputYear") Integer year, ModelMap model)
            throws ParseException, ExecutionException, InterruptedException {

        int month = 1;
        int day = 1;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyy");
        String dateInString = day+"-"+month+"-"+year;
        Date dateYear = sdf.parse(dateInString);

        UidModel user = new UidModel();
        uid = user.getUid();

        Firestore db = FirestoreClient.getFirestore();

        CollectionReference sales = db.collection(uid).document("cloud").collection("buy");
        Query queryTest = sales.orderBy("date", Query.Direction.DESCENDING);
        ApiFuture<QuerySnapshot> querySnapshot = queryTest.get();
        ArrayList<BuyModel> buy = new ArrayList<BuyModel>();
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            int DByear = document.getDate("date").getYear();
            if (DByear >= dateYear.getYear() && DByear < dateYear.getYear() + 1) {
                BuyModel buyModel = new BuyModel();
                buyModel.setBuyID(Integer.valueOf(document.getId()));
                buyModel.setShop(document.getString("shop"));
                buyModel.setProduct(document.getString("product"));
                buyModel.setType(document.getString("type"));
                buyModel.setDate(document.getDate("date"));
                buyModel.setValue(document.getDouble("value"));
                buyModel.setPaymentMethod(document.getString("paymentMethod"));
                buyModel.setVat(document.getString("vat"));
                if (document.contains("receipt")) {
                    buyModel.setReceipt(document.getString("receipt"));
                }
                buy.add(buyModel);
                System.out.println("");
            }
        }
        model.addAttribute("list", buy);
        model.addAttribute("year", year);
        return "buys";
    }

    @RequestMapping(value = "/buys/delete/{buyID}", method = RequestMethod.GET)
    public String handleDeleteBuysRequest(@PathVariable("buyID") int buyID) throws ExecutionException, InterruptedException {
        UidModel user = new UidModel();
        uid = user.getUid();
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = db.collection(uid).document("cloud").collection("buy").document(String.valueOf(buyID)).delete();
        System.out.println("Update time : " + writeResult.get().getUpdateTime());
        return "redirect:/buys";
    }
}
