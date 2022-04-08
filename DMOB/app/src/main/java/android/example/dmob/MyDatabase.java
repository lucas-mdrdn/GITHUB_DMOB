package android.example.dmob;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabase extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "AlarmLibrary.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "my_library";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_HOUR = "Hour";
    private static final String COLUMN_MINUTE = "Minute";
    private static final String COLUMN_LATITUDE = "Latitude";//BOOK TITLE CHEZ LUI
    private static final String COLUMN_LONGITUDE = "Longitude";//BOOK AUTHOR CHEZ LUI
    private static final String COLUMN_RINGTONE = "Ringtone";//BOOK PAGES CHEZ LUI


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
                        COLUMN_LATITUDE + " FLOAT, " +
                        COLUMN_LONGITUDE + " FLOAT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
