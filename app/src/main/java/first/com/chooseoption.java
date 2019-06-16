package first.com;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    Date currentd = new Date(new Date().getTime()+1*24*60*60*1000);

    //當周
    Date nextweek=new Date(new Date().getTime()+7*24*60*60*1000);
    SimpleDateFormat takeinidate= new SimpleDateFormat("yyyyMMdd");
    SimpleDateFormat takeenddate= new SimpleDateFormat("yyyyMMdd");
    String ini = takeinidate.format(currentd).substring(4);
    String end = takeenddate.format(nextweek).substring(4);
    String option1="當週("+ini+"~"+end+")";

    //第二周
    Date nextweek2=new Date(new Date().getTime()+8*24*60*60*1000);
    Date nextweek3=new Date(new Date().getTime()+14*24*60*60*1000);
    String ini2 = takeinidate.format(nextweek2).substring(4);
    String end2 = takeenddate.format(nextweek3).substring(4);
    String option2="下週("+ini2+"~"+end2+")";

    //顯示醫生姓名
    ArrayList data = new ArrayList<String>();
    ArrayList doctorname=new ArrayList<String>();
    Spinner seldoc;
    ListView timeview;
    String[] opening;
    String nameoftime=null;
    String selected_type="";
    String open="";
    String sellweek="";
    int clicknum=0;
    String docid;
    String[] timerec;
    String trec;
    ArrayList<String> docrec = new ArrayList<String>();
    ArrayList<Integer> docmax = new ArrayList<Integer>();
    String clinicrec;


    public void loadData(){
        String urlString = "https://api.airtable.com/v0/appgPqAWrw2xTWKdx/List of doctor?view=Grid%20view&api_key=keygkXy0a4GuCXh7p";
        final AsyncHttpClient client = new AsyncHttpClient();
        client.get(urlString, new JsonHttpResponseHandler() {
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        Log.d("Hot Text:", response.toString());
                        String Json = response.toString();
                try {

                    //顯示所選擇科別
                    TextView selectnum = (TextView)findViewById(R.id.selectnum);
                    Bundle bundle = getIntent().getExtras();
                    selected_type = bundle.getString("name");
                    selectnum.setText(selected_type);
                    //接所選order

                    JSONArray Array = response.getJSONArray("records");

                    for (int i = 0; i < Array.length(); i++) {
                        JSONObject userdata = Array.getJSONObject(i);
                        JSONObject fields = userdata.getJSONObject("fields");
                        String id = userdata.getString("id");
                        String name = fields.getString("Doctor_name");
                        int maximum=fields.getInt("Maxnum_of_people");
                        String name_of_div=fields.getString("Name_of_div");
                        //刪掉" "
                        String open2 = name_of_div.replace("\"", "");
                        //刪掉[
                        String open3 = open2.replace("[", "");
                        //刪掉]
                        String open4 = open3.replace("]", "");


                        //列出所選擇科別的醫生
                        if(selected_type.equals(open4)) {
                            docid = id;
                            docrec.add(docid);
                            doctorname.add(name);
                            docmax.add(maximum);

                            LinearLayout ll = findViewById(R.id.testll);
                            seldoc=findViewById(R.id.select_doc);
                            final ArrayAdapter<String> nameList = new ArrayAdapter<>(chooseoption.this,android.R.layout.simple_spinner_dropdown_item,doctorname);
                            seldoc.setAdapter(nameList);
                            seldoc.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
                                @Override
                                public void onItemSelected(AdapterView parent, View v, int position, long id) {
                                    String n1=doctorname.get(position).toString();
                                    String docrec2 = docrec.get(position);

                                    int doc_max=docmax.get(position);
                                    timeData(n1,docrec2,doc_max);


                                    // parent = 事件發生的母體 spinner_items
                                    // position = 被選擇的項目index = parent.getSelectedItemPosition()
                                    // id = row id，通常給資料庫使用
                                }
                                @Override
                                public void onNothingSelected(AdapterView parent) {}
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
    public void timeData(final String getname, final  String docrec, final int doc_max){
        String urlString = "https://api.airtable.com/v0/appgPqAWrw2xTWKdx/List of doctor?view=Grid%20view&api_key=keygkXy0a4GuCXh7p";
        final AsyncHttpClient client = new AsyncHttpClient();
        client.get(urlString, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("Hot Text:", response.toString());
                String Json = response.toString();
                try {
                    JSONArray Array = response.getJSONArray("records");
                    for(int index=0;index<Array.length();index++){
                        JSONObject userdata = Array.getJSONObject(index);
                        JSONObject fields = userdata.getJSONObject("fields");
                        String name = fields.getString("Doctor_name");
                        if(getname.equals(name)){
                            trec=fields.getString("Number_of_time");
                            //刪掉" "
                            String trec2 =trec.replace("\"", "");
                            //刪掉[
                            String trec3 = trec2.replace("[", "");
                            //刪掉]
                            String trec4 = trec3.replace("]", "");
                            timerec=trec4.split(",");
                            nameoftime = fields.getString("Name_of_time");
                            final String clinicnum1=fields.getString("Name_of_clinic");
                            //刪掉" "
                            String clinic =clinicnum1.replace("\"", "");
                            //刪掉[
                            String clinic1 = clinic.replace("[", "");
                            //刪掉]
                            final String clinicnum= clinic1.replace("]", "");
                            clinicrec=fields.getString("Clinic_number");
                            //刪掉" "
                            String clinicrec1 =clinicrec.replace("\"", "");
                            //刪掉[
                            String clinicrec2 = clinicrec1.replace("[", "");
                            //刪掉]
                            final String clinicrec3= clinicrec2.replace("]", "");
                            //clirec=clinicrec3.split(";");
                            open = nameoftime;
                            //刪掉" "
                            String open2 = open.replace("\"", "");
                            //刪掉[
                            String open3 = open2.replace("[", "");
                            //刪掉]
                            String open4 = open3.replace("]", "");
                            opening = open4.split(",");
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(chooseoption.this, android.R.layout.simple_list_item_1, opening);
                            timeview = (ListView) findViewById(R.id.timelist);
                            timeview.setAdapter(adapter);
                            timeview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView arg0, View arg1, int position, long arg3) {
                                    Intent intent = new Intent();
                                    intent.setClass(chooseoption.this, reservationimformation.class);
                                    Bundle bundle = new Bundle();
                                    String decided = opening[position];
                                    String docname = getname;
                                    bundle.putString("name", docname);
                                    bundle.putString("type",selected_type);
                                    bundle.putString("week",sellweek);
                                    bundle.putString("clinic",clinicnum);
                                    bundle.putString("time", decided);
                                    bundle.putString("timerec",timerec[position]);
                                    bundle.putString("docid",docrec);
                                    bundle.putInt("limit",doc_max);
                                    bundle.putString("clirec",clinicrec3);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }
                            });
                            break;
                        }
                    }



                } catch (JSONException e) {

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
                sellweek=dateList.getItem(position);
                Toast.makeText(chooseoption.this,"您選擇的月份是："+dateList.getItem(position),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        loadData();
    }
        }
