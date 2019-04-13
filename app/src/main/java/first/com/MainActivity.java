package first.com;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_clinic_number);

        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        final String[] lunch = {"胸腔內科", "心臟內科", "老年醫學科", "神經內科", "一般外科"};
        ArrayAdapter<String> lunchList = new ArrayAdapter<>(MainActivity.this,R.layout.myspinner, lunch);
        spinner.setAdapter(lunchList);

        ListView listview = (ListView) findViewById(R.id.listview);
        String[] str = {"您的看診號碼為 : 尚未預約","醫生 : 張三","看診位置 : A棟3樓 1診","目前看診號碼為 : 22"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,R.layout.myspinner,str);
        listview.setAdapter(adapter);

        ListView listview1 = (ListView) findViewById(R.id.listview);
        String[] str1 = {"您的看診號碼為 : 39","醫生 : 李四" ,"看診位置 : B棟2樓 3診","目前看診號碼為 : 24"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(MainActivity.this,R.layout.myspinner,str1);
        listview.setAdapter(adapter1);

    }
}
