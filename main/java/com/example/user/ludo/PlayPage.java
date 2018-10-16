package com.example.user.ludo;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class PlayPage extends AppCompatActivity {
    SQLiteDatabase sqlitedb;
    EditText etName, etScore;
    Random r;
    Button dadu;
    Button btn, btn2, btn3, btn4;
    int[] nowScore;
    int[] blueScore = {0};
    int[] redScore = {0};
    int[] yellowScore = {0};
    int[] greenScore = {0};
    int turn;
    boolean clickDadu, blueFinish, greenFinish, redFinish, yellowFinish;
    int winScore;
    String winPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_page);

        // untuk database
        dbHelper db = new dbHelper(this);
        sqlitedb = db.getWritableDatabase();
        db.onCreate(sqlitedb);

        // submit score
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("player_name", etName.getText().toString());
//        String scr = etScore.getText().toString();
//        int scr_final = Integer.parseInt(scr);
//        contentValues.put("player_score", scr_final);
//
//        long rowInserted = sqlitedb.insert("highscore", "", contentValues);
//        if (rowInserted != -1){
//            Toast.makeText(PlayPage.this, "Inserted", Toast.LENGTH_LONG).show();
//            Intent mainMenu = new Intent(PlayPage.this, MainMenu.class);
//            startActivity(mainMenu);
//        } else {
//            Toast.makeText(PlayPage.this, "Failed", Toast.LENGTH_LONG).show();
//        }

        // rolling dadu
//        Button btnDice = (Button) findViewById(R.id.rollDice);
//        btnDice.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Random rand = new Random();
//                int diceValue = rand.nextInt(6) + 1;
//                int resDice = getResources().getIdentifier("dice_" + diceValue, "drawable", "com.example.user.ludo");
//
//                ImageView imgDice = (ImageView) findViewById(R.id.dice);
//                imgDice.setImageResource(resDice);
//            }
//        });

        // passing nama player
        Intent thisIntent = getIntent();
        final String yellowPlayer = thisIntent.getStringExtra("yellowPlayer");
        final String greenPlayer = thisIntent.getStringExtra("greenPlayer");
        final String redPlayer = thisIntent.getStringExtra("redPlayer");
        final String bluePlayer = thisIntent.getStringExtra("bluePlayer");

//        TextView tvYellow = (TextView) findViewById(R.id.yellowTv);
//        TextView tvGreen = (TextView) findViewById(R.id.greenTv);
//        TextView tvRed = (TextView) findViewById(R.id.redTv);
//        TextView tvBlue = (TextView) findViewById(R.id.blueTv);

