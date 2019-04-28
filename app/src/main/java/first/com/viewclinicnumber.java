package first.com;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class viewclinicnumber extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewclinicnumber);
        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> clinicList = ArrayAdapter.createFromResource(viewclinicnumber.this,
                R.array.clinic_spinner,
                android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(clinicList);
    }
}

