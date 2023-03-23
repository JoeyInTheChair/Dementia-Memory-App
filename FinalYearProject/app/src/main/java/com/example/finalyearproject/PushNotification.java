package com.example.finalyearproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class PushNotification extends BroadcastReceiver {
    String fullName, title, description;

    @Override
    public void onReceive(Context context, Intent intent) {
        retrieveBundleInformation(intent);
        System.out.println("[INFO HERE]: " + fullName + ", " + title + ", " + description);
        NotificationCompat.Builder notification = new NotificationCompat.Builder(context, "notify " + fullName)
                .setSmallIcon(R.drawable.notification_bell)
                .setContentTitle(title)
                .setContentText(description)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManagerCompat.notify(200, notification.build());
    }
    private void retrieveBundleInformation(Intent intent) {
        Bundle task = intent.getExtras();
        if(task != null){
            fullName = task.getString("fullName");
            title = task.get("title").toString();
            description = task.get("description").toString();
        }
    }
}
