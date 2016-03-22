package com.app.fcp.chabufcp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.TextView;

public class NavigationUser extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private final String MSG_NavigateUser = "NavigateUser";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String mActivityTitle = getTitle().toString();
        Log.i(MSG_NavigateUser, "Start onCreate NavigateUser");


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.nav_toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

//                getSupportActionBar().setTitle("Navigation!");
                Bundle data = getIntent().getExtras();
                if(data==null){
                    return;
                }

                String msgId = data.getString("userId");
                String msgUser = data.getString("userName");
                String msgPwd = data.getString("password");
                String msgFname = data.getString("fname");
                String msgLname = data.getString("lname");
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            // Handle the camera action
        } else if (id == R.id.nav_logout) {
            Intent main = new Intent(this,MainActivity.class);
            startActivity(main);


        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
