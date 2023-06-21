package com.example.aplikacjagra;

import static com.example.aplikacjagra.AppService.MilisekundyStoper;
import static com.example.aplikacjagra.MainActivity.pieniadze;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class ClickerActivity extends AppCompatActivity {

    static public float iloscHajsu = 0;
    static public float iloscHajsuNaSekunde = 0;
    static public float iloscHajsuSekundeTemu = 0;
    static public float hajsZaKlikniecie = 1;
    static public float bonusAktywny = 1; // Czyli ile dodatkowego hajsu za 1 klikniecie
    static public float bonusPasywny = 0; // Czyli ile dodatkowego hajsu na 1 milisekunde
    static public int cenaBonus1 = 15;
    static public int cenaBonus2 = 100;
    static public int cenaBonus3 = 10;
    static public int cenaBonusU1 = 500;
    static public int cenaBonusU2 = 1000;
    static public int cenaBonusU3 = 100;
    static public int ileKlikniecNaSekunde = 0;
    private static final String TAG = "ClickerActivity";
    static public String rekordCzas = "00:00:00:00";

    private Button sklepButton;
    private Button ulepszeniaButton;
    private TextView tv1;
    private TextView tv2;
    private TextView hajs;
    private ImageView monster;

    // Stoper
    public CountDownTimer czasMiliSekundy;

    private long timeLeftInMillis = 60000000; // czas trwania stopera w milisekundach

    private void resetUstawien() {

        iloscHajsu = 0;
        hajsZaKlikniecie = 1;
        bonusAktywny = 1;
        bonusPasywny = 0;
        cenaBonus1 = 15;
        cenaBonus2 = 100;
        cenaBonus3 = 10;
        cenaBonusU1 = 500;
        cenaBonusU2 = 1000;
        cenaBonusU3 = 100;
    }
    @SuppressLint("SetTextI18n")
    private void aktualizacjaPoSekundzie() {


        final TextView hajsNaSekunde = (TextView) findViewById(R.id.hajsNaSekunde);
        iloscHajsuNaSekunde = iloscHajsu - iloscHajsuSekundeTemu;

        if (iloscHajsuNaSekunde > 0) hajsNaSekunde.setText(Math.round(iloscHajsuNaSekunde * 100.0) / 100.0 + " zł/s");
        else hajsNaSekunde.setText("0 zł/s");

        iloscHajsuSekundeTemu = iloscHajsu;
    }

    @SuppressLint("SetTextI18n")
    private void aktualizacjaPoDziesietnejSekundy() {

        final TextView tv2 = (TextView) findViewById(R.id.textView2);
        final ImageView monster = findViewById(R.id.monster);

        if (ileKlikniecNaSekunde >= 10) {
            tv2.setText("PREMIA x2!!!! " + ileKlikniecNaSekunde);
        }
        else {
            monster.setImageResource(R.drawable.pixel_monster);
            tv2.setText("Osiągnij 10, aby dostać premie: " + ileKlikniecNaSekunde);
        }

        if (ileKlikniecNaSekunde > 0) ileKlikniecNaSekunde--;
    }

    @SuppressLint("SetTextI18n")
    private void aktualizacjaPoMiliSekundzie() {

        final TextView hajs = (TextView) findViewById(R.id.hajs);
        final TextView ileZaKlikniecie = (TextView) findViewById(R.id.ileZaKlikniecie);

        hajs.setText("" + (int)iloscHajsu);
        ileZaKlikniecie.setText("Za kliknięcie dostajesz: " + Math.round(hajsZaKlikniecie * bonusAktywny  * 100.0) / 100.0);

        iloscHajsu = iloscHajsu + bonusPasywny;

        MilisekundyStoper++;
    }

    private void PokazSklep() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        final String[] options = {cenaBonus1 + " zł -> Większe butelki (+20%)", cenaBonus2 +  " zł -> Żabka pod domem (x2)", cenaBonus3 +  " zł -> Przecena (+1)"};
        dialogBuilder.setTitle("Sklep - ulepsza kliknięcia");
        dialogBuilder.setCancelable(true);
        //final boolean[] checkedItems = {false, false, false, false};
        //dialogBuilder.setMultiChoiceItems(options, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
        dialogBuilder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position /*, boolean isChecked*/) {
                switch(position) {
                    case 0:
                        if (iloscHajsu - cenaBonus1 >= 0) {
                            bonusAktywny = (float) ((double) bonusAktywny * 1.2);
                            iloscHajsu = iloscHajsu - cenaBonus1;
                            cenaBonus1 = cenaBonus1 * 2;
                            Toast.makeText(getApplicationContext(), "Pomyślnie kupiono <3", Toast.LENGTH_SHORT).show();
                        }
                        else Toast.makeText(getApplicationContext(), "Nie stać cię", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        if (iloscHajsu - cenaBonus2 >= 0) {
                            bonusAktywny = (float) ((double) bonusAktywny * 2);
                            iloscHajsu = iloscHajsu - cenaBonus2;
                            cenaBonus2 = cenaBonus2 * 2;
                            Toast.makeText(getApplicationContext(), "Pomyślnie kupiono <3", Toast.LENGTH_SHORT).show();
                        }
                        else Toast.makeText(getApplicationContext(), "Nie stać cię", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        if (iloscHajsu - cenaBonus3 >= 0) {
                            bonusAktywny = bonusAktywny + 1;
                            iloscHajsu = iloscHajsu - cenaBonus3;
                            cenaBonus3 = cenaBonus3 * 2;
                            Toast.makeText(getApplicationContext(), "Pomyślnie kupiono <3", Toast.LENGTH_SHORT).show();
                        }
                        else Toast.makeText(getApplicationContext(), "Nie stać cię", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Log.i("lol", "Nie wiem jakim cudem ty to zrobiłeś lmao");
                }
            }
        });
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }

    private void PokazUlepszenia() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        final String[] options = {cenaBonusU1 + " zł -> Burza mózgów (+20%)", cenaBonusU2 +  " zł -> Termin zerowy (x2)", cenaBonusU3 +  " zł -> Notatki (+1)"};
        dialogBuilder.setTitle("Ulepszenia - działają pasywnie");
        dialogBuilder.setCancelable(true);
        //final boolean[] checkedItems = {false, false, false, false};
        //dialogBuilder.setMultiChoiceItems(options, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
        dialogBuilder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position /*, boolean isChecked*/) {
                switch(position) {
                    case 0:
                        if (iloscHajsu - cenaBonusU1 >= 0) {
                            bonusPasywny = (float) ((double) bonusPasywny * 1.2);
                            iloscHajsu = iloscHajsu - cenaBonusU1;
                            cenaBonusU1 = cenaBonusU1 * 2;
                            Toast.makeText(getApplicationContext(), "Pomyślnie kupiono <3", Toast.LENGTH_SHORT).show();
                        }
                        else Toast.makeText(getApplicationContext(), "Nie stać cię", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        if (iloscHajsu - cenaBonusU2 >= 0) {
                            bonusPasywny = (float) ((double) bonusPasywny * 2);
                            iloscHajsu = iloscHajsu - cenaBonusU2;
                            cenaBonusU2 = cenaBonusU2 * 2;
                            Toast.makeText(getApplicationContext(), "Pomyślnie kupiono <3", Toast.LENGTH_SHORT).show();
                        }
                        else Toast.makeText(getApplicationContext(), "Nie stać cię", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        if (iloscHajsu - cenaBonusU3 >= 0) {
                            bonusPasywny = bonusPasywny + (float)0.01;
                            iloscHajsu = iloscHajsu - cenaBonusU3;
                            cenaBonusU3 = cenaBonusU3 * 2;
                            Toast.makeText(getApplicationContext(), "Pomyślnie kupiono <3", Toast.LENGTH_SHORT).show();
                        }
                        else Toast.makeText(getApplicationContext(), "Nie stać cię", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Log.i("lol", "Nie wiem jakim cudem ty to zrobiłeś lmao");
                }
            }
        });
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_main);

        resetUstawien();

        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        sklepButton = (Button) findViewById(R.id.sklepButton);
        ulepszeniaButton = (Button) findViewById(R.id.ulepszeniaButton);
        tv1 = (TextView) findViewById(R.id.textView);
        tv2 = (TextView) findViewById(R.id.textView2);
        hajs = (TextView) findViewById(R.id.hajs);
        monster = findViewById(R.id.monster);

        sklepButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            public void onClick(View v) {

                tv1.setText("To Sklep");
                PokazSklep();
            }
        });

        ulepszeniaButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            public void onClick(View v) {

                tv1.setText("To Ulepszenia");
                PokazUlepszenia();
            }
        });


        monster.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (iloscHajsu == 0) czasMiliSekundy.start(); // uruchomienie stopera

                        // sprawdzenie, czy jest premia (złoty monster)
                        if (ileKlikniecNaSekunde >= 10) {
                            monster.setImageResource(R.drawable.pixel_monster_gold_clicked);
                            iloscHajsu = iloscHajsu + hajsZaKlikniecie * bonusAktywny * 2;
                        }
                        else {
                            monster.setImageResource(R.drawable.pixel_monster_clicked);
                            iloscHajsu = iloscHajsu + hajsZaKlikniecie * bonusAktywny;
                        }
                        ileKlikniecNaSekunde++;
                        return true;
                    case MotionEvent.ACTION_UP:
                        if (ileKlikniecNaSekunde >= 10) monster.setImageResource(R.drawable.pixel_monster_gold);
                        else monster.setImageResource(R.drawable.pixel_monster);
                        return true;
                    default:
                        return false;
                }
            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
        czasMiliSekundy = new CountDownTimer(timeLeftInMillis, 10) {

            int i = 0, j = 0;

            @Override
            public void onTick(long millisUntilFinished) {

                if (iloscHajsu < 1000000) {
                    timeLeftInMillis = millisUntilFinished;
                    aktualizacjaPoMiliSekundzie();
                    i++;
                    j++;
                    if (i == 100) {
                        aktualizacjaPoSekundzie();
                        i = 0;
                    }
                    if (j == 7) {
                        aktualizacjaPoDziesietnejSekundy();
                        j = 0;
                    }
                }
                else {
                    long elapsedTime = MilisekundyStoper;

                    // obliczamy godziny, minuty, sekundy i milisekundy z czasu upłyniętego
                    long hours = (elapsedTime / 360000) % 24;
                    long minutes = (elapsedTime / 6000) % 60;
                    long seconds = (elapsedTime / 100) % 60;
                    long millis = elapsedTime % 100;

                    // wyświetlamy czas w formacie godziny:minuty:sekundy:milisekundy
                    rekordCzas = String.format("%02d:%02d:%02d:%02d%n", hours, minutes, seconds, millis);
                    aktualizacjaPoMiliSekundzie();
                    aktualizacjaPoSekundzie();
                    aktualizacjaPoDziesietnejSekundy();
                    pieniadze = pieniadze + 100;
                    openZapisWynikuActivity();
                    timeLeftInMillis = 0;
                    onDestroy();
                }
            }

            @Override
            public void onFinish() {
                // kod wykonywany po zakończeniu odliczania czasu
            }
        };
    }

    public void openZapisWynikuActivity() {
        Intent intent = new Intent(this, ZapisWynikuActivity.class);
        startActivity(intent);
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
}
