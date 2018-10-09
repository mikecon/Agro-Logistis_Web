package com.agrologistis.controllers;

import com.agrologistis.models.CustomersModel;
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
import java.util.*;
import java.util.concurrent.ExecutionException;

@Controller
public class CustomersController {

    private String uid;

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public String showCustomersPage(ModelMap model) throws ExecutionException, InterruptedException {
        UidModel user = new UidModel();
        uid = user.getUid();
        //System.out.println(uid);

        Firestore db = FirestoreClient.getFirestore();

        ApiFuture<QuerySnapshot> query = db.collection(uid).document("cloud").collection("buyer").get();
        // ...
        // query.get() blocks on response
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        ArrayList<CustomersModel> customer = new ArrayList<CustomersModel>();
        for (QueryDocumentSnapshot document : documents) {

            CustomersModel customersModel = new CustomersModel();
            customersModel.setBuyID(Integer.valueOf(document.getId()));
            customersModel.setName(document.getString("name"));
            customersModel.setAddress(document.getString("address"));
            customersModel.setContactName(document.getString("contactName"));
            customersModel.setTelephone(document.getString("telephone"));
            customersModel.setVat(document.getString("vat"));
            customer.add(customersModel);

        }
        model.addAttribute("list", customer);

        return "customers";
    }

    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    public String handleCustomersRequest() {

        return "customers";
    }

    @RequestMapping(value = "/customers/delete/{buyID}", method = RequestMethod.GET)
    public String handleDeleteCustomerRequest(@PathVariable("buyID") int buyID) throws ExecutionException, InterruptedException {
        UidModel user = new UidModel();
        uid = user.getUid();
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = db.collection(uid).document("cloud").collection("buyer").document(String.valueOf(buyID)).delete();
        System.out.println("Update time : " + writeResult.get().getUpdateTime());
        return "redirect:/customers";
    }
}
