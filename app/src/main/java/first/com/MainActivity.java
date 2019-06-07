package first.com;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cz.msebera.android.httpclient.Header;
public class MainActivity extends AppCompatActivity {
    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}*/


    /*public class MainActivity extends AppCompatActivity {

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            Button nextPageBtn = (Button)findViewById(R.id.userlogin);
            nextPageBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this , userlogin.class);
                    startActivity(intent);
                }
            });
        } */
    /*
    //reservation


    */
    //activity_main
    /*private void loadData(){
        String urlString = "https://api.airtable.com/v0/appgPqAWrw2xTWKdx/User?api_key=keyUwcLvTO51TNEHV";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(urlString, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode,Header[] headers, JSONObject response) {
                Toast.makeText(getApplicationContext(),
                        "Success!", Toast.LENGTH_LONG).show();
                Log.d("Hot Text:", response.toString());
                TextView dataview =(TextView)findViewById(R.id.dataview);
                String Json = response.toString();
                try {
                    JSONArray Array = response.getJSONArray("records");
                    for(int i = 0; i<Array.length();i++){
                        JSONObject userdata = Array.getJSONObject(i);
                        String id = userdata.getString("id");

                        dataview.append(id+"\n");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }


            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject error) {
                Toast.makeText(getApplicationContext(),
                        "Error: " + statusCode + " " + e.getMessage(),
                        Toast.LENGTH_LONG).show();
                // Log error message
                Log.e("Hot Text:", statusCode + " " + e.getMessage());
            }
        });
    }*/
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //loadData();

      Button nextPageBtn = (Button) findViewById(R.id.userlogin);
        nextPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, userlogin.class);
                startActivity(intent);
            }
        });
    }
}



