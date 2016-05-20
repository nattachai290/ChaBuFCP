package com.app.fcp.chabufcp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.List;

/**
 * Created by arm on 6/4/2559.
 */
public class fragmentPageAdapter extends FragmentPagerAdapter {
    private final String MSG = "fragmentPageAdapter";
    List<Fragment> list;

    public fragmentPageAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        Log.i(MSG, "fragmentPageAdapter");
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        Log.i(MSG, "getItem");
        return list.get(position);
    }

    @Override
    public int getCount() {
        Log.i(MSG, "getCount");
        return list.size();
    }
}
