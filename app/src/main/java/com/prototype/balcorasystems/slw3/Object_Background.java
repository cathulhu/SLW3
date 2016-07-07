package com.prototype.balcorasystems.slw3;


import android.util.Log;

public class Object_Background {
    private float annualRaise;      //
    private float[] annualSalaries;   //maybe ill make this array list, have to think about that
    private String owner;
    private String employmentSector;    //
    private int earliestActiveLoanDate;
    private boolean employedFulltime;       //
    private boolean publicServiceForgivenessEligible;
    private boolean collectingSsdi;     //
    private boolean totalDisability;    //
    private boolean teacherAtLowIncomeSchool;   //
    private boolean militaryDisability;     //
    private boolean deceasedChildParentLoan;    //

    public Object_Background() {

    }

    public Object_Background(float raise, String industry, boolean fulltime, boolean ssdi, boolean totallyDisabled, boolean lowIncomeSchoolTeacher, boolean vaDisability, boolean childDeceasedLoan) {
        annualRaise=raise;
        employmentSector=industry;
        employedFulltime=fulltime;
        collectingSsdi=ssdi;
        totalDisability=totallyDisabled;
        teacherAtLowIncomeSchool=lowIncomeSchoolTeacher;
        militaryDisability=vaDisability;
        deceasedChildParentLoan=childDeceasedLoan;

        float fakeTestincome = 10000.00f;

        annualSalaries=this.calculateFutureSalaries(fakeTestincome, annualRaise);
    }

    public float[] calculateFutureSalaries (float income, float raise) {

        float[] results = new float[25];
        results[0]=income;
        float raisedSalary = income;

        for (int i=1; i < 25; i++)
        {
            raisedSalary += raisedSalary*raise;
            results[i]=raisedSalary;

        }

        return results;
    }


    public float getAnnualRaise() {
        return annualRaise;
    }

    public void setAnnualRaise(int annualRaise) {
        this.annualRaise = annualRaise;
    }

    public float[] getAnnualSalaries() {
        return annualSalaries;
    }

    public void setAnnualSalaries(float[] annualSalaries) {
        this.annualSalaries = annualSalaries;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getEmploymentSector() {
        return employmentSector;
    }

    public void setEmploymentSector(String employmentSector) {
        this.employmentSector = employmentSector;
    }

    public int getEarliestActiveLoanDate() {
        return earliestActiveLoanDate;
    }

    public void setEarliestActiveLoanDate(int earliestActiveLoanDate) {
        this.earliestActiveLoanDate = earliestActiveLoanDate;
    }

    public boolean isEmployedFulltime() {
        return employedFulltime;
    }

    public void setEmployedFulltime(boolean employedFulltime) {
        this.employedFulltime = employedFulltime;
    }

    public boolean isPublicServiceForgivenessEligible() {
        return publicServiceForgivenessEligible;
    }

    public void setPublicServiceForgivenessEligible(boolean publicServiceForgivenessEligible) {
        this.publicServiceForgivenessEligible = publicServiceForgivenessEligible;
    }

    public boolean isCollectingSsdi() {
        return collectingSsdi;
    }

    public void setCollectingSsdi(boolean collectingSsdi) {
        this.collectingSsdi = collectingSsdi;
    }

    public boolean isTotalDisability() {
        return totalDisability;
    }

    public void setTotalDisability(boolean totalDisability) {
        this.totalDisability = totalDisability;
    }

    public boolean isTeacherAtLowIncomeSchool() {
        return teacherAtLowIncomeSchool;
    }

    public void setTeacherAtLowIncomeSchool(boolean teacherAtLowIncomeSchool) {
        this.teacherAtLowIncomeSchool = teacherAtLowIncomeSchool;
    }

    public boolean isMilitaryDisability() {
        return militaryDisability;
    }

    public void setMilitaryDisability(boolean militaryDisability) {
        this.militaryDisability = militaryDisability;
    }

    public boolean isDeceasedChildParentLoan() {
        return deceasedChildParentLoan;
    }

    public void setDeceasedChildParentLoan(boolean deceasedChildParentLoan) {
        this.deceasedChildParentLoan = deceasedChildParentLoan;
    }
}
