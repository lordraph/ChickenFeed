package com.acgg.chickenfeed;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.acgg.chickenfeed.data.ChickenFeedDbContract;
import com.acgg.chickenfeed.data.ChickenFeedHelper;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static android.R.layout.simple_dropdown_item_1line;
import static java.lang.String.valueOf;

public class CreateDietActivity extends AppCompatActivity {
    ChickenFeedHelper dbHelper;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolBar;
    AutoCompleteTextView chooseFirstIngrid, chooseSecondIngrid, chooseThirdIngrid, chooseFourIngrid;
    EditText edit_1, secondqty_edit_text, thirdqty_edit_text, fourqty_edit_text;
    ArrayList<String> ingredientSelected, classIngredientSelected, newIngredient, commentArr;
    ArrayList<Double> calciumNutrient, crudeProteinNutrient, phosphorusNutrient, newProportion;
    ArrayList<Double> getNewProportionUnit;
    ArrayList<Integer> energyNutrient, qtyselected, qtySpecified;
    Spinner quantityTypeSpinner;
    int numOfSelectedFeed=0, noOfFomulation=0;
    Button createmixBtn;
    RadioGroup radioGroup;
    String birdSelected;
    EditText getQtyToMix;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_diet);

        // Instantiate the database
        dbHelper = new ChickenFeedHelper(this);

        //find the autocomplete TextView

        chooseFirstIngrid =  findViewById(R.id.ingredient1_editText);
        chooseSecondIngrid = findViewById(R.id.ingredient2_editText);
        chooseThirdIngrid = findViewById(R.id.ingredient3_editText);
        chooseFourIngrid = findViewById(R.id.ingredient4_editText);



//        finding edit text
        edit_1 = findViewById(R.id.quantity1_editText);
        secondqty_edit_text = findViewById(R.id.quantity2_editText);
        thirdqty_edit_text = findViewById(R.id.quantity3_editText);
        fourqty_edit_text = findViewById(R.id.quantity4_editText);

        // Find the quantity type spinner
        quantityTypeSpinner= findViewById(R.id.quantitySpinner);

//        Find Create mnix button
        createmixBtn = findViewById(R.id.createDietMix_button);

        // Find the bird category selected
        radioGroup = findViewById(R.id.selectBirdradio);

        //Find qty to mix edit textview
        getQtyToMix = findViewById(R.id.qty_to_mix_edit_text);


        mToolBar = findViewById(R.id.nav_actionBar);
        setSupportActionBar(mToolBar);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.nav_view);
        NavigationMenuView navigationMenuView = (NavigationMenuView) navigationView.getChildAt(0);
        navigationMenuView.addItemDecoration(new DividerItemDecoration(CreateDietActivity.this, DividerItemDecoration.VERTICAL));




        populateTextView();
        populateSpinner();
        selectBirdToFormulate();
        mix();




