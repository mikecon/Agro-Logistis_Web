package com.agrologistis.controllers;

import com.agrologistis.models.CustomersModel;
import com.agrologistis.models.UidModel;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class AddCustomerController {

    private Integer customerID = 1;
    private static final AtomicInteger count = new AtomicInteger(1);

    @RequestMapping(value = "/addcustomer", method = RequestMethod.GET)
    public String showCustomerPage() {

        return "addcustomer";
    }

    @RequestMapping(value = "/addcustomer", method = RequestMethod.POST)
    public String handleAddCustomerRequest(@RequestParam String nameInput,@RequestParam String addressInput,
                                           @RequestParam String contactNameInput,@RequestParam String telephoneInput,
                                           @RequestParam String vatInput) throws ExecutionException, InterruptedException {

        UidModel user = new UidModel();
        String uid = user.getUid();

        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> query = db.collection(uid).document("cloud").collection("buyer").get();

        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            if (document.exists()){
                if (Integer.valueOf(document.getId()).equals(customerID))
                customerID = count.incrementAndGet();
            }
        }

        CustomersModel customersModel = new CustomersModel(customerID, nameInput, addressInput, contactNameInput,
                telephoneInput, vatInput);

        ApiFuture<WriteResult> future = db.collection(uid).document("cloud").collection("buyer").
                document(customerID.toString()).set(customersModel);

        System.out.println("Update time : " + future.get().getUpdateTime());

        return "redirect:/customers";
    }

}
