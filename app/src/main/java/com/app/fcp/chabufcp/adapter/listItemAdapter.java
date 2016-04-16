package com.app.fcp.chabufcp.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.app.fcp.chabufcp.R;
import com.app.fcp.constant.DatabaseConstant;
import com.app.fcp.database.service.Imp.QueryServiceImp;
import com.app.fcp.database.service.QueryService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by arm on 9/4/2559.
 */
public class listItemAdapter extends ArrayAdapter<String> {
    private final String MSG_MainActivity = "listItemAdapter";
    private int layoutResourceId;
    private List<String> list;
    private int[] imageId;

    Activity context;


    public listItemAdapter(Activity context, int[] resource, List<String> list) {
        super(context, R.layout.list_view_item, list);
        this.list = list;
        this.context = context;
        imageId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = context.getLayoutInflater().inflate(R.layout.list_view_item,null,true);
        TextView text = (TextView) convertView.findViewById(R.id.list_row_text);
        text.setText(list.get(position));

//        ImageView img = (ImageView) convertView.findViewById(R.id.image_list_pokemon);
//        img.setImageResource(imageId[position]);

        return convertView;
    }

}
