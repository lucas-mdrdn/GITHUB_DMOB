package android.example.dmob;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabase extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "AlarmLibrary.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "my_library";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_HOUR = "Hour";
    private static final String COLUMN_MINUTE = "Minute";
    private static final String COLUMN_LATITUDE = "Latitude";
    private static final String COLUMN_LONGITUDE = "Longitude";
    private static final String COLUMN_RINGTONE = "Ringtone";


    public MyDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_HOUR + " INTEGER, " +
                        COLUMN_MINUTE + " INTEGER, " +
                        COLUMN_LATITUDE + " TEXT, " +
                        COLUMN_LONGITUDE + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addAlarm(int hour, int minute, String latitude, String longitude){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_HOUR, hour);
        cv.put(COLUMN_MINUTE, minute);
        cv.put(COLUMN_LATITUDE, String.valueOf(latitude));
        cv.put(COLUMN_LONGITUDE, String.valueOf(longitude));
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}
