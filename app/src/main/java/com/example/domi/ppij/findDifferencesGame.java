package com.example.domi.ppij;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class findDifferencesGame extends Activity {
   private TextView tv;
    private ArrayList<ArrayList<Integer>> Coord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_differences_game);

        Bundle b = getIntent().getExtras();
        final int firstImRes = b.getInt("firstImage");
        int secondImRes = b.getInt("secondImage");
        final int brOfMistakes = b.getInt("misstakes");

        Coord = new ArrayList<>();

        ImageView firstImage = (ImageView) findViewById(R.id.firstImage);
        ImageView secondImage = (ImageView) findViewById(R.id.secondImage);
        firstImage.setImageResource(firstImRes);
        secondImage.setImageResource(secondImRes);

        Display display = getWindowManager().getDefaultDisplay();
        Point displaySize = new Point();
        display.getSize(displaySize);
        int width = displaySize.x;
        int height = displaySize.y;

        ArrayList<Integer> temp;
        for(int i = 0; i < brOfMistakes; i++){
            temp = new ArrayList<>();
            temp.add((int)((b.getFloat("miss" + String.valueOf(i + 1)))* width * 0.9));
            temp.add((int)((b.getFloat("miss" + String.valueOf(i + 1)) * 100 % 1)* height * 0.4));
            Coord.add(i,temp);
        }

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) firstImage.getLayoutParams();
        params.height = (int)(height * 0.4);
        params.width = (int)(height * 0.9);
        firstImage.setLayoutParams(params);
        firstImage.setScaleType(ImageView.ScaleType.FIT_XY);
        params = (RelativeLayout.LayoutParams) secondImage.getLayoutParams();
        params.height = (int)(height * 0.4);
        params.width = (int)(height * 0.9);
        params.topMargin = (int)(height * 0.02);
        secondImage.setLayoutParams(params);
        secondImage.setScaleType(ImageView.ScaleType.FIT_XY);

        tv = (TextView) findViewById(R.id.fdTextView);
        tv.setText("Found 0 of " + String.valueOf(brOfMistakes));


        final View.OnTouchListener tl = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                for(int i = 0; i < Coord.size(); i++) {
                    if(((int)event.getX()> Coord.get(i).get(0) -10 && (int)event.getX()<Coord.get(i).get(0)+10)
                            && ((int)event.getY()>Coord.get(i).get(1)-10 && (int)event.getY()<Coord.get(i).get(1)+10)){
                        Coord.remove(i);
                        tv.setText("Found "+ String.valueOf(brOfMistakes-Coord.size()) +" of " + String.valueOf(brOfMistakes));
                        if(Coord.isEmpty()){
                            final imageDialog iDialog = new imageDialog(findDifferencesGame.this, "You have unlocked:", firstImRes);
                            iDialog.onDismissClose(findDifferencesGame.this);
                            //TODO set that thingy
                            iDialog.show();
                        }
                        return  false;
                    }
                }
                return false;
            }
        };

        firstImage.setOnTouchListener(tl);
        secondImage.setOnTouchListener(tl);

    }



}
