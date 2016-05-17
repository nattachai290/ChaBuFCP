package com.app.fcp.chabufcp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.app.fcp.chabufcp.adapter.OverViewAdapter;
import com.app.fcp.chabufcp.entity.HisTrnDtl;
import com.app.fcp.chabufcp.entity.HisTrnHdr;

import java.util.List;
import java.util.Map;

public class History extends AppCompatActivity {
    private final String MSG = "History";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_activity);
//        initTable();
    }

    private void initTable() {
        HisTrnDtl dtl = new HisTrnDtl();
        Map<String,List> map = dtl.initData();
        if(map.get("ListHdrTableNo")!=null){
            ListView list = (ListView) findViewById(R.id.listView_his);
            OverViewAdapter myAdap = new OverViewAdapter(this,R.layout.history_activity, map);
            list.setAdapter(myAdap);

        }

    }
}
