package com.agrologistis.controllers;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class AddShopController {

    private Integer shopID = 1;
    private static final AtomicInteger count = new AtomicInteger(1);

    @RequestMapping(value = "/addshop", method = RequestMethod.GET)
    public String showShopPage() {

        return "addshop";
    }

    @RequestMapping(value = "/addshop", method = RequestMethod.POST)
    public String handleAddShopRequest(@RequestParam String nameInput, @RequestParam String addressInput,
                                       @RequestParam String contactNameInput, @RequestParam String telephoneInput,
                                       @RequestParam String vatInput) throws ExecutionException, InterruptedException {

        UidModel user = new UidModel();
        String uid = user.getUid();

        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> query = db.collection(uid).document("cloud").collection("shop").get();

        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            if (document.exists()){
                if (Integer.valueOf(document.getId()).equals(shopID))
                shopID = count.incrementAndGet();
            }
        }

        ShopModel shopModel = new ShopModel(shopID, nameInput, addressInput, contactNameInput,
                telephoneInput, vatInput);

        ApiFuture<WriteResult> future = db.collection(uid).document("cloud").collection("shop").
                document(shopID.toString()).set(shopModel);

        System.out.println("Update time : " + future.get().getUpdateTime());

        return "redirect:/shops";
    }

}
