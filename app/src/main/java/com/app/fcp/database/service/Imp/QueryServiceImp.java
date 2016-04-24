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
    private final String MSG = "QueryServiceImp";

    @Override
    public JSONObject selectData(String... param) throws InterruptedException, ExecutionException {
        Log.i(MSG, "Begin SelectData");
        final String link = param[0];
        final JSONObject[] jResult = {null};
        final String post = param[2];
        final String post_var = param[3];
        class connectDataBase extends AsyncTask<Void, Void,Void>{
            @Override
            protected Void doInBackground(Void... params) {

                Log.i(MSG, "Begin connectDataBase");
                URL url = null;
                HttpURLConnection urlConnection = null;

                try{
                    Uri.Builder builder = new Uri.Builder();
                    builder.appendQueryParameter(post,post_var);
                    String param = builder.build().getEncodedQuery();
                    Log.i(MSG, "Connection Database");
                    url = new URL(link);
                    urlConnection = (HttpURLConnection)url.openConnection();
                    Log.i(MSG, String.valueOf(urlConnection));

                    //add reuqest header
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setConnectTimeout(5000);

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
                            jResult[0] = new JSONObject(result);
                            Log.i(MSG,"JSONObject: "+ jResult[0].toString());
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
                return null;
            }
        }



        if(param[1].equalsIgnoreCase("now")){
            new connectDataBase().execute().get();
        }else{
            new connectDataBase().execute();
        }


        Log.i(MSG, "Exit SelectData");
        return jResult[0];
    }

    @Override
    public JSONObject InsertData(final String... param) throws ExecutionException, InterruptedException {
        Log.i(MSG, "Begin InsertData");
        final String link = param[0];
        final JSONObject[] jResult = {null};
        final int number_parameter = Integer.valueOf(param[2]);
        Log.i(MSG, "Link: "+link);
        Log.i(MSG, "number parameter: "+number_parameter);
        class connectDataBase extends AsyncTask<Void, Void,Void> {
            @Override
            protected Void doInBackground(Void... params) {

                Log.i(MSG, "Begin connectDataBase");
                URL url = null;
                HttpURLConnection urlConnection = null;

                try{
                    Uri.Builder builder = new Uri.Builder();
                    for(int k=0;k<number_parameter;k++){
                        builder.appendQueryParameter("para"+(k+1),param[k+2]);
                        Log.i(MSG, "para"+(k+1)+" = "+param[k+2]);
                    }

                    String param = builder.build().getEncodedQuery();
                    Log.i(MSG, "Connection Database");
                    url = new URL(link);
                    urlConnection = (HttpURLConnection)url.openConnection();
                    Log.i(MSG, String.valueOf(urlConnection));

                    //add reuqest header
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setConnectTimeout(5000);

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
                            jResult[0] = new JSONObject(result);
                            Log.i(MSG,"JSONObject: "+ jResult[0].toString());
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
                return null;
            }
        }
        if(param[1].equalsIgnoreCase("now")){
            new connectDataBase().execute().get();
        }else{
            new connectDataBase().execute();
        }
        Log.i(MSG, "Exit InsertData");
        return jResult[0];
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
