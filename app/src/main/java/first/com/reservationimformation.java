package first.com;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class reservationimformation extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner = (Spinner)findViewById(R.id.spinner01);
        final String[] name = {"廖品鈞", "廖子維", "林承鴻", "王冠文", "林育賢", "陳建甫"};
        ArrayAdapter<String> nameList = new ArrayAdapter<>(reservationimformation.this,
                android.R.layout.simple_spinner_dropdown_item,
                name);
        spinner.setAdapter(nameList);
    }
}