//        tvYellow.setText(yellowPlayer);
//        tvGreen.setText(greenPlayer);
//        tvRed.setText(redPlayer);
//        tvBlue.setText(bluePlayer);

        r = new Random();
        dadu = (Button) findViewById(R.id.buttondadu);
        btn = (Button) findViewById(R.id.button);//1
        btn2 = (Button) findViewById(R.id.buttongreen);//2
        btn3 = (Button) findViewById(R.id.buttonred);//3
        btn4 = (Button) findViewById(R.id.buttonyellow);//4
        nowScore = new int[1];
        nowScore[0] = 160;
        turn = 1;
        clickDadu = false;
        blueFinish = false;
        greenFinish = false;
        redFinish = false;
        yellowFinish = false;
        takeTurn(turn, btn, btn2, btn3, btn4);
        //
        final int[] start = new int[4];
        start[0] = 1;
        start[1] = 1;
        start[2] = 1;
        start[3] = 1;

        //green
        final ImageView[] green = new ImageView[4];
        green[0] = (ImageView) findViewById(R.id.g1);
        green[1] = (ImageView) findViewById(R.id.g2);
        green[2] = (ImageView) findViewById(R.id.g3);
        green[3] = (ImageView) findViewById(R.id.g4);
        final float[] posAwalxG = new float[4];
        final float[] posAwalyG = new float[4];

        posAwalxG[0] = 109;
        posAwalyG[0] = 285;
        posAwalxG[1] = 160;
        posAwalyG[1] = 285;
        posAwalxG[2] = 109;
        posAwalyG[2] = 234;
        posAwalxG[3] = 160;
        posAwalyG[3] = 234;

        final int[] startg = new int[4];
        startg[0] = 1;
        startg[1] = 1;
        startg[2] = 1;
        startg[3] = 1;

        //red
        final ImageView[] red = new ImageView[4];
        red[0] = (ImageView) findViewById(R.id.r1);
        red[1] = (ImageView) findViewById(R.id.r2);
        red[2] = (ImageView) findViewById(R.id.r3);
        red[3] = (ImageView) findViewById(R.id.r4);
        final float[] posAwalxR = new float[4];
        final float[] posAwalyR = new float[4];

        posAwalxR[0] = 102;
        posAwalyR[0] = 693;
        posAwalxR[1] = 153;
        posAwalyR[1] = 693;
        posAwalxR[2] = 102;
        posAwalyR[2] = 744;
        posAwalxR[3] = 153;
        posAwalyR[3] = 744;

        final int[] startr = new int[4];
        startr[0] = 1;
        startr[1] = 1;
        startr[2] = 1;
        startr[3] = 1;

        //yellow
        final ImageView[] yellow = new ImageView[4];
        yellow[0] = (ImageView) findViewById(R.id.y1);
        yellow[1] = (ImageView) findViewById(R.id.y2);
        yellow[2] = (ImageView) findViewById(R.id.y3);
        yellow[3] = (ImageView) findViewById(R.id.y4);

        final float[] posAwalxY = new float[4];
        final float[] posAwalyY = new float[4];

        posAwalxY[0] = 568;
        posAwalyY[0] = 285;
        posAwalxY[1] = 619;
        posAwalyY[1] = 285;
        posAwalxY[2] = 619;
        posAwalyY[2] = 234;
        posAwalxY[3] = 568;
        posAwalyY[3] = 234;

        final int[] starty = new int[4];
        starty[0] = 1;
        starty[1] = 1;
        starty[2] = 1;
        starty[3] = 1;


        //dadu
        final int[] nilai = new int[1];
        final boolean[] clicked = {false};
        final TextView a = (TextView) findViewById(R.id.text);
        clickDadu = false;
        dadu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickDadu == false) {
                    clicked[0] = false;
                    clickDadu = true;
                    nilai[0] = r.nextInt(6) + 1;

                    // gambar dice
                    int resDice = getResources().getIdentifier("dice_" + nilai[0], "drawable", "com.example.user.ludo");
                    ImageView imgDice = (ImageView) findViewById(R.id.dice);
                    imgDice.setImageResource(resDice);

                    //a.setText(nilai[0] + "");
                    boolean cek = true;

                    if (turn == 1) {
                        for (int i = 0; i < 4; i++) {
                            if (start[i] == 2) {
                                cek = false;
                            }
//                            if(start[i] == 4 || nilai[0]==6 && start[i]==4){
//                                clickDadu = false;
//                            }
                        }

                        if (cek == true && nilai[0] != 6) {
                            turn = 2;
                            takeTurn(turn, btn, btn2, btn3, btn4);
                            clickDadu = false;
                        }
                    } else if (turn == 2) {
                        for (int i = 0; i < 4; i++) {
                            if (startg[i] == 2) {
                                cek = false;
                            }
//                            if(startg[i] == 4 || nilai[0]==6 && startg[i]==4){
//                                clickDadu = false;
//                            }
                        }
                        if (cek == true && nilai[0] != 6) {
                            turn = 3;
                            takeTurn(turn, btn, btn2, btn3, btn4);
                            clickDadu = false;
                        }
                    } else if (turn == 3) {
                        for (int i = 0; i < 4; i++) {
                            if (startr[i] == 2) {
                                cek = false;
                            }
//                            if(startr[i] == 4 || nilai[0]==6 && startr[i]==4){
//                                clickDadu = false;
//                            }

                        }
                        if (cek == true && nilai[0] != 6) {
                            turn = 4;
                            takeTurn(turn, btn, btn2, btn3, btn4);
                            clickDadu = false;
                        }

                    } else if (turn == 4) {
                        for (int i = 0; i < 4; i++) {
                            if (starty[i] == 2) {
                                cek = false;
                            }
//                            if(starty[i] == 4 || nilai[0]==6 && starty[i]==4){
//                                clickDadu = false;
//                            }
                        }
                        if (cek == true && nilai[0] != 6) {
                            turn = 1;
                            takeTurn(turn, btn, btn2, btn3, btn4);
                            clickDadu = false;
                        }
                    }
                }
            }
        });
        //blue start
        final TextView scoreBlue = (TextView) findViewById(R.id.blue); //textView Score
        //final int[] blueScore = {0}; //skor

        // set textview nama player blue dan skornya
        scoreBlue.setText(bluePlayer + " : " + blueScore[0]);

        final ImageView[] blue = new ImageView[4];

        blue[0] = (ImageView) findViewById(R.id.b1);
        blue[1] = (ImageView) findViewById(R.id.b2);
        blue[2] = (ImageView) findViewById(R.id.b3);
        blue[3] = (ImageView) findViewById(R.id.b4);
        final int[] cblue = new int[1];
        blue[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cblue[0] = 0;
                clicked[0] = true;
            }
        });
        blue[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cblue[0] = 1;
                clicked[0] = true;
            }
        });
        blue[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cblue[0] = 2;
                clicked[0] = true;
            }
        });

        blue[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cblue[0] = 3;
                clicked[0] = true;
            }
        });
        final float[] posAwalx = new float[4];
        final float[] posAwaly = new float[4];

        posAwalx[0] = 619;
        posAwaly[0] = 693;
        posAwalx[1] = 619;
        posAwaly[1] = 744;
        posAwalx[2] = 568;
        posAwaly[2] = 693;
        posAwalx[3] = 568;
        posAwaly[3] = 744;

        final float[] b1posX = new float[1];
        final float[] b1posY = new float[1];
        final int[] left = new int[4];
        final int[] right = new int[4];
        final int[] up = new int[4];
        final int[] down = new int[4];

        left[0] = 3;
        right[0] = 3;
        up[0] = 3;
        down[0] = 3;

        final float[] temp = new float[1];
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clicked[0] == true && clickDadu == true) {
                    b1posX[0] = blue[cblue[0]].getX();
                    b1posY[0] = blue[cblue[0]].getY();
                    temp[0] = 0;
                    if (b1posY[0] == 489 && b1posX[0] > 415 && b1posY[0] <= 721) {
                        temp[0] = b1posX[0] - (nilai[0] * 51);
                        temp[0] = temp[0] - 415;
                        if (temp[0] < 0) {
                            temp[0] = 1;
                            if (nilai[0] == 6) {
                                clickDadu = false;
                            }
                        } else {
                            start[cblue[0]] = 2;
                            temp[0] = 0;
                        }
                    }
                    if (temp[0] == 0) {
                        if (start[cblue[0]] == 1 && nilai[0] == 6) {
                            blue[cblue[0]].setX(670);
                            blue[cblue[0]].setY(540);
                            start[cblue[0]] = 2;
                            left[cblue[0]] = 1;
                            right[cblue[0]] = 3;
                            up[cblue[0]] = 3;
                            down[cblue[0]] = 3;
                        } else if (start[cblue[0]] == 2) {
                            // a.setText("" + temp[0]);
                            for (int i = 0; i < nilai[0]; i++) {
                                b1posX[0] = blue[cblue[0]].getX();
                                b1posY[0] = blue[cblue[0]].getY();
                                if (left[cblue[0]] == 1) {
                                    b1posX[0] -= 51;
                                    if (up[cblue[0]] == 2) {
                                        b1posY[0] -= 51;
                                        up[cblue[0]] = 3;
                                    }
                                    if (b1posX[0] == 7) {
                                        left[cblue[0]] = 3;
                                        up[cblue[0]] = 1;
                                    } else if (b1posX[0] == 466 && b1posY[0] == 540) {
                                        left[cblue[0]] = 2;
                                        down[cblue[0]] = 1;
                                    } else if (b1posX[0] == 313) {
                                        left[cblue[0]] = 3;
                                        up[cblue[0]] = 1;
                                    }
                                } else if (down[cblue[0]] == 1) {
                                    if (left[cblue[0]] == 2) {
                                        b1posX[0] -= 51;
                                        b1posY[0] += 51;
                                        left[cblue[0]] = 3;
                                    } else {
                                        b1posY[0] += 51;
                                    }
                                    if (b1posY[0] == 846) {
                                        down[cblue[0]] = 3;
                                        left[cblue[0]] = 1;
                                    }
                                    if (b1posY[0] == 387) {
                                        down[cblue[0]] = 2;
                                        right[cblue[0]] = 1;
                                    }
                                    if (b1posY[0] == 489) {
                                        down[cblue[0]] = 3;
                                        left[cblue[0]] = 1;
                                    }
                                } else if (up[cblue[0]] == 1) {
                                    b1posY[0] -= 51;
                                    if (right[cblue[0]] == 2) {
                                        b1posX[0] += 51;
                                        right[cblue[0]] = 3;
                                    }
                                    if (b1posY[0] == 591) {
                                        up[cblue[0]] = 2;
                                        left[cblue[0]] = 1;
                                    }
                                    if (b1posY[0] == 132) {
                                        up[cblue[0]] = 3;
                                        right[cblue[0]] = 1;
                                    } else if (b1posY[0] == 438) {
                                        up[cblue[0]] = 3;
                                        right[cblue[0]] = 1;
                                    }
                                } else if (right[cblue[0]] == 1) {
                                    b1posX[0] += 51;
                                    if (b1posX[0] == 262) {
                                        right[cblue[0]] = 2;
                                        up[cblue[0]] = 1;
                                    }
                                    if (b1posX[0] == 415) {
                                        right[cblue[0]] = 3;
                                        down[cblue[0]] = 1;
                                    }
                                    if (down[cblue[0]] == 2) {
                                        b1posY[0] += 51;
                                        down[cblue[0]] = 3;
                                    }
                                    if (up[cblue[0]] == 2) {
                                        b1posY[0] -= 51;
                                        up[cblue[0]] = 3;
                                    }
                                    if (b1posX[0] == 721) {
                                        right[cblue[0]] = 3;
                                        down[cblue[0]] = 1;
                                    }
                                }
                                blue[cblue[0]].setX(b1posX[0]);
                                blue[cblue[0]].setY(b1posY[0]);

                                if (b1posX[0] == 415 && b1posY[0] == 489) {
                                    start[cblue[0]] = 3;
                                    blueScore[0] = blueScore[0] + nowScore[0];
                                    nowScore[0] -= 10;
                                    scoreBlue.setText(bluePlayer + " : " + blueScore[0]);

                                }
                            }

                        } else if (start[cblue[0]] == 1 && nilai[0] != 6) {
                            return;
                        }
                        for (int n = 0; n < 4; n++) {
                            if (yellow[n].getX() == blue[cblue[0]].getX() && yellow[n].getY() == blue[cblue[0]].getY()) {
                                yellow[n].setX(posAwalxY[n]);
                                yellow[n].setY(posAwalyY[n]);
                                starty[n] = 1;
                            }
                            if (green[n].getX() == blue[cblue[0]].getX() && green[n].getY() == blue[cblue[0]].getY()) {
                                green[n].setX(posAwalxG[n]);
                                green[n].setY(posAwalyG[n]);
                                startg[n] = 1;
                            }
                            if (red[n].getX() == blue[cblue[0]].getX() && red[n].getY() == blue[cblue[0]].getY()) {
                                red[n].setX(posAwalxR[n]);
                                red[n].setY(posAwalyR[n]);
                                startr[n] = 1;
                            }
                        }


                        for (int i = 0; i < 4; i++) {
                            if (start[i] == 3) {
                                blueFinish = true;
                            } else {
                                blueFinish = false;
                                break;
                            }
                        }
                        if (blueFinish == true && greenFinish == true && redFinish == true && yellowFinish == true) {
                            Toast.makeText(PlayPage.this, "FINISH BRUH", Toast.LENGTH_LONG).show();

                            // pengecekan skor paling tinggi
                            if (blueScore[0] > yellowScore[0] && blueScore[0] > redScore[0] && blueScore[0] > greenScore[0]) {
                                winScore = blueScore[0];
                                winPlayer = bluePlayer;
                            } else if (yellowScore[0] > blueScore[0] && yellowScore[0] > redScore[0] && yellowScore[0] > greenScore[0]) {
                                winScore = yellowScore[0];
                                winPlayer = yellowPlayer;
                            } else if (redScore[0] > yellowScore[0] && redScore[0] > blueScore[0] && redScore[0] > greenScore[0]) {
                                winScore = redScore[0];
                                winPlayer = redPlayer;
                            } else if (greenScore[0] > yellowScore[0] && greenScore[0] > redScore[0] && greenScore[0] > blueScore[0]) {
                                winScore = greenScore[0];
                                winPlayer = greenPlayer;
                            }

                            // tampilkan pop up pemenang dan masukkin skor ke database
                            AlertDialog.Builder winPrompt = new AlertDialog.Builder(PlayPage.this)
                                    .setCancelable(false)
                                    .setTitle("Game Finished!")
                                    .setMessage("Player " + winPlayer + " wins! \nScore : " + winScore)
                                    .setPositiveButton("Main Menu", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            ContentValues contentValues = new ContentValues();
                                            contentValues.put("player_name", winPlayer);
                                            contentValues.put("player_score", winScore);

                                            long hscoreInserted = sqlitedb.insert("highscore", "", contentValues);
                                            if (hscoreInserted != -1) {
                                                Toast.makeText(PlayPage.this, "Inserted", Toast.LENGTH_LONG).show();
                                                Intent backtoMain = new Intent(PlayPage.this, MainMenu.class);
                                                startActivity(backtoMain);
                                                finish();
                                            } else
                                                Toast.makeText(PlayPage.this, "Failed", Toast.LENGTH_LONG).show();
                                        }
                                    });

                            AlertDialog dialog = winPrompt.create();
                            dialog.show();
                        }
                    }
                    clicked[0] = false;
                    clickDadu = false;
                    if (temp[0] == 1) {
                        for (int i = 0; i < 4; i++) {
                            if (i != cblue[0]) {
                                if (start[i] == 2) {
                                    if (b1posY[0] == blue[i].getY() && b1posX[0] > blue[i].getX()) {

                                    } else {
                                        temp[0] = 0;
                                    }
                                } else if (start[i] == 1 && nilai[0] == 6) {
                                    temp[0] = 0;
                                }
                            }
                        }
                    }
                    if (nilai[0] != 6 && greenFinish == false || temp[0] == 1 && greenFinish == false) {
                        turn = 2;
                        takeTurn(turn, btn, btn2, btn3, btn4);
                    } else if (nilai[0] != 6 && redFinish == false || temp[0] == 1 && redFinish == false) {
                        turn = 3;
                        takeTurn(turn, btn, btn2, btn3, btn4);
                    } else if (nilai[0] != 6 && yellowFinish == false || temp[0] == 1 && yellowFinish == false) {
                        turn = 4;
                        takeTurn(turn, btn, btn2, btn3, btn4);
                    } else if (blueFinish == false || temp[0] == 1) {
                        turn = 1;
                        takeTurn(turn, btn, btn2, btn3, btn4);
                    }
                }
            }

        }); //blue end

        //green start
