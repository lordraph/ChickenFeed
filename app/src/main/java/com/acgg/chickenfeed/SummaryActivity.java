package com.acgg.chickenfeed;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.acgg.chickenfeed.data.ChickenFeedHelper;
import com.acgg.chickenfeed.summaryAdapter.CalculatedAnalysis;
import com.acgg.chickenfeed.summaryAdapter.CustomCalculatedAnalysisAdapter;
import com.acgg.chickenfeed.summaryAdapter.CustomFormulatedAdapter;
import com.acgg.chickenfeed.summaryAdapter.FormulatedDetails;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SummaryActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolBar;
    private ChickenFeedHelper dbHelper;
    private ArrayList <String> classArr, birdCatArr, ingredientArr, resultComment;
    private ArrayList <Integer> energyArr, QtyArr;
    private ArrayList <Double> phosphorusArr, calciumArr, crudeProteinArr, resultProportionPercent, resultProportionUnit;
    private Integer formNo = 0;
    private ListView formuListView, calcListView;
    private TextView proporText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        formuListView = findViewById(R.id.listview_Ingredient);

        dbHelper = new ChickenFeedHelper(this);


        mToolBar = findViewById(R.id.nav_actionBar);
        setSupportActionBar(mToolBar);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.nav_view);
        NavigationMenuView navigationMenuView = (NavigationMenuView) navigationView.getChildAt(0);
        navigationMenuView.addItemDecoration(new DividerItemDecoration(SummaryActivity.this, DividerItemDecoration.VERTICAL));

        //get formulation No
        Intent intent = getIntent();
        formNo = intent.getIntExtra("formuNo", 0);

        calcListView = findViewById(R.id.listview_CalculatedAnalysis);
        proporText = findViewById(R.id.value_proportion_toMix);

        loadIngredientForMix();
        loadcalculateAnalysis();

    }


    public void loadcalculateAnalysis() {

        ArrayList <CalculatedAnalysis> list = new ArrayList <>();

        Cursor cursor = dbHelper.getCalculatedAnalysis(formNo);

        while (cursor.moveToNext()) {
            list.add(new CalculatedAnalysis(cursor.getDouble(1), cursor.getDouble(3), cursor.getDouble(4),
                    cursor.getDouble(5)
            ));
        }

        CustomCalculatedAnalysisAdapter customCalculatedAnalysisAdapter = new CustomCalculatedAnalysisAdapter(this, R.layout.calculated_card, list);
        calcListView.setAdapter(customCalculatedAnalysisAdapter);

    }


    public void loadIngredientForMix() {
        classArr = new ArrayList <>();
        ingredientArr = new ArrayList <>();
        phosphorusArr = new ArrayList <>();
        calciumArr = new ArrayList <>();
        crudeProteinArr = new ArrayList <>();
        resultProportionPercent = new ArrayList <>();
        resultProportionUnit = new ArrayList <>();
        QtyArr = new ArrayList <>();
        resultComment = new ArrayList <>();


        Cursor cursor = dbHelper.getFormulationResult(formNo);

        while (cursor.moveToNext()) {
            ingredientArr.add(cursor.getString(1));
            resultProportionPercent.add(cursor.getDouble(3));
            resultProportionUnit.add(cursor.getDouble(4));
            resultComment.add(cursor.getString(6));

            QtyArr.add(cursor.getInt(5));
            classArr.add(cursor.getString(10));
            crudeProteinArr.add(cursor.getDouble(7));
            calciumArr.add(cursor.getDouble(8));
            phosphorusArr.add(cursor.getDouble(9));
        }

        Double total = 0.00;

        for (int i = 0; i < QtyArr.size(); i++) {
            total += QtyArr.get(i);
        }

//        proporText.setText(String.format("(%s)", total));

        ArrayList <FormulatedDetails> list = new ArrayList <>();


        for (int i = 0; i < ingredientArr.size(); i++) {

            list.add(new FormulatedDetails(ingredientArr.get(i),
                    classArr.get(i),
                    crudeProteinArr.get(i),
                    calciumArr.get(i),
                    phosphorusArr.get(i),
                    QtyArr.get(i),
                    resultComment.get(i),
                    resultProportionPercent.get(i),
                    resultProportionUnit.get(i),
                    QtyArr.get(i)
            ));
        }


        CustomFormulatedAdapter customFormulatedAdapter = new CustomFormulatedAdapter(this, R.layout.formulated_card, list);
        formuListView.setAdapter(customFormulatedAdapter);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void openMainActivity(MenuItem item) {
        Intent intent = new Intent(SummaryActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void openAccountActivity(MenuItem item) {
        Intent intent = new Intent(SummaryActivity.this, AccountActivity.class);
        startActivity(intent);
    }

    public void openNotificationActivity(MenuItem item) {
        Intent intent = new Intent(SummaryActivity.this, NotificationActivity.class);
        startActivity(intent);
    }

    public void openSavedActivityActivity(MenuItem item) {
        Intent intent = new Intent(SummaryActivity.this, SavedActivityActivity.class);
        startActivity(intent);
    }

    public void openAboutActivity(MenuItem item) {
        Intent intent = new Intent(SummaryActivity.this, AboutActivity.class);
        startActivity(intent);
    }

    public void openHelpActivity(MenuItem item) {
        Intent intent = new Intent(SummaryActivity.this, HelpActivity.class);
        startActivity(intent);
    }
}