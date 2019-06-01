package first.com;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
    private Button btn1; //宣告Button
    private EditText txt1; //宣告EditText
    private ArrayList r_data = new ArrayList<String>();
    private ArrayList division = new ArrayList<String>();

    private RadioGroup rg;

    private void loadData() {
        String urlString = "https://api.airtable.com/v0/appgPqAWrw2xTWKdx/reservation?api_key=keycNoUjTn05xspUe";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(urlString, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Toast.makeText(getApplicationContext(),
                        "Success!", Toast.LENGTH_LONG).show();
                Log.d("Hot Text:", response.toString());
                try {
                    JSONArray Array = response.getJSONArray("records");
                    for (int i = 0; i < Array.length(); i++) {
                        JSONObject userdata = Array.getJSONObject(i);
                        JSONObject fields = userdata.getJSONObject("fields");

                        JSONArray itemTime= fields.getJSONArray("time");
                        String item1 = (String) itemTime.get(0);
                        r_data.add(item1);

                        JSONArray itemPatient = fields.getJSONArray("patient");
                        String item2 = (String) itemPatient.get(0);
                        r_data.add(item2);

                        String item3 = fields.getString("r_number");
                        r_data.add(item3);

                        JSONArray itemDoctor = fields.getJSONArray("doctor");
                        String item4 = (String) itemDoctor.get(0);
                        r_data.add(item4);

                        JSONArray itemLocation = fields.getJSONArray("location");
                        String item5 = (String) itemLocation.get(0);
                        r_data.add(item5);
                    }

                    loadDivision();

                    TextView timeText = (TextView) findViewById(R.id.timeview);
                    String time = r_data.get(0).toString().substring(3,8);
                    switch(time){
                        case "9:00~":
                            timeText.setText("早上診");
                        case "13:30":
                            timeText.setText("下午診");
                        case "18:00":
                            timeText.setText("夜間診");
                    }

                    TextView patientText = (TextView) findViewById(R.id.patientview);
                    String number = r_data.get(1).toString();
                    patientText.setText(number);

                    TextView numText = (TextView) findViewById(R.id.numview);
                    String patient = r_data.get(2).toString();
                    numText.setText(patient);

                    TextView docText = (TextView) findViewById(R.id.docview);
                    String doctor = r_data.get(3).toString();
                    docText.setText(doctor);

                    TextView locText = (TextView) findViewById(R.id.locview);
                    String location = r_data.get(4).toString();
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

    private void loadDivision(){
        String urlString = "https://api.airtable.com/v0/appgPqAWrw2xTWKdx/List of divisions?view=Grid%20view&api_key=keycNoUjTn05xspUe";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(urlString, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Toast.makeText(getApplicationContext(),
                        "Success!", Toast.LENGTH_LONG).show();
                Log.d("Hot Text:", response.toString());
                try {
                    JSONArray Array = response.getJSONArray("records");
                    for (int i = 0; i < Array.length(); i++) {
                        JSONObject userdata = Array.getJSONObject(i);
                        JSONObject fields = userdata.getJSONObject("fields");

                        String item = fields.getString("Divisions_name");
                        division.add(item);
                    }

                    TextView titleText = (TextView) findViewById(R.id.titleview);
                    String title = division.get(0).toString();
                    titleText.setText(title);

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

        btn1 = (Button) findViewById(R.id.searchbtn);  //取得Button
        txt1 = (EditText) findViewById(R.id.searchbar); //取得EditText

        RadioGroup rg = (RadioGroup) findViewById(R.id.searchoption);
        // 为 RadioGroup 设置一个监听器 setOnCheckedChanged()
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton btn = (RadioButton) findViewById(checkedId);
                String option = (String) btn.getText();
                Toast.makeText(getApplicationContext(), option, Toast.LENGTH_LONG).show();

                switch (option) {
                    case "科別":
                        btn1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //取得EditText的輸入內容
                                String content = txt1.getText().toString();
                                int index = -1;
                                boolean isIn = division.contains(content);
                                if (isIn == true) {
                                    index = division.indexOf(content);
                                    Toast.makeText(viewclinicnumber.this, content + " " + index, Toast.LENGTH_SHORT).show();
                                    //Toast.makeText(viewclinicnumber.this, index, Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(viewclinicnumber.this, "error", Toast.LENGTH_SHORT).show();
                                }
                                //顯示在Debug Console
                                Log.d("debug", "button click");
                            }
                        });
                        break;
                    case "病人姓名":
                        btn1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //取得EditText的輸入內容
                                String content = txt1.getText().toString();
                                int index = -1;
                                boolean isIn = r_data.contains(content);
                                if (isIn == true) {
                                    index = r_data.indexOf(content);
                                    Toast.makeText(viewclinicnumber.this,  " hello" , Toast.LENGTH_SHORT).show();
                                    //Toast.makeText(viewclinicnumber.this, index, Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(viewclinicnumber.this, "wrong", Toast.LENGTH_SHORT).show();
                                }
                                //顯示在Debug Console
                                Log.d("debug", "button click");
                            }
                        });
                        break;
                }
            }

        });
    }
}

