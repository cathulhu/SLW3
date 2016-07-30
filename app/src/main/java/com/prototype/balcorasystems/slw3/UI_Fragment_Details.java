package com.prototype.balcorasystems.slw3;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class UI_Fragment_Details extends Fragment{

    //need to add SQL saving and loading of background object stuff to keep persistence between sessions.
    //need to add second question spawner for the spinner questions.

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


        //will put code in here to save to SQL (need to modify DB to contain proper tables
    }

    static int index = 0;
    private Handler mHandler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.background_questionnaire, container, false);

        savedBackground = loadBackgroundFromMainActivity();

        LinearLayout questionSpace = (LinearLayout) view.findViewById(R.id.dynamic_questions);
        final int N = savedBackground.getCustomQuestion().size(); // total number of textviews to add
        final TextView[] myTextViews = new TextView[N]; // create an empty array;
        final List<String> questions = savedBackground.getCustomQuestion();
        final List<Boolean> answers = savedBackground.getCustomAnswers();

        final TextView rowTextView = new TextView(getContext());
        final TextView emptySpace = new TextView(getContext());
        final RadioGroup answer = new RadioGroup(getContext());
        final RadioButton yes = new RadioButton(getContext());
        final RadioButton no = new RadioButton(getContext());
        rowTextView.setText(questions.get(index));
        yes.setText("Yes");
        no.setText("No");

        questionSpace.addView(rowTextView);
        answer.addView(yes);
        answer.addView(no);
        questionSpace.addView(answer);
        questionSpace.addView(emptySpace);
        answer.setOrientation(LinearLayout.HORIZONTAL);

        answer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton checkedRadioButton = (RadioButton) group.findViewById(checkedId);
                int choice = group.indexOfChild(checkedRadioButton);
                index++;

                if (index <= questions.size()-1)
                {
                    if (choice == 0) {
                        answers.set(index, true);

                    } else {
                        answers.set(index, false);
                    }


                    rowTextView.setText(questions.get(index));
                    //eventually would like to add animations in here
                    savedBackground.setCustomAnswers(answers);
                    mCallback.detailFragToMainActivity(savedBackground);
                }

                yes.setChecked(false);
                no.setChecked(false);

            }

        });

        return view;
    }
}

