package com.app.fcp.database.service;


import org.json.JSONObject;


import java.util.concurrent.ExecutionException;

/**
 * Created by Arm on 19/3/2559.
 */
public interface QueryService {
    public JSONObject selectData(String... param) throws ExecutionException, InterruptedException;
    public JSONObject InsertData(String... param) throws ExecutionException, InterruptedException;
    public JSONObject deleteData(String... param) throws ExecutionException, InterruptedException;
    public JSONObject updateData(String... param) throws ExecutionException, InterruptedException;
}
