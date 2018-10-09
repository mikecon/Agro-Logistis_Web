package com.agrologistis.controllers;

import com.agrologistis.models.PaymentModel;
import com.agrologistis.models.ShopModel;
import com.agrologistis.models.UidModel;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class AddPaymentController {

    private String uid;
    private Integer addPaymentID = 1;
    private static final AtomicInteger count = new AtomicInteger(1);

    @RequestMapping(value = "/addpayment", method = RequestMethod.GET)
    public String showAddPaymentPage(ModelMap model) throws ExecutionException, InterruptedException {

        UidModel user = new UidModel();
        uid = user.getUid();

        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> query = db.collection(uid).document("cloud").collection("shop").get();

        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        ArrayList<ShopModel> customer = new ArrayList<ShopModel>();
        for (QueryDocumentSnapshot document : documents) {
            ShopModel shopModel = new ShopModel();
            shopModel.setName(document.getString("name"));
            customer.add(shopModel);
        }
        model.addAttribute("shopList", customer);

        return "addpayment";
    }

    @RequestMapping(value = "/addpayment", method = RequestMethod.POST)
    public String handleAddPaymentRequest(@RequestParam("inputShop") String shop, @RequestParam("inputTotalAmount") String totalAmount,
                                          @RequestParam("inputPaymentAmount") String paymentAmount,
                                          @RequestParam("inputRestAmount") String restAmount, @RequestParam(value="inputDate")
                                              @DateTimeFormat(pattern= "yyyy-MM-dd") Date date, @RequestParam String imageUrlInput)
            throws ExecutionException, InterruptedException {

        UidModel user = new UidModel();
        uid = user.getUid();

        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> query = db.collection(uid).document("cloud").collection("payment").get();

        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            if (document.exists()){
                if (Integer.valueOf(document.getId()).equals(addPaymentID))
                addPaymentID = count.incrementAndGet();
            }
        }

        PaymentModel paymentModel = new PaymentModel(addPaymentID, shop, Double.valueOf(totalAmount),
                Double.valueOf(paymentAmount), Double.valueOf(restAmount), addTime(date), imageUrlInput);

        ApiFuture<WriteResult> future = db.collection(uid).document("cloud").collection("payment").
                document(addPaymentID.toString()).set(paymentModel);

        System.out.println("Update time : " + future.get().getUpdateTime());

        return "redirect:/payments";
    }

    private static Date addTime(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, LocalDateTime.now().getHour());
        cal.set(Calendar.MINUTE, LocalDateTime.now().getMinute());
        cal.set(Calendar.SECOND, LocalDateTime.now().getSecond());
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}
