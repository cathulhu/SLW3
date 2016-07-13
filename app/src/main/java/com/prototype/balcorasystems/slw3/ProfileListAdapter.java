package com.prototype.balcorasystems.slw3;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public class ProfileListAdapter extends BaseAdapter{

    private Context context;
    private List<Object_Profile> profileList;

    public ProfileListAdapter(Context passedContext, List<Object_Profile> profiles)
    {
        this.context = passedContext;
        this.profileList = profiles;
    }

    @Override
    public int getCount() {
        return profileList.size();
    }

    @Override
    public Object getItem(int position) {
        return profileList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(context, R.layout.profile_list, null);

        TextView name = (TextView) v.findViewById(R.id.loan_list_balance);
        TextView income = (TextView) v.findViewById(R.id.loan_list_date);
        TextView spouseIncome = (TextView) v.findViewById(R.id.profile_spouse_income);
        TextView taxFilingStatus = (TextView) v.findViewById(R.id.loan_list_type);
        TextView taxState = (TextView) v.findViewById(R.id.profile_filing_state);
        TextView familySize = (TextView) v.findViewById(R.id.loan_list_apr);

        name.setText(profileList.get(position).getProfileName());
        income.setText("Income: $" +String.valueOf(profileList.get(position).getGrossIncome()));
        spouseIncome.setText("Spouse: $" + profileList.get(position).getSpouseIncome());
        taxFilingStatus.setText(profileList.get(position).getFilingStatus().toLowerCase());
        taxState.setText("State: " + profileList.get(position).getFilingState());
        familySize.setText("Household Size: " + profileList.get(position).getFamilySize());

//        v.setTag(profileList.get(position).get);

        return v;
    }
}
