package com.app.fcp.chabufcp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.app.fcp.chabufcp.adapter.listCheckOrder;
import com.app.fcp.chabufcp.entity.User;
import com.app.fcp.constant.Constant;
import com.app.fcp.constant.DatabaseConstant;
import com.app.fcp.database.service.Imp.QueryServiceImp;
import com.app.fcp.database.service.QueryService;

import java.util.ArrayList;

public class CheckOrder extends AppCompatActivity  {
    private final String MSG = "CheckOrder";
    private ArrayList<String> order = new ArrayList(Constant.nameOrder);
    private ArrayList<Integer> num = new ArrayList(Constant.numberOrder);
    private ArrayList<Integer> itmID = new ArrayList(Constant.idOrder);

    private listCheckOrder mList;
    AlertDialog.Builder builder;
    private handlerVisible mVisible;

    public interface handlerVisible {
        public void setVisible(ImageView plus, ImageView minus);
    }

    ImageView imgPlus,imgMinus;
    String tableId,numTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_order_activity);
        Log.i(MSG, "nameOrder: " + Constant.nameOrder);
        Log.i(MSG, "numberOrder: " + Constant.numberOrder);
        Log.i(MSG, "idOrder: " + Constant.idOrder);
        Bundle data = getIntent().getExtras();
        if(data!=null){
            numTable = data.getString("numTable");
            tableId = data.getString("tableId");
        }
        builder = new AlertDialog.Builder(this);
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                return;
            }
        });
        mList = new listCheckOrder(this, order, num);
        ListView listView = (ListView) findViewById(R.id.listView_chckedOrder);
        listView.setAdapter(mList);
        Button button_confirm = (Button) findViewById(R.id.chckedOrder_confirm_button);
        Button editOrder = (Button) findViewById(R.id.chckedOrder_change_order_button);

        button_confirm.setOnTouchListener(new View.OnTouchListener() {
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

        editOrder.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(Color.parseColor("#FF1042F7"), PorterDuff.Mode.SRC_ATOP);
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

        try {
            mVisible =  mList;
        } catch (ClassCastException e) {
            throw new ClassCastException();
        }

        editOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVisible.setVisible(imgPlus, imgMinus);
            }
        });
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(MSG, "save instance state");
        outState.putIntegerArrayList("num", mList.getNumOrder());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(MSG, "restore instance state");
        num = savedInstanceState.getIntegerArrayList("num");
    }

    public void ConfirmOrder(final View view){
        Log.i(MSG, "ConfirmOrder");
        Log.i(MSG, String.valueOf(num.size()));
        if(num.size()>0) {


            String link = DatabaseConstant.INSERT_HISTRNSDTL;
            try {
                for (int i : num) {
                    new QueryServiceImp().InsertData(link, "", 4, tableId, String.valueOf(itmID.get(i)), String.valueOf(num.get(i)), User.getUSERID());
                }
                builder.setMessage(Constant.TABLE + numTable + " สั่งรายการอาหารเรียบร้อยแล้ว");
                builder.show();
            } catch (NullPointerException e) {
                Log.i(MSG, "Error NullPointer: " + e.toString());
            } catch (Exception e) {
                Log.i(MSG, "Error Exception: " + e.toString());
            }
        }
        else{
            builder.setMessage(Constant.TABLE + numTable + " ยังไม่ได้เลือกรายการอาหาร");
            builder.show();
        }
    }

}
