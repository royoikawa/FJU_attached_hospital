package first.com;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;

import android.widget.Button;
import android.view.View;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //loadData();

      Button nextPageBtn = (Button) findViewById(R.id.userlogin);
        nextPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, userlogin.class);
                startActivity(intent);
            }
        });
        Button mandgedlogin = (Button) findViewById(R.id.mandgedlogin);
        mandgedlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, adminlogin.class);
                startActivity(intent);
            }
        });


    }
}



