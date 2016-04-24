package com.app.fcp.chabufcp;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.app.fcp.constant.Constant;
import com.app.fcp.constant.DatabaseConstant;
import com.app.fcp.database.service.Imp.QueryServiceImp;
import com.app.fcp.database.service.QueryService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class AddTable extends AppCompatActivity {
    private final String MSG = "MainLoginActivity";
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
            Toast.makeText(this, Constant.ERROR_NUMBER_TABLE_AND_CUSTOMER, Toast.LENGTH_LONG).show();
        }
        else if(numTable.isEmpty()){
//            Snackbar.make(view, "กรุณาใส่หมายเลขโต๊ะ", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            Toast.makeText(this, Constant.ERROR_NUMBER_TABLE, Toast.LENGTH_LONG).show();
        }
        else if(numCustomer.isEmpty()){
//            Snackbar.make(view, "กรุณาใส่จำนวนลูกค้า", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            Toast.makeText(this, Constant.ERROR_NUMBER_CUSTOMER, Toast.LENGTH_LONG).show();
        }
        else{

            String num_parameter = "2";
            String link = DatabaseConstant.INSERT_HISTRNSHDR;
            try{
                QueryService query = new QueryServiceImp();
                JSONObject JObjectResult = query.InsertData(link,"now",num_parameter,numTable,numCustomer);
                int jResponse = JObjectResult.getInt("success");
                Log.i(MSG, "jResponse: " + jResponse);

                if(jResponse==1){

                    JSONArray jMember = JObjectResult.getJSONArray("hishdr");
                    Log.i(MSG, "jMember: " + jMember.toString());

                    Log.i(MSG, "=========================================");
                    String QhishdrId=null;

                    for (int i = 0; i < jMember.length(); i++) {

                        QhishdrId = jMember.getJSONObject(i).getString("hishdrId");
                        Log.i(MSG, "User Id: " + QhishdrId);
                    }
                    Log.i(MSG, "=========================================");

                    if(Integer.valueOf(QhishdrId) == 0){
                        Log.i(MSG, "Table not available!!! ");
                        Toast.makeText(this, Constant.TABLE+numTable+Constant.BUSY, Toast.LENGTH_LONG).show();
                    }
                    else{
                        Intent i = new Intent(this,OrderMenu.class);
                        i.putExtra("numTable", numTable);
                        i.putExtra("numCustomer", numCustomer);
                        i.putExtra("tableId", QhishdrId);
                        startActivity(i);
                    }


                }
                else{
                    Log.i(MSG, "Something wrong!!! ");
//                Snackbar.make(view, "username หรือ password ไม่ถูกต้อง", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    Toast.makeText(this, Constant.ERROR_PARAMETER, Toast.LENGTH_LONG).show();
                }

            }catch (JSONException e){
                Log.i(MSG, "Error JSON: "+e.toString());
            }catch (NullPointerException e){
                Log.i(MSG, "Error NullPointer: "+e.toString());
            }catch (InterruptedException e){
                Log.i(MSG, "Error Interrupted: "+e.toString());
            }catch (ExecutionException e){
                Log.i(MSG, "Error Execution: "+e.toString());
            }catch (Exception e){
                Log.i(MSG, "Error Exception: "+e.toString());
            }


        }

    }
}
