package com.app.fcp.chabufcp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import android.widget.Toast;

import com.app.fcp.chabufcp.adapter.fragmentPageAdapter;
import com.app.fcp.chabufcp.entity.User;
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
import com.app.fcp.constant.Constant;

import java.util.ArrayList;
import java.util.List;

public class OrderMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,ViewPager.OnPageChangeListener,TabHost.OnTabChangeListener {
    private final String MSG = "OrderMenu";
    ViewPager viewPager;
    TabHost tabhost;
    String numTable ;
    String tableId ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final String mActivityTitle = getTitle().toString();

        setContentView(R.layout.order_menu_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle data = getIntent().getExtras();
        if(data!=null){
            numTable = data.getString("numTable");
            tableId = data.getString("tableId");
        }

        getSupportActionBar().setTitle(Constant.TABLE + numTable);
        initViewPager();
        initTabHost();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){


            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);


                TextView textUser = (TextView) findViewById(R.id.nav_head_name);
                TextView textPosition = (TextView) findViewById(R.id.nav_head_pos);
                Log.i(MSG, User.getUSERNAME());
                Log.i(MSG, User.getPOSITION());
                textUser.setText(User.getUSERNAME());
                textPosition.setText(User.getPOSITION());

                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(Constant.TABLE+numTable);
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
        getMenuInflater().inflate(R.menu.order_menu, menu);
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
        Fragment fragment = null;
        Class fragmentClass = null;
        if (id == R.id.nav_profile) {
            // Handle the camera action
//            fragmentClass = AddCus7tomer.class;
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
            Intent main = new Intent(this,MainOverView.class);
            startActivity(main);
        }else if (id == R.id.nav_logout) {
            Intent main = new Intent(this,MainLoginActivity.class);
            startActivity(main);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void calcTabHost(){
        HorizontalScrollView hScrollView = (HorizontalScrollView) findViewById(R.id.scrollViewTab);
        View tabView = tabhost.getCurrentTabView();
        int scrollPos = tabView.getLeft()-(hScrollView.getWidth()-tabView.getWidth())/2;
        hScrollView.smoothScrollTo(scrollPos, 0);
    }

    private void initViewPager() {
//       {"หมู","ไก่","ทะเล","เนื้อวัว","ผัก","ซูชิ","อาหารเส้น","อาหารสำเร็จรูป","ไข่","ของหวาน","ผลไม้","แอลกอฮอล์"};
        viewPager = (ViewPager) findViewById(R.id.view_page);
        List<Fragment> listFragment = new ArrayList<Fragment>();
        listFragment.add(new fragment_pig());
        listFragment.add(new fragment_chicken());
        listFragment.add(new fragment_aquatic_animal());
        listFragment.add(new fragment_beef());
        listFragment.add(new fragment_vegetable());
        listFragment.add(new fragment_shushi());
        listFragment.add(new fragment_noodles());
        listFragment.add(new fragment_snack());
        listFragment.add(new fragment_egg());
        listFragment.add(new fragment_dessert());
        listFragment.add(new fragment_fruit());
        listFragment.add(new fragment_alcohol());

        fragmentPageAdapter myfragmentPagerAdapter = new fragmentPageAdapter(getSupportFragmentManager(),listFragment);
        viewPager.setAdapter(myfragmentPagerAdapter);
        viewPager.setOnPageChangeListener(this);
    }

    private void initTabHost() {
        tabhost = (TabHost) findViewById(R.id.tabHost);
        tabhost.setup();
        String[] tabName = {Constant.TAB_PIG,Constant.TAB_CHICKEN,Constant.TAB_AQUATIC_ANIMAL,Constant.TAB_BEEF,Constant.TAB_VEGETABLE,Constant.TAB_SHUSHI,Constant.TAB_NOODLES,Constant.TAB_SNACK,Constant.TAB_EGG,Constant.TAB_DESSERT,Constant.TAB_FRUIT,Constant.TAB_ALCOHOL};
        for (String i : tabName){
            TabHost.TabSpec tabSpec;
            tabSpec = tabhost.newTabSpec(i);
            tabSpec.setIndicator(i);
            tabSpec.setContent(new FakeConten(getApplicationContext()));
            tabhost.addTab(tabSpec);
        }
        tabhost.setOnTabChangedListener(this);
    }
    public class FakeConten implements TabHost.TabContentFactory {
        Context context;
        public FakeConten(Context context) {
            this.context = context;
        }

        @Override
        public View createTabContent(String tag) {
            View fakeView = new View(context);
            fakeView.setMinimumHeight(0);
            fakeView.setMinimumWidth(0);
            return fakeView;
        }
    }

    //pageListening
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        tabhost.setCurrentTab(position);

        calcTabHost();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //tabListening
    @Override
    public void onTabChanged(String tabId) {
        int selectTab = tabhost.getCurrentTab();
        viewPager.setCurrentItem(selectTab);

        calcTabHost();

    }

    public void removeItem(View view){

    }

    public void addItem(View view){


    }


}
