package first.com;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class reservationimformation extends AppCompatActivity {
    MyAPIServiceres myAPIService;
    ArrayList<resrecords> array = new ArrayList<>();
    String getId="";
    String getdocId="";
    String getNUM="";
    String gettimeid="";
      public void loadData(){
        String urlString = "https://api.airtable.com/v0/appgPqAWrw2xTWKdx/User?view=Grid%20view&api_key=keygkXy0a4GuCXh7p";
        final AsyncHttpClient client = new AsyncHttpClient();
        client.get(urlString, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("Hot Text:", response.toString());
                //ListView kindview =(ListView)findViewById(R.id.kindview);
                //TextView tview =(TextView)findViewById(R.id.tview);
                String Json = response.toString();
                try {
                    JSONArray Array = response.getJSONArray("records");

                    for (int i = 0; i < Array.length(); i++) {
                        JSONObject userdata = Array.getJSONObject(i);
                        JSONObject fields = userdata.getJSONObject("fields");
                        getId = fields.getString("ID");
                        String Name = "K265487159";
                        if (Name.equals(getId)) {
                            //Toast.makeText(getApplicationContext(),
                                    //getId ,Toast.LENGTH_LONG).show();
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





    public void loadData2(final String name){
        String urlString = "https://api.airtable.com/v0/appgPqAWrw2xTWKdx/List of doctor?view=Grid%20view&api_key=keygkXy0a4GuCXh7p";
        final AsyncHttpClient client = new AsyncHttpClient();
        client.get(urlString, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("Hot Text:", response.toString());
                //ListView kindview =(ListView)findViewById(R.id.kindview);
                //TextView tview =(TextView)findViewById(R.id.tview);
                String Json = response.toString();
                try {
                    JSONArray Array = response.getJSONArray("records");

                    for (int i = 0; i < Array.length(); i++) {
                        JSONObject userdata = Array.getJSONObject(i);
                        JSONObject fields = userdata.getJSONObject("fields");
                        String docn=fields.getString("Doctor_name");
                        if (name.equals(docn)){
                            getdocId = fields.getString("Doctor_number");
                            //Toast.makeText(getApplicationContext(),
                                    //getdocId,Toast.LENGTH_LONG).show();
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






    public void loadData3(final String clinic){
        String urlString = "https://api.airtable.com/v0/appgPqAWrw2xTWKdx/Clinic?view=Grid%20view&api_key=keygkXy0a4GuCXh7p";
        final AsyncHttpClient client = new AsyncHttpClient();
        client.get(urlString, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("Hot Text:", response.toString());
                //ListView kindview =(ListView)findViewById(R.id.kindview);
                //TextView tview =(TextView)findViewById(R.id.tview);
                String Json = response.toString();
                try {
                    JSONArray Array = response.getJSONArray("records");

                    for (int i = 0; i < Array.length(); i++) {
                        JSONObject userdata = Array.getJSONObject(i);
                        JSONObject fields = userdata.getJSONObject("fields");
                        getNUM=fields.getString("Clinic_number");
                        String getloc= fields.getString("Location_of_clinic");
                        if (clinic.equals(getloc)) {
                           // Toast.makeText(getApplicationContext(),
                                   // getNUM ,Toast.LENGTH_LONG).show();
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




    public void loadData4(final String decided){
        String urlString = "https://api.airtable.com/v0/appgPqAWrw2xTWKdx/Time?view=Grid%20view&api_key=keygkXy0a4GuCXh7p";
        final AsyncHttpClient client = new AsyncHttpClient();
        client.get(urlString, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("Hot Text:", response.toString());
                //ListView kindview =(ListView)findViewById(R.id.kindview);
                //TextView tview =(TextView)findViewById(R.id.tview);
                String Json = response.toString();
                try {
                    JSONArray Array = response.getJSONArray("records");

                    for (int i = 0; i < Array.length(); i++) {
                        JSONObject userdata = Array.getJSONObject(i);
                        JSONObject fields = userdata.getJSONObject("fields");
                        String getTime = fields.getString("Name_of_time");
                        gettimeid=fields.getString("Number_of_time");
                        if (decided.equals(getTime)) {
                            //Toast.makeText(getApplicationContext(),
                                   // gettimeid ,Toast.LENGTH_LONG).show();
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservationimformation);

        LinearLayout infor = findViewById(R.id.printinfor);
        Bundle bundle = getIntent().getExtras();
        String docname = bundle.getString("name");
        String Select_type=bundle.getString("type");
        final String clinic=bundle.getString("clinic");
        String decidetime=bundle.getString("time");
        TextView printname=new TextView(this);
        printname.setWidth(600);printname.setHeight(150);
        printname.setPadding(5,5,0,0);
        printname.setText(" 醫生姓名:"+docname);
        printname.setTextSize(24);
        infor.addView(printname);
        TextView printdiv=new TextView(this);
        printdiv.setWidth(500);printname.setHeight(150);
        printdiv.setPadding(5,30,0,0);
        printdiv.setText(" 所屬科別:"+Select_type);
        printdiv.setTextSize(24);
        infor.addView(printdiv);
        TextView printtime=new TextView(this);
        printtime.setWidth(500);printname.setHeight(150);
        printtime.setPadding(5,30,0,0);
        printtime.setText(" 看診時間:"+decidetime);
        printtime.setTextSize(24);
        infor.addView(printtime);
        TextView printloc=new TextView(this);
        printloc.setWidth(500);printname.setHeight(100);
        printloc.setPadding(5,30,0,0);
        printloc.setText("   診間:"+clinic);
        printloc.setTextSize(24);
        infor.addView(printloc);
        LinearLayout infor1= findViewById(R.id.printdate);
        TextView gotodoc=new TextView(this);
        gotodoc.setWidth(1000);gotodoc.setHeight(280);
        gotodoc.setPadding(5,100,0,0);
        gotodoc.setText("   就診日期:");
        gotodoc.setTextSize(28);
        infor1.addView(gotodoc);
        LinearLayout infor2= findViewById(R.id.printperson);
        TextView gotname=new TextView(this);
        gotname.setWidth(1000);gotname.setHeight(280);
        gotname.setPadding(5,10,0,0);
        gotname.setText("   就診者姓名:");
        gotname.setTextSize(28);
        infor2.addView(gotname);
        Spinner relative=new Spinner(this);
        relative.setMinimumWidth(200);
        relative.setMinimumHeight(80);
        relative.setPadding(170,80,0,0);
        infor2.addView(relative);




        loadData();
        loadData2(docname);
        loadData3(clinic);
        loadData4(decidetime);



        Button submit=findViewById(R.id.complete);
        submit.setOnClickListener(new  Button.OnClickListener(){
            public void onClick(View v) {
               String userid = getId;
                String  docid=  getdocId;
                String location = getNUM;
                String timesery = gettimeid;

                    try {
                        postUser(userid, docid,location,timesery);

                    } catch (Exception e) {

                        Log.e("MainActivity", e.getMessage());

                    }

            }
        });
    }
























        // final String[] name = {"廖品鈞", "廖子維", "林承鴻", "王冠文", "林育賢", "陳建甫"};
        //ArrayAdapter<String> nameList = new ArrayAdapter<>(MainActivity.this,
        //       android.R.layout.simple_spinner_dropdown_item,
        //       name);
        //spinner.setAdapter(nameList);





    public void postUser(String userid, String docid, String location, String timesery){
        myAPIService = Manager.getInstance().getAPI();
        Call<resrecords> call= myAPIService.postRecords(new patientpost(new resfields(userid, docid,location,timesery)));
        call.enqueue(new Callback<resrecords>(){

            @Override
            public void onResponse(Call<resrecords> call, Response<resrecords> response) {
                //利用Toast的靜態函式makeText來建立Toast物件
                Toast toast = Toast.makeText(reservationimformation.this,
                        "Success", Toast.LENGTH_LONG);
                //顯示Toast
                toast.show();
            }

            @Override
            public void onFailure(Call<resrecords> call, Throwable t) {
                //利用Toast的靜態函式makeText來建立Toast物件
                Toast toast = Toast.makeText(reservationimformation.this,
                        "Failed", Toast.LENGTH_LONG);
                //顯示Toast
                toast.show();
            }
        });
    }
}
