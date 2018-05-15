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
import com.acgg.chickenfeed.summaryAdapter.CustomFormulatedAdapter;
import com.acgg.chickenfeed.summaryAdapter.FormulatedDetails;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SummaryActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolBar;
    private LinearLayout linearLayout;
    private TableLayout tableLayout;
    private ChickenFeedHelper dbHelper;
    private ArrayList<String> classArr, birdCatArr, ingredientArr, classHeaderArr, resultFeedIngre, resultComment;
    private ArrayList<Integer> energyArr, QtyArr, formulationNoArr, resultqtySpecified;
    private ArrayList<Double> phosphorus, calciumArr, crudeProteinArr, resultProportionPercent, resultProportionUnit;
    private Integer formNo=0;
    private ListView formuListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        formuListView = findViewById(R.id.listview_Ingredient);

        dbHelper = new ChickenFeedHelper(this);
//        tableLayout = findViewById(R.id.select_ingredient_mixing_table);
//        linearLayout = findViewById(R.id.summary_View_Layout);

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
        formNo = intent.getIntExtra("formuNo",0);

        loadIngredientForMix();
//        outcomeFromFormulation();

    }

    private void outcomeFromFormulation(){
//        resultFeedIngre = new ArrayList <>();
//        resultProportionPercent = new ArrayList <>();
//        resultProportionUnit = new ArrayList <>();
//        resultqtySpecified = new ArrayList <>();
//        resultComment = new ArrayList <>();
//        classHeaderArr = new ArrayList <>();
//
//
//        // Load the table header
//        classHeaderArr.add("Ingredient");
//        classHeaderArr.add("Proportion(%)");
//        classHeaderArr.add("Proportion");
//        classHeaderArr.add("Quantity Specified");
//        classHeaderArr.add("Comment");

        //get data from database

        Cursor cursor = dbHelper.getFormulationResult(formNo);

        while(cursor.moveToNext()){
//            resultFeedIngre.add(cursor.getString(1));
//            resultProportionPercent.add(cursor.getDouble(3));
//            resultProportionUnit.add(cursor.getDouble(4));
//            resultqtySpecified.add(cursor.getInt(5));
//            resultComment.add(cursor.getString(6));
        }



//        // create textview for ingredient name
//        TextView ingredienttxt= new TextView(this);
//        ingredienttxt.setText("Outcome from Formulation");
//        ingredienttxt.setTypeface(null, Typeface.BOLD);
//        ingredienttxt.setTextColor(Color.parseColor("#1f7101"));
//        linearLayout.addView(ingredienttxt);
//
//
//        // Create a table
//        TableLayout resultTable = new TableLayout(this);
//        TableLayout.LayoutParams tp = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT);
//        resultTable.setLayoutParams(tp);
//        resultTable.setStretchAllColumns(true);
//
//        DecimalFormat decimalFormat = new DecimalFormat("0.00");
//
//        //        populate in a table
//
//        for(int i=0; i<classHeaderArr.size(); i++){
//
//            TableRow new_row = new TableRow(this);
//            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
//            new_row.setLayoutParams(lp);
//
//
//            // create textview for ingredient name
//            TextView ingredientName= new TextView(this);
//            ingredientName.setText(classHeaderArr.get(i));
//            ingredientName.setTypeface(null, Typeface.BOLD);
//
//            if( i%2 == 0){
//                ingredientName.setBackgroundColor(Color.parseColor("#a5d6a7"));
//            }else{
//                ingredientName.setBackgroundColor(Color.parseColor("#4caf50"));
//
//            }
//            new_row.addView(ingredientName);
//
//            for (int eachRow=0; eachRow < resultFeedIngre.size(); eachRow++){
//
//                if(classHeaderArr.get(i).equals("Ingredient")) {
//                    TextView eachName = new TextView(this);
//                    eachName.setText(String.valueOf(resultFeedIngre.get(eachRow)));
//                    eachName.setBackgroundColor(Color.parseColor("#a5d6a7"));
//                    new_row.addView(eachName);
//                }
//
//
//                if(classHeaderArr.get(i).equals("Proportion(%)")) {
//                    TextView eachName = new TextView(this);
//                    eachName.setText(String.valueOf(decimalFormat.format(resultProportionPercent.get(eachRow))));
//                    eachName.setBackgroundColor(Color.parseColor("#4caf50"));
//                    new_row.addView(eachName);
//                }
//
//                if(classHeaderArr.get(i).equals("Proportion")) {
//                    TextView eachName = new TextView(this);
//                    eachName.setText(String.valueOf(decimalFormat.format(resultProportionUnit.get(eachRow))));
//                    eachName.setBackgroundColor(Color.parseColor("#a5d6a7"));
//                    new_row.addView(eachName);
//                }
//
//                if(classHeaderArr.get(i).equals("Quantity Specified")) {
//                    TextView eachName = new TextView(this);
//                    eachName.setText(String.valueOf(resultqtySpecified.get(eachRow)));
//                    eachName.setBackgroundColor(Color.parseColor("#4caf50"));
//                    new_row.addView(eachName);
//                }
//
//                if(classHeaderArr.get(i).equals("Comment")) {
//                    TextView eachName = new TextView(this);
//                    eachName.setText(String.valueOf(resultComment.get(eachRow)));
//                    eachName.setBackgroundColor(Color.parseColor("#a5d6a7"));
//                    new_row.addView(eachName);
//                }
//
//
//
//
//            }
//            resultTable.addView(new_row);
//        }
//
//
//        linearLayout.addView(resultTable);

    }



    private void loadIngredientForMix() {

//        ingredientArr = new ArrayList<>();
//        formulationNoArr = new ArrayList<>();
//        birdCatArr  = new ArrayList<>();
//        classHeaderArr  = new ArrayList<>();
//        QtyArr = new ArrayList<>();
//        classArr = new ArrayList<>();
//        crudeProteinArr = new ArrayList<>();
//        energyArr  = new ArrayList<>();
//        calciumArr  = new ArrayList<>();
//        phosphorus  = new ArrayList<>();


        // Load the header into an array

//        classHeaderArr.add("Ingredient");
//        classHeaderArr.add("Class");
//        classHeaderArr.add("Crude Protein");
//        classHeaderArr.add("Calcium");
//        classHeaderArr.add("Phosphorus");
//        classHeaderArr.add("Quantity");


        Cursor getIngridient = dbHelper.getIngredient(formNo);

//        Toast.makeText(this, "get count: " + String.valueOf(getIngridient.getCount()), Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, "get count: " + String.valueOf(formNo), Toast.LENGTH_SHORT).show();

//         public FormulatedDetails(String ingredient, String ingredientlass, Double crudeProteinFormulated, Double calciumFormulated, Double phosphorusFormulated, Integer qtySpecified) {
//        this.ingredient = ingredient;
//        this.IngredientClass = ingredientlass;
//        this.crudeProteinFormulated = crudeProteinFormulated;
//        this.calciumFormulated = calciumFormulated;
//        this.phosphorusFormulated = phosphorusFormulated;
//        this.qtySpecified = qtySpecified;
        //  this.commentFormulated = commentFormulated;
        //        this.proportion = proportion;
        //        this.proportionUnit = proportionUnit;
        //        this.qtyAvailable = qtyAvailable;
//    }
        ArrayList<FormulatedDetails> list = new ArrayList<>();

        while(getIngridient.moveToNext()){
            list.add(new FormulatedDetails(getIngridient.getString(1),
                    getIngridient.getString(5), getIngridient.getDouble(6),
                    getIngridient.getDouble(8),
                    getIngridient.getDouble(9),
                    getIngridient.getInt(4),
                    "Appro",
                    0.00, 0.00, 10
                    ));
//            ingredientArr.add(getIngridient.getString(1));
//            QtyArr.add();
//            classArr.add();
//            crudeProteinArr.add();
//            energyArr.add(getIngridient.getInt(7));
//            calciumArr.add();
//            phosphorus.add();

        }

        CustomFormulatedAdapter customFormulatedAdapter = new CustomFormulatedAdapter(this, R.layout.formulated_card, list);
        formuListView.set
        formuListView.setAdapter(customFormulatedAdapter);

//        populate in a table

//        for(int i=0; i<classHeaderArr.size(); i++){
//
//            TableRow new_row = new TableRow(this);
//            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
//            new_row.setLayoutParams(lp);
//
//
//            // create textview for ingredient name
//            TextView ingredientName = new TextView(this);
//            ingredientName.setText(classHeaderArr.get(i));
//            ingredientName.setTypeface(null, Typeface.BOLD);
//
//            if( i%2 == 0){
//                ingredientName.setBackgroundColor(Color.parseColor("#a5d6a7"));
//            }else{
//                ingredientName.setBackgroundColor(Color.parseColor("#4caf50"));
//
//            }
//            new_row.addView(ingredientName);
//
//            for (int eachRow=0; eachRow < ingredientArr.size(); eachRow++){
//
//                if(classHeaderArr.get(i).equals("Ingredient")) {
//                    TextView eachName = new TextView(this);
//                    eachName.setText(String.valueOf(ingredientArr.get(eachRow)));
//                    eachName.setBackgroundColor(Color.parseColor("#a5d6a7"));
//                    new_row.addView(eachName);
//                }
//
//
//                if(classHeaderArr.get(i).equals("Class")) {
//                    TextView eachName = new TextView(this);
//                    eachName.setText(String.valueOf(classArr.get(eachRow)));
//                    eachName.setBackgroundColor(Color.parseColor("#4caf50"));
//                    new_row.addView(eachName);
//                }
//
//                if(classHeaderArr.get(i).equals("Crude Protein")) {
//                    TextView eachName = new TextView(this);
//                    eachName.setText(String.valueOf(crudeProteinArr.get(eachRow)));
//                    eachName.setBackgroundColor(Color.parseColor("#a5d6a7"));
//                    new_row.addView(eachName);
//                }
//
//                if(classHeaderArr.get(i).equals("Calcium")) {
//                    TextView eachName = new TextView(this);
//                    eachName.setText(String.valueOf(calciumArr.get(eachRow)));
//                    eachName.setBackgroundColor(Color.parseColor("#4caf50"));
//                    new_row.addView(eachName);
//                }
//
//                if(classHeaderArr.get(i).equals("Phosphorus")) {
//                    TextView eachName = new TextView(this);
//                    eachName.setText(String.valueOf(phosphorus.get(eachRow)));
//                    eachName.setBackgroundColor(Color.parseColor("#a5d6a7"));
//                    new_row.addView(eachName);
//                }
//
//                if(classHeaderArr.get(i).equals("Quantity")) {
//                    TextView eachName = new TextView(this);
//                    eachName.setText(String.valueOf(QtyArr.get(eachRow)));
//                    eachName.setBackgroundColor(Color.parseColor("#4caf50"));
//                    new_row.addView(eachName);
//                }
//
//
//
//            }
//            tableLayout.addView(new_row);
//        }

//        Toast.makeText(this, "size "+ ingredientArr.size(), Toast.LENGTH_SHORT).show();




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