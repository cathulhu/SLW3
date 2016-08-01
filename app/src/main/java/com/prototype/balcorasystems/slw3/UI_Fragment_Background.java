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

import java.util.ArrayList;
import java.util.List;

public class UI_Fragment_Background extends Fragment{

    public interface backgroundActivityLoader {
        public void backgroundFragToMainActivity(Object_Background outBoundBackground);
    }

    backgroundActivityLoader mCallback;

    public static Object_Profile loadProfileFromMainActivity (){

        Object_Profile fetchedProfile = MainActivity.dispatchProfile();
        return fetchedProfile;
    }

    public Object_Background extraQuestions (Object_Background background)
    {
        if (background.getCustomQuestionYN().isEmpty()==false)
        {
            background.setCustomQuestion("clear");
        }


        String[] vaDisabilityQuestionsYN = {"Do you have 1 or more service connected disability rated at least 60% disabling?",
                                      "Do you have 2+ service connected disabilities totaling 70%+ (with at least one at 40%+)?",
                                      "Regardless of rating, do any service connected disabilities prevent you from maintaining gainful employment?"};

        String[] militaryQuestionsMulti= {
                                    "Between 10/1998 and 8/2008, How many full years (12 consecutive month periods) of your military service were in a hostile fire or imminent danger area?",
                                    "Starting August 2008, How many years of your military service has been in a hostile fire or imminent danger area?\""};

                                    //national direct student loan or perkins loan    //for the National Defense Student Loan Discharge Program
                                    //HAZARDOUS DUTY PAY (is this the same is immenet danger?   //military.com has cool table of qualifying hot zones.
                                    //very hard to find info, cancelation is 12.5% per year up to 50% until 8/14/2008, after 8/14/2008 it follows scheme below @ (15/15/20/20/30%)
                                    //source says 15%+interest acrued during year for first and second years, 20% + interest for third and fourth year, 30% + interest for the 5th year
                                    //asking about branch because each branch has there own extra special programs in addition to public service.

        //should put something into the program to check for interest rates >6% while serving, so they can invoke SCRA to lower those for military

        String[] disabilityQuestionsYN = {"Is the permanent and total disability expected to last at least 60 months (5 years) or end in death?"};

        String[] parentPlusloanYN = {"Is the child for which this loan was intended deceased?",
                                    "Is the child for which this loan was intended permanently and totally disabled?"};

        String[] publicHealthQuestionsYN = {"Are you a certified/licensed nurse or medical technician that provides services directly to patients (LPN/RN, hygienist, PT, etc...)?"};
        //nurse, or medical technician are valid for this
        //check against state residency because alaska, arizona, california, colorado, florida, illinois, iowa, kansas, kentucky, louisiana, maryland, massachusetts, michigan, minnesota,
        // montana, nebraska, new jersey, new mexico, new york, ohio, oregon, pennsylvania, rhode island, tennessee, texas, vermont, virginia, washington, west virginia, wisconsin and wyoming
        // all have their own additional state programs.
        // perkins loan 5 year graduated forgiveness (15/15/20/20/30%)

        String[] generalQuestionsMulti = {"Since [perkins date 10-1998], how many years have you worked fulltime, in this industry?",
                                    "Since [pub service date 10-2007], how many full and on time loan payments have you made WHILE working fulltime in this industry?",
                                    };

//        "Which repayment method are you currently repaying your loans under? (Select Standard if repayment has not begun, you are in deferment, or you don't know.)"

        String[] publicSafetyPoliceQuestionsYN = {
                                            "Are you a sworn officer? (Sheriff, Officer, Probation/Parole, Special Agent, DA, Marshal, Fire Marshal, Detective, Coast Guard Officer, etc...)?",  //not sure what im gonna do about coast guard
                                            "Are you a prosecuting attorney, public defender, or community defender working on behalf of public law agency?",
                                            "Are your duties essential to the execution of your agencies mandate to prevent/control/reduce/enforce criminal law?"};

        String[] publicSafetyPoliceQuestionsMulti = {"Since August 2008, How many years have you been working full time in a capacity of criminal prevention, control, reduction, enforcement, or defense/prosecution?"};
                                        //law enforcement, corrections, public defender/community attorney, firefighter, courts, probation/parole

        String[] teacherQuestionsYN= {
                                    "Are you a teacher who provides direct classroom teaching (including Special Education and speech pathology)? at a public primary or secondary school?",
                                    "Are you a teacher employed at an educational service agency?",
                                    "Are you a full time teacher of mathematics, science or another state declared shortage area, at a public secondary school?",
                                    "Are you a teacher who has obtained full state teaching certification/and or passed the state teacher licensing examination and current license holder?",
                                    "Are you a teacher who has attained a bachelors degree or higher in the area of their instruction?",
                                    "Do you teach/serve at a federally designated low income school? (Title/Chapter 1 Status)",
                                    "Do you teach/serve at a 501c3 tax exempt not for profit private school?",
                                    "Do you teach/serve at a tribal or BIA administered university or school?"};    //is that the same as title/chapter 1?

//        "Are you employed fulltime in any of the following public education positions?",
//        "How many years have you been employed fulltime in one of these positions?"

        // All bureau of indian affairs schools qualify as low income schools.
        // FT in head start program,
        // FT teaching pre school or kindergarten (if part of state elementary program)
        // FT teacher in low income school (title 1),
        // FT special education,
        // FT Teacher of recreational, occupational, physical or therapy
        // FT psychological and counseling services
        // FT Speech pathologist/audiology w masters at low income school,
        // FT early intervention in public or non profit program,
        // FT early child hood education
        // FT teacher of handicapped students in primary or seconday school,
        // full time teacher in math,science, foreign language, bilingual edu OR any state cited shortage category
        // faculty at a tribal college or university
        // FT Librarian in (or serving a low income school)
        // FT Teacher employed by an educational service agency (After august 2008)
        // FT Employee who provides or supervises services directly and elusively to high risk children in designated low income communities.

        String[] peaceCorpQuestions = {"Did you receive a national service education award for your volunteer service?",
                                        "How many full years (12 month periods) of service did you perform?"};

        //up to 70% cancelation @ (15/15/20/20%)


        //Perkins cancellation: up 100% (50 or 70% in some cases) cancellation based on years of service. Usually 15/15/20/20/30%, for 70% IE Peacecorp (15/15/20/20%),
        //Public service cancellation: up to 100%, 120 on time full payments made after 2007, under standard or IBR results in forgiveness of remaining balance at end of 10 year period.



        List<String> detailedYesNoQuestions = new ArrayList<String>();
        List<String> detailedMultiChoiceQuestions = new ArrayList<>();


        if (background.getEmploymentSector().equals("Gov. Agency (Fed, State, Tribal, Local)"))
        {
            //gov service qualifies for pub service payoff (direct loans)
            background.setPublicServiceForgivenessEligible(true);   //as far as I can tell any federal, state, local, or tribal government employee qualifies.
            background.setPublicServiceForgivenessEligibleReason("Government Employee");

        }
        else if (background.getEmploymentSector().equals("Public Interest/Community Law"))
        {
            for (String item: publicSafetyPoliceQuestionsYN)
            {
                detailedYesNoQuestions.add(item);
            }

            for (String item: publicSafetyPoliceQuestionsMulti)
            {
                detailedMultiChoiceQuestions.add(item);
            }
        }
        else if (background.getEmploymentSector().equals("Public or Tribal Education/ Library/ Univ."))
        {
            for (String item: teacherQuestionsYN)
            {
                detailedYesNoQuestions.add(item);
            }
        }
        else if (background.getEmploymentSector().equals("Peace Corps or AmeriCorps-VISTA"))
        {
            for (String item: peaceCorpQuestions)
            {
                detailedYesNoQuestions.add(item);
            }
            //Peace corp/Americorp also qualifies for both perkins and PLSF cancellation
        }
        else if (background.getEmploymentSector().equals("Public Safety/ L.E.O/ Corrections"))
        {
            for (String item: publicSafetyPoliceQuestionsYN)
            {
                detailedYesNoQuestions.add(item);
            }

            for (String item: publicSafetyPoliceQuestionsMulti)
            {
                detailedMultiChoiceQuestions.add(item);
            }
            //public safety and law enforcement eligible for both PLSF and Perkins cancellation
        }
        else if (background.getEmploymentSector().equals("Armed Forces (incl. Coastguard)"))
        {

            for (String item: militaryQuestionsMulti)
            {
                detailedMultiChoiceQuestions.add(item);
            }

            for (String item: vaDisabilityQuestionsYN)
            {
                detailedYesNoQuestions.add(item);
            }
            //armed forces fall under the government service so quallify for direct loan PLSF forgiveness
            //armed forces also quallify for up to 100% perkins forgiveness for number of years served in area of hostility
        }
        else if (background.getEmploymentSector().equals("Public Healthcare (Nursing/Med Tech)"))
        {
            for (String item: publicHealthQuestionsYN)
            {
                detailedYesNoQuestions.add(item);
            }
            //(nurses, nurse practioner, and med tech people) qualifies for public service direct loan forgiveness
        }
        else if (background.getEmploymentSector().equals("501c3 Tax Exempt Organization"))
        {
            //501c3 qualifies for public service direct loan forgiveness
            background.setPublicServiceForgivenessEligible(true);
            background.setPublicServiceForgivenessEligibleReason("501c3");
        }

        for (String item: generalQuestionsMulti)
        {
            detailedMultiChoiceQuestions.add(item);
        }

        ArrayList<Boolean> defaultAnswers = new ArrayList<>();
        ArrayList<Long> defaultMultiAnswers = new ArrayList<>();

        for (String item: detailedYesNoQuestions)
        {
            defaultAnswers.add(Boolean.FALSE);
        }

        for (String item: detailedMultiChoiceQuestions)
        {
            defaultMultiAnswers.add(Long.MIN_VALUE);
        }

        background.setCustomAnswersYN(defaultAnswers);
        background.setCustomAnswersMulti(defaultMultiAnswers);

        background.setCustomQuestionYN(detailedYesNoQuestions);
        background.setCustomQuestionMulti(detailedMultiChoiceQuestions);

        return background;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (backgroundActivityLoader) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement profileActivityLoader");
        }

    }

    String[] extraQuestions;
    String industry="Private Sector - For Profit";
    boolean fulltime=false;
    boolean ssdi=false;
    boolean totallydisabled=false;
    boolean lowIncomeTeacher=false;
    boolean millitarydisability=false;
    boolean deceasedChildParentLoan=false;
    Object_Profile fetchedProfile = loadProfileFromMainActivity();
    Object_Background savedBackground;  //will = retrieve background from SQl
    //should have a list containing all owner loans here so background object can scan for important details or get a copy of the loans



    @Override
    public void onDestroyView() {
        super.onDestroyView();


        //will put code in here to save to SQL (need to modify DB to contain proper tables
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.background, container, false);

        savedBackground = new Object_Background(industry, fulltime, ssdi, totallydisabled, lowIncomeTeacher, millitarydisability, deceasedChildParentLoan);
        savedBackground.setOwner(fetchedProfile.getProfileName());

        final Spinner jobSpinner = (Spinner) view.findViewById(R.id.industrySpinner);
        final RadioGroup fulltimeGroup = (RadioGroup) view.findViewById(R.id.fulltimeRadioGroup);
        final RadioGroup ssdiGroup = (RadioGroup) view.findViewById(R.id.ssdiRadioGroup);
        final RadioGroup totalDisabilityGroup = (RadioGroup) view.findViewById(R.id.totalDisabilityRadioGroup);
        final RadioGroup militaryDisabilityGroup = (RadioGroup) view.findViewById(R.id.millitaryDisabilityRadioGroup);

        //will put code in here that populates the fields with whatever it finds in SQL

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getContext(), R.array.jobs_array, android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jobSpinner.setAdapter(adapter2);


        jobSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                industry = jobSpinner.getSelectedItem().toString();
                savedBackground.setEmploymentSector(industry);
                savedBackground=extraQuestions(savedBackground);
                mCallback.backgroundFragToMainActivity(savedBackground);

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

                if(choice == 0)
                {
                    fulltime=true;
                }

//                if (choice==0)
//                {
//                    savedBackground.setEmployedFulltime(true);
//                }
//                else
//                {
//                    savedBackground.setEmployedFulltime(false);
//                }
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
