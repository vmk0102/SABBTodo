package com.example.sabbtodo;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import java.util.Timer;
import java.util.TimerTask;

public class notifyService extends Service {
    private int counter = 0;
    private Timer timer;
    private NotificationManager notificationManager;

    public notifyService() {
    }
    @Override
    public void onCreate() {
        super.onCreate();
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(
                    "timer_channel", "Timer Channel", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(getApplicationContext(), "Service Chal Rahi", Toast.LENGTH_SHORT).show();
        startForeground(1,createNotification("Starting Timer"));
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                counter++;
                updateNotification("Timer Running" +counter + "Sec");
                if (counter >= 10) {
                    stopSelf();
                }
            }
        },0,1000);

        return START_NOT_STICKY;

    }
    public Notification createNotification(String text){
        NotificationCompat.Builder nb = new NotificationCompat.Builder(this,"timer_channel");
        nb.setContentTitle("SABB Karlo");
        nb.setContentText(text);
        nb.setSmallIcon(R.drawable.sabbkarlologo);
        return nb.build();

    }
    public void updateNotification(String text){
        notificationManager.notify(1,createNotification(text));
    }



    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}