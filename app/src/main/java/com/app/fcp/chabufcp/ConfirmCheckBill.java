package com.app.fcp.chabufcp;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.fcp.constant.Constant;

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
        getSupportActionBar().setTitle(Constant.TABLE + numTable);

        TextView customer = (TextView) findViewById(R.id.confirm_checkbill_num_customer);
        customer.setText(numCustomer);

        TextView timming = (TextView) findViewById(R.id.confirm_checkbill_time);
        timming.setText(time);

        TextView price = (TextView) findViewById(R.id.confirm_checkbill_price);
        price.setText("Total :"+totalPrice);

        confirm = (Button) findViewById(R.id.confirm_checkbill_btn_pay);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Please scan credit card", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                creditCard = 1;
                Log.i(MSG, "creditCard="+creditCard);
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
    protected void onResume() {
        Log.i(MSG, "onResume");
        Log.i(MSG, "creditCard="+creditCard);
        super.onResume();
        Intent intent = getIntent();
        Log.i(MSG, "intent = " + intent);
        String action = intent.getAction();
        Log.i(MSG, "action = " + action);

        Log.i(MSG, "getType = " + intent.getType());

        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)&&creditCard==1) {
            Toast.makeText(this,
                    "onResume() - ACTION_TAG_DISCOVERED",
                    Toast.LENGTH_SHORT).show();

            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            if(tag == null){
                Toast.makeText(this, "tag == null", Toast.LENGTH_LONG).show();
                Log.i(MSG, "tag == null");
            }else{
                Log.i(MSG, "tag != null");
                String tagInfo = tag.toString() + "\n";
                tagInfo += "\nTag Id: \n";

                byte[] tagId = tag.getId();
                tagInfo += "length = " + tagId.length +"\n";


                for(int i=0; i<tagId.length; i++){
                    tagInfo += Integer.toHexString(tagId[i] & 0xFF) + " ";

                }
                tagInfo += "\n";


                String[] techList = tag.getTechList();
                tagInfo += "\nTech List\n";


                tagInfo += "length = " + techList.length +"\n";


                for(int i=0; i<techList.length; i++){
                    tagInfo += techList[i] + "\n ";

                }

                Toast.makeText(this, tagInfo, Toast.LENGTH_LONG).show();

            }
        }else{
            Toast.makeText(this,
                    "onResume() : " + action,
                    Toast.LENGTH_SHORT).show();
        }
    }
}
