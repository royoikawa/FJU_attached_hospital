package first.com;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class chooseoption extends AppCompatActivity {
    //要先知道醫生上班日期,才能顯示選項
   // private String[] data={"4/18","4/22","4/24","4/26","4/27","4/29","4/30","5/1"};
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chooseoption);
        /*ArrayAdapter<String> adapter=new ArrayAdapter<String>(chooseoption.this,android.R.layout.simple_list_item_1,data);
        ListView listview=(ListView)findViewById(R.id.frank);
        listview.setAdapter(adapter);  */
    }
}
