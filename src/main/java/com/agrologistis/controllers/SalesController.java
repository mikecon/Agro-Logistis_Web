package com.agrologistis.controllers;

import com.agrologistis.models.SaleModel;
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

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

@Controller
public class SalesController {

    private String uid;

    @RequestMapping(value = "/sales", method = RequestMethod.GET)
    public String showSalesPage(ModelMap model) throws ExecutionException, InterruptedException {
        UidModel user = new UidModel();
        uid = user.getUid();

        Firestore db = FirestoreClient.getFirestore();

        CollectionReference sales = db.collection(uid).document("cloud").collection("sale");
        Query query = sales.orderBy("date", Query.Direction.DESCENDING);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        ArrayList<SaleModel> sells = new ArrayList<SaleModel>();
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            int DBdate = Objects.requireNonNull(document.getDate("date").getYear());
            if (DBdate >= 118 && DBdate < 119) {
                SaleModel saleModel = new SaleModel();
                saleModel.setSaleID(Integer.valueOf(document.getId()));
                saleModel.setBuyer(document.getString("buyer"));
                saleModel.setProduct(document.getString("product"));
                saleModel.setType(document.getString("type"));
                saleModel.setDate(document.getDate("date"));
                saleModel.setValue(document.getDouble("value"));
                saleModel.setPaymentMethod(document.getString("paymentMethod"));
                saleModel.setVat(document.getString("vat"));
                if (document.contains("receipt")) {
                    saleModel.setReceipt(document.getString("receipt"));
                }
                sells.add(saleModel);
            }
        }
        model.addAttribute("list", sells);
        Integer year = 2018;
        model.addAttribute("year", year);
        return "sales";
    }

    @RequestMapping(value = "/sales", method = RequestMethod.POST)
    public String handleSalesRequest(@RequestParam("inputYear") Integer year, ModelMap model)
            throws ParseException, ExecutionException, InterruptedException {

        int month = 1;
        int day = 1;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyy");
        String dateInString = day+"-"+month+"-"+year;
        Date dateYear = sdf.parse(dateInString);

        UidModel user = new UidModel();
        uid = user.getUid();

        Firestore db = FirestoreClient.getFirestore();

        CollectionReference sales = db.collection(uid).document("cloud").collection("sale");
        Query queryTest = sales.orderBy("date", Query.Direction.DESCENDING);
        ApiFuture<QuerySnapshot> querySnapshot = queryTest.get();
        ArrayList<SaleModel> sells = new ArrayList<SaleModel>();
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            int DByear = Objects.requireNonNull(document.getDate("date").getYear());
            if (DByear >= dateYear.getYear() && DByear < dateYear.getYear() + 1) {
                SaleModel saleModel = new SaleModel();
                saleModel.setSaleID(Integer.valueOf(document.getId()));
                saleModel.setBuyer(document.getString("buyer"));
                saleModel.setProduct(document.getString("product"));
                saleModel.setType(document.getString("type"));
                saleModel.setDate(document.getDate("date"));
                saleModel.setValue(document.getDouble("value"));
                saleModel.setPaymentMethod(document.getString("paymentMethod"));
                saleModel.setVat(document.getString("vat"));
                if (document.contains("receipt")) {
                    saleModel.setReceipt(document.getString("receipt"));
                }
                sells.add(saleModel);
                System.out.println("");
            }
        }
        model.addAttribute("list", sells);
        model.addAttribute("year", year);

        return "sales";
    }

    @RequestMapping(value = "/sales/delete/{saleID}", method = RequestMethod.GET)
    public String handleDeleteSaleRequest(@PathVariable("saleID") int saleID) throws ExecutionException, InterruptedException {
        UidModel user = new UidModel();
        uid = user.getUid();
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = db.collection(uid).document("cloud").collection("sale").document(String.valueOf(saleID)).delete();
        System.out.println("Update time : " + writeResult.get().getUpdateTime());
        return "redirect:/sales";
    }
}
