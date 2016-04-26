package com.app.fcp.chabufcp.fragment.tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.fcp.chabufcp.R;
import com.app.fcp.chabufcp.adapter.listItemAdapter;
import com.app.fcp.chabufcp.entity.itemgnl;
import com.app.fcp.constant.Constant;
import com.app.fcp.constant.DatabaseConstant;
import com.app.fcp.database.service.Imp.QueryServiceImp;
import com.app.fcp.database.service.QueryService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by arm on 6/4/2559.
 */
public class fragment_pig extends Fragment{
    private final String MSG = "fragment_pig";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.tab_fragment_pig,container,false);

        String post = "type";
        String type = "2";
        itemgnl itm = new itemgnl(type,post);
        Map<String,List> map = itm.initData();

        int[] imgAddMinus = {R.drawable.btn_plus,R.drawable.btn_minus};
        listItemAdapter myAdap = new listItemAdapter(getActivity(),imgAddMinus, map.get("ListItmName"));

        ListView list = (ListView) view.findViewById(R.id.listView_pig);
        list.setAdapter(myAdap);
//        list.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i(MSG, "View = "+v);
//            }
//        });
//        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                Log.i(MSG, "Start setOnItemClickListener");
//                Log.i(MSG, "position = "+position);
////                TextView numItem = (TextView) view.findViewById(R.id.number_order);
////                String order = numItem.getText().toString();
////
////                int number = 1;
////                Log.i(MSG, order);
////                int value = Integer.valueOf(order);
////                if (order.isEmpty()) {
////                    numItem.setText(String.valueOf(number));
////                } else if (value == 99) {
////                Toast.makeText(getActivity(), Constant.MAX_ORDER, Toast.LENGTH_LONG).show();
////                } else {
////                    int newValue = value + 1;
////                    numItem.setText(String.valueOf(newValue));
////                }
//
//            }
//        });

        return view;
    }

}
