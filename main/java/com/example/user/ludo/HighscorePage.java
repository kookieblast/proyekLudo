package com.example.user.ludo;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class HighscorePage extends AppCompatActivity {
    SQLiteDatabase sqlitedb;
    public static ArrayList<String> p_name;
    public static ArrayList<Integer> p_id, p_score;
    ListView hscore_lv;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore_page);

        p_id = new ArrayList<Integer>();
        p_name = new ArrayList<String>();
        p_score = new ArrayList<Integer>();

        dbHelper db = new dbHelper(this);
        sqlitedb = db.getWritableDatabase();
        db.onCreate(sqlitedb);

        Cursor cursor = db.getAllData(sqlitedb);
        if(cursor.moveToFirst()){
            do{
                p_id.add(cursor.getInt(0));
                p_name.add(cursor.getString(1));
                p_score.add(cursor.getInt(2));
            } while (cursor.moveToNext());
        }

        context = this;
        hscore_lv = (ListView) findViewById(R.id.hscores);
        hscore_lv.setAdapter(new customAdapter(this, p_id, p_score, p_name));

        Button btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainMenu = new Intent(HighscorePage.this, MainMenu.class);
                startActivity(mainMenu);
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent backBtn = new Intent(HighscorePage.this, MainMenu.class);
        startActivity(backBtn);
    }
}