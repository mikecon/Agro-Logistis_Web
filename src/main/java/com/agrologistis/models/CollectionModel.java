package com.agrologistis.models;

import java.util.Date;

public class CollectionModel {
    private Integer collectionID;
    private String shop;
    private Double totalAmount;
    private Double paymentAmount;
    private Double restOfAmount;
    private Date date;
    private String receipt;

    public CollectionModel(){}

    public CollectionModel(Integer collectionID, String shop, Double totalAmount, Double paymentAmount, Double restOfAmount,
                           Date date, String receipt) {
        this.collectionID = collectionID;
        this.shop = shop;
        this.totalAmount = totalAmount;
        this.paymentAmount = paymentAmount;
        this.restOfAmount = restOfAmount;
        this.date = date;
        this.receipt = receipt;
    }

    public Integer getCollectionID() {
        return collectionID;
    }

    public void setCollectionID(Integer collectionID) {
        this.collectionID = collectionID;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Double getRestOfAmount() {
        return restOfAmount;
    }

    public void setRestOfAmount(Double restOfAmount) {
        this.restOfAmount = restOfAmount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }
}
