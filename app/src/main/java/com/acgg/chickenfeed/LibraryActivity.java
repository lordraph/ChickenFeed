package com.acgg.chickenfeed;


import android.content.Intent;


import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.Toolbar;


import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;


import android.widget.ListView;
import java.util.ArrayList;

public class LibraryActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ListView mListView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolBar;

    private MenuItem item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        mToolBar = findViewById(R.id.nav_actionBar);
        setSupportActionBar(mToolBar);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.nav_view);
        NavigationMenuView navigationMenuView = (NavigationMenuView) navigationView.getChildAt(0);
        navigationMenuView.addItemDecoration(new DividerItemDecoration(LibraryActivity.this, DividerItemDecoration.VERTICAL));

        mListView = findViewById(R.id.listview);
        ArrayList<Feed> list = new ArrayList<>();
        list.add(new Feed("Acha (grain)", 86.67, 9.71,1.26, 4.48,80.98, 2.74,
                3511, 0.28,0.13,0.2,0.52, "Energy feed"));
        list.add(new Feed("Cassava peel meal", 87.59, 5.33,1.81, 14.23,70.37, 0,
                2807, 0.65,0.25,0,0, "Energy feed"));
        list.add(new Feed("Cassava flour", 89.42, 3.10,0.99, 3.73,82.90, 0,
                3090, 0.16,0.37,0.07,0, "Energy feed"));
        list.add(new Feed("Cassava leaf meal", 92.06, 23.79,6.83, 17.7,40.46, 0,
                2856, 1.24,0.6,1.04,0, "Energy feed"));
        list.add(new Feed("Cassava grits (Gaari)", 86.47, 1.20,0.38, 2.31,94.03, 2.08,
                3367, 0.2,0.03,0.07,0.02, "Energy feed"));
        list.add(new Feed("Cassava starch", 90.00, 2.00,1.00, 2.00,72.79, 0,
                2693, 0,0.01,0,0, "Energy feed"));
        list.add(new Feed("Cocoyam, tuber", 35.6, 6.3,0.72, 2.4,94.63, 2.2,
                3604, 0,0,0,0, "Energy feed"));
        list.add(new Feed("Sorghum (Guinea corn), red", 90.00, 8.00,3.00, 5.00,0, 0,
                3300, 0.04,0.32,0.21,0.16, "Energy feed"));
        list.add(new Feed("Sorghum (Guinea corn), white", 90.00, 8.00,3.00, 5.00,0, 0,
                3300, 0.04,0.32,0.21,0.16, "Energy feed"));
        list.add(new Feed("Maize, white", 89.10, 7.87,5.00, 2.60,66.79, 0,
                3245, 0.02,0.27,0.20,0.15, "Energy feed"));
        list.add(new Feed("Maize, yellow", 89.60, 8.80,4.8, 1.90,67.58, 0,
                3333, 0.02,0.27,0.20,0.15, "Energy feed"));
        list.add(new Feed("Millet", 90.00, 12.00,4.20, 1.80,70.07, 0,
                3440, 0.05,0.30,0.35,0.28, "Energy feed"));
        list.add(new Feed("Palm oil", 0, 0,98.9, 0,0, 0.10,
                7710, 6.0,0,0,0, "Energy feed"));
        list.add(new Feed("Rice grain, rough", 89.00, 7.30,1.70, 10.00,72.31, 0,
                2940, 0.04,0.26,0.24,0.14, "Energy feed"));
        list.add(new Feed("Rice grain, polished, broken", 89.00, 8.50,0.60, 0.20,88.81, 0,
                3472, 0.32,0.34,0.32,0.25, "Energy feed"));
        list.add(new Feed("Rice grain, polished, parboiled", 89.00,7.20,	0.30,	0.20	,91.17	,0	,
                3482,	0.13	,0.16,	0.27,	0.21,"Energy feed"));
        list.add(new Feed("Refined soya oil", 1.00,	0,	0,	0	,97	,0	,
                7800,	0	,0, 0	,0,"Energy feed"));
        list.add(new Feed("Raw Soya oil", 0,	0,	0,	0,		0,	8370,
                0,	0,	0,	0,0, "Energy feed"));
        list.add(new Feed("Sweet potato tuber (unpeeled)", 94.25,	2.76,	1.87,	1.20,	90.68,	3.49,
                0.60,	0.02,	0.03	,0.23	,0.09, "Energy feed"));
        list.add(new Feed("Sweet potato tuber (peeled)", 40.00,	5.8,	0.54,	1.2,	95.35,	3.9,
                3596,	0,	0,	0	,0,"Energy feed"));
        list.add(new Feed("Wheat ,hard, grain",	88.00	,13.50,	1.90	,3.00,	0,	0,
                3035,	0.05	,0.41,	0.40,	0.25,"Energy feed"));
        list.add(new Feed("Wheat, soft, grain",	86.00,	11.80,	1.70,	2.80,	0,	0,
                3190,	0.05,	0.30,	0.30,	0.14,"Energy feed"));
        list.add(new Feed("Water Yam, (unpeeled)",	25.4,	8.2,	0.90,	2.6,	82.03,	0,
                3248,	0,	0,	0.31,	0.13,"Energy feed"));
        list.add(new Feed("Water Yam (peels only)",	25.87,	11.73,	1.01,	6.56,	71.2,	9.5,
                3009,	0.38,	0.1,	0,	0,"Energy feed"));
        list.add(new Feed("Yellow Yam (unpeeled)",	22.4,	6.3,	0.40,	2.4,87.12,	0,
                3315,	0,	0,	0,	0,"Energy feed"));
        list.add(new Feed("Yellow Yam (peeled)",	16.1,	5.44,	0.17,	0.77,	90.60,	3.03,
                3387,	5.19,	0.13,	0,	0,"Energy feed"));
        list.add(new Feed("Yellow Yam (peels only)",	21.7,	7.42,	0.65,	7.61,	76.84,	7.48,
                3017,	1.36,	0.13,	0,	0,"Energy feed"));
        list.add(new Feed("Ripe pawpaw seeds",	96.00,	27.4,	0.4,	27.28,	7.47,	3.7,
                1308,	0.59,	0.26,	0,	0,"Energy feed"));
        list.add(new Feed("Water leaf seeds",	90.00,	18.6,	5.0,	20.6,	7.71,	0,
                1367,	0,	0,	0,	0,"Energy feed"));
        list.add(new Feed("Ripe pawpaw fruit (peeled)",	94.00,	6.55,	9.0,	6.0,	69.07,	4.0,
                3396,	0.16,	0.07,	0,	0,"Energy feed"));
        list.add(new Feed("Ripe plantain fruit",	95.00,4.12,	0.65,	0.05,	22.84,	4.43,
                1005,	0.10,	0.34,	0,0,"Energy feed"));
        list.add(new Feed("Toasted groundnut testa",	99.00,	18.98,	10.00,	10.00,	0,	3.45,
                912,	0,	0,	0,	0,"Energy feed"));
        list.add(new Feed("Soybean testa",	90.00,	16.63,	4.00,	25.43,	32.96,	2.75,
                2096,	0.84,	0.78,	2.38,	1.17,"Energy feed"));
        list.add(new Feed("Cowpea testa",	95.75,	16.97,	2.65,	20.35,	4.58,	1.55,
                1005,	2.06,	0.31,	0,	0,"Energy feed"));
        list.add(new Feed("Orange pulp",	89.48,	0,	6.78,	9.45,	0,	4.36,
                1049,	0,	0,	0,	0,"Energy feed"));
        list.add(new Feed("Melon fruit pulp",	93.50,	8.48,	4.17,	31.06,	14.09,	20.60,
                1148,	0,	0,	0,	0,"Energy feed"));
        list.add(new Feed("Orange peelings",	85.27,	6.80,	12.79,	8.48,	0,	4.87,
                0,	0,	0,	0,	0,"Energy feed"));
        list.add(new Feed("Plantain peels",	91.29,	14.03,	5.74,	4.72,	10.79,	12.15,
                1367,	0.10,	0.34,	0,	0,"Energy feed"));
        list.add(new Feed("Bitter leaves",	89.17,	22.80,	11.35,	11.35,	33.56,	14.57,
                2947,	0,	0,	0,	0,"Energy feed"));
        list.add(new Feed("Yam flour sieviates",	95.00,	3.53,	1.00,	5.00,	54.36,	4.25,
                2115,	0,	0,	0,	0,"Energy feed"));
        list.add(new Feed("Palm oil sludge",	91.00,	9.00,	31.5,	12.9,	79.15,	0,
                5680,	0,	0,	0,	0,"Energy feed"));
        list.add(new Feed("Evaporated palm oil sludge",	90.90,	4.30,	53.2,	7.50,	33.40,	0,
                4900,	0,	0,	0,	0,"Energy feed"));
        list.add(new Feed("Maize starch residue (Eeri, Yoruba?)",	89.90,	14.70,	3.80,	5.70,	24.42,	0,
                3305,	0,	0,	0.12,	0.10,"Energy feed"));
        list.add(new Feed("African locust bean seed",	94.33,	30.38,	20.30,	8.82,	35.12,	5.38,
                2370,	0.37,	0.28,	0,	0,"Plant protein feed"));
        list.add(new Feed("African locust bean seed and pod powder material",	91.88,	19.28,	10.60,	11.15,	52.55,	6.42,
                2561,	0.28,	0.28,	0,	0,"Plant protein feed"));
        list.add(new Feed("African locust bean whole fruit",	92.90,	12.71,	6.75,	18.03,	49.33,	6.21,
                2202,	0.40,	0.28,	0.3,	0.07,"Plant protein feed"));
        list.add(new Feed("Almond kernel",	94.06,	29.65,	36.81,	2.67,	14.01,	0,
                4598,	0.50,	0.68,	0,	0,"Plant protein feed"));
        list.add(new Feed("Bambara groundnut (decorticated)",	92.10,	21.14,	6.54,	5.08,	53.54,	3.78,
                2661,	0.90,	0.76,	0,	0,"Plant protein feed"));
        list.add(new Feed("Bambara groundnut (undecorticated)",	95.18,	17.58,	5.26,	13.66,	54.84,	5.24,
                2574,	0.90,	0.76,	0,	0,"Plant protein feed"));
        list.add(new Feed("Brewer’s Yeast",	90.30,	43.10,	0.30,	6.60,	40.7,	5.3,
                3049,	0.13,	1.56,	3.30,	0.61,"Plant protein feed"));
        list.add(new Feed("Cashew nut (unextracted)",	0,	21.18,	48.09,	0.68,	22.20,	3.30,
                5494,	0.03,	0.88,	0.86,	0.35,"Plant protein feed"));
        list.add(new Feed("Cashew nut (extracted)",	0,	36.04,	7.44,	1.23,	42.19,	5.10,
                3419,	0.06,	1.64,	0.86,	0.35,"Plant protein feed"));
        list.add(new Feed("Cotton seed, whole seeds with lint",	92,	23.00,	19.00,	26.00,	0,	0,
                19.0,	0.19,	0.16,	0.40,	1.02,"Plant protein feed"));
        list.add(new Feed("Full fat soya roasted/toasted",	92.00,	37,	18,	5.50,	19.12,	0,
                3100,	0.20,	0.50,	2.25,	0.49,"Plant protein feed"));
        list.add(new Feed("Groundnut cake",	90.00,	40,	6.00,	8.00,	17.01,	0,
                2640,	0.20,	0.20,	1.60,	0.48,"Plant protein feed"));
        list.add(new Feed("Groundnut meal",	90.00,	42,	3.00,	8.00,	21.45,	0,
                2550,	0.20,	0.20,	1.60,	0.48,"Plant protein feed"));
        list.add(new Feed("Pigeon pea",	94.7,	23.77,	1.11,	7.49,	62.39,	5.24,
                3064,	1.29,	2.80,	1.16,	0.38,"Plant protein feed"));
        list.add(new Feed("Roselle (Zobo) seed",  	88.00,	29.0,	6.0,	17.00,	26.75,	0,
                2500,	0.45,	0.20,	1.60,	0.45,"Plant protein feed"));
        list.add(new Feed("Sesame seed",	94,	42.00,	7.00,	6.50,	3.67,	0,
                2255,	2.00,	1.30,	1.37,	1.48,"Plant protein feed"));
        list.add(new Feed("Sunflower seed",	93,	41.0,	7.60,	21.00,	4.89,	0,
                2310,	0.43,	1.00,	2.00,	1.60,"Plant protein feed"));
        list.add(new Feed("Water melon seed  (unshelled)",	88,	24.36,	35.36,	31.63,	4.45,	4.15,
                1086,	0.07,	0.31,	0,	0,"Plant protein feed"));
        list.add(new Feed("Water melon seed  (shelled)",	91.92,	34.48,	46.74,	8.24,	4.37,	6.17,
                3147,	0.07,	0.31,	0,	0,"Plant protein feed"));
        list.add(new Feed("Blood meal",	89.00,	80.00,	1.00,	1.0,	5.09,	6.0,
                3220,	0.28,	0.22,	6.90,	1.0,"Animal protein feed"));
        list.add(new Feed("Earthworm (dried)",	91.4,	63,	5.9,	1.9,	0,	0,
                0,	0.53,	0.94,	6.35,	5.30,"Animal protein feed"));
        list.add(new Feed("Feather (dried)", 	93,	85.00,	4.00,	1.50,	0,	0,
                2880,	0.20,	0.70,	2.05,	0.65,"Animal protein feed"));
        list.add(new Feed("Garden snail (dried with shell)",	91,	66.8,	7.9,	4.1,	0,	0,
                0,	1.13,	0.15,	5.10,	1.33,"Animal protein feed"));
        list.add(new Feed("Housefly Maggot (dried)",	88.5,	60.00,	19.00,	0.50,	0,	7.30,
                0,	0.20,	0.20,	3.60,	1.40,"Animal protein feed"));
        list.add(new Feed("Local fish meal",	92,	39.00,	8.50,	0.40,	22.35,	33.00,
                0,	3.00,	1.80,	3.00,	0.90,"Animal protein feed"));
        list.add(new Feed("Termite meal",	96.3,	46.3,	30.1,	7.3,	0,	0,
                0,	0.23,	0.38,	2.83,	1.68,"Animal protein feed"));
        list.add(new Feed("Unskinned dried tadpole", 	93.2,	43.5,	11.3,	3.8,	0,	0,
                0,	0.25,	0.57,	6.97,	2.08,"Animal protein feed"));
        list.add(new Feed("Unskinned boiled tadpole meal",	93.4,	45.9,	10.7,	3.8,	0,	0,
                0,	2.43,	0.42,	6.72,	1.74,"Animal protein feed"));
        list.add(new Feed("Brewer’s wet grain",	22.30,	27.80,	8.00,	12.6,	46.70,	4.9,
                3320,	0.16,	0.65,	0.92,	0.33,"High Fibre/By Product feed"));
        list.add(new Feed("Brewer’s dry grain",	84.30,	17.00,	6.80,	13.20,	53.70,	9.3,
                0, 0.08,	0.10,	0.56,	0.20,"High Fibre/By Product feed"));
        list.add(new Feed("Corn cobs (Dried, milled)",	89,	2.3,	0.4,	35.00,	11.53,	0,
                523,	0.11,	0.04,	0,	0,"High Fibre/By Product feed"));
        list.add(new Feed("Cotton seed hulls",	90,	4.00,	4.40,	43.00,	0,	0,
                0,	0.14,	0.09,	0,	0,"High Fibre/By Product feed"));
        list.add(new Feed("Okra, (dried fruit)",	87.50,	14.63,	9.75,	36.9,	30.66,	8.06,
                1622,	0.80,	0.62,	0,	0,"High Fibre/By Product feed"));
        list.add(new Feed("Okra, (seeds from dried fruit)",	86.22,	19.66,	13.34,	34.6,	27.49,	4.91,
                1700,	0.80,	0.62,	0,	0,"High Fibre/By Product feed"));
        list.add(new Feed("Palm kernel meal",90,	21.30,	4.40,	17.50,	0,	0,
                2500,	0.40,	0.50,	0.69,	0.32,"High Fibre/By Product feed"));
        list.add(new Feed("Poultry manure, dried, cage",	89,	28.70,	1.70,	14.90,	0,	0,
                0,	7.80,	2.20,	0.39,	0.12,"High Fibre/By Product feed"));
        list.add(new Feed("Poultry manure, dried, floor",	85,	25.30,	2.30,	18.60,	0,	0,
                0,	2.50,	1.60,	0.49,	0.13,"High Fibre/By Product feed"));
        list.add(new Feed("Rice hulls",	92.00,	3.00,	0.50,	44.00,	16.23,	0,
                720,	0.04	,0.10	,0,	0,"High Fibre/By Product feed"));
        list.add(new Feed("Rice bran",	91.00,	13.50,	5.90,	13.00,	30.23,	0,
                2040,	0.10,	1.70,	0.50,	0.17,"High Fibre/By Product feed"));
        list.add(new Feed("Rice grain with hulls",	93.93,	11.17,	1.89,	9.02,	73.45,	4.47,
                2986,	0.45,	0.10,	0.40,	0.18,"High Fibre/By Product feed"));
        list.add(new Feed("Rice grain, hull removed (brown rice)",	94.38,	12.51,	0.21,	0.19,	85.71,	1.38,
                3463,	0.15,	0.36,	0.23,	0.16,"High Fibre/By Product feed"));
        list.add(new Feed("Rice offal (unparboiled)",	90.00,	5.50,	3.50,	30.00,	23.15,	0,
                1300,	0.13,	0,	0.20,	0.07,"High Fibre/By Product feed"));
        list.add(new Feed("Rice offal (parboiled)",	90.00,	3.50,	3.00,	36.00,	23.57,	0,
                1200,	0.16,	0,	0.20,	0.07,"High Fibre/By Product feed"));
        list.add(new Feed("Sorghum offal",	91.00,	9.00,	5.00,	6.00,	55.94,	0,
                2700,	0.01,	0.09,	0.25,	0.18,"High Fibre/By Product feed"));
        list.add(new Feed("Tomato pulp, dried",	93,	21.00,	10.00,	25.00,	4.71,	0,
                1760,	0.40,	0.57,	1.60,	0.10,"High Fibre/By Product feed"));
        list.add(new Feed("Wheat bran",	89.00,	14.80,	4.00,	10.00,	41.29,	0,
                2320,	0.14,	1.17,	0.60,	0.20,"High Fibre/By Product feed"));
        list.add(new Feed("Wheat offal",	89.00,	15.00,	3.50,	8.50,	29.39,	0,
                1870,	0.10,	0.30,	0.90,	0.25,"High Fibre/By Product feed"));
        list.add(new Feed("Bone meal",0	,0,	0,	0,	0,	0,
                0,	32.00,	12.5,	0,	0,"Minerals feed"));
        list.add(new Feed("Dicalcium phosphate",	0	,0,	0,	0,	0,	0,
                0,	22.00,	0,	0,	0,"Minerals feed"));
        list.add(new Feed("Limestone",	0,	0,	0,	0,	0,	0,
                0,	35.00,	0,	0,	0,"Minerals feed"));
        list.add(new Feed("Oyster shell",	0	,0,	0,	0	,0,	0,
                0,	35.00,	0,	0,	0,"Minerals feed"));
        list.add(new Feed("Periwinkle shell",	0	,0,	0,	0,	0,	0,
                0,	32.00,	0,	0,	0,"Minerals feed"));
        list.add(new Feed("Egg shell meal",	0,	0,	0	,0,	0,	0,
                0,	35.2,	0.12,	0,	0,"Minerals feed"));


        CustomListAdapter adapter = new CustomListAdapter(this, R.layout.card, list);
        mListView.setAdapter(adapter);

    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void openMainActivity(MenuItem item) {
        Intent intent = new Intent(LibraryActivity.this, MainActivity.class);
        startActivity(intent);
    }


    public void openAccountActivity(MenuItem item) {
        Intent intent = new Intent(LibraryActivity.this, AccountActivity.class);
        startActivity(intent);
    }

    public void openNotificationActivity(MenuItem item) {
        Intent intent = new Intent(LibraryActivity.this, NotificationActivity.class);
        startActivity(intent);
    }

    public void openSavedActivityActivity(MenuItem item) {
        Intent intent = new Intent(LibraryActivity.this, SavedActivityActivity.class);
        startActivity(intent);
    }

    public void openAboutActivity(MenuItem item) {
        Intent intent = new Intent(LibraryActivity.this, AboutActivity.class);
        startActivity(intent);
    }

    public void openHelpActivity(MenuItem item) {
        Intent intent = new Intent(LibraryActivity.this, HelpActivity.class);
        startActivity(intent);
    }

}
