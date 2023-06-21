package com.example.aplikacjagra;

import static com.example.aplikacjagra.MainActivity.listaPuszek;
import static com.example.aplikacjagra.MainActivity.pieniadze;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShopActivity extends AppCompatActivity {

    ListView listView;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        ImageView monsterShop = findViewById(R.id.monsterShop);
        TextView nazwa = findViewById(R.id.kupText1);
        TextView cenaText = findViewById(R.id.kupText);
        TextView pieniadzeText = findViewById(R.id.pieniadzeText);
        Button kup = findViewById(R.id.kup1);
        monsterShop.setImageResource(R.drawable.pixel_monster);
        pieniadzeText.setText("Posiadasz " + pieniadze + " pkt");

        monsterShop.setImageResource(listaPuszek.get(0).skin);
        nazwa.setText(listaPuszek.get(0).nazwa);
        cenaText.setText("od " + listaPuszek.get(0).koszt + " pkt");
        pieniadzeText.setText("Posiadasz " + pieniadze + " pkt");

        if (listaPuszek.get(0).posiadane) {

            kup.setEnabled(false);
            kup.setText("Odblokowany");
        }
        else if (pieniadze >= listaPuszek.get(0).koszt) {

            kup.setEnabled(true);
            kup.setText("Odblokuj");
        }
        else {

            kup.setEnabled(false);
            kup.setText("Zablokowany");
        }

        listView = (ListView) findViewById(R.id.listView);
        PuszkaAdapter arrayAdapter = new PuszkaAdapter (this,listaPuszek);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

                monsterShop.setImageResource(listaPuszek.get(i).skin);
                nazwa.setText(listaPuszek.get(i).nazwa);
                cenaText.setText("od " + listaPuszek.get(i).koszt + " pkt");
                pieniadzeText.setText("Posiadasz " + pieniadze + " pkt");

                if (listaPuszek.get(i).posiadane) {

                    kup.setEnabled(false);
                    kup.setText("Odblokowany");
                }
                else if (pieniadze >= listaPuszek.get(i).koszt) {

                    kup.setEnabled(true);
                    kup.setText("Odblokuj");
                }
                else {

                    kup.setEnabled(false);
                    kup.setText("Zablokowany");
                }

                // Sprawdzenie w logach czy ArrayAdapter działa po kliknięciu w element z listy
                Log.i("LIST_VIEW", "Item on position " + i + " is clicked");
                Log.i("LIST_VIEW", "Lista = " + listaPuszek );
                Log.i("LIST_VIEW", "Obiekt = " + listaPuszek.get(i) );
                Log.i("LIST_VIEW", "Skin = " + listaPuszek.get(i).skin);
                Log.i("LIST_VIEW", "Nazwa = " + listaPuszek.get(i).nazwa );
                Log.i("LIST_VIEW", "Posiadane = " + listaPuszek.get(i).posiadane );
                Log.i("LIST_VIEW", "Nazwa toString = " + listaPuszek.get(i).toString() );
            }
        });
    }
}
