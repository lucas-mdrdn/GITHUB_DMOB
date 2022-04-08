package android.example.dmob;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    MyDatabase myDatabase;
    ArrayList<String> alarm_id, alarm_hour, alarm_minute, alarm_latitude, alarm_longitude;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        myDatabase = new MyDatabase(MainActivity.this);
        alarm_id = new ArrayList<>();
        alarm_hour = new ArrayList<>();
        alarm_minute = new ArrayList<>();
        alarm_latitude = new ArrayList<>();
        alarm_longitude = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(MainActivity.this, alarm_id, alarm_hour, alarm_minute);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    void storeDataInArrays(){
        Cursor cursor = myDatabase.readAllData();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "No alarms yet", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()){
                alarm_id.add(cursor.getString(0));
                alarm_hour.add(cursor.getString(1));
                alarm_minute.add(cursor.getString(2));
                alarm_latitude.add(cursor.getString(3));
                alarm_longitude.add(cursor.getString(4));
            }
        }
    }


    public void launchCreateAlarm(View v){
        Intent i = new Intent(this, CreateAlarm.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == R.id.delete_all){
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All ?");
        builder.setMessage("Are you sure you want to delete all ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabase myDatabase = new MyDatabase(MainActivity.this);
                myDatabase.deleteAllData();
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }

}

