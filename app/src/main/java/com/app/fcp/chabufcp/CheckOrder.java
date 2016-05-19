package com.app.fcp.chabufcp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class CheckOrder extends AppCompatActivity implements listCheckOrder.VisiblaImg {
    private final String MSG = "CheckOrder";
    private ArrayList<String> order = new ArrayList<String>(Constant.nameOrder);;
    private ArrayList<Integer> num = new ArrayList<Integer>(Constant.numberOrder);;
    private ArrayList<Integer> itmID = new ArrayList<Integer>(Constant.idOrder);;
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
        listCheckOrder mList = new listCheckOrder(this, order, num);

        ListView listView = (ListView) findViewById(R.id.listView_chckedOrder);
        listView.setAdapter(mList);

        Button edit = (Button) findViewById(R.id.chckedOrder_change_order_button);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(MSG, "imgPlus: " + imgPlus);
                Log.i(MSG, "imgMinus: " + imgMinus);
                imgPlus.setVisibility(View.INVISIBLE);
                imgMinus.setVisibility(View.INVISIBLE);
            }
        });


    }

    public void ConfirmOrder(final View view){
        Log.i(MSG, "ConfirmOrder");
        Log.i(MSG, String.valueOf(num.size()));
                String link = DatabaseConstant.INSERT_HISTRNSDTL;
        try{
            for(int i : num){
                new QueryServiceImp().InsertData(link, "", 4, tableId,String.valueOf(itmID.get(i)),String.valueOf(num.get(i)), User.getUSERID());
            }

            new AlertDialog.Builder(this)
                    .setMessage(Constant.TABLE + numTable+" สั่งรายการอาหารเรียบร้อยแล้ว")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(view.getContext(),MainOverView.class);
                            startActivity(intent);
                        }
                    })
                    .show();
        }catch (NullPointerException e){
            Log.i(MSG, "Error NullPointer: "+e.toString());
        } catch (Exception e){
            Log.i(MSG, "Error Exception: "+e.toString());
        }
    }
    @Override
    public void sendImage(ImageView imgPlus, ImageView imgMinus) {
        this.imgPlus = imgPlus;
        this.imgMinus = imgMinus;
    }
}
