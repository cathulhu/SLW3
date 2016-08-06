package com.prototype.balcorasystems.slw3;


import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        UI_Fragment_Info.profileActivityLoader,
        UI_Fragment_Loan.loanActivityLoader,
        UI_Fragment_Background.backgroundActivityLoader,
        UI_Fragment_Details.detailActivityLoader
{


    public static Object_Profile storedProfile;
    public static List<Object_Loan> storedLoanList;
    public static Object_Background storedBackground;

    public void detailFragToMainActivity(Object_Background inBoundBackground) {
        storedBackground = inBoundBackground;
    }

    public void backgroundFragToMainActivity(Object_Background inBoundBackground) {
        storedBackground = inBoundBackground;
    }

    public void profileFragToMainActivity(Object_Profile inBoundProfile) {
        storedProfile = inBoundProfile;

    }

    public void loanFragToMainActivity (List<Object_Loan> inBoundLoanList)////
    {
        storedLoanList = inBoundLoanList;
    }

    public static Object_Profile dispatchProfile () {
        return storedProfile;
    }

    public static Object_Background dispatchBackground () {
        return storedBackground;
    }

    public static List<Object_Loan> dispatchLoans() ////
    {
        return storedLoanList;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        UI_Fragment_Guide topFragmentGuide = (UI_Fragment_Guide) getSupportFragmentManager().findFragmentById(R.id.top_fragment_box);
        if (topFragmentGuide == null) {
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            UI_Fragment_Guide fragmentGuide = new UI_Fragment_Guide();
            fragmentTransaction.add(R.id.top_fragment_box, fragmentGuide);
            fragmentTransaction.commit();
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();





        if (id == R.id.navDrawer_info)
        {

            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            UI_Fragment_Info fragmentInfo = new UI_Fragment_Info();
            fragmentTransaction.replace(R.id.main_fragment_box, fragmentInfo);
            fragmentTransaction.commit();

        }
        else if (id == R.id.navDrawer_loan)
        {

            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            UI_Fragment_Loan UIFragmentLoan = new UI_Fragment_Loan();
            fragmentTransaction.replace(R.id.main_fragment_box, UIFragmentLoan);
//            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();


        }
        else if (id == R.id.navDrawer_background)
        {

            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            UI_Fragment_Background UIFragmentRepayment = new UI_Fragment_Background();
            fragmentTransaction.replace(R.id.main_fragment_box, UIFragmentRepayment);
//            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        }
        else if (id == R.id.navDrawer_analysis)
        {

            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            UI_Fragment_Analysis UIFragmentAnalysis = new UI_Fragment_Analysis();
            fragmentTransaction.replace(R.id.main_fragment_box, UIFragmentAnalysis);
            fragmentTransaction.commit();

        }
        else if (id == R.id.navDrawer_Details)
        {
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            UI_Fragment_Details UIFragment_Details = new UI_Fragment_Details();
            fragmentTransaction.replace(R.id.main_fragment_box, UIFragment_Details);
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
