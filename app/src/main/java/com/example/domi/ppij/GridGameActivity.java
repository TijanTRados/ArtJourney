package com.example.domi.ppij;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.Random;


public class GridGameActivity extends Activity {

    private SliderElement[] elements;
    private SliderElement Element;

    private int size;
    private float width;
    private float height;
    private int resource;
    public int b;

    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

    private imageDialog iDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_game);

        //Layout building
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.Main);
        Bundle bundle = getIntent().getExtras();
        size = bundle.getInt("size");
        resource=bundle.getInt("res");
        Display display = getWindowManager().getDefaultDisplay();
        Point displaySize = new Point();
        display.getSize(displaySize);
        width = displaySize.x;
        height = displaySize.y;

        //Image dialog constructor
        iDialog = new imageDialog(GridGameActivity.this, "You have unlocked:", resource - 1);
        iDialog.onDismissClose(this);

        //Call constructor
        elements= new SliderElement[size*size];
        int[] indexArray = new int[size*size];
        for(int i = 0; i < size*size;i++){
            indexArray[i]=i;
        }

        //Randomizes the position of images
        Random rnd = new Random();
        for (int i = indexArray.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            int a = indexArray[index];
            indexArray[index] = indexArray[i];
            indexArray[i] = a;
        }

        //Swipe detector initializer
        final GestureDetector gdt = new GestureDetector(this, new GestureListener());
        View.OnTouchListener tl = new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View view, final MotionEvent event) {
                b = view.getId();
                gdt.onTouchEvent(event);
                return true;
            }

        };

        //Image view parameter setting
        for (int i = 0; i < indexArray.length;i++){
            elements[indexArray[i]]=new SliderElement(this,resource+i,i, indexArray[i]);
            rl.addView(elements[indexArray[i]].getIv());
            elements[indexArray[i]].setParams(width,height,size);
            elements[indexArray[i]].setListener(tl);
        }

    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                // Right to left
                Log.d("R", "R->L");

                for (int i = 0; i < size * size; i++) {
                    if (elements[i].getIv().getId() == b) {
                        b = i;
                        break;
                    }
                }
                for (int i = 0; i < size * size; i++) {
                    if ((elements[i].getPosition() % size) == (elements[b].getPosition() % size)) {
                        Element=elements[i];
                        elements[i]=elements[(i+size)%(size*size)];
                        elements[i].setPosition(i);
                        elements[i].updateParams(width, height, size);
                        for(int z = 1; z < size - 1; z++){
                            elements[(i+(size*z))%(size*size)]=elements[(i+(size*(z+1)))%(size*size)];
                            elements[(i+(size*z))%(size*size)].setPosition((i+(size*z))%(size*size));
                            elements[(i+(size*z))%(size*size)].updateParams(width, height, size);
                        }
                        elements[(i+(size*(size-1)))%(size*size)]=Element;
                        elements[(i+(size*(size-1)))%(size*size)].setPosition((i+6)%9);
                        elements[(i+(size*(size-1)))%(size*size)].updateParams(width, height, size);
                        break;
                    }
                }

            } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                // Left to right
                Log.d("R", "L->R");

                for (int i = 0; i < size * size; i++) {
                    if (elements[i].getIv().getId() == b) {
                        b = i;
                        break;
                    }
                }
                for (int i = 0; i < size * size; i++) {
                    if ((elements[i].getPosition() % size) == (elements[b].getPosition() % size)) {
                        Element=elements[(i+(size*(size-1)))%(size*size)];
                        elements[(i+(size*(size-1)))%(size*size)]=elements[(i+(size*(size-2)))%(size*size)];
                        elements[(i+(size*(size-1)))%(size*size)].setPosition((i+(size*(size-1)))%(size*size));
                        elements[(i+(size*(size-1)))%(size*size)].updateParams(width, height, size);
                        for(int z = size-2; z > 0; z--){
                            elements[(i+(size*z))%(size*size)]=elements[(i+(size*(z-1)))%(size*size)];
                            elements[(i+(size*z))%(size*size)].setPosition((i+(size*z))%(size*size));
                            elements[(i+(size*z))%(size*size)].updateParams(width, height, size);
                        }
                        elements[i]=Element;
                        elements[i].setPosition(i);
                        elements[i].updateParams(width, height, size);
                        break;
                    }
                }

            } else if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                // Bottom to top
                Log.d("R", "D->G");

                for (int i = 0; i < size * size; i++) {
                    if (elements[i].getIv().getId() == b) {
                        b = i;
                        break;
                    }
                }
                for (int i = 0; i <= size * (size-1); i=i+size) {
                    if ((elements[i].getPosition() / size) == (elements[b].getPosition() / size)) {
                        Element=elements[i];
                        elements[i]=elements[(i+1)%size+i];
                        elements[i].setPosition(i);
                        elements[i].updateParams(width, height, size);
                        for(int z = 1; z < size - 1; z++){
                            elements[(i+z)%size+i]=elements[(i+z+1)%size+i];
                            elements[(i+z)%size+i].setPosition((i+z)%size+i);
                            elements[(i+z)%size+i].updateParams(width, height, size);
                        }
                        elements[(i+size -1)%size+i]=Element;
                        elements[(i+size -1)%size+i].setPosition((i+size -1)%size+i);
                        elements[(i+size -1)%size+i].updateParams(width, height, size);
                        break;

                    }
                }

            } else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                // Top to bottom
                Log.d("R", "G->D");

                for (int i = 0; i < size * size; i++) {
                    if (elements[i].getIv().getId() == b) {
                        b = i;
                        break;
                    }
                }
                for (int i = 0; i <= size * (size-1); i=i+size) {
                    if ((elements[i].getPosition() / size) == (elements[b].getPosition() / size)) {
                        Element=elements[(i+2)%3+i];
                        elements[(i+size -1)%size+i]=elements[(i+size -2)%size+i];
                        elements[(i+size -1)%size+i].setPosition((i+size -1)%size+i);
                        elements[(i+size -1)%size+i].updateParams(width, height, size);
                        for(int z = size-2; z > 0; z--){
                            elements[(i+z)%size+i]=elements[(i+z-1)%size+i];
                            elements[(i+z)%size+i].setPosition((i+z)%size+i);
                            elements[(i+z)%size+i].updateParams(width, height, size);
                        }
                        elements[i]=Element;
                        elements[i].setPosition(i);
                        elements[i].updateParams(width, height, size);
                        break;

                    }
                }
            }
            if(gameFinished()){
                setResult(Activity.RESULT_OK, new Intent());
                iDialog.show();
            }
            return false;

        }
    }

    //Checks if the game is finished
    private boolean gameFinished(){
        for(int i = 0; i < size*size; i++){
            if(elements[i].getIndex()!=i){
                return false;
            }
        }
        return true;
    }

}
