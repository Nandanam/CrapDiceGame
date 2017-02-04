package com.example.aneeli.dice;
/**
 * Application Name: Crap game(Dice rolling game)
 * <p/>
 * Rules for playing the game:
 * Click on the ROLL button to begin rolling the dice, when the dice come to rest,if the sum of the
 * two faces is 7 or 11 , you WIN . Yay!! .But if the result is 2,3 or 12 you LOOSE. If the
 * sum is 4,5,6,8,9 or 10 you can continue playing till you get a 7,which means you loose , you WIN
 * if you roll the same number as the first throw. The minimum amount to bet is $50 until u bet ,
 * you will not be able to start rolling.
 * if you win Your money DOUBLES !! and incase you loose you LOOSE it all .. , and you can continue
 * betting with the money you won or choose a minimum of $50 from it .
 */

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

import javax.security.auth.callback.Callback;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {
    public TextView Sum;
    public Integer mSum;
    private Context myContext;
    ImageView i1;//reference to dice picture
    ImageView i2;
    TextView t1;
    Button b1;
    Button b5;
    Button b6;
    Button b7;
    Button b8;
    TextView t4;
    SoundPool mySound;
    Integer dice1, dice2;
    Random roll = new Random();    //generate random numbers
    SoundPool dice_sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
    int sound_id;        //Used to control sound stream return by SoundPool
    Handler handler;    //Post message to start roll
    int Result;
    int betAmnt = 50;
    int count = 0;
    int point;
    int pressed = 0;
    int applause;
    int lose;
    int blip;
    int yeah;
    int des;

    public void about(View v) {
        startActivity(new Intent(MainActivity.this, about.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        //Initializing Sound
        mySound = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        applause = mySound.load(this, R.raw.applause, 1);
        lose = mySound.load(this, R.raw.loser, 1);
        blip = mySound.load(this, R.raw.blip2, 1);
        des = mySound.load(this, R.raw.des, 1);

        //Initializing buttons
        b1 = (Button) findViewById(R.id.button);
        b5 = (Button) findViewById(R.id.button50);
        b6 = (Button) findViewById(R.id.button100);
        b7 = (Button) findViewById(R.id.button200);
        b8 = (Button) findViewById(R.id.buttonX);

        //Initializing text view
        t4 = (TextView) findViewById(R.id.textView4);
        t1 = (TextView) findViewById(R.id.textView3);
        sound_id = mySound.load(this, R.raw.des, 1);

        //Button for betting amount $200
        b7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                mySound.play(blip, 1, 1, 1, 0, 1);
                pressed++;
                betAmnt = 200;
                t4.setText(Integer.toString(betAmnt));
                Toast.makeText(MainActivity.this, "You bet $200", Toast.LENGTH_SHORT).show();
            }
        });

        //Button for betting $100
        b6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mySound.play(blip, 1, 1, 1, 0, 1);
                pressed++;
                betAmnt = 100;
                t4.setText(Integer.toString(betAmnt));
                Toast.makeText(MainActivity.this, "You bet $100", Toast.LENGTH_SHORT).show();
            }
        });

        //Button for betting $50
        b5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mySound.play(blip, 1, 1, 1, 0, 1);
                pressed++;
                betAmnt = 50;
                t4.setText(Integer.toString(betAmnt));
                Toast.makeText(MainActivity.this, "You bet $50", Toast.LENGTH_SHORT).show();
            }
        });


        //Button for rolling dice
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mySound.play(blip, 1, 1, 1, 0, 1);
                if (pressed >= 1) {

                    //Initializing images of dices
                    ImageView i1 = (ImageView) findViewById(R.id.i1);

                    ImageView i2 = (ImageView) findViewById(R.id.i2);

                    //Applying animations for rolling dice on button click
                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                            R.anim.clockwise);
                    i1.startAnimation(animation);
                    i2.startAnimation(animation);
                    int dice1 = roll.nextInt(6) + 1;
                    int dice2 = roll.nextInt(6) + 1;

                    //mSum is the sum of two dice values
                    int mSum = dice1 + dice2;


                    //If the value of dice is 7 or 11 you loose
                    if ((mSum == 7 || mSum == 11) && count == 0) {
                        mySound.play(yeah, 1, 1, 1, 0, 1);
                        t1.setText(String.valueOf(mSum));
                        betAmnt = betAmnt * 2;
                        t4.setText(Integer.toString(betAmnt));
                        Toast.makeText(MainActivity.this, "Yeah!!! You got : " + mSum +
                                "   You win $" + betAmnt, Toast.LENGTH_SHORT).show();

                    } else if ((mSum == 2 || mSum == 3 || mSum == 12) && count == 0) {
                        mySound.play(lose, 1, 1, 1, 0, 1);
                        t1.setText(String.valueOf(mSum));
                        Toast.makeText(MainActivity.this, "Crap!!! You got :   " + mSum +
                                "    You lose $" + betAmnt, Toast.LENGTH_SHORT).show();
                        betAmnt = 0;
                        t4.setText(Integer.toString(betAmnt));
                        t1.setText("");
                        pressed = 0; //pressed counts the number of time the button was clicked

                    } else if (count == 0) {
                        point = mSum;
                        count = 1;
                        Toast.makeText(MainActivity.this, "Dice value  : " + mSum +
                                "   continue playing", Toast.LENGTH_SHORT).show();
                        t1.setText(String.valueOf(mSum));
                    } else {
                        Toast.makeText(MainActivity.this, "Dice value  : " + mSum +
                                "   continue playing", Toast.LENGTH_SHORT).show();


                        if (mSum == 7) {
                            mySound.play(lose, 1, 1, 1, 0, 1);
                            Toast.makeText(MainActivity.this, "Crap!!! You got 7, after first throw "
                                    + point + "   You lose $" + betAmnt, Toast.LENGTH_SHORT).show();
                            t1.setText(String.valueOf(""));
                            betAmnt = 0;
                            t4.setText(Integer.toString(betAmnt));
                            t1.setText("");
                            pressed = 0;
                            count = 0;
                        } else if (point == mSum) {
                            mySound.play(applause, 1, 1, 1, 0, 1);
                            betAmnt = betAmnt * 2;
                            t4.setText(Integer.toString(betAmnt));
                            Toast.makeText(MainActivity.this, "Yeah!!! it is  :     " + mSum +
                                    "   again,You win: $" + betAmnt, Toast.LENGTH_SHORT).show();
                            count = 0;
                        }

                    }

                    //Initializing dice images for dice 1
                    if (dice1 == 1) {
                        i1.setImageResource(R.drawable.die1);
                    } else if (dice1 == 2) {
                        i1.setImageResource(R.drawable.die2);
                    } else if (dice1 == 3) {
                        i1.setImageResource(R.drawable.die3);
                    } else if (dice1 == 4) {
                        i1.setImageResource(R.drawable.die4);
                    } else if (dice1 == 5) {
                        i1.setImageResource(R.drawable.die5);
                    } else if (dice1 == 6) {
                        i1.setImageResource(R.drawable.die6);
                    }

                    {
                        //Initializing dice images for dice 2
                        if (dice2 == 1) {
                            i2.setImageResource(R.drawable.die1);
                        } else if (dice2 == 2) {
                            i2.setImageResource(R.drawable.die2);
                        } else if (dice2 == 3) {
                            i2.setImageResource(R.drawable.die3);
                        } else if (dice2 == 4) {
                            i2.setImageResource(R.drawable.die4);
                        } else if (dice2 == 5) {
                            i2.setImageResource(R.drawable.die5);
                        } else if (dice2 == 6) {
                            i2.setImageResource(R.drawable.die6);
                        }
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Bet Minimum $50 to roll the dice",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    //This is called when quit button is clicked, shows alert box with buttons
    public void showAlert(View v) {
        AlertDialog.Builder myAlert = new AlertDialog.Builder(this);
        myAlert.setMessage("Are you sure you want to quit the game?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                        System.exit(0);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }

                })
                .setTitle("Quit")
                .setIcon(R.drawable.icon)
                .create();
        myAlert.show();
    }
}





           /* //load dice sound
    sound_id=dice_sound.load(this,R.raw.shake_dice,1);
     //get reference to image widget
     dice_picture = (ImageView) findViewById(R.id.imageView);
     //link handler to callback
     handler=new Handler(callback);
 }*/
