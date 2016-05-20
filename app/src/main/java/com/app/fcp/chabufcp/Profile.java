package com.app.fcp.chabufcp;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.app.fcp.chabufcp.entity.User;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);

        TextView usrId = (TextView) findViewById(R.id.profile_userId);
        TextView usrName = (TextView) findViewById(R.id.profile_username);
        TextView usrFname = (TextView) findViewById(R.id.profile_fname);
        TextView usrLname = (TextView) findViewById(R.id.profile_lname);
        TextView usrPostion = (TextView) findViewById(R.id.profile_pos);
        TextView usrSex = (TextView) findViewById(R.id.profile_sex);
//        Typeface f = Typeface.createFromAsset(getAssets(),"fonts/THSaraban.ttf");
//        usrId.setTypeface(f);
//        usrName.setTypeface(f);
//        usrFname.setTypeface(f);
//        usrLname.setTypeface(f);
//        usrPostion.setTypeface(f);
//        usrSex.setTypeface(f);

        usrId.setText("รหัสพนักงาน : " + User.getUSERID());
        usrName.setText("ชื่อไอดี : "+User.getUSERNAME());
        usrFname.setText("ชื่อ : "+User.getFNAME());
        usrLname.setText("นามสกุล : "+User.getLNAME());
        usrSex.setText("เพศ : "+User.getSEX());
        usrPostion.setText("ตำแหน่ง : "+User.getPOSITION());

    }
}
