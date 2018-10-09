package com.agrologistis.controllers;

import com.agrologistis.models.SaleTypesModel;
import com.agrologistis.models.UidModel;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class SaleTypesController {

    private String uid;
    private Integer saleTypeID = 1;
    private static final AtomicInteger count = new AtomicInteger(1);

    @RequestMapping(value = "/saletypes", method = RequestMethod.GET)
    public String showSaleTypesPage(ModelMap model) throws ExecutionException, InterruptedException {

        UidModel user = new UidModel();
        uid = user.getUid();

        Firestore db = FirestoreClient.getFirestore();

        ApiFuture<QuerySnapshot> query = db.collection(uid).document("cloud").collection("saletypes").get();

        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        ArrayList<SaleTypesModel> saleTypesList = new ArrayList<SaleTypesModel>();

        for (QueryDocumentSnapshot document : documents) {

            SaleTypesModel saleTypesModel = new SaleTypesModel();
            saleTypesModel.setSaleTypeID(Integer.valueOf(document.getId()));
            saleTypesModel.setName(document.getString("name"));
            saleTypesList.add(saleTypesModel);

        }
        model.addAttribute("list", saleTypesList);

        return "saletypes";
    }

    @RequestMapping(value = "/saletypes", method = RequestMethod.POST)
    public String handleSaleTypesRequest(@RequestParam String nameInput) throws ExecutionException, InterruptedException {

        UidModel user = new UidModel();
        uid = user.getUid();

        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> query = db.collection(uid).document("cloud").collection("saletypes").get();

        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            if (document.exists()){
                if (Integer.valueOf(document.getId()).equals(saleTypeID))
                saleTypeID = count.incrementAndGet();
            }
        }

        SaleTypesModel saleTypesModel = new SaleTypesModel(saleTypeID, nameInput);

        ApiFuture<WriteResult> future = db.collection(uid).document("cloud").collection("saletypes").
                document(saleTypeID.toString()).set(saleTypesModel);

        System.out.println("Update time : " + future.get().getUpdateTime());

        return "redirect:/saletypes";
    }

    @RequestMapping(value = "/saletypes/delete/{saleTypeID}", method = RequestMethod.GET)
    public String handleDeleteSaleTypesRequest(@PathVariable("saleTypeID") int saleTypeID) throws ExecutionException, InterruptedException {
        UidModel user = new UidModel();
        uid = user.getUid();
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = db.collection(uid).document("cloud").collection("saletypes").document(String.valueOf(saleTypeID)).delete();
        System.out.println("Update time : " + writeResult.get().getUpdateTime());
        return "redirect:/saletypes";
    }

}
