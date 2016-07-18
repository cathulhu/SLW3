package com.prototype.balcorasystems.slw3;


import android.content.Context;
import android.text.format.DateFormat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public class Adapter_LoanList extends BaseAdapter{

    private Context context;
    private List<Object_Loan> loanList;

    public Adapter_LoanList(Context passedContext, List<Object_Loan> loans)
    {
        this.context = passedContext;
        this.loanList = loans;
    }

    public void notifyDataSetChanged(List<Object_Loan> newLoanList) {
        super.notifyDataSetChanged();

        this.loanList = newLoanList;
    }

    @Override
    public int getCount() {
        return loanList.size();
    }

    @Override
    public Object getItem(int position) {
        return loanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(context, R.layout.loan_list, null);

        TextView balance = (TextView) v.findViewById(R.id.loan_list_balance);
        TextView type = (TextView) v.findViewById(R.id.loan_list_type);
        TextView apr = (TextView) v.findViewById(R.id.loan_list_apr);
        TextView date = (TextView) v.findViewById(R.id.loan_list_date);
        TextView status = (TextView) v.findViewById(R.id.loan_list_status);

        balance.setText("Balance: $"+ String.valueOf(loanList.get(position).getLoanBalance()));
        type.setText("Type: " + loanList.get(position).getPrettyName());
        apr.setText("APR: "+ String.valueOf(loanList.get(position).getLoanAPR()) + "%");

        date.setText("Date: " + DateFormat.format("M-d-yyyy", loanList.get(position).getInceptionDate()*1000));

        status.setText("Status: " + String.valueOf(loanList.get(position).getLoanStatus()));

//        v.setTag(profileList.get(position).get);

        return v;
    }
}
