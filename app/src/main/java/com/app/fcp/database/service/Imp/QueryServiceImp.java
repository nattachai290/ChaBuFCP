package com.app.fcp.database.service.Imp;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.app.fcp.database.service.QueryService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

/**
 * Created by Arm on 19/3/2559.
 */
public class QueryServiceImp implements QueryService {
    private final String MSG_QueryServiceImp = "QueryServiceImp";

    @Override
    public JSONObject selectData(String... param) throws InterruptedException, ExecutionException {
        Log.i(MSG_QueryServiceImp, "Begin SelectData");
        final String link = param[0];
        final JSONObject[] jResult = {null};
        final String name = param[1]!=null?param[1]:null;
        final String id = param[2]!=null?param[2]:null;

        class connectDataBase extends AsyncTask<Void, Void,Void>{
            @Override
            protected Void doInBackground(Void... params) {

                Log.i(MSG_QueryServiceImp, "Begin connectDataBase");
                URL url = null;
                HttpURLConnection urlConnection = null;

                try{
                    Uri.Builder builder = new Uri.Builder();
                    builder.appendQueryParameter("name",name);
                    builder.appendQueryParameter("id",id);
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

    @Override
    public JSONObject InsertData(String... param) throws ExecutionException, InterruptedException {

        class connectDataBase extends AsyncTask<Void, Void,Void> {
            @Override
            protected Void doInBackground(Void... params) {
                return null;
            }
        }
        return null;
    }

    @Override
    public JSONObject deleteData(String... param) throws ExecutionException, InterruptedException {
        class connectDataBase extends AsyncTask<Void, Void,Void> {
            @Override
            protected Void doInBackground(Void... params) {
                return null;
            }
        }
        return null;
    }

    @Override
    public JSONObject updateData(String... param) throws ExecutionException, InterruptedException {
        class connectDataBase extends AsyncTask<Void, Void,Void> {
            @Override
            protected Void doInBackground(Void... params) {
                return null;
            }
        }
        return null;
    }
}
