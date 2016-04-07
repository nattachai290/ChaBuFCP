package com.app.fcp.chabufcp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.fcp.chabufcp.R;

public class OrderFragment extends Fragment /*implements  ViewPager.OnPageChangeListener,TabHost.OnTabChangeListener*/{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.order_fragment,container,false);
        return view;
    }

//    private void calcTabHost(){
//        HorizontalScrollView hScrollView = (HorizontalScrollView) findViewById(R.id.scrollViewTab);
//        View tabView = tabhost.getCurrentTabView();
//        int scrollPos = tabView.getLeft()-(hScrollView.getWidth()-tabView.getWidth())/2;
//        hScrollView.smoothScrollTo(scrollPos, 0);
//    }
//
//    private void initViewPager() {
////       {"หมู","ไก่","ทะเล","เนื้อวัว","ผัก","ซูชิ","อาหารเส้น","อาหารสำเร็จรูป","ไข่","ของหวาน","ผลไม้","แอลกอฮอล์"};
//        viewPager = (ViewPager) findViewById(R.id.view_page);
//        List<Fragment> listFragment = new ArrayList<Fragment>();

//
//        fragmentPageAdapter myfragmentPagerAdapter = new fragmentPageAdapter(getSupportFragmentManager(),listFragment);
//        viewPager.setAdapter(myfragmentPagerAdapter);
//        viewPager.setOnPageChangeListener(this);
//    }
//
//    private void initTabHost() {
//        tabhost = (TabHost) findViewById(R.id.tabHost);
//        tabhost.setup();
//        String[] tabName = {"หมู","ไก่","ทะเล","เนื้อวัว","ผัก","ซูชิ","อาหารเส้น","อาหารสำเร็จรูป","ไข่","ของหวาน","ผลไม้","แอลกอฮอล์"};
//        for (String i : tabName){
//            TabHost.TabSpec tabSpec;
//            tabSpec = tabhost.newTabSpec(i);
//            tabSpec.setIndicator(i);
//            tabSpec.setContent(new FakeConten(getApplicationContext()));
//            tabhost.addTab(tabSpec);
//        }
//        tabhost.setOnTabChangedListener(this);
//    }
//    public class FakeConten implements TabHost.TabContentFactory {
//        Context context;
//        public FakeConten(Context context) {
//            this.context = context;
//        }
//
//        @Override
//        public View createTabContent(String tag) {
//            View fakeView = new View(context);
//            fakeView.setMinimumHeight(0);
//            fakeView.setMinimumWidth(0);
//            return fakeView;
//        }
//    }
//
//    //pageListening
//    @Override
//    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//    }
//
//    @Override
//    public void onPageSelected(int position) {
//        tabhost.setCurrentTab(position);
//
//        calcTabHost();
//    }
//
//    @Override
//    public void onPageScrollStateChanged(int state) {
//
//    }
//
//    //tabListening
//    @Override
//    public void onTabChanged(String tabId) {
//        int selectTab = tabhost.getCurrentTab();
//        viewPager.setCurrentItem(selectTab);
//
//        calcTabHost();
//
//    }


}
