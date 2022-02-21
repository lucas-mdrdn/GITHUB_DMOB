package android.example.dmob;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

class alarm {
    float x;
    float y;
    float altitude;
    boolean lundi;
    boolean mardi;
    boolean mercredi;
    boolean jeudi;
    boolean vendredi;
    boolean samedi;
    boolean dimanche;
}