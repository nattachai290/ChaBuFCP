package com.app.fcp.database.service.Imp;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.app.fcp.database.service.QueryService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by Arm on 19/3/2559.
 */
public class QueryServiceImp implements QueryService {
    private final String MSG_QueryServiceImp = "Msg";
//    private final String USER_AGENT = "Mozilla/5.0";

    @Override
    public JSONObject SelectData(String... param) throws InterruptedException, ExecutionException {
        Log.i(MSG_QueryServiceImp, "Begin SelectData");
        final String link = param[0];
        final JSONObject[] jResult = {null};
        final String tableName = param[1];
        final String userName = param[2];

        class connectDataBase extends AsyncTask<Void, Void,Void>{

            @Override
            protected Void doInBackground(Void... params) {

                Log.i(MSG_QueryServiceImp, "Begin connectDataBase");
                URL url = null;
                HttpURLConnection urlConnection = null;

                try{
                    Uri.Builder builder = new Uri.Builder();
                    builder.appendQueryParameter("userName",userName);

                    String param = builder.build().getEncodedQuery();
                    Log.i(MSG_QueryServiceImp, "Connection Database");
                    url = new URL(link);
                    urlConnection = (HttpURLConnection)url.openConnection();
                    Log.i(MSG_QueryServiceImp, String.valueOf(urlConnection));

                    //add reuqest header
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setConnectTimeout(5000);

                    // Send post request
                    urlConnection.setDoOutput(true);
                    BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8"));
                    wr.write(param);
                    wr.flush();
                    wr.close();

                    Log.i(MSG_QueryServiceImp, "Post parameters : " + param);
                    Log.i(MSG_QueryServiceImp, "Sending 'POST' request to URL :  : " + link);

                    Log.i(MSG_QueryServiceImp, String.valueOf("ResponseCode : " + urlConnection.getResponseCode()));
                    Log.i(MSG_QueryServiceImp, "Success Open Connection Database");

                    if(urlConnection.getResponseCode()==HttpURLConnection.HTTP_OK){
                        //post data
                        String result ="";
                        InputStream in = urlConnection.getInputStream();
                        InputStreamReader isw = new InputStreamReader(in);
                        BufferedReader reader = null;
                        Log.i(MSG_QueryServiceImp, "Start read Data");

                        try {
                            reader = new BufferedReader(isw);
                            StringBuilder sb = new StringBuilder();
                            String line = null;
                            while ((line = reader.readLine()) != null) {
                                sb.append(line);
                            }

                            result = sb.toString();
                            Log.i(MSG_QueryServiceImp, "Result " + result);
                        }
                        catch (Exception e) {
                            Log.i(MSG_QueryServiceImp, "Reader Error "+e.toString());
                        }
                        finally {
                            in.close();
                            reader.close();
                        }

                        try {
                            jResult[0] = new JSONObject(result);
                            Log.i(MSG_QueryServiceImp,"JSONObject: "+ jResult[0].toString());
//                            jArray[0] = jObject.getJSONArray(tableName);
//                            Log.i(MSG_QueryServiceImp,"JSONArray: "+ jArray[0].toString());

                        }
                        catch (JSONException e) {
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
        return jResult[0];
    }
}