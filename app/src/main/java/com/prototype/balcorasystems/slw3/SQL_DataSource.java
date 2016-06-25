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

    public void updateDbEntry (Object_Profile profile) {
        SQLiteDatabase db = open();
        db.beginTransaction();

        ContentValues profileValues = new ContentValues();
        profileValues.put(storageHandler.FAMILY_SIZE, profile.getFamilySize());
        profileValues.put(storageHandler.GROSS_INCOME, profile.getGrossIncome());
        profileValues.put(storageHandler.SPOUSE_INCOME, profile.getSpouseIncome());
        profileValues.put(storageHandler.FILING_STATUS, profile.getFilingStatus());
        profileValues.put(storageHandler.FILING_STATE, profile.getFilingState());
        profileValues.put(storageHandler.PROFILE_NAME, profile.getProfileName());
        Log.d("newname ", profile.getProfileName());

        String sqlIDString = Integer.toString(profile.getSqlID());

        db.update(storageHandler.TABLE_PROFILE, profileValues, sqlIDString, null);

        db.endTransaction();
        close(db);
    }

    public int getEntryCount ()
    {
        int count;
        SQLiteDatabase db = open();
        String query = "SELECT COUNT(*) FROM " + storageHandler.TABLE_PROFILE;
        Cursor cursor = db.rawQuery(query, null);

        db.beginTransaction();
        cursor.moveToFirst();
        count=Integer.parseInt(cursor.getString(0));


        db.endTransaction();
        db.close();
        return count;
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

        db.endTransaction();

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
        Cursor cursor = db.rawQuery(selectionQuery, null);;
        cursor.moveToFirst();
        int id = Integer.parseInt(cursor.getString(0));
        cursor.close();

        profile.setSqlID(id);

        db.setTransactionSuccessful();
        db.endTransaction();
        close(db);

    }


//    public void createLoan (Object_Loan loan) {
//        SQLiteDatabase db = open();
//        db.beginTransaction();
//
//        ContentValues loanValues = new ContentValues();
//        loanValues.put(storageHandler.LOAN_CHOICE_CATEGORY, loan.getLoanType());
//        loanValues.put(storageHandler.LOAN_CHOICE_CODE, loan.getLoanCode());
//        loanValues.put(storageHandler.LOAN_PRINCIPAL, loan.getLoanPrincipal());
//        loanValues.put(storageHandler.LOAN_APR, loan.getLoanAPR());
//        loanValues.put(storageHandler.LOAN_OWNER, loan.getLoanOwner());
//        db.insert(SQL_LocalStorageHandler.TABLE_LOAN, null, loanValues);
//
//
//        db.setTransactionSuccessful();
//        db.endTransaction();
//        close(db);
//    }


}
