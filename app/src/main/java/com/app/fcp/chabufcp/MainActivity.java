package com.app.fcp.chabufcp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


import com.app.fcp.constant.DatabaseConstant;
import com.app.fcp.database.service.Imp.QueryServiceImp;
import com.app.fcp.database.service.QueryService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private final String MSG_MainActivity = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void clickLogin(View view){
        Log.i(MSG_MainActivity, "Begin clickLogin");
        EditText username_input = (EditText) findViewById(R.id.usrname);
        EditText pwd_input = (EditText) findViewById(R.id.password);
        String userName = username_input.getText().toString();
        String password = pwd_input.getText().toString();

        String link = DatabaseConstant.SELECT_ADMIN;
        String tableName = "ADMIN";
        try{
            QueryService query = new QueryServiceImp();
            JSONObject JObjectResult = query.selectData(link,userName,null);
            int jResponse = JObjectResult.getInt("success");
            Log.i(MSG_MainActivity, "jResponse: " + jResponse);

            if(jResponse==1){
                Log.i(MSG_MainActivity, "UserName: " + userName);
                Log.i(MSG_MainActivity, "Password: " + password);

                JSONArray jMember = JObjectResult.getJSONArray("admin");
                Log.i(MSG_MainActivity, "jMember: " + jMember.toString());

                Log.i(MSG_MainActivity, "=========================================");
                String QuserId=null,Qname=null,Qpassword=null,Qfname=null,Qlname=null,Qposition=null;

                for (int i = 0; i < jMember.length(); i++) {

                    QuserId = jMember.getJSONObject(i).getString("userId");
                    Log.i(MSG_MainActivity, "User Id: " + QuserId);

                    Qname = jMember.getJSONObject(i).getString("username");
                    Log.i(MSG_MainActivity, "User Name: " + Qname);

                    Qpassword = jMember.getJSONObject(i).getString("pwd");
                    Log.i(MSG_MainActivity, "Password: " + Qpassword);

                    Qfname = jMember.getJSONObject(i).getString("fname");
                    Log.i(MSG_MainActivity, "First Name: " + Qfname);

                    Qlname = jMember.getJSONObject(i).getString("lname");
                    Log.i(MSG_MainActivity, "Last Name: " + Qlname);

                    Qposition = jMember.getJSONObject(i).getString("position");
                    Log.i(MSG_MainActivity, "Position: " + Qposition);

                }
                Log.i(MSG_MainActivity, "=========================================");

                if(Qpassword.equals(password)){
                    Log.i(MSG_MainActivity, "Welcome "+userName);
                    Intent i = new Intent(this,NavigationUser.class);
                    i.putExtra("userId", QuserId);
                    i.putExtra("userName", Qname);
                    i.putExtra("password", Qpassword);
                    i.putExtra("fname", Qfname);
                    i.putExtra("lname", Qlname);
                    i.putExtra("position", Qposition);
                    startActivity(i);
                }
                else{
                    Log.i(MSG_MainActivity, "User name or password Wrong!!! ");
                }
            }
            else{
                Log.i(MSG_MainActivity, "User name or password Wrong!!! ");
            }

        }catch (JSONException e){
            Log.i(MSG_MainActivity, "Error JSON: "+e.toString());
        }catch (NullPointerException e){
            Log.i(MSG_MainActivity, "Error NullPointer: "+e.toString());
        }catch (InterruptedException e){
            Log.i(MSG_MainActivity, "Error Interrupted: "+e.toString());
        }catch (ExecutionException e){
            Log.i(MSG_MainActivity, "Error Execution: "+e.toString());
        }catch (Exception e){
            Log.i(MSG_MainActivity, "Error Exception: "+e.toString());
        }

        Log.i(MSG_MainActivity, "Exit clickLogin");
    }

}