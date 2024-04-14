package com.example.demo.entity;

public class GSTINResult {
    private String pan;
    private String contactNumber;
    private String gstin;
    private String tradeName;
    private String status;
    private String registeredDate;
    private String constitutionOfBusiness;
    private String city;
    private String stateCode;
    private String pinCode;

    public GSTINResult() {
    }

    public GSTINResult(String pan, String contactNumber, String gstin, String tradeName, String status, String registeredDate, String constitutionOfBusiness, String city, String stateCode, String pinCode) {
        this.pan = pan;
        this.contactNumber = contactNumber;
        this.gstin = gstin;
        this.tradeName = tradeName;
        this.status = status;
        this.registeredDate = registeredDate;
        this.constitutionOfBusiness = constitutionOfBusiness;
        this.city = city;
        this.stateCode = stateCode;
        this.pinCode = pinCode;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getGstin() {
        return gstin;
    }

    public void setGstin(String gstin) {
        this.gstin = gstin;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    public String getConstitutionOfBusiness() {
        return constitutionOfBusiness;
    }

    public void setConstitutionOfBusiness(String constitutionOfBusiness) {
        this.constitutionOfBusiness = constitutionOfBusiness;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    @Override
    public String toString() {
        return "GSTResult{" +
                "pan='" + pan + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", gstin='" + gstin + '\'' +
                ", tradeName='" + tradeName + '\'' +
                ", status='" + status + '\'' +
                ", registeredDate='" + registeredDate + '\'' +
                ", constitutionOfBusiness='" + constitutionOfBusiness + '\'' +
                ", city='" + city + '\'' +
                ", stateCode='" + stateCode + '\'' +
                ", pinCode='" + pinCode + '\'' +
                '}';
    }
}
