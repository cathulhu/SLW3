package com.prototype.balcorasystems.slw3;


public class Object_TaxProfile
{
    private int familySize;
    private float grossIncome;
    private float spouseIncome;
    private String filingStatus;
    private String filingState;
    private String profileName;

    public Object_TaxProfile(int size, float gross, float spouse, String status, String state, String name) {
        familySize=size;
        grossIncome=gross;
        spouseIncome=spouse;
        filingStatus=status;
        filingState=state;
        profileName=name;
    }

    public Object_TaxProfile() {
        //overloaded method for spawning with no input ready, not sure which one is better to use yet
    }


    public int getFamilySize() {
        return familySize;
    }

    public void setFamilySize(int familySize) {
        this.familySize = familySize;
    }

    public float getGrossIncome() {
        return grossIncome;
    }

    public void setGrossIncome(float grossIncome) {
        this.grossIncome = grossIncome;
    }

    public float getSpouseIncome() {
        return spouseIncome;
    }

    public void setSpouseIncome(float spouseIncome) {
        this.spouseIncome = spouseIncome;
    }

    public String getFilingStatus() {
        return filingStatus;
    }

    public void setFilingStatus(String filingStatus) {
        this.filingStatus = filingStatus;
    }

    public String getFilingState() {
        return filingState;
    }

    public void setFilingState(String filingState) {
        this.filingState = filingState;
    }
}
