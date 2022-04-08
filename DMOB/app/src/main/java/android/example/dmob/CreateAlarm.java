package android.example.dmob;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


public class CreateAlarm extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    Button LocationButton;//Pour la localisation
    TextView PosX, PosY;//Pour la localisation
    FusedLocationProviderClient fusedLocationProviderClient;//Pour la localisation

    int Hour;
    int Minute;
    Button saveBtn;


    TextView mTextView;//Pour l'alarm

    Button SongButton;//Pour choisir le son

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_alarm);

        LocationButton = findViewById(R.id.btn_pos);
        PosX = findViewById(R.id.textView4);
        PosY = findViewById(R.id.textView5);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        mTextView = findViewById(R.id.textview1);

        saveBtn = findViewById(R.id.buttonSave);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabase myDB = new MyDatabase(CreateAlarm.this);
                String latitude = PosX.getText().toString();
                String longitude = PosY.getText().toString();
                myDB.addAlarm(Hour, Minute, latitude, longitude);
            }
        });


        getFragmentManager().beginTransaction().replace(R.id.fragment, new RingtonePreference()).commit();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        Button buttonTimePicker = findViewById(R.id.button2);
        buttonTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });


        LocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check permission
                if (ActivityCompat.checkSelfPermission(CreateAlarm.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    //When permission granted
                    getLocation();
                } else {
                    ActivityCompat.requestPermissions(CreateAlarm.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 44);
                }
            }
        });
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if (location != null) {
                    try {
                        Geocoder geocoder = new Geocoder(CreateAlarm.this, Locale.getDefault());
                        List<Address> adresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
//                        PosX.setText(String.valueOf(adresses.get(0).getLatitude()));
                        PosX.setText(Html.fromHtml("<font color='2#6200EE'><b>Latitude :</b><br></font>" + adresses.get(0).getLatitude()));
                        PosY.setText(Html.fromHtml("<font color='2#6200EE'><b>Longitude :</b><br></font>" + adresses.get(0).getLongitude()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void launchMainActivity(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }


    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        Hour = hourOfDay;
        Minute = minute;

        updateTimeText(calendar);
        startAlarm(calendar);
    }

    private void updateTimeText(Calendar calendar) {
        String timeText = "Alarm set for : ";
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar.getTime());

        mTextView.setText(timeText);
    }

    private void startAlarm(Calendar calendar) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

//        if (calendar.before(Calendar.getInstance())) {
//            calendar.add(Calendar.DATE, 1);
//        }//CODE A DECOMMENTER POUR LA VERSION FINALE

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }


    public void launchStopAlarm(View view) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if (location != null){
                    try {
                        Geocoder geocoder = new Geocoder(CreateAlarm.this, Locale.getDefault());
                        List<Address> adresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    float num1 = Float.parseFloat(String.valueOf(PosX));
                    float num2 = Float.parseFloat(String.valueOf(PosY));
                    if (num1 != location.getLatitude()){
                        //CODE SENSÉ ARRÊTER L'ALARME
                    }
                    else if (num2 != location.getLongitude()){
                        //CODE SENSÉ ARRÊTER L'ALARME
                    }
                }
            }
        });
    }
}