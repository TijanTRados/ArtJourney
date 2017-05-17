package com.example.domi.ppij;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Random;


public class
        SliderGameActivity extends Activity {

    private SliderElement[] elements;
    private int size;
    private float width;
    private float height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider_game);

        RelativeLayout rl = (RelativeLayout) findViewById(R.id.sliderLayout);
        Bundle b = getIntent().getExtras();
        size = b.getInt("size");
        int resource=b.getInt("res");
        Display display = getWindowManager().getDefaultDisplay();
        Point displaySize = new Point();
        display.getSize(displaySize);
        width = displaySize.x;
        height = displaySize.y;

        ImageView info = (ImageView) findViewById(R.id.infoImage);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) info.getLayoutParams();
        params.height = (int) (0.1 * height);
        params.width = params.height;
        info.setLayoutParams(params);
        info.setAlpha(0.25f);

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SliderGameActivity.this);
                builder.setMessage("Touch the image to move it to the blank field!")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        ImageView back = (ImageView) findViewById(R.id.backImage);
        params = (ViewGroup.MarginLayoutParams) back.getLayoutParams();
        params.height = (int) (0.1 * height);
        params.width = params.height * 2;
        back.setLayoutParams(params);
        //back.setAlpha(0.25f);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                SliderGameActivity.this.finish();
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(SliderGameActivity.this);
                builder.setMessage("Are you sure you want to quit?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
        });

        final imageDialog iDialog = new imageDialog(SliderGameActivity.this, "You have unlocked:", resource-1);
        iDialog.onDismissClose(this);

        elements= new SliderElement[size*size];
        int[] indexArray = new int[size*size];
        for(int i = 0; i < size*size;i++){
            indexArray[i]=i;
        }
        Random rnd = new Random();
        for (int i = indexArray.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            int a = indexArray[index];
            indexArray[index] = indexArray[i];
            indexArray[i] = a;
        }

        View.OnTouchListener tl = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int x=0, y =0;
                for(int i = 0; i < size*size; i++){
                    if(elements[i]!=null && elements[i].getIv().getId()==v.getId()) {
                        x = elements[i].getPosition() / size;
                        y = elements[i].getPosition() % size;
                    }
                }
                if(x>-1 && x+1 < size  && (elements[(x+1)*size+y] == null)){
                    elements[(x+1)*size+y]=elements[x*size+y];
                    elements[x*size+y]=null;
                    elements[(x+1)*size+y].setPosition((x+1)*size+y);
                    elements[(x+1)*size+y].updateParams(width, height, size);
                }

                else if(x>0 && x<=size && (elements[(x-1)*size+y] == null)){
                    elements[(x-1)*size+y]=elements[x*size+y];
                    elements[x*size+y]=null;
                    elements[(x-1)*size+y].setPosition((x-1)*size+y);
                    elements[(x-1)*size+y].updateParams(width, height, size);
                }
                else if(y>-1 && y+1<size && elements[x*size+y+1] == null){
                    elements[x*size+y+1]=elements[x*size+y];
                    elements[x*size+y]=null;
                    elements[x*size+y+1].setPosition(x*size+y+1);
                    elements[x*size+y+1].updateParams(width, height, size);
                }
                else if(y>0 && y<=size && elements[x*size+y-1] == null){
                    elements[x*size+y-1]=elements[x*size+y];
                    elements[x*size+y]=null;
                    elements[x*size+y-1].setPosition(x*size+y-1);
                    elements[x*size+y-1].updateParams(width, height, size);
                }

                if(gameFinished()){
                    setResult(Activity.RESULT_OK, new Intent());
                    iDialog.show();
                }
                return false;
            }
        };


        for (int i = 0; i < indexArray.length-1;i++){
            elements[indexArray[i]]=new SliderElement(this,resource+i,i, indexArray[i]);
            rl.addView(elements[indexArray[i]].getIv());
            elements[indexArray[i]].setParams(width,height,size);
            elements[indexArray[i]].setListener(tl);
        }

    }

    private boolean gameFinished(){
        for(int i = 0; i < size*size-1; i++){
            if(elements[i]==null || elements[i].getIndex()!=i){
                return false;
            }
        }
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_slider_game, menu);
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
