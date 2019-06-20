package first.com;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

public class searchres extends AppCompatActivity{
    private Button btn1;
    private EditText txt1;
    static ArrayList<String> s_data = new ArrayList<>();
    private LinearLayout linearLayout;//创建对象
    private RadioGroup rg;
    static String person;

    private void loadResData() {
        String urlString = "https://api.airtable.com/v0/appgPqAWrw2xTWKdx/reservation?api_key=key3BJkoA85rsN2mC";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(urlString, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                //Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_LONG).show();
                Log.d("Hot Text:", response.toString());
                try {
                    JSONArray Array = response.getJSONArray("records");
                    for (int i = 0; i < Array.length(); i++) {
                        JSONObject userdata = Array.getJSONObject(i);
                        JSONObject fields = userdata.getJSONObject("fields");

                        JSONArray searchDate = fields.getJSONArray("r_date");
                        String itemA = (String) searchDate.get(0);
                        s_data.add(itemA);

                        JSONArray searchTime= fields.getJSONArray("time");
                        String itemB = (String) searchTime.get(0);
                        s_data.add(itemB);

                        JSONArray searchPatient = fields.getJSONArray("patient");
                        String itemC = (String) searchPatient.get(0);
                        s_data.add(itemC);

                        //String itemD = fields.getString("r_number");
                        //s_data.add(itemD);

                        JSONArray searchDoctor = fields.getJSONArray("doctor");
                        String itemE = (String) searchDoctor.get(0);
                        s_data.add(itemE);

                        JSONArray searchDivition = fields.getJSONArray("divition");
                        String itemF = (String) searchDivition.get(0);
                        s_data.add(itemF);

                        JSONArray searchLocation = fields.getJSONArray("location");
                        String itemG = (String) searchLocation.get(0);
                        s_data.add(itemG);
                    }

                    //loadDivision();
                    printResData(person);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject error) {
                //Toast.makeText(getApplicationContext(), "Error: " + statusCode + " " + e.getMessage(), Toast.LENGTH_LONG).show();
                // Log error message
                Log.e("Hot Text:", statusCode + " " + e.getMessage());
            }
        });
    }

    /*private void loadDivision(){
        String urlString = "https://api.airtable.com/v0/appgPqAWrw2xTWKdx/List of divisions?view=Grid%20view&api_key=keycNoUjTn05xspUe";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(urlString, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("Hot Text:", response.toString());
                try {
                    JSONArray dArray = response.getJSONArray("records");
                    for (int i = 0; i < dArray.length(); i++) {
                        JSONObject userdata = dArray.getJSONObject(i);
                        JSONObject fields = userdata.getJSONObject("fields");
                        final String item = fields.getString("Divisions_name");
                        String dname=item.replace("\"","");
                        division.add(dname);
                        //Toast.makeText(getApplicationContext(), "123456789!", Toast.LENGTH_LONG).show();
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

    private void printResData(String person){
        /*division.add("胸腔內科");
        division.add("腎臟內科");
        division.add("眼科");
        division.add("骨科");
        division.add("皮膚科");*/
        ArrayList<Integer> list=new ArrayList<>();
        for(int i=2;i<s_data.size();i+=6){
            if(s_data.get(i).equals(person))
                list.add(i);
        }

        if(list.size()>0){
            for(int i=0;i<list.size();i++){
                Resources res=getResources();
                LinearLayout ll0=new LinearLayout(searchres.this);
                ll0.setOrientation(LinearLayout.VERTICAL);
                //ll0.setBackground(res.getDrawable(R.drawable.rectangle_blue);
                TextView tt0=new TextView(searchres.this);

                tt0.setText(s_data.get(list.get(i)-2));
                tt0.setTextColor(Color.WHITE);
                tt0.setTextSize(32);
                tt0.setGravity(Gravity.CENTER);
                ll0.addView(tt0);

                LinearLayout ll=findViewById(R.id.llview);
                LinearLayout ll1=new LinearLayout(searchres.this);
                ll1.setOrientation(LinearLayout.VERTICAL);

                ll1.setBackgroundDrawable(res.getDrawable(R.drawable.rectangle));
                ll1.setMinimumHeight(200);

                LinearLayout ll2=new LinearLayout(searchres.this);
                ll2.setOrientation(LinearLayout.HORIZONTAL);
                ll2.setMinimumHeight(40);

                LinearLayout ll3=new LinearLayout(searchres.this);
                ll3.setOrientation(LinearLayout.VERTICAL);
                ll3.setMinimumHeight(40);
                ll3.setMinimumWidth(200);
                TextView tt1=new TextView(searchres.this);
                tt1.setText("看診時段 : ");
                tt1.setTextColor(Color.BLACK);
                tt1.setTextSize(28);
                ll3.addView(tt1);

                LinearLayout ll4=new LinearLayout(searchres.this);
                ll4.setOrientation(LinearLayout.VERTICAL);
                ll4.setMinimumHeight(40);
                ll4.setMinimumWidth(200);
                TextView tt2=new TextView(searchres.this);
                String time=s_data.get(list.get(i)-1);
                String time1=time.substring(3,8);
                switch (time1){
                    case "9:00~":
                        tt2.setText("上午診");
                        break;
                    case "13:30":
                        tt2.setText("下午診");
                        break;
                    case "18:00":
                        tt2.setText("夜間診");
                        break;
                }
                //tt2.setText(r_data.get(list.get(i)-1));
                tt2.setTextColor(Color.GREEN);
                tt2.setTextSize(28);
                ll4.addView(tt2);

                ll2.addView(ll3);
                ll2.addView(ll4);
                ll1.addView(ll2);

                LinearLayout ll5=new LinearLayout(searchres.this);
                ll5.setOrientation(LinearLayout.HORIZONTAL);
                ll5.setMinimumHeight(40);

                LinearLayout ll6=new LinearLayout(searchres.this);
                ll6.setOrientation(LinearLayout.VERTICAL);
                ll6.setMinimumHeight(40);
                ll6.setMinimumWidth(200);
                TextView tt3=new TextView(searchres.this);
                tt3.setText("看診病人 : ");
                tt3.setTextColor(Color.BLACK);
                tt3.setTextSize(28);
                ll6.addView(tt3);

                LinearLayout ll7=new LinearLayout(searchres.this);
                ll7.setOrientation(LinearLayout.VERTICAL);
                ll7.setMinimumHeight(40);
                ll7.setMinimumWidth(200);
                TextView tt4=new TextView(searchres.this);
                tt4.setText(s_data.get(list.get(i)));
                tt4.setTextColor(Color.GREEN);
                tt4.setTextSize(28);
                ll7.addView(tt4);

                ll5.addView(ll6);
                ll5.addView(ll7);
                ll1.addView(ll5);

                LinearLayout ll8=new LinearLayout(searchres.this);
                ll8.setOrientation(LinearLayout.HORIZONTAL);
                ll8.setMinimumHeight(40);

                LinearLayout ll9=new LinearLayout(searchres.this);
                ll9.setOrientation(LinearLayout.VERTICAL);
                ll9.setMinimumHeight(40);
                ll9.setMinimumWidth(200);
                TextView tt5=new TextView(searchres.this);
                tt5.setText("掛號號碼 : ");
                tt5.setTextColor(Color.BLACK);
                tt5.setTextSize(28);
                ll9.addView(tt5);

                LinearLayout ll10=new LinearLayout(searchres.this);
                ll10.setOrientation(LinearLayout.VERTICAL);
                ll10.setMinimumHeight(40);
                ll10.setMinimumWidth(200);
                TextView tt6=new TextView(searchres.this);
                tt6.setText(s_data.get(list.get(i)+1));
                tt6.setTextColor(Color.GREEN);
                tt6.setTextSize(28);
                ll10.addView(tt6);

                ll8.addView(ll9);
                ll8.addView(ll10);
                ll1.addView(ll8);

                LinearLayout ll11=new LinearLayout(searchres.this);
                ll11.setOrientation(LinearLayout.HORIZONTAL);
                ll11.setMinimumHeight(40);

                LinearLayout ll12=new LinearLayout(searchres.this);
                ll12.setOrientation(LinearLayout.VERTICAL);
                ll12.setMinimumHeight(40);
                ll12.setMinimumWidth(200);
                TextView tt7=new TextView(searchres.this);
                tt7.setText("看診醫師 : ");
                tt7.setTextColor(Color.BLACK);
                tt7.setTextSize(28);
                ll12.addView(tt7);

                LinearLayout ll13=new LinearLayout(searchres.this);
                ll13.setOrientation(LinearLayout.VERTICAL);
                ll13.setMinimumHeight(40);
                ll13.setMinimumWidth(200);
                TextView tt8=new TextView(searchres.this);
                tt8.setText(s_data.get(list.get(i)+2));
                tt8.setTextColor(Color.GREEN);
                tt8.setTextSize(28);
                ll13.addView(tt8);

                ll11.addView(ll12);
                ll11.addView(ll13);
                ll1.addView(ll11);

                LinearLayout ll14=new LinearLayout(searchres.this);
                ll14.setOrientation(LinearLayout.HORIZONTAL);
                ll14.setMinimumHeight(40);

                LinearLayout ll15=new LinearLayout(searchres.this);
                ll15.setOrientation(LinearLayout.VERTICAL);
                ll15.setMinimumHeight(40);
                ll15.setMinimumWidth(200);
                TextView tt9=new TextView(searchres.this);
                tt9.setText("門診位置 : ");
                tt9.setTextColor(Color.BLACK);
                tt9.setTextSize(28);
                ll15.addView(tt9);

                LinearLayout ll16=new LinearLayout(searchres.this);
                ll16.setOrientation(LinearLayout.VERTICAL);
                ll16.setMinimumHeight(40);
                ll16.setMinimumWidth(200);
                TextView tt10=new TextView(searchres.this);
                tt10.setText(s_data.get(list.get(i)+3));
                tt10.setTextColor(Color.GREEN);
                tt10.setTextSize(28);
                ll16.addView(tt10);

                ll14.addView(ll15);
                ll14.addView(ll16);
                ll1.addView(ll14);

                LinearLayout ll17=new LinearLayout(searchres.this);
                ll17.setOrientation(LinearLayout.HORIZONTAL);
                ll17.setMinimumHeight(40);

                LinearLayout ll18=new LinearLayout(searchres.this);
                ll18.setOrientation(LinearLayout.VERTICAL);
                ll18.setMinimumHeight(40);
                ll18.setMinimumWidth(200);
                TextView tt11=new TextView(searchres.this);
                tt11.setText("目前門診號碼 : ");
                tt11.setTextColor(Color.BLACK);
                tt11.setTextSize(28);
                ll18.addView(tt11);

                LinearLayout ll19=new LinearLayout(searchres.this);
                ll19.setOrientation(LinearLayout.VERTICAL);
                ll19.setMinimumHeight(40);
                ll19.setMinimumWidth(200);
                TextView tt12=new TextView(searchres.this);
                int number=(int)(Math.random()*50+1);
                tt12.setText(number+"");
                tt12.setTextColor(Color.BLUE);
                tt12.setTextSize(30);
                ll19.addView(tt12);

                ll17.addView(ll18);
                ll17.addView(ll19);
                ll1.addView(ll17);

                ll.addView(ll0);
                ll.addView(ll1);
            }
        }
        else
            Toast.makeText(searchres.this, "無掛號紀錄!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_test);
        //s_data.clear();
        //person="周捷倫";
        //loadResData();
        //loadDivision();
        //printData(person);

        Resources res=getResources();
        LinearLayout ll0=new LinearLayout(searchres.this);
        ll0.setOrientation(LinearLayout.VERTICAL);
        //ll0.setBackground(res.getDrawable(R.drawable.rectangle_blue);
        TextView tt0=new TextView(searchres.this);

        tt0.setText("reservationimformation");
        tt0.setTextColor(Color.WHITE);
        tt0.setTextSize(32);
        tt0.setGravity(Gravity.CENTER);
        ll0.addView(tt0);

        LinearLayout ll=findViewById(R.id.llview);
        ll.addView(ll0);

        /*btn1 = (Button) findViewById(R.id.searchbtn);  //取得Button
        txt1 = (EditText) findViewById(R.id.searchbar); //取得EditText

        RadioGroup rg = (RadioGroup) findViewById(R.id.searchoption);
        // 为 RadioGroup 设置一个监听器 setOnCheckedChanged()
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton btn = (RadioButton) findViewById(checkedId);
                String option = (String) btn.getText();
                //Toast.makeText(getApplicationContext(), option, Toast.LENGTH_LONG).show();

                switch (option) {
                    case "科別":
                        btn1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //取得EditText的輸入內容
                                String content = txt1.getText().toString();

                                linearLayout = (LinearLayout) findViewById(R.id.llview);//绑定控件
                                linearLayout.removeAllViews();//清空布局

                                if(!content.isEmpty()){
                                    ArrayList<Integer> in_d=new ArrayList<>();
                                    //int d=r_data.indexOf(content);
                                    for(int i=0;i<r_data.size();i+=6){
                                        if(r_data.get(i).equals(content))
                                            in_d.add(i);
                                    }

                                    if(in_d.size()>0){
                                        for(int i=0;i<in_d.size();i++){
                                            int index=in_d.get(i);
                                            Resources res=getResources();
                                            if(r_data.get(index+2).equals(person)){
                                                LinearLayout ll0=new LinearLayout(viewclinicnumber.this);
                                                ll0.setOrientation(LinearLayout.VERTICAL);
                                                ll0.setBackground(res.getDrawable(R.drawable.rectangle_blue));
                                                TextView tt0=new TextView(viewclinicnumber.this);
                                                tt0.setText(content);
                                                tt0.setTextColor(Color.WHITE);
                                                tt0.setTextSize(32);
                                                tt0.setGravity(Gravity.CENTER);
                                                ll0.addView(tt0);

                                                //LinearLayout ll=findViewById(R.id.llview);
                                                LinearLayout ll1=new LinearLayout(viewclinicnumber.this);
                                                ll1.setOrientation(LinearLayout.VERTICAL);

                                                ll1.setBackgroundDrawable(res.getDrawable(R.drawable.rectangle));
                                                ll1.setMinimumHeight(200);

                                                LinearLayout ll2=new LinearLayout(viewclinicnumber.this);
                                                ll2.setOrientation(LinearLayout.HORIZONTAL);
                                                ll2.setMinimumHeight(40);

                                                LinearLayout ll3=new LinearLayout(viewclinicnumber.this);
                                                ll3.setOrientation(LinearLayout.VERTICAL);
                                                ll3.setMinimumHeight(40);
                                                ll3.setMinimumWidth(200);
                                                TextView tt1=new TextView(viewclinicnumber.this);
                                                tt1.setText("看診時段 : ");
                                                tt1.setTextColor(Color.BLACK);
                                                tt1.setTextSize(28);
                                                ll3.addView(tt1);

                                                LinearLayout ll4=new LinearLayout(viewclinicnumber.this);
                                                ll4.setOrientation(LinearLayout.VERTICAL);
                                                ll4.setMinimumHeight(40);
                                                ll4.setMinimumWidth(200);
                                                TextView tt2=new TextView(viewclinicnumber.this);
                                                String time=r_data.get(index+1);
                                                String time1=time.substring(3,8);
                                                switch (time1){
                                                    case "9:00~":
                                                        tt2.setText("上午診");
                                                        break;
                                                    case "13:30":
                                                        tt2.setText("下午診");
                                                        break;
                                                    case "18:00":
                                                        tt2.setText("夜間診");
                                                        break;
                                                }
                                                //tt2.setText(r_data.get(index+1));
                                                tt2.setTextColor(Color.GREEN);
                                                tt2.setTextSize(28);
                                                ll4.addView(tt2);

                                                ll2.addView(ll3);
                                                ll2.addView(ll4);
                                                ll1.addView(ll2);

                                                LinearLayout ll5=new LinearLayout(viewclinicnumber.this);
                                                ll5.setOrientation(LinearLayout.HORIZONTAL);
                                                ll5.setMinimumHeight(40);

                                                LinearLayout ll6=new LinearLayout(viewclinicnumber.this);
                                                ll6.setOrientation(LinearLayout.VERTICAL);
                                                ll6.setMinimumHeight(40);
                                                ll6.setMinimumWidth(200);
                                                TextView tt3=new TextView(viewclinicnumber.this);
                                                tt3.setText("看診病人 : ");
                                                tt3.setTextColor(Color.BLACK);
                                                tt3.setTextSize(28);
                                                ll6.addView(tt3);

                                                LinearLayout ll7=new LinearLayout(viewclinicnumber.this);
                                                ll7.setOrientation(LinearLayout.VERTICAL);
                                                ll7.setMinimumHeight(40);
                                                ll7.setMinimumWidth(200);
                                                TextView tt4=new TextView(viewclinicnumber.this);
                                                tt4.setText(r_data.get(index+2));
                                                tt4.setTextColor(Color.GREEN);
                                                tt4.setTextSize(28);
                                                ll7.addView(tt4);

                                                ll5.addView(ll6);
                                                ll5.addView(ll7);
                                                ll1.addView(ll5);

                                                LinearLayout ll8=new LinearLayout(viewclinicnumber.this);
                                                ll8.setOrientation(LinearLayout.HORIZONTAL);
                                                ll8.setMinimumHeight(40);

                                                LinearLayout ll9=new LinearLayout(viewclinicnumber.this);
                                                ll9.setOrientation(LinearLayout.VERTICAL);
                                                ll9.setMinimumHeight(40);
                                                ll9.setMinimumWidth(200);
                                                TextView tt5=new TextView(viewclinicnumber.this);
                                                tt5.setText("掛號號碼 : ");
                                                tt5.setTextColor(Color.BLACK);
                                                tt5.setTextSize(28);
                                                ll9.addView(tt5);

                                                LinearLayout ll10=new LinearLayout(viewclinicnumber.this);
                                                ll10.setOrientation(LinearLayout.VERTICAL);
                                                ll10.setMinimumHeight(40);
                                                ll10.setMinimumWidth(200);
                                                TextView tt6=new TextView(viewclinicnumber.this);
                                                tt6.setText(r_data.get(index+3));
                                                tt6.setTextColor(Color.GREEN);
                                                tt6.setTextSize(28);
                                                ll10.addView(tt6);

                                                ll8.addView(ll9);
                                                ll8.addView(ll10);
                                                ll1.addView(ll8);

                                                LinearLayout ll11=new LinearLayout(viewclinicnumber.this);
                                                ll11.setOrientation(LinearLayout.HORIZONTAL);
                                                ll11.setMinimumHeight(40);

                                                LinearLayout ll12=new LinearLayout(viewclinicnumber.this);
                                                ll12.setOrientation(LinearLayout.VERTICAL);
                                                ll12.setMinimumHeight(40);
                                                ll12.setMinimumWidth(200);
                                                TextView tt7=new TextView(viewclinicnumber.this);
                                                tt7.setText("看診醫師 : ");
                                                tt7.setTextColor(Color.BLACK);
                                                tt7.setTextSize(28);
                                                ll12.addView(tt7);

                                                LinearLayout ll13=new LinearLayout(viewclinicnumber.this);
                                                ll13.setOrientation(LinearLayout.VERTICAL);
                                                ll13.setMinimumHeight(40);
                                                ll13.setMinimumWidth(200);
                                                TextView tt8=new TextView(viewclinicnumber.this);
                                                tt8.setText(r_data.get(index+4));
                                                tt8.setTextColor(Color.GREEN);
                                                tt8.setTextSize(28);
                                                ll13.addView(tt8);

                                                ll11.addView(ll12);
                                                ll11.addView(ll13);
                                                ll1.addView(ll11);

                                                LinearLayout ll14=new LinearLayout(viewclinicnumber.this);
                                                ll14.setOrientation(LinearLayout.HORIZONTAL);
                                                ll14.setMinimumHeight(40);

                                                LinearLayout ll15=new LinearLayout(viewclinicnumber.this);
                                                ll15.setOrientation(LinearLayout.VERTICAL);
                                                ll15.setMinimumHeight(40);
                                                ll15.setMinimumWidth(200);
                                                TextView tt9=new TextView(viewclinicnumber.this);
                                                tt9.setText("門診位置 : ");
                                                tt9.setTextColor(Color.BLACK);
                                                tt9.setTextSize(28);
                                                ll15.addView(tt9);

                                                LinearLayout ll16=new LinearLayout(viewclinicnumber.this);
                                                ll16.setOrientation(LinearLayout.VERTICAL);
                                                ll16.setMinimumHeight(40);
                                                ll16.setMinimumWidth(200);
                                                TextView tt10=new TextView(viewclinicnumber.this);
                                                tt10.setText(r_data.get(index+5));
                                                tt10.setTextColor(Color.GREEN);
                                                tt10.setTextSize(28);
                                                ll16.addView(tt10);

                                                ll14.addView(ll15);
                                                ll14.addView(ll16);
                                                ll1.addView(ll14);

                                                LinearLayout ll17=new LinearLayout(viewclinicnumber.this);
                                                ll17.setOrientation(LinearLayout.HORIZONTAL);
                                                ll17.setMinimumHeight(40);

                                                LinearLayout ll18=new LinearLayout(viewclinicnumber.this);
                                                ll18.setOrientation(LinearLayout.VERTICAL);
                                                ll18.setMinimumHeight(40);
                                                ll18.setMinimumWidth(200);
                                                TextView tt11=new TextView(viewclinicnumber.this);
                                                tt11.setText("目前門診號碼 : ");
                                                tt11.setTextColor(Color.BLACK);
                                                tt11.setTextSize(28);
                                                ll18.addView(tt11);

                                                LinearLayout ll19=new LinearLayout(viewclinicnumber.this);
                                                ll19.setOrientation(LinearLayout.VERTICAL);
                                                ll19.setMinimumHeight(40);
                                                ll19.setMinimumWidth(200);
                                                TextView tt12=new TextView(viewclinicnumber.this);
                                                int number=(int)(Math.random()*50+1);
                                                tt12.setText(number+"");
                                                tt12.setTextColor(Color.BLUE);
                                                tt12.setTextSize(30);
                                                ll19.addView(tt12);

                                                ll17.addView(ll18);
                                                ll17.addView(ll19);
                                                ll1.addView(ll17);

                                                linearLayout.addView(ll0);
                                                linearLayout.addView(ll1);
                                            }
                                        }

                                    }
                                    else
                                        Toast.makeText(viewclinicnumber.this, "查無此筆資料!", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(viewclinicnumber.this, "請輸入欲查詢目標", Toast.LENGTH_SHORT).show();
                                    //顯示在Debug Console
                                    Log.d("debug", "button click");
                                }
                            }
                        });
                        break;
                  /*case "病人姓名":
                        btn1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //取得EditText的輸入內容
                                String content = txt1.getText().toString();
                                linearLayout = (LinearLayout) findViewById(R.id.llview);//绑定控件
                                linearLayout.removeAllViews();//清空布局
                                if(!content.isEmpty()){
                                    ArrayList<Integer> in_p=new ArrayList<>();
                                    for(int i=0;i<r_data.size();i++){
                                        if(r_data.get(i).equals(content))
                                            in_p.add(i);
                                    }
                                    if(in_p.size()>0){
                                        for(int i=0;i<in_p.size();i++){
                                            int index=in_p.get(i);
                                            int n=index-2;
                                            Resources res=getResources();
                                            LinearLayout ll0=new LinearLayout(viewclinicnumber.this);
                                            ll0.setOrientation(LinearLayout.VERTICAL);
                                            ll0.setBackground(res.getDrawable(R.drawable.rectangle_blue));
                                            TextView tt0=new TextView(viewclinicnumber.this);
                                            int num=Integer.parseInt(r_data.get(n))-1;
                                            tt0.setText(division.get(num));
                                            tt0.setTextColor(Color.WHITE);
                                            tt0.setTextSize(32);
                                            tt0.setGravity(Gravity.CENTER);
                                            ll0.addView(tt0);
                                            //LinearLayout ll=findViewById(R.id.llview);
                                            LinearLayout ll1=new LinearLayout(viewclinicnumber.this);
                                            ll1.setOrientation(LinearLayout.VERTICAL);
                                            ll1.setBackgroundDrawable(res.getDrawable(R.drawable.rectangle));
                                            ll1.setMinimumHeight(200);
                                            LinearLayout ll2=new LinearLayout(viewclinicnumber.this);
                                            ll2.setOrientation(LinearLayout.HORIZONTAL);
                                            ll2.setMinimumHeight(40);
                                            LinearLayout ll3=new LinearLayout(viewclinicnumber.this);
                                            ll3.setOrientation(LinearLayout.VERTICAL);
                                            ll3.setMinimumHeight(40);
                                            ll3.setMinimumWidth(200);
                                            TextView tt1=new TextView(viewclinicnumber.this);
                                            tt1.setText("看診時段 : ");
                                            tt1.setTextColor(Color.BLACK);
                                            tt1.setTextSize(28);
                                            ll3.addView(tt1);
                                            LinearLayout ll4=new LinearLayout(viewclinicnumber.this);
                                            ll4.setOrientation(LinearLayout.VERTICAL);
                                            ll4.setMinimumHeight(40);
                                            TextView tt2=new TextView(viewclinicnumber.this);
                                            ll4.setMinimumWidth(200);
                                            tt2.setText(r_data.get(index-1));
                                            tt2.setTextColor(Color.GREEN);
                                            tt2.setTextSize(28);
                                            ll4.addView(tt2);
                                            ll2.addView(ll3);
                                            ll2.addView(ll4);
                                            ll1.addView(ll2);
                                            LinearLayout ll5=new LinearLayout(viewclinicnumber.this);
                                            ll5.setOrientation(LinearLayout.HORIZONTAL);
                                            ll5.setMinimumHeight(40);
                                            LinearLayout ll6=new LinearLayout(viewclinicnumber.this);
                                            ll6.setOrientation(LinearLayout.VERTICAL);
                                            ll6.setMinimumHeight(40);
                                            ll6.setMinimumWidth(200);
                                            TextView tt3=new TextView(viewclinicnumber.this);
                                            tt3.setText("看診病人 : ");
                                            tt3.setTextColor(Color.BLACK);
                                            tt3.setTextSize(28);
                                            ll6.addView(tt3);
                                            LinearLayout ll7=new LinearLayout(viewclinicnumber.this);
                                            ll7.setOrientation(LinearLayout.VERTICAL);
                                            ll7.setMinimumHeight(40);
                                            ll7.setMinimumWidth(200);
                                            TextView tt4=new TextView(viewclinicnumber.this);
                                            tt4.setText(r_data.get(index));
                                            tt4.setTextColor(Color.GREEN);
                                            tt4.setTextSize(28);
                                            ll7.addView(tt4);
                                            ll5.addView(ll6);
                                            ll5.addView(ll7);
                                            ll1.addView(ll5);
                                            LinearLayout ll8=new LinearLayout(viewclinicnumber.this);
                                            ll8.setOrientation(LinearLayout.HORIZONTAL);
                                            ll8.setMinimumHeight(40);
                                            LinearLayout ll9=new LinearLayout(viewclinicnumber.this);
                                            ll9.setOrientation(LinearLayout.VERTICAL);
                                            ll9.setMinimumHeight(40);
                                            ll9.setMinimumWidth(200);
                                            TextView tt5=new TextView(viewclinicnumber.this);
                                            tt5.setText("掛號號碼 : ");
                                            tt5.setTextColor(Color.BLACK);
                                            tt5.setTextSize(28);
                                            ll9.addView(tt5);
                                            LinearLayout ll10=new LinearLayout(viewclinicnumber.this);
                                            ll10.setOrientation(LinearLayout.VERTICAL);
                                            ll10.setMinimumHeight(40);
                                            ll10.setMinimumWidth(200);
                                            TextView tt6=new TextView(viewclinicnumber.this);
                                            tt6.setText(r_data.get(index+1));
                                            tt6.setTextColor(Color.GREEN);
                                            tt6.setTextSize(28);
                                            ll10.addView(tt6);
                                            ll8.addView(ll9);
                                            ll8.addView(ll10);
                                            ll1.addView(ll8);
                                            LinearLayout ll11=new LinearLayout(viewclinicnumber.this);
                                            ll11.setOrientation(LinearLayout.HORIZONTAL);
                                            ll11.setMinimumHeight(40);
                                            LinearLayout ll12=new LinearLayout(viewclinicnumber.this);
                                            ll12.setOrientation(LinearLayout.VERTICAL);
                                            ll12.setMinimumHeight(40);
                                            ll12.setMinimumWidth(200);
                                            TextView tt7=new TextView(viewclinicnumber.this);
                                            tt7.setText("看診醫師 : ");
                                            tt7.setTextColor(Color.BLACK);
                                            tt7.setTextSize(28);
                                            ll12.addView(tt7);
                                            LinearLayout ll13=new LinearLayout(viewclinicnumber.this);
                                            ll13.setOrientation(LinearLayout.VERTICAL);
                                            ll13.setMinimumHeight(40);
                                            ll13.setMinimumWidth(200);
                                            TextView tt8=new TextView(viewclinicnumber.this);
                                            tt8.setText(r_data.get(index+2));
                                            tt8.setTextColor(Color.GREEN);
                                            tt8.setTextSize(28);
                                            ll13.addView(tt8);
                                            ll11.addView(ll12);
                                            ll11.addView(ll13);
                                            ll1.addView(ll11);
                                            LinearLayout ll14=new LinearLayout(viewclinicnumber.this);
                                            ll14.setOrientation(LinearLayout.HORIZONTAL);
                                            ll14.setMinimumHeight(40);
                                            LinearLayout ll15=new LinearLayout(viewclinicnumber.this);
                                            ll15.setOrientation(LinearLayout.VERTICAL);
                                            ll15.setMinimumHeight(40);
                                            ll15.setMinimumWidth(200);
                                            TextView tt9=new TextView(viewclinicnumber.this);
                                            tt9.setText("門診位置 : ");
                                            tt9.setTextColor(Color.BLACK);
                                            tt9.setTextSize(28);
                                            ll15.addView(tt9);
                                            LinearLayout ll16=new LinearLayout(viewclinicnumber.this);
                                            ll16.setOrientation(LinearLayout.VERTICAL);
                                            ll16.setMinimumHeight(40);
                                            ll16.setMinimumWidth(200);
                                            TextView tt10=new TextView(viewclinicnumber.this);
                                            tt10.setText(r_data.get(index+3));
                                            tt10.setTextColor(Color.GREEN);
                                            tt10.setTextSize(28);
                                            ll16.addView(tt10);
                                            ll14.addView(ll15);
                                            ll14.addView(ll16);
                                            ll1.addView(ll14);
                                            LinearLayout ll17=new LinearLayout(viewclinicnumber.this);
                                            ll17.setOrientation(LinearLayout.HORIZONTAL);
                                            ll17.setMinimumHeight(40);
                                            LinearLayout ll18=new LinearLayout(viewclinicnumber.this);
                                            ll18.setOrientation(LinearLayout.VERTICAL);
                                            ll18.setMinimumHeight(40);
                                            ll18.setMinimumWidth(200);
                                            TextView tt11=new TextView(viewclinicnumber.this);
                                            tt11.setText("目前門診號碼 : ");
                                            tt11.setTextColor(Color.BLACK);
                                            tt11.setTextSize(28);
                                            ll18.addView(tt11);
                                            LinearLayout ll19=new LinearLayout(viewclinicnumber.this);
                                            ll19.setOrientation(LinearLayout.VERTICAL);
                                            ll19.setMinimumHeight(40);
                                            ll19.setMinimumWidth(200);
                                            TextView tt12=new TextView(viewclinicnumber.this);
                                            int number=(int)(Math.random()*50+1);
                                            tt12.setText(number+"");
                                            tt12.setTextColor(Color.BLUE);
                                            tt12.setTextSize(30);
                                            ll19.addView(tt12);
                                            ll17.addView(ll18);
                                            ll17.addView(ll19);
                                            ll1.addView(ll17);
                                            linearLayout.addView(ll0);
                                            linearLayout.addView(ll1);
                                        }
                                    }
                                    else
                                        Toast.makeText(viewclinicnumber.this, "查無此筆資料!", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(viewclinicnumber.this, "請輸入欲查詢目標", Toast.LENGTH_SHORT).show();
                                    //顯示在Debug Console
                                    Log.d("debug", "button click");
                                }
                            }
                        });
                        break;
                }
            }

        });   */
    }
}