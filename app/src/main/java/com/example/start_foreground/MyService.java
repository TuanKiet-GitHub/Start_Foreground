package com.example.start_foreground;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import static com.example.start_foreground.Channel_Id.CHANNEL_ID;

public class MyService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("Log", "On Create Service");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("Log" , "On Start Command Service");
        // Nhận dữ liệu trong OnStartCommand
        String dataIntent = intent.getStringExtra("key_data_intent");
        sendNotification(dataIntent);



        return START_NOT_STICKY;
    }

    private void sendNotification(String dataIntent) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this , 0 , intent , PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new NotificationCompat.Builder(this , CHANNEL_ID)
                .setContentTitle("Title Notification Service")
                .setContentText(dataIntent)
                .setSmallIcon(R.drawable.notification)
                .setContentIntent(pendingIntent) // dòng này để click vào thông báo hiển thị intent ta dùng Pending Intent.
                .build();
        startForeground(1, notification);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("Log", "On Destroy Service");
    }
}
