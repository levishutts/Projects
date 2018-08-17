package com.example.lcs.tidepredictions;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by lcs on 7/19/2017.
 */

public class TideData {

    public static final String DATA_NAME = "tide.db";
    public static final int    DATA_VERSION = 1;
    public static final String TIDE_TABLE = "tide";
    public static final String TIDE_ID= "_id";
    public static final int    TIDE_ID_COL = 0 ;
    public static final String TIDE_STATION = "station";
    public static final int    TIDE_STATION_ID = 1;
    public static final String TIDE_DATE = "date";
    public static final int    TIDE_DATE_ID = 2;
    public static final String TIDE_DAY = "day";
    public static final int    TIDE_DAY_ID = 3;
    public static final String TIDE_TIME = "time";
    public static final int    TIDE_TIME_ID = 4;
    public static final String TIDE_PRED_IN_FT = "pred_in_ft";
    public static final int    TIDE_PRED_IN_FT_ID = 5;
    public static final String TIDE_PRED_IN_CM = "pred_in_cm";
    public static final int    TIDE_PRED_IN_CM_ID = 6;
    public static final String TIDE_HIGHLOW= "highlow";
    public static final int    TIDE_HIGHLOW_ID = 7;
    public static final String CREATE_TIDE_TABLE = "CREATE TABLE "+TIDE_TABLE+ " (" +
            TIDE_ID         + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TIDE_STATION    + " TEXT, "                              +
            TIDE_DATE       + " TEXT, "                              +
            TIDE_DAY        + " TEXT, "                              +
            TIDE_TIME       + " TEXT, "                              +
            TIDE_PRED_IN_FT + " TEXT, "                              +
            TIDE_PRED_IN_CM + " TEXT, "                              +
            TIDE_HIGHLOW    + " TEXT, "
            + "UNIQUE(" + TIDE_STATION +
            ", " + TIDE_DATE +
            ", " + TIDE_TIME + ") ON CONFLICT REPLACE);";
    public static final String DROP_TIDE_TABLE = "DROP TABLE IF EXISTS " + TIDE_TABLE;

    private SQLiteDatabase database;
    private DBHelper databaseHelper;

    public TideData(Context context){
        databaseHelper = new DBHelper(context, DATA_NAME, null, DATA_VERSION);
    }

    private static class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TIDE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.d("Tide list", "Upgrading database from version "+ oldVersion + " to version " + newVersion);
            db.execSQL(TideData.DROP_TIDE_TABLE);
            onCreate(db);
        }
    }

    private void openReadable(){
        database = databaseHelper.getReadableDatabase();
    }

    private void openWritable(){
        database = databaseHelper.getWritableDatabase();
    }

    private void closeDatabase(){
        if(database != null){
            database.close();
        }
    }

    public long insertTideItem(TideItem tideItem){
        ContentValues contentValues = new ContentValues();
        contentValues.put(TIDE_DATE, tideItem.getDate());
        contentValues.put(TIDE_DAY, tideItem.getDay());
        contentValues.put(TIDE_TIME, tideItem.getTime());
        contentValues.put(TIDE_PRED_IN_FT ,tideItem.getFeet());
        contentValues.put(TIDE_PRED_IN_CM, tideItem.getCm());
        contentValues.put(TIDE_HIGHLOW, tideItem.getHighLow());
        contentValues.put(TIDE_STATION, tideItem.getStation());

        this.openWritable();
        long rowID = database.insert(TIDE_TABLE, null, contentValues);
        this.closeDatabase();

        return rowID;
    }

    public ArrayList<TideItem> getTideItems(String date, String station){

        this.openReadable();
        Cursor cursor = database.query(TIDE_TABLE, null, TIDE_DATE + " like '%" +date +"%' AND " + TIDE_STATION + " like '%" + station + "%'", null,null, null, null);
        ArrayList<TideItem> tides = new ArrayList<TideItem>();
        while(cursor.moveToNext()){
            tides.add(getTide(cursor));
        }
        if (cursor != null){
            cursor.close();
        }

        this.closeDatabase();
        return tides;
    }

    private static TideItem getTide(Cursor cursor){
        if (cursor==null || cursor.getCount() == 0){
            return null;
        }

        else{
            try{
                TideItem tide = new TideItem(
                        cursor.getString(TIDE_STATION_ID),
                        cursor.getString(TIDE_DATE_ID),
                        cursor.getString(TIDE_DAY_ID),
                        cursor.getString(TIDE_TIME_ID),
                        cursor.getString(TIDE_PRED_IN_FT_ID),
                        cursor.getString(TIDE_PRED_IN_CM_ID),
                        cursor.getString(TIDE_HIGHLOW_ID));
                return tide;
            }catch(Exception e){
                return null;
            }
        }
    }
}