//        edit_1.setText(String.valueOf(numOfSelectedFeed));

    }

    public void mix(){
        createmixBtn.setOnClickListener(v -> {

            // Formulate
            if(numOfFeedSelected() == 2){
//                    formulate
                formulateForTwoFeed();

               Intent intent = new Intent(this, SummaryActivity.class);
               intent.putExtra("formuNo", noOfFomulation);
               startActivity(intent);

            }else{
                Toast.makeText(CreateDietActivity.this, "Please Select At least " +
                        "two Ingredient ", Toast.LENGTH_SHORT).show();
            }


        });
    }

    public void getNumberofFormulation(){

//        get the number of formulation made so far from the database
        Cursor numOfFormu = dbHelper.getFormuRecord();

        while(numOfFormu.moveToNext()) {

//            Toast.makeText(this, "updated" + String.valueOf(numOfFormu.getInt(1)), Toast.LENGTH_SHORT).show();
            noOfFomulation = numOfFormu.getInt(1);
        }
//        increment by 1
        noOfFomulation++;

//        store back

        if(dbHelper.updateFomulatedRecord(noOfFomulation)){
//            Toast.makeText(this, "updated", Toast.LENGTH_SHORT).show();
        }else{

            Toast.makeText(this, "failed updated", Toast.LENGTH_SHORT).show();
        }


    }

    public void selectBirdToFormulate(){
        birdSelected = "";

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {

            switch(checkedId){
                case R.id.grower_radioButton:
                    birdSelected = "Grower";
                    break;
                case R.id.layer_radioButton:
                    birdSelected = "Layer";
                    break;

                    default:
                        Toast.makeText(CreateDietActivity.this, "Please select " +
                                "a bird", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void formulateForTwoFeed(){
    // Check if the formulation can be carried out

    //        check protein formulation
        if(birdSelected.length() != 0){
            //  check the bird selected
            if(birdSelected.equals("Grower")){
                double Crude_protein = 8.0, Calcium =0.8 ,Phosphorus = 0.4;

            // Check if the nutrient meet the requirement
            if((Collections.max(crudeProteinNutrient) >= Crude_protein) && (Collections.min(crudeProteinNutrient) < Crude_protein) ){



                formulateProtein(Crude_protein, birdSelected);

//                Toast.makeText(CreateDietActivity.this, "formulation is possible", Toast.LENGTH_SHORT).show();


            }else{

                Toast.makeText(CreateDietActivity.this, "Fomulation cannot be made", Toast.LENGTH_SHORT).show();


            }

            }else{

                double Crude_protein = 8.5, Calcium = 3.4, Phosphorus = 0.32;
                // Check if the nutrient meet the requirement
                if((Collections.max(crudeProteinNutrient) >= Crude_protein) && (Collections.min(crudeProteinNutrient) < Crude_protein) ){


                    formulateProtein(Crude_protein,birdSelected );

//                    Toast.makeText(CreateDietActivity.this, "crop ", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(CreateDietActivity.this, "Fomulation cannot be made", Toast.LENGTH_SHORT).show();


                }
            }

        }else{
            Toast.makeText(CreateDietActivity.this, "Please Select a bird", Toast.LENGTH_SHORT).show();
        }

    }

    public void addFormulatedIngredientToDb(String birdCat){

        for(int i=0; i<ingredientSelected.size(); i++){
            dbHelper.addIngredient(ingredientSelected.get(i),noOfFomulation, birdCat, qtyselected.get(i),
                    classIngredientSelected.get(i),calciumNutrient.get(i),crudeProteinNutrient.get(i),
                    phosphorusNutrient.get(i),energyNutrient.get(i));
        }

    }


    public void formulateProtein(Double targetProtein, String birdCat){
        //Declare initiate variable
        commentArr = new ArrayList <>();
        qtySpecified = new ArrayList <>();
        newIngredient = new ArrayList <>();
        newProportion = new ArrayList <>();
        getNewProportionUnit = new ArrayList <>();

        // get the number of formulation made
        getNumberofFormulation();

        // add formulated ingredient into database
        addFormulatedIngredientToDb(birdCat);


    // The crop with max crude protein value
        String ingredientWithMaxCP = "";
        String ingredientWithMinCP = "";

        Integer QtySelectedForMaxCP = qtyselected.get(crudeProteinNutrient.indexOf(Collections.max(crudeProteinNutrient)));
        Integer QtySelectedForMinCP = qtyselected.get(crudeProteinNutrient.indexOf(Collections.min(crudeProteinNutrient)));

        qtySpecified.add(QtySelectedForMaxCP);
        qtySpecified.add(QtySelectedForMinCP);


        Double ingredientWithMaxCPValue = 0.0;
        Double ingredientWithMinCPValue = 0.00;

        ingredientWithMaxCP = ingredientSelected.get(crudeProteinNutrient.indexOf(Collections.max(crudeProteinNutrient)));
        ingredientWithMinCP = ingredientSelected.get(crudeProteinNutrient.indexOf(Collections.min(crudeProteinNutrient)));

        // store the ingredient selected
        newIngredient.add(ingredientWithMaxCP);
        newIngredient.add(ingredientWithMinCP);


        //   Toast.makeText(CreateDietActivity.this, "crop" + ingredientWithMaxCP, Toast.LENGTH_SHORT).show();
        ingredientWithMaxCPValue = Collections.max(crudeProteinNutrient);
        ingredientWithMinCPValue = Collections.min(crudeProteinNutrient);




        // Step 1 of the calculation
        Double newIngredientWithMaxCPValue = Math.abs(targetProtein - ingredientWithMaxCPValue);
        Double newIngredientWithMinCPValue = Math.abs(targetProtein - ingredientWithMinCPValue);

        Double TotalValue = newIngredientWithMaxCPValue + newIngredientWithMinCPValue;

        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        // Step 2 of the calculation
        Double getPercentForIngreWithMinValue = (newIngredientWithMaxCPValue/TotalValue) * 100;
        Double getPercentForIngreWithMaxValue = (newIngredientWithMinCPValue/TotalValue) * 100;

        // store proportion percent
        newProportion.add(getPercentForIngreWithMaxValue);
        newProportion.add(getPercentForIngreWithMinValue);

        // Step 3 of the calculation
        Double NewCPForIngreWithMinValue = getPercentForIngreWithMinValue * (ingredientWithMinCPValue/100);
        Double NewCPForIngreWithMaxValue = getPercentForIngreWithMaxValue * (ingredientWithMaxCPValue/100);

        // Step 4 of of the calculation
        Double qtyOfMixMinValueCP = (Double.parseDouble(getQtyToMix.getText().toString())/100) * getPercentForIngreWithMinValue;
        Double qtyOfMixMaxValueCP = (Double.parseDouble(getQtyToMix.getText().toString())/100) * getPercentForIngreWithMaxValue;

        getNewProportionUnit.add(qtyOfMixMaxValueCP);
        getNewProportionUnit.add(qtyOfMixMinValueCP);


//        Toast.makeText(this, "Crop: " + ingredientWithMinCP + " Qty Mix: " +  decimalFormat.format(qtyOfMixMaxValueCP), Toast.LENGTH_SHORT ).show();
//        Toast.makeText(this, "Crop: " + ingredientWithMinCP + " Qty Mix: " +  decimalFormat.format(qtyOfMixMinValueCP), Toast.LENGTH_SHORT ).show();

        if(qtyOfMixMaxValueCP <= QtySelectedForMaxCP){
            commentArr.add("Appropriate");
        }else{
            commentArr.add("Get " + String.valueOf(qtyOfMixMaxValueCP - QtySelectedForMaxCP) + " more");
        }

        if(qtyOfMixMinValueCP <= QtySelectedForMinCP){
            commentArr.add("Appropriate");
        }else{
            commentArr.add("Get " + String.valueOf(decimalFormat.format(qtyOfMixMinValueCP - QtySelectedForMinCP)) + quantityTypeSpinner.getSelectedItem().toString() +" more");
        }

        addResultToDb();


    }

    private void addResultToDb() {
        for(int i=0; i<numOfSelectedFeed; i++) {
            dbHelper.addFormulationResult(newIngredient.get(i),noOfFomulation,newProportion.get(i),getNewProportionUnit.get(i), qtySpecified.get(i),commentArr.get(i));
        }
    }


    //Check the number to feeds to be formulated
    public int numOfFeedSelected(){

//        Declare initiate variable

        numOfSelectedFeed = 0;
        classIngredientSelected = new ArrayList <>();
        qtyselected = new ArrayList <>();
         ingredientSelected = new ArrayList <>();
         calciumNutrient  = new ArrayList <>();
         crudeProteinNutrient  = new ArrayList <>();
         phosphorusNutrient  = new ArrayList <>();
         energyNutrient  = new ArrayList <>();

        if ((chooseFirstIngrid.getText().length()) != 0 && (edit_1.getText().length() != 0)){
            numOfSelectedFeed++;
            qtyselected.add(Integer.parseInt(edit_1.getText().toString()));
            // Get the selected ingredient and their respective nutrient content
            getFeedNut(chooseFirstIngrid.getText().toString());

        }

        if ((chooseSecondIngrid.getText().length() != 0) &&  (secondqty_edit_text.getText().length() != 0)){
            numOfSelectedFeed++;
            qtyselected.add(Integer.parseInt(secondqty_edit_text.getText().toString()));
            getFeedNut(chooseSecondIngrid.getText().toString());
        }

        if ((chooseThirdIngrid.getText().length() != 0) && (thirdqty_edit_text.getText().length() != 0)){
            numOfSelectedFeed++;
            qtyselected.add(Integer.parseInt(thirdqty_edit_text.getText().toString()));
            getFeedNut(chooseThirdIngrid.getText().toString());
        }

        if ((chooseFourIngrid.getText().length() != 0) && (fourqty_edit_text.getText().length() != 0)){
            numOfSelectedFeed++;
            qtyselected.add(Integer.parseInt(fourqty_edit_text.getText().toString()));
            getFeedNut(chooseFourIngrid.getText().toString());
        }

//        edit_1.setText(String.valueOf(ingredientSelected.size()));

        return numOfSelectedFeed;
    }


    public void getFeedNut(String selectedFeed){

//       insert id, feed_ingridient,class,crude_protein,energy,calcium,phosphorus
        Cursor getFeedDetails = dbHelper.getFeedNutrients();

        while(getFeedDetails.moveToNext()){
            if(selectedFeed.equals(getFeedDetails.getString(1))){
                ingredientSelected.add(getFeedDetails.getString(1));
                classIngredientSelected.add(getFeedDetails.getString(2));
                crudeProteinNutrient.add(getFeedDetails.getDouble(3));
                energyNutrient.add(getFeedDetails.getInt(4));
                calciumNutrient.add(getFeedDetails.getDouble(5));
                phosphorusNutrient.add(getFeedDetails.getDouble(6));


//                Toast.makeText(this, "get count" + selectedFeed, Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Populate the spinner
    public void populateSpinner(){
        String[] quantityType = new String[]{"Kg", "g"};
        ArrayAdapter qtyArrayAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item, quantityType);

        qtyArrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        quantityTypeSpinner.setAdapter(qtyArrayAdapter);
    }

    //   get all ingridient from the database and populate them into a textview
    public void populateTextView(){

        Cursor getAllFeed = dbHelper.getTable();
        String[] Ingrident = new String[getAllFeed.getCount()];


        //  Populate AutocompleteTextView with All crop
        int i=0;
        while(getAllFeed.moveToNext()){
            Ingrident[i] = getAllFeed.getString(0);
            i++;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter <>(this, simple_dropdown_item_1line, Ingrident);
        //   Populate Autocomplete
        chooseFirstIngrid.setAdapter(adapter);
        chooseSecondIngrid.setAdapter(adapter);
        chooseThirdIngrid.setAdapter(adapter);
        chooseFourIngrid.setAdapter(adapter);

        chooseFirstIngrid.setThreshold(1);
        chooseSecondIngrid.setThreshold(1);
        chooseThirdIngrid.setThreshold(1);
        chooseFourIngrid.setThreshold(1);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void openMainActivity(MenuItem item) {
        Intent intent = new Intent(CreateDietActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void openAccountActivity(MenuItem item) {
        Intent intent = new Intent(CreateDietActivity.this, AccountActivity.class);
        startActivity(intent);
    }

    public void openNotificationActivity(MenuItem item) {
        Intent intent = new Intent(CreateDietActivity.this, NotificationActivity.class);
        startActivity(intent);
    }

    public void openSavedActivityActivity(MenuItem item) {
        Intent intent = new Intent(CreateDietActivity.this, SavedActivityActivity.class);
        startActivity(intent);
    }

    public void openAboutActivity(MenuItem item) {
        Intent intent = new Intent(CreateDietActivity.this, AboutActivity.class);
        startActivity(intent);
    }

    public void openHelpActivity(MenuItem item) {
        Intent intent = new Intent(CreateDietActivity.this, HelpActivity.class);
        startActivity(intent);
    }

}
