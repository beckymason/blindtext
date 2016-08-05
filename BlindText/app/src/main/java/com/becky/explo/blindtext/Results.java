package com.becky.explo.blindtext;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Results extends AppCompatActivity {

    TextView originalTextView;
    TextView submissionTextView;
    Button buttonTryAgain;
    TextView scoreTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        originalTextView = (TextView)findViewById(R.id.OriginalTextView);
        submissionTextView = (TextView)findViewById(R.id.submissionTextView);
        buttonTryAgain = (Button)findViewById(R.id.buttonTryAgain);
        scoreTextView = (TextView)findViewById(R.id.scoreTextView);

        Intent intentresults = getIntent();

        Bundle bundleresults = intentresults.getExtras();

        String original = bundleresults.getString("original");
        String submission = bundleresults.getString("submission");

       originalTextView.setText( original );
        submissionTextView.setText( submission);
        int score = 0;

        for (int i = 0; i < original.length(); i++)
        {

            if (i < submission.length())
            {
                if (original.charAt(i) == submission.charAt(i)) score++;
            }
        }

        scoreTextView.setText(String.valueOf(score));

        buttonTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Results.this, MainActivity.class));
            }
        });
    }
}
