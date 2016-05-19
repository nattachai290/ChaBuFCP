package com.app.fcp.chabufcp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.app.fcp.chabufcp.adapter.HistoryAdapter;
import com.app.fcp.chabufcp.adapter.OverViewAdapter;
import com.app.fcp.chabufcp.adapter.listCheckOrder;
import com.app.fcp.chabufcp.entity.HisTrnDtl;
import com.app.fcp.chabufcp.entity.HisTrnHdr;

import java.util.List;
import java.util.Map;

public class History extends AppCompatActivity {
    private final String MSG = "History";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(MSG,"onCreate");
        setContentView(R.layout.history_activity);
        initTable();
    }

    private void initTable() {
        Log.i(MSG,"initTable");
        HisTrnDtl dtl = new HisTrnDtl();
        Map<String,List> map = dtl.initData();
        if(map.get("ListTableNo")!=null){
            ListView list = (ListView) findViewById(R.id.history_listView_his);
            HistoryAdapter myAdap = new HistoryAdapter(this, map.get("ListTableNo"), map.get("ListItmName"), map.get("ListQty"), map.get("ListTime"), map.get("ListPeople"));
            list.setAdapter(myAdap);
        }

    }
}
