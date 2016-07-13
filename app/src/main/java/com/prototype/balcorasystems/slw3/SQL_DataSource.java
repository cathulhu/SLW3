package com.prototype.balcorasystems.slw3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;


public class SQL_DataSource {

    private SQL_LocalStorageHandler storageHandler;

    public SQL_DataSource(Context context) {
        storageHandler = new SQL_LocalStorageHandler(context);
    }

    private SQLiteDatabase open() {

        return storageHandler.getWritableDatabase();
    }

    private void close(SQLiteDatabase activeDB) {
        activeDB.close();
    }

    public void deleteDbFile(Context context)
    {
        context.deleteDatabase("localData.db");

    }


    public ArrayList<Object_Profile> readAllProfiles() {

        SQLiteDatabase db = open();
        db.beginTransaction();
        String selectionQuery = "SELECT * FROM " + storageHandler.TABLE_PROFILE;
        ArrayList<Object_Profile> profiles = new ArrayList<Object_Profile>();

        Cursor cursor = db.rawQuery(selectionQuery, null);

        if (cursor.moveToFirst()) {                //note using implicit positive if statement here
            do {
                Object_Profile profile = new Object_Profile();

                profile.setSqlID(Integer.parseInt(cursor.getString(0)));
                profile.setFamilySize(Integer.parseInt(cursor.getString(1)));
                profile.setGrossIncome(Integer.parseInt(cursor.getString(2)));
                profile.setSpouseIncome(Integer.parseInt(cursor.getString(3)));
                profile.setFilingStatus(cursor.getString(4));
                profile.setFilingState(cursor.getString(5));
                profile.setProfileName(cursor.getString(6));

                profiles.add(profile);
            } while (cursor.moveToNext());
        }

        db.setTransactionSuccessful();
        db.endTransaction();
        close(db);

        return profiles;
    }

    public void deleteAllProfiles() {
        SQLiteDatabase db = open();
        db.beginTransaction();
        db.delete(storageHandler.TABLE_PROFILE, null, null);
        db.setTransactionSuccessful();
        db.endTransaction();
        close(db);
    }

    public void updateProfileEntry(Object_Profile profile) {
        SQLiteDatabase db = open();
//        db.beginTransaction();

        ContentValues profileValues = new ContentValues();
        profileValues.put(storageHandler.FAMILY_SIZE, profile.getFamilySize());
        profileValues.put(storageHandler.GROSS_INCOME, profile.getGrossIncome());
        profileValues.put(storageHandler.SPOUSE_INCOME, profile.getSpouseIncome());
        profileValues.put(storageHandler.FILING_STATUS, profile.getFilingStatus());
        profileValues.put(storageHandler.FILING_STATE, profile.getFilingState());
        profileValues.put(storageHandler.PROFILE_NAME, profile.getProfileName());

        String condition = storageHandler.PROFILE_NAME + "= ?";
        String[] args ={profile.getProfileName()};

        db.update(storageHandler.TABLE_PROFILE, profileValues, condition, args);

        // CRAZY ERROR WHERE DATABASE CHANGES DISSAPEAR IF I CLOSE?! PROBLEM ON SIMULATOR AND REAL PHONES?! IS IT MODIFYING SOME OTHER DB FILE?? IS IT NOT SAVING/COMMITING CHANGES? NEEDS TO BE ASYNC(thread issue?!)
//        db.endTransaction();
//        close(db);
//
//
//        Object_Profile checkResult = getLastProfileDbEntry();


    }

    public int getProfileCount()
    {
        int count;
        SQLiteDatabase db = open();
        String query = "SELECT COUNT(*) FROM " + storageHandler.TABLE_PROFILE;
        Cursor cursor = db.rawQuery(query, null);

        db.beginTransaction();
        cursor.moveToFirst();
        count=Integer.parseInt(cursor.getString(0));

        cursor.close();
        db.endTransaction();
//        db.close();
        return count;
    }