//        final int[] greenScore = {0};
        final int[] cgreen = new int[1];

        green[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cgreen[0] = 0;
                clicked[0] = true;
            }
        });
        green[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cgreen[0] = 1;
                clicked[0] = true;
            }
        });
        green[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cgreen[0] = 2;
                clicked[0] = true;
            }
        });
        green[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cgreen[0] = 3;
                clicked[0] = true;
            }
        });
        final TextView scoreGreen = (TextView) findViewById(R.id.green);

        // set textview dgn nama player green dan skornya
        scoreGreen.setText(greenPlayer + " : " + greenScore[0]);

        final float[] gposX = new float[1];
        final float[] gposY = new float[1];
        final int[] leftg = new int[4];
        final int[] rightg = new int[4];
        final int[] upg = new int[4];
        final int[] downg = new int[4];

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clicked[0] == true && clickDadu == true) {
                    gposX[0] = green[cgreen[0]].getX();
                    gposY[0] = green[cgreen[0]].getY();
                    temp[0] = 0;
                    if (gposY[0] == 489 && gposX[0] >= 7 && gposX[0] < 313) {//540
                        temp[0] = gposX[0] + (nilai[0] * 51);
                        temp[0] = temp[0] - 313;
                        if (temp[0] > 0) {
                            temp[0] = 1;
                            if (nilai[0] == 6) {
                                clickDadu = false;
                            }
                        } else {
                            startg[cgreen[0]] = 2;
                            temp[0] = 0;
                        }
                    }

                    if (temp[0] == 0) {
                        if (startg[cgreen[0]] == 1 && nilai[0] == 6) {
                            green[cgreen[0]].setX(58);
                            green[cgreen[0]].setY(438);
                            startg[cgreen[0]] = 2;
                            rightg[cgreen[0]] = 1;
                            leftg[cgreen[0]] = 3;
                            upg[cgreen[0]] = 3;
                            downg[cgreen[0]] = 3;
                        } else if (startg[cgreen[0]] == 2) {
                            for (int i = 0; i < nilai[0]; i++) {
                                gposX[0] = green[cgreen[0]].getX();
                                gposY[0] = green[cgreen[0]].getY();
                                if (leftg[cgreen[0]] == 1) {
                                    gposX[0] -= 51;
                                    if (upg[cgreen[0]] == 2) {
                                        gposY[0] -= 51;
                                        upg[cgreen[0]] = 3;
                                    }
                                    if (gposX[0] == 7) {
                                        leftg[cgreen[0]] = 3;
                                        upg[cgreen[0]] = 1;
                                    } else if (gposX[0] == 466 && gposY[0] == 540) {
                                        leftg[cgreen[0]] = 2;
                                        downg[cgreen[0]] = 1;
                                    } else if (gposX[0] == 313) {
                                        leftg[cgreen[0]] = 3;
                                        upg[cgreen[0]] = 1;
                                    }
                                } else if (downg[cgreen[0]] == 1) {
                                    if (leftg[cgreen[0]] == 2) {
                                        gposX[0] -= 51;
                                        gposY[0] += 51;
                                        leftg[cgreen[0]] = 3;
                                    } else {
                                        gposY[0] += 51;
                                    }
                                    if (gposY[0] == 846) {
                                        downg[cgreen[0]] = 3;
                                        leftg[cgreen[0]] = 1;
                                    }
                                    if (gposY[0] == 387) {
                                        downg[cgreen[0]] = 2;
                                        rightg[cgreen[0]] = 1;
                                    }
                                    if (gposY[0] == 540) {
                                        downg[cgreen[0]] = 3;
                                        leftg[cgreen[0]] = 1;
                                    }
                                } else if (upg[cgreen[0]] == 1) {
                                    gposY[0] -= 51;
                                    if (rightg[cgreen[0]] == 2) {
                                        gposX[0] += 51;
                                        rightg[cgreen[0]] = 3;
                                    }
                                    if (gposY[0] == 591) {
                                        upg[cgreen[0]] = 2;
                                        leftg[cgreen[0]] = 1;
                                    }
                                    if (gposY[0] == 489) {
                                        upg[cgreen[0]] = 3;
                                        rightg[cgreen[0]] = 1;
                                    }
                                    if (gposY[0] == 132) {
                                        upg[cgreen[0]] = 3;
                                        rightg[cgreen[0]] = 1;
                                    } else if (gposY[0] == 438) {
                                        upg[cgreen[0]] = 3;
                                        rightg[cgreen[0]] = 1;
                                    }
                                } else if (rightg[cgreen[0]] == 1) {
                                    gposX[0] += 51;
                                    if (gposX[0] == 262 && gposY[0] == 438) {
                                        rightg[cgreen[0]] = 2;
                                        upg[cgreen[0]] = 1;
                                    }
                                    if (gposX[0] == 415) {
                                        rightg[cgreen[0]] = 3;
                                        downg[cgreen[0]] = 1;
                                    }
                                    if (downg[cgreen[0]] == 2) {
                                        gposY[0] += 51;
                                        downg[cgreen[0]] = 3;
                                    }
                                    if (upg[cgreen[0]] == 2) {
                                        gposY[0] -= 51;
                                        upg[cgreen[0]] = 3;
                                    }
                                    if (gposX[0] == 721) {
                                        rightg[cgreen[0]] = 3;
                                        downg[cgreen[0]] = 1;
                                    }
                                }
                                green[cgreen[0]].setX(gposX[0]);
                                green[cgreen[0]].setY(gposY[0]);

                                if (gposX[0] == 313 && gposY[0] == 489) {
                                    startg[cgreen[0]] = 3;
                                    greenScore[0] = greenScore[0] + nowScore[0];
                                    nowScore[0] -= 10;
                                    scoreGreen.setText(greenPlayer + " : " + greenScore[0]);
                                }
                            }
                        } else if (startg[cgreen[0]] == 1 && nilai[0] != 6) {
                            return;
                        }

                        for (int n = 0; n < 4; n++) {
                            if (yellow[n].getX() == green[cgreen[0]].getX() && yellow[n].getY() == green[cgreen[0]].getY()) {
                                yellow[n].setX(posAwalxY[n]);
                                yellow[n].setY(posAwalyY[n]);
                                starty[n] = 1;
                            }
                            if (blue[n].getX() == green[cgreen[0]].getX() && blue[n].getY() == green[cgreen[0]].getY()) {
                                blue[n].setX(posAwalx[n]);
                                blue[n].setY(posAwaly[n]);
                                start[n] = 1;
                            }
                            if (red[n].getX() == green[cgreen[0]].getX() && red[n].getY() == green[cgreen[0]].getY()) {
                                red[n].setX(posAwalxR[n]);
                                red[n].setY(posAwalyR[n]);
                                startr[n] = 1;
                            }
                        }


                        for (int i = 0; i < 4; i++) {
                            if (startg[i] == 3) {
                                greenFinish = true;
                            } else {
                                greenFinish = false;
                                break;
                            }
                        }

                        if (blueFinish == true && greenFinish == true && redFinish == true && yellowFinish == true) {
                            Toast.makeText(PlayPage.this, "FINISH BRUH", Toast.LENGTH_LONG).show();

                            // pengecekan skor paling tinggi
                            if (blueScore[0] > yellowScore[0] && blueScore[0] > redScore[0] && blueScore[0] > greenScore[0]) {
                                winScore = blueScore[0];
                                winPlayer = bluePlayer;
                            } else if (yellowScore[0] > blueScore[0] && yellowScore[0] > redScore[0] && yellowScore[0] > greenScore[0]) {
                                winScore = yellowScore[0];
                                winPlayer = yellowPlayer;
                            } else if (redScore[0] > yellowScore[0] && redScore[0] > blueScore[0] && redScore[0] > greenScore[0]) {
                                winScore = redScore[0];
                                winPlayer = redPlayer;
                            } else if (greenScore[0] > yellowScore[0] && greenScore[0] > redScore[0] && greenScore[0] > blueScore[0]) {
                                winScore = greenScore[0];
                                winPlayer = greenPlayer;
                            }

                            // tampilkan pop up pemenang dan masukkin skor ke database
                            AlertDialog.Builder winPrompt = new AlertDialog.Builder(PlayPage.this)
                                    .setCancelable(false)
                                    .setTitle("Game Finished!")
                                    .setMessage("Player " + winPlayer + " wins! \nScore : " + winScore)
                                    .setPositiveButton("Main Menu", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            ContentValues contentValues = new ContentValues();
                                            contentValues.put("player_name", winPlayer);
                                            contentValues.put("player_score", winScore);

                                            long hscoreInserted = sqlitedb.insert("highscore", "", contentValues);
                                            if (hscoreInserted != -1) {
                                                Toast.makeText(PlayPage.this, "Inserted", Toast.LENGTH_LONG).show();
                                                Intent backtoMain = new Intent(PlayPage.this, MainMenu.class);
                                                startActivity(backtoMain);
                                                finish();
                                            } else
                                                Toast.makeText(PlayPage.this, "Failed", Toast.LENGTH_LONG).show();
                                        }
                                    });

                            AlertDialog dialog = winPrompt.create();
                            dialog.show();
                        }
                    }
                    clicked[0] = false;
                    clickDadu = false;

                    if (temp[0] == 1) {
                        for (int i = 0; i < 4; i++) {
                            if (i != cgreen[0]) {
                                if (gposY[0] == green[i].getY() && gposX[0] < green[i].getX()) {

                                } else {
                                    temp[0] = 0;
                                    break;
                                }
                            } else if (startg[i] == 1 && nilai[0] == 6) {
                                temp[0] = 0;
                                break;
                            }
                        }
                    }
                    if (nilai[0] != 6 && redFinish == false || temp[0] == 1 && redFinish == false) {
                        turn = 3;
                        takeTurn(turn, btn, btn2, btn3, btn4);
                    } else if (nilai[0] != 6 && yellowFinish == false || temp[0] == 1 && yellowFinish == false) {
                        turn = 4;
                        takeTurn(turn, btn, btn2, btn3, btn4);
                    } else if (nilai[0] != 6 && blueFinish == false || temp[0] == 1 && blueFinish == false) {
                        turn = 1;
                        takeTurn(turn, btn, btn2, btn3, btn4);
                    } else if (greenFinish == false || temp[0] == 1) {
                        turn = 2;
                        takeTurn(turn, btn, btn2, btn3, btn4);
                    }
                }
            }
        });
        //green end

        //red start
        final int[] cred = new int[1];
