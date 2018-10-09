package com.agrologistis.models;

public class UidModel {

    public static String uid;

    public UidModel(){
    }

    public UidModel (String uid){
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
