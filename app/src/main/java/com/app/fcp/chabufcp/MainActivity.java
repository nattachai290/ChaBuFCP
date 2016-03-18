package com.app.fcp.chabufcp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.app.fcp.constant.DatabaseConstant;
import com.app.fcp.database.connectBase;
import com.app.fcp.database.service.Imp.QueryServiceImp;
import com.app.fcp.database.service.QueryService;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private final String MSG_MainActivity = "Msg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void testConnectDatabase(View view) throws JSONException, ExecutionException, InterruptedException {
        Log.i(MSG_MainActivity, "Begin testConnectDatabase");
        String link = DatabaseConstant.HTTP+DatabaseConstant.DATABASE_GENY_EMU+"/android/testQuery_getAll.php";
        String tableName = "member";
        QueryService query = new QueryServiceImp();
        JSONArray jMember = query.SelectData(link,tableName);

        if(jMember!=null) {
            for (int i = 0; i < jMember.length(); i++) {
                Log.i(MSG_MainActivity, "index: " + i);
                String userId = jMember.getJSONObject(i).getString("userId");
                String name = jMember.getJSONObject(i).getString("username");
                String password = jMember.getJSONObject(i).getString("pwd");
                String fname = jMember.getJSONObject(i).getString("fname");
                String lname = jMember.getJSONObject(i).getString("lname");
                String position = jMember.getJSONObject(i).getString("position");
                Log.i(MSG_MainActivity, "Data: " + userId + "," + name + "," + password + "," + fname + "," + lname + "," + position);
            }
        }
        Log.i(MSG_MainActivity, "Exit testConnectDatabase");
    }


}