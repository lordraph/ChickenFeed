package com.acgg.chickenfeed;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.acgg.chickenfeed.data.ChickenFeedHelper;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

import static android.R.layout.simple_dropdown_item_1line;

public class CreateDietActivity extends AppCompatActivity {
    ChickenFeedHelper dbHelper;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolBar;
    AutoCompleteTextView chooseFirstIngrid, chooseSecondIngrid, chooseThirdIngrid, chooseFourIngrid;
    EditText edit_1, secondqty_edit_text, thirdqty_edit_text, fourqty_edit_text;
    ArrayList<String> ingredientSelected, classIngredientSelected, newIngredient, commentArr, forClass;
    ArrayList<Double> calciumNutrient, crudeProteinNutrient, phosphorusNutrient, newProportion, forCalcium, forCrudeProtein, forPhosphorus;
    ArrayList<Double> getNewProportionUnit, calCrudeProtein, calCalcium, calPhosphorus, calEnergy;
    ArrayList<Double> groupA, groupB, ordGroupA, ordGroupB, comGroupA, comGroupB, forCalA, forPhosB, forPhosA, forCalB ;
    ArrayList<Integer> energyNutrient, qtyselected, qtySpecified, forEnergy, ordqtyAvailableA, ordqtyAvailableB, forenergyA, forenergyB;
    Spinner quantityTypeSpinner;
    int numOfSelectedFeed=0, noOfFomulation=0,  assProp2first = 51, assProp2secon = 49, qtyTMix = 0 ;
    ArrayList<String>  ingredientGrpA, ingredientGrpB, ordIngredientGrpA, ordIngredientGrpB;
    ArrayList<String>  ordClassA, ordClassB;
    Button createmixBtn;
    RadioGroup radioGroup;
    String birdSelected;
    EditText getQtyToMix;
    boolean formulate = false, recalculate = false, clickYes = false, clickNo = false;
    boolean minConditionCheck = false, maxConditionCheck = false;
    Double qtyToMix, Crude_protein, Calcium, Phosphorus, Total = 0.00;
    double Crude_proteintarget = 0.0, Calciumtarget = 0.00 ,Phosphorustarget = 0.0;
    DecimalFormat decimalFormat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_diet);

        decimalFormat = new DecimalFormat("0.00");

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

