package android.example.dmob;

import android.app.NotificationManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.util.Log;
import android.widget.Toolbar;

import androidx.annotation.Nullable;

public class RingtonePreference extends PreferenceFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.ringtone_preference);
    }
}
