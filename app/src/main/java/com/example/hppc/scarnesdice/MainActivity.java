package com.example.hppc.scarnesdice;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button roll,hold,reset;
    ImageView imageView;
    TextView play1sc,play2sc, turnText,sc;
    int turnsc,score1,score2;
    boolean turn;
    View layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        layout = findViewById(R.id.clayout);
        turn = true;
        turnsc = score2 = score1 = 0;

        play1sc = (TextView) findViewById(R.id.textView2);
        play2sc = (TextView) findViewById(R.id.textView);
        turnText = (TextView) findViewById(R.id.textView3);
        sc = (TextView) findViewById(R.id.textView4);
        imageView = (ImageView) findViewById(R.id.imageView);
        roll = (Button) findViewById(R.id.button2);
        hold = (Button) findViewById(R.id.button3);
        reset = (Button) findViewById(R.id.button);

        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Random random = new Random();
                int num = random.nextInt(6) + 1;
                imageSet(num);
                if(num != 1) {
                    turnsc += num;
                    sc.setText("Turn Score : " + turnsc);
                }
                else{
                    turnsc = 0;
                    sc.setText("Turn Score : 0");
                    changePlayer(turn);
                }
            }
        });

        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setScore();
            }
        });


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });
    }

    private void setScore() {
        if(turn){
            score1 += turnsc;
            play1sc.setText("Player 1 Score : " + score1);
            changePlayer(turn);
            if (score1 >= 100){
                Snackbar.make(layout,"Player 1 wins !!",Snackbar.LENGTH_LONG).show();
                reset();
            }
        }
        else{
            score2 += turnsc;
            play2sc.setText("Player 2 Score : " + score2);
            changePlayer(turn);
            if (score2 >= 100){
                Snackbar.make(layout,"Player 2 wins !!",Snackbar.LENGTH_LONG).show();
                reset();
            }
        }
        turnsc = 0;
        sc.setText("Turn Score : 0");
    }

    private void changePlayer(boolean t) {
        turn = !t;
        if (turn){
            turnText.setText("Player 1's Turn");
        }
        else{
            turnText.setText("Player 2's Turn");
        }
    }

    private void reset() {
        turnsc = score1 = score2 = 0;
        play1sc.setText("Player 1 Score : 0");
        play2sc.setText("player 2 Score : 0");
        sc.setText("Turn score : 0");
        changePlayer(false);
    }

    private void imageSet(int n) {
        switch(n){
            case 1: imageView.setImageResource(R.drawable.dice1);
                break;
            case 2: imageView.setImageResource(R.drawable.dice2);
                break;
            case 3: imageView.setImageResource(R.drawable.dice3);
                break;
            case 4: imageView.setImageResource(R.drawable.dice4);
                break;
            case 5: imageView.setImageResource(R.drawable.dice5);
                break;
            case 6: imageView.setImageResource(R.drawable.dice6);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
