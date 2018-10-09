package com.agrologistis.models;

public class SaleTypesModel {
    private Integer saleTypeID;
    private String name;

    public SaleTypesModel(){}

    public SaleTypesModel(Integer saleTypeID, String name){
        this.saleTypeID = saleTypeID;
        this.name = name;
    }

    public Integer getSaleTypeID() {
        return saleTypeID;
    }

    public void setSaleTypeID(Integer saleTypeID) {
        this.saleTypeID = saleTypeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
