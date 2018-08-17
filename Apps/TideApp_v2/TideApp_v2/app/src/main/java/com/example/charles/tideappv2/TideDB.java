package com.example.charles.tideappv2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Charles on 7/17/2017.
 */

public class TideDB {
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    //Constructor
    public TideDB(Context context){
        dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
    }
    //private methods
    private void openReadableDB(){
        db = dbHelper.getReadableDatabase();
    }
    private void openWritableDB(){
        db = dbHelper.getWritableDatabase();
    }
    private void closeDB(){
        if(db != null){
            db.close();
        }
    }

    //database constants
    public static final String DB_NAME = "tide.db";
    public static final int    DB_VERSION = 1;

    //tide table constants
    public static final String TIDE_TABLE = "tide";
    /*

    _id | station | date | day | time | pred_in_ft | pred_in_cm | highlow
    0        1        2     3      4         5            6          7

    */
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
            db.execSQL(TideDB.DROP_TIDE_TABLE);
            onCreate(db);
        }
    }

    //public methods
    public long insertTideItem(TideItem tideItem){
        ContentValues cv = new ContentValues();
        cv.put(TIDE_STATION, tideItem.getStation());
        cv.put(TIDE_DATE, tideItem.getDate());
        cv.putNull(TIDE_DAY);
        cv.put(TIDE_TIME, tideItem.getTime());
        cv.put(TIDE_PRED_IN_FT ,tideItem.getPred_in_ft());
        cv.putNull(TIDE_PRED_IN_CM);
        cv.put(TIDE_HIGHLOW, tideItem.getHighlow());

        this.openWritableDB();
        long rowID = db.insert(TIDE_TABLE, null, cv);
        this.closeDB();

        return rowID;
    }
    public boolean dataExists( String station){
        //return true if data IS NOT there
        this.openReadableDB();
        Cursor cursor = db.query(TIDE_TABLE, null, TIDE_STATION + " like '%" + station + "%'", null,null, null, null);
        if( cursor !=null && cursor.getCount()>0){
            Log.e("dataExists", "Data did exist");
            cursor.close();
            this.closeDB();
            return true;
        }
        cursor.close();
        this.closeDB();
        Log.e("dataExists", "Data did not exist");
        return false;
    }
    public ArrayList<TideItem> getTideItems(String date, String station){

        this.openReadableDB();
        Cursor cursor = db.query(TIDE_TABLE, null, TIDE_DATE + " like '%" +date +"%' AND " + TIDE_STATION + " like '%" + station + "%'", null,null, null, null);
        ArrayList<TideItem> returnTides = new ArrayList<TideItem>();
        while(cursor.moveToNext()){
            returnTides.add(getTideFromCursor(cursor));
        }
        if (cursor != null){
            cursor.close();
        }

        this.closeDB();
        return returnTides;
    }
    private static TideItem getTideFromCursor(Cursor cursor){
        if (cursor==null || cursor.getCount() == 0){
            return null;
        }
        else{
            try{
                TideItem tide = new TideItem(
                        cursor.getString(TIDE_STATION_ID),
                        cursor.getString(TIDE_DATE_ID),
                        null,
                        cursor.getString(TIDE_TIME_ID),
                        cursor.getString(TIDE_PRED_IN_FT_ID),
                        null,
                        cursor.getString(TIDE_HIGHLOW_ID));
                return tide;
            }catch(Exception e){
                return null;
            }
        }
    }
}
