package com.app.fcp.chabufcp.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.fcp.chabufcp.R;
import com.app.fcp.constant.Constant;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by arm on 9/4/2559.
 */


public class HistoryAdapter extends ArrayAdapter<String> {
    private final String MSG = "HistoryAdapter";
    private List<String> tableNoList,nameList,qtyList,timeList,peopleList;
    Activity context;

    public HistoryAdapter(Activity context,List<String> tableNoList, List<String> nameList,List<String> qtyList,List<String> timeList,List<String> peopleList) {
        super(context, R.layout.list_view_his, tableNoList);
        this.context = context;
        this.tableNoList = new ArrayList<String>(tableNoList);
        this.nameList = new ArrayList<String>(nameList);
        this.qtyList = new ArrayList<String>(qtyList);
        this.timeList = new ArrayList<String>(timeList);
        this.peopleList = new ArrayList<String>(peopleList);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = context.getLayoutInflater().inflate(R.layout.list_view_his,null,true);
        TextView tableNo = (TextView) convertView.findViewById(R.id.list_view_his_table_no);
        TextView name = (TextView) convertView.findViewById(R.id.list_view_his_order_name);
        TextView qty = (TextView) convertView.findViewById(R.id.list_view_his_num);
        TextView time = (TextView) convertView.findViewById(R.id.list_view_his_time);
        TextView people = (TextView) convertView.findViewById(R.id.list_view_his_officer);

        tableNo.setText(tableNoList.get(position));
        name.setText(nameList.get(position));
        qty.setText(qtyList.get(position));
        time.setText(timeList.get(position));
        people.setText(peopleList.get(position));


        return convertView;
    }

}
