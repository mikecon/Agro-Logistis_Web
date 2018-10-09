package com.agrologistis.controllers;

import com.agrologistis.models.CollectionModel;
import com.agrologistis.models.CustomersModel;
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
public class AddCollectionController {

    private String uid;
    private Integer addCollectionID = 1;
    private static final AtomicInteger count = new AtomicInteger(1);

    @RequestMapping(value = "/addcollection", method = RequestMethod.GET)
    public String showAddCollectionPage(ModelMap model) throws ExecutionException, InterruptedException {

        UidModel user = new UidModel();
        uid = user.getUid();

        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> query = db.collection(uid).document("cloud").collection("buyer").get();

        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        ArrayList<CustomersModel> customer = new ArrayList<CustomersModel>();
        for (QueryDocumentSnapshot document : documents) {
            CustomersModel customersModel = new CustomersModel();
            customersModel.setName(document.getString("name"));
            customer.add(customersModel);
        }
        model.addAttribute("customerList", customer);

        return "addcollection";
    }

    @RequestMapping(value = "/addcollection", method = RequestMethod.POST)
    public String handleAddCollectionRequest(@RequestParam("inputCustomer") String customer, @RequestParam("inputTotalAmount") String totalAmount,
                                          @RequestParam("inputPaymentAmount") String paymentAmount,
                                          @RequestParam("inputRestAmount") String restAmount, @RequestParam(value="inputDate")
                                          @DateTimeFormat(pattern= "yyyy-MM-dd") Date date, @RequestParam String imageUrlInput)
            throws ExecutionException, InterruptedException {

        UidModel user = new UidModel();
        uid = user.getUid();

        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> query = db.collection(uid).document("cloud").collection("collection").get();

        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            if (document.exists()){
                if (Integer.valueOf(document.getId()).equals(addCollectionID))
                addCollectionID = count.incrementAndGet();
            }
        }

        CollectionModel collectionModel = new CollectionModel(addCollectionID, customer, Double.valueOf(totalAmount),
                Double.valueOf(paymentAmount), Double.valueOf(restAmount), addTime(date), imageUrlInput);

        ApiFuture<WriteResult> future = db.collection(uid).document("cloud").collection("collection").
                document(addCollectionID.toString()).set(collectionModel);

        System.out.println("Update time : " + future.get().getUpdateTime());

        return "redirect:/collections";
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
