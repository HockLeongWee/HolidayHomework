package sg.edu.rp.c346.id20046765.holidayhomework;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
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
    ArrayAdapter sorting;
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
        ArrayList<String> alSorting;
        ArrayList<Integer> alMonth;
        ArrayList<Integer> alYear;

        alThings  = new ArrayList<String>(); // ArrayList
        alName = new ArrayList<String>();
        alDate = new ArrayList<String>();
        alSorting = new ArrayList<String>();
        alMonth = new ArrayList<Integer>();
        alYear = new ArrayList<Integer>();

        sorting  = new ArrayAdapter<>(this, R.layout.list_deco, alSorting);
        aaThings  = new ArrayAdapter<>(this, R.layout.list_deco, alThings);
        lvItems.setAdapter(aaThings);

        ArrayAdapter adapterForSpinner = ArrayAdapter.createFromResource(this, R.array.spinnerMonths, R.layout.spinnertext);
        spnMonth.setAdapter(adapterForSpinner);

        Calendar calendar = Calendar.getInstance(); // Creating a Calendar object while using it's class method .getInstances. ( To get today's day,month,year).
        int day = calendar.get(Calendar.DAY_OF_MONTH); // Using the object, we can get the today's day.
        int month = calendar.get(Calendar.MONTH); // today's month
        int year = calendar.get(Calendar.YEAR); // today's year.

        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
              String date = String.format("%s-%s-%s", etYear.getText().toString(), etMonth.getText().toString(), etDay.getText().toString());
              alDate.add(date);

              String month = String.format("%s", etMonth.getText().toString());
              int month1 = Integer.parseInt(month);
              alMonth.add(month1);

              String year = String.format("%s", etYear.getText().toString());
              int year1 = Integer.parseInt(year);
              alYear.add(year1);

              String name = etName.getText().toString();
              alName.add(name);
              Collections.sort(alName);
              alThings.clear();


              for(int i = 0; i < alName.size(); i++){
                  String format = String.format("Expires %s %s", alDate.get(i), alName.get(i));
                  alThings.add(format);
              }

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
                        alSorting.clear();
                        for(int i = 0; i < alThings.size(); i++){
                            // 1 MONTH LATER
                            String monthStr = String.format("%d", (month + 2));
                            int monthInt = Integer.parseInt(monthStr);
                            if(alMonth.get(i) > monthInt || alYear.get(i) > year){
                                alSorting.add(alThings.get(i));
                                lvItems.setAdapter(sorting);
                                sorting.notifyDataSetChanged();
                            }
                        }

                        break;
                    case 1: // 3 months
                        for(int i = 0; i < alThings.size(); i++){
                            // 3 MONTH LATER
                            String monthStr1 = String.format("%d", (month + 4)); // giving the month +4 to get the things that expires 3 months later
                            int monthInt1 = Integer.parseInt(monthStr1); // making it into a Integer, for later use. what it would be like is 20210704 < date of 2021-07-04
                            if(alMonth.get(i) > monthInt1){ // comparing if the expiry date is bigger than
                                alSorting.clear();
                                alSorting.add(alThings.get(i));
                                lvItems.setAdapter(sorting);
                                sorting.notifyDataSetChanged();
                            }
                        }

                    case 2: // 6 months
                        for(int i = 0; i < alThings.size(); i++){
                            // 6 MONTH LATER
                            String monthStr2 = String.format("%d", (month + 7));
                            int monthInt2 = Integer.parseInt(monthStr2);
                            if(alMonth.get(i) > monthInt2){
                                alSorting.clear();
                                alSorting.add(alThings.get(i));
                                lvItems.setAdapter(sorting);
                                sorting.notifyDataSetChanged();
                            }else if(alYear.get(i) > year && alMonth.get(i) < monthInt2){ // HAHA STUPID IDEA FROM ME. 6months later hmm, 6 months later can't have any months bigger than it.
                                alSorting.clear();
                                alSorting.add(alThings.get(i));
                                lvItems.setAdapter(sorting);
                                sorting.notifyDataSetChanged();                                             // Making sure it's next year and also it's month is lesser than the current month. Because it's only 6 months.
                            }
                        }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}