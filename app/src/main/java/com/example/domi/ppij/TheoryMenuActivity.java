package com.example.domi.ppij;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class TheoryMenuActivity extends ActionBarActivity {
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theory_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.expandableList);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Intent intent = new Intent(TheoryMenuActivity.this, showTheory.class);
                Bundle b = new Bundle();
                b.putString("data","_"+groupPosition+"_"+childPosition);
                intent.putExtras(b);
                startActivityForResult(intent, 1);
                return false;
            }
        });
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();

        // Adding child data
        listDataHeader.add("Ancient art");
        listDataHeader.add("Renaissance");
        listDataHeader.add("Baroque");
        listDataHeader.add("Impressionism");
        listDataHeader.add("Expressionism");
        listDataHeader.add("Cubism");
        listDataHeader.add("Modern art");

        // Adding child data
        List<String> ancient = new ArrayList<>();
        ancient.add("Overview");

        List<String> renaissance = new ArrayList<>();
        renaissance.add("Overview");
        renaissance.add("Leonardo da Vinci");
        renaissance.add("Mona Lisa");
        renaissance.add("The Last Supper");
        renaissance.add("Michelangelo");
        renaissance.add("Ceiling of Sistine Chapel");
        renaissance.add("David");
        renaissance.add("Raphael");
        renaissance.add("School of Athens");
        renaissance.add("Sistine Madonna");
        renaissance.add("Titian");
        renaissance.add("Assumption of the Virgin");
        renaissance.add("The Birth of Venus");

        List<String> baroque = new ArrayList<>();
        baroque.add("Overview");

        List<String> impressionism = new ArrayList<>();
        impressionism.add("Overview");
        impressionism.add("Claude Monet");
        impressionism.add("Haystacks");
        impressionism.add("Water Lilies");
        impressionism.add("Piere-Auguste Renoir");
        impressionism.add("Bal");
        impressionism.add("Boating Party");
        impressionism.add("Edgar Degas");
        impressionism.add("The Bellielli Family");
        impressionism.add("Vincent van Gogh");
        impressionism.add("The Starry Night");
        impressionism.add("Portrait of Dr. Gachet");

        List<String> expressionism = new ArrayList<>();
        expressionism.add("Overview");

        List<String> cubism = new ArrayList<>();
        cubism.add("Overview");

        List<String> modern = new ArrayList<>();
        modern.add("Overview");

        listDataChild.put(listDataHeader.get(0), ancient);
        listDataChild.put(listDataHeader.get(1), renaissance); // Header, Child data
        listDataChild.put(listDataHeader.get(2), baroque);
        listDataChild.put(listDataHeader.get(3), impressionism);
        listDataChild.put(listDataHeader.get(4), expressionism);
        listDataChild.put(listDataHeader.get(5), cubism);
        listDataChild.put(listDataHeader.get(6), modern);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_theory_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id==android.R.id.home) {
            finish();
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
