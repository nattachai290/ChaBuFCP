package com.app.fcp.database.service;


import org.json.JSONObject;


import java.util.concurrent.ExecutionException;

/**
 * Created by Arm on 19/3/2559.
 */
public interface QueryService {
    public JSONObject selectData(String link_in,String now,String post_in,String var_in) throws ExecutionException, InterruptedException;
    public JSONObject InsertData(String link,String now,int number_parameter,String... param) throws ExecutionException, InterruptedException;
    public void deleteData(String... param) throws ExecutionException, InterruptedException;
    public void updateData(String link,String now,int number_parameter,String... param) throws ExecutionException, InterruptedException;
}
