package com.agrologistis.controllers;

import com.agrologistis.models.CustomersModel;
import com.agrologistis.models.SaleModel;
import com.agrologistis.models.SaleTypesModel;
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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class AddSaleController {

    private String uid;
    private Integer addSaleID = 1;
    private static final AtomicInteger count = new AtomicInteger(1);

    @RequestMapping(value = "/addsale", method = RequestMethod.GET)
    public String showSalePage(ModelMap model) throws ExecutionException, InterruptedException {
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

        ApiFuture<QuerySnapshot> queryType = db.collection(uid).document("cloud").collection("saletypes").get();

        QuerySnapshot querySnapshotTypes = queryType.get();
        List<QueryDocumentSnapshot> documentsTypes = querySnapshotTypes.getDocuments();
        ArrayList<SaleTypesModel> saleTypeList = new ArrayList<SaleTypesModel>();
        for (QueryDocumentSnapshot document : documentsTypes) {
            SaleTypesModel saleTypesModel = new SaleTypesModel();
            saleTypesModel.setName(document.getString("name"));
            saleTypeList.add(saleTypesModel);
        }

        model.addAttribute("customerList", customer);

        model.addAttribute("saleTypesList", saleTypeList);

        return "addsale";
    }

    @RequestMapping(value = "/addsale", method = RequestMethod.POST)
    public String handleAddSaleRequest(@RequestParam("inputCustomer") String buyer,
                                       @RequestParam("inputType") String type, @RequestParam("inputValue") String value,
                                       @RequestParam("gridRadios") String vat, @RequestParam("inputPaymentMethod")
                                       String paymentMethod, @RequestParam(value="inputDate")
                                           @DateTimeFormat(pattern= "yyyy-MM-dd") Date date,
                                       @RequestParam("inputProduct") String product, @RequestParam String imageUrlInput)
            throws ExecutionException, InterruptedException {

        UidModel user = new UidModel();
        uid = user.getUid();

        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> query = db.collection(uid).document("cloud").collection("sale").get();

        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            if (document.exists()){
                if (Integer.valueOf(document.getId()).equals(addSaleID))
                    addSaleID = count.incrementAndGet();
            }
        }
        SaleModel saleModel = new SaleModel(addSaleID, buyer, addTime(date), type,
                 product, paymentMethod, imageUrlInput, vat, Double.valueOf(value));

        ApiFuture<WriteResult> future = db.collection(uid).document("cloud").collection("sale").
                document(addSaleID.toString()).set(saleModel);

        System.out.println("Update time : " + future.get().getUpdateTime());

        return "redirect:/sales";
    }

    private static Date addTime(Date date){
        Calendar calsale = Calendar.getInstance();
        calsale.setTime(date);
        calsale.set(Calendar.HOUR_OF_DAY, LocalDateTime.now().getHour());
        calsale.set(Calendar.MINUTE, LocalDateTime.now().getMinute());
        calsale.set(Calendar.SECOND, LocalDateTime.now().getSecond());
        calsale.set(Calendar.MILLISECOND, 0);
        return calsale.getTime();
    }
}
