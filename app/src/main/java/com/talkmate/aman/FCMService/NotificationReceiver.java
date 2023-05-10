package com.talkmate.aman.FCMService;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.core.app.RemoteInput;

import com.talkmate.aman.R;


public class NotificationReceiver extends BroadcastReceiver {
    NotificationManager notificationManager;
    @Override
    public void onReceive(Context context, Intent intent) {

        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if(intent.hasExtra("ID"))
        {
            int noteId = intent.getIntExtra("ID",1);
            notificationManager.cancel(noteId);

        }else {
            Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
            if (remoteInput != null) {
                CharSequence feedback = remoteInput.getCharSequence("DirectReplyNotification");
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.talkmatelogo)
                        .setContentTitle("Thank you for your feedback!!!");

                notificationManager.notify(1, mBuilder.build());
            }

        }

    }
}
