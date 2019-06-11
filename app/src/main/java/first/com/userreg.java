package first.com;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class userreg extends AppCompatActivity {
    MyAPIService myAPIService;
    ArrayList<records> array = new ArrayList<>();

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
                        //boolean haveData = checkUser(sid);
                        if(true){
                             Toast toast = Toast.makeText(userreg.this,
                                    "此身分證已有帳號", Toast.LENGTH_LONG);
                            toast.show();
                        }
                        else{
                            postUser(sbir, sname, spassWord, sphone, sid);
                            intent();
                        }

                    } catch (Exception e) {

                        Log.e("MainActivity", e.getMessage());

                    }
                }
                else{
                    Toast toast = Toast.makeText(userreg.this,
                            "請填寫完整資訊", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }

    public void postUser(String bir, String name, String passWord, String phone, String id){
        myAPIService = RetrofitManager.getInstance().getAPI();
        Call<records> call= myAPIService.postRecords(new userPost(new fields(bir, name, passWord, phone, id)));
        call.enqueue(new Callback<records>(){

            @Override
            public void onResponse(Call<records> call, Response<records> response) {
                //利用Toast的靜態函式makeText來建立Toast物件
                Toast toast = Toast.makeText(userreg.this,
                        "註冊成功", Toast.LENGTH_LONG);
                //顯示Toast
                toast.show();
            }

            @Override
            public void onFailure(Call<records> call, Throwable t) {
                //利用Toast的靜態函式makeText來建立Toast物件
                Toast toast = Toast.makeText(userreg.this,
                        "註冊失敗", Toast.LENGTH_LONG);
                //顯示Toast
                toast.show();
            }
        });
    }
    public String checkUser(final String id){

        myAPIService = RetrofitManager.getInstance().getAPI();
        Call<records> call = myAPIService.getRecords();
        call.enqueue(new Callback<records>() {
            @Override
            public void onResponse(Call<records> call, Response<records> response) {
                for(int i=0; i < response.body().getRecords().length; i++){
                    if(id.equals(response.body().getFieldsID(i))){

                        break;
                    }
                }
            }

            @Override
            public void onFailure(Call<records> call, Throwable t) {
                Toast toast = Toast.makeText(userreg.this,
                        "驗證資料失敗", Toast.LENGTH_LONG);
                toast.show();
            }
        });
        return "1";
    }

    public void intent(){
        Intent intent = new Intent();
        intent.setClass(userreg.this,userlist.class); //告訴它從哪邊切換到哪邊
        startActivity(intent);//切換
    }


}
