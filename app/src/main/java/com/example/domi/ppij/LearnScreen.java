package com.example.domi.ppij;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class LearnScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_screen);

        Display display = getWindowManager().getDefaultDisplay();
        Point displaySize = new Point();
        display.getSize(displaySize);
        int width = displaySize.x;
        int height = displaySize.y - getStatusBarHeight();

        ImageView renIm = (ImageView) findViewById(R.id.renTab);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) renIm.getLayoutParams();
        params.height = height/6;
        params.setMargins(0,0,0,0);
        params.width = width;
        renIm.setLayoutParams(params);
        renIm.setScaleType(ImageView.ScaleType.FIT_XY);
        renIm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LearnScreen.this, renaissance_levels.class));
            }
        });

        ImageView barIm = (ImageView) findViewById(R.id.barTab);
        params = (ViewGroup.MarginLayoutParams) barIm.getLayoutParams();
        params.height = height/6;
        barIm.setLayoutParams(params);
        barIm.setScaleType(ImageView.ScaleType.FIT_XY);
        barIm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LearnScreen.this, baroque_levels.class));
            }
        });

        ImageView impIm = (ImageView) findViewById(R.id.impTab);
        params = (ViewGroup.MarginLayoutParams) impIm.getLayoutParams();
        params.height = height/6;
        impIm.setLayoutParams(params);
        impIm.setScaleType(ImageView.ScaleType.FIT_XY);
        impIm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LearnScreen.this, impressionism_levels.class));
            }
        });

        ImageView expIm = (ImageView) findViewById(R.id.expTab);
        params = (ViewGroup.MarginLayoutParams) expIm.getLayoutParams();
        params.height = height/6;
        expIm.setLayoutParams(params);
        expIm.setScaleType(ImageView.ScaleType.FIT_XY);
        expIm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LearnScreen.this, expressionism_levels.class));
            }
        });

        ImageView cubIm = (ImageView) findViewById(R.id.cubTab);
        params = (ViewGroup.MarginLayoutParams) cubIm.getLayoutParams();
        params.height = height/6;
        cubIm.setLayoutParams(params);
        cubIm.setScaleType(ImageView.ScaleType.FIT_XY);
        cubIm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LearnScreen.this, cubism_levels.class));
            }
        });

        ImageView modIm = (ImageView) findViewById(R.id.modTab);
        params = (ViewGroup.MarginLayoutParams) modIm.getLayoutParams();
        params.height = height/6;
        modIm.setLayoutParams(params);
        modIm.setScaleType(ImageView.ScaleType.FIT_XY);
        modIm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LearnScreen.this, modern_levels.class));
            }
        });

        /*Button playGame = (Button) findViewById(R.id.playSliderBtn);
        playGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LearnScreen.this, SliderGameActivity.class);
                Bundle b = new Bundle();
                b.putInt("size",3);
                b.putInt("res", R.drawable.mona_lisa_1);
                intent.putExtras(b);
                startActivityForResult(intent, 1);
            }
        });

        Button playMem = (Button) findViewById(R.id.playMemBtn);
        playMem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LearnScreen.this, MemoryActivity.class);
                Bundle b = new Bundle();
                b.putInt("memoryImage0",R.drawable.slika1);
                b.putInt("memoryImage1", R.drawable.slika1);
                b.putInt("memoryImage2",R.drawable.slika2);
                b.putInt("memoryImage3", R.drawable.slika2);
                b.putInt("memoryImage4",R.drawable.slika3);
                b.putInt("memoryImage5", R.drawable.slika3);
                b.putInt("memoryImage6",R.drawable.slika4);
                b.putInt("memoryImage7", R.drawable.slika4);
                b.putInt("memoryImage8",R.drawable.slika5);
                b.putInt("memoryImage9", R.drawable.slika5);
                b.putInt("memoryImage10",R.drawable.slika6);
                b.putInt("memoryImage11", R.drawable.slika6);
                b.putInt("reward",R.drawable.mona_lisa_0);
                intent.putExtras(b);
                startActivityForResult(intent, 1);
            }
        });

        Button playDiff = (Button) findViewById(R.id.playDiffBtn);
        playDiff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LearnScreen.this, findDifferencesGame.class);
                Bundle b = new Bundle();
                b.putInt("firstImage", R.drawable.mona_lisa_0);
                b.putInt("secondImage", R.drawable.mona_lisa_0);
                b.putInt("misstakes", 5);
                b.putFloat("miss1", 0.4040f);
                b.putFloat("miss2", 0.0590f);
                b.putFloat("miss3", 0.3070f);
                b.putFloat("miss4", 0.1555f);
                b.putFloat("miss5", 0.5515f);
                intent.putExtras(b);
                startActivityForResult(intent, 1);
            }
        });

        Button playMaster = (Button) findViewById(R.id.playMasterBtn);
        playMaster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LearnScreen.this, MastermindActivity.class);
                Bundle b = new Bundle();
                b.putInt("dots",4);
                b.putInt("res", R.drawable.mona_lisa_0);
                intent.putExtras(b);
                startActivityForResult(intent, 1);
            }
        });

        Button playColors = (Button) findViewById(R.id.ColorPickbtn);
        playColors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LearnScreen.this, ColorPickerActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        Button playGrid = (Button) findViewById(R.id.gridGameBtn);
        playGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LearnScreen.this, GridGameActivity.class);
                startActivityForResult(intent, 1);
            }
        });*/
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_learn_screen, menu);
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
