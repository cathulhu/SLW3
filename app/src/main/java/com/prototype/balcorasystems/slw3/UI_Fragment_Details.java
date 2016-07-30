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
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class UI_Fragment_Details extends Fragment{

    public static Object_Profile loadProfileFromMainActivity (){

        Object_Profile fetchedProfile = MainActivity.dispatchProfile();
        return fetchedProfile;
    }

    public static Object_Background loadBackgroundFromMainActivity (){

        Object_Background fetchedBackground = MainActivity.dispatchBackground();
        return fetchedBackground;
    }


    static Object_Profile fetchedProfile = loadProfileFromMainActivity();
    static Object_Background savedBackground = loadBackgroundFromMainActivity(); //will = retrieve background from SQl
    //should have a list containing all owner loans here so background object can scan for important details or get a copy of the loans


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();



        //will put code in here to save to SQL (need to modify DB to contain proper tables
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.background_questionnaire, container, false);

        savedBackground = loadBackgroundFromMainActivity();

        LinearLayout questionSpace = (LinearLayout) view.findViewById(R.id.dynamic_questions);
        final int N = savedBackground.getCustomQuestion().size(); // total number of textviews to add
        final TextView[] myTextViews = new TextView[N]; // create an empty array;

        for (int i = 0; i < N; i++) {
            // create a new textview
            final TextView rowTextView = new TextView(getContext());
            final TextView emptySpace = new TextView(getContext());

            final List<String> questions = savedBackground.getCustomQuestion();

            // set some properties of rowTextView or something
            rowTextView.setText(questions.get(i));

            // add the textview to the linearlayout
            questionSpace.addView(rowTextView);
            questionSpace.addView(emptySpace);

            // save a reference to the textview for later
            myTextViews[i] = rowTextView;
        }

        return view;

    }

}
