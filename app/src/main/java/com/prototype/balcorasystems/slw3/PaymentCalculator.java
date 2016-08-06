package com.prototype.balcorasystems.slw3;


import android.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class PaymentCalculator extends Fragment {

    private static List<Object_Loan> fetchedLoans;
    ArrayList<ArrayList> paymentsUberList = new ArrayList<>();
    int std10yearNumMonths = 120;

    public ArrayList<Double> standardCalc() {
        fetchedLoans = MainActivity.dispatchLoans();
        ArrayList<Double> payments = new ArrayList<>();

        for (Object_Loan loan : fetchedLoans) {

            double monthlyInterestRate = (loan.getLoanAPR() / 100) / 12;
            float loanBalance = loan.getLoanBalance();
            float runningTotal = loanBalance;

            int month = 0;
            double repaymentTermMonhts = 120;

            double paymentCalcNumerator = monthlyInterestRate * runningTotal;
            double paymentCalcDenominator = 1 - Math.pow(1 + monthlyInterestRate, -repaymentTermMonhts);
            double fixedPayment = paymentCalcNumerator / paymentCalcDenominator;


            payments.add(fixedPayment);
        }

        return payments;
    }


}