    public boolean isProfileNameUnique(Object_Profile profileToCheck)
    {
        int count;
        boolean unique=false;
        SQLiteDatabase db = open();
        String query = "SELECT COUNT(*) FROM " + storageHandler.TABLE_PROFILE + " WHERE " + storageHandler.PROFILE_NAME + "=" + "\"" + profileToCheck.getProfileName() + "\"";
        Cursor cursor = db.rawQuery(query, null);

        db.beginTransaction();
        cursor.moveToFirst();
        count=Integer.parseInt(cursor.getString(0));

        if (count == 0)
        {
            unique=true;
        }

        cursor.close();
        db.endTransaction();
//        db.close();
        return unique;

    }


    public Object_Profile getLastProfileDbEntry () {

        SQLiteDatabase db = open();
        String selectionQuery = "SELECT * FROM "  + storageHandler.TABLE_PROFILE + " ORDER BY " + storageHandler.PROFILE_KEY_ID + " DESC LIMIT 1";
        Cursor cursor = db.rawQuery(selectionQuery, null);
        Object_Profile storedProfile = new Object_Profile();


            db.beginTransaction();
            ContentValues profileValues = new ContentValues();

            cursor.moveToFirst();

                storedProfile.setSqlID(Integer.parseInt(cursor.getString(0)));
                storedProfile.setFamilySize(Integer.parseInt(cursor.getString(1)));
                storedProfile.setGrossIncome(Integer.parseInt(cursor.getString(2)));
                storedProfile.setSpouseIncome(Integer.parseInt(cursor.getString(3)));
                storedProfile.setFilingStatus(cursor.getString(4));
                storedProfile.setFilingState(cursor.getString(5));
                storedProfile.setProfileName(cursor.getString(6));
        cursor.close();
        db.endTransaction();
//        close(db);
        return storedProfile;
    }


    public void createProfileDbEntry(Object_Profile profile) {
        SQLiteDatabase db = open();
        db.beginTransaction();

        ContentValues profileValues = new ContentValues();
        profileValues.put(storageHandler.FAMILY_SIZE, profile.getFamilySize());
        profileValues.put(storageHandler.GROSS_INCOME, profile.getGrossIncome());
        profileValues.put(storageHandler.SPOUSE_INCOME, profile.getSpouseIncome());
        profileValues.put(storageHandler.FILING_STATUS, profile.getFilingStatus());
        profileValues.put(storageHandler.FILING_STATE, profile.getFilingState());
        profileValues.put(storageHandler.PROFILE_NAME, profile.getProfileName());

        db.insert(SQL_LocalStorageHandler.TABLE_PROFILE, null, profileValues);  //2nd argument only used for inserting row with no data

        ContentValues sqlIDresult;
        String selectionQuery = "SELECT MAX ("  + storageHandler.PROFILE_KEY_ID + ") FROM " + storageHandler.TABLE_PROFILE;
        Cursor cursor = db.rawQuery(selectionQuery, null);
        cursor.moveToFirst();
        int id = Integer.parseInt(cursor.getString(0));
        cursor.close();

        profile.setSqlID(id);

        db.setTransactionSuccessful();
        db.endTransaction();
//        close(db);                                  //for some reason close works ok for inserting rows

    }








