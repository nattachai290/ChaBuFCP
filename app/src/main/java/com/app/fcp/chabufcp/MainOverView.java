package com.app.fcp.chabufcp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.fcp.chabufcp.adapter.OverViewAdapter;
import com.app.fcp.chabufcp.entity.HisTrnHdr;
import com.app.fcp.chabufcp.entity.User;
import com.app.fcp.chabufcp.entity.itemgnl;
import com.app.fcp.constant.Constant;

import java.util.List;
import java.util.Map;

public class MainOverView extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private final String MSG = "MainOverView";
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final String mActivityTitle = getTitle().toString();
        Log.i(MSG, "Start onCreate MainOverView");


        setContentView(R.layout.over_view_activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.nav_toolbar);
        setSupportActionBar(toolbar);
        initTable();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        Button button_confirm = (Button) findViewById(R.id.add_table_button);
        fab.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(Color.parseColor("#FF37F60C"), PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;
                    }
                }
                return false;
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                order();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                TextView textUser = (TextView) findViewById(R.id.nav_head_name);
                TextView textPosition = (TextView) findViewById(R.id.nav_head_pos);
//                Typeface f = Typeface.createFromAsset(getAssets(),"fonts/THSaraban.ttf");
                Log.i(MSG, User.getUSERNAME());
                Log.i(MSG, User.getPOSITION());

                try{
//                    textUser.setTypeface(f);
                    textUser.setText(User.getUSERNAME());
                    textPosition.setText(User.getPOSITION());
//                    textPosition.setTypeface(f);
                }catch (Exception e){
                    Intent loginScreen = new Intent(getApplicationContext(),MainLoginActivity.class);
                    startActivity(loginScreen);
                }


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


    private void initTable() {
        HisTrnHdr hdr = new HisTrnHdr();
        Map<String,List> map = hdr.initData();
        if(map.get("ListHdrTableNo")!=null){
            ListView list = (ListView) findViewById(R.id.listView_overview);
            OverViewAdapter myAdap = new OverViewAdapter(this,R.layout.over_view_content_navigation, map);
            list.setAdapter(myAdap);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.i(MSG, "Start setOnItemClickListener");
                    TextView TextViewtableNo = (TextView) view.findViewById(R.id.list_view_over_view_table_no);
                    String numTable = TextViewtableNo.getText().toString();

                    TextView TextViewTableId = (TextView) view.findViewById(R.id.list_view_over_view_table_id);
                    String tableId = TextViewTableId.getText().toString();
                    Intent i = new Intent(getApplicationContext(), OrderMenu.class);
                    i.putExtra("numTable", numTable);
                    i.putExtra("tableId", tableId);
                    startActivity(i);
                }
            });
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
    public boolean onNavigationItemSelected(MenuItem item) {
        return OverViewOnNavigationItemSelected(item);
    }

    public boolean OverViewOnNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_profile) {
            Intent i = new Intent(this,Profile.class);
            startActivity(i);

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


        }else if(id==R.id.nav_history){
            Intent i = new Intent(this,History.class);
            startActivity(i);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void order(){
            Intent main = new Intent(this,AddTable.class);
            startActivity(main);
    }


}
