package com.app.fcp.chabufcp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
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
    AlertDialog.Builder builder_Error;
    private ProgressBar progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        Button btn = (Button) findViewById(R.id.login_btn_logIn);

        builder_Error = new AlertDialog.Builder(this);

        builder_Error.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i(MSG, "click ok");
                return;
            }
        });

        btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(0xe0f47521, PorterDuff.Mode.SRC_ATOP);
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

    }


    public void clickLogin(View view){
//        MainLoginActivity.this.progress.setProgress(0);
        Log.i(MSG, "Begin clickLogin");
        EditText username_input = (EditText) findViewById(R.id.login_usrname);
        EditText pwd_input = (EditText) findViewById(R.id.login_password);
        final String userName = username_input.getText().toString();
        final String password = pwd_input.getText().toString();
        final String post = "name";
        final String link = DatabaseConstant.SELECT_ADMIN;

        try {
            QueryService query = new QueryServiceImp();
            JSONObject JObjectResult = query.selectData(link,"now",post,userName);
            int jResponse = JObjectResult.getInt("success");
            Log.i(MSG, "jResponse: " + jResponse);

            if(jResponse==1){
                builder_Error.setMessage(Constant.ERROR_PASSWORD);
                Log.i(MSG, "UserName: " + userName);
                Log.i(MSG, "Password: " + password);

                JSONArray jMember = JObjectResult.getJSONArray("admin");
                Log.i(MSG, "jMember: " + jMember.toString());

                Log.i(MSG, "=========================================");
                String QuserId=null,Qname=null,Qpassword=null,Qfname=null,Qlname=null,Qposition=null,Qsex=null;

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

                    Qsex = jMember.getJSONObject(i).getString("sex");
                    Log.i(MSG, "Sex: " + Qsex);

                    Qposition = jMember.getJSONObject(i).getString("position");
                    Log.i(MSG, "Position: " + Qposition);

                }
                Log.i(MSG, "=========================================");

                if(Qpassword.equals(password)){
                    Log.i(MSG, "Welcome " + userName);
                    Intent i = new Intent(getApplicationContext(),MainOverView.class);

                    User.setUSERID(QuserId);
                    User.setUSERNAME(Qname);
                    User.setFNAME(Qfname);
                    User.setLNAME(Qlname);
                    User.setPOSITION(Qposition);
                    User.setSEX(Qsex);
                    startActivity(i);
                    finish();
                }
                else{
                    Log.i(MSG, "User name or password Wrong!!! ");
                    builder_Error.show();
//                    Toast.makeText(getApplicationContext(), Constant.ERROR_PASSWORD, Toast.LENGTH_LONG).show();

                }
            }
            else{
                Log.i(MSG, "User name or password Wrong!!! ");

//                Toast.makeText(getApplicationContext(), Constant.ERROR_PASSWORD, Toast.LENGTH_LONG).show();
                builder_Error.show();
            }
            Thread.sleep(3000);
        }
        catch (InterruptedException e) {
            Log.e(MSG,e.toString());
        } catch (ExecutionException e) {
            Log.e(MSG, e.toString());
        } catch (JSONException e) {
            Log.e(MSG, e.toString());
        }catch (NullPointerException e){
            builder_Error.setMessage(Constant.ERROR_CONNECT_DATABASE);
            Log.e(MSG, e.toString());
            builder_Error.show();
        }
        Log.i(MSG, "Exit clickLogin");
    }




    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}