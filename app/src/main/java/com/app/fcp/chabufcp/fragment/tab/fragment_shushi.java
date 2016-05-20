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
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by arm on 6/4/2559.
 */
public class fragment_shushi extends Fragment {
    private final String MSG = "fragment_shushi";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.tab_fragment_shushi,container,false);
        Log.i(MSG, "onCreateView");
        String post = "type";
        String type = "7";
        itemgnl itm = new itemgnl(type,post);
        Map<String,List> map = itm.initData();

        int[] imgAddMinus = {Constant.BUTTON_ADD, Constant.BUTTON_REMOVE};
        listItemAdapter myAdap = new listItemAdapter(getActivity(),imgAddMinus, map.get("ListItmName"),map.get("ListItmId"));

        ListView list = (ListView) view.findViewById(R.id.listView_shushi);
        list.setAdapter(myAdap);
        return view;
    }
    @Override
    public void onResume() {
        Log.i(MSG, "onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.i(MSG, "onPause");
        super.onPause();
    }

    @Override
    public void onStart() {
        Log.i(MSG, "onStart");
        super.onStart();
    }

    @Override
    public void onDestroy() {
        Log.i(MSG, "onDestroy");
        super.onDestroy();
    }
}
