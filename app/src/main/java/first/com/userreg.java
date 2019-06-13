package first.com;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class userreg extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userreg);

        Button nextPageBtn = (Button)findViewById(R.id.backuserlogin);
        nextPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(userreg.this , userlogin.class);
                startActivity(intent);
            }
        });

        Button nextPageBtn1 = (Button)findViewById(R.id.ok);
        nextPageBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(userreg.this , userlogin.class);
                startActivity(intent);
            }
        });
    }
}

