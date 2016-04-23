package com.app.fcp.chabufcp;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddTable extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_table_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void addTable_clickedSubmit(View view) {

        EditText number_table = (EditText) findViewById(R.id.editText_number_table);
        EditText number_customer = (EditText) findViewById(R.id.editText_number_customer);
        String numTable = number_table.getText().toString();
        String numCustomer = number_customer.getText().toString();
        if(numTable.isEmpty()&&numCustomer.isEmpty()){
//            Snackbar.make(view, "กรุณาใส่หมายเลขโต๊ะ และจำนวนลูกค้า", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            Toast.makeText(this, "กรุณาใส่หมายเลขโต๊ะ และจำนวนลูกค้า", Toast.LENGTH_LONG).show();
        }
        else if(numTable.isEmpty()){
//            Snackbar.make(view, "กรุณาใส่หมายเลขโต๊ะ", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            Toast.makeText(this, "กรุณาใส่หมายเลขโต๊ะ", Toast.LENGTH_LONG).show();
        }
        else if(numCustomer.isEmpty()){
//            Snackbar.make(view, "กรุณาใส่จำนวนลูกค้า", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            Toast.makeText(this, "กรุณาใส่จำนวนลูกค้า", Toast.LENGTH_LONG).show();
        }
        else{
            Intent i = new Intent(this,OrderMenu.class);
            i.putExtra("numTable", numTable);
            i.putExtra("numCustomer", numCustomer);
            startActivity(i);
        }

    }
}
