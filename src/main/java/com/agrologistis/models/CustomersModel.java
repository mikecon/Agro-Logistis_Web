package com.agrologistis.models;

public class CustomersModel {

    private Integer buyID;
    private String name;
    private String address;
    private String contactName;
    private String telephone;
    private String vat;

    public CustomersModel(){
    }

    public CustomersModel(Integer buyID, String name, String address, String contactName, String telephone, String vat){
        this.buyID = buyID;
        this.name = name;
        this.address = address;
        this.contactName = contactName;
        this.telephone = telephone;
        this.vat = vat;
    }

    public Integer getBuyID() {
        return buyID;
    }

    public void setBuyID(Integer buyID) {
        this.buyID = buyID;
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
