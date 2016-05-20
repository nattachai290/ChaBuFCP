package com.app.fcp.chabufcp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.fcp.constant.Constant;
import com.app.fcp.constant.DatabaseConstant;
import com.app.fcp.database.service.Imp.QueryServiceImp;
import com.app.fcp.database.service.QueryService;

import java.util.concurrent.ExecutionException;

public class ConfirmCheckBill extends AppCompatActivity {
    private final String MSG = "ConfirmCheckBill";
    private String numTable ;
    private String tableId ;
    private String numCustomer ;
    private String time ;
    private String totalPrice;
    private Button confirm;
    NfcAdapter nfcAdapter;
    private int creditCard = 0;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_check_bill_activity);
        Log.i(MSG, "creditCard=" + creditCard);
        Bundle data = getIntent().getExtras();
        if(data!=null){
            numTable = data.getString("numTable");
            tableId = data.getString("tableId");
            numCustomer= data.getString("numCustomer");
            time = data.getString("time");
            totalPrice = data.getString("totalPrice");
        }
        builder = new AlertDialog.Builder(this);
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                return;
            }
        });
        TextView customer = (TextView) findViewById(R.id.confirm_checkbill_num_customer);
        customer.setText(numCustomer);

        TextView tableNo = (TextView) findViewById(R.id.confirm_checkbill_table_no);
        tableNo.setText(Constant.TABLE + numTable);

        TextView timming = (TextView) findViewById(R.id.confirm_checkbill_time);
        timming.setText(time);

        TextView price = (TextView) findViewById(R.id.confirm_checkbill_price);
        price.setText("Total \n" + totalPrice);

        confirm = (Button) findViewById(R.id.confirm_checkbill_btn_pay);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Please scan a credit card", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                creditCard = 1;
                Log.i(MSG, "creditCard="+creditCard);
            }
        });
        confirm.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(Color.parseColor("#FF37F60C"), PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;
                    }
                }
                return false;
            }
        });


        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        Log.i(MSG, "nfcAdapter = " + nfcAdapter);

        if (nfcAdapter == null) {
            // Stop here, we definitely need NFC
            Toast.makeText(this, "This device doesn't support NFC.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        else if (!nfcAdapter.isEnabled()) {
            Toast.makeText(this, "NFC is disabled.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(MSG, "onNewIntent");
        Log.i(MSG, "intent = " + intent);

        String action = intent.getAction();
        Log.i(MSG, "action = " + action);



        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)&&creditCard==1) {
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            Log.i(MSG, "tag =" + tag);
            if(tag == null){
                Log.i(MSG, "tag == null");
            }else{
                Log.i(MSG, "tag != null");
                String link = DatabaseConstant.UPDATE_HISTRNSHDR;
                try{
                    QueryService query = new QueryServiceImp();
                    query.updateData(link,"",1,tableId);
                }catch (NullPointerException e){
                    Log.i(MSG, "Error NullPointer: "+e.toString());
                } catch (Exception e){
                    Log.i(MSG, "Error Exception: "+e.toString());
                }

                builder.setMessage(Constant.TABLE + numTable+" ชำระเงินเรียบร้อยแล้ว");
                builder.show();
                finish();
            }
        }
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)&&creditCard==0){
            Toast.makeText(this, Constant.TABLE + numTable+" กรุณากดยืนยันการชำระเงิน", Toast.LENGTH_LONG).show();
        }
        Log.i(MSG, "onNewIntent end");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(tableId == null){
            finish();
            return;
        }
    }
}
