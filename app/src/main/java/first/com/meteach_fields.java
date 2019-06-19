package first.com;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class meteach_fields extends AppCompatActivity {
    private Button meteach_social;
    private Button meteach_smoking;
    private Button meteach_new;
    private Button meteach_holiday;
    private Button meteach_table;
    private Button meteach_surgery;
    private Button meteach_microfilm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meteach_fields);

        meteach_social =(Button) findViewById(R.id.meteach_social);
        meteach_social.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(meteach_fields.this, social.class );
                startActivity(intent);
            }
        } );

        meteach_smoking=(Button) findViewById(R.id.meteach_smoking);
        meteach_smoking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(meteach_fields.this, smoking.class);
                startActivity(intent);
            }
        });

        meteach_new=(Button) findViewById(R.id.meteach_new);
        meteach_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(meteach_fields.this, menew.class);
                startActivity(intent);
            }
        });

        meteach_holiday=(Button) findViewById(R.id.meteach_holiday);
        meteach_holiday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(meteach_fields.this, holiday.class);
                startActivity(intent);
            }
        });

        meteach_table=(Button) findViewById(R.id.meteach_table);
        meteach_table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(meteach_fields.this, table.class);
                startActivity(intent);
            }
        });

        meteach_surgery=(Button) findViewById(R.id.meteach_surgery);
        meteach_surgery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(meteach_fields.this,  surgery.class);
                startActivity(intent);
            }
        });

        meteach_microfilm=(Button) findViewById(R.id.meteach_microfilm);
        meteach_microfilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(meteach_fields.this, microfilm.class);
                startActivity(intent);
            }
        });

    }
}
