package com.example.domi.ppij;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.metaio.sdk.MetaioDebug;
import com.metaio.tools.io.AssetsManager;

import java.io.IOException;


public class MainScreen extends Activity {

    AssetsExtracter mTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        mTask = new AssetsExtracter();
        mTask.execute(0);

        Display display = getWindowManager().getDefaultDisplay();
        Point displaySize = new Point();
        display.getSize(displaySize);
        int width = displaySize.x;
        int height = displaySize.y - getStatusBarHeight();

        ImageView playImg = (ImageView) findViewById(R.id.playImgBtn);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) playImg.getLayoutParams();
        params.width = (int) (0.34 * width);
        params.height = (int) (0.11 * height);
        params.topMargin = (int) (0.076 * height);
        params.leftMargin = (int) (0.62 * width);
        playImg.setLayoutParams(params);

        ImageView learnImg = (ImageView) findViewById(R.id.learnImgBtn);
        params = (ViewGroup.MarginLayoutParams) learnImg.getLayoutParams();
        params.width = (int) (0.34 * width);
        params.height = (int) (0.11 * height);
        params.topMargin = (int) (0.26 * height);
        params.leftMargin = (int) (0.02 * width);
        learnImg.setLayoutParams(params);

        ImageView galleryImg = (ImageView) findViewById(R.id.galleryImgBtn);
        params = (ViewGroup.MarginLayoutParams) galleryImg.getLayoutParams();
        params.width = (int) (0.34 * width);
        params.height = (int) (0.11 * height);
        params.topMargin = (int) (0.5 * height);
        params.leftMargin = (int) (0.64 * width);
        galleryImg.setLayoutParams(params);

        playImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainScreen.this, LearnScreen.class));
            }
        });

        galleryImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainScreen.this,GalleryScreen.class));
            }
        });

        learnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainScreen.this, TheoryMenuActivity.class));
            }
        });
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private class AssetsExtracter extends AsyncTask<Integer, Integer, Boolean>{

        @Override
        protected Boolean doInBackground(Integer... params) {
            try{
                AssetsManager.extractAllAssets(getApplicationContext(), true);
            } catch (IOException e) {
                MetaioDebug.printStackTrace(Log.ERROR, e);
                return false;
            }
            return true;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_screen, menu);
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
