package first.com;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class viewclinicnumber extends AppCompatActivity {
    private  Spinner spinner;
    private ArrayAdapter<String> adapter;
    private static final String[] Clinic={"泌尿科","骨科","皮膚科"};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewclinicnumber);


        try{
            spinner = (Spinner) findViewById(R.id.spinner01);
            //將可選内容與ArrayAdapter連接起來
            adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,Clinic);
            //設置下拉列表的風格
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            //将adapter 添加到spinner中
            spinner.setAdapter(adapter);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

