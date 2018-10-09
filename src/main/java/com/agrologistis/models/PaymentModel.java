package com.agrologistis.models;

import java.util.Date;

public class PaymentModel {
    private Integer paymentID;
    private String shop;
    private Double totalAmount;
    private Double paymentAmount;
    private Double restOfAmount;
    private Date date;
    private String receipt;

    public PaymentModel(){}

    public PaymentModel(Integer paymentID, String shop, Double totalAmount, Double paymentAmount, Double restOfAmount,
                        Date date, String receipt){
        this.paymentID = paymentID;
        this.shop = shop;
        this.totalAmount = totalAmount;
        this.paymentAmount = paymentAmount;
        this.restOfAmount = restOfAmount;
        this.date = date;
        this.receipt = receipt;
    }

    public Integer getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(Integer paymentID) {
        this.paymentID = paymentID;
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
