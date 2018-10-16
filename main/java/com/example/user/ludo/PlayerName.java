package com.example.user.ludo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PlayerName extends AppCompatActivity {
    EditText etYellow, etGreen, etRed, etBlue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_name);

        etYellow = (EditText) findViewById(R.id.yellowName);
        etGreen = (EditText) findViewById(R.id.greenName);
        etRed = (EditText) findViewById(R.id.redName);
        etBlue = (EditText) findViewById(R.id.blueName);

        Button btnSubmit = (Button) findViewById(R.id.submitName);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toPlayPage = new Intent(PlayerName.this, PlayPage.class);
                toPlayPage.putExtra("yellowPlayer", etYellow.getText().toString());
                toPlayPage.putExtra("greenPlayer", etGreen.getText().toString());
                toPlayPage.putExtra("redPlayer", etRed.getText().toString());
                toPlayPage.putExtra("bluePlayer", etBlue.getText().toString());
                startActivity(toPlayPage);
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent backBtn = new Intent(PlayerName.this, MainMenu.class);
        startActivity(backBtn);
    }
}