package first.com;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class reservation extends AppCompatActivity{

    private String[] data={"胸腔內科","心臟內科","老年醫學科","神經內科","血液腫瘤科","腎臟內科","一般外科","神經外科","小兒科","整形外科","骨科","泌尿科"};
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation);
        //setContentView(R.layout.reservationimformation);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(reservation.this,android.R.layout.simple_list_item_1,data);
        ListView listview=(ListView)findViewById(R.id.frank);
        listview.setAdapter(adapter);

    }
}
