package first.com;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class reservationimformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservationimformation);
        LinearLayout infor = findViewById(R.id.printinfor);
        Bundle bundle = getIntent().getExtras();
        String docname = bundle.getString("name");
        String Select_type=bundle.getString("type");
        String clinic=bundle.getString("clinic");
        String decidetime=bundle.getString("time");
        TextView printname=new TextView(this);
        printname.setWidth(600);printname.setHeight(150);
        printname.setPadding(5,5,0,0);
        printname.setText(" 醫生姓名:"+docname);
        printname.setTextSize(24);
        infor.addView(printname);
        TextView printdiv=new TextView(this);
        printdiv.setWidth(500);printname.setHeight(150);
        printdiv.setPadding(5,30,0,0);
        printdiv.setText(" 所屬科別:"+Select_type);
        printdiv.setTextSize(24);
        infor.addView(printdiv);
        TextView printtime=new TextView(this);
        printtime.setWidth(500);printname.setHeight(150);
        printtime.setPadding(5,30,0,0);
        printtime.setText(" 看診時間:"+decidetime);
        printtime.setTextSize(24);
        infor.addView(printtime);
        TextView printloc=new TextView(this);
        printloc.setWidth(500);printname.setHeight(100);
        printloc.setPadding(5,30,0,0);
        printloc.setText("   診間:"+clinic);
        printloc.setTextSize(24);
        infor.addView(printloc);
        LinearLayout infor1= findViewById(R.id.printdate);
        TextView gotodoc=new TextView(this);
        gotodoc.setWidth(1000);gotodoc.setHeight(280);
        gotodoc.setPadding(5,100,0,0);
        gotodoc.setText("   就診日期:");
        gotodoc.setTextSize(28);
        infor1.addView(gotodoc);
        LinearLayout infor2= findViewById(R.id.printperson);
        TextView gotname=new TextView(this);
        gotname.setWidth(1000);gotname.setHeight(280);
        gotname.setPadding(5,10,0,0);
        gotname.setText("   就診者姓名:");
        gotname.setTextSize(28);
        infor2.addView(gotname);
        Spinner relative=new Spinner(this);
        relative.setMinimumWidth(200);
        relative.setMinimumHeight(80);
        relative.setPadding(170,80,0,0);
        infor2.addView(relative);
        // final String[] name = {"廖品鈞", "廖子維", "林承鴻", "王冠文", "林育賢", "陳建甫"};
        //ArrayAdapter<String> nameList = new ArrayAdapter<>(MainActivity.this,
        //       android.R.layout.simple_spinner_dropdown_item,
        //       name);
        //spinner.setAdapter(nameList);
    }
}
