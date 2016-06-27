package com.prototype.balcorasystems.slw3;

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
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class UI_Fragment_Info extends Fragment {

    public boolean check_info_ready ()
    {
        boolean result = false;

        if (familysizeInput != -5 && incomeValue != -5 && spouseIncomeValue != -5 && taxState != null && name!= null)
        {
            result=true;
        }
        return result;
    }

    public Object_Profile getLast ()
    {
        SQL_DataSource dataSource = new SQL_DataSource(getContext());
        Object_Profile lastProfile=dataSource.getLastProfileDbEntry();

        return lastProfile;
    }

    public ArrayList<Object_Profile> getData ()
    {
        SQL_DataSource dataSource = new SQL_DataSource(getContext());   //check to make sure getContext is correct vs get activity since this is fragment called from main
        ArrayList<Object_Profile> resultsArrayList = new ArrayList<>();
        resultsArrayList = dataSource.readAllProfiles();
        Toast toast = Toast.makeText(getContext(), resultsArrayList.size() + " SQL entries found", Toast.LENGTH_SHORT);
        toast.show();
        return resultsArrayList;
    }

    public void saveData (Object_Profile profile) {
        SQL_DataSource dataSource = new SQL_DataSource(getContext());   //check to make sure getContext is correct vs get activity since this is fragment called from main
        dataSource.createProfileDbEntry(profile);
        Toast toast = Toast.makeText(getContext(), "populating profile object and saving to SQL", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void updateData (Object_Profile updatedProfile)
    {
        SQL_DataSource dataSource = new SQL_DataSource(getContext());   //check to make sure getContext is correct vs get activity since this is fragment called from main
        dataSource.updateProfileEntry(updatedProfile);


        Toast toast = Toast.makeText(getContext(), "tried to edit " + updatedProfile.getProfileName(), Toast.LENGTH_SHORT);
        toast.show();

        Object_Profile postUpdateCheck = getLast();
    }

    public void clearData()
    {
        SQL_DataSource dataSource = new SQL_DataSource(getContext());   //check to make sure getContext is correct vs get activity since this is fragment called from main
        dataSource.deleteAllProfiles();
        Toast toast = Toast.makeText(getContext(), "sql cleared", Toast.LENGTH_SHORT);
        toast.show();
    }

    public boolean isSqlEmpty () {
        boolean result = true;
        SQL_DataSource dataSource = new SQL_DataSource(getContext());
        int count = dataSource.getProfileCount();
        if (count > 0)
        {
            result=false;
        }
        return result;

    }

    public boolean isUniqueName (Object_Profile profileToCheck)
    {
        boolean result = false; //false means it already exists which means edit rather than save new version

        SQL_DataSource dataSource = new SQL_DataSource(getContext());
        result=dataSource.isProfileNameUnique(profileToCheck);

        return result;
    }

    String[] stateList = {"AK", "AL", "AR", "AZ", "CA", "CO", "CT", "DC", "DE", "FL", "GA", "GU", "HI",
            "IA", "ID", "IL", "IN", "KS", "KY", "LA", "MA", "MD", "ME", "MH", "MI", "MN", "MO", "MS", "MT",
            "NC", "ND", "NE", "NH", "NJ", "NM", "NV", "NY", "OH", "OK", "OR", "PA", "PR", "PW", "RI", "SC",
            "SD", "TN", "TX", "UT", "VA", "VI", "VT", "WA", "WI", "WV", "WY"};

    //could make a special object to hold these values but as far as global variables go these are pretty safe
    String taxStatus= "SINGLE";
    int familysizeInput = -5;
    float incomeValue = -5;
    float spouseIncomeValue = -5;
    String taxState;
    String name;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (check_info_ready()==true)
        {
            Object_Profile updatedProfile = new Object_Profile(familysizeInput, incomeValue, spouseIncomeValue, taxStatus, taxState, name);

            if (isUniqueName(updatedProfile)==false)
            {
                updateData(updatedProfile);
            }
            else
            {
                saveData(updatedProfile);
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

//        clearData();

        View view = inflater.inflate(R.layout.info, container, false);

        final EditText profileName = (EditText) view.findViewById(R.id.profileAssoc);
        final EditText familysizeField = (EditText) view.findViewById(R.id.familySizeInput);
        final EditText incomeInput = (EditText) view.findViewById(R.id.incomeInput);
        final EditText spouseIncomeInput = (EditText) view.findViewById(R.id.spouseIncomeInput);

        if (isSqlEmpty()== false)
        {
            Object_Profile freshProfile = getLast();
            profileName.setText(freshProfile.getProfileName());
            familysizeField.setText( String.valueOf(freshProfile.getFamilySize()) );
            incomeInput.setText( String.valueOf(freshProfile.getGrossIncome()) );
            spouseIncomeInput.setText( String.valueOf(freshProfile.getSpouseIncome()) );
        }






//        final ListView profileList = (ListView) view.findViewById(R.id.listView2);
//        profileList.setAdapter(new ArrayAdapter<String>(), );

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
                String input0 = profileName.getText().toString();
                String input1 = familysizeField.getText().toString();
                String input2 = incomeInput.getText().toString();
                String input3 = spouseIncomeInput.getText().toString();
                //must use .equals for popper equivalence comparison of strings in java

                if (input0.equals("") == false)
                {
                    name = input0;
                }

                if (input1.equals("") == false)
                {
                    familysizeInput = Integer.parseInt(familysizeField.getText().toString());
                }
                else
                {
                    familysizeInput = -5;
                }

                if (input2.equals("") == false)
                {
                    incomeValue = Float.parseFloat(incomeInput.getText().toString());
                }
                else
                {
                    incomeValue= -5;
                }

                if (input3.equals("") == false)
                {
                    spouseIncomeValue = Float.parseFloat(spouseIncomeInput.getText().toString());
                }
                else
                {
                    spouseIncomeValue= -5;
                }

            }

        };

        familysizeField.addTextChangedListener(watcher);
        incomeInput.addTextChangedListener(watcher);
        spouseIncomeInput.addTextChangedListener(watcher);
        profileName.addTextChangedListener(watcher);

        final RadioGroup taxStatusRadio = (RadioGroup) view.findViewById(R.id.taxInput);
        taxStatusRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                int value = 0;
                int choice = taxStatusRadio.getCheckedRadioButtonId();
                if (choice == R.id.marriedSepRadio) {
                    value = 1;
                } else if (choice == R.id.marriedTogRadio) {
                    value = 2;
                } else if (choice == R.id.headRadio) {
                    value = 3;
                }

                String[] taxFilingOptions = {"SINGLE", "MARRIED_FILING_SINGLY", "MARRIED_FILING_JOINTLY", "HEAD_OF_HOUSEHOLD"};
                taxStatus = taxFilingOptions[value];
            }

        });


        Button stateButton = (Button) view.findViewById(R.id.stateButton);
        stateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                AlertDialog.Builder stateBuilder = new AlertDialog.Builder(getContext());
                stateBuilder.setTitle("Select Tax Filing State");
                stateBuilder.setItems(stateList, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        taxState = stateList[which];

                    }
                });
                AlertDialog dialog = stateBuilder.create();
                dialog.show();
            }

        });




        return view;
    }
}
