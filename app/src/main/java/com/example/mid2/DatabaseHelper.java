package com.example.mid2;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by Mitch on 2016-05-13.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Company.db";
    public static final String TABLE_NAME = "Employees";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_LASTNAME = "LASTNAME";
    public static final String COLUMN_NATIONALID = "NATIONALID";


    /* Constructor */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    /* Code runs automatically when the dB is created */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME +
                " (ID INTEGER PRIMARY KEY, " +
                " NAME TEXT,LASTNAME TEXT,NATIONALID INTEGER)";
        db.execSQL(createTable);
    }

    /* Every time the dB is updated (or upgraded) */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    /* Basic function to add data. REMEMBER: The fields
       here, must be in accordance with those in
       the onCreate method above.
    */
    public boolean addData(String ID, String Name, String lastname, String nationalid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, ID);
        contentValues.put(COLUMN_NAME, Name );
        contentValues.put(COLUMN_LASTNAME, lastname);
        contentValues.put(COLUMN_NATIONALID, nationalid);


        long result = db.insert(TABLE_NAME, null, contentValues);

        //if data are inserted incorrectly, it will return -1
        if(result == -1) {return false;} else {return true;}
    }


    public boolean deleteData(String productID) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COLUMN_ID + "=" + productID, null) > 0;
    }
    public Cursor ViewEmployee(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        return data;
    }
}