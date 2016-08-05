package com.becky.explo.blindtext;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Time;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    TextView randomSentencetextView;
    TextView timerTextView;
    EditText hiddenSentenceEditText;
    Button  buttonSubmit;
    private Timer t;
    private int TimeCounter = 3;
    private boolean reading;



    //runs without a timer by reposting this handler at the end of the runnable
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
           TimeCounter--;

            timerTextView.setText(String.valueOf(TimeCounter));

            if (TimeCounter == 0){
                if (reading) {
                    TimeCounter = 11;
                    randomSentencetextView.setVisibility(View.GONE);
                    hiddenSentenceEditText.setVisibility(View.VISIBLE);
                    buttonSubmit.setVisibility(View.VISIBLE);
                    reading = false;

                }else{
                    //put screen change
                    //startActivity(new Intent(MainActivity.this, Results.class));
                    Intent intentresults =
                            new Intent(MainActivity.this, Results.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("original", randomSentencetextView.getText().toString());
                    bundle.putString("submission", hiddenSentenceEditText.getText().toString());
                    intentresults.putExtras(bundle);
                    timerHandler.removeCallbacks(timerRunnable);
                    startActivity(intentresults);
                }
            }



            timerHandler.postDelayed(this, 1000);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        randomSentencetextView = (TextView)findViewById(R.id.randomSentenceTextView);
        randomSentencetextView.setVisibility(View.INVISIBLE);
        hiddenSentenceEditText = (EditText) findViewById(R.id.hiddenSentenceEditText);
        buttonSubmit = (Button)findViewById(R.id.buttonSubmit);
        timerTextView = (TextView) findViewById(R.id.timerTextView);

        hiddenSentenceEditText.setVisibility(View.GONE);
        buttonSubmit.setVisibility(View.GONE);
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("Welcome to Blind Text!");
        builder1.setMessage("As soon as you press the button, the timer will start. You have 3 seconds to read, and then re-type it as fast as possible. If you are unable to finish in the 10 seconds then you will be scored on what you did write.");
        builder1.setCancelable(false);

        builder1.setPositiveButton("Start", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //This will run when you tap the Okay button write any code here
                timerHandler.postDelayed(timerRunnable,0);
                reading = true;
                dialog.cancel();
            }
        });

        AlertDialog alert11 = builder1.create();
        alert11.show();



        String[]randomsentences = {
                "I really like to eat pizza and popcorn and chocolate cake!",
                "Many dogs like long walks in the park and eating treats.",
                "On Friday I went to pick up mt paycheck",
                "The banana is no longer ripe",
                "Sally went to the beach on Sunday and got a really bad sunburn",
                "Instead of going to the dentist went went to the candy store",
                "This weekend I am going to see seven movies",
                "The flower blooms in the garden in the spring"
        };
                 Random rand = new Random();
                int randomNumber = rand.nextInt(8);
                randomSentencetextView.setText(randomsentences[randomNumber]);



        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentresults =
                new Intent(MainActivity.this, Results.class);
                Bundle bundle = new Bundle();
                bundle.putString("original", randomSentencetextView.getText().toString());
                bundle.putString("submission", hiddenSentenceEditText.getText().toString());
                intentresults.putExtras(bundle);
                timerHandler.removeCallbacks(timerRunnable);
                startActivity(intentresults);
            }
        });

            //timerHandler.postDelayed(timerRunnable,500);

    }

}
