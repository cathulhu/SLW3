package com.prototype.balcorasystems.slw3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class UI_Fragment_Analysis extends Fragment {

    public static Object_Profile loadProfileFromMainActivity (){

        Object_Profile fetchedProfile = MainActivity.dispatchProfile();
        return fetchedProfile;
    }

    Object_Profile fetchedProfile;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.analysis, container, false);

        fetchedProfile = loadProfileFromMainActivity();

        PaymentCalculator calculator = new PaymentCalculator();
        ArrayList<Double> PaymentsList = calculator.standardCalc();
        ArrayList<Double> ibrPaymentsList = calculator.IBRpayments(fetchedProfile);

        float totalStandardPayment = 0;

        for (Double payment: PaymentsList)
        {
            totalStandardPayment+=payment;
        }

        float totalSumPayed = totalStandardPayment*120;

        final Spinner raiseSpinner = (Spinner) view.findViewById(R.id.annualRaiseSpinner);
        final Spinner paymentTypeSpinner = (Spinner) view.findViewById(R.id.repaymentTypeSpinner);
        TextView stdPaymentField = (TextView) view.findViewById(R.id.standardPayment);
        TextView stdTotalPaymentField = (TextView) view.findViewById(R.id.standardtotalpaid);

        stdPaymentField.setText( String.format( "$ %.2f", totalStandardPayment ) );
        stdTotalPaymentField.setText( String.format( "$ %.2f", totalSumPayed ) );


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.annual_raise_array, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        raiseSpinner.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getContext(), R.array.optimization_array, android.R.layout.simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paymentTypeSpinner.setAdapter(adapter1);

        return view;
    }
}

/*

graph code - currently unused

        List<Float> standard10results = new ArrayList<>();
        List<Float> IBRresults = new ArrayList<>();
        List<Float> ICresults = new ArrayList<>();
        List<Float> PayeResults = new ArrayList<>();

        List<String> xvals1 = new ArrayList<String>();
        LineChart chart1 = (LineChart) view.findViewById(R.id.repayChart1); //start by using the line charts

        List<Entry> yvals1 = new ArrayList<>();   //make y values list for graph
        List<Entry> yvals2 = new ArrayList<>();
        List<Entry> yvals3 = new ArrayList<>();
        List<Entry> yvals4 = new ArrayList<>();

//        List<String> xvals1 = new ArrayList<String>();   //make x value labels, think I can leave this blank


        // LOOP THAT ADDS VALUES TO standard10results
//        int i = 0;
//        for (float monthlyPay : PaymentCalc.stdPaymentsList(120)) {
//            yvals1.add(new Entry(monthlyPay, i));
//            //           xvals1.add("");
//            i++;
//        }

        int index = 0;

        for (int i = 0; i < 121; i++)
        {
            yvals1.add(new Entry(i, i));
            yvals2.add(new Entry(i, i*2+4));
            yvals3.add(new Entry(i, i*3+8));
            yvals4.add(new Entry(i, i*4+12));
        }

        chart1.setDrawGridBackground(false);

        XAxis xAxis = chart1.getXAxis();
        //     xAxis.setTextColor(Color.WHITE);

        YAxis yAxis = chart1.getAxisLeft();
        //     yAxis.setTextColor(Color.WHITE);
        yAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);  //not sure if I want to keep this.


        LineDataSet ydata1 = new LineDataSet(yvals1, "Std 10 year");  //4. New BarDataSet to contain yvalues and descriptions
        LineDataSet ydata2 = new LineDataSet(yvals2, "I.B.R");
        LineDataSet ydata3 = new LineDataSet(yvals3, "I.C.R");
        LineDataSet ydata4 = new LineDataSet(yvals4, "PAYE");

        ydata1.setColor(-16777216);
        ydata2.setColor(-16777216);
        ydata3.setColor(-16777216);
        ydata4.setColor(-16777216);

        ydata1.setFillColor(-65536);
        ydata2.setFillColor(-78536);
        ydata3.setFillColor(-16776961);
        ydata4.setFillColor(-16711681);

        ydata1.setDrawCircles(false);
        ydata2.setDrawCircles(false);
        ydata3.setDrawCircles(false);
        ydata4.setDrawCircles(false);

        ydata1.setDrawFilled(true);
        ydata2.setDrawFilled(true);
        ydata3.setDrawFilled(true);
        ydata4.setDrawFilled(true);


        List<ILineDataSet> dataSets = new ArrayList<>();     // Add the BarDataSet (ydata) to the set of all datasets available to graph within a List containing IBarDataSet objects
        dataSets.add(ydata1);
        dataSets.add(ydata2);
        dataSets.add(ydata3);
        dataSets.add(ydata4);

//       YAxis yRaXxis = chart2.getAxisRight();
//       yRaXxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);


        LineData data = new LineData(dataSets);        //6. Add the set of all data to the master data container and optional x labels;
        chart1.setData(data);

        // data.setValueTextSize(10f);

        //       Legend l = chart2.getLegend();
        //       l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART_INSIDE);
        //       l.setCustom(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, new String[]{"Standard 10 Year", "Graduated 10 Year", "Extended 20 Year", "Extended Graduated 20 Year", "Revised P.A.Y.E 25 Year", "P.A.Y.E 20 Year", "Income Based 25 Year", "IBR New Borrowers", "Income Continent Repayment"});
        //       l.setTextColor(Color.WHITE);
        //       l.setTextSize(15f);

        chart1.setDescription("");
        chart1.animateXY(1000, 1000);
        chart1.invalidate();

        return view;
    }

    private int[] getColors() {

        int stacksize = 3;

        // have as many colors as stack-values per entry
        int[] colors = new int[stacksize];

        for (int i = 0; i < stacksize; i++) {
            colors[i] = ColorTemplate.PASTEL_COLORS[i];
        }

        return colors;
    }

 */