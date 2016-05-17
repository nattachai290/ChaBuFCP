package com.app.fcp.chabufcp;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
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

import java.util.List;
import java.util.Map;

public class CheckBill extends AppCompatActivity {
    private final String MSG = "CheckBill";
    NfcAdapter nfcAdapter;
    PendingIntent pendingIntent;
    IntentFilter[] intentFilterArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(MSG, "onCreate");
        setContentView(R.layout.check_bill_activity);
        initTable();


//        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
//        Log.i(MSG, "nfcAdapter = "+nfcAdapter);
//
//        if(nfcAdapter!=null && nfcAdapter.isEnabled()){
//            Toast.makeText(this,"NFC Available",Toast.LENGTH_LONG).show();
//            Log.i(MSG, "NFC Available");
//        }
//        else{
//            Toast.makeText(this,"NFC not Available",Toast.LENGTH_LONG).show();
//            Log.i(MSG, "NFC not Available");
//            finish();
//            return;
//        }
        
    }

    
//    @Override
//    protected void onNewIntent(Intent intent) {
//        Toast.makeText(this, "NFc intent received!", Toast.LENGTH_LONG).show();
//        handleIntent(getIntent());
//        super.onNewIntent(intent);
//    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFilterArray, null);
//    }
//
//    private void handleIntent(Intent intent) {
//        Intent ndcIntent = new Intent(this, getClass());
//        ndcIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//
//        pendingIntent = PendingIntent.getActivity(this, 0,ndcIntent, 0);
//        IntentFilter intentFilter = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
//        try{
//            intentFilter.addDataType("text/plain");
//            intentFilterArray = new IntentFilter[]{intentFilter};
//        }catch (Throwable t){
//            Log.i(MSG, "Throwable :" + t.toString());
//        }
//    }
//
//    @Override
//    protected void onPause() {
//        nfcAdapter.disableForegroundDispatch(this);
//        super.onPause();
//    }


    private void initTable() {
        HisTrnHdr hdr = new HisTrnHdr();
        Map<String,List> map = hdr.initData();
        if(map.get("ListHdrTableNo")!=null){
            ListView list = (ListView) findViewById(R.id.listView_checkbill);
            OverViewAdapter myAdap = new OverViewAdapter(this,R.layout.check_bill_activity, map);
            list.setAdapter(myAdap);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.i(MSG, "Start setOnItemClickListener");
                    TextView TextViewtableNo = (TextView) view.findViewById(R.id.table_no);
                    String numTable = TextViewtableNo.getText().toString();

                    TextView TextViewTableId = (TextView) view.findViewById(R.id.table_id);
                    String tableId = TextViewTableId.getText().toString();

                    Log.i(MSG, "numTable ="+numTable);
                    Log.i(MSG, "tableId = "+tableId);
                }
            });
        }

    }
}
