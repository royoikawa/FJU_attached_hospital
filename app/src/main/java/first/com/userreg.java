package first.com;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Header;
import retrofit2.http.QueryMap;

public class userreg extends AppCompatActivity {
    MyAPIService myAPIService;
    EditText name;
    EditText id;
    EditText bir;
    EditText phone;
    EditText passWord;
    EditText checkPass;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userreg);
        Button submit = (Button) findViewById(R.id.reg);
        submit.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = (EditText) findViewById(R.id.name);
                id = (EditText) findViewById(R.id.id);
                bir = (EditText) findViewById(R.id.bir);
                phone = (EditText) findViewById(R.id.phone);
                passWord = (EditText) findViewById(R.id.passWord);
                checkPass = (EditText) findViewById(R.id.checkPass);
                dataControl(name, bir, phone, passWord, id);
            }
        });
    }

    public void dataControl(EditText name, EditText bir, EditText phone, EditText passWord, EditText id) {
        if (!("".equals(name.getText().toString())
                || "".equals(id.getText().toString())
                || "".equals(bir.getText().toString())
                || "".equals(phone.getText().toString())
                || "".equals(passWord.getText().toString())
                || "".equals(checkPass.getText().toString()))) {
            try {
                String sname = name.getText().toString();
                String sbir = bir.getText().toString();
                String sphone = phone.getText().toString();
                String spassWord = passWord.getText().toString();
                String sid = id.getText().toString();
                checkUser(sid, sname, sbir, spassWord, sphone);



            } catch (Exception e) {

                Log.e("MainActivity", e.getMessage());

            }
        } else {
            Toast toast = Toast.makeText(userreg.this,
                    "請填寫完整資訊", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public void postUser(String bir, String name, String passWord, String phone, String id) {
        myAPIService = RetrofitManager.getInstance().getAPI();
        Call<records> call = myAPIService.postRecords(new userPost(new fields(bir, name, passWord, phone, id)));
        call.enqueue(new Callback<records>() {

            @Override
            public void onResponse(Call<records> call, Response<records> response) {
                Toast toast = Toast.makeText(userreg.this,
                        "註冊成功", Toast.LENGTH_LONG);
                toast.show();

            }

            @Override
            public void onFailure(Call<records> call, Throwable t) {
                Toast toast = Toast.makeText(userreg.this,
                        "註冊失敗", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }

    public void checkUser(final String id, final String name, final String bir, final String passWord, final String phone) {
        myAPIService = RetrofitManager.getInstance().getAPI();
        Call<records> call = myAPIService.getRecords();
        call.enqueue(new Callback<records>(){

            @Override
            public void onResponse(Call<records> call, Response<records> response) {
                String haveData = "F";
                for (int i = 0; i < response.body().getRecords().length; i++) {
                    String ID = response.body().getFieldsID(i);
                    if(id.equals(ID)){
                        haveData = "T";
                        break;
                    }
                }
                if(haveData=="T"){
                    Toast toast = Toast.makeText(userreg.this,
                            "此身分已有帳號", Toast.LENGTH_LONG);
                    toast.show();
                }
                else{
                    postUser(bir, name, passWord, phone, id);
                    getUser(id);
                }
            }

            @Override
            public void onFailure(Call<records> call, Throwable t) {

            }
        });

    }
    public void getUser(final String id){
        myAPIService = RetrofitManager.getInstance().getAPI();
        Call<records> call = myAPIService.getRecords();
        call.enqueue(new Callback<records>(){

            @Override
            public void onResponse(Call<records> call, Response<records> response) {
                String recordsId = null;    //註冊者fields外面的id
                String userName = null;     //註冊者的名字
                for (int i = 0; i < response.body().getRecords().length; i++) {
                    String ID = response.body().getFieldsID(i);
                    if(id.equals(ID)){
                        recordsId = response.body().getId(i);
                        userName = response.body().getFieldsName(i);
                        break;
                    }
                }
                if(recordsId!=null){
                    sendUserList(userName, recordsId);
                }
            }

            @Override
            public void onFailure(Call<records> call, Throwable t) {

            }
        });
    }

    //切換至主畫面
    public void sendUserList(String name, String recordsId) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("name",name);
        bundle.putString("recordsId",recordsId);
        intent.setClass(userreg.this, userlist.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }




}
