package com.prototype.balcorasystems.slw3;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class UI_Fragment_Details extends Fragment{

    //need to add SQL saving and loading of background object stuff to keep persistence between sessions.
    //need to modify database file with appropriate fields to store things in SQL
    //need to add second question spawner for the spinner questions.
    //need to analyze answer to questions and set background flags and attributes appropriately
    //

    public interface detailActivityLoader {
        public void detailFragToMainActivity(Object_Background outBoundBackground);
    }

    detailActivityLoader mCallback;

//    public static Object_Profile loadProfileFromMainActivity() {
//
//        Object_Profile fetchedProfile = MainActivity.dispatchProfile();
//        return fetchedProfile;
//    }

    public static Object_Background loadBackgroundFromMainActivity() {

        Object_Background fetchedBackground = MainActivity.dispatchBackground();
        return fetchedBackground;
    }


//    static Object_Profile fetchedProfile = loadProfileFromMainActivity();
    static Object_Background savedBackground = loadBackgroundFromMainActivity(); //will = retrieve background from SQl
    //should have a list containing all owner loans here so background object can scan for important details or get a copy of the loans


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (detailActivityLoader) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement detailActivityLoader");
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        index=0;

        //will put code in here to save to SQL (need to modify DB to contain proper tables
    }

    static int index = 0;
    private Handler mHandler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.background_questionnaire, container, false);

        savedBackground = loadBackgroundFromMainActivity();

        final LinearLayout questionSpace = (LinearLayout) view.findViewById(R.id.dynamic_questions);
        final int numQuestions = savedBackground.getCustomQuestionYN().size(); // total number of textviews to add
//        final TextView[] myTextViews = new TextView[numQuestions]; // create an empty array;
        final List<String> questionsYn = savedBackground.getCustomQuestionYN();
        final List<Boolean> answersYn = savedBackground.getCustomAnswersYN();

        final TextView rowTextView = new TextView(getContext());
        final TextView emptySpace = new TextView(getContext());
        final RadioGroup answer = new RadioGroup(getContext());
        final RadioButton yes = new RadioButton(getContext());
        final RadioButton no = new RadioButton(getContext());

        rowTextView.setText(questionsYn.get(index));

        yes.setText("Yes");
        no.setText("No");
//        no.setSelected(true);

        questionSpace.addView(rowTextView);
        answer.addView(yes);
        answer.addView(no);
        questionSpace.addView(answer);
//        questionSpace.addView(emptySpace);
        answer.setOrientation(LinearLayout.HORIZONTAL);

        answer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton checkedRadioButton = (RadioButton) group.findViewById(checkedId);
                int choice = group.indexOfChild(checkedRadioButton);


                if (index < questionsYn.size()-1)
                {
                    if (choice == 0)
                    {
                        answersYn.set(index, true);
                    }
                    else
                    {
                        answersYn.set(index, false);
                    }
                }

                    //eventually would like to add animations in here
            }
        });


        final LinearLayout spinnerSpace = (LinearLayout) view.findViewById(R.id.dynamic_spinner);

        final TextView MultiQuestionTextView = new TextView(getContext());
        final TextView disclaimerTextView = new TextView(getContext());
        final TextView emptyMultiSpace2 = new TextView(getContext());
        final TextView emptyMultiSpace1 = new TextView(getContext());

        final List<String> questionsMulti = savedBackground.getCustomQuestionMulti();
        final List<Long> answersMulti = savedBackground.getCustomAnswersMulti();
        final EditText numericalMultiChoice = new EditText(getContext());
        final Button nextQuestion = (Button) view.findViewById(R.id.nextQuestionButton);

        disclaimerTextView.setText("Enter 0 if question is not applicable or answer is less than 1");
        numericalMultiChoice.setInputType(InputType.TYPE_CLASS_NUMBER);
        no.setChecked(true);

        MultiQuestionTextView.setText(questionsMulti.get(0));
        spinnerSpace.addView(MultiQuestionTextView);
        spinnerSpace.addView(emptyMultiSpace1);
        spinnerSpace.addView(disclaimerTextView);
        spinnerSpace.addView(emptyMultiSpace2);
        spinnerSpace.addView(numericalMultiChoice);

        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //&& ((index <= questionsYn.size()-1) || (index < questionsMulti.size()-1))
                if (!numericalMultiChoice.getText().toString().equals("")  )
                {
                    if (((index <= questionsYn.size()-1) || (index < questionsMulti.size()-1)))
                    {
                        answersMulti.set(index, Long.parseLong (numericalMultiChoice.getText().toString()) );

                    }

                    index++;

                    if (index <= questionsYn.size()-1)
                    {
                        rowTextView.setText(questionsYn.get(index));
                        yes.setChecked(false);
                        no.setChecked(true);
                    }
                    else
                    {
                        questionSpace.removeAllViews();
                    }

                    if (index < questionsMulti.size()-1)
                    {
                        MultiQuestionTextView.setText(questionsMulti.get(index));
                        questionsMulti.clear();
                    }
                    else
                    {
                        spinnerSpace.removeAllViews();
                    }

                    if (spinnerSpace.getChildCount()==0 && questionSpace.getChildCount()==0)
                    {
                        final TextView finishedText = (TextView) view.findViewById(R.id.finishText);
                        finishedText.setText("All Questions Finished, ready to move on to next stage.");
                        nextQuestion.setText("Continue");
                    }

                    if (nextQuestion.getText().equals("Continue"))
                    {
                        Toast toast = Toast.makeText(getContext(),"Not hooked up yet, but should interface to main activity and load next stage", Toast.LENGTH_SHORT);
                        toast.show();
                    }

                    savedBackground.setCustomAnswersYN(answersYn);
                    savedBackground.setCustomAnswersMulti(answersMulti);
                    mCallback.detailFragToMainActivity(savedBackground);

                }
            }
        });


//        ArrayList<String> spinnerArray = new ArrayList<>();
//        spinnerArray.add(questionsMulti.get(0));
//        Spinner dynamicSpinner = new Spinner(getContext());
//        spinnerSpace.addView(dynamicSpinner);
//        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, spinnerArray);
//        dynamicSpinner.setAdapter(spinnerArrayAdapter);

        int numMultiChoiceQuestions = savedBackground.getCustomQuestionMulti().size();






        return view;
    }
}

