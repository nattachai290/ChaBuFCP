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
 * Created by arm on 27/4/2559.
 */
public class HisTrnDtl {

    private final String MSG = "HisTrnDtl";
    JSONObject JObjectResult ;

    public Map<String,List> initData(){
        Log.i(MSG, "Begin initData");
        Map<String,List> map = new HashMap<String,List>();
        String link = DatabaseConstant.SELECT_HISTRNSDTL;

        try {

            QueryService query = new QueryServiceImp();
            JObjectResult = query.selectData(link, "now", null, null);
            Log.i(MSG, "JObjectResult: " + JObjectResult);
            int jResponse = JObjectResult.getInt("success");
            Log.i(MSG, "jResponse: " + jResponse);

            if(jResponse==1){
                JSONArray jMember = JObjectResult.getJSONArray("hisdtl");
                Log.i(MSG, "hisdtl: " + jMember.toString());

                Log.i(MSG, "=========================================");
                String time=null,tableNo=null,itmName=null,people=null,qty=null;
                ArrayList<String> ListTime = new  ArrayList<String>();
                ArrayList<String> ListTableNo = new  ArrayList<String>();
                ArrayList<String> ListItmName = new  ArrayList<String>();
                ArrayList<String> ListPeople = new  ArrayList<String>();
                ArrayList<String> ListQty = new  ArrayList<String>();
                for (int i = 0; i < jMember.length(); i++) {

                    time = jMember.getJSONObject(i).getString("time");
                    Log.i(MSG, "time: " + time);
                    ListTime.add(time);

                    tableNo = jMember.getJSONObject(i).getString("tableNo");
                    Log.i(MSG, "tableNo: " + tableNo);
                    ListTableNo.add(tableNo);

                    itmName = jMember.getJSONObject(i).getString("itmName");
                    Log.i(MSG, "itmName: " + itmName);
                    ListItmName.add(itmName);

                    people = jMember.getJSONObject(i).getString("people");
                    Log.i(MSG, "hishdrTime: " + people);
                    ListPeople.add(people);

                    qty = jMember.getJSONObject(i).getString("qty");
                    Log.i(MSG, "hishdrPrice: " + qty);
                    ListQty.add(qty);

                }
                map.put("ListTime",ListTime);
                map.put("ListTableNo",ListTableNo);
                map.put("ListItmName",ListItmName);
                map.put("ListPeople",ListPeople);
                map.put("ListQty",ListQty);
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
