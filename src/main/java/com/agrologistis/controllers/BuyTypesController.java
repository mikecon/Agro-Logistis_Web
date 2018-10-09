package com.agrologistis.controllers;

import com.agrologistis.models.BuyTypesModel;
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
public class BuyTypesController {

    private String uid;
    private Integer buyTypeID = 1;
    private static final AtomicInteger count = new AtomicInteger(1);

    @RequestMapping(value = "/buytypes", method = RequestMethod.GET)
    public String showBuyTypesPage(ModelMap model) throws ExecutionException, InterruptedException {

        UidModel user = new UidModel();
        uid = user.getUid();

        Firestore db = FirestoreClient.getFirestore();

        ApiFuture<QuerySnapshot> query = db.collection(uid).document("cloud").collection("buytypes").get();

        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        ArrayList<BuyTypesModel> buyTypesList = new ArrayList<BuyTypesModel>();

        for (QueryDocumentSnapshot document : documents) {

            BuyTypesModel buyTypesModel = new BuyTypesModel();
            buyTypesModel.setBuyTypeID(Integer.valueOf(document.getId()));
            buyTypesModel.setName(document.getString("name"));
            buyTypesList.add(buyTypesModel);

        }
        model.addAttribute("list", buyTypesList);

        return "buytypes";
    }

    @RequestMapping(value = "/buytypes", method = RequestMethod.POST)
    public String handleBuyTypesRequest(@RequestParam String nameInput) throws ExecutionException, InterruptedException {

        UidModel user = new UidModel();
        uid = user.getUid();

        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> query = db.collection(uid).document("cloud").collection("buytypes").get();

        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            if (document.exists()){
                if (Integer.valueOf(document.getId()).equals(buyTypeID))
                buyTypeID = count.incrementAndGet();
            }
        }

        BuyTypesModel buyTypesModel = new BuyTypesModel(buyTypeID, nameInput);

        ApiFuture<WriteResult> future = db.collection(uid).document("cloud").collection("buytypes").
                document(buyTypeID.toString()).set(buyTypesModel);

        System.out.println("Update time : " + future.get().getUpdateTime());

        return "redirect:/buytypes";
    }

    @RequestMapping(value = "/buytypes/delete/{buyTypeID}", method = RequestMethod.GET)
    public String handleDeleteBuyTypesRequest(@PathVariable("buyTypeID") int buyTypeID) throws ExecutionException, InterruptedException {
        UidModel user = new UidModel();
        uid = user.getUid();
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = db.collection(uid).document("cloud").
                collection("buytypes").document(String.valueOf(buyTypeID)).delete();
        System.out.println("Update time : " + writeResult.get().getUpdateTime());
        return "redirect:/buytypes";
    }

}
