package com.example.domi.ppij;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class cubism_levels extends Activity {
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cubism_levels);
        prefs = this.getSharedPreferences("ppijProject", Context.MODE_PRIVATE);

        Display display = getWindowManager().getDefaultDisplay();
        Point displaySize = new Point();
        display.getSize(displaySize);
        int width = displaySize.x;
        int height = displaySize.y - getStatusBarHeight();
        //////////////////////////   LEVEL 1 ///////////////////////////////////////////////////////////
        ImageView level1 = (ImageView) findViewById(R.id.level_41);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) level1.getLayoutParams();
        params.height = (int) (height *0.076);
        params.width = (int) (width * 0.14);
        params.topMargin = (int)(height * 0.826);
        params.leftMargin = (int)(width * 0.417);
        level1.setLayoutParams(params);
        level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(cubism_levels.this);
                builder.setMessage("You arive at the renaissance floor but it is locked. Guess the code to open the door!")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(cubism_levels.this, MastermindActivity.class);
                                Bundle b = new Bundle();
                                b.putInt("dots",4);
                                b.putInt("res", R.drawable.mona_lisa_0);
                                intent.putExtras(b);
                                startActivityForResult(intent, 1);
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        //////////////////////////   LEVEL 2 ///////////////////////////////////////////////////////////

        Boolean unLocked = prefs.getBoolean("unlocked_cubism_2",true);//TODO set false
        if(unLocked) {
            ImageView level2 = (ImageView) findViewById(R.id.level_42);
            params = (ViewGroup.MarginLayoutParams) level2.getLayoutParams();
            params.height = (int) (height * 0.076);
            params.width = (int) (width * 0.14);
            params.topMargin = (int) (height * 0.55);
            params.leftMargin = (int) (width * 0.694);
            level2.setLayoutParams(params);
            level2.setPivotX(level2.getWidth()/2);
            level2.setPivotY(level2.getHeight()/2);
            level2.setRotation(45);
            level2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(cubism_levels.this);
                    builder.setMessage("You opened the door but you see something terrible. Someone destroyed certain work of art. Put it together!")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent = new Intent(cubism_levels.this, SliderGameActivity.class);
                                    Bundle b = new Bundle();
                                    b.putInt("size",3);
                                    b.putInt("res", R.drawable.mona_lisa_1);
                                    intent.putExtras(b);
                                    startActivityForResult(intent, 2);
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            });
        }
        else
            findViewById(R.id.level_42).setVisibility(View.INVISIBLE);

        //////////////////////////   LEVEL 3 ///////////////////////////////////////////////////////////

        unLocked = prefs.getBoolean("unlocked_cubism_3",true);//TODO set false
        if(unLocked) {
            ImageView level3 = (ImageView) findViewById(R.id.level_43);
            params = (ViewGroup.MarginLayoutParams) level3.getLayoutParams();
            params.height = (int) (height * 0.076);
            params.width = (int) (width * 0.14);
            params.topMargin = (int) (height * 0.417);
            params.leftMargin = (int) (width * 0.785);
            level3.setLayoutParams(params);
            level3.setPivotX(level3.getWidth()/2);
            level3.setPivotY(level3.getHeight()/2);
            level3.setRotation(315);
            level3.setLayoutParams(params);
            level3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(cubism_levels.this);
                    builder.setMessage("Chief of the museum showed you two copies of another famous art and challenged you to find the differences!")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent = new Intent(cubism_levels.this, findDifferencesGame.class);
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
                                    startActivityForResult(intent, 3);
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            });
        }
        else
            findViewById(R.id.level_43).setVisibility(View.INVISIBLE);

        //////////////////////////   LEVEL 4 ///////////////////////////////////////////////////////////

        unLocked = prefs.getBoolean("unlocked_cubism_4",true);//TODO set false
        if(unLocked) {
            ImageView level4 = (ImageView) findViewById(R.id.level_44);
            params = (ViewGroup.MarginLayoutParams) level4.getLayoutParams();
            params.height = (int) (height * 0.076);
            params.width = (int) (width * 0.14);
            params.topMargin = (int) (height * 0.281);
            params.leftMargin = (int) (width * 0.56);
            level4.setLayoutParams(params);
            level4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(cubism_levels.this);
                    builder.setMessage("As you walk around you find another masterpiece in pieces. Looks like guard was sleeping last night!")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent = new Intent(cubism_levels.this, GridGameActivity.class);
                                    Bundle b = new Bundle();
                                    b.putInt("size",3);
                                    b.putInt("res", R.drawable.mona_lisa_1);
                                    intent.putExtras(b);
                                    startActivityForResult(intent, 4);
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            });
        }
        else
            findViewById(R.id.level_44).setVisibility(View.INVISIBLE);

        //////////////////////////   LEVEL 5 ///////////////////////////////////////////////////////////

        unLocked = prefs.getBoolean("unlocked_cubism_5",true);//TODO set false
        if(unLocked) {
            ImageView level5 = (ImageView) findViewById(R.id.level_45);
            params = (ViewGroup.MarginLayoutParams) level5.getLayoutParams();
            params.height = (int) (height * 0.076);
            params.width = (int) (width * 0.14);
            params.topMargin = (int) (height * 0.076);
            params.leftMargin = (int) (width * 0.604);
            level5.setLayoutParams(params);
            level5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(cubism_levels.this);
                    builder.setMessage("Chief of the museum challenged you to a game of memory. He seems to like playing games, and he is not worried about those masterpieces being destroyed!")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent = new Intent(cubism_levels.this, MemoryActivity.class);
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
                                    startActivityForResult(intent, 5);
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            });
        }
        else
            findViewById(R.id.level_45).setVisibility(View.INVISIBLE);

        //////////////////////////   LEVEL 6 ///////////////////////////////////////////////////////////

        unLocked = prefs.getBoolean("unlocked_cubism_6",true);//TODO set false
        if(unLocked) {
            ImageView level6 = (ImageView) findViewById(R.id.level_46);
            params = (ViewGroup.MarginLayoutParams) level6.getLayoutParams();
            params.height = (int) (height * 0.076);
            params.width = (int) (width * 0.14);
            params.topMargin = (int) (height * 0.076);
            params.leftMargin = (int) (width * 0.26);
            level6.setLayoutParams(params);
            level6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(cubism_levels.this);
                    builder.setMessage("This museum is a strange one. You found another duplicate of an ancient masterpiece! Find the differences!")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent = new Intent(cubism_levels.this, findDifferencesGame.class);
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
                                    startActivityForResult(intent, 6);
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            });
        }
        else
            findViewById(R.id.level_46).setVisibility(View.INVISIBLE);

        //////////////////////////   LEVEL 7 ///////////////////////////////////////////////////////////

        unLocked = prefs.getBoolean("unlocked_cubism_7",true);//TODO set false
        if(unLocked) {
            ImageView level7 = (ImageView) findViewById(R.id.level_47);
            params = (ViewGroup.MarginLayoutParams) level7.getLayoutParams();
            params.height = (int) (height * 0.076);
            params.width = (int) (width * 0.14);
            params.topMargin = (int) (height * 0.295);
            params.leftMargin = (int) (width * 0.224);
            level7.setLayoutParams(params);
            level7.setPivotX(level7.getWidth() / 2);
            level7.setPivotY(level7.getHeight() / 2);
            level7.setRotation(130);
            level7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(cubism_levels.this);
                    builder.setMessage("You see an empty canvas. Choose the right colors and show that you can paint as well!")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent = new Intent(cubism_levels.this, ColorPickerActivity.class);
                                    Bundle b = new Bundle();
                                    b.putInt("res", R.drawable.vrisak);
                                    intent.putExtras(b);
                                    startActivityForResult(intent, 7);
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            });
        }
        else
            findViewById(R.id.level_47).setVisibility(View.INVISIBLE);

        //////////////////////////   LEVEL 8 ///////////////////////////////////////////////////////////

        unLocked = prefs.getBoolean("unlocked_cubism_8",true);//TODO set false
        if(unLocked) {
            ImageView level8 = (ImageView) findViewById(R.id.level_48);
            params = (ViewGroup.MarginLayoutParams) level8.getLayoutParams();
            params.height = (int) (height * 0.076);
            params.width = (int) (width * 0.14);
            params.topMargin = (int) (height * 0.455);
            params.leftMargin = (int) (width * 0.447);
            level8.setLayoutParams(params);
            level8.setPivotX(level8.getWidth() / 2);
            level8.setPivotY(level8.getHeight() / 2);
            level8.setRotation(180);
            level8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(cubism_levels.this);
                    builder.setMessage("Wow another artwork in pieces. I know it's old but that's just not ok!")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent = new Intent(cubism_levels.this, SliderGameActivity.class);
                                    Bundle b = new Bundle();
                                    b.putInt("size",3);
                                    b.putInt("res", R.drawable.mona_lisa_1);
                                    intent.putExtras(b);
                                    startActivityForResult(intent, 8);
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            });
        }
        else
            findViewById(R.id.level_48).setVisibility(View.INVISIBLE);

        //////////////////////////   LEVEL 9 ///////////////////////////////////////////////////////////

        unLocked = prefs.getBoolean("unlocked_cubism_9",true);//TODO set false
        if(unLocked) {
            ImageView level9 = (ImageView) findViewById(R.id.level_49);
            params = (ViewGroup.MarginLayoutParams) level9.getLayoutParams();
            params.height = (int) (height * 0.076);
            params.width = (int) (width * 0.14);
            params.topMargin = (int) (height * 0.679);
            params.leftMargin = (int) (width * 0.491);
            level9.setLayoutParams(params);
            level9.setPivotX(level9.getWidth() / 2);
            level9.setPivotY(level9.getHeight() / 2);
            level9.setRotation(180);
            level9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(cubism_levels.this);
                    builder.setMessage("Chief of the museum left before you and forgot you where still here. I guees it's time to break the lock. AGAIN")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent = new Intent(cubism_levels.this, MastermindActivity.class);
                                    Bundle b = new Bundle();
                                    b.putInt("dots",4);
                                    b.putInt("res", R.drawable.mona_lisa_0);
                                    intent.putExtras(b);
                                    startActivityForResult(intent, 9);
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            });
        }
        else
            findViewById(R.id.level_49).setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK){
            //TODO set shared preferences
            prefs.edit().putBoolean("unlocked_cubism_" + (requestCode + 1), true).apply();
            Log.d("resultCheck", "OK");
        }
        else
            Log.d("resultCheck", "Not OK");
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
        getMenuInflater().inflate(R.menu.menu_cubism_levels, menu);
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
