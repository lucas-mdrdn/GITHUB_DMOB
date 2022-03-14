package android.example.dmob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CreateAlarm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_alarm);
    }

    public void launchMainActivity(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
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
}