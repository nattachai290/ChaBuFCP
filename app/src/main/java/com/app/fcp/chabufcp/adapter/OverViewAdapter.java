package com.app.fcp.chabufcp.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.fcp.chabufcp.R;

import java.util.List;
import java.util.Map;

/**
 * Created by arm on 25/4/2559.
 */
public class OverViewAdapter  extends ArrayAdapter<String> {

    private final String MSG = "OverViewAdapter";
    private int layoutResourceId;
    private Map<String,List> list;
    Activity context;


    public OverViewAdapter(Activity context, int resource, Map<String,List> list) {
        super(context, R.layout.list_view_over_view, list.get("ListHdrTableNo"));
        this.list = list;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = context.getLayoutInflater().inflate(R.layout.list_view_over_view,null,true);
        Log.i(MSG, "list: " + list);
        try {
            TextView tableId = (TextView) convertView.findViewById(R.id.table_no);
            tableId.setText(list.get("ListHdrId").get(position).toString());

            TextView tableNo = (TextView) convertView.findViewById(R.id.table_no);
            tableNo.setText(list.get("ListHdrTableNo").get(position).toString());

            TextView time = (TextView) convertView.findViewById(R.id.table_time);
            time.setText(list.get("ListHdrTime").get(position).toString());


        }catch (NullPointerException e){
            Log.i(MSG, "NullPointerException: " + e.toString());
        }


        return convertView;
    }
}
