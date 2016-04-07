package com.app.fcp.chabufcp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddTable extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_table_activity);
    }

    public void addTable_clickedSubmit(View view) {
        Intent i = new Intent(this,OrderMenu.class);
        EditText number_table = (EditText) findViewById(R.id.editText_number_table);
        EditText number_customer = (EditText) findViewById(R.id.editText_number_customer);
        String numTable = number_table.getText().toString();
        String numCustomer = number_customer.getText().toString();
        i.putExtra("numTable", numTable);
        i.putExtra("numCustomer", numCustomer);
        startActivity(i);
    }
}
