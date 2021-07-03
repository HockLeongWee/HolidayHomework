package sg.edu.rp.c346.id20046765.holidayhomework;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.jar.Attributes;

public class ItemListActivity extends AppCompatActivity {

    EditText etName;
    EditText etYear;
    EditText etMonth;
    EditText etDay;
    Spinner spnMonth;
    Button btnAdd;
    Button btnUpdate;
    Button btnDelete;
    ListView lvItems;
    ArrayAdapter aaThings;
    String format = "";

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
        lvItems = findViewById(R.id.lvItems);

        ArrayList<String> alThings ;
        ArrayList<String> alName ;
        ArrayList<String> alDate;

        alThings  = new ArrayList<String>(); // ArrayList
        alName = new ArrayList<String>();
        alDate = new ArrayList<String>();

        aaThings  = new ArrayAdapter<>(this, R.layout.list_deco, alThings);
        lvItems.setAdapter(aaThings);

        ArrayAdapter adapterForSpinner = ArrayAdapter.createFromResource(this, R.array.spinnerMonths, R.layout.spinnertext);
        spnMonth.setAdapter(adapterForSpinner);

        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
              String date = String.format("%s-%s-%s", etYear.getText().toString(), etMonth.getText().toString(), etDay.getText().toString());
              alDate.add(date);
              String name = etName.getText().toString();
              alName.add(name);
                Collections.sort(alName);
              String format = String.format("Expires %s %s", date, etName.getText().toString());
              alThings.add(format);

//              Collections.sort(alName);

              aaThings.notifyDataSetChanged();
              etName.setText(null);
              etYear.setText(null);
              etMonth.setText(null);
              etDay.setText(null);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(alName.size() == 0){
                    Toast.makeText(ItemListActivity.this, "You don't have any items to remove.", Toast.LENGTH_SHORT).show();
                }else {
                    for (int i = 0; i < alThings.size(); i++) {
                        String namePosition = etName.getText().toString();
                        if (alName.get(i).equalsIgnoreCase(etName.getText().toString())) {
                            alThings.remove(i);
                            alName.remove(i);
                            alDate.remove(i);
                            aaThings.notifyDataSetChanged();
                            etName.setText(null);
                        } else {
                            Toast.makeText(ItemListActivity.this, "Item isn't found", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(alName.size() == 0){
                    Toast.makeText(ItemListActivity.this, "You don't have any items to remove.", Toast.LENGTH_SHORT).show();
                }else {
                    for (int i = 0; i < alThings.size(); i++) {
                        String name = etName.getText().toString();
                        if (alName.get(i).equalsIgnoreCase(name)) {
                            alName.set((i), name);
                            String date = String.format("%s-%s-%s", etYear.getText().toString(), etMonth.getText().toString(), etDay.getText().toString());
                            alDate.set((i), date);
                            String format = String.format("Expires %s %s", date, name);
                            alThings.set((i), format);
                            aaThings.notifyDataSetChanged();
                            etName.setText(null);
                            etDay.setText(null);
                            etMonth.setText(null);
                            etYear.setText(null);
                        } else {
                            Toast.makeText(ItemListActivity.this, "Item isn't found", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        spnMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0: // 1 month
                        Calendar calendar = Calendar.getInstance();
                        int day = calendar.get(Calendar.DAY_OF_MONTH);
                        int month = calendar.get(Calendar.MONTH);
                        int year = calendar.get(Calendar.YEAR);

                        break;
                    case 1: // 3 months

                    case 2: // 6 months
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}