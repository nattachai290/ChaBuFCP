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
import com.app.fcp.constant.Constant;
import com.app.fcp.constant.DatabaseConstant;
import com.app.fcp.database.service.Imp.QueryServiceImp;
import com.app.fcp.database.service.QueryService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by arm on 6/4/2559.
 */
public class fragment_aquatic_animal extends Fragment {
    private final String MSG_MainActivity = "fragment_aquatic_animal";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.tab_fragment_aquatic_animal,container,false);
        String post = "type";
        String type = "4";

        itemgnl itm = new itemgnl(type,post);
        Map<String,List> map = itm.initData();
        int[] imgAddMinus = {Constant.BUTTON_ADD, Constant.BUTTON_REMOVE};
        listItemAdapter myAdap = new listItemAdapter(getActivity(),imgAddMinus, map.get("ListItmName"),map.get("ListItmId"));

        ListView list = (ListView) view.findViewById(R.id.listView_aquatic_animal);
        list.setAdapter(myAdap);
        return view;
    }
}
