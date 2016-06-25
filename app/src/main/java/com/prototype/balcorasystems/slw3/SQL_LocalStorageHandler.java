package com.prototype.balcorasystems.slw3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SQL_LocalStorageHandler extends SQLiteOpenHelper{
    //db name & version
    public static String DB_NAME = "localData.db";
    public static int DB_VERSION = 1;
    //db table names
    public static String TABLE_PROFILE = "profile";
    public static String TABLE_LOAN = "loan";

    //profile table columns
    public static final String PROFILE_KEY_ID = "profileID";

    public static String FAMILY_SIZE = "familySize";  //int
    public static String GROSS_INCOME ="grossIncome";   //float
    public static String SPOUSE_INCOME = "spouseIncome";  //float
    public static String FILING_STATUS = "filingStatus";
    public static String FILING_STATE = "filingState";
    public static String PROFILE_NAME = "profileName";

    //loan table columns
    public static String LOAN_ID = "loanID";

    public static String LOAN_OWNER = "loanOwner";
    public static String LOAN_CHOICE_CATEGORY = "loanChoiceCategory";
    public static String LOAN_CHOICE_CODE = "loanChoiceCode";
    public static String LOAN_PRINCIPAL = "loanPrincipal";    //float
    public static String LOAN_APR = "apr";               //float
    public static String LOAN_BALANCE = "loanBalance";               //float

    public SQL_LocalStorageHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }   //not sure what that third thing "cursor factory" is for, leave null for now seems ok, setting 4 item version to 1"


    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_PROFILE_TABLE = "CREATE TABLE " + TABLE_PROFILE + " (" + PROFILE_KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + FAMILY_SIZE + " INTEGER," + GROSS_INCOME + " REAL,"
                                    + SPOUSE_INCOME + " REAL," + FILING_STATUS + " TEXT," + FILING_STATE + " TEXT," + PROFILE_NAME + " TEXT" + ")";

        String CREATE_LOAN_TABLE = "CREATE TABLE " + TABLE_LOAN + " (" + LOAN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + LOAN_OWNER + " TEXT," + LOAN_CHOICE_CATEGORY + " TEXT," + LOAN_CHOICE_CODE + " TEXT,"
                                    + LOAN_PRINCIPAL + " REAL," + LOAN_APR + " REAL," + LOAN_BALANCE + " REAL" +")";

        db.execSQL(CREATE_PROFILE_TABLE);
        db.execSQL(CREATE_LOAN_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
