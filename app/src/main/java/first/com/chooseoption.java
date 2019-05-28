package first.com;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class chooseoption extends AppCompatActivity {
    //要先知道醫生上班日期,才能顯示選項
   // private String[] data={"4/18","4/22","4/24","4/26","4/27","4/29","4/30","5/1"};
    Date currentd = new Date();

    //當周
    Date nextweek=new Date(new Date().getTime()+6*24*60*60*1000);
    SimpleDateFormat takeinidate= new SimpleDateFormat("yyyyMMdd");
    SimpleDateFormat takeenddate= new SimpleDateFormat("yyyyMMdd");
    String ini = takeinidate.format(currentd).substring(4);
    String end = takeenddate.format(nextweek).substring(4);
    String option1="當週("+ini+"~"+end+")";

    //第二周
    Date nextweek2=new Date(new Date().getTime()+7*24*60*60*1000);
    Date nextweek3=new Date(new Date().getTime()+13*24*60*60*1000);
    String ini2 = takeinidate.format(nextweek2).substring(4);
    String end2 = takeenddate.format(nextweek3).substring(4);
    String option2="下週("+ini2+"~"+end2+")";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chooseoption);
        String[] datedata={option1,option2};
        Spinner spinner = findViewById(R.id.week);
        ArrayAdapter<String> dateList = new ArrayAdapter<>(chooseoption.this,android.R.layout.simple_spinner_dropdown_item,datedata);
        spinner.setAdapter(dateList);
        //顯示所選擇科別
        TextView selectnum = (TextView)findViewById(R.id.selectnum);
        Bundle bundle = getIntent().getExtras();
        String selected_type = bundle.getString("name");
        selectnum.setText(selected_type+"醫生列表");

        /*ArrayAdapter<String> adapter=new ArrayAdapter<String>(chooseoption.this,android.R.layout.simple_list_item_1,data);
        ListView listview=(ListView)findViewById(R.id.frank);
        listview.setAdapter(adapter);  */
    }
}
