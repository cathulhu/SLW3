//package com.prototype.balcorasystems.slw3;
//
//import android.support.v4.app.Fragment;
//import java.util.ArrayList;
//import java.util.List;
//
//
//import android.support.v4.app.Fragment;
//
//import java.util.ArrayList;
//import java.util.List;
//
////repaye = 10% of discretionary income, IE (0.10*(agi-finhardshipline))/12
//
////paye = 10% of discretionary income OR std monthly payment, whichever is less
//
////IBR = 10% of discretionary income (for new borrowers after july 1 2014) OR  std monthly payments, whichever is less
////      OR 15% of discretionary income (if not a new borrower), OR  std monthly payments, whichever is less
//
////ICR = 20% of discretionary income or 12 year fixed plan * income percentage factor algorithm
////income percentage factor:
////take AGI and compare nearest (below and above) values for IPF, IE for 30k                                                 (36793 - 29337) = $10456 = income interval
////minus the IPF from each of the below and above figures, here its                                                          100%-88% = 12% = income percentage factor interval
////subtract closest chart value from income, here its                                                                        30,000-30,000 (its on chart) = $0
////divide result by income interval (step 1 result),  here:                                                                  $0/$10456 = 0 <--- decimal result
////multiply result by income percentage factor interval,                                                                     0 * 12 = 0% (note ones place 6.12 for 6.12% not 0.612)
////Add the result to the income percentage factor that corresponds to the closest lesser chart value. Here =                 88.77% + 0%
////The result is your income percentage factor.                                                                              88.77% = IPF
////final payment is then                                                                                                     20% of discretionary income * 88.77%
////see example PDF for a non zero example
//
//public class PaymentCalc extends Fragment {
//
//    List<Double> STDpayments;
//    int principal;
//    double monthlyRate;     //=rate/12
//
//    double monthlyInterest;
//    int standardPaymentMonthly; //will get this from other section of program
//
//    public static int dumbTotalLoan;       //just adds up total of all loans with no type checking for now
//    int familysize=0; //take this from loaninfo screen eventually
//    int[] povertylevels = {11880, 16020, 20160, 24300, 28440, 32580, 36730, 40890};
//    double AGI; //geting income from info screen, later should get for object oriented
//    static double interestRateMonthly = (0.06)/12;     //fixed at 6 percent right now
//    int numberMonths= 120; //fixed at 120 months for now
//    static List<Object_Loan> loans = loadProfileFromMainActivity();
//
//    int income;
//    int interestTotal;
//    static double runningTotal;
//    double workingTotal;
//    static double fixedPayment;
//
//    static double finHardshipLine;
//    double IBpaymentAnnual;
//    double ICpaymentAnnual;
//    double IBpaymentMonthly;
//
//    double repayeAnnual;
//    double payeAnnual;
//    double IBRnewAnnual;
//    double IBRoldAnnual;
//    double ICRAnnual;
//
//    public static List<Object_Loan> loadProfileFromMainActivity (){
//
//        List<Object_Loan> fetchedLoans = MainActivity.dispatchLoans();
//        return fetchedLoans;
//    }
//
//    public PaymentCalc()
//    {
//        loans=loadProfileFromMainActivity();
//
//        finHardshipLine=povertylevels[familysize]*1.5;
//
//        for(Loan loan : Loan.getAllLoans())
//        {
//            runningTotal=loan.principal;
//        }
//        //code here will determine which IBR plans actually eligible for
//    }
//
//    public static double stdPayments (int numberMonths)
//    {
//        // (rate*presentTotal) / (1- (1+rate)^-#months )
//
////        runningTotal = dumbTotalLoan;
//
//        if (numberMonths==300 && runningTotal < 30000 )
//        {
//            fixedPayment = 0;
//        }
//        else
//        {
//            fixedPayment = ( (interestRateMonthly)*runningTotal ) / ( 1- Math.pow( (1+interestRateMonthly), -numberMonths ) );
//        }
//
//        return  fixedPayment;
//
//    }

////    public static List<Float> stdPaymentsList (int numberMonths)
////    {
////        List<Float> stdpayments = new ArrayList<>();
////        Float payment = (float) stdPayments(numberMonths);
////
////        for (int i = 0; i <= numberMonths ; i++)
////        {
////            stdpayments.add(payment);
////        }
////
////        return stdpayments;
////    }
//
//
////
//////    public static List<Float> gradPaymentList ()
//////    {
//////
//////    }
////
////
////
////    public List<Float> IBR ()
////    {
////        List<Float> IBRepayeResults = new ArrayList<>();
////        IBpaymentAnnual = (AGI-finHardshipLine);
////
////        repayeAnnual = IBpaymentAnnual*0.10;
////        payeAnnual= IBpaymentAnnual*0.10;
////        IBRnewAnnual= IBpaymentAnnual *0.10;
////        IBRoldAnnual = IBpaymentAnnual *0.15;
////
////        IBRepayeResults.add((float)repayeAnnual/12);
////        IBRepayeResults.add((float)payeAnnual/12);
////        IBRepayeResults.add((float)IBRnewAnnual/12);
////        IBRepayeResults.add((float)IBRoldAnnual/12);
////
////        return IBRepayeResults;
////    }
////
////
////
////    public List<Float> ICR ()
////    {
////        List<Float> ICRepayeResults = new ArrayList<>();
////        ICpaymentAnnual = (AGI-povertylevels[0]);   //note that for ICR discretionary income is just AGI-poverty level, no %150 here.
////        ICRAnnual = ICpaymentAnnual * 0.2;
////
////        double ICRfixed12 = this.stdPayments(144)*0.7273 ;   //ICR adjustment of 12 year fixed payment times 20000 ICR table adjustment
////        float ICRfixed12float = (float) ICRfixed12;
////
////        ICRepayeResults.add( (float)ICRAnnual/12 );
////        ICRepayeResults.add( (float)ICRfixed12float );
////
////        return ICRepayeResults;
////    }
//
//
//
//}
