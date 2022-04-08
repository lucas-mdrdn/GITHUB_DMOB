package android.example.dmob;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.util.Log;

import androidx.annotation.Nullable;

public class RingtonePreference extends PreferenceFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.ringtone_preference);
    }
}
