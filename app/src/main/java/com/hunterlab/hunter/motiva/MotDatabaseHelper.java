package com.hunterlab.hunter.motiva;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by hunter on 13/9/17.
 */

public class MotDatabaseHelper  extends SQLiteOpenHelper {

    public String DBPath;
    public static final String DBName = "hey.db";
    public static final int version = '1';
    public static String tableName = "babe";


    public MotDatabaseHelper(Context context) {
        super(context, DBName, null, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
// TODO Auto-generated method stub


        db.execSQL("CREATE TABLE IF NOT EXISTS " +
                tableName +
                " ( ID INTEGER PRIMARY KEY , Last INTEGER );");

        db.execSQL("INSERT INTO "+tableName+" values( 0 , 0 )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// TODO Auto-generated method stub

    }


public void updateLastValue(SQLiteDatabase db,int number1)
{
    db.execSQL("UPDATE " + tableName + " SET Last=" + number1+" WHERE ID=0");
}


}
