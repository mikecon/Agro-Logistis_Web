package com.agrologistis.models;

public class ShopModel {
    private Integer shopID;
    private String name;
    private String address;
    private String contactName;
    private String telephone;
    private String vat;

    public ShopModel(){
    }

    public ShopModel(Integer shopID, String name, String address, String contactName, String telephone, String vat){
        this.shopID = shopID;
        this.name = name;
        this.address = address;
        this.contactName = contactName;
        this.telephone = telephone;
        this.vat = vat;
    }

    public Integer getShopID() {
        return shopID;
    }

    public void setShopID(Integer shopID) {
        this.shopID = shopID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }
}
