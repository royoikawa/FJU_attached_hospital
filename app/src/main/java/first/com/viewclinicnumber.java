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

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class viewclinicnumber extends AppCompatActivity {
    private ArrayList r_data = new ArrayList<String>();
    private void loadData(){
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
                   /* JSONArray Array = response.getJSONArray("records");
                    for(int i = 0; i<Array.length();i++){
                        JSONObject userdata = Array.getJSONObject(i);
                        JSONObject fields = userdata.getJSONObject("fields");
                        JSONArray itemsArray=fields.getJSONArray("r_patient");
                        //String itemitem=fields.getString("r_location");
                        String itemitem= (String) itemsArray.get(0);
                        *//*for (int j = 0; j < itemsArray.length(); j++) {
                            JSONObject item = itemsArray.getJSONObject(j);
                            dataview.append(item.length()+"\n");
                        }*//*
                        r_data.add(itemitem);
                    }
                    for(int i=0;i<r_data.size();i++){
                        dataview.append((CharSequence) r_data.get(i)+"\n");
                    }
                    dataview.append((CharSequence) r_data.get(2)+"\n");*/

                    JSONArray Array = response.getJSONArray("records");
                    for(int  i=0;i<Array.length();i++){
                        JSONObject userdata = Array.getJSONObject(i);
                        JSONObject fields = userdata.getJSONObject("fields");
                        JSONArray itemdoctor=fields.getJSONArray("r_doctor");
                        String item1= (String) itemdoctor.get(0);
                        r_data.add(item1);
                        String item2= fields.getString("r_id");
                        r_data.add(item2);
                        JSONArray itemlocation=fields.getJSONArray("r_location");
                        String item3= (String) itemlocation.get(0);
                        r_data.add(item3);
                        JSONArray itempatient=fields.getJSONArray("r_patient");
                        String item4= (String) itempatient.get(0);
                        r_data.add(item4);
                        String item5= fields.getString("r_number");
                        r_data.add(item5);
                    }

                    /*for(int i=0;i<r_data.size();i++){
                        dataview.append((CharSequence) r_data.get(i)+"\n");
                    }*/

                    TextView numText = (TextView)findViewById(R.id.numview);
                    String number = r_data.get(4).toString();
                    numText.setText(number);

                    TextView docText = (TextView)findViewById(R.id.docview);
                    String doctor = r_data.get(0).toString();
                    docText.setText(doctor);

                    TextView locText = (TextView)findViewById(R.id.locview);
                    String location = r_data.get(1).toString();
                    locText.setText(location);

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
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewclinicnumber);
        loadData();


       /* try{
            spinner = (Spinner) findViewById(R.id.spinner);
            //將可選内容與ArrayAdapter連接起來
            adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,Clinic);
            //設置下拉列表的風格
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            //将adapter 添加到spinner中
            spinner.setAdapter(adapter);
        }catch(Exception e){
            e.printStackTrace();
        }*/
    }
}

