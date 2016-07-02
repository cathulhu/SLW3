package com.prototype.balcorasystems.slw3;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class UI_Fragment_Loan extends Fragment {

//    public static void loadProfile (Object_Profile inputProfile){
//        selectedProfile = inputProfile;
//    }


    public boolean check_info_ready ()
    {
        boolean result = false;

        if (loanPrincipal != -5 && apr != -5 && loanChoiceCategory != null && loanChoiceCode != null && loanStatus != null)
        {
            result=true;
        }
        return result;
    }

    public Object_Loan getLast ()
    {
        SQL_DataSource dataSource = new SQL_DataSource(getContext());
        Object_Loan savedLoan = dataSource.getLastLoanDbEntry();

        return savedLoan;
    }

    public void deleteWholeDb (Context context)
    {
        SQL_DataSource dataSource = new SQL_DataSource(getContext());
        dataSource.deleteDbFile(context);
    }

    public boolean isSqlEmpty () {
        boolean result = true;
        SQL_DataSource dataSource = new SQL_DataSource(getContext());
        int count = dataSource.getLoanCount();
        if (count > 0)
        {
            result=false;
        }
        return result;

    }

    public void clearData()
    {
        SQL_DataSource dataSource = new SQL_DataSource(getContext());   //check to make sure getContext is correct vs get activity since this is fragment called from main
        dataSource.deleteAllLoans();
        Toast toast = Toast.makeText(getContext(), "sql cleared", Toast.LENGTH_SHORT);
        toast.show();
    }

//    public void saveData (Object_Profile profile) {
//        SQL_DataSource dataSource = new SQL_DataSource(getContext());   //check to make sure getContext is correct vs get activity since this is fragment called from main
//        dataSource.createProfileDbEntry(profile);
//        Toast toast = Toast.makeText(getContext(), "populating profile object and saving to SQL", Toast.LENGTH_SHORT);
//        toast.show();
//    }

//    public void updateData (Object_Profile updatedProfile)
//    {
//        SQL_DataSource dataSource = new SQL_DataSource(getContext());   //check to make sure getContext is correct vs get activity since this is fragment called from main
//        dataSource.updateProfileEntry(updatedProfile);
//
//
//        Toast toast = Toast.makeText(getContext(), "tried to edit " + updatedProfile.getProfileName(), Toast.LENGTH_SHORT);
//        toast.show();
//
//    }
//
    String[] loanStates =
        {           "Deferment",
                    "Grace Period",
                    "Forbearance",
                    "Repayment",
                    "Delinquent",
                    "Loan Rehab",
                    "1st Default",
                    "2nd Default"
        };

    String[] prettyLoantypes =
            {       "Direct Subsidized Loan",
                    "Direct Unsubsidized Loan",
                    "Subsidized Federal Stafford Loan",
                    "Unsubsidized Federal Stafford Loan",
                    "Direct Subsidized Consolidation Loan",
                    "Direct Unsubsidized Consolidation Loan",
                    "FFEL Consolidation Loan",
                    "Direct PLUS Loan for Graduate/Professional Students",
                    "FFEL PLUS Loan for Graduate/Professional Students",
                    "Direct PLUS Loan for Parents",
                    "FFEL PLUS Loan for Parents",
                    "Direct PLUS Consolidation Loan",
                    " **NOT IMPLIMENTED** Federal Perkins Loan",
                    " **NOT IMPLIMENTED** Private Loan"};

    String[] loanCategory =
            {
                    "DIRECT_SUBSIDIZED",
                    "DIRECT_UNSUBSIDIZED",
                    "FFEL_SUBSIDIZED",
                    "FFEL_UNSUBSIDIZED",
                    "DIRECT_SUBSIDIZED",
                    "DIRECT_UNSUBSIDIZED",
                    "FFEL_UNSUBSIDIZED",
                    "DIRECT_PLUS",
                    "FFEL_PLUS",
                    "DIRECT_PLUS",
                    "FFEL_PLUS",
                    "DIRECT_PLUS",
                    "PERKINS",
                    "ADDITIONAL"
            };

    String[] loanCodes = {"D1", "D2", "SF", "SU","D6","D5", "CL", "D3", "GB", "D4", "PL", "D7", "PU", "PV"};

    String loanChoiceCategory;
    String loanStatus;
    String loanChoiceCode;
    String niceLoanName;
    float loanPrincipal =-5;
    float apr =-5;
    boolean currentlyEditing=false;
    static Object_Profile selectedProfile;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.loan, container, false);

