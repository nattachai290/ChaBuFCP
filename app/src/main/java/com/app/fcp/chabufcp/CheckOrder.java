package com.app.fcp.chabufcp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.app.fcp.chabufcp.adapter.listCheckOrder;

import java.util.ArrayList;

public class CheckOrder extends AppCompatActivity {
    private ArrayList<String> order;
    private ArrayList<Integer> num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_order);
        listCheckOrder mList = new listCheckOrder(this, order, num);

        ListView listView = (ListView) findViewById(R.id.listView_chckedOrder);
        listView.setAdapter(mList);
    }

}
