package com.example.aplikacjagra;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ScoreActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    MojaBazaDanychHelper myDB;
    ArrayList<String> rekord_id, rekord_name, rekord_time;
    CustomAdapter customAdapter;
    Button wroc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        recyclerView = findViewById(R.id.recyclerView);

        myDB = new MojaBazaDanychHelper(ScoreActivity.this);
        rekord_id = new ArrayList<>();
        rekord_name = new ArrayList<>();
        rekord_time = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(ScoreActivity.this, rekord_id, rekord_name, rekord_time);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ScoreActivity.this));

        wroc = (Button) findViewById(R.id.wroc);
        wroc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openMainActivity(); }
        });
    }

    void storeDataInArrays() {
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0) {
            Toast.makeText(this,"Brak rekordów...", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                rekord_id.add(cursor.getString(0));
                rekord_name.add(cursor.getString(1));
                rekord_time.add(cursor.getString(2));
            }
        }
    }

    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
