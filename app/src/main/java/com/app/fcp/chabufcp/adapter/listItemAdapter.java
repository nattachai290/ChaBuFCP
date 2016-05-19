package com.app.fcp.chabufcp.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.fcp.chabufcp.R;
import com.app.fcp.constant.Constant;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by arm on 9/4/2559.
 */


public class listItemAdapter extends ArrayAdapter<String> {
    private final String MSG = "listItemAdapter";
    private int layoutResourceId;
    private List<String> list;
    private List<String> listId;
    private int[] imageId;
    Activity context;

    public listItemAdapter(Activity context, int[] resource, List<String> list,List<String> listId) {
        super(context, R.layout.list_view_item, list);
        this.list = new ArrayList<String>(list);
        this.context = context;
        this.listId = new ArrayList<String>(listId);

        imageId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        final int pos = position;
        convertView = context.getLayoutInflater().inflate(R.layout.list_view_item,null,true);
        final TextView numItem = (TextView) convertView.findViewById(R.id.list_view_item_number_order);

        TextView text = (TextView) convertView.findViewById(R.id.list_view_item_list_row_text);
        text.setText(list.get(position));

        ImageView imgPlus = (ImageView) convertView.findViewById(R.id.list_view_item_imageButtonPlus);
        imgPlus.setImageResource(imageId[0]);
        imgPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String order = numItem.getText().toString();
                int number = 1;
                Integer value = null;
                Log.i(MSG, order);

                try {
                    value = Integer.valueOf(order);

                    if (value == 99) {
                        Toast.makeText(getContext(), Constant.MAX_ORDER, Toast.LENGTH_LONG).show();
                    } else {
                        int newValue = value + 1;
                        numItem.setText(String.valueOf(newValue));
                        int index = Constant.nameOrder.indexOf(list.get(pos));
                        Constant.numberOrder.set(index, newValue);
                    }

                } catch (NumberFormatException e) {
                    numItem.setText(String.valueOf(number));
                    Constant.nameOrder.add(list.get(pos));
                    Constant.numberOrder.add(number);
                    Constant.idOrder.add(Integer.valueOf(listId.get(pos)));
                } finally {
//                    sendDataAdapter.sendData(Constant.nameOrder,Constant.numberOrder);
                    Log.i(MSG, "order=" + list.get(pos));
                }
            }
        });
        ImageView imgMinus = (ImageView) convertView.findViewById(R.id.list_view_item_imageButtonMinus);
        imgMinus.setImageResource(imageId[1]);
        imgMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String order = numItem.getText().toString();
                Integer value = null;

                Log.i(MSG, order);
                try {
                    value = Integer.valueOf(order);

                    if (value == 0) {
                        Toast.makeText(getContext(), Constant.MIN_ORDER, Toast.LENGTH_LONG).show();
                    } else {
                        int newValue = value - 1;
                        if (newValue == 0) {
                            numItem.setText("");
                        } else {
                            numItem.setText(String.valueOf(newValue));
                        }
                        int index = Constant.nameOrder.indexOf(list.get(pos));
                        Constant.numberOrder.set(index,newValue);
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(getContext(), Constant.MIN_ORDER, Toast.LENGTH_LONG).show();
                }
            }
        });


        return convertView;
    }

}
