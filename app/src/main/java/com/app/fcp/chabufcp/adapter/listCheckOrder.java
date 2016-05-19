package com.app.fcp.chabufcp.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.app.fcp.chabufcp.R;

import java.util.ArrayList;

/**
 * Created by user on 5/19/2016.
 */
public class listCheckOrder extends ArrayAdapter {
    private final Activity context;
    private final ArrayList<String> order;
    private final ArrayList<Integer> numOrder;

    public listCheckOrder(Activity context, ArrayList<String> order,
                                            ArrayList<Integer> numOrder) {
        super(context, R.layout.list_chck_order, order);
        this.context = context;
        this.order = order;
        this.numOrder = numOrder;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = context.getLayoutInflater().inflate(R.layout.list_chck_order, null, true);
        TextView tvOrder = (TextView) convertView.findViewById(R.id.textView_check_order);
        TextView tvNum = (TextView) convertView.findViewById(R.id.textView_check_num);
        tvOrder.setText(order.get(position));
        tvNum.setText(numOrder.get(position));
        return super.getView(position, convertView, parent);
    }
}
