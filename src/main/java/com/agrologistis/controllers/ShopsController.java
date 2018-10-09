package com.agrologistis.controllers;

import com.agrologistis.models.CustomersModel;
import com.agrologistis.models.ShopModel;
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

@Controller
public class ShopsController {

    private String uid;

    @RequestMapping(value = "/shops", method = RequestMethod.GET)
    public String showShopsPage(ModelMap model) throws ExecutionException, InterruptedException {
        UidModel user = new UidModel();
        uid = user.getUid();

        Firestore db = FirestoreClient.getFirestore();

        ApiFuture<QuerySnapshot> query = db.collection(uid).document("cloud").collection("shop").get();
        // ...
        // query.get() blocks on response
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        ArrayList<ShopModel> shop = new ArrayList<ShopModel>();
        for (QueryDocumentSnapshot document : documents) {

            ShopModel shopModel = new ShopModel();
            shopModel.setShopID(Integer.valueOf(document.getId()));
            shopModel.setName(document.getString("name"));
            shopModel.setAddress(document.getString("address"));
            shopModel.setContactName(document.getString("contactName"));
            shopModel.setTelephone(document.getString("telephone"));
            shopModel.setVat(document.getString("vat"));
            shop.add(shopModel);

        }
        model.addAttribute("list", shop);
        return "shops";
    }

    @RequestMapping(value = "/shops", method = RequestMethod.POST)
    public String handleShopsRequest() {

        return "shops";
    }

    @RequestMapping(value = "/shops/delete/{shopID}", method = RequestMethod.GET)
    public String handleDeleteShopRequest(@PathVariable("shopID") int shopID) throws ExecutionException, InterruptedException {
        UidModel user = new UidModel();
        uid = user.getUid();
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = db.collection(uid).document("cloud").collection("shop").document(String.valueOf(shopID)).delete();
        System.out.println("Update time : " + writeResult.get().getUpdateTime());
        return "redirect:/shops";
    }
}
