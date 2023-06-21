package com.example.aplikacjagra;

import static com.example.aplikacjagra.AppService.czas;
import static com.example.aplikacjagra.ClickerActivity.rekordCzas;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ZapisWynikuActivity extends AppCompatActivity {

    private Button btn_take;
    private Button btn_zapisz;
    private ImageView awatar;
    private EditText nazwa;
    private TextView rekordCzasText;

    private String rekordCzas = "00:05:12.00";
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zapis_wyniku);

        btn_take = (Button) findViewById(R.id.btn_take);
        btn_zapisz = (Button) findViewById(R.id.btn_zapisz);
        awatar = (ImageView) findViewById(R.id.photo);
        nazwa = (EditText) findViewById(R.id.editText);
        rekordCzasText = (TextView) findViewById(R.id.stoperTextView);
        rekordCzasText.setText(rekordCzas);

        btn_take.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Wykonuje zdjęcie
                TakePicture();
            }
        });

        btn_zapisz.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                MojaBazaDanychHelper myDB = new MojaBazaDanychHelper(ZapisWynikuActivity.this);
                myDB.dodajRekord(nazwa.getText().toString().trim(), rekordCzas);
                openScoreActivity();
            }
        });

    }

    public void openScoreActivity(){
        Intent intent = new Intent(this, ScoreActivity.class);
        startActivity(intent);
    }

    protected void TakePicture() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null)
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            awatar.setImageBitmap(imageBitmap);
        }
    }
}
