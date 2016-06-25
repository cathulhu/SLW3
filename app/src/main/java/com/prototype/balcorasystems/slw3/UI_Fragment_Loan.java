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
import android.widget.TextView;


public class UI_Fragment_Loan extends Fragment {

    String[] loantypes =
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
    String loanChoiceCode;
    float loanPrincipal;
    float apr;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.loan, container, false);

        final EditText loanInput = (EditText) view.findViewById(R.id.debtInput);
        final EditText aprInput = (EditText) view.findViewById(R.id.aprInput);
        TextView profileAssociation = (TextView) view.findViewById(R.id.profileAssoc);

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
                stateBuilder.setItems(loantypes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        loanChoiceCategory = loanCategory[which];
                        loanChoiceCode = loanCodes[which];
                    }
                });
                AlertDialog dialog = stateBuilder.create();
                dialog.show();
            }


        });

        return view;
    }
}
