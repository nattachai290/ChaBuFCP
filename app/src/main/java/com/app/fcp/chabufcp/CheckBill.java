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
//    private NfcAdapter nfcAdapter;
//    private int creditCard = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(MSG, "onCreate");
        setContentView(R.layout.check_bill_activity);
        initTable();

//        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
//        Log.i(MSG, "nfcAdapter = " + nfcAdapter);
//
//        if (nfcAdapter == null) {
//            // Stop here, we definitely need NFC
//            Toast.makeText(this, "This device doesn't support NFC.", Toast.LENGTH_LONG).show();
//            finish();
//            return;
//        }
//
//        else if (!nfcAdapter.isEnabled()) {
//            Toast.makeText(this, "NFC is disabled.", Toast.LENGTH_LONG).show();
//        }
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

//                    creditCard = 1;
//                    Log.i(MSG, "creditCard="+creditCard);
//                    Snackbar.make(view, "Please scan credit card", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    Intent i = new Intent(getApplicationContext(),ConfirmCheckBill.class);
//                    i.setAction("android.nfc.action.TAG_DISCOVERED");
//                    i.addCategory("android.intent.category.DEFAULT");
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
//    @Override
//    protected void onResume() {
//        Log.i(MSG, "onResume");
//        Log.i(MSG, "creditCard="+creditCard);
//        super.onResume();
//        Intent intent = getIntent();
//        Log.i(MSG, "intent = " + intent);
//        String action = intent.getAction();
//        Log.i(MSG, "action = " + action);
//
//        Log.i(MSG, "getType = " + intent.getType());
//
//        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)&&creditCard==1) {
//            Toast.makeText(this,
//                    "onResume() - ACTION_TAG_DISCOVERED",
//                    Toast.LENGTH_SHORT).show();
//
//            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
//            if(tag == null){
//                Toast.makeText(this, "tag == null", Toast.LENGTH_LONG).show();
//                Log.i(MSG, "tag == null");
//            }else{
//                Log.i(MSG, "tag != null");
//                String tagInfo = tag.toString() + "\n";
//                tagInfo += "\nTag Id: \n";
//
//                byte[] tagId = tag.getId();
//                tagInfo += "length = " + tagId.length +"\n";
//
//
//                for(int i=0; i<tagId.length; i++){
//                    tagInfo += Integer.toHexString(tagId[i] & 0xFF) + " ";
//
//                }
//                tagInfo += "\n";
//
//
//                String[] techList = tag.getTechList();
//                tagInfo += "\nTech List\n";
//
//
//                tagInfo += "length = " + techList.length +"\n";
//
//
//                for(int i=0; i<techList.length; i++){
//                    tagInfo += techList[i] + "\n ";
//
//                }
//
//                Toast.makeText(this, tagInfo, Toast.LENGTH_LONG).show();
//
//            }
//        }else{
//            Toast.makeText(this,
//                    "onResume() : " + action,
//                    Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        Log.i(MSG, "onNewIntent");
//    }
}
