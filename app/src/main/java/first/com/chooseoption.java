package first.com;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

public class chooseoption extends AppCompatActivity {
    //要先知道醫生上班日期,才能顯示選項
   // private String[] data={"4/18","4/22","4/24","4/26","4/27","4/29","4/30","5/1"};
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
    //顯示醫生姓名
    ArrayList data = new ArrayList<String>();
    public void loadData(){
        String urlString = "https://api.airtable.com/v0/appgPqAWrw2xTWKdx/List of doctor?view=Grid%20view&api_key=keygkXy0a4GuCXh7p";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(urlString, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("Hot Text:", response.toString());
                //ListView kindview =(ListView)findViewById(R.id.kindview);
                //TextView tview =(TextView)findViewById(R.id.tview);
                String Json = response.toString();
                try {

                    //顯示所選擇科別
                    TextView selectnum = (TextView)findViewById(R.id.selectnum);
                    Bundle bundle = getIntent().getExtras();
                    String selected_type = bundle.getString("name");
                    selectnum.setText(selected_type+"醫生列表");
                    //接所選order
                    String selected_num = bundle.getString("order");

                    JSONArray Array = response.getJSONArray("records");

                    for (int i = 0; i < Array.length(); i++) {
                        JSONObject userdata = Array.getJSONObject(i);
                        JSONObject fields = userdata.getJSONObject("fields");
                        String id = fields.getString("Divisions_number");
                        String name = fields.getString("Doctor_name");
                        String nmaeoftiome = fields.getString("Name_of_time");
                        /*if(selected_num.equals(id)){
                            String docname = fields.getString("Doctor_name");
                            Toast.makeText(getApplicationContext(),
                                   docname, Toast.LENGTH_LONG).show();
                        }*/
                        //Toast.makeText(getApplicationContext(),
                               // id, Toast.LENGTH_LONG).show();
                         //data.add(id);


                        //列出所選擇科別的醫生
                        if(selected_num.equals(id)) {
                            LinearLayout ll = findViewById(R.id.testll);
                            LinearLayout ll2 = new LinearLayout(chooseoption.this);
                            //取得風格
                            Resources res = getResources();
                            ll2.setBackgroundDrawable(res.getDrawable(R.drawable.rectangle));
                            TextView tt = new TextView(chooseoption.this);

                            tt.setWidth(400);
                            tt.setHeight(100);
                            tt.setText("醫生姓名:"+name);
                            ll2.addView(tt);
                            ll.addView(ll2);
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
        setContentView(R.layout.chooseoption);
        final String[] datedata={option1,option2};
        Spinner spinner = findViewById(R.id.week);
        final ArrayAdapter<String> dateList = new ArrayAdapter<>(chooseoption.this,android.R.layout.simple_spinner_dropdown_item,datedata);
        spinner.setAdapter(dateList);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(chooseoption.this,"您選擇的月份是："+dateList.getItem(position),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        loadData();




        /*ArrayAdapter<String> adapter=new ArrayAdapter<String>(chooseoption.this,android.R.layout.simple_list_item_1,data);
        ListView listview=(ListView)findViewById(R.id.frank);
        listview.setAdapter(adapter);  */
    }
        }
