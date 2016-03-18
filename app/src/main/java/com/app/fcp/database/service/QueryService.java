package com.app.fcp.database.service;

import org.json.JSONArray;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.concurrent.ExecutionException;

/**
 * Created by Arm on 19/3/2559.
 */
public interface QueryService {
    public JSONArray SelectData(String link,String tableName) throws ExecutionException, InterruptedException;
}
