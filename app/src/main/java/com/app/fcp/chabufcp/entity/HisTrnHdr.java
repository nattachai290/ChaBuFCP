package com.app.fcp.chabufcp.entity;

import android.util.Log;

import com.app.fcp.constant.DatabaseConstant;
import com.app.fcp.database.service.Imp.QueryServiceImp;
import com.app.fcp.database.service.QueryService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by arm on 25/4/2559.
 */
public class HisTrnHdr {
    private final String MSG = "HisTrnHdr";

    public Map<String,List> initData(){
        Log.i(MSG, "Begin initData");
        Map<String,List> map = new HashMap<String,List>();
        String link = DatabaseConstant.SELECT_HISTRNSHDR;

        try {

            QueryService query = new QueryServiceImp();
            JSONObject JObjectResult = query.selectData(link,"",null,null);
            Log.i(MSG, "JObjectResult: " + JObjectResult);
            int jResponse = JObjectResult.getInt("success");
            Log.i(MSG, "jResponse: " + jResponse);

            if(jResponse==1){
                JSONArray jMember = JObjectResult.getJSONArray("hishdr");
                Log.i(MSG, "hishdr: " + jMember.toString());

                Log.i(MSG, "=========================================");
                String QHdrId=null,QHdrTableNo=null,QHdrPrice=null,QHdrCus=null;
                ArrayList<String> ListHdrId = new  ArrayList<String>();
                ArrayList<String> ListHdrTableNo = new  ArrayList<String>();
                ArrayList<String> ListHdrCus = new  ArrayList<String>();
                ArrayList<String> ListHdrPrice = new  ArrayList<String>();

                for (int i = 0; i < jMember.length(); i++) {

                    QHdrId = jMember.getJSONObject(i).getString("hishdrId");
                    Log.i(MSG, "item Id: " + QHdrId);
                    ListHdrId.add(QHdrId);

                    QHdrTableNo = jMember.getJSONObject(i).getString("hishdrTblNo");
                    Log.i(MSG, "item Name: " + QHdrTableNo);
                    ListHdrTableNo.add(QHdrTableNo);

                    QHdrCus = jMember.getJSONObject(i).getString("hishdrCus");
                    Log.i(MSG, "qty: " + QHdrCus);
                    ListHdrCus.add(QHdrCus);

                    QHdrPrice = jMember.getJSONObject(i).getString("hishdrPrice");
                    ListHdrPrice.add(QHdrPrice);
                    Log.i(MSG, "item type: " + QHdrPrice);
                }
                map.put("ListHdrId",ListHdrId);
                map.put("ListHdrTableNo",ListHdrTableNo);
                map.put("ListHdrCus",ListHdrCus);
                map.put("ListHdrPrice",ListHdrPrice);
                Log.i(MSG, "=========================================");

            }
            else{
                Log.i(MSG, "No data found");
            }
        }
        catch (JSONException e){
            Log.i(MSG, "Error JSON: " + e.toString());
        }catch (NullPointerException e){
            Log.i(MSG, "Error NullPointer: "+e.toString());
        }catch (InterruptedException e){
            Log.i(MSG, "Error Interrupted: "+e.toString());
        }catch (ExecutionException e){
            Log.i(MSG, "Error Execution: "+e.toString());
        }catch (Exception e){
            Log.i(MSG, "Error Exception: "+e.toString());
        }
        Log.i(MSG, "Exit initData");
        return map;
    }
}
