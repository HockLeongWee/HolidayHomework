package sg.edu.rp.c346.id20046765.holidayhomework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnViewMe;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnViewMe = findViewById(R.id.btnVistMe);
        btnNext = findViewById(R.id.btnNext);

        btnViewMe.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intentCall = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/HockLeongWee"));
                startActivity(intentCall);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, ItemListActivity.class);
                startActivity(intent);
            }
        });
    }
}