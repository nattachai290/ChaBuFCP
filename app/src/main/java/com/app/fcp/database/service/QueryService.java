package com.app.fcp.database.service;


import org.json.JSONObject;


import java.util.concurrent.ExecutionException;

/**
 * Created by Arm on 19/3/2559.
 */
public interface QueryService {
    public JSONObject SelectData(String... param) throws ExecutionException, InterruptedException;
}
