package android.example.dmob;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.se.omapi.Channel;

import androidx.core.app.NotificationCompat;

public class NotificationHelper extends ContextWrapper {
    public static final String channel1ID = "channel1ID";
    public static final String channel1Name = "channel 1";
    public static final String channel2ID = "channel2ID";
    public static final String channel2Name = "channel 2";
    public static Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);

    private NotificationManager mManager;
    
    public NotificationHelper(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
        createChannels();
        }
    }


    @TargetApi(Build.VERSION_CODES.O)
    private void createChannels() {//A MODIFIER POUR CHANGER LE TYPE DE NOTIFICATION (METTRE LE SON)
        NotificationChannel channel1 = new NotificationChannel(channel1ID, channel1Name, NotificationManager.IMPORTANCE_HIGH);
        channel1.enableLights(true);
        channel1.enableVibration(true);
        channel1.setLightColor(R.color.black);
        channel1.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        getManager().createNotificationChannel(channel1);


        NotificationChannel channel2 = new NotificationChannel(channel2ID, channel2Name, NotificationManager.IMPORTANCE_HIGH);
        channel2.enableLights(true);
        channel2.enableVibration(true);
        channel2.setLightColor(R.color.black);
        channel2.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(channel2);
    }

    public NotificationManager getManager(){
        if (mManager == null){
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return mManager;
    }

    public NotificationCompat.Builder getChannel1Notification(String title, String message){
        return new NotificationCompat.Builder(getApplicationContext(),channel1ID).setContentTitle(title).setContentText(message).setSmallIcon(R.drawable.ic_one).setSound(alarmSound);
    }

//    public NotificationCompat.Builder getChannel2Notification(String title, String message){
//        return new NotificationCompat.Builder(getApplicationContext(),channel2ID).setContentTitle(title).setContentText(message).setSmallIcon(R.drawable.ic_one).setSound(alarmSound);
//    }
}
