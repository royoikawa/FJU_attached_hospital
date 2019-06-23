package first.com;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class adminreg extends AppCompatActivity {
    MyAPIService myAPIService;
    EditText name;
    EditText id;
    EditText borned;
    EditText phone;
    EditText password;
    EditText password2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminregistered);

        Button back = (Button) findViewById(R.id.test);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(adminreg.this, MainActivity.class);
                startActivity(intent);
            }
        });

        name = (EditText) findViewById(R.id.name);
        id = (EditText) findViewById(R.id.id);
        borned = (EditText) findViewById(R.id.borned);
        phone = (EditText) findViewById(R.id.phone);
        password = (EditText) findViewById(R.id.password);
        password2 = (EditText) findViewById(R.id.password2);

        final Button reg = (Button) findViewById(R.id.test2);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aname = name.getText().toString().trim();
                String aid = id.getText().toString().trim();
                String aborned = borned.getText().toString().trim();
                String aphone = phone.getText().toString().trim();
                String apassword = password.getText().toString().trim();
                String apassword2 = password2.getText().toString().trim();
                myAPIService = RetrofitManager.getInstance().getAPI();
                Call<records> call = myAPIService.postRecords1(new userPost(new fields(aid, aname, apassword, aphone, aborned)));
                call.enqueue(new Callback<records>() {
                    @Override
                    public void onResponse(Call<records> call, Response<records> response) {
                     //   Toast.makeText(adminreg.this,response.toString(),Toast.LENGTH_SHORT).show();

                        Button return1 = (Button) findViewById(R.id.test2);
                        return1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.setClass(adminreg.this, adminlogin.class);
                                startActivity(intent);
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<records> call, Throwable t) {

                    }
                });

            }
        });
    }

}