package com.agrologistis.models;

public class BuyTypesModel {
    private Integer buyTypeID;
    private String name;

    public BuyTypesModel(){}

    public BuyTypesModel(Integer buyTypeID, String name){
        this.buyTypeID = buyTypeID;
        this.name = name;
    }

    public Integer getBuyTypeID() {
        return buyTypeID;
    }

    public void setBuyTypeID(Integer buyTypeID) {
        this.buyTypeID = buyTypeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
