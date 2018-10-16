package com.example.user.ludo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbHelper extends SQLiteOpenHelper{
    private static final int DBVersion = (int)1.1;
    private static final String DBName = "ludo";
    private static final String TableName = "highscore";

    private static final String player_id = "player_id";
    private static final String player_name = "player_name";
    private static final String player_score = "player_score";

    public dbHelper(Context context) {
        super(context, DBName, null, DBVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CreateTable = "CREATE TABLE if not exists " + TableName + "(" +
                player_id + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                player_name + " TEXT, " +
                player_score + " TEXT)";
        db.execSQL(CreateTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        db.execSQL("DROP TABLE if EXISTS " + TableName);
        onCreate(db);
    }

    public Cursor getAllData(SQLiteDatabase db){
        return db.query(TableName, new String[] {player_id, player_name, player_score}, null, null, null, null, player_score + " DESC");
    }
}