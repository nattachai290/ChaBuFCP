package com.app.fcp.database;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.app.fcp.database.service.Async;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by arm on 25/4/2559.
 */
public class ConnectDB extends AsyncTask<String,Void,JSONObject> {
    private final String MSG = "ConnectDB";
    public Async async = null;
    String link;
    Uri.Builder builder;

    public ConnectDB(String link, Uri.Builder builder) {
        this.link = link;
        this.builder = builder;
    }

    @Override
    protected JSONObject doInBackground(String... params) {
        JSONObject JSONresult = null;
        Log.i(MSG, "Begin ConnectDB");
        URL url = null;
        HttpURLConnection urlConnection = null;

        try{
            Log.i(MSG, "Connection Database");
            String param = builder.build().getEncodedQuery();
            Log.i(MSG, "param: " +param);
            url = new URL(link);
            urlConnection = (HttpURLConnection)url.openConnection();
            Log.i(MSG, String.valueOf(urlConnection));

            //add reuqest header
            urlConnection.setRequestMethod("POST");
            urlConnection.setConnectTimeout(50000);

            // Send post request
            urlConnection.setDoOutput(true);
            BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8"));
            wr.write(param);
            wr.flush();
            wr.close();

            Log.i(MSG, "Post parameters : " + param);
            Log.i(MSG, "Sending 'POST' request to URL :  : " + link);

            Log.i(MSG, String.valueOf("ResponseCode : " + urlConnection.getResponseCode()));
            Log.i(MSG, "Success Open Connection Database");

            if(urlConnection.getResponseCode()==HttpURLConnection.HTTP_OK){
                //post data
                String result ="";
                InputStream in = urlConnection.getInputStream();
                InputStreamReader isw = new InputStreamReader(in,"iso-8859-11");
                BufferedReader reader = null;
                Log.i(MSG, "Start read Data");

                try {
                    reader = new BufferedReader(isw,8);
                    StringBuilder sb = new StringBuilder();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }

                    result = sb.toString();
                    Log.i(MSG, "Result " + result);
                }
                catch (Exception e) {
                    Log.i(MSG, "Reader Error "+e.toString());
                }
                finally {
                    in.close();
                    reader.close();
                }

                try {
                    JSONresult = new JSONObject(result);
//                    jResult[0] = JSONresult;
                    Log.i(MSG, "JSONObject: " + JSONresult.toString());
                }
                catch (JSONException e) {
                    Log.i(MSG, "Error parsing data " +e.toString());
                }
                catch (Exception e) {
                    Log.i(MSG, "Error parsing data " +e.toString());

                }
            }
        }
        catch (Exception e){
            Log.i(MSG, "Error in http connection: "+ e.toString());
        }
        finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
                Log.i(MSG, "Disconnect Database");
            }
        }
        return JSONresult;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        Log.i(MSG, "onPostExecute " + jsonObject);
        if(async!=null){
            async.afterExec(jsonObject);
        }
        else{
            Log.i(MSG, "You have not sign Async");
        };
    }
}
