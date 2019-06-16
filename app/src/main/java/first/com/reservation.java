package first.com;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class reservation extends AppCompatActivity{
    private  ArrayList searchdata=new ArrayList<String>();
    private ArrayList  data = new ArrayList<String>();
    private void loadData(){
        String urlString = "https://api.airtable.com/v0/appgPqAWrw2xTWKdx/List of divisions?view=Grid%20view&api_key=keygkXy0a4GuCXh7p";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(urlString, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode,Header[] headers, JSONObject response) {
               // Toast.makeText(getApplicationContext(),
                      //  "Success!", Toast.LENGTH_LONG).show();
                Log.d("Hot Text:", response.toString());
                //ListView kindview =(ListView)findViewById(R.id.kindview);
                //TextView tview =(TextView)findViewById(R.id.tview);
                String Json = response.toString();
                try {
                    JSONArray Array = response.getJSONArray("records");
                        for(int i = 0; i<Array.length();i++) {
                            JSONObject userdata = Array.getJSONObject(i);
                            JSONObject fields = userdata.getJSONObject("fields");
                            String id = fields.getString("Divisions_name");
                            int sortid = fields.getInt("Divisions_number");
                            data.add(id);
                            boolean isser = false;
                            EditText ser = findViewById(R.id.ser);
                            if (id.contains(ser.getText().toString()) && ser.getText().toString() != " ") {
                                searchdata.add(id);
                                isser = true;
                            }
                            if (isser == false) {
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(reservation.this, android.R.layout.simple_list_item_1, searchdata);
                                ListView listview = (ListView) findViewById(R.id.kindview);
                                listview.setAdapter(adapter);
                                Toast.makeText(getApplicationContext(),
                                        ser.getText().toString(), Toast.LENGTH_LONG).show();
                                //點選科別跳頁
                                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView arg0, View arg1, int position, long arg3) {
                                        Intent intent = new Intent();
                                        intent.setClass(reservation.this, chooseoption.class);
                                        Bundle bundle = new Bundle();
                                        String divname = searchdata.get(position).toString();
                                        bundle.putString("name", divname);
                                        //bundle.putString("order",position+1+"");
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                    }
                                });
                            } else {
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(reservation.this, android.R.layout.simple_list_item_1, data);
                                ListView listview = (ListView) findViewById(R.id.kindview);
                                listview.setAdapter(adapter);
                                //點選科別跳頁
                                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView arg0, View arg1, int position, long arg3) {
                                        Intent intent = new Intent();
                                        intent.setClass(reservation.this, chooseoption.class);
                                        Bundle bundle = new Bundle();
                                        String divname = data.get(position).toString();
                                        bundle.putString("name", divname);
                                        //bundle.putString("order",position+1+"");
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                    }
                                });
                            }


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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation);
        loadData();

    }
}
