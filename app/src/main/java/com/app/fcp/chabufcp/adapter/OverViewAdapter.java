package com.app.fcp.chabufcp.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.fcp.chabufcp.R;

import java.util.List;

/**
 * Created by arm on 25/4/2559.
 */
public class OverViewAdapter  extends ArrayAdapter<String> {

    private final String MSG = "OverViewAdapter";
    private int layoutResourceId;
    private List<String> list;
    Activity context;


    public OverViewAdapter(Activity context, int resource, List<String> list) {
        super(context, R.layout.list_view_item, list);
        this.list = list;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = context.getLayoutInflater().inflate(R.layout.list_view_item,null,true);
        TextView text = (TextView) convertView.findViewById(R.id.list_row_text);
        text.setText(list.get(position));
        return convertView;
    }
}
