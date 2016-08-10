package com.prototype.balcorasystems.slw3;


import android.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class PaymentCalculator extends Fragment {

    int[] povertylevels = {11880, 16020, 20160, 24300, 28440, 32580, 36730, 40890};    //2016 level for 48 states later put in hawaii and alaska.

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

    public ArrayList<Double> IBRpayments (Object_Profile profile)
    {
        fetchedLoans = MainActivity.dispatchLoans();
        ArrayList<Double> payments = new ArrayList<>();

        int familySize = profile.getFamilySize();
        double income = profile.getGrossIncome();
        double annualRaise = 1.05; //will later grab this from the form
        double financialHardshipLine = povertylevels[familySize]*1.5;   //later put in check for family size greater than 8 and add $4160 for each person
        //consider that this calculation assumes that poverty lines stay the same each year, could later add interpolation
        boolean ibrNewBorrower = false;
        int ibrExpiration=25;
        int repaymentYear;
        double grandTotal = 0;

        for (Object_Loan loan: fetchedLoans)
        {
            if (loan.getInceptionDate() >= 1404172800)  // 7-1-14 0:00:00
            {
                ibrNewBorrower=true;
                ibrExpiration=20;
            }
        }

        ArrayList<Double> compositePayments = new ArrayList<>();

        for (Object_Loan loan: fetchedLoans)
        {
            double runningIncome = income;
            double monthlyInterestRate = (loan.getLoanAPR() / 100) / 12;
            float loanBalance = loan.getLoanBalance();
            float runningTotal = loanBalance;

            repaymentYear=0;

            ArrayList<Double> subPayments = new ArrayList<>();

            if (!loan.getLoanType().equals("Direct PLUS Loan for Parents") || !loan.getLoanType().equals("FFEL PLUS Loan for Parents") || !loan.getLoanType().equals("FFEL PLUS Loan for Parents") || !loan.getLoanType().equals("Federal Perkins Loan") || !loan.getLoanType().equals("Private Loan") )
            {

                while (runningTotal > 0 && repaymentYear < ibrExpiration)
                {
                    double discretionaryIncome = runningIncome - financialHardshipLine;
                    double ibrPaymentOld = (discretionaryIncome * 0.15) / 12;
                    double ibrPaymentNew = (discretionaryIncome * 0.10) / 12;
                    double ibrPayment;

                    if (ibrNewBorrower == true)
                    {
                        ibrPayment = ibrPaymentNew;
                    }
                    else
                    {
                        ibrPayment = ibrPaymentOld;
                    }


                    for (int i = 0; i < 12; i++)
                    {
                        //runningTotal - payment + interest;

                        if (runningTotal <= 0 || ibrPayment < 0)
                        {
                            ibrPayment=0;
                        }

                        if (runningTotal-ibrPayment < 0)
                        {
                            ibrPayment = runningTotal;
                        }

                        runningTotal -= ibrPayment;
                        double interestThisMonth = runningTotal * monthlyInterestRate;
                        runningTotal += interestThisMonth;
                        subPayments.add(ibrPayment);
                        grandTotal += ibrPayment;

                    }

                    repaymentYear++;
                    runningIncome = runningIncome * annualRaise;
                }

                double subtotal;

                if (compositePayments.size()==0)
                {
                    for (int i = 0; i < subPayments.size() ; i++)
                    {
                        subtotal = subPayments.get(i);
                        compositePayments.add(subtotal);
                    }
                }
                else
                {
                    for (int i = 0; i < subPayments.size() ; i++)
                    {
                        subtotal = compositePayments.get(i) + subPayments.get(i);
                        compositePayments.set(i, subtotal);
                    }
                }

            }
        }

        return compositePayments;

    }


}
