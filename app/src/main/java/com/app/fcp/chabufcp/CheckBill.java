package com.app.fcp.chabufcp;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.AsyncTask;
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
    private NfcAdapter nfcAdapter;
    public final String MIME_TEXT_PLAIN = "text/plain";
    PendingIntent pendingIntent;
    IntentFilter[] intentFilterArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(MSG, "onCreate");
        setContentView(R.layout.check_bill_activity);
        initTable();


        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        Log.i(MSG, "nfcAdapter = " + nfcAdapter);

        if (nfcAdapter == null) {
            // Stop here, we definitely need NFC
            Toast.makeText(this, "This device doesn't support NFC.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        if (!nfcAdapter.isEnabled()) {
            Toast.makeText(this, "NFC is disabled.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "NFC is enabled.", Toast.LENGTH_LONG).show();
        }

        handleIntent(getIntent());

    }
    private void handleIntent(Intent intent) {
        // TODO: handle Intent
        String action = intent.getAction();
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {

            String type = intent.getType();
            if (MIME_TEXT_PLAIN.equals(type)) {

                Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
                new NdefReaderTask().execute(tag);

            } else {
                Log.d(MSG, "Wrong mime type: " + type);
            }
        } else if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)) {

            // In case we would still use the Tech Discovered Intent
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            String[] techList = tag.getTechList();
            String searchedTech = Ndef.class.getName();

            for (String tech : techList) {
                if (searchedTech.equals(tech)) {
                    new NdefReaderTask().execute(tag);
                    break;
                }
            }
        }
    }


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

    public class NdefReaderTask extends AsyncTask<Tag, Void, String> {
        private final String MSG = "CheckBill";
        @Override
        protected String doInBackground(Tag... params) {
            Tag tag = params[0];

            Ndef ndef = Ndef.get(tag);
            if (ndef == null) {
                // NDEF is not supported by this Tag.
                return null;
            }

            NdefMessage ndefMessage = ndef.getCachedNdefMessage();

            NdefRecord[] records = ndefMessage.getRecords();
            for (NdefRecord ndefRecord : records) {
                if (ndefRecord.getTnf() == NdefRecord.TNF_WELL_KNOWN && Arrays.equals(ndefRecord.getType(), NdefRecord.RTD_TEXT)) {
                    try {
                        return readText(ndefRecord);
                    } catch (UnsupportedEncodingException e) {
                        Log.e(MSG, "Unsupported Encoding", e);
                    }
                }
            }
            return null;
        }

        private String readText(NdefRecord record) throws UnsupportedEncodingException {
        /*
         * See NFC forum specification for "Text Record Type Definition" at 3.2.1
         *
         * http://www.nfc-forum.org/specs/
         *
         * bit_7 defines encoding
         * bit_6 reserved for future use, must be 0
         * bit_5..0 length of IANA language code
         */

            byte[] payload = record.getPayload();

            // Get the Text Encoding
            String textEncoding ;
            if((payload[0] & 128) == 0) textEncoding = "UTF-8";
            else textEncoding = "UTF-16";

            // Get the Language Code
            int languageCodeLength = payload[0] & 0063;

            // String languageCode = new String(payload, 1, languageCodeLength, "US-ASCII");
            // e.g. "en"

            // Get the Text
            return new String(payload, languageCodeLength + 1, payload.length - languageCodeLength - 1, textEncoding);
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                Log.i(MSG, "Read content: " + result);
            }
        }
    }
}
