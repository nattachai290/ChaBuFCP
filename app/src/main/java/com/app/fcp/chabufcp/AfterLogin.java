package com.app.fcp.chabufcp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class AfterLogin extends AppCompatActivity {
    private final String MSG_AfterLogin = "AfterLogin";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);
        Log.i(MSG_AfterLogin, "Start After Login");
        Bundle data = getIntent().getExtras();
        if(data==null){
            return;
        }

        String msgId = data.getString("userId");
        String msgUser = data.getString("userName");
        String msgPwd = data.getString("password");
        String msgFname = data.getString("fname");
        String msgLname = data.getString("lname");
        String msgPos = data.getString("position");
        Log.i(MSG_AfterLogin, "msgId: "+msgId);
        Log.i(MSG_AfterLogin, "msgUser: "+msgUser);
        Log.i(MSG_AfterLogin, "msgPwd: "+msgPwd);
        Log.i(MSG_AfterLogin, "msgFname: "+msgFname);
        Log.i(MSG_AfterLogin, "msgLname: "+msgLname);
        Log.i(MSG_AfterLogin, "msgPos: "+msgPos);

        TextView textId = (TextView) findViewById(R.id.userId);
        TextView textUser = (TextView) findViewById(R.id.userName);
        TextView textFname = (TextView) findViewById(R.id.fname);
        TextView textLname = (TextView) findViewById(R.id.lname);
        TextView textPwd = (TextView) findViewById(R.id.password);
        TextView textPosition = (TextView) findViewById(R.id.pos);

        textId.setText(msgId);
        textUser.setText(msgUser);
        textPosition.setText(msgPos);
        textFname.setText(msgFname);
        textLname.setText(msgLname);
        textPwd.setText(msgPwd);
    }
}
