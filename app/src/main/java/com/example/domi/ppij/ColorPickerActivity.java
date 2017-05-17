package com.example.domi.ppij;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.List;
import java.util.Random;


public class ColorPickerActivity extends Activity {

    private ImageView[] imageViewKockice = new ImageView[6];
    private int inicijalniBiracBoja;
    private int pokazivacNaKockice=-1;
    private int[] postavaIgre = new int[6];
    private imageDialog pobjednickiDialog;
    private ImageView konacnaSlika;


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Random rand = new Random();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker);
        Bundle bundle = getIntent().getExtras();
        ((ImageView)findViewById(R.id.imageViewslika)).setImageResource(bundle.getInt("res"));
        napuniListuImageViewa();
        inicijalniBiracBoja = findViewById(R.id.imageViewzuto).getId();
        int inicijalniBiracKockica = findViewById(R.id.imageView1).getId();
        for (int i = 0; i < 6; i++) {
            postavaIgre[i] = rand.nextInt(6);
        }
        konacnaSlika = (ImageView) findViewById(R.id.imageViewslika);
        konacnaSlika.setImageAlpha(0);
        pobjednickiDialog = new imageDialog(ColorPickerActivity.this, "Congratulation, you painted the picture!!!", bundle.getInt("res") );
        pobjednickiDialog.onDismissClose(this);
        for(int i = 0; i < 6; i++) {
            imageViewKockice[i].setTag("-1");
        }
    }

    private void napuniListuImageViewa() {
        imageViewKockice[0]=((ImageView) findViewById(R.id.imageView1));
        imageViewKockice[1]=((ImageView) findViewById(R.id.imageView2));
        imageViewKockice[2]=((ImageView) findViewById(R.id.imageView3));
        imageViewKockice[3]=((ImageView) findViewById(R.id.imageView4));
        imageViewKockice[4]=((ImageView) findViewById(R.id.imageView5));
        imageViewKockice[5]=((ImageView) findViewById(R.id.imageView6));

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

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void klikNaBoju(View view) {
        if(pokazivacNaKockice < 5) {
            pokazivacNaKockice++;
        }
       int biracBoja = view.getId() - inicijalniBiracBoja;
        imageViewKockice[pokazivacNaKockice].setImageAlpha(255);
        if(biracBoja == 0) {
            imageViewKockice[pokazivacNaKockice].setImageResource(R.drawable.zuto);
            imageViewKockice[pokazivacNaKockice].setTag("0");
        }else if(biracBoja == 1) {
            imageViewKockice[pokazivacNaKockice].setImageResource(R.drawable.narancasto);
            imageViewKockice[pokazivacNaKockice].setTag("1");
        }else if(biracBoja == 2) {
            imageViewKockice[pokazivacNaKockice].setImageResource(R.drawable.crveno);
            imageViewKockice[pokazivacNaKockice].setTag("2");
        }else if(biracBoja == 3) {
            imageViewKockice[pokazivacNaKockice].setImageResource(R.drawable.ljubicasto);
            imageViewKockice[pokazivacNaKockice].setTag("3");
        }else if(biracBoja == 4) {
            imageViewKockice[pokazivacNaKockice].setImageResource(R.drawable.plavo);
            imageViewKockice[pokazivacNaKockice].setTag("4");
        }else if(biracBoja == 5) {
            imageViewKockice[pokazivacNaKockice].setImageResource(R.drawable.zeleno);
            imageViewKockice[pokazivacNaKockice].setTag("5");
        }

        int br = brojPogodjenih();
        if( br == 6){
           pobjednickiDialog.show();
        }
        if(br == 0){
            konacnaSlika.setImageAlpha(0);
        }
        else if(br == 1) {
            konacnaSlika.setImageAlpha(40);
        } else if(br == 2) {
            konacnaSlika.setImageAlpha(80);
        } else if(br == 3) {
            konacnaSlika.setImageAlpha(120);
        } else if(br == 4) {
            konacnaSlika.setImageAlpha(160);
        } else if(br == 5) {
            konacnaSlika.setImageAlpha(200);
        } else if(br == 6) {
            konacnaSlika.setImageAlpha(255);
        }
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private int brojPogodjenih() {
        int broj = 0;
        for(int i = 0; i < 6; i++){
            if(postavaIgre[i] == 0 && imageViewKockice[i].getImageAlpha()!=0 && imageViewKockice[i].getTag().equals("0")) {
                broj++;
            }else if(postavaIgre[i] == 1 && imageViewKockice[i].getImageAlpha()!=0 && imageViewKockice[i].getTag().equals("1")) {
                broj++;
            }else if(postavaIgre[i] == 2 && imageViewKockice[i].getImageAlpha()!=0 && imageViewKockice[i].getTag().equals("2")) {
                broj++;
            }else if(postavaIgre[i] == 3 && imageViewKockice[i].getImageAlpha()!=0 && imageViewKockice[i].getTag().equals("3")) {
                broj++;
            }else if(postavaIgre[i] == 4 && imageViewKockice[i].getImageAlpha()!=0 && imageViewKockice[i].getTag().equals("4")) {
                broj++;
            }else if(postavaIgre[i] == 5 && imageViewKockice[i].getImageAlpha()!=0 && imageViewKockice[i].getTag().equals("5")) {
                broj++;
            }
        }

        return broj;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void klikNaKockicu(View view) {

            if(pokazivacNaKockice>=0) {
                imageViewKockice[pokazivacNaKockice].setImageAlpha(0);
            }

            if(pokazivacNaKockice >-1) {
                pokazivacNaKockice--;
            }
        int br = brojPogodjenih();
        if( br == 6){
            pobjednickiDialog.show();
        }
        if(br == 0){
            konacnaSlika.setImageAlpha(0);
        }
        else if(br == 1) {
            konacnaSlika.setImageAlpha(40);
        } else if(br == 2) {
            konacnaSlika.setImageAlpha(80);
        } else if(br == 3) {
            konacnaSlika.setImageAlpha(120);
        } else if(br == 4) {
            konacnaSlika.setImageAlpha(160);
        } else if(br == 5) {
            konacnaSlika.setImageAlpha(200);
        } else if(br == 6) {
            konacnaSlika.setImageAlpha(255);
        }

    }
}