//        clearData();

        selectedProfile = MainActivity.storedProfile;
        TextView nameTitle = (TextView) view.findViewById(R.id.whosLoans);
        nameTitle.setText(selectedProfile.getProfileName() + "s Loans");

        final TextView loanSelection = (TextView) view.findViewById(R.id.loanTypeField);
        final EditText loanInput = (EditText) view.findViewById(R.id.debtInput);
        final EditText aprInput = (EditText) view.findViewById(R.id.aprInput);
        final EditText loanStatusText = (EditText) view.findViewById(R.id.loanStatusField);

        Object_Loan fetchedLoan = new Object_Loan();

        if (isSqlEmpty()==false)
        {
            fetchedLoan = getLast();
            loanInput.setText(String.valueOf(fetchedLoan.getLoanPrincipal()));
            aprInput.setText(String.valueOf(fetchedLoan.getLoanAPR()));
            loanSelection.setText(String.valueOf(fetchedLoan.getPrettyName()));
            loanStatusText.setText(String.valueOf(fetchedLoan.getLoanStatus()));
        }



        TextWatcher watcher = new TextWatcher() {
            @Override
            public final void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                String input0 = loanInput.getText().toString();
                String input1 = aprInput.getText().toString();
                //must use .equals for popper equivalence comparison of strings in java

                if (input0.equals("") == false)
                {
                    loanPrincipal = Float.parseFloat(input0);
                }
                else
                {
                    loanPrincipal = 0;
                }

                if (input1.equals("") == false)
                {
                    apr = Float.parseFloat(input1);
                }
                else
                {
                    apr = 0;
                }
            }
        };

        aprInput.addTextChangedListener(watcher);
        loanInput.addTextChangedListener(watcher);

        Button loanTypeButton = (Button) view.findViewById(R.id.loanSelectbutton);
        loanTypeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                AlertDialog.Builder stateBuilder = new AlertDialog.Builder(getContext());
                stateBuilder.setTitle("Select Loan Type");
                stateBuilder.setItems(prettyLoantypes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        loanChoiceCategory = loanCategory[which];
                        loanChoiceCode = loanCodes[which];
                        niceLoanName = prettyLoantypes[which];

                        loanSelection.setText(niceLoanName);
//                        loanSelection.invalidate();
                    }
                });
                AlertDialog dialog = stateBuilder.create();
                dialog.show();



            }


        });

        Button loanStatusButton = (Button) view.findViewById(R.id.loanStatusButton);
        loanStatusButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                AlertDialog.Builder statusBuilder = new AlertDialog.Builder(getContext());
                statusBuilder.setTitle("Select Current Loan Status");
                statusBuilder.setItems(loanStates, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        loanStatus=loanStates[which];

                        loanStatusText.setText(loanStatus);
//                        loanStatusText.invalidate();
                    }
                });
                AlertDialog dialog = statusBuilder.create();
                dialog.show();



            }


        });

        Button saveLoanButton = (Button) view.findViewById(R.id.addLoan);
        saveLoanButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (check_info_ready())
                {
                    Object_Loan loan = new Object_Loan(loanPrincipal, apr, loanChoiceCategory, loanChoiceCode, selectedProfile.getProfileName(), niceLoanName, loanStatus);
                    SQL_DataSource dataSource = new SQL_DataSource(getContext());

                    if (currentlyEditing==true)   //will put condition here to detect if entry is being edited or added but for now just do new loan functionality
                    {

                        Toast toast = Toast.makeText(getContext(), "attempted to save changes to SQL", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else
                    {
                        dataSource.createLoanDbEntry(loan);
                        Toast toast = Toast.makeText(getContext(), "attempted to create new loan entry in SQL", Toast.LENGTH_SHORT);
                        toast.show();

                    }
                }
                else
                {
                    Toast toast = Toast.makeText(getContext(), "Input fields not ready, no changes saved", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }

        });

        return view;
    }
}
