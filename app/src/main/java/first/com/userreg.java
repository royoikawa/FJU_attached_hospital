package first.com;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class userreg extends AppCompatActivity {

    EditText name;
    EditText id;
    EditText bir;
    EditText phone;
    EditText passWord;
    EditText checkPass;

    private void loadData(final String name, final String id, final String bir, final String phone, final String passWord) {
        String urlString = "https://api.airtable.com/v0/appgPqAWrw2xTWKdx/User?api_key=keyUwcLvTO51TNEHV";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(urlString, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray records = response.getJSONArray("records");
                    JSONObject userData = new JSONObject();
                    userData.put("Borned",bir);
                    userData.put("Name",name);
                    userData.put("Phone_number",phone);
                    userData.put("ID",id);
                    userData.put("Password",passWord);
                    JSONObject user = new JSONObject();
                    user.put("fields",userData);
                    records.put(records.length(),user);
                    Toast.makeText(getApplicationContext(),
                            "Success", Toast.LENGTH_LONG).show();
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
                    loadData(name.getText().toString(), id.getText().toString(), bir.getText().toString(), phone.getText().toString(), passWord.getText().toString());
                }
            }
        });
    }
}
