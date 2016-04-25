package com.app.fcp.database.service;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Created by arm on 25/4/2559.
 */
public interface Async {
    public void afterExec(JSONObject jsonObject);
}
