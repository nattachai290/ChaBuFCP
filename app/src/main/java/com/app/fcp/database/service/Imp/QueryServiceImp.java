package com.app.fcp.database.service.Imp;

import android.net.Uri;
import android.util.Log;

import com.app.fcp.database.ConnectDB;
import com.app.fcp.database.service.Async;
import com.app.fcp.database.service.QueryService;

import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by Arm on 19/3/2559.
 */
public class QueryServiceImp implements QueryService,Async {
    private final String MSG = "QueryServiceImp";
    JSONObject jResult = null;

    @Override
    public JSONObject selectData(String link,String now,String post,String post_var) throws ExecutionException, InterruptedException {
        Log.i(MSG, "Begin SelectData");

        Uri.Builder builder = new Uri.Builder();
        builder.appendQueryParameter(post, post_var);
        ConnectDB con = new ConnectDB(link,builder);

        if(now.equalsIgnoreCase("now")){
            Log.i(MSG, "now");
            jResult = con.execute().get();
            Log.i(MSG, "after now: "+jResult.toString());
        }else{
            Log.i(MSG, "background");
            con.async = this;
            con.execute();
        }
        Log.i(MSG, "Exit SelectData");

        return jResult;
    }

    @Override
    public JSONObject InsertData(String link,String now,int number_parameter,String... param) throws ExecutionException, InterruptedException {
        Log.i(MSG, "Begin InsertData");
        JSONObject jResult = null;
        Log.i(MSG, "Link: "+link);
        Log.i(MSG, "number parameter: "+number_parameter);

        Uri.Builder builder = new Uri.Builder();
        for(int k=0;k<number_parameter;k++){
            builder.appendQueryParameter("para"+(k+1),param[k]);
            Log.i(MSG, "para"+(k+1)+" = "+param[k]);
        }

        ConnectDB con = new ConnectDB(link,builder);
        if(now.equalsIgnoreCase("now")){
            Log.i(MSG, "now");
            jResult = con.execute().get();
            Log.i(MSG, "after now: " + jResult.toString());
        }else{
            Log.i(MSG, "background");
            con.async = this;
            con.execute();
        }
        Log.i(MSG, "Exit InsertData");
        return jResult;
    }

    @Override
    public void deleteData(String... param) throws ExecutionException, InterruptedException {

    }

    @Override
    public void updateData(String link,String now,int number_parameter,String... param) throws ExecutionException, InterruptedException {
        Log.i(MSG, "Begin updateData");
        Log.i(MSG, "Link: "+link);
        Log.i(MSG, "number parameter: "+number_parameter);

        Uri.Builder builder = new Uri.Builder();
        for(int k=0;k<number_parameter;k++){
            builder.appendQueryParameter("para"+(k+1),param[k]);
            Log.i(MSG, "para"+(k+1)+" = "+param[k]);
        }

        ConnectDB con = new ConnectDB(link,builder);
        if(now.equalsIgnoreCase("now")){
            Log.i(MSG, "now");
            jResult = con.execute().get();
            Log.i(MSG, "after now: " + jResult.toString());
        }else{
            Log.i(MSG, "background");
            con.async = this;
            con.execute();
        }
        Log.i(MSG, "Exit updateData");
    }

    @Override
    public void afterExec(JSONObject jsonObject) {
        Log.i(MSG, "afterExec " + jsonObject);
        jResult = jsonObject;
    }
}
