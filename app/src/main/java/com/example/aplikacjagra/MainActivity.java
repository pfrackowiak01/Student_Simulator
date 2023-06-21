package com.example.aplikacjagra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.lang.Object;

public class MainActivity extends AppCompatActivity {

    private Button nowaGraButton;
    private Button sklepMenuButton;
    private Button rekordyButton;
    private Button zapisWynikuButton;
    private Button startServiceButton;
    private Button stopServiceButton;
    private Button locationButton;
    private static final String TAG = "MainActivity";
    static public int pieniadze = 350;

    static public ArrayList<Puszka> listaPuszek = new ArrayList<>();

    public void stworzListePuszek() {

        listaPuszek.add(new Puszka(R.drawable.pixel_monster,R.drawable.pixel_monster_clicked,"Classic",200,true));
        listaPuszek.add(new Puszka(R.drawable.pixel_monster,R.drawable.pixel_monster_clicked,"Mango Loco",300,false));
        listaPuszek.add(new Puszka(R.drawable.pixel_monster_gold,R.drawable.pixel_monster_gold_clicked,"Forever Gold",400,false));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Log.i(TAG, "onCreate");

        stworzListePuszek();
        nowaGraButton = (Button) findViewById(R.id.nowaGraButton);
        sklepMenuButton = (Button) findViewById(R.id.sklepMenuButton);
        rekordyButton = (Button) findViewById(R.id.rekordyButton);
        zapisWynikuButton = (Button) findViewById(R.id.zapisWynikuButton);
        startServiceButton = (Button) findViewById(R.id.startServiceButton);
        stopServiceButton = (Button) findViewById(R.id.stopServiceButton);
        locationButton = (Button) findViewById(R.id.locationButton);

        nowaGraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openClickerActivity(); }
        });

        sklepMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openShopActivity(); }
        });

        rekordyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openScoreActivity(); }
        });

        zapisWynikuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openZapisWynikuActivity(); }
        });

        startServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { startService(); }
        });

        stopServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { stopService(); }
        });

        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openLocationActivity(); }
        });

    }

    public void openClickerActivity() {
        startService();
        Intent intent = new Intent(this, ClickerActivity.class);
        startActivity(intent);
    }

    public void openShopActivity(){
        Intent intent = new Intent(this, ShopActivity.class);
        startActivity(intent);
    }

    public void openScoreActivity(){
        Intent intent = new Intent(this, ScoreActivity.class);
        startActivity(intent);
    }

    public void openZapisWynikuActivity() {
        Intent intent = new Intent(this, ZapisWynikuActivity.class);
        startActivity(intent);
    }

    public void openLocationActivity() {
        Intent intent = new Intent(this, LocationActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        stopService();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
        Intent serviceIntent = new Intent(this, ClickerActivity.class);
    }

    // Zaczyna serwis
    public void startService() {
        Log.i(TAG, "startService");
        String input = "Hello";
        Intent serviceIntent = new Intent(this, AppService.class);
        serviceIntent.putExtra("inputExtra", input);
        startService(serviceIntent);
    }

    // Kończy serwis
    public void stopService() {
        Log.i(TAG, "stopService");
        Intent serviceIntent = new Intent(this, AppService.class);
        stopService(serviceIntent);
    }

}