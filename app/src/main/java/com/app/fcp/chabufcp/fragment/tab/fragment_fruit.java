package com.app.fcp.chabufcp.fragment.tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.app.fcp.chabufcp.R;
import com.app.fcp.chabufcp.adapter.listItemAdapter;
import com.app.fcp.chabufcp.entity.itemgnl;
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
public class fragment_fruit extends Fragment {
    private final String MSG_MainActivity = "fragment_fruit";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.tab_fragment_fruit,container,false);

        String post = "type";
        String type = "9";
        itemgnl itm = new itemgnl(type,post);
        Map<String,List> map = itm.initData();

        int[] imgAddMinus = {R.drawable.button_add,R.drawable.button_remove};
        listItemAdapter myAdap = new listItemAdapter(getActivity(),imgAddMinus, map.get("ListItmName"));

        ListView list = (ListView) view.findViewById(R.id.listView_fruit);
        list.setAdapter(myAdap);
        return view;
    }
}
