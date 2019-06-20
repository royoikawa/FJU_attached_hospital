package first.com;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class userlist extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userlist);
        //從userreg接值
        Bundle bundlefrom = getIntent().getExtras();
        String name = bundlefrom.getString("name");
        String recordsId = bundlefrom.getString("recordsId");

        TextView username = findViewById(R.id.usern);
        username.setText(name+"您好");
//        String name = "test";
//        String recordsId = "recBDTGG9VTzJ45i0";
        final Bundle bundleto = new Bundle();
        bundleto.putString("name",name);
        bundleto.putString("recordsId",recordsId);

        Button nextPageBtn = (Button)findViewById(R.id.reservation);
        nextPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                //傳值到reservation
                Intent intent = new Intent();
                intent.putExtras(bundleto);
                intent.setClass(userlist.this , reservation.class);
                startActivity(intent);
            }
        });

        Button nextPageBtn1 = (Button)findViewById(R.id.viewclinicnumber);
        nextPageBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(userlist.this , viewclinicnumber.class);
                startActivity(intent);
            }
        });
    }
}