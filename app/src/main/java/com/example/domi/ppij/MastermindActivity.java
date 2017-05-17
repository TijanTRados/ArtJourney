package com.example.domi.ppij;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;


public class MastermindActivity extends Activity {

    private float width;
    private float height;
    MastermindElement[] me;
    private int guesses;
    private MastermindElement[] guessedField;
    private TextView[] correctChoicesIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mastermind);
        guesses = 0;
        guessedField = new MastermindElement[48];
        correctChoicesIndicator = new TextView[16];

        Bundle b = getIntent().getExtras();
        final int brOfDots = b.getInt("dots");
        final int resource = b.getInt("res");

        Random rand = new Random();
        final int[] computersChoice = new int[brOfDots];
        for(int i = 0; i < brOfDots ; i++){
            computersChoice[i] = rand.nextInt(6);
        }

        final RelativeLayout rl = (RelativeLayout) findViewById(R.id.mastermidLayout);
        Display display = getWindowManager().getDefaultDisplay();
        Point displaySize = new Point();
        display.getSize(displaySize);
        width = displaySize.x;
        height = displaySize.y;
        Button checkAnswerBtn = new Button(this);
        checkAnswerBtn.setText("Verify");
        checkAnswerBtn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10.f);
        rl.addView(checkAnswerBtn);
        ViewGroup.MarginLayoutParams btnParams = (ViewGroup.MarginLayoutParams) checkAnswerBtn.getLayoutParams();
        btnParams.width = (int) (width*0.2f);
        btnParams.leftMargin = (int) (width*0.7f);
        btnParams.topMargin = (int) (height*0.8f);
        checkAnswerBtn.setLayoutParams(btnParams);
        //TODO design button properly


        final View.OnClickListener cl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i < brOfDots; i++){
                    if(me[i].getIv().getId()==v.getId()) {
                        me[i].incIndex(6);
                    }
                }
            }
        };

        me = new MastermindElement[brOfDots];
        for(int i = 0; i < brOfDots ; i++){
            me[i] = new MastermindElement(this, R.drawable.mastermind_dot_1, i, 0);
            rl.addView(me[i].getIv());
            me[i].setParams(width, height, brOfDots);
            me[i].setListener(cl);
        }

        for (int i = 0; i < 8; i++){
            correctChoicesIndicator[i*2] = new TextView(MastermindActivity.this);
            correctChoicesIndicator[i*2 + 1] = new TextView(MastermindActivity.this);
            rl.addView(correctChoicesIndicator[i*2]);
            rl.addView(correctChoicesIndicator[i*2 + 1]);
            ViewGroup.MarginLayoutParams textParams = (ViewGroup.MarginLayoutParams) correctChoicesIndicator[i*2].getLayoutParams();
            textParams.topMargin = (int)(height*(0.7f-0.07f*i));
            textParams.leftMargin = (int) (width * 0.75f);
            correctChoicesIndicator[i*2].setLayoutParams(textParams);
            textParams = (ViewGroup.MarginLayoutParams) correctChoicesIndicator[i*2 + 1].getLayoutParams();
            textParams.topMargin = (int)(height*(0.7f-0.07f*i));
            textParams.leftMargin = (int) (width * 0.85f);
            correctChoicesIndicator[i*2 +1].setLayoutParams(textParams);
        }



        checkAnswerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(guesses == 8){
                    return;
                }

                if(guessedRight(computersChoice)){
                    final imageDialog iDialog = new imageDialog(MastermindActivity.this, "You have unlocked:", resource);
                    iDialog.onDismissClose(MastermindActivity.this);
                    setResult(Activity.RESULT_OK, new Intent());
                    iDialog.show();
                    return;
                }
                int correctPlaces = 0;
                int correctColors = 0;
                boolean[] checked = new boolean[me.length];
                for(int i = 0; i < me.length; i++)
                    checked[i] = false;

                for(int i = 0; i < me.length; i++) {
                    if (me[i].getDotColor() == computersChoice[i]) {
                        correctPlaces++;
                        checked[i] = true;
                    }
                }
                for (MastermindElement aMe : me) {
                    for (int j = 0; j < computersChoice.length; j++) {
                        if (aMe.getDotColor() == computersChoice[j] && !checked[j]) {
                            correctColors++;
                            checked[j] = true;
                            break;
                        }
                    }
                }

                correctChoicesIndicator[guesses*2].setText(String.valueOf(correctColors));
                correctChoicesIndicator[guesses*2 + 1].setText(String.valueOf(correctPlaces));

                for(int i = 0; i < brOfDots; i++){
                    guessedField[guesses*6+i]=me[i];
                    guessedField[guesses*6+i].setListener(null);
                    guessedField[guesses*6+i].setTopMargin((int)(height*(0.7f-0.07f*guesses)));
                    me[i] = new MastermindElement(MastermindActivity.this, R.drawable.mastermind_dot_1, i, 0);
                    rl.addView(me[i].getIv());
                    me[i].setParams(width, height, brOfDots);
                    me[i].setListener(cl);
                }


                guesses++;
                if(guesses == 8) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MastermindActivity.this);
                    builder.setMessage("Unfortunately you have lost")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    setResult(Activity.RESULT_CANCELED, new Intent());
                                    finish();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }
        });

    }

    public boolean guessedRight(int cc[]){
        for(int i = 0; i < me.length; i++) {
            if (me[i].getDotColor() != cc[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mastermind, menu);
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