    public ArrayList<Object_Loan> readAllLoans() {

        SQLiteDatabase db = open();
        db.beginTransaction();
        String selectionQuery = "SELECT * FROM " + storageHandler.TABLE_LOAN;
        ArrayList<Object_Loan> loans = new ArrayList<Object_Loan>();

        Cursor cursor = db.rawQuery(selectionQuery, null);

        if (cursor.moveToFirst()) {                //note using implicit positive if statement here
            do {
                Object_Loan loan = new Object_Loan();

                loan.setSqlID(Integer.parseInt(cursor.getString(0)));
                loan.setLoanOwner(cursor.getString(1));
                loan.setLoanType(cursor.getString(2));
                loan.setLoanCode(cursor.getString(3));
                loan.setLoanPrincipal(Float.parseFloat(cursor.getString(4)));
                loan.setLoanAPR(Float.parseFloat(cursor.getString(5)));
                loan.setLoanBalance(Float.parseFloat(cursor.getString(6)));
                loan.setLoanStatus(cursor.getString(7));
//                loan.setStatusElapsedDays(Integer.parseInt(cursor.getString(8)));
                loan.setPrettyName(cursor.getString(9));

                loans.add(loan);
            } while (cursor.moveToNext());
        }

        db.setTransactionSuccessful();
        db.endTransaction();
        close(db);

        return loans;
    }

//Integer.parseInt(cursor.getString(3))


    public void createLoanDbEntry (Object_Loan loan) {
        SQLiteDatabase db = open();
        db.beginTransaction();

        ContentValues loanValues = new ContentValues();
        loanValues.put(storageHandler.LOAN_CHOICE_CATEGORY, loan.getLoanType());
        loanValues.put(storageHandler.LOAN_CHOICE_CODE, loan.getLoanCode());
        loanValues.put(storageHandler.LOAN_PRINCIPAL, loan.getLoanPrincipal());
        loanValues.put(storageHandler.LOAN_APR, loan.getLoanAPR());
        loanValues.put(storageHandler.LOAN_OWNER, loan.getLoanOwner());
        loanValues.put(storageHandler.LOAN_BALANCE, loan.getLoanBalance());
        loanValues.put(storageHandler.LOAN_STATUS, loan.getLoanStatus());
        loanValues.put(storageHandler.LOAN_NICE_NAME, loan.getPrettyName());
        db.insert(SQL_LocalStorageHandler.TABLE_LOAN, null, loanValues);


        db.setTransactionSuccessful();
        db.endTransaction();
        close(db);
    }

    public Object_Loan getLastLoanDbEntry () {

        SQLiteDatabase db = open();
        String selectionQuery = "SELECT * FROM "  + storageHandler.TABLE_LOAN + " ORDER BY " + storageHandler.LOAN_KEY_ID + " DESC LIMIT 1";
        Cursor cursor = db.rawQuery(selectionQuery, null);
        Object_Loan storedLoan = new Object_Loan();

        db.beginTransaction();
        ContentValues profileValues = new ContentValues();

        cursor.moveToFirst();

        storedLoan.setSqlID(Integer.parseInt(cursor.getString(0)));
        storedLoan.setLoanOwner(cursor.getString(1));
        storedLoan.setLoanType(cursor.getString(2));
        storedLoan.setLoanCode(cursor.getString(3));
        storedLoan.setLoanPrincipal(Float.parseFloat(cursor.getString(4)));
        storedLoan.setLoanAPR(Float.parseFloat(cursor.getString(5)));
        storedLoan.setLoanBalance(Float.parseFloat(cursor.getString(6)));
        storedLoan.setLoanStatus(cursor.getString(7));
        storedLoan.setPrettyName(cursor.getString(9));

        cursor.close();
        db.endTransaction();
        close(db);
        return storedLoan;
    }

    public int getLoanCount()
    {
        int count;
        SQLiteDatabase db = open();
        String query = "SELECT COUNT(*) FROM " + storageHandler.TABLE_LOAN;
        Cursor cursor = db.rawQuery(query, null);

        db.beginTransaction();
        cursor.moveToFirst();
        count=Integer.parseInt(cursor.getString(0));

        cursor.close();
        db.endTransaction();
        db.close();
        return count;
    }

    public void deleteAllLoans() {
        SQLiteDatabase db = open();
        db.beginTransaction();
        db.delete(storageHandler.TABLE_LOAN, null, null);
        db.setTransactionSuccessful();
        db.endTransaction();
        close(db);
    }



}
