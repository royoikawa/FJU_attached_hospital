package first.com;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    String[] opening;
    String open="";
    public void loadData(){
        String urlString = "https://api.airtable.com/v0/appgPqAWrw2xTWKdx/List of doctor?view=Grid%20view&api_key=keygkXy0a4GuCXh7p";
        final AsyncHttpClient client = new AsyncHttpClient();
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
                        String nameoftime = fields.getString("Name_of_time");
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
                            LinearLayout ll1 = new LinearLayout(chooseoption.this);
                            ll1.setOrientation(LinearLayout.HORIZONTAL);
                            Resources res = getResources();
                            ll1.setBackgroundDrawable(res.getDrawable(R.drawable.rectangle));
                            ll1.setMinimumHeight(450);
                           // ll2.setDividerPadding(350);
                            //取得風格
                            //樣式
                            //ll2放圖
                            LinearLayout ll2 = new LinearLayout(chooseoption.this);
                            ll2.setOrientation(LinearLayout.HORIZONTAL);
                            ll2.setMinimumWidth(200);ll2.setMinimumHeight(350);
                           //ll2.addView(tt);ll.addView(ll2);
                            //適當排版印出資料
                            LinearLayout ll3=new LinearLayout(chooseoption.this);
                            ll3.setOrientation(LinearLayout.VERTICAL);
                            ll3.setMinimumWidth(400);
                            ll3.setMinimumHeight(350);
                            LinearLayout ll4=new LinearLayout(chooseoption.this);
                            ll4.setOrientation(LinearLayout.VERTICAL);
                            ll4.setMinimumWidth(300);
                            ll4.setMinimumHeight(150);
                            ll4.setPadding(450,0,0,0);
                            //TextView tt = new TextView(chooseoption.this);
                            //tt.setWidth(300);tt.setHeight(80);
                            //tt.setPadding(450,10,0,0);
                           // tt.setText("醫生姓名:"+name);
                            TextView tt = new TextView(chooseoption.this);
                            tt.setWidth(300);tt.setHeight(130);
                            tt.setPadding(20,40,0,0);
                            tt.setText("醫生姓名:"+name);
                            tt.setTextSize(17);
                            ll4.addView(tt);ll3.addView(ll4);
                            open+= nameoftime;
                            //刪掉" "
                            String open2 = open.replace("\"","");
                            //刪掉[
                            String open3 = open2.replace("[","");
                            //刪掉]
                            String open4 = open3.replace("]","");
                            opening=open4.split(",");
                            LinearLayout ll5=new LinearLayout(chooseoption.this);
                            ll5.setOrientation(LinearLayout.VERTICAL);
                            ll5.setMinimumWidth(1000);
                            ll5.setMinimumHeight(140);
                            ll5.setPadding(450,10,0,0);
                            TextView time = new TextView(chooseoption.this);
                            time.setWidth(200);time.setHeight(140);
                            time.setPadding(20,0,0,0);
                            time.setText("開診時段:");
                            time.setTextSize(17);
                            Spinner choosetime=new Spinner(chooseoption.this);
                            choosetime.setMinimumWidth(20);choosetime.setMinimumHeight(70);
                            choosetime.setPadding(250,0,0,0);
                            final ArrayAdapter<String> timeList = new ArrayAdapter<>(chooseoption.this,android.R.layout.simple_spinner_dropdown_item,opening);
                            choosetime.setAdapter(timeList);
                            ll5.addView(time);ll5.addView(choosetime);ll3.addView(ll5);
                            LinearLayout ll6=new LinearLayout(chooseoption.this);
                            ll6.setOrientation(LinearLayout.VERTICAL);
                           ll6.setMinimumWidth(260); ll6.setMinimumHeight(100);
                           ll6.setPadding(510,0,0,0);
                            Button getoption=new Button(chooseoption.this);
                            getoption.setMinimumWidth(230);getoption.setMinimumHeight(100);
                            getoption.setBackgroundColor(Color.RED);
                            getoption.setPadding(10,0,0,0);
                            getoption.setText("預約");
                            getoption.setTextSize(16);
                            getoption.setTextColor(Color.WHITE);
                            ll6.addView(getoption);
                            ll3.addView(ll6);ll1.addView(ll3);ll.addView(ll1);
                           /* LinearLayout ll4=new LinearLayout(chooseoption.this);
                            ll4.setOrientation(LinearLayout.HORIZONTAL);
                            ll4.setMinimumHeight(100);
                            TextView timetext = new TextView(chooseoption.this);
                            timetext.setWidth(300);timetext.setHeight(100);
                            timetext.setPadding(450,0,0,0);
                            timetext.setText("開診時段:");
                            Spinner choosetime=new Spinner(chooseoption.this);
                            choosetime.setPadding(30,0,0,0);
                            final ArrayAdapter<String> timeList = new ArrayAdapter<>(chooseoption.this,android.R.layout.simple_spinner_dropdown_item,opening);
                            choosetime.setAdapter(timeList);
                            ll4.addView(timetext);ll4.addView(choosetime);ll2.addView(ll4);
                            LinearLayout ll5=new LinearLayout(chooseoption.this);
                            ll5.setOrientation(LinearLayout.HORIZONTAL);
                            ll5.setMinimumHeight(100);
                            Button getoption=new Button(chooseoption.this);
                            getoption.setMinimumWidth(150);getoption.setMinimumHeight(100);
                            getoption.setBackgroundColor(Color.WHITE);
                            getoption.setPadding(470,0,0,0);
                            getoption.setText("預約");
                            ll5.addView(getoption);
                            ll2.addView(ll5);*/


                            //LinearLayout ll3=new LinearLayout(chooseoption.this);
                            //ll3.setOrientation(LinearLayout.HORIZONTAL);
                           /* TextView timetext = new TextView(chooseoption.this);
                            timetext.setWidth(1200);timetext.setHeight(100);
                            timetext.setPadding(500,30,0,0);
                            timetext.setText("開診時段:");
                            ll3.addView(timetext);
                            ll.addView(ll3);*/
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
