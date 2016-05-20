package com.app.fcp.chabufcp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.app.fcp.constant.Constant;
import com.app.fcp.constant.DatabaseConstant;
import com.app.fcp.chabufcp.entity.User;
import com.app.fcp.database.service.Imp.QueryServiceImp;
import com.app.fcp.database.service.QueryService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MainLoginActivity extends AppCompatActivity {
    private final String MSG = "MainLoginActivity";
    private ProgressBar spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        EditText user = (EditText) findViewById(R.id.login_usrname);
        EditText pwd = (EditText) findViewById(R.id.login_password);
        Button btn_login = (Button) findViewById(R.id.login_btn_logIn);
//        Typeface f = Typeface.createFromAsset(getAssets(),"fonts/THSarabun.ttf");
//        user.setTypeface(f);
//        pwd.setTypeface(f);
//        btn_login.setTypeface(f);
    }

    public void clickLogin(View view){

        new AlertDialog.Builder(this)
                .setMessage("กำลังเชื่อมต่อ")
                .show();

        Log.i(MSG, "Begin clickLogin");
        EditText username_input = (EditText) findViewById(R.id.login_usrname);
        EditText pwd_input = (EditText) findViewById(R.id.login_password);
        String userName = username_input.getText().toString();
        String password = pwd_input.getText().toString();
        String post = "name";
        String link = DatabaseConstant.SELECT_ADMIN;

        try{
            QueryService query = new QueryServiceImp();
            JSONObject JObjectResult = query.selectData(link,"now",post,userName);
            int jResponse = JObjectResult.getInt("success");
            Log.i(MSG, "jResponse: " + jResponse);

            if(jResponse==1){
                Log.i(MSG, "UserName: " + userName);
                Log.i(MSG, "Password: " + password);

                JSONArray jMember = JObjectResult.getJSONArray("admin");
                Log.i(MSG, "jMember: " + jMember.toString());

                Log.i(MSG, "=========================================");
                String QuserId=null,Qname=null,Qpassword=null,Qfname=null,Qlname=null,Qposition=null;

                for (int i = 0; i < jMember.length(); i++) {

                    QuserId = jMember.getJSONObject(i).getString("userId");
                    Log.i(MSG, "User Id: " + QuserId);

                    Qname = jMember.getJSONObject(i).getString("username");
                    Log.i(MSG, "User Name: " + Qname);

                    Qpassword = jMember.getJSONObject(i).getString("pwd");
                    Log.i(MSG, "Password: " + Qpassword);

                    Qfname = jMember.getJSONObject(i).getString("fname");
                    Log.i(MSG, "First Name: " + Qfname);

                    Qlname = jMember.getJSONObject(i).getString("lname");
                    Log.i(MSG, "Last Name: " + Qlname);

                    Qposition = jMember.getJSONObject(i).getString("position");
                    Log.i(MSG, "Position: " + Qposition);

                }
                Log.i(MSG, "=========================================");

                if(Qpassword.equals(password)){
                    Log.i(MSG, "Welcome "+userName);
                    Intent i = new Intent(this,MainOverView.class);

                    User.setUSERID(QuserId);
                    User.setUSERNAME(Qname);
                    User.setFNAME(Qfname);
                    User.setLNAME(Qlname);
                    User.setPOSITION(Qposition);
                    startActivity(i);
                }
                else{
                    Log.i(MSG, "User name or password Wrong!!! ");
                    Toast.makeText(this, Constant.ERROR_PASSWORD, Toast.LENGTH_LONG).show();
                }
            }
            else{
                Log.i(MSG, "User name or password Wrong!!! ");
                Toast.makeText(this, Constant.ERROR_PASSWORD, Toast.LENGTH_LONG).show();
            }

        }catch (JSONException e){
            Log.i(MSG, "Error JSON: "+e.toString());
        }catch (NullPointerException e){
            Toast.makeText(this, Constant.ERROR_CONNECT_DATABASE, Toast.LENGTH_LONG).show();
            Log.i(MSG, "Error NullPointer: "+e.toString());
        }catch (InterruptedException e){
            Log.i(MSG, "Error Interrupted: "+e.toString());
        }catch (ExecutionException e){
            Log.i(MSG, "Error Execution: "+e.toString());
        }catch (Exception e){
            Log.i(MSG, "Error Exception: "+e.toString());
        }
        Log.i(MSG, "Exit clickLogin");
    }

}