//        Find Create mix button
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

    }


    public void mix(){
        createmixBtn.setOnClickListener(v -> {

            // Formulate
            if(numOfFeedSelected() == 2){
//                    formulate
                formulateForTwoFeed();

                if(formulate && maxConditionCheck && minConditionCheck) {

                    Intent intent = new Intent(this, SummaryActivity.class);
                    intent.putExtra("formuNo", noOfFomulation);
                    startActivity(intent);
                }



            }else if(numOfFeedSelected() == 3){

                formulateForThreeFeeds();

                if(formulate) {

                    Intent intent = new Intent(this, SummaryActivity.class);
                    intent.putExtra("formuNo", noOfFomulation);
                    startActivity(intent);
                }

            }
            else{

                Toast.makeText(CreateDietActivity.this, "Please Select At least " +
                        "two Ingredient ", Toast.LENGTH_SHORT).show();
            }


        });
    }

    public void formulateForThreeFeeds(){
        // Check if the formulation can be carried out

        //        check protein formulation
        if(birdSelected.length() != 0){
            //  check the bird selected
            if(birdSelected.equals("Grower")){
//                double Crude_protein = 8.0, Calcium = 0.8 ,Phosphorus = 0.4;

                Crude_proteintarget = 8.0;
                Calciumtarget = 0.8;
                Phosphorustarget = 0.4;

                // Check if the nutrient meet the requirement
                if((Collections.max(crudeProteinNutrient) >= Crude_proteintarget) && (Collections.min(crudeProteinNutrient) < Crude_proteintarget) ){

                    formulate = true;

                    if(formulate){
                        formulateProteinForThreeFeed(Crude_proteintarget, birdSelected);
                    }


                }else{

                    formulate = false;
                    Toast.makeText(CreateDietActivity.this, "Formulation cannot be made", Toast.LENGTH_SHORT).show();

                }

            }else{

                Crude_proteintarget = 8.5;
                Calciumtarget = 3.4;
                Phosphorustarget = 0.32;

//                double Crude_protein = 8.5, Calcium = 3.4, Phosphorus = 0.32;
                // Check if the nutrient meet the requirement
                if((Collections.max(crudeProteinNutrient) >= Crude_proteintarget) && (Collections.min(crudeProteinNutrient) < Crude_proteintarget) ){

                    formulate = true;
                    if(formulate) {
                        formulateProteinForThreeFeed(Crude_proteintarget, birdSelected);
                    }

                }else{
                    formulate = false;
                    Toast.makeText(CreateDietActivity.this, "Formulation cannot be made", Toast.LENGTH_SHORT).show();


                }
            }

        }else{
            Toast.makeText(CreateDietActivity.this, "Please Select a bird", Toast.LENGTH_SHORT).show();
        }

    }

    public void formulateProteinForThreeFeed(Double targetProtein, String birdCat){




        // get the number of formulation made
        getNumberofFormulation();

        // add formulated ingredient into database
        addFormulatedIngredientToDb(birdCat);

        // Seperate the feeds into two groups
        divGroups();
        reOrderGroup();
        calNewCPLevel();
        formulateFeed(groupA, groupB);
        formulateFeed(groupB, groupA);
        storeResultToDb();
        storecalculatedAnalysis(Crude_proteintarget, noOfFomulation, calCalcium.get(0), calPhosphorus.get(0), calEnergy.get(0));


    }

    public void storeResultToDb() {
        for(int i=0; i<numOfSelectedFeed; i++) {
            dbHelper.addFormulationResult(newIngredient.get(i),noOfFomulation,newProportion.get(i),
                    getNewProportionUnit.get(i), qtySpecified.get(i),commentArr.get(i),
                    forCrudeProtein.get(i), forCalcium.get(i),forPhosphorus.get(i), forClass.get(i)
            );
        }
    }

    private void formulateFeed(ArrayList<Double> groupDivA, ArrayList<Double> groupDivB) {

        if(groupDivA.size() > 1){

            //Declare initiate variable
            calCalcium = new ArrayList <>();
            calCrudeProtein = new ArrayList <>();
            calPhosphorus = new ArrayList <>();
            calEnergy = new ArrayList <>();

            forCalcium = new ArrayList <>();
            forPhosphorus = new ArrayList <>();
            forCrudeProtein = new ArrayList <>();
            forClass = new ArrayList <>();
            forEnergy = new ArrayList <>();
            commentArr = new ArrayList <>();

            qtySpecified = new ArrayList <>();
            newIngredient = new ArrayList <>();
            newProportion = new ArrayList <>();
            getNewProportionUnit = new ArrayList <>();
            qtyTMix = Integer.parseInt(getQtyToMix.getText().toString());

            Double groupDivACP = Math.abs(groupDivB.get(0) - Crude_proteintarget);
            Double groupDivBCP = Math.abs(Crude_proteintarget - Total);
            Double forTotal = groupDivACP + groupDivBCP;

            Double newCPFirst = assProp2first * 0.01 * groupDivACP;
            Double newCPsecond = assProp2secon * 0.01 * groupDivACP;

            //Implement step 6
            double prop_100_kg_groupB = groupDivBCP * 100/forTotal;
            double prop_100_kg_groupA_first = newCPFirst * 100/forTotal;
            double prop_100_kg_groupA_second  = newCPsecond * 100/forTotal;

            // Implement step 7
            double propfirstgroupB = 0.01 * prop_100_kg_groupB * groupB.get(0);
            double propfirstgroupA = 0.01 * prop_100_kg_groupA_first * groupA.get(0);
            double propsecondgroupA = 0.01 * prop_100_kg_groupA_second * groupA.get(1);

            //store class
            forClass.add(ordClassB.get(0));
            forClass.add(ordClassA.get(0));
            forClass.add(ordClassA.get(1));

            //store energy
            forEnergy.add(forenergyB.get(0));
            forEnergy.add(forenergyA.get(0));
            forEnergy.add(forenergyA.get(1));

            //store phosphorus
            forPhosphorus.add(forPhosB.get(0));
            forPhosphorus.add(forPhosA.get(0));
            forPhosphorus.add(forPhosA.get(1));

            // store calcium
            forCalcium.add(forCalB.get(0));
            forCalcium.add(forCalA.get(0));
            forCalcium.add(forCalA.get(1));

            //store crude_protein
            forCrudeProtein.add(groupB.get(0));
            forCrudeProtein.add(groupA.get(0));
            forCrudeProtein.add(groupA.get(1));

            //store proportion percentage
            newProportion.add(propfirstgroupB);
            newProportion.add(propfirstgroupA);
            newProportion.add(propsecondgroupA);

            //store ingredient
            newIngredient.add(ordIngredientGrpB.get(0));
            newIngredient.add(ordIngredientGrpA.get(0));
            newIngredient.add(ordIngredientGrpA.get(1));

            //store qty specified
            qtySpecified.add(ordqtyAvailableB.get(0));
            qtySpecified.add(ordqtyAvailableA.get(0));
            qtySpecified.add(ordqtyAvailableA.get(1));


            //scale down proportion to mix
            double scaleprop_groupB = prop_100_kg_groupB * qtyTMix * 0.01;
            double scaleprop_first_groupA = prop_100_kg_groupA_first * qtyTMix * 0.01;
            double scaleprop_second_groupA = prop_100_kg_groupA_second * qtyTMix * 0.01;

            //calculate analysis for calcium
            double anacalcium = (scaleprop_groupB * forCalB.get(0)) + (scaleprop_first_groupA * forCalA.get(0)) + (scaleprop_second_groupA * forCalA.get(1));

            double anaphos = (scaleprop_groupB * forPhosB.get(0)) + (scaleprop_first_groupA * forPhosA.get(0)) + (scaleprop_second_groupA * forPhosA.get(1));

            double anaenergy = (scaleprop_groupB * forenergyB.get(0)) + (scaleprop_first_groupA * forenergyA.get(0)) + (scaleprop_second_groupA * forenergyA.get(1));

            calCalcium.add(anacalcium);
            calPhosphorus.add(anaphos);
            calEnergy.add(anaenergy);

            getNewProportionUnit.add(scaleprop_groupB);
            getNewProportionUnit.add(scaleprop_first_groupA);
            getNewProportionUnit.add(scaleprop_second_groupA);

            //add quantity to mix into the database
//            dbHelper.addQuantityToMix(noOfFomulation, Double.parseDouble(qtyTMix));


            if(scaleprop_groupB <= ordqtyAvailableB.get(0)){
                commentArr.add("Appropriate");
            }else{
                commentArr.add("Get " +  decimalFormat.format(scaleprop_groupB - ordqtyAvailableB.get(0)) + quantityTypeSpinner.getSelectedItem().toString().toLowerCase() + " more");
            }

            if(scaleprop_first_groupA <= ordqtyAvailableA.get(0)){
                commentArr.add("Appropriate");
            }else{
                commentArr.add("Get " + decimalFormat.format(scaleprop_first_groupA - ordqtyAvailableA.get(0)) + quantityTypeSpinner.getSelectedItem().toString().toLowerCase() +" more");
            }

            if(scaleprop_second_groupA <= ordqtyAvailableA.get(1)){
                commentArr.add("Appropriate");
            }else{
                commentArr.add("Get " + decimalFormat.format(scaleprop_second_groupA - ordqtyAvailableA.get(1)) + quantityTypeSpinner.getSelectedItem().toString().toLowerCase() +" more");
            }

            checkQtyScaledDown(scaleprop_groupB, scaleprop_first_groupA, scaleprop_second_groupA);

        }

    }

    private void checkQtyScaledDown(double scaleprop_groupB, double scaleprop_first_groupA, double scaleprop_second_groupA) {

        if(scaleprop_groupB > ordqtyAvailableB.get(0)){
            performPearson();
        }

        if((scaleprop_first_groupA > ordqtyAvailableA.get(0)) && (scaleprop_second_groupA > ordqtyAvailableA.get(1))){
            performPearson();
        }

        if((scaleprop_first_groupA < ordqtyAvailableA.get(0)) && (scaleprop_second_groupA > ordqtyAvailableA.get(1))){
            // 2nd ingridient not appropriate
            assProp2first++;
            assProp2secon--;
            formulateFeed(groupA, groupB);
            formulateFeed(groupB, groupA);

            if(assProp2secon == 92){
                performPearson();
            }
        }

        if((scaleprop_first_groupA > ordqtyAvailableA.get(0)) && (scaleprop_second_groupA < ordqtyAvailableA.get(0))){
            //1st ingredient not appropriate
            assProp2first--;
            assProp2secon++;
            formulateFeed(groupA, groupB);
            formulateFeed(groupB, groupA);

            if(assProp2first == 8){
                performPearson();
            }
        }


    }

    private void performPearson() {
        //Implementing step 1
        checkgroupIngre(groupA, groupB);
        checkgroupIngre(groupB, groupA);

    }

    private void checkgroupIngre(ArrayList<Double> groupA, ArrayList<Double> groupB) {


        if(groupA.size() > 1){
            //Declare initiate variable
            calCalcium = new ArrayList <>();
            calCrudeProtein = new ArrayList <>();
            calPhosphorus = new ArrayList <>();
            calEnergy = new ArrayList <>();
            newProportion = new ArrayList <>();
            getNewProportionUnit = new ArrayList <>();

            qtyTMix = Integer.parseInt(getQtyToMix.getText().toString());
            double total = 0;

            for(int i=0; i<groupA.size(); i++){
                total += groupA.get(i);
            }

            // Step 2
            double firstIngrAPercent = groupA.get(0) * 0.01/total;
            double secondIngrAPercent = groupA.get(1) * 0.01/total;

            //step 3
            double groupCPfirstA = firstIngrAPercent * 0.01 * groupA.get(0);
            double groupCPsecondA = secondIngrAPercent * 0.01 * groupA.get(1);

            double totalCP = groupCPfirstA + groupCPsecondA ;

            //step 4
            double newGroupCP = Math.abs(groupB.get(0) - Crude_proteintarget);
            double newUngroupedCP = Math.abs(totalCP - Crude_proteintarget);
            double totalPart = newGroupCP + newUngroupedCP;

            //step 5
            double newgroupCPfirst = firstIngrAPercent * 0.01 * newGroupCP;
            double newgroupCPsecond = secondIngrAPercent * 0.01 * newGroupCP;

            //step 5
            double ungroupedCPPercent = newUngroupedCP * 100/totalPart;
            double groupedCPFirstPercent = newgroupCPfirst * 100/totalPart;
            double groupedCPSecondPercent = newgroupCPsecond * 100/totalPart;


            //store proportion percentage
            newProportion.add(ungroupedCPPercent);
            newProportion.add(groupedCPFirstPercent);
            newProportion.add(groupedCPSecondPercent);

            //last step
            double ungroupedqtyToMix = ungroupedCPPercent * 0.01 * qtyTMix;
            double groupedFirstqtyToMix = groupedCPFirstPercent * 0.01 * qtyTMix;
            double groupedSecondqtyToMix = groupedCPSecondPercent * 0.01 * qtyTMix;

            getNewProportionUnit.add(ungroupedqtyToMix);
            getNewProportionUnit.add(groupedFirstqtyToMix);
            getNewProportionUnit.add(groupedSecondqtyToMix);

            //calculate analysis for calcium
            double anacalcium = (ungroupedqtyToMix * forCalB.get(0)) + (groupedFirstqtyToMix * forCalA.get(0)) + (groupedSecondqtyToMix * forCalA.get(1));

            double anaphos = (ungroupedqtyToMix * forPhosB.get(0)) + (groupedFirstqtyToMix * forPhosA.get(0)) + (groupedSecondqtyToMix * forPhosA.get(1));

            double anaenergy = (ungroupedqtyToMix * forenergyB.get(0)) + (groupedFirstqtyToMix * forenergyA.get(0)) + (groupedSecondqtyToMix * forenergyA.get(1));

            calCalcium.add(anacalcium);
            calPhosphorus.add(anaphos);
            calEnergy.add(anaenergy);


        }
    }

    private void calNewCPLevel() {

        if(groupA.size() == 2){
            Double firstNutrient = (0.01 * assProp2first) * groupA.get(0);
            Double secondNutrient = (0.01 * assProp2secon) * groupA.get(1);
             Total = firstNutrient + secondNutrient;
        }

        if(groupB.size() == 2){
            Double firstNutrient = (0.01 * assProp2first) * groupA.get(0);
            Double secondNutrient = (0.01 * assProp2secon) * groupA.get(1);
            Total = firstNutrient + secondNutrient;
        }



    }

    private void reOrderGroup() {
        ordIngredientGrpA = new ArrayList <>();
        ordIngredientGrpB = new ArrayList <>();
        ordqtyAvailableA = new ArrayList <>();
        ordqtyAvailableB = new ArrayList <>();
        forCalA = new ArrayList<>();
        forCalB = new ArrayList<>();
        forPhosA = new ArrayList<>();
        forPhosB = new ArrayList<>();
        forenergyA = new ArrayList<>();
        forenergyB = new ArrayList<>();
        ordClassA = new ArrayList<>();
        ordClassB = new ArrayList<>();

        if(groupA.size()>1){
           Collections.sort(groupA);
           for (int i=0; i<groupA.size(); i++){

           ordIngredientGrpA.add(ingredientGrpA.get(comGroupA.indexOf(groupA.get(i))));
           ordqtyAvailableA.add(qtyselected.get(comGroupA.indexOf(groupA.get(i))));
           forCalA.add(calciumNutrient.get(comGroupA.indexOf(groupA.get(i))));
           forPhosA.add(phosphorusNutrient.get(comGroupA.indexOf(groupA.get(i))));
           forenergyA.add(energyNutrient.get(comGroupA.indexOf(groupA.get(i))));
           ordClassA.add(classIngredientSelected.get(comGroupA.indexOf(groupA.get(i))));

           }

            ordIngredientGrpB.add(ingredientGrpB.get(0));
            ordqtyAvailableB.add(qtyselected.get(comGroupB.indexOf(groupB.get(0))));
            forCalB.add(calciumNutrient.get(comGroupB.indexOf(groupB.get(0))));
            forPhosB.add(phosphorusNutrient.get(comGroupB.indexOf(groupB.get(0))));
            forenergyB.add(energyNutrient.get(comGroupB.indexOf(groupB.get(0))));
            ordClassB.add(classIngredientSelected.get(comGroupB.indexOf(groupB.get(0))));
        }

        if(groupB.size()>1){
           Collections.sort(groupB);
           for (int i=0; i<groupB.size(); i++){

           ordIngredientGrpB.add(ingredientGrpB.get(comGroupB.indexOf(groupB.get(i))));
           ordqtyAvailableB.add(qtyselected.get(comGroupB.indexOf(groupB.get(i))));
           forCalB.add(calciumNutrient.get(comGroupB.indexOf(groupB.get(i))));
           forPhosB.add(phosphorusNutrient.get(comGroupB.indexOf(groupB.get(i))));
           forenergyB.add(energyNutrient.get(comGroupB.indexOf(groupB.get(i))));
           ordClassB.add(classIngredientSelected.get(comGroupB.indexOf(groupB.get(i))));
           }

            ordIngredientGrpA.add(ingredientGrpA.get(0));
            ordqtyAvailableA.add(qtyselected.get(comGroupA.indexOf(groupA.get(0))));
            forCalB.add(calciumNutrient.get(comGroupA.indexOf(groupA.get(0))));
            forPhosB.add(phosphorusNutrient.get(comGroupA.indexOf(groupA.get(0))));
            forenergyB.add(energyNutrient.get(comGroupA.indexOf(groupA.get(0))));
            ordClassB.add(classIngredientSelected.get(comGroupA.indexOf(groupA.get(0))));
        }
    }

    private void divGroups() {
        groupA = new ArrayList <>();
        groupB = new ArrayList <>();
        comGroupA = new ArrayList <>();
        comGroupB = new ArrayList <>();
        ingredientGrpA = new ArrayList <>();
        ingredientGrpB = new ArrayList <>();

        groupB = new ArrayList <>();
        ingredientGrpB = new ArrayList <>();

        //ingredientSelected.add(
        //classIngredientSelected
        //crudeProteinNutrient.ad
        //energyNutrient.add(getF
        //calciumNutrient.add(get
        //phosphorusNutrient.add(
        for (int i=0; i<crudeProteinNutrient.size(); i++){

        if(crudeProteinNutrient.get(i) >= Crude_proteintarget){

            groupA.add(crudeProteinNutrient.get(i));
            comGroupA.add(crudeProteinNutrient.get(i));

            ingredientGrpA.add(ingredientSelected.get(i));
            }else{
            groupB.add(crudeProteinNutrient.get(i));
            comGroupB.add(crudeProteinNutrient.get(i));
            ingredientGrpB.add(ingredientSelected.get(i));
        }

        }



    }

    public void getNumberofFormulation(){

        //get the number of formulation made so far from the database
        Cursor numOfFormu = dbHelper.getFormuRecord();

        while(numOfFormu.moveToNext()) {

            noOfFomulation = numOfFormu.getInt(1);
        }
        //increment by 1
        noOfFomulation++;

        //store back

        if(dbHelper.updateFomulatedRecord(noOfFomulation)){
//            Toast.makeText(this, "Form No: " + noOfFomulation, Toast.LENGTH_SHORT).show();
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
//                double Crude_proteintar = 8.0, Calcium =0.8 ,Phosphorus = 0.4;
                Crude_proteintarget = 8.0;
                Calciumtarget = 0.8;
                Phosphorustarget = 0.4;

                // Check if the nutrient meet the requirement
                if((Collections.max(crudeProteinNutrient) >= Crude_proteintarget) && (Collections.min(crudeProteinNutrient) < Crude_proteintarget) ){

                    formulate = true;

                    if(formulate){
                        formulateProtein(Crude_proteintarget, birdSelected);
                    }


                }else{

                    formulate = false;
                   // Toast.makeText(CreateDietActivity.this, "Formulation cannot be made", Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
                    alertBuilder.setCancelable(true);
                    AlertDialog alertDialog = alertBuilder.create();
                    alertDialog.setTitle("FORMULATION CANNOT BE MADE!!");
                    alertDialog.setMessage("Please select ingredients where, one is greater than 8.0 and the other is lesser than 8.0");
                    alertDialog.show();

                }

            }else{

                Crude_proteintarget = 8.5;
                Calciumtarget = 3.4;
                Phosphorustarget = 0.32;

//                double Crude_protein = 8.5, Calcium = 3.4, Phosphorus = 0.32;
                // Check if the nutrient meet the requirement
                if((Collections.max(crudeProteinNutrient) >= Crude_proteintarget) && (Collections.min(crudeProteinNutrient) < Crude_proteintarget) ){

                    formulate = true;
                    if(formulate) {
                        formulateProtein(Crude_proteintarget, birdSelected);
                    }

                }else{
                    formulate = false;
                    //Toast.makeText(CreateDietActivity.this, "Formulation cannot be made", Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
                    alertBuilder.setCancelable(true);
                    AlertDialog alertDialog = alertBuilder.create();
                    alertDialog.setTitle("FORMULATION CANNOT BE MADE!!");
                    alertDialog.setMessage("Please select ingredients where, one is greater than the 8.5 and the other is lesser than 8.5");
                    alertDialog.show();


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
        forCalcium = new ArrayList <>();
        forPhosphorus = new ArrayList <>();
        forCrudeProtein = new ArrayList <>();
        forClass = new ArrayList <>();
        forEnergy = new ArrayList <>();
        calCalcium = new ArrayList <>();
        calCrudeProtein = new ArrayList <>();
        calPhosphorus = new ArrayList <>();
        calEnergy = new ArrayList <>();



        // get the number of formulation made
        getNumberofFormulation();

        // add formulated ingredient into database
        addFormulatedIngredientToDb(birdCat);

        forCalcium.add(calciumNutrient.get(crudeProteinNutrient.indexOf(Collections.max(crudeProteinNutrient))));
        forCalcium.add(calciumNutrient.get(crudeProteinNutrient.indexOf(Collections.min(crudeProteinNutrient))));

        forPhosphorus.add(phosphorusNutrient.get(crudeProteinNutrient.indexOf(Collections.max(crudeProteinNutrient))));
        forPhosphorus.add(phosphorusNutrient.get(crudeProteinNutrient.indexOf(Collections.min(crudeProteinNutrient))));

        forCrudeProtein.add(crudeProteinNutrient.get(crudeProteinNutrient.indexOf(Collections.max(crudeProteinNutrient))));
        forCrudeProtein.add(crudeProteinNutrient.get(crudeProteinNutrient.indexOf(Collections.min(crudeProteinNutrient))));

        forClass.add(classIngredientSelected.get(crudeProteinNutrient.indexOf(Collections.max(crudeProteinNutrient))));
        forClass.add(classIngredientSelected.get(crudeProteinNutrient.indexOf(Collections.min(crudeProteinNutrient))));



        //get formulated nutrients
        Double maxCal = calciumNutrient.get(crudeProteinNutrient.indexOf(Collections.max(crudeProteinNutrient)));
        Double minCal = calciumNutrient.get(crudeProteinNutrient.indexOf(Collections.min(crudeProteinNutrient)));
        //forCalcium.add(calciumNutrient.get(crudeProteinNutrient.indexOf(Collections.min(crudeProteinNutrient))));

        Double maxPhos = phosphorusNutrient.get(crudeProteinNutrient.indexOf(Collections.max(crudeProteinNutrient)));
        Double minPhos = phosphorusNutrient.get(crudeProteinNutrient.indexOf(Collections.min(crudeProteinNutrient)));
        //forPhosphorus.add(phosphorusNutrient.get(crudeProteinNutrient.indexOf(Collections.min(crudeProteinNutrient))));

        Double maxCrude = crudeProteinNutrient.get(crudeProteinNutrient.indexOf(Collections.max(crudeProteinNutrient)));
        Double minCrude = crudeProteinNutrient.get(crudeProteinNutrient.indexOf(Collections.min(crudeProteinNutrient)));
        //forCrudeProtein.add(crudeProteinNutrient.get(crudeProteinNutrient.indexOf(Collections.min(crudeProteinNutrient))));

        String maxClass = classIngredientSelected.get(crudeProteinNutrient.indexOf(Collections.max(crudeProteinNutrient)));
        String minClass = classIngredientSelected.get(crudeProteinNutrient.indexOf(Collections.min(crudeProteinNutrient)));
        //forClass.add(classIngredientSelected.get(crudeProteinNutrient.indexOf(Collections.min(crudeProteinNutrient))));

        Integer maxEnergy = energyNutrient.get(crudeProteinNutrient.indexOf(Collections.max(crudeProteinNutrient)));
        Integer minEnergy = energyNutrient.get(crudeProteinNutrient.indexOf(Collections.min(crudeProteinNutrient)));
        //forEnergy.add(energyNutrient.get(crudeProteinNutrient.indexOf(Collections.min(crudeProteinNutrient))));


        // The crop with max crude protein value
        String ingredientWithMaxCP = "";
        String ingredientWithMinCP = "";

        Integer QtySelectedForMaxCP = qtyselected.get(crudeProteinNutrient.indexOf(Collections.max(crudeProteinNutrient)));
        Integer QtySelectedForMinCP = qtyselected.get(crudeProteinNutrient.indexOf(Collections.min(crudeProteinNutrient)));

        qtySpecified.add(QtySelectedForMaxCP);
        qtySpecified.add(QtySelectedForMinCP);


        Double ingredientWithMaxCPValue = 0.0;
        Double ingredientWithMinCPValue = 0.0;

        ingredientWithMaxCP = ingredientSelected.get(crudeProteinNutrient.indexOf(Collections.max(crudeProteinNutrient)));
        ingredientWithMinCP = ingredientSelected.get(crudeProteinNutrient.indexOf(Collections.min(crudeProteinNutrient)));

        // store the ingredient selected
        newIngredient.add(ingredientWithMaxCP);
        newIngredient.add(ingredientWithMinCP);


        //   Toast.makeText(CreateDietActivity.this, "crop" + ingredientWithMaxCP, Toast.LENGTH_SHORT).show();
        ingredientWithMaxCPValue = Collections.max(crudeProteinNutrient);
        ingredientWithMinCPValue = Collections.min(crudeProteinNutrient);




        // Step 1 of the calculation
        Double newIngredientWithMaxCPValue = Math.abs(targetProtein - ingredientWithMinCPValue);
        Double newIngredientWithMinCPValue = Math.abs(targetProtein - ingredientWithMaxCPValue);

        Double TotalValue = newIngredientWithMaxCPValue + newIngredientWithMinCPValue;

        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        // Step 2 of the calculation
        Double getPercentForIngreWithMaxValue = (newIngredientWithMaxCPValue/TotalValue) * 100;
        Double getPercentForIngreWithMinValue = (newIngredientWithMinCPValue/TotalValue) * 100;

        // store proportion percent
        newProportion.add(getPercentForIngreWithMaxValue);
        newProportion.add(getPercentForIngreWithMinValue);

        // Step 3 of the calculation
        Double NewCPForIngreWithMinValue = getPercentForIngreWithMinValue * (ingredientWithMinCPValue/100);
        Double NewCPForIngreWithMaxValue = getPercentForIngreWithMaxValue * (ingredientWithMaxCPValue/100);

        //get the quantity to mix
        if(!recalculate){
            qtyToMix = 0.00;
            qtyToMix = Double.parseDouble(getQtyToMix.getText().toString());
            recalculate = false;
        }

        // Step 4 of of the calculation
        Double qtyOfMixMinValueCP = (qtyToMix/100) * getPercentForIngreWithMinValue;
        Double qtyOfMixMaxValueCP = (qtyToMix/100) * getPercentForIngreWithMaxValue;

        getNewProportionUnit.add(qtyOfMixMaxValueCP);
        getNewProportionUnit.add(qtyOfMixMinValueCP);

        //calculating calcium leveo
        Double newCalciumLevel = ((getPercentForIngreWithMaxValue * maxCal) + (getPercentForIngreWithMinValue * minCal)) * 0.01;
        Double newPhosphprusLevel = ((getPercentForIngreWithMaxValue * maxPhos) + (getPercentForIngreWithMinValue* minPhos)) * 0.01;
        Double newEnergyLevel = ((getPercentForIngreWithMaxValue * maxEnergy) + (getPercentForIngreWithMinValue* minEnergy)) * 0.01;
        Double newCrudeProteinLevel = NewCPForIngreWithMinValue + NewCPForIngreWithMaxValue;


//         qtyToMix = Double.parseDouble(getQtyToMix.getText().toString());

        //Check the quantity available is enough to mix feed
        if((qtyOfMixMaxValueCP > QtySelectedForMaxCP) && !recalculate){
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
//            alertBuilder.setMessage("OBSERVATION!!!");

            alertBuilder.setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Double newQty = 0.00;
                            newQty = (QtySelectedForMaxCP * qtyToMix)/qtyOfMixMaxValueCP;
                            qtyToMix = newQty;
                            recalculate = true;
                            maxConditionCheck = false;
//                            mix();1
                            formulateForTwoFeed();
                        }

                    });


            alertBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    recalculate = false;
                    maxConditionCheck = true;
//                    clickNo = true;
                    dialog.cancel();

                }
            });

            AlertDialog alertDialog = alertBuilder.create();
            alertDialog.setTitle("OBSERVATION!!");
            alertDialog.setMessage(String.format("The target quantity can not be reached. You will need the following quantity to achieve it: \n" +
                    "Get " +  decimalFormat.format(qtyOfMixMaxValueCP - QtySelectedForMaxCP) + quantityTypeSpinner.getSelectedItem().toString().toLowerCase() + " more of %s. \n" +
                    " Do you want to scale down to the next mixable quantity?", ingredientWithMaxCP));
            alertDialog.show();
        }else{
            recalculate = recalculate || false;
            maxConditionCheck = true;
        }

        //Check the quantity available is enough to mix feed
        if((qtyOfMixMinValueCP > QtySelectedForMinCP) && !recalculate){
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
            alertBuilder.setMessage("OBSERVATION!!!");

            alertBuilder.setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Double newQty = 0.00;
                            newQty = (QtySelectedForMinCP * qtyToMix)/qtyOfMixMinValueCP;
                            qtyToMix = newQty;
                            recalculate = true;
                            minConditionCheck = false;
                            formulateForTwoFeed();

                        }
                    });

            alertBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    recalculate = false;
                    minConditionCheck = true;
