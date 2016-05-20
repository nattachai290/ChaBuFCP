package com.app.fcp.chabufcp.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.fcp.chabufcp.CheckOrder;
import com.app.fcp.chabufcp.R;
import com.app.fcp.constant.Constant;

import java.util.ArrayList;
import java.util.zip.Checksum;

/**
 * Created by user on 5/19/2016.
 */
public class listCheckOrder extends ArrayAdapter implements CheckOrder.handlerVisible {
    private final String MSG = "listCheckOrder";
    private Activity context;
    private ArrayList<String> order;
    private ArrayList<Integer> numOrder;
    private int[] imageId = {Constant.BUTTON_ADD, Constant.BUTTON_REMOVE};

    private ImageView imgPlus;
    private ImageView imgMinus;

    public listCheckOrder(Activity context, ArrayList<String> order,ArrayList<Integer> numOrder) {
        super(context, R.layout.list_chck_order, order);
        this.context = context;
        this.order = new ArrayList<String>(order);
        this.numOrder = new ArrayList<Integer>(numOrder);
    }


    /*public interface VisibleImg {
        public void sendImage(ImageView imgPlus, ImageView imgMinus);
        public int onClickImgPlusBtn(int value);
        public int onClickImgMinusBtn(int value);
    }

    VisibleImg visibleImg = (VisibleImg) getContext();*/

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = context.getLayoutInflater().inflate(R.layout.list_chck_order, null, true);
        final TextView tvOrder = (TextView) convertView.findViewById(R.id.list_check_order_check_order);
        final TextView tvNum = (TextView) convertView.findViewById(R.id.list_check_order_check_num);
        tvOrder.setText(order.get(position));
        tvNum.setText(String.valueOf(numOrder.get(position)));

        imgPlus = (ImageView) convertView.findViewById(R.id.list_check_order_imageButtonPlus);
        imgPlus.setImageResource(imageId[0]);
        imgPlus.setVisibility(View.INVISIBLE);
        imgPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String orderNum = tvNum.getText().toString();
                int number = 1;
                Integer value = null;
                Log.i(MSG, orderNum);

                try {
                    value = Integer.valueOf(orderNum);

                    if (value == 99) {
                        Toast.makeText(getContext(), Constant.MAX_ORDER, Toast.LENGTH_LONG).show();
                    } else {
                        int newValue = ++value;
                        tvNum.setText(String.valueOf(newValue));
                        int index = Constant.nameOrder.indexOf(order.get(position));
                        Constant.numberOrder.set(index, newValue);
                    }

                } catch (NumberFormatException e) {
                    tvNum.setText(String.valueOf(number));
                    Constant.nameOrder.add(order.get(position));
                    Constant.numberOrder.add(number);
                } finally {
//                    sendDataAdapter.sendData(Constant.nameOrder,Constant.numberOrder);
                    Log.i(MSG, "order=" + order.get(position));
                }
            }
        });
        imgMinus = (ImageView) convertView.findViewById(R.id.list_check_order_imageButtonMinus);
        imgMinus.setImageResource(imageId[1]);
        imgMinus.setVisibility(View.INVISIBLE);
        imgMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String orderNum = tvNum.getText().toString();
                Integer value = null;

                Log.i(MSG, orderNum);
                try {
                    value = Integer.valueOf(orderNum);

                    if (value == 0) {
                        Toast.makeText(getContext(), Constant.MIN_ORDER, Toast.LENGTH_LONG).show();
                    } else {
                        int newValue = --value;
                        if (newValue == 0) {
                            tvNum.setText("");
                        } else {
                            tvNum.setText(String.valueOf(newValue));
                        }
                        int index = Constant.nameOrder.indexOf(order.get(position));
                        Constant.numberOrder.set(index, newValue);
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(getContext(), Constant.MIN_ORDER, Toast.LENGTH_LONG).show();
                }
            }
        });

        return convertView;
    }

    public ArrayList<Integer> getNumOrder() {
        return numOrder;
    }

    public ImageView[] getImgView() {
        ImageView[] mArray = {imgPlus, imgMinus};
        return mArray;
    }

    @Override
    public void setVisible(ImageView plus, ImageView minus) {
        plus.setVisibility(View.VISIBLE);
        minus.setVisibility(View.VISIBLE);
    }
}
