package first.com;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class userlogin extends AppCompatActivity {

    MyAPIService myAPIService;
    EditText userId;
    EditText userPass;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlogin);
        Button nextPageBtn = (Button)findViewById(R.id.register);
        nextPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(userlogin.this , userreg.class);
                startActivity(intent);

            }
        });
        Button nextPageBtn1 = (Button)findViewById(R.id.userList);
        nextPageBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userId = (EditText) findViewById(R.id.userId);
                userPass = (EditText) findViewById(R.id.userPass);

                if (!("".equals(userId.getText().toString())
                        || "".equals(userPass.getText().toString()))){
                    String sId = userId.getText().toString().trim();
                    String sPass = userPass.getText().toString().trim();
                    checkUser(sId, sPass);
                }
                else{
                    Toast toast = Toast.makeText(userlogin.this,
                            "請把資料打完", Toast.LENGTH_LONG);
                    toast.show();
                }

            }
        });
    }
    public void checkUser(final String sId,final String sPass){
        //textView = (TextView)findViewById(R.id.hi);
        myAPIService = RetrofitManager.getInstance().getAPI();
        Call<records> call = myAPIService.getRecords();
        call.enqueue(new Callback<records>(){

            @Override
            public void onResponse(Call<records> call, Response<records> response) {
                String recordsId = null;
                String userName = null;
                for (int i = 0; i < response.body().getRecords().length; i++) {
                    String ID = response.body().getFieldsID(i);
                    //搜索身分證字號
                    if(sId.equalsIgnoreCase(ID)){
                        recordsId = response.body().getId(i);
                        userName = response.body().getFieldsName(i);
                        break;
                    }
                }
                if(recordsId==null && userName==null){
                    Toast toast = Toast.makeText(userlogin.this,
                            "此身分尚未註冊", Toast.LENGTH_LONG);
                    toast.show();
                    sendReg();
                }
                else{
                    Toast toast = Toast.makeText(userlogin.this,
                            "登入成功", Toast.LENGTH_LONG);
                    toast.show();
                    sendUserList(recordsId, userName);

                }
            }

            @Override
            public void onFailure(Call<records> call, Throwable t) {

            }
        });
    }

    public void sendUserList(String id, String name){
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("name",name);
        bundle.putString("recordsId",id);
        intent.setClass(userlogin.this, userlist.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void sendReg(){
        Intent intent = new Intent();
        intent.setClass(userlogin.this, userreg.class);
        startActivity(intent);
    }

}
