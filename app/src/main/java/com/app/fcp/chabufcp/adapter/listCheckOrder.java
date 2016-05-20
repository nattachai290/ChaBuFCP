package com.app.fcp.chabufcp.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.fcp.chabufcp.CheckOrder;
import com.app.fcp.chabufcp.R;
import com.app.fcp.constant.Constant;

import java.util.ArrayList;
import java.util.zip.Checksum;

/**
 * Created by user on 5/19/2016.
 */
public class listCheckOrder extends ArrayAdapter {
    private final String MSG = "listCheckOrder";
    private Activity context;
    private ArrayList<String> order;
    private ArrayList<Integer> numOrder;
    public listCheckOrder(Activity context, ArrayList<String> order,ArrayList<Integer> numOrder) {
        super(context, R.layout.list_chck_order, order);
        this.context = context;
        this.order = new ArrayList<String>(order);
        this.numOrder = new ArrayList<Integer>(numOrder);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = context.getLayoutInflater().inflate(R.layout.list_chck_order, null, true);
        final TextView tvOrder = (TextView) convertView.findViewById(R.id.list_check_order_check_order);
        final TextView tvNum = (TextView) convertView.findViewById(R.id.list_check_order_check_num);
        tvOrder.setText(order.get(position));
        tvNum.setText(String.valueOf(numOrder.get(position)));
        return convertView;
    }

    public ArrayList<Integer> getNumOrder() {
        return numOrder;
    }




}
