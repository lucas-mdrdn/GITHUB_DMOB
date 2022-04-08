package android.example.dmob;

import android.app.NotificationManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.util.Log;
import android.widget.Toolbar;

import androidx.annotation.Nullable;

public class RingtonePreference extends PreferenceFragment {
    private int messageCount = 0;
    private static Uri alarmSound;
    private final long[] pattern = {100, 300, 300, 300};
    private NotificationManager mNotificationManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.ringtone_preference);
    }
}