//                    clickNo = true;
                    dialog.cancel();

                }
            });
            AlertDialog alertDialog = alertBuilder.create();
            alertDialog.setTitle("OBSERVATION!!!");
            alertDialog.setMessage(String.format("The target quantity can not be reached. You will need the following quantity to achieve it: \n" +
                    "Get " +  decimalFormat.format(qtyOfMixMinValueCP - QtySelectedForMinCP) + quantityTypeSpinner.getSelectedItem().toString().toLowerCase() + " more of %s. \n" +
                    " Do you want to scale down to the next mixable quantity?", ingredientWithMinCP));
            alertDialog.show();
        }else{
            recalculate = recalculate || false;
            minConditionCheck = true;
        }

//        while(! (minConditionCheck || maxConditionCheck));

        //add quantity to mix into the database
        dbHelper.addQuantityToMix(noOfFomulation, qtyToMix);


        if(qtyOfMixMaxValueCP <= QtySelectedForMaxCP){
            commentArr.add("Appropriate");
        }else{
            commentArr.add("Get " +  decimalFormat.format(qtyOfMixMaxValueCP - QtySelectedForMaxCP) + quantityTypeSpinner.getSelectedItem().toString().toLowerCase() + " more");
        }

        if(qtyOfMixMinValueCP <= QtySelectedForMinCP){
            commentArr.add("Appropriate");
        }else{
            commentArr.add("Get " + decimalFormat.format(qtyOfMixMinValueCP - QtySelectedForMinCP) + quantityTypeSpinner.getSelectedItem().toString().toLowerCase() +" more");
        }

        addResultToDb();
        storecalculatedAnalysis(newCrudeProteinLevel,noOfFomulation,newCalciumLevel,newPhosphprusLevel,newEnergyLevel);


    }



    public void storecalculatedAnalysis(Double crude_protein, Integer form_no, Double calcium, Double phos, Double energy){

        dbHelper.addCalculatedAnalysis(crude_protein,form_no,calcium,phos,energy);

    }


    public void addResultToDb() {
        for(int i=0; i<numOfSelectedFeed; i++) {
            dbHelper.addFormulationResult(newIngredient.get(i),noOfFomulation,newProportion.get(i),
                    getNewProportionUnit.get(i), qtySpecified.get(i),commentArr.get(i),
                    forCrudeProtein.get(i), forCalcium.get(i),forPhosphorus.get(i), forClass.get(i)
            );
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


        return numOfSelectedFeed;
    }


    public void getFeedNut(String selectedFeed){

//       insert id, feed_ingredient,class,crude_protein,energy,calcium,phosphorus
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
        String[] quantityType = new String[]{"KG", "G"};
        ArrayAdapter qtyArrayAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item, quantityType);

        qtyArrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        quantityTypeSpinner.setAdapter(qtyArrayAdapter);
    }

    //   get all ingredient from the database and populate them into a textview
    public void populateTextView(){

        Cursor getAllFeed = dbHelper.getTable();
        String[] Ingredient = new String[getAllFeed.getCount()];


        //  Populate AutocompleteTextView with All crop
        int i=0;
        while(getAllFeed.moveToNext()){
            Ingredient[i] = getAllFeed.getString(0);
            i++;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter <>(this, simple_dropdown_item_1line, Ingredient);
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
