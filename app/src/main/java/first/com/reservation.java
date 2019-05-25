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
import android.widget.ListView;

import cz.msebera.android.httpclient.Header;

public class reservation extends AppCompatActivity{
    private String[] data;
    private void loadData(){
        String urlString = "https://api.airtable.com/v0/appgPqAWrw2xTWKdx/List of divisions?api_key=keyKStB2L0gi9sY2B";
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
                        JSONObject fields=userdata.getJSONObject("fields");
                        String id = fields.getString("Divisions_name");

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
    }


    //private String[] data={"胸腔內科","心臟內科","老年醫學科","神經內科","血液腫瘤科","腎臟內科","一般外科","神經外科","小兒科","整形外科","骨科","泌尿科"};
    /*protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation);
        //setContentView(R.layout.reservationimformation);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(reservation.this,android.R.layout.simple_list_item_1,data);
        ListView listview=(ListView)findViewById(R.id.branch);
        listview.setAdapter(adapter);

    }*/
}
