package com.agrologistis.controllers;

import com.agrologistis.models.*;
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
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class AddBuyController {

    private String uid;
    private Integer addBuyID = 1;
    private static final AtomicInteger count = new AtomicInteger(1);

    @RequestMapping(value = "/addbuy", method = RequestMethod.GET)
    public String showBuyPage(ModelMap model) throws ExecutionException, InterruptedException {
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

        ApiFuture<QuerySnapshot> queryType = db.collection(uid).document("cloud").collection("buytypes").get();

        QuerySnapshot querySnapshotTypes = queryType.get();
        List<QueryDocumentSnapshot> documentsTypes = querySnapshotTypes.getDocuments();
        ArrayList<BuyTypesModel> buyTypeList = new ArrayList<BuyTypesModel>();
        for (QueryDocumentSnapshot document : documentsTypes) {
            BuyTypesModel buyTypesModel = new BuyTypesModel();
            buyTypesModel.setName(document.getString("name"));
            buyTypeList.add(buyTypesModel);
        }

        model.addAttribute("shopList", customer);

        model.addAttribute("buyList", buyTypeList);

        return "addbuy";
    }

    @RequestMapping(value = "/addbuy", method = RequestMethod.POST)
    public String handleAddBuyRequest(@RequestParam("inputShop") String shop,
                                       @RequestParam("inputType") String type, @RequestParam("inputValue") String value,
                                       @RequestParam("gridRadios") String vat, @RequestParam("inputPaymentMethod")
                                               String paymentMethod, @RequestParam(value="inputDate")
                                          @DateTimeFormat(pattern= "yyyy-MM-dd") Date date,
                                       @RequestParam String inputProduct, @RequestParam String imageUrlInput)
            throws ExecutionException, InterruptedException {

        UidModel user = new UidModel();
        uid = user.getUid();

        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> query = db.collection(uid).document("cloud").collection("buy").get();

        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            if (document.exists()){
                if (Integer.valueOf(document.getId()).equals(addBuyID))
                addBuyID = count.incrementAndGet();
            }
        }

        BuyModel buyModel = new BuyModel(addBuyID, shop, addTime(date), type,
                 inputProduct, paymentMethod, imageUrlInput, vat, Double.valueOf(value));

        ApiFuture<WriteResult> future = db.collection(uid).document("cloud").collection("buy").
                document(addBuyID.toString()).set(buyModel);

        System.out.println("Update time : " + future.get().getUpdateTime());

        return "redirect:/buys";
    }

    private static Date addTime(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, LocalDateTime.now().getHour());
        cal.set(Calendar.MINUTE, LocalDateTime.now().getMinute());
        cal.set(Calendar.SECOND, LocalDateTime.now().getSecond());
        cal.set(Calendar.MILLISECOND, 0);
        System.out.println(cal.getTime());
        return cal.getTime();
    }
}
