package com.prototype.balcorasystems.slw3;



public class Object_Loan {

    private float loanPrincipal;
    private float loanAPR;
    private float loanBalance;
    private String loanCode;
    private String loanType;
    private String loanOwner;
    private String prettyName;
    private int sqlID;


    public Object_Loan(float principal, float apr, float balance, String code, String name, String type, String owner) {

        loanPrincipal= principal;
        loanAPR=apr;
        loanBalance=balance;
        loanCode = code;
        loanType = type;
        loanOwner = owner;
        prettyName = name;

    }

    public Object_Loan () {

    }

    public float getLoanPrincipal() {
        return loanPrincipal;
    }

    public void setLoanPrincipal(float loanPrincipal) {
        this.loanPrincipal = loanPrincipal;
    }

    public float getLoanAPR() {
        return loanAPR;
    }

    public void setLoanAPR(float loanAPR) {
        this.loanAPR = loanAPR;
    }

    public float getLoanBalance() {
        return loanBalance;
    }

    public void setLoanBalance(float loanBalance) {
        this.loanBalance = loanBalance;
    }

    public String getLoanCode() {
        return loanCode;
    }

    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public String getLoanOwner() {
        return loanOwner;
    }

    public void setLoanOwner(String loanOwner) {
        this.loanOwner = loanOwner;
    }

    public String getPrettyName() {
        return prettyName;
    }

    public void setPrettyName(String prettyName) {
        this.prettyName = prettyName;
    }

    public int getSqlID() {
        return sqlID;
    }

    public void setSqlID(int sqlID) {
        this.sqlID = sqlID;
    }
}
