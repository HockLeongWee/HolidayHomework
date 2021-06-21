package sg.edu.rp.c346.id20046765.holidayhomework;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class ItemListActivity extends AppCompatActivity {

    EditText etName;
    EditText etYear;
    EditText etMonth;
    EditText etDay;
    Spinner spnMonth;
    Button btnAdd;
    Button btnUpdate;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        etName = findViewById(R.id.etName);
        etYear = findViewById(R.id.etYear);
        etMonth = findViewById(R.id.etMonth);
        etDay = findViewById(R.id.etDay);
        spnMonth = findViewById(R.id.spnMonth);
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        ArrayAdapter adapterForSpinner = ArrayAdapter.createFromResource(this, R.array.spinnerMonths, R.layout.spinnertext);
        spnMonth.setAdapter(adapterForSpinner);

    }
}