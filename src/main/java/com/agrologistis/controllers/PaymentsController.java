package com.agrologistis.controllers;

import com.agrologistis.models.PaymentModel;
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
public class PaymentsController {

    private String uid;

    @RequestMapping(value = "/payments", method = RequestMethod.GET)
    public String showPaymentsPage(ModelMap model) throws ExecutionException, InterruptedException {

        UidModel user = new UidModel();
        uid = user.getUid();

        Firestore db = FirestoreClient.getFirestore();

        CollectionReference sales = db.collection(uid).document("cloud").collection("payment");
        Query query = sales.orderBy("date", Query.Direction.DESCENDING);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        ArrayList<PaymentModel> payment = new ArrayList<PaymentModel>();
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            int DBdate = Objects.requireNonNull(document.getDate("date").getYear());
            if (DBdate >= 118 && DBdate < 119) {

                PaymentModel paymentModel = new PaymentModel();
                paymentModel.setPaymentID(Integer.valueOf(document.getId()));
                paymentModel.setShop(document.getString("shop"));
                paymentModel.setTotalAmount(document.getDouble("totalAmount"));
                paymentModel.setPaymentAmount(document.getDouble("paymentAmount"));
                paymentModel.setRestOfAmount(document.getDouble("restOfAmount"));
                paymentModel.setDate(document.getDate("date"));
                if (document.contains("receipt")) {
                    paymentModel.setReceipt(document.getString("receipt"));
                }
                payment.add(paymentModel);
            }
        }
        model.addAttribute("list", payment);
        Integer year = 2018;
        model.addAttribute("year", year);

        return "payments";
    }

    @RequestMapping(value = "/payments", method = RequestMethod.POST)
    public String handlePaymentsRequest(@RequestParam("inputYear") Integer year, ModelMap model) throws ParseException,
            ExecutionException, InterruptedException {

        int month = 1;
        int day = 1;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyy");
        String dateInString = day+"-"+month+"-"+year;
        Date dateYear = sdf.parse(dateInString);

        UidModel user = new UidModel();
        uid = user.getUid();

        Firestore db = FirestoreClient.getFirestore();

        CollectionReference sales = db.collection(uid).document("cloud").collection("payment");
        Query queryTest = sales.orderBy("date", Query.Direction.DESCENDING);
        ApiFuture<QuerySnapshot> querySnapshot = queryTest.get();
        ArrayList<PaymentModel> payment = new ArrayList<PaymentModel>();
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            int DByear = Objects.requireNonNull(document.getDate("date").getYear());
            if (DByear >= dateYear.getYear() && DByear < dateYear.getYear() + 1) {

                PaymentModel paymentModel = new PaymentModel();
                paymentModel.setPaymentID(Integer.valueOf(document.getId()));
                paymentModel.setShop(document.getString("shop"));
                paymentModel.setTotalAmount(document.getDouble("totalAmount"));
                paymentModel.setPaymentAmount(document.getDouble("paymentAmount"));
                paymentModel.setRestOfAmount(document.getDouble("restOfAmount"));
                paymentModel.setDate(document.getDate("date"));
                if (document.contains("receipt")) {
                    paymentModel.setReceipt(document.getString("receipt"));
                }
                payment.add(paymentModel);
                System.out.println("");
            }
            model.addAttribute("list", payment);
            model.addAttribute("year", year);
        }

        return "payments";
    }

    @RequestMapping(value = "/payments/delete/{paymentID}", method = RequestMethod.GET)
    public String handleDeletePaymentRequest(@PathVariable("paymentID") int paymentID) throws ExecutionException, InterruptedException {
        UidModel user = new UidModel();
        uid = user.getUid();
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = db.collection(uid).document("cloud").collection("payment").document(String.valueOf(paymentID)).delete();
        System.out.println("Update time : " + writeResult.get().getUpdateTime());
        return "redirect:/payments";
    }

}
