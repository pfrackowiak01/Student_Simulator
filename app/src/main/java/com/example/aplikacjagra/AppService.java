package com.example.aplikacjagra;

import static com.example.aplikacjagra.App.CHANNEL_ID;
import static com.example.aplikacjagra.ClickerActivity.iloscHajsu;
import static com.example.aplikacjagra.ClickerActivity.iloscHajsuNaSekunde;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

public class AppService extends Service {

    private static final int NOTIFICATION_ID = 1;
    private boolean isRunning = false;
    private static final String TAG = "AppService";
    private Handler handler = new Handler();
    private NotificationManager notificationManager;
    private NotificationCompat.Builder builder;
    public static String czas;

    private int FPS = 4;
    private int dlugoscTrwaniaJednejKlatki = 1000 / FPS;
    private int poczatekKlatki;
    private int czasKlatki;
    static public int MilisekundyStoper = 0;

    private void createNotificationChannel()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "default",
                    "Default Channel",
                    NotificationManager.IMPORTANCE_LOW);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (isRunning) {
                // System FPS
                poczatekKlatki = MilisekundyStoper;

                czas = aktualizujCzas();
                //Log.i(TAG, "Sekundy: " + counter);
                //Log.i(TAG, "Milisekundy: " + MilisekundyStoper);
                Log.i(TAG, "Czas: " + czas);
                //builder.setContentText("Sekundy: " + counter);
                //builder.setContentText("Czas: " + czas + "\nPunkty: " + iloscHajsu + "\nPasywny przychód: " + iloscHajsuNaSekunde + "pkt/s");
                builder.setContentText("Czas: " + czas);
                notificationManager.notify(NOTIFICATION_ID, builder.build());

                // System FPS
                czasKlatki = MilisekundyStoper - poczatekKlatki;
                if (czasKlatki < dlugoscTrwaniaJednejKlatki) {
                    handler.postDelayed(this, (dlugoscTrwaniaJednejKlatki - czasKlatki));
                }
            }
        }
    };

    @SuppressLint("DefaultLocale")
    public String aktualizujCzas() {
        // obliczamy czas upłynięty od czasu początkowego
        long elapsedTime = MilisekundyStoper;

        // obliczamy godziny, minuty, sekundy i milisekundy z czasu upłyniętego
        long hours = (elapsedTime / 360000) % 24;
        long minutes = (elapsedTime / 6000) % 60;
        long seconds = (elapsedTime / 100) % 60;
        long millis = elapsedTime % 100;

        // wyświetlamy czas w formacie godziny:minuty:sekundy:milisekundy
        return String.format("%02d:%02d:%02d:%02d%n", hours, minutes, seconds, millis);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("MyService", "Service został utworzony");
        isRunning = true;

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        createNotificationChannel();

        builder = new NotificationCompat.Builder(this, "default")
                .setContentTitle("Czy zdążysz przed deadlinem?")
                .setContentText("Czas: " + czas)
                .setSmallIcon(R.drawable.pixel_monster)
                .setOngoing(true);
        startForeground(NOTIFICATION_ID, builder.build());
        //handler.postDelayed(runnable, 1000);

        handler.postDelayed(runnable, 0);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "Service został zniszczony");
        isRunning = false;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "Service został uruchomiony");
        /*
        String input = intent.getStringExtra("inputExtra");

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,notificationIntent,0);

        Notification notification = new NotificationCompat.Builder(this,CHANNEL_ID)
                .setContentTitle("Stan twojej inżynierki = " + iloscHajsu / 10000 + "%")
                .setContentText(input)
                .setSmallIcon(R.drawable.pixel_monster)
                .setContentIntent(pendingIntent)
                .build();

        startForeground(1,notification);

        // do heavy work on a background thread
        //stopSelf();
        */
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
