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
import java.util.concurrent.ExecutionException;

public class CheckOrder extends AppCompatActivity {
    private final String MSG = "CheckOrder";
    private ArrayList<String> order = new ArrayList<String>(Constant.nameOrder);;
    private ArrayList<Integer> num = new ArrayList<Integer>(Constant.numberOrder);;
    private ArrayList<Integer> itmID = new ArrayList<Integer>(Constant.idOrder);;
    private ImageView imgPlus,imgMinus;
    private String tableId,numTable;
    private listCheckOrder mList;
    private handlerVisible mVisible;

    public interface handlerVisible {
        public void setVisible(ImageView plus, ImageView minus);
    }
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
        mList = new listCheckOrder(this, order, num);

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
        final String link = DatabaseConstant.INSERT_HISTRNSDTL;
        try{
            Log.i(MSG, "size="+num.size());
            for(int i=0;i<num.size();i++){
                Log.i(MSG, "i="+i);
                new QueryServiceImp().InsertData(link, "", 4, tableId,String.valueOf(itmID.get(i)),String.valueOf(num.get(i)), User.getUSERID());
            }
            Constant.idOrder = new ArrayList<Integer>();
            Constant.nameOrder = new ArrayList<String>();
            Constant.numberOrder = new ArrayList<Integer>();
            new AlertDialog.Builder(this)
                    .setMessage(Constant.TABLE + numTable + " สั่งรายการอาหารเรียบร้อยแล้ว")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Intent intent = new Intent(view.getContext(), MainOverView.class);
                            startActivity(intent);
                        }
                    })
                    .show();

        }catch (NullPointerException e){
            Log.e(MSG, e.toString());
        }catch (IndexOutOfBoundsException e){
            Log.e(MSG, e.toString());
        }
        catch (Exception e){
            Log.e(MSG,e.toString());
        }
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



//    @Override
//    public void sendImage(ImageView imgPlus, ImageView imgMinus) {
//        this.imgPlus = imgPlus;
//        this.imgMinus = imgMinus;
//    }
//
//    @Override
//    public int onClickImgPlusBtn(int value) {
//        return ++value;
//    }
//
//    @Override
//    public int onClickImgMinusBtn(int value) {
//        return --value;
//    }
}
