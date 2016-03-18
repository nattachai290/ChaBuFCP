package com.app.fcp.database.service.Imp;

import android.os.AsyncTask;
import android.util.Log;

import com.app.fcp.database.service.QueryService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

/**
 * Created by Arm on 19/3/2559.
 */
public class QueryServiceImp implements QueryService {
    private final String MSG_QueryServiceImp = "Msg";

    @Override
    public JSONArray SelectData(final String link_input,String tableName_input) throws InterruptedException, ExecutionException {
        Log.i(MSG_QueryServiceImp, "Begin SelectData");
        final String link = link_input;
        final JSONArray[] jArray = new JSONArray[1];
        final String tableName = tableName_input;

        class connectDataBase extends AsyncTask<Void, Void,Void>{

            @Override
            protected Void doInBackground(Void... params) {

                Log.i(MSG_QueryServiceImp, "Begin connectDataBase");
                URL url = null;
                HttpURLConnection urlConnection = null;

                try{
                    Log.i(MSG_QueryServiceImp, "Connection Database");
                    url = new URL(link);
                    urlConnection = (HttpURLConnection)url.openConnection();
                    Log.i(MSG_QueryServiceImp, String.valueOf(urlConnection));
                    urlConnection.setConnectTimeout(5000);
                    Log.i(MSG_QueryServiceImp, String.valueOf("getResponseCode : "+urlConnection.getResponseCode()));
                    Log.i(MSG_QueryServiceImp, "Success Open Connection Database");
                    Log.i(MSG_QueryServiceImp,String.valueOf("HttpURLConnection.HTTP_OK = "+HttpURLConnection.HTTP_OK));

                    if(urlConnection.getResponseCode()==HttpURLConnection.HTTP_OK){

                        String result ="";
                        InputStream in = urlConnection.getInputStream();
                        InputStreamReader isw = new InputStreamReader(in);
                        Log.i(MSG_QueryServiceImp, "Start read Date");

                        try {
                            BufferedReader reader = new BufferedReader(isw);
                            StringBuilder sb = new StringBuilder();
                            String line = null;
                            while ((line = reader.readLine()) != null) {
                                sb.append(line);
                            }
                            in.close();
                            result = sb.toString();
                            Log.i(MSG_QueryServiceImp, "Result " + result);
                        } catch (Exception e) {
                            Log.i(MSG_QueryServiceImp, "Reader Error "+e.toString());
                        }
                        try {
                            JSONObject jObject = new JSONObject(result);
                            Log.i(MSG_QueryServiceImp,"JSONObject: "+jObject.toString());
                            jArray[0] = jObject.getJSONArray(tableName);
                            Log.i(MSG_QueryServiceImp,"JSONArray: "+ jArray[0].toString());

                        } catch (JSONException e) {
                            Log.i(MSG_QueryServiceImp, "Error parsing data " +e.toString());
                        }
                        catch (Exception e) {
                            Log.i(MSG_QueryServiceImp, "Error parsing data " +e.toString());

                        }
                    }
                }
                catch (Exception e){
                    Log.i(MSG_QueryServiceImp, "Error in http connection: "+ e.toString());
                }
                finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                        Log.i(MSG_QueryServiceImp, "Disconnect Database");
                    }
                }
                return null;
            }
        }

        new connectDataBase().execute().get();

        Log.i(MSG_QueryServiceImp, "Exit SelectData");
        return jArray[0];
    }
}
