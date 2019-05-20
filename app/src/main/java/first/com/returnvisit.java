package first.com;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class returnvisit extends AppCompatActivity{

    private  String[] data={"周捷倫","鄧紫其","方炯彬","張三寶","李曉明","黃踏鄆","周捷倫","鄧紫其","方炯彬","張三寶","李曉明","黃踏鄆","周捷倫","鄧紫其","方炯彬","張三寶","李曉明","黃踏鄆"};
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_return_visit);
        //setContentView(R.layout.reservationimformation);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(returnvisit.this,android.R.layout.simple_list_item_1,data);
        ListView listview=(ListView)findViewById(R.id.patient);
        listview.setAdapter(adapter);

    }
}