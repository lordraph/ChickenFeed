package com.acgg.chickenfeed;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.acgg.chickenfeed.data.ChickenFeedHelper;

import java.text.DecimalFormat;

public class SummaryActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolBar;
    private ChickenFeedHelper dbHelper;
    private Integer formNo = 0;
    private TableLayout formulatedTable, outcomeTable;
    DecimalFormat decimalFormat;
    TextView crudeProteinValue, valuePhosphorus, valueCalcium, valueEnergy, proporUnitText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        dbHelper = new ChickenFeedHelper(this);


        decimalFormat = new DecimalFormat("0.00");


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

        //get the display table layout
        formulatedTable = findViewById(R.id.table_ingreSelect);
        outcomeTable = findViewById(R.id.table_outcome);

        //get the textview of crudeProtein, phosphorus, calcium and energy for calculated analysis
        crudeProteinValue = findViewById(R.id.value_crudeprotein);
        valuePhosphorus = findViewById(R.id.value_phosphorus);
        valueCalcium = findViewById(R.id.value_calcium);
        valueEnergy = findViewById(R.id.value_energy);

        //get proportion unit text
        proporUnitText = findViewById(R.id.proportionKg_text);


        loadIngredientForMix();
        loadcalculateAnalysis();
        getQtyToMix();

    }

    //Proportion(Kg)
    public void getQtyToMix(){
        Cursor getQtytoMix = dbHelper.getQuantityToMix(formNo);

        while(getQtytoMix.moveToNext()){
            proporUnitText.setText(String.format("Proportion(%skg)", decimalFormat.format(getQtytoMix.getDouble(2))));
        }
    }

    //Double crudeProtein, Double calcium, Double phosphorus, Double energy

    public void loadcalculateAnalysis() {


        Cursor cursor = dbHelper.getCalculatedAnalysis(formNo);

        while (cursor.moveToNext()) {

            crudeProteinValue.setText(decimalFormat.format(cursor.getDouble(1)));
            valueCalcium.setText(decimalFormat.format(cursor.getDouble(3)));
            valuePhosphorus.setText(decimalFormat.format(cursor.getDouble(4)));
            valueEnergy.setText(decimalFormat.format(cursor.getDouble(5)));
        }

    }


    public void loadIngredientForMix() {


        Cursor cursor = dbHelper.getFormulationResult(formNo);

        while (cursor.moveToNext()) {
            //Create a row for formulated ingredient
            TableRow new_row = new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
            new_row.setLayoutParams(lp);

            TextView ingredient = new TextView(this);
            ingredient.setText(cursor.getString(1));
            ingredient.setGravity(Gravity.CENTER_HORIZONTAL);
            new_row.addView(ingredient);

            TextView classArr = new TextView(this);
            classArr.setText(cursor.getString(10));
            classArr.setGravity(Gravity.CENTER_HORIZONTAL);
            new_row.addView(classArr);

            TextView crudeProteinArr = new TextView(this);
            crudeProteinArr.setText(decimalFormat.format(cursor.getDouble(7)));
            crudeProteinArr.setGravity(Gravity.CENTER_HORIZONTAL);
            new_row.addView(crudeProteinArr);

            TextView phosphorusArr = new TextView(this);
            phosphorusArr.setText(decimalFormat.format(cursor.getDouble(9)));
            phosphorusArr.setGravity(Gravity.CENTER_HORIZONTAL);
            new_row.addView(phosphorusArr);

            TextView calciumArr = new TextView(this);
            calciumArr.setText(decimalFormat.format(cursor.getDouble(8)));
            calciumArr.setGravity(Gravity.CENTER_HORIZONTAL);
            new_row.addView(calciumArr);

            TextView QtyArr = new TextView(this);
            QtyArr.setText(decimalFormat.format(cursor.getInt(5)));
            QtyArr.setGravity(Gravity.CENTER_HORIZONTAL);
            new_row.addView(QtyArr);

            formulatedTable.addView(new_row);

            //Create a new row for outcome formulated table
            TableRow new_row_outcome = new TableRow(this);
            TableRow.LayoutParams lpoutcome = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
            new_row_outcome.setLayoutParams(lpoutcome);

            TextView ingredientOutcome = new TextView(this);
            ingredientOutcome.setText(cursor.getString(1));
            ingredientOutcome.setGravity(Gravity.CENTER_HORIZONTAL);
            new_row_outcome.addView(ingredientOutcome);

            TextView resultProportionPercent = new TextView(this);
            resultProportionPercent.setText(decimalFormat.format(cursor.getDouble(3)));
            resultProportionPercent.setGravity(Gravity.CENTER_HORIZONTAL);
            new_row_outcome.addView(resultProportionPercent);

            TextView resultProportionUnit = new TextView(this);
            resultProportionUnit.setText(decimalFormat.format(cursor.getDouble(4)));
            resultProportionUnit.setGravity(Gravity.CENTER_HORIZONTAL);
            new_row_outcome.addView(resultProportionUnit);

            TextView QtyArrcalc = new TextView(this);
            QtyArrcalc.setText(decimalFormat.format(cursor.getInt(5)));
            QtyArrcalc.setGravity(Gravity.CENTER_HORIZONTAL);
            new_row_outcome.addView(QtyArrcalc);

            TextView resultComment = new TextView(this);
            resultComment.setText(cursor.getString(6));
            resultComment.setGravity(Gravity.CENTER_HORIZONTAL);
            new_row_outcome.addView(resultComment);

            outcomeTable.addView(new_row_outcome);

        }


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