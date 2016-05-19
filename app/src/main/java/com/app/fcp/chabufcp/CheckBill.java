package com.app.fcp.chabufcp;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.fcp.chabufcp.adapter.OverViewAdapter;
import com.app.fcp.chabufcp.entity.HisTrnHdr;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CheckBill extends AppCompatActivity {
    private final String MSG = "CheckBill";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(MSG, "onCreate");
        setContentView(R.layout.check_bill_activity);
        initTable();

    }

    private void initTable() {
        HisTrnHdr hdr = new HisTrnHdr();
        Map<String,List> map = hdr.initData();
        if(map.get("ListHdrTableNo")!=null){
            ListView list = (ListView) findViewById(R.id.check_bill_listView_checkbill);
            OverViewAdapter myAdap = new OverViewAdapter(this,R.layout.check_bill_activity, map);
            list.setAdapter(myAdap);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.i(MSG, "Start setOnItemClickListener");
                    TextView TextViewtableNo = (TextView) view.findViewById(R.id.list_view_over_view_table_no);
                    String numTable = TextViewtableNo.getText().toString();

                    TextView TextViewTableId = (TextView) view.findViewById(R.id.list_view_over_view_table_id);
                    String tableId = TextViewTableId.getText().toString();

                    TextView TextViewTime = (TextView) view.findViewById(R.id.list_view_over_view_table_time);
                    TextView TextViewCus = (TextView) view.findViewById(R.id.list_view_over_view_table_cus);
                    TextView TextViewPrice = (TextView) view.findViewById(R.id.list_view_over_view_table_price);


                    Log.i(MSG, "numTable =" + numTable);
                    Log.i(MSG, "tableId = " + tableId);

                    Intent i = new Intent(getApplicationContext(),ConfirmCheckBill.class);
                    i.putExtra("numTable", numTable);
                    i.putExtra("tableId", tableId);
                    i.putExtra("numCustomer",TextViewCus.getText().toString());
                    i.putExtra("time",TextViewTime.getText().toString());
                    i.putExtra("totalPrice",TextViewPrice.getText().toString());
                    startActivity(i);
                }
            });
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        initTable();
    }
}
