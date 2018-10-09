package com.agrologistis.controllers;

import com.agrologistis.models.UidModel;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileInputStream;
import java.io.IOException;

@Controller
public class IndexController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String showIndexPage() {
        return "index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public String handleIndexRequest(@RequestParam String user_uid, ModelMap model) throws IOException {

        // Initialize Cloud Firestore
        FileInputStream serviceAccount =
                new FileInputStream("/Users/mikecon/Desktop/AgroLogistis_Web/src/main/resources/agrologistis-2889d-firebase-adminsdk-02exf-6aa1ee68f6.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://agrologistis-2889d.firebaseio.com")
                .build();
        if(FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }

        // Set UID
        UidModel uidModel = new UidModel();
        uidModel.setUid(user_uid);

        model.put("user_uid", uidModel.getUid());
        //System.out.println(uidModel.getUid());

        return "redirect:/home";
    }

}
