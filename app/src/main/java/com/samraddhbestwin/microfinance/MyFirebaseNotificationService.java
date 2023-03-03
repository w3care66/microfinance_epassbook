package com.samraddhbestwin.microfinance;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.List;
import java.util.Map;

public class MyFirebaseNotificationService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (remoteMessage.getData() != null) {
            showNotification(remoteMessage.getData());
        }
    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        LoginActivity.firebaseToken = token;
    }

    // Method to display the notifications
    public void showNotification(Map<String, String> data) {
        // Pass the intent to switch to the MainActivity
        String channel_id = "microfinance_chennel";
        Intent intent = null;
        if(isAppOnForeground(getApplicationContext())){
            Intent broadcast = new Intent("UPDATE_COUNTER");
            LocalBroadcastManager.getInstance(this).sendBroadcast(broadcast);
            if (!data.get("type").equalsIgnoreCase("member_profile")){
                intent = new Intent(this, NotificationDetailActivity.class);
                intent.putExtra("type", data.get("type"));
                intent.putExtra("loan_id", data.get("loan_id"));
                intent.putExtra("notification_id", data.get("id"));
            }else {
                intent = new Intent(this, HomeActivity.class);
                intent.putExtra("is_from", "notification");
            }
        }else{
            intent = new Intent(this, PinActivity.class);
            intent.putExtra("type", data.get("type"));
            intent.putExtra("loan_id", data.get("loan_id"));
            intent.putExtra("notification_id", data.get("id"));
            intent.putExtra("is_from", "notification");
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent
                = PendingIntent.getActivity(
                this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        // Create a Builder object using NotificationCompat
        // class. This will allow control over all the flags
        NotificationCompat.Builder builder
                = new NotificationCompat
                .Builder(getApplicationContext(),
                channel_id)
                .setSmallIcon(R.drawable.logo_icon)
                .setAutoCancel(true)
                .setVibrate(new long[]{1000, 1000, 1000,
                        1000, 1000})
                .setOnlyAlertOnce(false)
                .setContentIntent(pendingIntent);

        builder = builder.setContentTitle(data.get("title"))
                .setContentText(data.get("body"))
                .setAutoCancel(false)
                .setSmallIcon(R.drawable.logo_icon);
        NotificationManager notificationManager
                = (NotificationManager) getSystemService(
                Context.NOTIFICATION_SERVICE);
        // Check if the Android Version is greater than Oreo
        if (Build.VERSION.SDK_INT
                >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel
                    = new NotificationChannel(
                    channel_id, "microfinance_app",
                    NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(
                    notificationChannel);
        }


        notificationManager.notify((int) System.currentTimeMillis(), builder.build());
    }

    private boolean isAppOnForeground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null) {
            return false;
        }
        final String packageName = getPackageName();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND && appProcess.processName.equals(packageName)) {
                return true;
            }
        }
        return false;
    }
}