//        final int[] redScore = {0};
        red[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cred[0] = 0;
                clicked[0] = true;
            }
        });
        red[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cred[0] = 1;
                clicked[0] = true;

            }
        });
        red[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cred[0] = 2;
                clicked[0] = true;
            }
        });
        red[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cred[0] = 3;
                clicked[0] = true;
            }
        });

        final TextView scoreRed = (TextView) findViewById(R.id.red);

        // textview untuk nama dan skor player red
        scoreRed.setText(redPlayer + " : " + redScore[0]);

        final float[] rposX = new float[1];
        final float[] rposY = new float[1];
        final int[] leftr = new int[4];
        final int[] rightr = new int[4];
        final int[] upr = new int[4];
        final int[] downr = new int[4];


        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clicked[0] == true && clickDadu == true) {
                    rposX[0] = red[cred[0]].getX();
                    rposY[0] = red[cred[0]].getY();
                    temp[0] = 0;
                    if (rposX[0] == 364 && rposY[0] > 540 && rposY[0] <= 846) {//540
                        temp[0] = rposY[0] - (nilai[0] * 51);
                        temp[0] = temp[0] - 540;
                        if (temp[0] < 0) {
                            temp[0] = 1;
                            if (nilai[0] == 6) {
                                clickDadu = false;
                            }
                        } else {
                            startr[cred[0]] = 2;
                            temp[0] = 0;
                        }
                    }
                    if (temp[0] == 0) {
                        if (startr[cred[0]] == 1 && nilai[0] == 6) {
                            red[cred[0]].setX(313);
                            red[cred[0]].setY(795);
                            startr[cred[0]] = 2;
                            upr[cred[0]] = 1;
                            leftr[cred[0]] = 3;
                            rightr[cred[0]] = 3;
                            downr[cred[0]] = 3;
                        } else if (startr[cred[0]] == 2) {
                            for (int i = 0; i < nilai[0]; i++) {
                                rposX[0] = red[cred[0]].getX();
                                rposY[0] = red[cred[0]].getY();
                                if (leftr[cred[0]] == 1) {
                                    rposX[0] -= 51;
                                    if (upr[cred[0]] == 2) {
                                        rposY[0] -= 51;
                                        upr[cred[0]] = 3;
                                    }
                                    if (rposX[0] == 7) {
                                        leftr[cred[0]] = 3;
                                        upr[cred[0]] = 1;
                                    } else if (rposX[0] == 466 && rposY[0] == 540) {
                                        leftr[cred[0]] = 2;
                                        downr[cred[0]] = 1;
                                    } else if (rposX[0] == 364) {
                                        leftr[cred[0]] = 3;
                                        upr[cred[0]] = 1;
                                    }
                                } else if (downr[cred[0]] == 1) {
                                    if (leftr[cred[0]] == 2) {
                                        rposX[0] -= 51;
                                        rposY[0] += 51;
                                        leftr[cred[0]] = 3;
                                    } else {
                                        rposY[0] += 51;
                                    }
                                    if (rposY[0] == 846) {
                                        downr[cred[0]] = 3;
                                        leftr[cred[0]] = 1;
                                    }
                                    if (rposY[0] == 387) {
                                        downr[cred[0]] = 2;
                                        rightr[cred[0]] = 1;
                                    }
                                    if (rposY[0] == 540) {
                                        downr[cred[0]] = 3;
                                        leftr[cred[0]] = 1;
                                    }
                                } else if (upr[cred[0]] == 1) {
                                    rposY[0] -= 51;
                                    if (rightr[cred[0]] == 2) {
                                        rposX[0] += 51;
                                        rightr[cred[0]] = 3;
                                    }
                                    if (rposY[0] == 591 && rposX[0] == 313) {
                                        upr[cred[0]] = 2;
                                        leftr[cred[0]] = 1;
                                    }
                                    if (rposY[0] == 132) {
                                        upr[cred[0]] = 3;
                                        rightr[cred[0]] = 1;
                                    } else if (rposY[0] == 438) {
                                        upr[cred[0]] = 3;
                                        rightr[cred[0]] = 1;
                                    }
                                } else if (rightr[cred[0]] == 1) {
                                    rposX[0] += 51;
                                    if (rposX[0] == 262 && rposY[0] == 438) {
                                        rightr[cred[0]] = 2;
                                        upr[cred[0]] = 1;
                                    }
                                    if (rposX[0] == 415) {
                                        rightr[cred[0]] = 3;
                                        downr[cred[0]] = 1;
                                    }
                                    if (downr[cred[0]] == 2) {
                                        rposY[0] += 51;
                                        downr[cred[0]] = 3;
                                    }
                                    if (upr[cred[0]] == 2) {
                                        rposY[0] -= 51;
                                        upr[cred[0]] = 3;
                                    }
                                    if (rposX[0] == 721) {
                                        rightr[cred[0]] = 3;
                                        downr[cred[0]] = 1;
                                    }
                                }
                                red[cred[0]].setX(rposX[0]);
                                red[cred[0]].setY(rposY[0]);

                                if (rposX[0] == 364 && rposY[0] == 540) {
                                    startr[cred[0]] = 3;
                                    redScore[0] = redScore[0] + nowScore[0];
                                    nowScore[0] -= 10;
                                    scoreRed.setText(redPlayer + " : " + redScore[0]);
                                    //break;
                                }
                            }
                        } else if (startr[cred[0]] == 1 && nilai[0] != 6) {
                            return;
                        }
                        for (int n = 0; n < 4; n++) {
                            if (green[n].getX() == red[cred[0]].getX() && green[n].getY() == red[cred[0]].getY()) {
                                green[n].setX(posAwalxG[n]);
                                green[n].setY(posAwalyG[n]);
                                startg[n] = 1;
                            }
                            if (blue[n].getX() == red[cred[0]].getX() && blue[n].getY() == red[cred[0]].getY()) {
                                blue[n].setX(posAwalx[n]);
                                blue[n].setY(posAwaly[n]);
                                start[n] = 1;
                            }
                            if (yellow[n].getX() == red[cred[0]].getX() && yellow[n].getY() == red[cred[0]].getY()) {
                                yellow[n].setX(posAwalxY[n]);
                                yellow[n].setY(posAwalyY[n]);
                                starty[n] = 1;
                            }
                        }


                        for (int i = 0; i < 4; i++) {
                            if (startr[i] == 3) {
                                redFinish = true;
                            } else {
                                redFinish = false;
                                break;
                            }
                        }
                        if (blueFinish == true && greenFinish == true && redFinish == true && yellowFinish == true) {
                            Toast.makeText(PlayPage.this, "FINISH BRUH", Toast.LENGTH_LONG).show();

                            // pengecekan skor paling tinggi
                            if (blueScore[0] > yellowScore[0] && blueScore[0] > redScore[0] && blueScore[0] > greenScore[0]) {
                                winScore = blueScore[0];
                                winPlayer = bluePlayer;
                            } else if (yellowScore[0] > blueScore[0] && yellowScore[0] > redScore[0] && yellowScore[0] > greenScore[0]) {
                                winScore = yellowScore[0];
                                winPlayer = yellowPlayer;
                            } else if (redScore[0] > yellowScore[0] && redScore[0] > blueScore[0] && redScore[0] > greenScore[0]) {
                                winScore = redScore[0];
                                winPlayer = redPlayer;
                            } else if (greenScore[0] > yellowScore[0] && greenScore[0] > redScore[0] && greenScore[0] > blueScore[0]) {
                                winScore = greenScore[0];
                                winPlayer = greenPlayer;
                            }

                            // tampilkan pop up pemenang dan masukkin skor ke database
                            AlertDialog.Builder winPrompt = new AlertDialog.Builder(PlayPage.this)
                                    .setCancelable(false)
                                    .setTitle("Game Finished!")
                                    .setMessage("Player " + winPlayer + " wins! \nScore : " + winScore)
                                    .setPositiveButton("Main Menu", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            ContentValues contentValues = new ContentValues();
                                            contentValues.put("player_name", winPlayer);
                                            contentValues.put("player_score", winScore);

                                            long hscoreInserted = sqlitedb.insert("highscore", "", contentValues);
                                            if (hscoreInserted != -1) {
                                                Toast.makeText(PlayPage.this, "Inserted", Toast.LENGTH_LONG).show();
                                                Intent backtoMain = new Intent(PlayPage.this, MainMenu.class);
                                                startActivity(backtoMain);
                                                finish();
                                            } else
                                                Toast.makeText(PlayPage.this, "Failed", Toast.LENGTH_LONG).show();
                                        }
                                    });

                            AlertDialog dialog = winPrompt.create();
                            dialog.show();
                        }
                    }
                    clicked[0] = false;
                    clickDadu = false;

                    if (temp[0] == 1) {
                        for (int i = 0; i < 4; i++) {
                            if (i != cred[0]) {
                                if (startr[i] != 3 || red[cred[0]].getY() >= red[i].getY()) {
                                    temp[0] = 1;
                                }
                            }
                        }
                    }
                    if (temp[0] == 1) {
                        for (int i = 0; i < 4; i++) {
                            if (i != cred[0]) {
                                if (startr[i] == 2) {
                                    if (rposX[0] == red[i].getX() && rposY[0] < red[i].getY()) {

                                    } else {
                                        temp[0] = 0;
                                        break;
                                    }
                                } else if (startr[i] == 1 && nilai[0] == 6) {
                                    temp[0] = 0;
                                    break;
                                }
                            }
                        }
                    }

                    if (nilai[0] != 6 && yellowFinish == false || temp[0] == 1 && yellowFinish == false) {
                        turn = 4;
                        takeTurn(turn, btn, btn2, btn3, btn4);
                    } else if (nilai[0] != 6 && blueFinish == false || temp[0] == 1 && blueFinish == false) {
                        turn = 1;
                        takeTurn(turn, btn, btn2, btn3, btn4);
                    } else if (nilai[0] != 6 && greenFinish == false || temp[0] == 1 && greenFinish == false) {
                        turn = 2;
                        takeTurn(turn, btn, btn2, btn3, btn4);
                    } else if (redFinish == false || temp[0] == 1) {
                        turn = 3;
                        takeTurn(turn, btn, btn2, btn3, btn4);
                    }
                }
            }
        });
        //red end

        //yellow start
        final int[] cyellow = new int[1];
