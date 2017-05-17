package com.example.domi.ppij;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class MemoryActivity extends Activity {

    Random r = new Random();
   // TextView testniEkran;

    char[] postava = new char[12];
    ImageView[] photos = new ImageView[12];
    ImageView inicijalni;
    int initNum;
    int brStisnutih = 0;
    int indexPrvi, indexDrugi;
    int[] resources = new int[12];
    int reward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);
        inicijalni = (ImageView) findViewById(R.id.photo1);
        initNum = inicijalni.getId();

        Bundle b = getIntent().getExtras();
        reward = b.getInt("reward");

        inicijalizacija(postava, b);
        inicijalizacijaImagea(photos);

       // testniEkran = (TextView) findViewById(R.id.textViewtest);
       // testniEkran.setText(postava, 0, 12);
    }

    public void inicijalizacijaImagea(ImageView[] photos) {
        photos[0] = (ImageView) findViewById(R.id.photo1);
        photos[1] = (ImageView) findViewById(R.id.photo2);
        photos[2] = (ImageView) findViewById(R.id.photo3);
        photos[3] = (ImageView) findViewById(R.id.photo4);
        photos[4] = (ImageView) findViewById(R.id.photo5);
        photos[5] = (ImageView) findViewById(R.id.photo6);
        photos[6] = (ImageView) findViewById(R.id.photo7);
        photos[7] = (ImageView) findViewById(R.id.photo8);
        photos[8] = (ImageView) findViewById(R.id.photo9);
        photos[9] = (ImageView) findViewById(R.id.photo10);
        photos[10] = (ImageView) findViewById(R.id.photo11);
        photos[11] = (ImageView) findViewById(R.id.photo12);
    }


    public void imageClick(View view) {
        //ako je broj stisnutih jednak 0 ili 1 tada je moguce
        //otvoriti jos jednu kartu, inace nije moguce
        if(brStisnutih == 0 || brStisnutih == 1) {
            ImageView pritisnuti = (ImageView) view;
            int stisnuti = pritisnuti.getId();
            int indexPozicije;
            //char indexSlike;
            indexPozicije = stisnuti - initNum;
            //spremi indexe prve ili druge slike (da ih znas maknuti ako su iste)
            if(brStisnutih == 0) {
                indexPrvi = indexPozicije;
            } else /*if(brStisnutih == 1)*/ {
                indexDrugi = indexPozicije;
            }
            if(brStisnutih == 1 && indexPrvi == indexDrugi){
                return;
            }
            //indexSlike = postava[indexPozicije];
            pritisnuti.setImageResource(resources[indexPozicije]);
            /*if(indexSlike == '1') {
                //pritisnuti.setImageResource(R.drawable.slika1);
                pritisnuti.setImageResource(resources[indexPozicije]);
            } else if(indexSlike == '2') {
                pritisnuti.setImageResource(R.drawable.slika2);
            } else if(indexSlike == '3') {
                pritisnuti.setImageResource(R.drawable.slika3);
            } else if(indexSlike == '4') {
                pritisnuti.setImageResource(R.drawable.slika4);
            } else if(indexSlike == '5') {
                pritisnuti.setImageResource(R.drawable.slika5);
            } else if(indexSlike == '6') {
                pritisnuti.setImageResource(R.drawable.slika6);
            }*/
            if (brStisnutih == 0) {
                brStisnutih = 1;
            } else if(brStisnutih == 1) {
                brStisnutih = 2;
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        //tu treba provjeriti dali su isti
                        //ako su isti treba im postaviti bijelu pozadinu
                        if(postava[indexPrvi] == postava[indexDrugi]) {
                            photos[indexPrvi].setVisibility(View.INVISIBLE);//setImageResource(R.drawable.bijelapozadina);
                            photos[indexDrugi].setVisibility(View.INVISIBLE);//setImageResource(R.drawable.bijelapozadina);
                            postava[indexPrvi] = '$';
                            postava[indexDrugi] = '$';
                        }
                        okreniSveNaopacke();
                        brStisnutih = 0;

                        if(gotovaIgra()) {
                            imageDialog iDialog = new imageDialog(MemoryActivity.this, "You have unlocked:", reward);
                            iDialog.onDismissClose(MemoryActivity.this);
                            setResult(Activity.RESULT_OK, new Intent());
                            iDialog.show();
                        }
                    }
                }, 2000);
            }
        }

    }

    private boolean gotovaIgra(){
        for(int i = 0; i < 12; i++){
            if(postava[i] != '$'){
                return false;
            }
        }
        return true;
    }

    void okreniSveNaopacke() {
        for(int i = 0; i < photos.length; i++) {
            if(postava[i] != '$')
                photos[i].setImageResource(R.drawable.backofcard);
        }
    }
    void inicijalizacija(char[] postava, Bundle b) {
        int i = 80;
        for(int j = 0; j < 12; j+=2){
            postava[j]=(char)j;
            postava[j+1]=(char)j;
            resources[j] = b.getInt("memoryImage" + j);
            resources[j+1] = b.getInt("memoryImage" + (j+1));
        }
        /*postava[0] = '1';
        postava[1] = '1';
        postava[2] = '2';
        postava[3] = '2';
        postava[4] = '3';
        postava[5] = '3';
        postava[6] = '4';
        postava[7] = '4';
        postava[8] = '5';
        postava[9] = '5';
        postava[10] = '6';
        postava[11] = '6';*/
        while(i>0) {
            int j = r.nextInt(12);
            int z = r.nextInt(12);
            zamjeniIndexe(postava, j, z);
            zamjeniResurse(resources, j, z);
            i--;
        }
    }

    void zamjeniResurse(int[] ress, int i, int j){
        int temp = ress[i];
        ress[i] = ress[j];
        ress[j] = temp;
    }

    void zamjeniIndexe(char[] postava, int i, int j) {
        char temp = postava[i];
        postava[i] = postava[j];
        postava[j] = temp;
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
