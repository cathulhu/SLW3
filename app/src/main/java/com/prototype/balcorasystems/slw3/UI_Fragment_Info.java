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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class UI_Fragment_Info extends Fragment {

    public interface profileActivityLoader {
        public void profileFragToMainActivity(Object_Profile outBoundProfile);
    }

    public static Object_Profile loadProfileFromMainActivity (){

        Object_Profile fetchedProfile = MainActivity.dispatchProfile();
        return fetchedProfile;
    }

    profileActivityLoader mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (profileActivityLoader) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement profileActivityLoader");
        }

    }




    public void deleteWholeDb (Context context)
    {
        SQL_DataSource dataSource = new SQL_DataSource(getContext());
        dataSource.deleteDbFile(context);
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


    public boolean check_info_ready ()
    {
        boolean result = false;

        if (familysizeInput != -5 && incomeValue != -5 && spouseIncomeValue != -5 && taxState != null && name!= null)
        {
            result=true;
        }
        return result;
    }

    String[] stateList = {"AK", "AL", "AR", "AZ", "CA", "CO", "CT", "DC", "DE", "FL", "GA", "GU", "HI",
            "IA", "ID", "IL", "IN", "KS", "KY", "LA", "MA", "MD", "ME", "MH", "MI", "MN", "MO", "MS", "MT",
            "NC", "ND", "NE", "NH", "NJ", "NM", "NV", "NY", "OH", "OK", "OR", "PA", "PR", "PW", "RI", "SC",
            "SD", "TN", "TX", "UT", "VA", "VI", "VT", "WA", "WI", "WV", "WY"};

    String[] taxFilingOptions = {"SINGLE", "MARRIED_FILING_JOINTLY", "MARRIED_FILING_SINGLY", "HEAD_OF_HOUSEHOLD"};

    //could make a special object to hold these values but as far as global variables go these are pretty safe
    static String taxStatus= "SINGLE";
    static int familysizeInput = -5;
    static float incomeValue = -5;
    static float spouseIncomeValue = -5;
    static String taxState;
    static String name;
    static ArrayList<Object_Profile> storedProfiles;




    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (check_info_ready()==true)   //when i put in a list view of different profiles ill need a trigger for updating data on selected profile changed also
        {
            if (taxStatus.equals("SINGLE"))
            {
                spouseIncomeValue=0;
                familysizeInput=1;
            }
            else if (taxStatus.equals("HEAD_OF_HOUSEHOLD"))
            {
                spouseIncomeValue=0;
            }

            Object_Profile updatedProfile = new Object_Profile(familysizeInput, incomeValue, spouseIncomeValue, taxStatus, taxState, name);

            if (isUniqueName(updatedProfile)==false)
            {
                updateData(updatedProfile);
            }
            else
            {
                saveData(updatedProfile);
            }

            mCallback.profileFragToMainActivity(updatedProfile);

        }
    }




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

//        clearData();
//        deleteWholeDb(getContext());

        storedProfiles = getData();

        View view = inflater.inflate(R.layout.info, container, false);

        final EditText profileName = (EditText) view.findViewById(R.id.profileAssoc);
        final EditText familysizeField = (EditText) view.findViewById(R.id.familySizeInput);
        final EditText incomeInput = (EditText) view.findViewById(R.id.incomeInput);
        final EditText spouseIncomeInput = (EditText) view.findViewById(R.id.spouseIncomeInput);

        final Button stateButton = (Button) view.findViewById(R.id.stateButton);
        stateButton.setText("Selected Filing State");

        final TextView houseSizeLabel = (TextView) view.findViewById(R.id.householdSizeLabel);
        final TextView spouseIncomeLabel = (TextView) view.findViewById(R.id.spouseIncomeLabel);
        final TextView primaryIncomeLabel = (TextView) view.findViewById(R.id.primaryIncomeLabel);

        houseSizeLabel.setVisibility(View.INVISIBLE);
        spouseIncomeLabel.setVisibility(View.INVISIBLE);
        spouseIncomeInput.setVisibility(View.INVISIBLE);
        familysizeField.setVisibility(View.INVISIBLE);

        final Spinner taxSpinner = (Spinner) view.findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.tax_status_array, android.R.layout.simple_spinner_dropdown_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        taxSpinner.setAdapter(adapter);


        Adapter_ProfileList profileAdapter;
        ListView profilesList = (ListView) view.findViewById(R.id.profileList);
        profileAdapter = new Adapter_ProfileList(getContext(), storedProfiles);
        profilesList.setAdapter(profileAdapter);
        profilesList.setItemsCanFocus(true);

        profilesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                profileName.setText(storedProfiles.get(position).getProfileName());
                familysizeField.setText(String.valueOf(storedProfiles.get(position).getFamilySize()));
                incomeInput.setText(String.valueOf(storedProfiles.get(position).getGrossIncome()));
                spouseIncomeInput.setText(String.valueOf(storedProfiles.get(position).getSpouseIncome()));
                stateButton.setText("Selected Filing State: " + storedProfiles.get(position).getFilingState());
                taxState=storedProfiles.get(position).getFilingState();

                if (storedProfiles.get(position).getFilingStatus().equals("SINGLE") )
                {
                    taxSpinner.setSelection(0);
                }
                else if (storedProfiles.get(position).getFilingStatus().equals("MARRIED_FILING_SINGLY"))
                {
                    taxSpinner.setSelection(2);
                }
                else if (storedProfiles.get(position).getFilingStatus().equals("MARRIED_FILING_JOINTLY"))
                {
                    taxSpinner.setSelection(1);
                }
                else
                {
                    taxSpinner.setSelection(3);
                }

                mCallback.profileFragToMainActivity(storedProfiles.get(position));

