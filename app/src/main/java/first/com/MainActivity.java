package first.com;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity {
    /*private void loadData(){
        String urlString = "https://api.airtable.com/v0/appgPqAWrw2xTWKdx/reservation?api_key=keycNoUjTn05xspUe";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(urlString, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode,Header[] headers, JSONObject response) {
                Toast.makeText(getApplicationContext(),
                        "Success!", Toast.LENGTH_LONG).show();
                Log.d("Hot Text:", response.toString());
                TextView dataview =(TextView)findViewById(R.id.dataview);
                //String Json = response.toString();
                try {
                    JSONArray Array = response.getJSONArray("records");
                    for(int i = 0; i<Array.length();i++){
                        JSONObject userdata = Array.getJSONObject(i);
                        JSONObject fields = userdata.getJSONObject("fields");
                        JSONArray itemsArray=fields.getJSONArray("r_patient");
                        //String itemitem=fields.getString("r_location");
                        //String itemitem= (String) itemsArray.get(0);
                        for (int j = 0; j < itemsArray.length(); j++) {
                            JSONObject item = itemsArray.getJSONObject(j);
                            dataview.append(item.length()+"\n");
                        }
                        //dataview.append(item+"\n");
                        //dataview.append(item+"\n");

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
      Button nextPageBtn = (Button)findViewById(R.id.userlogin);
        nextPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this , userlogin.class);
                startActivity(intent);
            }
        });
        }

    }

