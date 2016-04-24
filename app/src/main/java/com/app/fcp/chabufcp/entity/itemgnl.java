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
 * Created by arm on 9/4/2559.
 */
public class itemgnl {
    private final String MSG = "itemgnl";
    private String type = null;
    private String post = null;

    public itemgnl(String type, String post) {
        this.type = type;
        this.post = post;

    }

    public Map<String,List> initData(){
        Map<String,List> map = new HashMap<String,List>();
        String link = DatabaseConstant.SELECT_ITMGNL;

        try {
            QueryService query = new QueryServiceImp();
            JSONObject JObjectResult = query.selectData(link,"now",post,type);
            int jResponse = JObjectResult.getInt("success");
            Log.i(MSG, "jResponse: " + jResponse);

            if(jResponse==1){
                JSONArray jMember = JObjectResult.getJSONArray("itmgnl");
                Log.i(MSG, "itmgnl: " + jMember.toString());

                Log.i(MSG, "=========================================");
                String QitmId=null,QitmName=null,QitmQty=null,QitmType=null;
                ArrayList<String> ListItmId = new  ArrayList<String>();
                ArrayList<String> ListItmName = new  ArrayList<String>();
                ArrayList<String> ListItmQty = new  ArrayList<String>();

                for (int i = 0; i < jMember.length(); i++) {

                    QitmId = jMember.getJSONObject(i).getString("itmId");
                    Log.i(MSG, "item Id: " + QitmId);
                    ListItmId.add(QitmId);

                    QitmName = jMember.getJSONObject(i).getString("itmName");
                    Log.i(MSG, "item Name: " + QitmName);
                    ListItmName.add(QitmName);

                    QitmQty = jMember.getJSONObject(i).getString("itmQty");
                    Log.i(MSG, "qty: " + QitmQty);
                    ListItmQty.add(QitmQty);

                    QitmType = jMember.getJSONObject(i).getString("itmType");
                    Log.i(MSG, "item type: " + QitmType);
                }
                map.put("ListItmId",ListItmId);
                map.put("ListItmName",ListItmName);
                map.put("ListItmQty",ListItmQty);
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

        return map;
    }
}
