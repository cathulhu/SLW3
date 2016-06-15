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
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

//may want to add arguments to class so fragment knows whether its been spawned to edit or create new object
//for now store results of input in gloabl variables, later hookup dynamic object creation.
public class UI_Fragment_Info extends Fragment {

    String[] stateList = {"AK", "AL", "AR", "AZ", "CA", "CO", "CT", "DC", "DE", "FL", "GA", "GU", "HI",
            "IA", "ID", "IL", "IN", "KS", "KY", "LA", "MA", "MD", "ME", "MH", "MI", "MN", "MO", "MS", "MT",
            "NC", "ND", "NE", "NH", "NJ", "NM", "NV", "NY", "OH", "OK", "OR", "PA", "PR", "PW", "RI", "SC",
            "SD", "TN", "TX", "UT", "VA", "VI", "VT", "WA", "WI", "WV", "WY"};

    String taxStatus;
    int familysizeInput;
    int incomeValue;
    int spouseIncomeValue;
    String taxState;
    String name;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.info, container, false);

        final EditText profileName = (EditText) view.findViewById(R.id.profileAssoc);
        final EditText familysizeField = (EditText) view.findViewById(R.id.familySizeInput);
        final EditText incomeInput = (EditText) view.findViewById(R.id.incomeInput);
        final EditText spouseIncomeInput = (EditText) view.findViewById(R.id.spouseIncomeInput);

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
                else
                {
                    familysizeInput = 0;
                }

                if (input1.equals("") == false)
                {
                    familysizeInput = Integer.parseInt(familysizeField.getText().toString());
                }
                else
                {
                    familysizeInput = 0;
                }

                if (input2.equals("") == false)
                {
                    incomeValue = Integer.parseInt(incomeInput.getText().toString());
                }
                else
                {
                    incomeValue=0;
                }

                if (input3.equals("") == false)
                {
                    spouseIncomeValue = Integer.parseInt(spouseIncomeInput.getText().toString());
                }
                else
                {
                    spouseIncomeValue=0;
                }

            }

        };

        familysizeField.addTextChangedListener(watcher);
        incomeInput.addTextChangedListener(watcher);
        spouseIncomeInput.addTextChangedListener(watcher);

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
                Log.d("Checked Button ID is: ", taxStatus);
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
