package com.agrologistis.models;

import java.util.Date;

public class BuyModel {
    private Integer buyID;
    private String shop;
    private Date date;
    private String type;
    private String product;
    private String paymentMethod;
    private String receipt;
    private Double value;
    private String vat;

    public BuyModel(){ }

    public BuyModel(Integer buyID, String shop, Date date, String type, String product, String paymentMethod,
                    String receipt, String vat, Double value){
        this.buyID = buyID;
        this.shop =  shop;
        this.date = date;
        this.type = type;
        this.product = product;
        this.paymentMethod = paymentMethod;
        this.receipt = receipt;
        this.value = value;
        this.vat = vat;
    }

    public Integer getBuyID() {
        return buyID;
    }

    public void setBuyID(Integer buyID) {
        this.buyID = buyID;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }
}
