package com.app.fcp.chabufcp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.HorizontalScrollView;
import android.widget.TabHost;
import android.widget.TextView;

import com.app.fcp.chabufcp.adapter.fragmentPageAdapter;
import com.app.fcp.chabufcp.fragment.AddCustomer;
import com.app.fcp.chabufcp.fragment.tab.fragment_alcohol;
import com.app.fcp.chabufcp.fragment.tab.fragment_aquatic_animal;
import com.app.fcp.chabufcp.fragment.tab.fragment_beef;
import com.app.fcp.chabufcp.fragment.tab.fragment_chicken;
import com.app.fcp.chabufcp.fragment.tab.fragment_dessert;
import com.app.fcp.chabufcp.fragment.tab.fragment_egg;
import com.app.fcp.chabufcp.fragment.tab.fragment_fruit;
import com.app.fcp.chabufcp.fragment.tab.fragment_noodles;
import com.app.fcp.chabufcp.fragment.tab.fragment_pig;
import com.app.fcp.chabufcp.fragment.tab.fragment_shushi;
import com.app.fcp.chabufcp.fragment.tab.fragment_snack;
import com.app.fcp.chabufcp.fragment.tab.fragment_vegetable;

import java.util.ArrayList;
import java.util.List;

public class MainOverView extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private final String MSG_NavigateUser = "MainOverView";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String mActivityTitle = getTitle().toString();
        Log.i(MSG_NavigateUser, "Start onCreate NavigateUser");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.over_view_activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.nav_toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                order();

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                Bundle data = getIntent().getExtras();
                if(data==null){
                    return;
                }

//                String msgId = data.getString("userId");
                String msgUser = data.getString("userName");
//                String msgPwd = data.getString("password");
//                String msgFname = data.getString("fname");
//                String msgLname = data.getString("lname");
                String msgPos = data.getString("position");

//                Log.i(MSG_NavigateUser, "msgUser: "+msgUser);
//                Log.i(MSG_NavigateUser, "msgPos: "+msgPos);

                TextView textUser = (TextView) findViewById(R.id.nav_head_name);
                TextView textPosition = (TextView) findViewById(R.id.nav_head_pos);

//                Log.i(MSG_NavigateUser, "TextView User: "+textUser);
//                Log.i(MSG_NavigateUser, "TextView Pos: " + textPosition);

                textUser.setText(msgUser);
                textPosition.setText(msgPos);

                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




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
        getMenuInflater().inflate(R.menu.navigation_user, menu);
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

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return OverViewOnNavigationItemSelected(item);
    }

    public boolean OverViewOnNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        Class fragmentClass = null;
        if (id == R.id.nav_profile) {
            // Handle the camera action
//            fragmentClass = AddCustomer.class;
//
//            try {
//                fragment = (Fragment) fragmentClass.newInstance();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            fragmentManager.beginTransaction().replace(R.id.main_nav_content, fragment).commit();
//            item.setChecked(true);

        }
        else if(id==R.id.nav_checkBill){
            Intent i = new Intent(this,CheckBill.class);
            startActivity(i);
        }
        else if (id == R.id.nav_home) {
            Intent i = new Intent(this,MainOverView.class);
            startActivity(i);
        }else if (id == R.id.nav_logout) {
            Intent i = new Intent(this,MainLoginActivity.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void chooseTable(View view){

    }

    public void order(){
            Intent main = new Intent(this,AddTable.class);
            startActivity(main);

    }


}
