package com.prototype.balcorasystems.slw3;


import java.util.ArrayList;
import java.util.List;

public class Object_Background {//
    private float[] annualSalaries;   //maybe ill make this array list, have to think about that
    private String owner;
    private String employmentSector;    //
    private int earliestActiveLoanDate;
    private boolean employedFulltime;       //
    private boolean publicServiceForgivenessEligible;
    public String publicServiceForgivenessEligibleReason;
    private boolean collectingSsdi;     //
    private boolean totalDisability;    //
    private boolean teacherAtLowIncomeSchool;   //
    private boolean militaryDisability;     //
    private boolean deceasedChildParentLoan;    //
    private boolean spouseOrParentof911Victim;
    private boolean hasPerkinsLoans;
    private boolean hasDirectLoans;
    private boolean peaceCorpTypeService;
    private boolean militaryService;
    private List<String> customQuestionYN = new ArrayList<String>() {{ add("blank"); }};
    private List<String> customQuestionMulti = new ArrayList<String>() {{ add("blank"); }};
    private List<Boolean> customAnswersYN = new ArrayList<>();
    private List<Long> customAnswersMulti = new ArrayList<>();  //long to store time stamps and regular ints for # of years.

    public Object_Background() {

    }

    public Object_Background(String profileOwner, String industry, boolean fulltime, boolean ssdi, boolean totallyDisabled, boolean lowIncomeSchoolTeacher, boolean vaDisability, boolean nine11Victim, boolean peaceCorps, boolean exMilitary) {
        owner=profileOwner;
        employmentSector=industry;
        employedFulltime=fulltime;
        collectingSsdi=ssdi;
        totalDisability=totallyDisabled;
        teacherAtLowIncomeSchool=lowIncomeSchoolTeacher;
        militaryDisability=vaDisability;
        spouseOrParentof911Victim=nine11Victim;
        militaryService=exMilitary;
        peaceCorpTypeService=peaceCorps;

        float fakeTestincome = 10000.00f;

//        annualSalaries=this.calculateFutureSalaries(fakeTestincome, annualRaise);
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



    public boolean isSpouseOrParentof911Victim() {
        return spouseOrParentof911Victim;
    }

    public void setSpouseOrParentof911Victim(boolean spouseOrParentof911Victim) {
        this.spouseOrParentof911Victim = spouseOrParentof911Victim;
    }

    public boolean isPeaceCorpTypeService() {
        return peaceCorpTypeService;
    }

    public void setPeaceCorpTypeService(boolean peaceCorpTypeService) {
        this.peaceCorpTypeService = peaceCorpTypeService;
    }

    public boolean isMilitaryService() {
        return militaryService;
    }

    public void setMilitaryService(boolean militaryService) {
        this.militaryService = militaryService;
    }

    public List<String> getCustomQuestionMulti() {
        return customQuestionMulti;
    }

    public void setCustomQuestionMulti(List<String> customQuestionMulti) {
        this.customQuestionMulti = customQuestionMulti;
    }

    public List<Long> getCustomAnswersMulti() {
        return customAnswersMulti;
    }

    public void setCustomAnswersMulti(List<Long> customAnswersMulti) {
        this.customAnswersMulti = customAnswersMulti;
    }

    public List<Boolean> getCustomAnswersYN() {
        return customAnswersYN;
    }

    public void setCustomAnswersYN(List<Boolean> customAnswersYN) {
        this.customAnswersYN = customAnswersYN;
    }

    public String getPublicServiceForgivenessEligibleReason() {
        return publicServiceForgivenessEligibleReason;
    }

    public void setPublicServiceForgivenessEligibleReason(String publicServiceForgivenessEligibleReason) {
        this.publicServiceForgivenessEligibleReason = publicServiceForgivenessEligibleReason;
    }

    public List<String> getCustomQuestionYN() {
        return customQuestionYN;
    }

    public void setCustomQuestionYN(List<String> customQuestionYN) {
        this.customQuestionYN = customQuestionYN;
    }

    public void setCustomQuestion(String command)
    {
        if (command.equals("clear"))
        {
            this.customQuestionYN.clear();
        }

    }

    public boolean isHasPerkinsLoans() {
        return hasPerkinsLoans;
    }

    public void setHasPerkinsLoans(boolean hasPerkinsLoans) {
        this.hasPerkinsLoans = hasPerkinsLoans;
    }

    public boolean isHasDirectLoans() {
        return hasDirectLoans;
    }

    public void setHasDirectLoans(boolean hasDirectLoans) {
        this.hasDirectLoans = hasDirectLoans;
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
