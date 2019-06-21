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

public class adminlogin extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminlogin);

        Button adminlogin = (Button) findViewById(R.id.button5);
        adminlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(adminlogin.this, adminreg.class);
                startActivity(intent);
            }
        });
    }

}