//                view.invalidate();
//                Toast toast = Toast.makeText(getContext(), " on click triggered ", Toast.LENGTH_SHORT);
//                toast.show();

            }
        });



        if (isSqlEmpty()== false)
        {
            Object_Profile freshProfile = new Object_Profile();

            if (loadProfileFromMainActivity()!=null)
            {
                freshProfile = loadProfileFromMainActivity();
            }
            else
            {
                freshProfile = getLast();

            }

            mCallback.profileFragToMainActivity(freshProfile);

//            profilesList.setSelection();  //highlight the current working profile (uncomment when current profile stays selected on view refresh instead of last item)
            profileName.setText(freshProfile.getProfileName());
            name = freshProfile.getProfileName();

            familysizeField.setText( String.valueOf(freshProfile.getFamilySize()) );
            familysizeInput = freshProfile.getFamilySize();
            familysizeField.setText(String.valueOf(familysizeInput));

            incomeInput.setText( String.valueOf(freshProfile.getGrossIncome()) );
            incomeValue = freshProfile.getGrossIncome();
            spouseIncomeInput.setText( String.valueOf(freshProfile.getSpouseIncome()) );
            spouseIncomeValue = freshProfile.getSpouseIncome();
            spouseIncomeInput.setText(String.valueOf(spouseIncomeValue));
            stateButton.setText("Selected Filing State: " + freshProfile.getFilingState());
            taxState=freshProfile.getFilingState();

            if (freshProfile.getFilingStatus().equals("SINGLE") )
            {
                taxSpinner.setSelection(0);
            }
            else if (freshProfile.getFilingStatus().equals("MARRIED_FILING_SINGLY"))
            {
                taxSpinner.setSelection(2);
            }
            else if (freshProfile.getFilingStatus().equals("MARRIED_FILING_JOINTLY"))
            {
                taxSpinner.setSelection(1);
            }
            else if (freshProfile.getFilingStatus().equals("HEAD_OF_HOUSEHOLD"))
            {
                taxSpinner.setSelection(3);
            }

            taxStatus=freshProfile.getFilingStatus();

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

                if (taxStatus.equals("MARRIED_FILING_JOINTLY"))
                {
                    spouseIncomeValue= 0;
                }


            }

        };

        familysizeField.addTextChangedListener(watcher);
        incomeInput.addTextChangedListener(watcher);
        spouseIncomeInput.addTextChangedListener(watcher);
        profileName.addTextChangedListener(watcher);


        taxSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                taxStatus= taxFilingOptions[position];

                if (position != 0)
                {
                    houseSizeLabel.setVisibility(View.VISIBLE);
                    familysizeField.setVisibility(View.VISIBLE);
//                    familysizeField.setText("");
                    spouseIncomeLabel.setVisibility(View.VISIBLE);
                    spouseIncomeInput.setVisibility(View.VISIBLE);

                    if (spouseIncomeValue==0.0)
                    {
                                            spouseIncomeInput.setText("");
                    }

                    if (familysizeInput==0)
                    {
                        familysizeField.setText("");
                    }
                }

                if (position==0)
                {
                    houseSizeLabel.setVisibility(View.INVISIBLE);
                    familysizeField.setVisibility(View.INVISIBLE);
                    familysizeInput=1;
                    spouseIncomeLabel.setVisibility(View.INVISIBLE);
                    spouseIncomeInput.setVisibility(View.INVISIBLE);
                    spouseIncomeValue=0;
                }

                if (position==1)
                {
                    primaryIncomeLabel.setText("Joint Filing Income");
                    spouseIncomeLabel.setVisibility(View.INVISIBLE);
                    spouseIncomeInput.setVisibility(View.INVISIBLE);
                    spouseIncomeValue=0;

                }

                if (position==3)
                {
                    spouseIncomeInput.setVisibility(View.INVISIBLE);
                    spouseIncomeLabel.setVisibility(View.INVISIBLE);
                    spouseIncomeValue=0;
                }

                if (position!=1)
                {
                    primaryIncomeLabel.setText("Filing Income");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        stateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                AlertDialog.Builder stateBuilder = new AlertDialog.Builder(getContext());
                stateBuilder.setTitle("Select Tax Filing State");
                stateBuilder.setItems(stateList, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        taxState = stateList[which];
                        stateButton.setText("Tax Filing State: " + taxState);

                    }
                });
                AlertDialog dialog = stateBuilder.create();
                dialog.show();
            }

        });




        return view;
    }
}
