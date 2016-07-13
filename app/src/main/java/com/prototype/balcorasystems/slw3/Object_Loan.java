package com.prototype.balcorasystems.slw3;



public class Object_Loan {

    private float loanPrincipal;
    private float loanBalance;
    private float loanAPR;
    private String loanStatus;
    private String loanCode;
    private String loanType;
    private String loanOwner;
    private String prettyName;
    private boolean inDefault;
    private boolean isPrivateLoan;
    private boolean inGracePeriod;
    private int repaymentMonth;
    private int statusElapsedDays;
    private int sqlID;

    //many of these variables not implemented in SQLdb yet

    public Object_Loan(float principal, float apr, String type, String code, String owner, String niceName, String status) {

        loanPrincipal = principal;
        loanAPR = apr;
        loanBalance = principal;
        loanCode = code;
        loanType = type;
        loanOwner = owner;
        prettyName = niceName;
        loanStatus = status;


    }

    public Object_Loan() {

    }


    public float getLoanPrincipal() {
        return loanPrincipal;
    }

    public void setLoanPrincipal(float loanPrincipal) {
        this.loanPrincipal = loanPrincipal;
    }

    public float getLoanBalance() {
        return loanBalance;
    }

    public void setLoanBalance(float loanBalance) {
        this.loanBalance = loanBalance;
    }

    public float getLoanAPR() {
        return loanAPR;
    }

    public void setLoanAPR(float loanAPR) {
        this.loanAPR = loanAPR;
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

    public boolean isInDefault() {
        return inDefault;
    }

    public void setInDefault(boolean inDefault) {
        this.inDefault = inDefault;
    }

    public boolean isPrivateLoan() {
        return isPrivateLoan;
    }

    public void setPrivateLoan(boolean privateLoan) {
        isPrivateLoan = privateLoan;
    }

    public boolean isInGracePeriod() {
        return inGracePeriod;
    }

    public void setInGracePeriod(boolean inGracePeriod) {
        this.inGracePeriod = inGracePeriod;
    }

    public int getRepaymentMonth() {
        return repaymentMonth;
    }

    public void setRepaymentMonth(int repaymentMonth) {
        this.repaymentMonth = repaymentMonth;
    }

    public int getSqlID() {
        return sqlID;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public int getStatusElapsedDays() {
        return statusElapsedDays;
    }

    public void setStatusElapsedDays(int statusElapsedDays) {
        this.statusElapsedDays = statusElapsedDays;
    }

    public void setSqlID(int sqlID) {
        this.sqlID = sqlID;
    }
}