//        final int[] yellowScore = {0};
        yellow[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cyellow[0] = 0;
                clicked[0] = true;
            }
        });
        yellow[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cyellow[0] = 1;
                clicked[0] = true;
            }
        });
        yellow[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cyellow[0] = 2;
                clicked[0] = true;
            }
        });
        yellow[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cyellow[0] = 3;
                clicked[0] = true;
            }
        });

        final TextView scoreYellow = (TextView) findViewById(R.id.yellow);

        // textview untuk nampilkan nama dan skor player yellow
        scoreYellow.setText(yellowPlayer + " : " + yellowScore[0]);

        final float[] yposX = new float[1];
        final float[] yposY = new float[1];
        final int[] lefty = new int[4];
        final int[] righty = new int[4];
        final int[] upy = new int[4];
        final int[] downy = new int[4];


        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clicked[0] == true && clickDadu == true) {
                    yposX[0] = yellow[cyellow[0]].getX();
                    yposY[0] = yellow[cyellow[0]].getY();
                    temp[0] = 0;
                    if (yposX[0] == 364 && yposY[0] >= 132 && yposY[0] < 438) {//540
                        temp[0] = yposY[0] + (nilai[0] * 51);
                        temp[0] = temp[0] - 438;
                        if (temp[0] > 0) {
                            temp[0] = 1;
                            if (nilai[0] == 6) {
                                clickDadu = false;
                            }
                        } else {
                            starty[cyellow[0]] = 2;
                            temp[0] = 0;
                        }
                    }
                    if (temp[0] == 0) {
                        if (starty[cyellow[0]] == 1 && nilai[0] == 6) {
                            yellow[cyellow[0]].setX(415);
                            yellow[cyellow[0]].setY(183);
                            starty[cyellow[0]] = 2;
                            downy[cyellow[0]] = 1;
                            lefty[cyellow[0]] = 3;
                            righty[cyellow[0]] = 3;
                            upy[cyellow[0]] = 3;
                        } else if (starty[cyellow[0]] == 2) {
                            for (int i = 0; i < nilai[0]; i++) {
                                yposX[0] = yellow[cyellow[0]].getX();
                                yposY[0] = yellow[cyellow[0]].getY();
                                if (lefty[cyellow[0]] == 1) {
                                    yposX[0] -= 51;
                                    if (upy[cyellow[0]] == 2) {
                                        yposY[0] -= 51;
                                        upy[cyellow[0]] = 3;
                                    }
                                    if (yposX[0] == 7) {
                                        lefty[cyellow[0]] = 3;
                                        upy[cyellow[0]] = 1;
                                    } else if (yposX[0] == 466 && yposY[0] == 540) {
                                        lefty[cyellow[0]] = 2;
                                        downy[cyellow[0]] = 1;
                                    } else if (yposX[0] == 313) {
                                        lefty[cyellow[0]] = 3;
                                        upy[cyellow[0]] = 1;
                                    }
                                } else if (downy[cyellow[0]] == 1) {
                                    if (lefty[cyellow[0]] == 2) {
                                        yposX[0] -= 51;
                                        yposY[0] += 51;
                                        lefty[cyellow[0]] = 3;
                                    } else {
                                        yposY[0] += 51;
                                    }
                                    if (yposY[0] == 846) {
                                        downy[cyellow[0]] = 3;
                                        lefty[cyellow[0]] = 1;
                                    }
                                    if (yposY[0] == 387 && yposX[0] == 415) {
                                        downy[cyellow[0]] = 2;
                                        righty[cyellow[0]] = 1;
                                    }
                                    if (yposY[0] == 540) {
                                        downy[cyellow[0]] = 3;
                                        lefty[cyellow[0]] = 1;
                                    }
                                } else if (upy[cyellow[0]] == 1) {
                                    yposY[0] -= 51;
                                    if (righty[cyellow[0]] == 2) {
                                        yposX[0] += 51;
                                        righty[cyellow[0]] = 3;
                                    }
                                    if (yposY[0] == 591) {
                                        upy[cyellow[0]] = 2;
                                        lefty[cyellow[0]] = 1;
                                    }
                                    if (yposY[0] == 132) {
                                        upy[cyellow[0]] = 3;
                                        righty[cyellow[0]] = 1;
                                    } else if (yposY[0] == 438) {
                                        upy[cyellow[0]] = 3;
                                        righty[cyellow[0]] = 1;
                                    }
                                } else if (righty[cyellow[0]] == 1) {
                                    yposX[0] += 51;
                                    if (yposX[0] == 262 && yposY[0] == 438) {
                                        righty[cyellow[0]] = 2;
                                        upy[cyellow[0]] = 1;
                                    }
                                    if (yposX[0] == 364) {
                                        righty[cyellow[0]] = 3;
                                        downy[cyellow[0]] = 1;
                                    }
                                    if (downy[cyellow[0]] == 2) {
                                        yposY[0] += 51;
                                        downy[cyellow[0]] = 3;
                                    }
                                    if (upy[cyellow[0]] == 2) {
                                        yposY[0] -= 51;
                                        upy[cyellow[0]] = 3;
                                    }
                                    if (yposX[0] == 721) {
                                        righty[cyellow[0]] = 3;
                                        downy[cyellow[0]] = 1;
                                    }
                                }
                                yellow[cyellow[0]].setX(yposX[0]);
                                yellow[cyellow[0]].setY(yposY[0]);

                                if (yposX[0] == 364 && yposY[0] == 438) {
                                    starty[cyellow[0]] = 3;
                                    yellowScore[0] = yellowScore[0] + nowScore[0];
                                    nowScore[0] -= 10;
                                    scoreYellow.setText(yellowPlayer + " : " + yellowScore[0]);
                                }
                            }
                        } else if (starty[cyellow[0]] == 1 && nilai[0] != 6) {
                            return;
                        }
                        for (int n = 0; n < 4; n++) {
                            if (green[n].getX() == yellow[cyellow[0]].getX() && green[n].getY() == yellow[cyellow[0]].getY()) {
                                green[n].setX(posAwalxG[n]);
                                green[n].setY(posAwalyG[n]);
                                startg[n] = 1;
                            }
                            if (blue[n].getX() == yellow[cyellow[0]].getX() && blue[n].getY() == yellow[cyellow[0]].getY()) {
                                blue[n].setX(posAwalx[n]);
                                blue[n].setY(posAwaly[n]);
                                start[n] = 1;
                            }
                            if (red[n].getX() == yellow[cyellow[0]].getX() && red[n].getY() == yellow[cyellow[0]].getY()) {
                                red[n].setX(posAwalxR[n]);
                                red[n].setY(posAwalyR[n]);
                                startr[n] = 1;
                            }
                        }

                        for (int i = 0; i < 4; i++) {
                            if (starty[i] == 3) {
                                yellowFinish = true;
                            } else {
                                yellowFinish = false;
                                break;
                            }
                        }

                        if (blueFinish == true && greenFinish == true && redFinish == true && yellowFinish == true) {
                            Toast.makeText(PlayPage.this, "FINISH BRUH", Toast.LENGTH_LONG).show();

                            // pengecekan skor paling tinggi
                            if (blueScore[0] > yellowScore[0] && blueScore[0] > redScore[0] && blueScore[0] > greenScore[0]) {
                                winScore = blueScore[0];
                                winPlayer = bluePlayer;
                            } else if (yellowScore[0] > blueScore[0] && yellowScore[0] > redScore[0] && yellowScore[0] > greenScore[0]) {
                                winScore = yellowScore[0];
                                winPlayer = yellowPlayer;
                            } else if (redScore[0] > yellowScore[0] && redScore[0] > blueScore[0] && redScore[0] > greenScore[0]) {
                                winScore = redScore[0];
                                winPlayer = redPlayer;
                            } else if (greenScore[0] > yellowScore[0] && greenScore[0] > redScore[0] && greenScore[0] > blueScore[0]) {
                                winScore = greenScore[0];
                                winPlayer = greenPlayer;
                            }

                            // tampilkan pop up pemenang dan masukkin skor ke database
                            AlertDialog.Builder winPrompt = new AlertDialog.Builder(PlayPage.this)
                                    .setCancelable(false)
                                    .setTitle("Game Finished!")
                                    .setMessage("Player " + winPlayer + " wins! \nScore : " + winScore)
                                    .setPositiveButton("Main Menu", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            ContentValues contentValues = new ContentValues();
                                            contentValues.put("player_name", winPlayer);
                                            contentValues.put("player_score", winScore);

                                            long hscoreInserted = sqlitedb.insert("highscore", "", contentValues);
                                            if (hscoreInserted != -1) {
                                                Toast.makeText(PlayPage.this, "Inserted", Toast.LENGTH_LONG).show();
                                                Intent backtoMain = new Intent(PlayPage.this, MainMenu.class);
                                                startActivity(backtoMain);
                                                finish();
                                            } else
                                                Toast.makeText(PlayPage.this, "Failed", Toast.LENGTH_LONG).show();
                                        }
                                    });

                            AlertDialog dialog = winPrompt.create();
                            dialog.show();
                        }
                    }

                    if (temp[0] == 1) {
                        for (int i = 0; i < 4; i++) {
                            if (i != cyellow[0]) {

                                if (starty[i] == 2) {
                                    if (yposX[0] == yellow[i].getX() && yposY[0] > yellow[i].getY()) {

                                    } else {
                                        temp[0] = 0;
                                        break;
                                    }
                                } else if (starty[i] == 1 && nilai[0] == 6) {
                                    temp[0] = 0;
                                    break;
                                }
                            }
                        }
                    }

                    if (nilai[0] != 6 && blueFinish == false || temp[0] == 1 && blueFinish == false) {
                        turn = 1;
                        takeTurn(turn, btn, btn2, btn3, btn4);
                    } else if (nilai[0] != 6 && greenFinish == false || temp[0] == 1 && greenFinish == false) {
                        turn = 2;
                        takeTurn(turn, btn, btn2, btn3, btn4);
                    } else if (nilai[0] != 6 && redFinish == false || temp[0] == 1 && redFinish == false) {
                        turn = 3;
                        takeTurn(turn, btn, btn2, btn3, btn4);
                    } else if (yellowFinish == false || temp[0] == 1) {
                        turn = 4;
                        takeTurn(turn, btn, btn2, btn3, btn4);
                    }
                    clicked[0] = false;
                    clickDadu = false;
                }
            }
        });
    }

    // kalau tekan back ke main menu
    @Override
    public void onBackPressed() {
        finish();
        Intent backBtn = new Intent(PlayPage.this, MainMenu.class);
        startActivity(backBtn);
    }

    public void takeTurn(int turn, Button button1, Button button2, Button button3, Button button4) {
        if (turn == 1) {
            button1.setEnabled(true);
            button2.setEnabled(false);
            button3.setEnabled(false);
            button4.setEnabled(false);
        }
        if (turn == 2) {
            button1.setEnabled(false);
            button2.setEnabled(true);
            button3.setEnabled(false);
            button4.setEnabled(false);
        }
        if (turn == 3) {
            button1.setEnabled(false);
            button2.setEnabled(false);
            button3.setEnabled(true);
            button4.setEnabled(false);
        }
        if (turn == 4) {
            button1.setEnabled(false);
            button2.setEnabled(false);
            button3.setEnabled(false);
            button4.setEnabled(true);
        }
    }
}