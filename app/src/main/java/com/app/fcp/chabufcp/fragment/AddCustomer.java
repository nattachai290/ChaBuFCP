package com.app.fcp.chabufcp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.fcp.chabufcp.R;

/**
 * Created by arm on 6/4/2559.
 */
public class AddCustomer extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.add_table_fragment,container,false);
        return view;
    }
}