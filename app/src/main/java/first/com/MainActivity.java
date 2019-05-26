package first.com;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View;
import android.os.Bundle;
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

    Date currentd = new Date();

    //當周
    Date nextweek=new Date(new Date().getTime()+6*24*60*60*1000);
    SimpleDateFormat takeinidate= new SimpleDateFormat("yyyyMMdd");
    SimpleDateFormat takeenddate= new SimpleDateFormat("yyyyMMdd");
    String ini = takeinidate.format(currentd).substring(4);
    String end = takeenddate.format(nextweek).substring(4);
    String option1="當週("+ini+"~"+end+")";

    //第二周
    Date nextweek2=new Date(new Date().getTime()+7*24*60*60*1000);
    Date nextweek3=new Date(new Date().getTime()+13*24*60*60*1000);
    String ini2 = takeinidate.format(nextweek2).substring(4);
    String end2 = takeenddate.format(nextweek3).substring(4);
    String option2="下週("+ini2+"~"+end2+")";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chooseoption);
        String[] datedata={option1,option2};
        Spinner spinner = findViewById(R.id.week);
        ArrayAdapter<String> dateList = new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_spinner_dropdown_item,datedata);
        spinner.setAdapter(dateList);

        /*跳頁按鈕Button nextPageBtn = (Button)findViewById(R.id.userlogin);
        nextPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this , userlogin.class);
                startActivity(intent);
            }
        });*/
    }
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
    /*protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //loadData();

      / Button nextPageBtn = (Button)findViewById(R.id.button12);
        nextPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this , userlogin.class);
                startActivity(intent);
            }
        });*/
}



