package com.prototype.balcorasystems.slw3;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;



//may want to add arguments to class so fragment knows whether its been spawned to edit or create new object
//for now store results of input in gloabl variables, later hookup dynamic object creation.
public class UI_Fragment_Info extends Fragment {

    public boolean check_info_ready () {
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
        Object_Profile profile=dataSource.getLastProfileDbEntry();

        return profile;
    }

    public ArrayList<Object_Profile> getData () {
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

    public void clearData() {
        SQL_DataSource dataSource = new SQL_DataSource(getContext());   //check to make sure getContext is correct vs get activity since this is fragment called from main
        dataSource.deleteAllProfiles();
        Toast toast = Toast.makeText(getContext(), "sql cleared", Toast.LENGTH_SHORT);
        toast.show();
    }

    public boolean isSqlEmpty () {
        boolean result = true;
        SQL_DataSource dataSource = new SQL_DataSource(getContext());
        int count = dataSource.getEntryCount();
        if (count > 0)
        {
            result=false;
        }
        return result;

    }

    Object_Profile profile = new Object_Profile();
    ArrayList<Object_Profile> profileList = new ArrayList<>();

    String[] stateList = {"AK", "AL", "AR", "AZ", "CA", "CO", "CT", "DC", "DE", "FL", "GA", "GU", "HI",
            "IA", "ID", "IL", "IN", "KS", "KY", "LA", "MA", "MD", "ME", "MH", "MI", "MN", "MO", "MS", "MT",
            "NC", "ND", "NE", "NH", "NJ", "NM", "NV", "NY", "OH", "OK", "OR", "PA", "PR", "PW", "RI", "SC",
            "SD", "TN", "TX", "UT", "VA", "VI", "VT", "WA", "WI", "WV", "WY"};

    String taxStatus= "SINGLE";
    int familysizeInput = -5;
    float incomeValue = -5;
    float spouseIncomeValue = -5;
    String taxState;
    String name;
//    boolean profileExists = false;



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (check_info_ready()==true)
        {
            Object_Profile profile = new Object_Profile(familysizeInput, incomeValue, spouseIncomeValue, taxStatus, taxState, name);
             saveData(profile);


        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

//        clearData();

        profileList = getData();

        View view = inflater.inflate(R.layout.info, container, false);

        final EditText profileName = (EditText) view.findViewById(R.id.profileAssoc);
        final EditText familysizeField = (EditText) view.findViewById(R.id.familySizeInput);
        final EditText incomeInput = (EditText) view.findViewById(R.id.incomeInput);
        final EditText spouseIncomeInput = (EditText) view.findViewById(R.id.spouseIncomeInput);

        if (isSqlEmpty()== false)
        {
            profile = getLast();
            profileName.setText(profile.getProfileName());
            familysizeField.setText( String.valueOf(profile.getFamilySize()) );
            incomeInput.setText( String.valueOf(profile.getGrossIncome()) );
            spouseIncomeInput.setText( String.valueOf(profile.getSpouseIncome()) );
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
