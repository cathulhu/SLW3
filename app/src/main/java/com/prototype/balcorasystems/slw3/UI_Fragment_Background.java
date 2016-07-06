package com.prototype.balcorasystems.slw3;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class UI_Fragment_Background extends Fragment{

    float annualRaise;
    String industry;
    boolean fulltime;
    boolean ssdi;
    boolean totallydisabled;
    boolean lowIncomeTeacher;
    boolean millitarydisability;
    boolean deceasedChildParentLoan;


    @Override
    public void onDestroyView() {
        super.onDestroyView();

        Object_Background newBackground = new Object_Background(annualRaise, industry, fulltime, ssdi, totallydisabled, lowIncomeTeacher, millitarydisability, deceasedChildParentLoan);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.background, container, false);

        final Spinner raiseSpinner = (Spinner) view.findViewById(R.id.annualRaiseSpinner);
        final Spinner jobSpinner = (Spinner) view.findViewById(R.id.industrySpinner);

        final RadioGroup fulltimeGroup = (RadioGroup) view.findViewById(R.id.fulltimeRadioGroup);
        final RadioGroup ssdiGroup = (RadioGroup) view.findViewById(R.id.ssdiRadioGroup);
        final RadioGroup totalDisabilityGroup = (RadioGroup) view.findViewById(R.id.totalDisabilityRadioGroup);
        final RadioGroup lowIncomeTeacherGroup = (RadioGroup) view.findViewById(R.id.lowIncomeTeacherRadioGroup);
        final RadioGroup militaryDisabilityGroup = (RadioGroup) view.findViewById(R.id.millitaryDisabilityRadioGroup);
        final RadioGroup childDeceasedGroup = (RadioGroup) view.findViewById(R.id.parentPlusDeceasedRadioGroup);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getContext(), R.array.annual_raise_array, android.R.layout.simple_spinner_dropdown_item);
        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        raiseSpinner.setAdapter(adapter1);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getContext(), R.array.jobs_array, android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jobSpinner.setAdapter(adapter2);


        raiseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                annualRaise=position/100.0f;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        jobSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                industry = jobSpinner.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        fulltimeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton checkedRadioButton = (RadioButton)fulltimeGroup.findViewById(checkedId);

                int choice = fulltimeGroup.indexOfChild(checkedRadioButton);

                if (choice==0)
                {
                    fulltime=true;
                }
            }
        });

        ssdiGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton checkedRadioButton = (RadioButton)ssdiGroup.findViewById(checkedId);

                int choice = ssdiGroup.indexOfChild(checkedRadioButton);

                if (choice==0)
                {
                    ssdi=true;
                }
            }
        });

        totalDisabilityGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton checkedRadioButton = (RadioButton)totalDisabilityGroup.findViewById(checkedId);

                int choice = totalDisabilityGroup.indexOfChild(checkedRadioButton);

                if (choice==0)
                {
                    totallydisabled=true;
                }
            }
        });

        lowIncomeTeacherGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton checkedRadioButton = (RadioButton)lowIncomeTeacherGroup.findViewById(checkedId);

                int choice = lowIncomeTeacherGroup.indexOfChild(checkedRadioButton);

                if (choice==0)
                {
                    lowIncomeTeacher=true;
                }
            }
        });

        militaryDisabilityGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton checkedRadioButton = (RadioButton)militaryDisabilityGroup.findViewById(checkedId);

                int choice = militaryDisabilityGroup.indexOfChild(checkedRadioButton);

                if (choice==0)
                {
                    millitarydisability=true;
                }
            }
        });

        childDeceasedGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton checkedRadioButton = (RadioButton)childDeceasedGroup.findViewById(checkedId);

                int choice = childDeceasedGroup.indexOfChild(checkedRadioButton);

                if (choice==0)
                {
                    deceasedChildParentLoan=true;
                }
            }
        });

        return view;

    }

}
