package com.prototype.balcorasystems.slw3;


import android.content.Context;
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

    public interface backgroundActivityLoader {
        public void loanFragToMainActivity(Object_Background outBoundBackground);
    }

    public static Object_Profile loadProfileFromMainActivity (){

        Object_Profile fetchedProfile = MainActivity.dispatchProfile();
        return fetchedProfile;
    }

    backgroundActivityLoader mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (backgroundActivityLoader) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement profileActivityLoader");
        }

    }

    float annualRaise;
    String industry;
    boolean fulltime;
    boolean ssdi;
    boolean totallydisabled;
    boolean lowIncomeTeacher;
    boolean millitarydisability;
    boolean deceasedChildParentLoan;
    Object_Profile fetchedProfile = loadProfileFromMainActivity();
    Object_Background savedBackground;  //will = retrieve background from SQl


    @Override
    public void onDestroyView() {
        super.onDestroyView();

        Object_Background newBackground = new Object_Background(industry, fulltime, ssdi, totallydisabled, lowIncomeTeacher, millitarydisability, deceasedChildParentLoan);

        //will put code in here to save to SQL (need to modify DB to contain proper tables
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.background, container, false);


        final Spinner jobSpinner = (Spinner) view.findViewById(R.id.industrySpinner);

        final RadioGroup fulltimeGroup = (RadioGroup) view.findViewById(R.id.fulltimeRadioGroup);
        final RadioGroup ssdiGroup = (RadioGroup) view.findViewById(R.id.ssdiRadioGroup);
        final RadioGroup totalDisabilityGroup = (RadioGroup) view.findViewById(R.id.totalDisabilityRadioGroup);
        final RadioGroup lowIncomeTeacherGroup = (RadioGroup) view.findViewById(R.id.lowIncomeTeacherRadioGroup);
        final RadioGroup militaryDisabilityGroup = (RadioGroup) view.findViewById(R.id.millitaryDisabilityRadioGroup);

        //will put code in here that populates the fields with whatever it finds in SQL



        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getContext(), R.array.jobs_array, android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jobSpinner.setAdapter(adapter2);

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

        return view;

    }

}
