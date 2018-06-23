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
import android.widget.TextView;
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
    TextView layersText;
    AutoCompleteTextView chooseFirstIngrid, chooseSecondIngrid, chooseThirdIngrid, chooseFourIngrid;
    EditText edit_1, secondqty_edit_text, thirdqty_edit_text, fourqty_edit_text;
    ArrayList<String> ingredientSelected, classIngredientSelected, newIngredient, commentArr, forClass;
    ArrayList<Double> calciumNutrient, crudeProteinNutrient, phosphorusNutrient, newProportion, forCalcium, forCrudeProtein, forPhosphorus;
    ArrayList<Double> getNewProportionUnit, calCrudeProtein, calCalcium, calPhosphorus, calEnergy;
    ArrayList<Double> groupA, groupB, ordGroupA, ordGroupB, comGroupA, comGroupB, forCalA, forPhosB, forPhosA, forCalB ;
    ArrayList<Integer> energyNutrient, qtyselected, qtySpecified, forEnergy, ordqtyAvailableA, ordqtyAvailableB, forenergyA, forenergyB, ExtraMin, ExtraMax;
    Spinner quantityTypeSpinner;
    int numOfSelectedFeed=0, noOfFomulation=0, assProp3first = 50, assProp3secon = 50,  assProp2first = 50, assProp2secon = 50, countProp = 0, iter1=0, iter2 =0;
    ArrayList<String>  ingredientGrpA, ingredientGrpB, ordIngredientGrpA, ordIngredientGrpB;
    ArrayList<String>  ordClassA, ordClassB;
    Button createmixBtn;
    double assignPropfirst=0, assignPropsecond=0, assignPropthird=0;
    double assPropSeconddouble =0.0, assPropFirstdouble =0.0;
    RadioGroup radioGroup;
    String birdSelected;
    boolean scaledownToForm = false, finishformu = false, breakoff = false, alreadyDisplay = false;
    boolean iterProtein= false, iterRest = false, finishProteinformu = false;
    String ingredientA = "", ingredientB="";
    EditText getQtyToMix;
    boolean formulate = false, recalculate = false, clickYes = false, clickNo = false, conditionCheck = false;
    boolean minConditionCheck = false, maxConditionCheck = false, reform = false, saveAlready = false;
    boolean scalegroup = false, addcomprop = false, nextpropTwoFeed = false;
    double qtyToMix, Crude_protein, Calcium, Phosphorus, Total = 0.00, Total2A = 0.0, Total2B = 0.0;
    double Total3A = 0.0, Total3B = 0.0;
    double Crude_proteintarget = 0.0, Calciumtarget = 0.00 ,Phosphorustarget = 0.0, qtyTMix = 0.00;
    double scaleprop_groupBFeed = 0.0, scaleprop_first_groupAFeed = 0.0, scaleprop_second_groupAFeed = 0.0;
    double scaleprop_third_groupAFeed = 0.0, qtyToAddGrpA=0.0, qtyToAddGrpB=0.0, qtyAvailableGrpA=0.0, qtyAvailableGrpB=0.0;
    double scaledownA = 0.0, scaledownB = 0.0;
    DecimalFormat decimalFormat;
    Integer[] formulationiter1 = {50,60,70,80,90,10,20,30,40};
    Integer[] formulationiter2 = {50,40,30,20,10,90,80,70,60};
    Integer[] formulationiter3feeds1 ={59};
    Integer[] formulationiter3feeds2 ={41};
    Integer[] formulationiter3a = {40,40,50,50,60,70,80};
    Integer[] formulationiter3b = {30,40,40,30,30,20,10};
    Integer[] formulationiter3c = {30,20,10,20,10,10,10};
    boolean nextprop = true, answerDialog = false, appr = false, twoFeedApp = false;
    double newCal=0.0, newPhos=0.0, newCrude = 0.0, newEnergy=0.0;
    double scale_prop_four_first_grpA =0.0, scale_prop_four_second_grpA =0.0, scale_prop_four_first_grpB =0.0, scale_prop_four_second_grpB =0.0;


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



        // finding edit text
        edit_1 = findViewById(R.id.quantity1_editText);
        secondqty_edit_text = findViewById(R.id.quantity2_editText);
        thirdqty_edit_text = findViewById(R.id.quantity3_editText);
        fourqty_edit_text = findViewById(R.id.quantity4_editText);

        // Find the quantity type spinner
        quantityTypeSpinner= findViewById(R.id.quantitySpinner);

       // Find Create mix button
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

            }else if(numOfFeedSelected() == 3){

                formulateForThreeFeeds();


            }else if (numOfFeedSelected() == 4){

                formulateFourFeed();

                if(formulate){
                    Intent intent = new Intent(this, SummaryActivity.class);
                    intent.putExtra("formuNo", noOfFomulation);
                    startActivity(intent);
                }

            }else{

                Toast.makeText(CreateDietActivity.this, "Please Select At least " +
                        "two Ingredient ", Toast.LENGTH_SHORT).show();
            }


        });
    }

    private void formulateFourFeed() {

        boolean checkNoOfFeed = checkIfFourFeedareselected();

        if(checkNoOfFeed) {

            getNumberofFormulation();

            boolean conditionset = setConditions();

            //formulate the feed
            if(conditionset){
                boolean setNewCPLevel = calNewCPLevelFourFeed();
                reOrderGroupFour();
                boolean finish = false;

                while(!finish) {
                    formulateProteinFeedFour(groupA, groupB);
                    finish = checkQtyScaledDownFourFeed();
                }

                if(finish) {
                    storeComment();
                    storeResultToDb();
                    storecalculatedAnalysis(Crude_proteintarget, noOfFomulation, calCalcium.get(0), calPhosphorus.get(0), calEnergy.get(0));

                    // add formulated ingredient into database
                    addFormulatedIngredientToDb(birdSelected);
                }
            }



        }






    }

    private boolean formulateProteinFeedFour(ArrayList<Double> groupDivA, ArrayList<Double> groupDivB) {
        if(((groupDivA.size() == 3) && (groupDivB.size() == 1)) || ((groupDivA.size() == 1) && (groupDivB.size() == 3))) {

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

            double groupDivACP = 0.0, groupDivBCP = 0.0, forTotal=0.0, newCPFirst =0.0;
            double newCPsecond = 0.0, newCPthird = 0.0, prop_100_kg_groupB=0.0;
            double prop_100_kg_groupA_first =0.0, prop_100_kg_groupA_second =0.0;
            double prop_100_kg_groupA_third = 0.0, propfirstgroupB =0.0, propfirstgroupA =0.0, propsecondgroupA =0.0;
            double propthirdgroupA = 0.0;

            if(!reform) {
                qtyTMix = Double.parseDouble(getQtyToMix.getText().toString());
            }


            if(groupDivA.size() == 3) {
                groupDivACP = Math.abs(groupDivB.get(0) - Crude_proteintarget);
                groupDivBCP = Math.abs(Crude_proteintarget - Total3A);
                forTotal = groupDivACP + groupDivBCP;

                if(nextprop){
                    assignPropfirst = formulationiter3a[countProp];
                    assignPropsecond = formulationiter3b[countProp];
                    assignPropthird = formulationiter3c[countProp];
                    nextprop = false;
                }

                newCPFirst =  assignPropfirst * 0.01 * groupDivACP;
                newCPsecond = assignPropsecond * 0.01 * groupDivACP;
                newCPthird = assignPropthird * 0.01 * groupDivACP;

                //Implement step 6
                prop_100_kg_groupB = groupDivBCP * 100 / forTotal;
                prop_100_kg_groupA_first = newCPFirst * 100 / forTotal;
                prop_100_kg_groupA_second = newCPsecond * 100 / forTotal;
                prop_100_kg_groupA_third = newCPthird * 100 / forTotal;


                // Implement step 7
                propfirstgroupB = 0.01 * prop_100_kg_groupB * groupB.get(0);
                propfirstgroupA = 0.01 * prop_100_kg_groupA_first * groupA.get(0);
                propsecondgroupA = 0.01 * prop_100_kg_groupA_second * groupA.get(1);
                propthirdgroupA = 0.01 * prop_100_kg_groupA_third * groupA.get(2);

                //store class
                forClass.add(ordClassB.get(0));
                forClass.add(ordClassA.get(0));
                forClass.add(ordClassA.get(1));
                forClass.add(ordClassA.get(2));

                //store energy
                forEnergy.add(forenergyB.get(0));
                forEnergy.add(forenergyA.get(0));
                forEnergy.add(forenergyA.get(1));
                forEnergy.add(forenergyA.get(2));

                //store phosphorus
                forPhosphorus.add(forPhosB.get(0));
                forPhosphorus.add(forPhosA.get(0));
                forPhosphorus.add(forPhosA.get(1));
                forPhosphorus.add(forPhosA.get(2));

                // store calcium
                forCalcium.add(forCalB.get(0));
                forCalcium.add(forCalA.get(0));
                forCalcium.add(forCalA.get(1));
                forCalcium.add(forCalA.get(2));

                //store crude_protein
                forCrudeProtein.add(groupB.get(0));
                forCrudeProtein.add(groupA.get(0));
                forCrudeProtein.add(groupA.get(1));
                forCrudeProtein.add(groupA.get(2));

                //store proportion percentage
                newProportion.add(prop_100_kg_groupB);
                newProportion.add(prop_100_kg_groupA_first);
                newProportion.add(prop_100_kg_groupA_second);
                newProportion.add(prop_100_kg_groupA_third);

                //store ingredient
                newIngredient.add(ordIngredientGrpB.get(0));
                newIngredient.add(ordIngredientGrpA.get(0));
                newIngredient.add(ordIngredientGrpA.get(1));
                newIngredient.add(ordIngredientGrpA.get(2));

                //store qty specified
                qtySpecified.add(ordqtyAvailableB.get(0));
                qtySpecified.add(ordqtyAvailableA.get(0));
                qtySpecified.add(ordqtyAvailableA.get(1));
                qtySpecified.add(ordqtyAvailableA.get(2));



            }else{
                groupDivACP = Math.abs(Total3B  - Crude_proteintarget);
                groupDivBCP = Math.abs(Crude_proteintarget - groupDivA.get(0));
                forTotal = groupDivACP + groupDivBCP;


                if(nextprop){
                    assignPropfirst = formulationiter3a[countProp];
                    assignPropsecond = formulationiter3b[countProp];
                    assignPropthird = formulationiter3c[countProp];
                    nextprop = false;
                }

                newCPFirst = assignPropfirst * 0.01 * groupDivBCP;
                newCPsecond = assignPropsecond * 0.01 * groupDivBCP;
                newCPthird = assignPropthird * 0.01 * groupDivBCP;

                //Implement step 6
                prop_100_kg_groupB = groupDivACP * 100 / forTotal;
                prop_100_kg_groupA_first = newCPFirst * 100 / forTotal;
                prop_100_kg_groupA_second = newCPsecond * 100 / forTotal;
                prop_100_kg_groupA_third = newCPthird * 100 / forTotal;


                // Implement step 7
                propfirstgroupB = 0.01 * prop_100_kg_groupB * groupA.get(0);
                propfirstgroupA = 0.01 * prop_100_kg_groupA_first * groupB.get(0);
                propsecondgroupA = 0.01 * prop_100_kg_groupA_second * groupB.get(1);
                propthirdgroupA = 0.01 * prop_100_kg_groupA_third * groupB.get(2);

                //store class
                forClass.add(ordClassA.get(0));
                forClass.add(ordClassB.get(0));
                forClass.add(ordClassB.get(1));
                forClass.add(ordClassB.get(2));

                //store energy
                forEnergy.add(forenergyA.get(0));
                forEnergy.add(forenergyB.get(0));
                forEnergy.add(forenergyB.get(1));
                forEnergy.add(forenergyB.get(2));

                //store phosphorus
                forPhosphorus.add(forPhosA.get(0));
                forPhosphorus.add(forPhosB.get(0));
                forPhosphorus.add(forPhosB.get(1));
                forPhosphorus.add(forPhosB.get(2));

                // store calcium
                forCalcium.add(forCalA.get(0));
                forCalcium.add(forCalB.get(0));
                forCalcium.add(forCalB.get(1));
                forCalcium.add(forCalB.get(2));

                //store crude_protein
                forCrudeProtein.add(groupA.get(0));
                forCrudeProtein.add(groupB.get(0));
                forCrudeProtein.add(groupB.get(1));
                forCrudeProtein.add(groupB.get(2));

                //store proportion percentage
                newProportion.add(prop_100_kg_groupB);
                newProportion.add(prop_100_kg_groupA_first);
                newProportion.add(prop_100_kg_groupA_second);
                newProportion.add(prop_100_kg_groupA_third);

                //store ingredient
                newIngredient.add(ordIngredientGrpA.get(0));
                newIngredient.add(ordIngredientGrpB.get(0));
                newIngredient.add(ordIngredientGrpB.get(1));
                newIngredient.add(ordIngredientGrpB.get(2));

                //store qty specified
                qtySpecified.add(ordqtyAvailableA.get(0));
                qtySpecified.add(ordqtyAvailableB.get(0));
                qtySpecified.add(ordqtyAvailableB.get(1));
                qtySpecified.add(ordqtyAvailableB.get(2));
            }


            //scale down proportion to mix
            scaleprop_groupBFeed = prop_100_kg_groupB * qtyTMix * 0.01;
            scaleprop_first_groupAFeed = prop_100_kg_groupA_first * qtyTMix * 0.01;
            scaleprop_second_groupAFeed = prop_100_kg_groupA_second * qtyTMix * 0.01;
            scaleprop_third_groupAFeed = prop_100_kg_groupA_third * qtyTMix * 0.01;


            //calculate analysis for calcium
            if(groupDivA.size() == 3) {
                double anacalcium = ((prop_100_kg_groupB * forCalB.get(0)) + (prop_100_kg_groupA_first * forCalA.get(0)) + (prop_100_kg_groupA_second * forCalA.get(1)) + (prop_100_kg_groupA_third * forCalA.get(2))) * 0.01;

                double anaphos = ((prop_100_kg_groupB * forPhosB.get(0)) + (prop_100_kg_groupA_first * forPhosA.get(0)) + (prop_100_kg_groupA_second * forPhosA.get(1)) + (prop_100_kg_groupA_third * forPhosA.get(2))) * 0.01;

                double anaenergy = ((prop_100_kg_groupB * forenergyB.get(0)) + (prop_100_kg_groupA_first * forenergyA.get(0)) + (prop_100_kg_groupA_second * forenergyA.get(1)) + (prop_100_kg_groupA_third * forenergyA.get(2))) * 0.01;

                calCalcium.add(anacalcium);
                calPhosphorus.add(anaphos);
                calEnergy.add(anaenergy);
                return true;
            }else{

                double anacalcium = ((prop_100_kg_groupB * forCalA.get(0)) + (prop_100_kg_groupA_first * forCalB.get(0)) + (prop_100_kg_groupA_second * forCalB.get(1)) + (prop_100_kg_groupA_third * forCalB.get(2))) * 0.01;

                double anaphos = ((prop_100_kg_groupB * forPhosA.get(0)) + (prop_100_kg_groupA_first * forPhosB.get(0)) + (prop_100_kg_groupA_second * forPhosB.get(1)) + (prop_100_kg_groupA_third * forPhosB.get(2))) * 0.01;

                double anaenergy = ((prop_100_kg_groupB * forenergyA.get(0)) + (prop_100_kg_groupA_first * forenergyB.get(0)) + (prop_100_kg_groupA_second * forenergyB.get(1)) + (prop_100_kg_groupA_third * forenergyB.get(2))) * 0.01;

                calCalcium.add(anacalcium);
                calPhosphorus.add(anaphos);
                calEnergy.add(anaenergy);
                return true;

            }


        }else if((groupDivA.size() == 2) && (groupDivB.size() == 2)) {

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


            double groupDivACP = 0.0, groupDivBCP = 0.0, forTotal=0.0, newCPFirstAC =0.0;
            double newCPsecond = 0.0, newCPthird = 0.0, prop_100_kg_groupB=0.0;
            double prop_100_kg_groupA_first =0.0, prop_100_kg_groupA_second =0.0;
            double prop_100_kg_groupA_third = 0.0, propfirstgroupB =0.0, propfirstgroupA =0.0, propsecondgroupA =0.0;
            double propthirdgroupA = 0.0, prop_100_kg_groupB_first =0.0, prop_100_kg_groupB_second =0.0;
            double newCPFirstA =0.0, newCPsecondA = 0.0, newCPFirstB =0.0, newCPsecondB = 0.0;
            double propsecondgroupB=0.0;


            if(!reform) {
                qtyTMix = Double.parseDouble(getQtyToMix.getText().toString());
            }

            groupDivACP = Math.abs(Total2B - Crude_proteintarget);
            groupDivBCP = Math.abs(Crude_proteintarget - Total2A);
            forTotal = groupDivACP + groupDivBCP;


            if(nextprop){
                assignPropfirst = formulationiter3feeds1[countProp];
                assignPropsecond = formulationiter3feeds2[countProp];
                nextprop = false;
            }


            newCPFirstA = assignPropfirst * 0.01 * groupDivBCP;
            newCPsecondA = assignPropsecond * 0.01 * groupDivBCP;


            newCPFirstB = assignPropfirst * 0.01 * groupDivACP;
            newCPsecondB = assignPropsecond * 0.01 * groupDivACP;

            //Implement step 6
            prop_100_kg_groupB_first = newCPFirstB * 100 / forTotal;
            prop_100_kg_groupB_second = newCPsecondB * 100 / forTotal;
            prop_100_kg_groupA_first = newCPFirstA * 100 / forTotal;
            prop_100_kg_groupA_second = newCPsecondA * 100 / forTotal;

            // Implement step 7
            propfirstgroupB = 0.01 * prop_100_kg_groupB_first * groupB.get(0);
            propsecondgroupB = 0.01 * prop_100_kg_groupB_second * groupB.get(1);
            propfirstgroupA = 0.01 * prop_100_kg_groupA_first * groupA.get(0);
            propsecondgroupA = 0.01 * prop_100_kg_groupA_second * groupA.get(1);

            //store class
            forClass.add(ordClassA.get(0));
            forClass.add(ordClassA.get(1));
            forClass.add(ordClassB.get(0));
            forClass.add(ordClassB.get(1));

            //store energy
            forEnergy.add(forenergyA.get(0));
            forEnergy.add(forenergyA.get(1));
            forEnergy.add(forenergyB.get(0));
            forEnergy.add(forenergyB.get(1));

            //store phosphorus
            forPhosphorus.add(forPhosA.get(0));
            forPhosphorus.add(forPhosA.get(1));
            forPhosphorus.add(forPhosB.get(0));
            forPhosphorus.add(forPhosB.get(1));

            // store calcium
            forCalcium.add(forCalA.get(0));
            forCalcium.add(forCalA.get(1));
            forCalcium.add(forCalB.get(0));
            forCalcium.add(forCalB.get(1));

            //store crude_protein
            forCrudeProtein.add(groupA.get(0));
            forCrudeProtein.add(groupA.get(1));
            forCrudeProtein.add(groupB.get(0));
            forCrudeProtein.add(groupB.get(1));

            //store proportion percentage
            newProportion.add(prop_100_kg_groupB_first);
            newProportion.add(prop_100_kg_groupB_second);
            newProportion.add(prop_100_kg_groupA_first);
            newProportion.add(prop_100_kg_groupA_second);

            //store ingredient
            newIngredient.add(ordIngredientGrpA.get(0));
            newIngredient.add(ordIngredientGrpA.get(1));
            newIngredient.add(ordIngredientGrpB.get(0));
            newIngredient.add(ordIngredientGrpB.get(1));

            //store qty specified
            qtySpecified.add(ordqtyAvailableA.get(0));
            qtySpecified.add(ordqtyAvailableA.get(1));
            qtySpecified.add(ordqtyAvailableB.get(0));
            qtySpecified.add(ordqtyAvailableB.get(1));


            //scale down proportion to mix
            scale_prop_four_first_grpB = prop_100_kg_groupB_first * qtyTMix * 0.01;
            scale_prop_four_second_grpB = prop_100_kg_groupB_second * qtyTMix * 0.01;
            scale_prop_four_first_grpA = prop_100_kg_groupA_first * qtyTMix * 0.01;
            scale_prop_four_second_grpA = prop_100_kg_groupA_second * qtyTMix * 0.01;

            //calculate analysis for calcium
            double anacalcium = ((prop_100_kg_groupB_first * forCalB.get(0)) + (prop_100_kg_groupB_second * forCalB.get(1)) + (prop_100_kg_groupA_first * forCalA.get(0)) + (prop_100_kg_groupA_second * forCalA.get(1))) * 0.01;

            double anaphos = ((prop_100_kg_groupB_first * forPhosB.get(0)) + (prop_100_kg_groupB_second * forPhosB.get(1)) + (prop_100_kg_groupA_first * forPhosA.get(0)) + (prop_100_kg_groupA_second * forPhosA.get(1))) * 0.01;

            double anaenergy = ((prop_100_kg_groupB_first * forenergyB.get(0)) + (prop_100_kg_groupB_second * forenergyB.get(1)) + (prop_100_kg_groupA_first * forenergyA.get(0)) + (prop_100_kg_groupA_second * forenergyA.get(1))) * 0.01;

            calCalcium.add(anacalcium);
            calPhosphorus.add(anaphos);
            calEnergy.add(anaenergy);

        }

        return false;
    }

    private boolean checkQtyScaledDownFourFeed() {


        if(ordqtyAvailableA.size() == 3) {

            // check the difference to scale down
            double firstScaleDown = scaleprop_first_groupAFeed - ordqtyAvailableA.get(0);
            double secondScaleDown = scaleprop_second_groupAFeed - ordqtyAvailableA.get(1);
            double thirdScaleDown = scaleprop_third_groupAFeed - ordqtyAvailableA.get(2);

            if ((firstScaleDown <= 0) && (secondScaleDown > 0) && (thirdScaleDown > 0)) {

                assignPropfirst+=2;
                assignPropsecond--;
                assignPropthird--;

                if (countProp == (formulationiter1.length - 1)) {
                    double qtyscaled = 0.0;
                    double qtyavailableGrp = 0.0;

                    if(secondScaleDown >= thirdScaleDown){
                        qtyavailableGrp = ordqtyAvailableA.get(1);
                        qtyscaled = scaleprop_second_groupAFeed;
                    }else {
                        qtyavailableGrp = ordqtyAvailableA.get(2);
                        qtyscaled = scaleprop_third_groupAFeed;
                    }

                    qtyTMix = Math.floor(qtyTMix * qtyavailableGrp / qtyscaled);
                    countProp = 0;
                    nextprop = true;
                    reform = true;
                    return false;
                }


                if (assignPropfirst == 98) {
                    countProp++;
                    nextprop = true;
                    return false;
                }

                return false;

            }else if((firstScaleDown > 0) && (secondScaleDown <= 0) && (thirdScaleDown > 0)){
                assignPropsecond += 2;
                assignPropfirst--;
                assignPropthird--;

                if (countProp == (formulationiter1.length - 1)) {
                    double qtyscaled = 0.0;
                    double qtyavailableGrp = 0.0;

                    if(firstScaleDown < thirdScaleDown){
                        qtyavailableGrp = ordqtyAvailableA.get(2);
                        qtyscaled = scaleprop_third_groupAFeed;
                    }else {
                        qtyavailableGrp = ordqtyAvailableA.get(0);
                        qtyscaled = scaleprop_first_groupAFeed;
                    }

                    qtyTMix = Math.floor(qtyTMix * qtyavailableGrp / qtyscaled);
                    countProp = 0;
                    reform = true;
                    nextprop = true;
                    return false;
                }


                if (assignPropsecond == 98) {
                    countProp++;
                    nextprop = true;
                    return false;
                }

                return false;

            }else if((firstScaleDown > 0) && (secondScaleDown > 0) && (thirdScaleDown <= 0)){
                assignPropthird+=2;
                assignPropfirst--;
                assignPropsecond--;

                if (countProp == (formulationiter1.length - 1)) {
                    double qtyscaled = 0.0;
                    double qtyavailableGrp = 0.0;

                    if(firstScaleDown < secondScaleDown){
                        qtyavailableGrp = ordqtyAvailableA.get(1);
                        qtyscaled = scaleprop_second_groupAFeed;
                    }else {
                        qtyavailableGrp = ordqtyAvailableA.get(0);
                        qtyscaled = scaleprop_first_groupAFeed;
                    }

                    qtyTMix = Math.floor(qtyTMix * qtyavailableGrp / qtyscaled);
                    countProp = 0;
                    reform = true;
                    nextprop = true;
                    return false;
                }


                if (assignPropthird == 98) {
                    countProp++;
                    nextprop = true;
                    return false;
                }

                return false;

            }else if((firstScaleDown <= 0) && (secondScaleDown <= 0) && (thirdScaleDown > 0)){
                assignPropthird--;
                assignPropfirst += 0.25;
                assignPropsecond += 0.75;

                if (countProp == (formulationiter1.length - 1)) {
                    double qtyscaled = 0.0;
                    double qtyavailableGrp = 0.0;
                    qtyavailableGrp = ordqtyAvailableA.get(2);
                    qtyscaled = scaleprop_third_groupAFeed;

                    qtyTMix = Math.floor(qtyTMix * qtyavailableGrp / qtyscaled);
                    countProp = 0;
                    reform = true;
                    nextprop = true;
                    return false;
                }


                if (assignPropsecond == 99.25) {
                    countProp++;
                    nextprop = true;
                    return false;
                }

                return false;

            }else if((firstScaleDown <= 0) && (secondScaleDown > 0) && (thirdScaleDown <= 0)){

                assignPropthird += 0.75;
                assignPropfirst += 0.25;
                assignPropsecond--;

                if (countProp == (formulationiter1.length - 1)) {
                    double qtyscaled = 0.0;
                    double qtyavailableGrp = 0.0;
                    qtyavailableGrp = ordqtyAvailableA.get(1);
                    qtyscaled = scaleprop_second_groupAFeed;

                    qtyTMix = Math.floor(qtyTMix * qtyavailableGrp / qtyscaled);
                    countProp = 0;
                    reform = true;
                    nextprop = true;
                    return false;
                }


                if (assignPropthird == 99.25) {
                    countProp++;
                    nextprop = true;
                    return false;
                }

                return false;

            }else if((firstScaleDown > 0) && (secondScaleDown <= 0) && (thirdScaleDown <= 0)){
                assignPropthird += 0.75;
                assignPropfirst--;
                assignPropsecond+= 0.25;

                if (countProp == (formulationiter1.length - 1)) {
                    double qtyscaled = 0.0;
                    double qtyavailableGrp = 0.0;
                    qtyavailableGrp = ordqtyAvailableA.get(0);
                    qtyscaled = scaleprop_first_groupAFeed;

                    qtyTMix = Math.floor(qtyTMix * qtyavailableGrp / qtyscaled);
                    countProp = 0;
                    reform = true;
                    nextprop = true;
                    return false;
                }


                if (assignPropthird == 99.25) {
                    countProp++;
                    nextprop = true;
                    return false;
                }

                return false;
            }
//            else if((firstScaleDown<=0) && (secondScaleDown <=0 ) && (thirdScaleDown <=0)){
//                return true;
//            }

            else if ((firstScaleDown > 0) && (secondScaleDown > 0) && (thirdScaleDown > 0)){
                //none is appropriate
                if((firstScaleDown >= secondScaleDown) && (firstScaleDown >= thirdScaleDown)){

                    qtyTMix = Math.floor(qtyTMix * ordqtyAvailableA.get(0) / scaleprop_first_groupAFeed);
                    countProp = 0;
                    reform = true;
                    nextprop = true;
                    return false;

                }else if((firstScaleDown <= secondScaleDown) && (secondScaleDown >= thirdScaleDown)){

                    qtyTMix = Math.floor(qtyTMix * ordqtyAvailableA.get(1) / scaleprop_second_groupAFeed);
                    countProp = 0;
                    reform = true;
                    nextprop = true;
                    return false;
                }else if((firstScaleDown <= thirdScaleDown) && (secondScaleDown <= thirdScaleDown)){

                    qtyTMix = Math.floor(qtyTMix * ordqtyAvailableA.get(2) / scaleprop_third_groupAFeed);
                    countProp = 0;
                    reform = true;
                    nextprop = true;
                    return false;
                }

                if(scaleprop_groupBFeed > ordqtyAvailableB.get(0)){
                    qtyTMix = Math.floor(qtyTMix * ordqtyAvailableB.get(0) / scaleprop_groupBFeed);
                    countProp = 0;
                    reform = true;
                    nextprop = true;
                    return false;
                }

            }
        }else if(ordqtyAvailableB.size() == 3) {
            // check the difference to scale down
            double firstScaleDown = scaleprop_first_groupAFeed - ordqtyAvailableB.get(0);
            double secondScaleDown = scaleprop_second_groupAFeed - ordqtyAvailableB.get(1);
            double thirdScaleDown = scaleprop_third_groupAFeed - ordqtyAvailableB.get(2);

            if ((firstScaleDown <= 0) && (secondScaleDown > 0) && (thirdScaleDown > 0)) {

                assignPropfirst+=2;
                assignPropsecond--;
                assignPropthird--;

                if (countProp == (formulationiter1.length - 1)) {
                    double qtyscaled = 0.0;
                    double qtyavailableGrp = 0.0;

                    if(secondScaleDown >= thirdScaleDown){
                        qtyavailableGrp = ordqtyAvailableB.get(1);
                        qtyscaled = scaleprop_second_groupAFeed;
                    }else {
                        qtyavailableGrp = ordqtyAvailableB.get(2);
                        qtyscaled = scaleprop_third_groupAFeed;
                    }

                    qtyTMix = Math.floor(qtyTMix * qtyavailableGrp / qtyscaled);
                    countProp = 0;
                    reform = true;
                    nextprop = true;
                    return false;
                }


                if (assignPropfirst == 98) {
                    countProp++;
                    nextprop = true;
                    return false;
                }

                return false;

            }else if((firstScaleDown > 0) && (secondScaleDown <= 0) && (thirdScaleDown > 0)){
                assignPropsecond += 2;
                assignPropfirst--;
                assignPropthird--;

                if (countProp == (formulationiter1.length - 1)) {
                    double qtyscaled = 0.0;
                    double qtyavailableGrp = 0.0;

                    if(firstScaleDown >= thirdScaleDown){
                        qtyavailableGrp = ordqtyAvailableB.get(0);
                        qtyscaled = scaleprop_first_groupAFeed;
                    }else {
                        qtyavailableGrp = ordqtyAvailableB.get(2);
                        qtyscaled = scaleprop_third_groupAFeed;
                    }

                    qtyTMix = Math.floor(qtyTMix * qtyavailableGrp / qtyscaled);
                    countProp = 0;
                    reform = true;
                    nextprop = true;
                    return false;
                }


                if (assignPropsecond == 98) {
                    countProp++;
                    nextprop = true;
                    return false;
                }

                return false;

            }else if((firstScaleDown > 0) && (secondScaleDown > 0) && (thirdScaleDown <= 0)){
                assignPropthird+=2;
                assignPropfirst--;
                assignPropsecond--;

                if (countProp == (formulationiter1.length - 1)) {
                    double qtyscaled = 0.0;
                    double qtyavailableGrp = 0.0;

                    if(firstScaleDown >= secondScaleDown){
                        qtyavailableGrp = ordqtyAvailableB.get(0);
                        qtyscaled = scaleprop_first_groupAFeed;
                    }else {
                        qtyavailableGrp = ordqtyAvailableB.get(1);
                        qtyscaled = scaleprop_second_groupAFeed;
                    }

                    qtyTMix = Math.floor(qtyTMix * qtyavailableGrp / qtyscaled);
                    countProp = 0;
                    reform = true;
                    nextprop = true;
                    return false;
                }


                if (assignPropthird == 98) {
                    countProp++;
                    nextprop = true;
                    return false;
                }

                return false;

            }else if((firstScaleDown <= 0) && (secondScaleDown <= 0) && (thirdScaleDown > 0)){
                assignPropthird--;
                assignPropfirst += 0.25;
                assignPropsecond += 0.75;

                if (countProp == (formulationiter1.length - 1)) {
                    double qtyscaled = 0.0;
                    double qtyavailableGrp = 0.0;
                    qtyavailableGrp = ordqtyAvailableB.get(2);
                    qtyscaled = scaleprop_third_groupAFeed;

                    qtyTMix = Math.floor(qtyTMix * qtyavailableGrp / qtyscaled);
                    countProp = 0;
                    reform = true;
                    nextprop = true;
                    return false;
                }


                if (assignPropsecond == 99.25) {
                    countProp++;
                    nextprop = true;
                    return false;
                }

                return false;

            }else if((firstScaleDown <= 0) && (secondScaleDown > 0) && (thirdScaleDown <= 0)){

                assignPropthird += 0.75;
                assignPropfirst += 0.25;
                assignPropsecond--;

                if (countProp == (formulationiter1.length - 1)) {
                    double qtyscaled = 0.0;
                    double qtyavailableGrp = 0.0;
                    qtyavailableGrp = ordqtyAvailableB.get(1);
                    qtyscaled = scaleprop_second_groupAFeed;

                    qtyTMix = Math.floor(qtyTMix * qtyavailableGrp / qtyscaled);
                    countProp = 0;
                    reform = true;
                    nextprop = true;
                    return false;
                }


                if (assignPropthird == 99.25) {
                    countProp++;
                    nextprop = true;
                    return false;
                }

                return false;

            }else if((firstScaleDown > 0) && (secondScaleDown <= 0) && (thirdScaleDown <= 0)){
                assignPropthird += 0.75;
                assignPropfirst--;
                assignPropsecond+= 0.25;

                if (countProp == (formulationiter1.length - 1)) {
                    double qtyscaled = 0.0;
                    double qtyavailableGrp = 0.0;
                    qtyavailableGrp = ordqtyAvailableB.get(0);
                    qtyscaled = scaleprop_first_groupAFeed;

                    qtyTMix = Math.floor(qtyTMix * qtyavailableGrp / qtyscaled);
                    countProp = 0;
                    reform = true;
                    nextprop = true;
                    return false;
                }


                if (assignPropthird == 99.25) {
                    countProp++;
                    nextprop = true;
                    return false;
                }

                return false;
            }

            else if ((firstScaleDown > 0) && (secondScaleDown > 0) && (thirdScaleDown > 0)){
                //none is appropriate
                if((firstScaleDown >= secondScaleDown) && (firstScaleDown >= thirdScaleDown)){

                    qtyTMix = Math.floor(qtyTMix * ordqtyAvailableB.get(0) / scaleprop_first_groupAFeed);
                    countProp = 0;
                    reform = true;
                    nextprop = true;
                    return false;

                }else if((secondScaleDown >= firstScaleDown) && (secondScaleDown >= thirdScaleDown)){

                    qtyTMix = Math.floor(qtyTMix * ordqtyAvailableB.get(1) / scaleprop_second_groupAFeed);
                    countProp = 0;
                    reform = true;
                    nextprop = true;
                    return false;
                }else if((thirdScaleDown >= firstScaleDown) && (thirdScaleDown >= secondScaleDown)){

                    qtyTMix = Math.floor(qtyTMix * ordqtyAvailableB.get(2) / scaleprop_third_groupAFeed);
                    countProp = 0;
                    reform = true;
                    nextprop = true;
                    return false;
                }

            }
            if(scaleprop_groupBFeed > ordqtyAvailableA.get(0)){
                qtyTMix = Math.floor(qtyTMix * ordqtyAvailableA.get(0) / scaleprop_groupBFeed);
                countProp = 0;
                reform = true;
                nextprop = true;
                return false;
            }
        }

        if(ordqtyAvailableA.size() == 2 && ordqtyAvailableB.size() == 2){

            // check the difference to scale down
            double firstScaleDownB = scale_prop_four_first_grpB - ordqtyAvailableB.get(0);
            double secondScaleDownB = scale_prop_four_second_grpB - ordqtyAvailableB.get(1);
            double firstScaleDownA = scale_prop_four_first_grpA - ordqtyAvailableA.get(0);
            double secondScaleDownA = scale_prop_four_second_grpA - ordqtyAvailableA.get(1);

            if((firstScaleDownB > 0) && (secondScaleDownB <=0)){
                assignPropfirst--;
                assignPropsecond++;

                if (countProp == (formulationiter1.length - 1)) {
                    double qtyscaled = 0.0;
                    double qtyavailableGrp = 0.0;

                    qtyavailableGrp = ordqtyAvailableB.get(0);
                    qtyscaled = scale_prop_four_first_grpB;

                    qtyTMix = Math.floor(qtyTMix * qtyavailableGrp / qtyscaled);
                    countProp = 0;
                    nextprop = true;
                    reform = true;
                    return false;
                }


                if (assignPropsecond == 99) {
                    countProp++;
                    nextprop = true;
                    return false;
                }

                return false;
            }else if((firstScaleDownB <= 0) && (secondScaleDownB > 0)){
                assignPropfirst++;
                assignPropsecond--;

                if (countProp == (formulationiter1.length - 1)) {
                    double qtyscaled = 0.0;
                    double qtyavailableGrp = 0.0;

                    qtyavailableGrp = ordqtyAvailableB.get(1);
                    qtyscaled = scale_prop_four_second_grpB;

                    qtyTMix = Math.floor(qtyTMix * qtyavailableGrp / qtyscaled);
                    countProp = 0;
                    nextprop = true;
                    reform = true;
                    return false;
                }


                if (assignPropfirst == 99) {
                    countProp++;
                    nextprop = true;
                    return false;
                }
                return false;
            }else if((firstScaleDownB > 0) && (secondScaleDownB > 0)){

                if(firstScaleDownB >= secondScaleDownB){

                    double qtyscaled = 0.0;
                    double qtyavailableGrp = 0.0;

                    qtyavailableGrp = ordqtyAvailableB.get(0);
                    qtyscaled = scale_prop_four_first_grpB;

                    qtyTMix = Math.floor(qtyTMix * qtyavailableGrp / qtyscaled);
                    countProp = 0;
                    nextprop = true;
                    reform = true;
                    return false;

                }else{
                    double qtyscaled = 0.0;
                    double qtyavailableGrp = 0.0;

                    qtyavailableGrp = ordqtyAvailableB.get(1);
                    qtyscaled = scale_prop_four_second_grpB;

                    qtyTMix = Math.floor(qtyTMix * qtyavailableGrp / qtyscaled);
                    countProp = 0;
                    nextprop = true;
                    reform = true;
                    return false;
                }



            }else if((firstScaleDownA > 0) && (secondScaleDownA <=0)){
                assignPropfirst--;
                assignPropsecond++;

                if (countProp == (formulationiter1.length - 1)) {
                    double qtyscaled = 0.0;
                    double qtyavailableGrp = 0.0;

                    qtyavailableGrp = ordqtyAvailableA.get(0);
                    qtyscaled = scale_prop_four_first_grpA;

                    qtyTMix = Math.floor(qtyTMix * qtyavailableGrp / qtyscaled);
                    countProp = 0;
                    nextprop = true;
                    reform = true;
                    return false;
                }


                if (assignPropsecond == 99) {
                    countProp++;
                    nextprop = true;
                    return false;
                }

                return false;
            }else if((firstScaleDownA <= 0) && (secondScaleDownA > 0)){
                assignPropfirst++;
                assignPropsecond--;

                if (countProp == (formulationiter1.length - 1)) {
                    double qtyscaled = 0.0;
                    double qtyavailableGrp = 0.0;

                    qtyavailableGrp = ordqtyAvailableA.get(1);
                    qtyscaled = scale_prop_four_second_grpA;

                    qtyTMix = Math.floor(qtyTMix * qtyavailableGrp / qtyscaled);
                    countProp = 0;
                    nextprop = true;
                    reform = true;
                    return false;
                }


                if (assignPropfirst == 99) {
                    countProp++;
                    nextprop = true;
                    return false;
                }

                return false;

            }else if((firstScaleDownA > 0) && (secondScaleDownA > 0)){

                if(firstScaleDownA >= secondScaleDownA){

                    double qtyscaled = 0.0;
                    double qtyavailableGrp = 0.0;

                    qtyavailableGrp = ordqtyAvailableA.get(0);
                    qtyscaled = scale_prop_four_first_grpA;

                    qtyTMix = Math.floor(qtyTMix * qtyavailableGrp / qtyscaled);
                    countProp = 0;
                    nextprop = true;
                    reform = true;
                    return false;

                }else{
                    double qtyscaled = 0.0;
                    double qtyavailableGrp = 0.0;

                    qtyavailableGrp = ordqtyAvailableA.get(1);
                    qtyscaled = scale_prop_four_second_grpA;

                    qtyTMix = Math.floor(qtyTMix * qtyavailableGrp / qtyscaled);
                    countProp = 0;
                    nextprop = true;
                    reform = true;
                    return false;
                }


            }


        }


        return true;


    }

    private boolean storeComment(){
        if((groupA.size() == 3) || (groupB.size() == 3)) {
            getNewProportionUnit.add(scaleprop_groupBFeed);
            getNewProportionUnit.add(scaleprop_first_groupAFeed);
            getNewProportionUnit.add(scaleprop_second_groupAFeed);
            getNewProportionUnit.add(scaleprop_third_groupAFeed);
            addcomprop = true;

            //add quantity to mix into the database
            dbHelper.addQuantityToMix(noOfFomulation, qtyTMix);

            if (groupA.size() == 3) {
                if (scaleprop_groupBFeed <= ordqtyAvailableB.get(0)) {
                    commentArr.add("Appropriate");
                } else {
                    commentArr.add("Get " + decimalFormat.format(scaleprop_groupBFeed - ordqtyAvailableB.get(0)) + quantityTypeSpinner.getSelectedItem().toString().toLowerCase() + " more");
                }

                if (scaleprop_first_groupAFeed <= ordqtyAvailableA.get(0)) {
                    commentArr.add("Appropriate");
                } else {
                    commentArr.add("Get " + decimalFormat.format(scaleprop_first_groupAFeed - ordqtyAvailableA.get(0)) + quantityTypeSpinner.getSelectedItem().toString().toLowerCase() + " more");
                }

                if (scaleprop_second_groupAFeed <= ordqtyAvailableA.get(1)) {
                    commentArr.add("Appropriate");
                } else {
                    commentArr.add("Get " + decimalFormat.format(scaleprop_second_groupAFeed - ordqtyAvailableA.get(1)) + quantityTypeSpinner.getSelectedItem().toString().toLowerCase() + " more");
                }
                if (scaleprop_third_groupAFeed <= ordqtyAvailableA.get(2)) {
                    commentArr.add("Appropriate");
                } else {
                    commentArr.add("Get " + decimalFormat.format(scaleprop_third_groupAFeed - ordqtyAvailableA.get(2)) + quantityTypeSpinner.getSelectedItem().toString().toLowerCase() + " more");
                }
            } else if (groupB.size() == 3) {
                if (scaleprop_groupBFeed <= ordqtyAvailableA.get(0)) {
                    commentArr.add("Appropriate");
                } else {
                    commentArr.add("Get " + decimalFormat.format(scaleprop_groupBFeed - ordqtyAvailableA.get(0)) + quantityTypeSpinner.getSelectedItem().toString().toLowerCase() + " more");
                }

                if (scaleprop_first_groupAFeed <= ordqtyAvailableB.get(0)) {
                    commentArr.add("Appropriate");
                } else {
                    commentArr.add("Get " + decimalFormat.format(scaleprop_first_groupAFeed - ordqtyAvailableB.get(0)) + quantityTypeSpinner.getSelectedItem().toString().toLowerCase() + " more");
                }

                if (scaleprop_second_groupAFeed <= ordqtyAvailableB.get(1)) {
                    commentArr.add("Appropriate");
                } else {
                    commentArr.add("Get " + decimalFormat.format(scaleprop_second_groupAFeed - ordqtyAvailableB.get(1)) + quantityTypeSpinner.getSelectedItem().toString().toLowerCase() + " more");
                }
                if (scaleprop_third_groupAFeed <= ordqtyAvailableB.get(2)) {
                    commentArr.add("Appropriate");
                } else {
                    commentArr.add("Get " + decimalFormat.format(scaleprop_third_groupAFeed - ordqtyAvailableB.get(2)) + quantityTypeSpinner.getSelectedItem().toString().toLowerCase() + " more");
                }
            }
        }else if((groupA.size() == 2) && (groupB.size() == 2)){

            getNewProportionUnit.add(scale_prop_four_first_grpB);
            getNewProportionUnit.add(scale_prop_four_second_grpB);
            getNewProportionUnit.add(scale_prop_four_first_grpA);
            getNewProportionUnit.add(scale_prop_four_second_grpA);
            addcomprop = true;

            //add quantity to mix into the database
            dbHelper.addQuantityToMix(noOfFomulation, qtyTMix);

            if (scale_prop_four_first_grpB <= ordqtyAvailableB.get(0)) {
                commentArr.add("Appropriate");
            } else {
                commentArr.add("Get " + decimalFormat.format(scaleprop_groupBFeed - ordqtyAvailableB.get(0)) + quantityTypeSpinner.getSelectedItem().toString().toLowerCase() + " more");
            }

            if (scale_prop_four_second_grpB <= ordqtyAvailableB.get(1)) {
                commentArr.add("Appropriate");
            } else {
                commentArr.add("Get " + decimalFormat.format(scaleprop_first_groupAFeed - ordqtyAvailableA.get(0)) + quantityTypeSpinner.getSelectedItem().toString().toLowerCase() + " more");
            }

            if (scale_prop_four_first_grpA <= ordqtyAvailableA.get(0)) {
                commentArr.add("Appropriate");
            } else {
                commentArr.add("Get " + decimalFormat.format(scaleprop_second_groupAFeed - ordqtyAvailableA.get(1)) + quantityTypeSpinner.getSelectedItem().toString().toLowerCase() + " more");
            }
            if (scale_prop_four_second_grpA <= ordqtyAvailableA.get(1)) {
                commentArr.add("Appropriate");
            } else {
                commentArr.add("Get " + decimalFormat.format(scaleprop_third_groupAFeed - ordqtyAvailableA.get(2)) + quantityTypeSpinner.getSelectedItem().toString().toLowerCase() + " more");
            }

        }

        return true;
    }

    private boolean setConditions() {
        int sumProtein = 0;
        int checkNoOfProteinPlant = Collections.frequency(classIngredientSelected, "Plant");
        int checkNoOfProteinAnimal = Collections.frequency(classIngredientSelected, "Animal");
        int checkNoOfFibre = Collections.frequency(classIngredientSelected, "High Fibre/Byproduct");
        int checkNoOfEnergy = Collections.frequency(classIngredientSelected, "Energy");

        sumProtein = checkNoOfProteinPlant + checkNoOfProteinAnimal;

        if(((sumProtein== 2) && (checkNoOfEnergy == 2)) ||
                ((checkNoOfEnergy == 3) && (sumProtein == 1)) ||
                ((checkNoOfEnergy == 1) && (sumProtein == 3)) ){

            divClass();
            return true;


        }else{
            if((sumProtein == 1) && (checkNoOfEnergy ==1) && (checkNoOfFibre == 2)){
                //check fibre CP
                divProteinFibreEnergy();
                return true;

            }else if((sumProtein == 1) && (checkNoOfEnergy == 2) && (checkNoOfFibre == 1)){
                //check fibre CP
                divProteinFibreEnergy();

                return true;

            }else if((sumProtein == 2) && (checkNoOfEnergy ==1) && (checkNoOfFibre == 1)){
                // check fibre CP
                divProteinFibreEnergy();

                return true;
            }

        }

        //group by CP
        divGroups();

        return true;
    }

    private boolean checkIfFourFeedareselected() {
        if((birdSelected.length() !=0) && (getQtyToMix.getText().toString().length() > 0)){
            int sum =0;
            //check number of protein either plant or animal
            int plantProtein = Collections.frequency(classIngredientSelected, "Plant");
            int animalProtein = Collections.frequency(classIngredientSelected, "Animal");
            int mineral = Collections.frequency(classIngredientSelected, "Minerals");
            sum = plantProtein + animalProtein;

            //  check the bird selected
            if((birdSelected.equals("Grower")) && (sum != 4) && conditionCheck && (mineral < 1)){

                Crude_proteintarget = 8.0;
                Calciumtarget = 0.8;
                Phosphorustarget = 0.4;

                // Check if the nutrient meet the requirement
                if((Collections.max(crudeProteinNutrient) >= Crude_proteintarget) && (Collections.min(crudeProteinNutrient) < Crude_proteintarget) ){

                    formulate = true;

                    if(formulate){
                        return true;
                    }


                }else{

                    formulate = false;
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
                    alertBuilder.setCancelable(true);
                    AlertDialog alertDialog = alertBuilder.create();
                    alertDialog.setTitle("FORMULATION CANNOT BE MADE!!");
                    alertDialog.setMessage("Please select ingredients where, one is not in protein class");
                    alertDialog.show();
//                    Toast.makeText(CreateDietActivity.this, "Formulation cannot be made", Toast.LENGTH_SHORT).show();

                }

            }else {

                if ((sum != 4) && conditionCheck & (mineral < 1)){

                    Crude_proteintarget = 8.5;
                    Calciumtarget = 3.4;
                    Phosphorustarget = 0.32;

//                double Crude_protein = 8.5, Calcium = 3.4, Phosphorus = 0.32;
                    // Check if the nutrient meet the requirement
                    if ((Collections.max(crudeProteinNutrient) >= Crude_proteintarget) && (Collections.min(crudeProteinNutrient) < Crude_proteintarget)) {

                        formulate = true;
                        if (formulate) {
                            return true;
                        }

                    } else {
                        formulate = false;

                        Intent intent = new Intent(this, SummaryActivity.class);
                        intent.putExtra("formuNo", noOfFomulation);
                        startActivity(intent);     AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
                        alertBuilder.setCancelable(true);
                        AlertDialog alertDialog = alertBuilder.create();
                        alertDialog.setTitle("FORMULATION CANNOT BE MADE!!");
                        alertDialog.setMessage("Please select ingredients where, one is not in protein class");
                        alertDialog.show();
//                    Toast.makeText(CreateDietActivity.this, "Formulation cannot be made", Toast.LENGTH_SHORT).show();


                    }
                }else{
                    formulate = false;
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
                    alertBuilder.setCancelable(true);
                    AlertDialog alertDialog = alertBuilder.create();
                    alertDialog.setTitle("FORMULATION CANNOT BE MADE!!");
                    alertDialog.setMessage("Please select ingredients where, one is not in protein class");
                    alertDialog.show();
                }
            }

        }else{
            Toast.makeText(CreateDietActivity.this, "Please Select a bird and Quantity to Mix", Toast.LENGTH_SHORT).show();
        }

        return false;
    }

    public void formulateForThreeFeeds(){
        // Check if the formulation can be carried out

        //        check protein formulation
        if((birdSelected.length() != 0) && (getQtyToMix.getText().toString().length() > 0)){
            int sum =  0;
            //check number of protein either plant or animal
            int plantProtein = Collections.frequency(classIngredientSelected, "Plant");
            int animalProtein = Collections.frequency(classIngredientSelected, "Animal");
            int mineral = Collections.frequency(classIngredientSelected, "Minerals");
            sum = plantProtein + animalProtein;

            //  check the bird selected
            if((birdSelected.equals("Grower")) && (sum != 3) && (mineral < 1)){
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
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
                    alertBuilder.setCancelable(true);
                    AlertDialog alertDialog = alertBuilder.create();
                    alertDialog.setTitle("FORMULATION CANNOT BE MADE!!");
                    alertDialog.setMessage("Please select ingredients where, one is not in protein class");
                    alertDialog.show();
//                    Toast.makeText(CreateDietActivity.this, "Formulation cannot be made", Toast.LENGTH_SHORT).show();

                }

            }else {

                if ((sum != 3) & (mineral < 1)){

                    Crude_proteintarget = 8.5;
                    Calciumtarget = 3.4;
                    Phosphorustarget = 0.32;

//                double Crude_protein = 8.5, Calcium = 3.4, Phosphorus = 0.32;
                    // Check if the nutrient meet the requirement
                    if ((Collections.max(crudeProteinNutrient) >= Crude_proteintarget) && (Collections.min(crudeProteinNutrient) < Crude_proteintarget)) {

                        formulate = true;
                        if (formulate) {
                            formulateProteinForThreeFeed(Crude_proteintarget, birdSelected);
                        }

                    } else {
                        formulate = false;

                        Intent intent = new Intent(this, SummaryActivity.class);
                        intent.putExtra("formuNo", noOfFomulation);
                        startActivity(intent);     AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
                        alertBuilder.setCancelable(true);
                        AlertDialog alertDialog = alertBuilder.create();
                        alertDialog.setTitle("FORMULATION CANNOT BE MADE!!");
                        alertDialog.setMessage("Please select ingredients where, one is not in protein class");
                        alertDialog.show();
//                    Toast.makeText(CreateDietActivity.this, "Formulation cannot be made", Toast.LENGTH_SHORT).show();


                    }
                }else{
                    formulate = false;
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
                    alertBuilder.setCancelable(true);
                    AlertDialog alertDialog = alertBuilder.create();
                    alertDialog.setTitle("FORMULATION CANNOT BE MADE!!");
                    alertDialog.setMessage("Please select ingredients where, one is not in protein class");
                    alertDialog.show();
                }
            }

        }else{
            Toast.makeText(CreateDietActivity.this, "Please Select a bird and quantity to mix", Toast.LENGTH_SHORT).show();
        }

    }

    public void formulateProteinForThreeFeed(Double targetProtein, String birdCat){

        // get the number of formulation made
        getNumberofFormulation();



        //condition 4 & 5: check number of energy and fibre

        if(!conditionCheck){
            checkIfContainEnergyAndFibre();

        }

        //condition 6 & 7: check number of protein and energy
        if(!conditionCheck){

        checkIfContainProteinAndEnergy();
        }

        //condition 8 & 9: check number of protein and fibre
        if(!conditionCheck){

        checkIfContainProteinAndFibre();
        }

        //condition 10: check number of protein, energy and fibre
        if(!conditionCheck){
        checkIfContainProteinFibreEnergy();

        }

        // add formulated ingredient into database
        if(finishformu ||finishProteinformu ){

            addFormulatedIngredientToDb(birdCat);
        }

    }

    private void checkIfContainProteinFibreEnergy() {

        int sumprotein = 0;
        int sum = 0;
        int checkNoOfProteinPlant = Collections.frequency(classIngredientSelected, "Plant");
        int checkNoOfProteinAnimal = Collections.frequency(classIngredientSelected, "Animal");
        int checkNoOfFibre = Collections.frequency(classIngredientSelected, "High Fibre/Byproduct");
        int checkNoOfEnergy = Collections.frequency(classIngredientSelected, "Energy");
        sumprotein = checkNoOfProteinPlant + checkNoOfProteinAnimal;

        if((sumprotein == 1 ) && (checkNoOfFibre==1) && (checkNoOfEnergy ==1) ){
            implementFormuProteinFibreEnergy();
            //conditionCheck = true;
        }


    }

    private void implementFormuProteinFibreEnergy() {
        divProteinFibreEnergy();
        reOrderGroup();
        calNewCPLevel();
        formulateProteinFeed(groupA, groupB);

        if(!saveAlready) {
            storeResultToDb();
            storecalculatedAnalysis(Crude_proteintarget, noOfFomulation, calCalcium.get(0), calPhosphorus.get(0), calEnergy.get(0));
        }

    }

    private void divProteinFibreEnergy(){
        groupA = new ArrayList <>();
        groupB = new ArrayList <>();
        comGroupA = new ArrayList <>();
        comGroupB = new ArrayList <>();
        ingredientGrpA = new ArrayList <>();
        ingredientGrpB = new ArrayList <>();

        groupB = new ArrayList <>();
        ingredientGrpB = new ArrayList <>();

        for (int i=0; i<classIngredientSelected.size(); i++){

            if((classIngredientSelected.get(i).equals("Animal")) || (classIngredientSelected.get(i).equals("Plant"))){

                groupA.add(crudeProteinNutrient.get(i));
                comGroupA.add(crudeProteinNutrient.get(i));

                ingredientGrpA.add(ingredientSelected.get(i));
            }else if(classIngredientSelected.get(i).equals("High Fibre/Byproduct")){
                if(crudeProteinNutrient.get(i) >= Crude_proteintarget){
                    groupA.add(crudeProteinNutrient.get(i));
                    comGroupA.add(crudeProteinNutrient.get(i));
                    ingredientGrpA.add(ingredientSelected.get(i));
                }else{
                    groupB.add(crudeProteinNutrient.get(i));
                    comGroupB.add(crudeProteinNutrient.get(i));
                    ingredientGrpB.add(ingredientSelected.get(i));
                }

            }else {
                groupB.add(crudeProteinNutrient.get(i));
                comGroupB.add(crudeProteinNutrient.get(i));
                ingredientGrpB.add(ingredientSelected.get(i));
            }

        }

    }

    private void checkIfContainProteinAndFibre() {
        int sum = 0;
        int sumProtein = 0;
        int checkNoOfProteinPlant = Collections.frequency(classIngredientSelected, "Plant");
        int checkNoOfProteinAnimal = Collections.frequency(classIngredientSelected, "Animal");
        int checkNoOfFibre = Collections.frequency(classIngredientSelected, "High Fibre/Byproduct");
        sumProtein = checkNoOfProteinPlant + checkNoOfProteinAnimal;
        sum = checkNoOfProteinPlant + checkNoOfProteinAnimal + checkNoOfFibre;

        if(sum == 3 && sumProtein > 0){
            implementFormuProtein();
        }
    }

    private void checkIfContainProteinAndEnergy() {
        int sum = 0;
        int sumProtein = 0;
        int checkNoOfProteinPlant = Collections.frequency(classIngredientSelected, "Plant");
        int checkNoOfProteinAnimal = Collections.frequency(classIngredientSelected, "Animal");
        int checkNoOfEnergy = Collections.frequency(classIngredientSelected, "Energy");
        sumProtein = checkNoOfProteinPlant + checkNoOfProteinAnimal;
        sum = checkNoOfProteinPlant + checkNoOfProteinAnimal + checkNoOfEnergy;

        if((sum == 3) && sumProtein > 0) {
            implementFormuProtein();
        }


    }

    private void checkIfContainEnergyAndFibre() {
        int sum = 0;
        int checkNoOfEnergy = Collections.frequency(classIngredientSelected, "Energy");
        int checkNoOfFibre = Collections.frequency(classIngredientSelected, "High Fibre/Byproduct");
        sum = checkNoOfEnergy + checkNoOfFibre;

        if(sum == 3){

            implementFormu();
            conditionCheck = true;
        }

    }

    private void implementFormuProtein() {

        divClass();
        reOrderGroup();
        calNewCPLevel();

        while(!finishProteinformu && !breakoff ){
            formulateProteinFeed(groupA, groupB);
            checkQtyScaledDown();
        }

            if(finishProteinformu) {
                setCommentArrForThreeFeed();
                storeResultToDb();
                storecalculatedAnalysis(Crude_proteintarget, noOfFomulation, calCalcium.get(0), calPhosphorus.get(0), calEnergy.get(0));


                if(formulate) {
                    Intent intent = new Intent(this, SummaryActivity.class);
                    intent.putExtra("formuNo", noOfFomulation);
                    startActivity(intent);
                }

            }
    }

    private void formulateProteinFeed(ArrayList<Double> groupDivA, ArrayList<Double> groupDivB) {
        if(groupDivA.size() > 1) {

            if (groupDivB.get(0) < Crude_proteintarget) {
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

                if(!reform) {
                    qtyTMix = Double.parseDouble(getQtyToMix.getText().toString());
                }

                if(nextpropTwoFeed){
                    assProp2first = formulationiter1[iter1];
                    assProp2secon = formulationiter2[iter1];
                    nextpropTwoFeed = false;
                }

                Double groupDivACP = Math.abs(groupDivB.get(0) - Crude_proteintarget);
                Double groupDivBCP = Math.abs(Crude_proteintarget - Total);
                Double forTotal = groupDivACP + groupDivBCP;

                Double newCPFirst = assProp2first * 0.01 * groupDivACP;
                Double newCPsecond = assProp2secon * 0.01 * groupDivACP;

                //Implement step 6
                double prop_100_kg_groupB = groupDivBCP * 100 / forTotal;
                double prop_100_kg_groupA_first = newCPFirst * 100 / forTotal;
                double prop_100_kg_groupA_second = newCPsecond * 100 / forTotal;

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
                newProportion.add(prop_100_kg_groupB);
                newProportion.add(prop_100_kg_groupA_first);
                newProportion.add(prop_100_kg_groupA_second);

                //store ingredient
                newIngredient.add(ordIngredientGrpB.get(0));
                newIngredient.add(ordIngredientGrpA.get(0));
                newIngredient.add(ordIngredientGrpA.get(1));

                //store qty specified
                qtySpecified.add(ordqtyAvailableB.get(0));
                qtySpecified.add(ordqtyAvailableA.get(0));
                qtySpecified.add(ordqtyAvailableA.get(1));


                //scale down proportion to mix
                scaleprop_groupBFeed = prop_100_kg_groupB * qtyTMix * 0.01;
                 scaleprop_first_groupAFeed = prop_100_kg_groupA_first * qtyTMix * 0.01;
                 scaleprop_second_groupAFeed = prop_100_kg_groupA_second * qtyTMix * 0.01;

                //calculate analysis for calcium
                double anacalcium = ((prop_100_kg_groupB * forCalB.get(0)) + (prop_100_kg_groupA_first * forCalA.get(0)) + (prop_100_kg_groupA_second * forCalA.get(1))) * 0.01;

                double anaphos = ((prop_100_kg_groupB * forPhosB.get(0)) + (prop_100_kg_groupA_first * forPhosA.get(0)) + (prop_100_kg_groupA_second * forPhosA.get(1))) * 0.01;

                double anaenergy = ((prop_100_kg_groupB * forenergyB.get(0)) + (prop_100_kg_groupA_first * forenergyA.get(0)) + (prop_100_kg_groupA_second * forenergyA.get(1))) * 0.01;

                calCalcium.add(anacalcium);
                calPhosphorus.add(anaphos);
                calEnergy.add(anaenergy);
                iterProtein = true;
                iterRest = false;



            }else{
                conditionCheck = false;
            }
        }else if(groupDivB.size() > 1) {

            if (Collections.min(groupDivB) < Crude_proteintarget) {

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


                if(!reform) {
                    qtyTMix = Double.parseDouble(getQtyToMix.getText().toString());
                }

                if(nextpropTwoFeed){
                    assProp2first = formulationiter1[iter1];
                    assProp2secon = formulationiter2[iter1];
                    nextpropTwoFeed = false;
                }

                Double groupDivACP = Math.abs(groupDivA.get(0) - Crude_proteintarget);
                Double groupDivBCP = Math.abs(Crude_proteintarget - Total);
                Double forTotal = groupDivACP + groupDivBCP;

                Double newCPFirst = assProp2first * 0.01 * groupDivACP;
                Double newCPsecond = assProp2secon * 0.01 * groupDivACP;

                //Implement step 6
                double prop_100_kg_groupB = groupDivBCP * 100 / forTotal;
                double prop_100_kg_groupA_first = newCPFirst * 100 / forTotal;
                double prop_100_kg_groupA_second = newCPsecond * 100 / forTotal;

                // Implement step 7
                double propfirstgroupB = 0.01 * prop_100_kg_groupB * groupA.get(0);
                double propfirstgroupA = 0.01 * prop_100_kg_groupA_first * groupB.get(0);
                double propsecondgroupA = 0.01 * prop_100_kg_groupA_second * groupB.get(1);

                //store class
                forClass.add(ordClassA.get(0));
                forClass.add(ordClassB.get(0));
                forClass.add(ordClassB.get(1));

                //store energy
                forEnergy.add(forenergyA.get(0));
                forEnergy.add(forenergyB.get(0));
                forEnergy.add(forenergyB.get(1));

                //store phosphorus
                forPhosphorus.add(forPhosA.get(0));
                forPhosphorus.add(forPhosB.get(0));
                forPhosphorus.add(forPhosB.get(1));

                // store calcium
                forCalcium.add(forCalA.get(0));
                forCalcium.add(forCalB.get(0));
                forCalcium.add(forCalB.get(1));

                //store crude_protein
                forCrudeProtein.add(groupA.get(0));
                forCrudeProtein.add(groupB.get(0));
                forCrudeProtein.add(groupB.get(1));

                //store proportion percentage
                newProportion.add(prop_100_kg_groupB);
                newProportion.add(prop_100_kg_groupA_first);
                newProportion.add(prop_100_kg_groupA_second);

                //store ingredient
                newIngredient.add(ordIngredientGrpA.get(0));
                newIngredient.add(ordIngredientGrpB.get(0));
                newIngredient.add(ordIngredientGrpB.get(1));

                //store qty specified
                qtySpecified.add(ordqtyAvailableA.get(0));
                qtySpecified.add(ordqtyAvailableB.get(0));
                qtySpecified.add(ordqtyAvailableB.get(1));


                //scale down proportion to mix
                 scaleprop_groupBFeed = prop_100_kg_groupB * qtyTMix * 0.01;
                 scaleprop_first_groupAFeed = prop_100_kg_groupA_first * qtyTMix * 0.01;
                 scaleprop_second_groupAFeed = prop_100_kg_groupA_second * qtyTMix * 0.01;

                //calculate analysis for calcium
                double anacalcium = ((prop_100_kg_groupB * forCalA.get(0)) + (prop_100_kg_groupA_first * forCalB.get(0)) + (prop_100_kg_groupA_second * forCalB.get(1))) * 0.01;

                double anaphos = ((prop_100_kg_groupB * forPhosA.get(0)) + (prop_100_kg_groupA_first * forPhosB.get(0)) + (prop_100_kg_groupA_second * forPhosB.get(1))) * 0.01;

                double anaenergy = ((prop_100_kg_groupB * forenergyA.get(0)) + (prop_100_kg_groupA_first * forenergyB.get(0)) + (prop_100_kg_groupA_second * forenergyB.get(1))) * 0.01;

                calCalcium.add(anacalcium);
                calPhosphorus.add(anaphos);
                calEnergy.add(anaenergy);
                iterProtein = true;
                iterRest = false;

            }else{
                conditionCheck = false;
            }
        }
    }

    private void implementFormu() {

        divGroups();
        reOrderGroup();
        calNewCPLevel();


        while(!finishformu && !breakoff ){

            if(groupA.size() > 1){
                formulateFeed(groupA, groupB);
                checkQtyScaledDown();

            }else{

                formulateFeed2(groupB, groupA);
                checkQtyScaledDown();
            }

        }

        if(finishformu) {
            setCommentArrForThreeFeed();
            storeResultToDb();
            storecalculatedAnalysis(Crude_proteintarget, noOfFomulation, calCalcium.get(0), calPhosphorus.get(0), calEnergy.get(0));
//            saveAlready = true;
            if(formulate) {
                Intent intent = new Intent(this, SummaryActivity.class);
                intent.putExtra("formuNo", noOfFomulation);
                startActivity(intent);
            }
        }

    }

    private void formulateFeed2(ArrayList<Double> groupDivA, ArrayList<Double> groupDivB) {

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

            if(!reform) {
                qtyTMix = Double.parseDouble(getQtyToMix.getText().toString());
            }

            if(nextpropTwoFeed){
                assProp2first = formulationiter1[iter1];
                assProp2secon = formulationiter2[iter1];
                nextpropTwoFeed = false;
            }

            double groupDivACP = Math.abs(groupDivB.get(0) - Crude_proteintarget);
            double groupDivBCP = Math.abs(Crude_proteintarget - Total);
            double forTotal = groupDivACP + groupDivBCP;

            double newCPFirst = assProp2first * 0.01 * groupDivACP;
            double newCPsecond = assProp2secon * 0.01 * groupDivACP;

            //Implement step 6
            double prop_100_kg_groupB = groupDivBCP * 100/forTotal;
            double prop_100_kg_groupA_first = newCPFirst * 100/forTotal;
            double prop_100_kg_groupA_second  = newCPsecond * 100/forTotal;

            // Implement step 7
            double propfirstgroupB = 0.01 * prop_100_kg_groupB * groupDivB.get(0);
            double propfirstgroupA = 0.01 * prop_100_kg_groupA_first * groupDivA.get(0);
            double propsecondgroupA = 0.01 * prop_100_kg_groupA_second * groupDivA.get(1);

            //store class
            forClass.add(ordClassA.get(0));
            forClass.add(ordClassB.get(0));
            forClass.add(ordClassB.get(1));

            //store energy
            forEnergy.add(forenergyA.get(0));
            forEnergy.add(forenergyB.get(0));
            forEnergy.add(forenergyB.get(1));

            //store phosphorus
            forPhosphorus.add(forPhosA.get(0));
            forPhosphorus.add(forPhosB.get(0));
            forPhosphorus.add(forPhosB.get(1));

            // store calcium
            forCalcium.add(forCalA.get(0));
            forCalcium.add(forCalB.get(0));
            forCalcium.add(forCalB.get(1));

            //store crude_protein
            forCrudeProtein.add(groupA.get(0));
            forCrudeProtein.add(groupB.get(0));
            forCrudeProtein.add(groupB.get(1));

            //store proportion percentage
            newProportion.add(prop_100_kg_groupB);
            newProportion.add(prop_100_kg_groupA_first);
            newProportion.add(prop_100_kg_groupA_second);

            //store ingredient
            newIngredient.add(ordIngredientGrpA.get(0));
            newIngredient.add(ordIngredientGrpB.get(0));
            newIngredient.add(ordIngredientGrpB.get(1));

            //store qty specified
            qtySpecified.add(ordqtyAvailableA.get(0));
            qtySpecified.add(ordqtyAvailableB.get(0));
            qtySpecified.add(ordqtyAvailableB.get(1));


            //scale down proportion to mix
            scaleprop_groupBFeed = prop_100_kg_groupB * qtyTMix * 0.01;
            scaleprop_first_groupAFeed = prop_100_kg_groupA_first * qtyTMix * 0.01;
            scaleprop_second_groupAFeed = prop_100_kg_groupA_second * qtyTMix * 0.01;

            //calculate analysis for calcium
            double anacalcium = ((prop_100_kg_groupB * forCalA.get(0)  * 0.01) + (prop_100_kg_groupA_first * forCalB.get(0) * 0.01) + (prop_100_kg_groupA_second * forCalB.get(1)) * 0.01);

            double anaphos = ((prop_100_kg_groupB * forPhosA.get(0)) + (prop_100_kg_groupA_first * forPhosB.get(0)) + (prop_100_kg_groupA_second * forPhosB.get(1))) * 0.01;

            double anaenergy = ((prop_100_kg_groupB * forenergyA.get(0)) + (prop_100_kg_groupA_first * forenergyB.get(0)) + (prop_100_kg_groupA_second * forenergyB.get(1))) * 0.01;

            calCalcium.add(anacalcium);
            calPhosphorus.add(anaphos);
            calEnergy.add(anaenergy);
            iterProtein = false;
            iterRest = true;



        }

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


            if(!reform) {
                qtyTMix = Double.parseDouble(getQtyToMix.getText().toString());

            }

            if(nextpropTwoFeed){
                assProp2first = formulationiter1[iter1];
                assProp2secon = formulationiter2[iter1];
                nextpropTwoFeed = false;
            }

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
            double propfirstgroupB = 0.01 * prop_100_kg_groupB * groupDivB.get(0);
            double propfirstgroupA = 0.01 * prop_100_kg_groupA_first * groupDivA.get(0);
            double propsecondgroupA = 0.01 * prop_100_kg_groupA_second * groupDivA.get(1);

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
            newProportion.add(prop_100_kg_groupB);
            newProportion.add(prop_100_kg_groupA_first);
            newProportion.add(prop_100_kg_groupA_second);

            //store ingredient
            newIngredient.add(ordIngredientGrpB.get(0));
            newIngredient.add(ordIngredientGrpA.get(0));
            newIngredient.add(ordIngredientGrpA.get(1));

            //store qty specified
            qtySpecified.add(ordqtyAvailableB.get(0));
            qtySpecified.add(ordqtyAvailableA.get(0));
            qtySpecified.add(ordqtyAvailableA.get(1));


            //scale down proportion to mix
            scaleprop_groupBFeed = prop_100_kg_groupB * qtyTMix * 0.01;
            scaleprop_first_groupAFeed = prop_100_kg_groupA_first * qtyTMix * 0.01;
             scaleprop_second_groupAFeed = prop_100_kg_groupA_second * qtyTMix * 0.01;

            //calculate analysis for calcium
            double anacalcium = ((prop_100_kg_groupB * forCalB.get(0)) + (prop_100_kg_groupA_first * forCalA.get(0)) + (prop_100_kg_groupA_second * forCalA.get(1))) * 0.01;

            double anaphos = ((prop_100_kg_groupB * forPhosB.get(0)) + (prop_100_kg_groupA_first * forPhosA.get(0)) + (prop_100_kg_groupA_second * forPhosA.get(1))) * 0.01;

            double anaenergy = ((prop_100_kg_groupB * forenergyB.get(0)) + (prop_100_kg_groupA_first * forenergyA.get(0)) + (prop_100_kg_groupA_second * forenergyA.get(1))) * 0.01;

            calCalcium.add(anacalcium);
            calPhosphorus.add(anaphos);
            calEnergy.add(anaenergy);

            iterProtein = false;
            iterRest = true;

        }

    }

    private void setCommentArrForThreeFeed(){

        getNewProportionUnit.add(scaleprop_groupBFeed);
        getNewProportionUnit.add(scaleprop_first_groupAFeed);
        getNewProportionUnit.add(scaleprop_second_groupAFeed);

        //add quantity to mix into the database
        dbHelper.addQuantityToMix(noOfFomulation, qtyTMix);


        if (scaleprop_groupBFeed <= ordqtyAvailableB.get(0)) {
            commentArr.add("Appropriate");
        } else {
            commentArr.add("Get " + decimalFormat.format(scaleprop_groupBFeed - ordqtyAvailableB.get(0)) + quantityTypeSpinner.getSelectedItem().toString().toLowerCase() + " more");
        }

        if (scaleprop_first_groupAFeed <= ordqtyAvailableA.get(0)) {
            commentArr.add("Appropriate");
        } else {
            commentArr.add("Get " + decimalFormat.format(scaleprop_first_groupAFeed - ordqtyAvailableA.get(0)) + quantityTypeSpinner.getSelectedItem().toString().toLowerCase() + " more");
        }

        if (scaleprop_second_groupAFeed <= ordqtyAvailableA.get(1)) {
            commentArr.add("Appropriate");
        } else {
            commentArr.add("Get " + decimalFormat.format(scaleprop_second_groupAFeed - ordqtyAvailableA.get(1)) + quantityTypeSpinner.getSelectedItem().toString().toLowerCase() + " more");
        }

    }

    private void checkQtyScaledDown() {

        double ExtraGrpA = scaleprop_first_groupAFeed - ordqtyAvailableA.get(0);
        double ExtraGrpB = scaleprop_second_groupAFeed - ordqtyAvailableA.get(1);


        if(ordqtyAvailableA.size()>1) {

            if ((scaleprop_first_groupAFeed <= ordqtyAvailableA.get(0)) && (scaleprop_second_groupAFeed > ordqtyAvailableA.get(1))) {

                if (iter1 == (formulationiter1.length - 1) && !alreadyDisplay ) {
                    breakoff = true;

                    //pop up a dialog to ask if the user want to scale down
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);

                    alertBuilder.setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    double qtyscaled = 0.0;
                                    double qtyavailableGrp = 0.0;
                                    qtyavailableGrp = ordqtyAvailableA.get(1);
                                    qtyscaled = scaleprop_second_groupAFeed;


                                    if (iterRest) {
                                        iter1 = 0;
                                        qtyTMix = Math.floor(qtyTMix * qtyavailableGrp / qtyscaled);

                                        while(!finishformu){

                                            if(groupA.size() > 1){
                                                formulateFeed(groupA, groupB);
                                                checkQtyScaledDown();

                                            }else{

                                                formulateFeed2(groupB, groupA);
                                                checkQtyScaledDown();
                                            }

                                        }

                                        if(finishformu) {
                                            setCommentArrForThreeFeed();
                                            storeResultToDb();
                                            storecalculatedAnalysis(Crude_proteintarget, noOfFomulation, calCalcium.get(0), calPhosphorus.get(0), calEnergy.get(0));
                                            if(formulate) {
                                                Intent intent = new Intent(getBaseContext(), SummaryActivity.class);
                                                intent.putExtra("formuNo", noOfFomulation);
                                                startActivity(intent);
                                            }
                                        }

                                    }

                                    if (iterProtein) {
                                        qtyTMix = Math.floor(qtyTMix * qtyavailableGrp / qtyscaled);
                                        while(!finishProteinformu && !breakoff ){
                                            formulateProteinFeed(groupA, groupB);
                                            checkQtyScaledDown();
                                        }

                                        if(finishProteinformu) {
                                            setCommentArrForThreeFeed();
                                            storeResultToDb();
                                            storecalculatedAnalysis(Crude_proteintarget, noOfFomulation, calCalcium.get(0), calPhosphorus.get(0), calEnergy.get(0));


                                            if(formulate) {
                                                Intent intent = new Intent(getBaseContext(), SummaryActivity.class);
                                                intent.putExtra("formuNo", noOfFomulation);
                                                startActivity(intent);
                                            }

                                        }
//
                                    }
                                    alreadyDisplay = true;
//
                                }

                            });

                    alertBuilder.setNegativeButton("No", (dialog, which) -> {
                        alreadyDisplay = true;
                        dialog.cancel();
                        setCommentArrForThreeFeed();
                        storeResultToDb();
                        storecalculatedAnalysis(Crude_proteintarget, noOfFomulation, calCalcium.get(0), calPhosphorus.get(0), calEnergy.get(0));

                            Intent intent = new Intent(getBaseContext(), SummaryActivity.class);
                            intent.putExtra("formuNo", noOfFomulation);
                            startActivity(intent);

                    });

                    AlertDialog alertDialog = alertBuilder.create();
                    alertDialog.setTitle("OBSERVATION!!");
                    alertDialog.setMessage(String.format("The target quantity can not be reached. You will need the following quantity to achieve it: \n" +
                            "Get " + decimalFormat.format(scaleprop_second_groupAFeed - ordqtyAvailableA.get(1)) + quantityTypeSpinner.getSelectedItem().toString().toLowerCase() + " more of %s. \n" +
                            " Do you want to scale down to the next mixable quantity?", ordIngredientGrpA.get(1)));
                    alertDialog.show();


                }

                if (iter1 == (formulationiter1.length - 1) && alreadyDisplay ) {
                    breakoff = true;

                    double qtyscaled = 0.0;
                    double qtyavailableGrp = 0.0;
                    qtyavailableGrp = ordqtyAvailableA.get(1);
                    qtyscaled = scaleprop_second_groupAFeed;


                    if (iterRest) {
                        iter1 = 0;
                        iter2 = 0;
                        qtyTMix = Math.floor(qtyTMix * qtyavailableGrp / qtyscaled);

                        while(!finishformu){

                            if(groupA.size() > 1){
                                formulateFeed(groupA, groupB);
                                checkQtyScaledDown();

                            }else{

                                formulateFeed2(groupB, groupA);
                                checkQtyScaledDown();
                            }

                        }

                        if(finishformu) {
                            setCommentArrForThreeFeed();
                            storeResultToDb();
                            storecalculatedAnalysis(Crude_proteintarget, noOfFomulation, calCalcium.get(0), calPhosphorus.get(0), calEnergy.get(0));
                            if(formulate) {
                                Intent intent = new Intent(getBaseContext(), SummaryActivity.class);
                                intent.putExtra("formuNo", noOfFomulation);
                                startActivity(intent);
                            }
                        }

                    }

                    if (iterProtein) {
                        qtyTMix = Math.floor(qtyTMix * qtyavailableGrp / qtyscaled);
                        while(!finishProteinformu && !breakoff ){
                            formulateProteinFeed(groupA, groupB);
                            checkQtyScaledDown();
                        }

                        if(finishProteinformu) {
                            setCommentArrForThreeFeed();
                            storeResultToDb();
                            storecalculatedAnalysis(Crude_proteintarget, noOfFomulation, calCalcium.get(0), calPhosphorus.get(0), calEnergy.get(0));


                            if(formulate) {
                                Intent intent = new Intent(getBaseContext(), SummaryActivity.class);
                                intent.putExtra("formuNo", noOfFomulation);
                                startActivity(intent);
                            }

                        }
//
                    }

                }


                    // 2nd ingridient not appropriate
                if (assProp2first == 96) {
                    iter1++;
                    nextpropTwoFeed = true;

                    if (iterRest) {

                        finishformu = false;
                    }

                    if (iterProtein) {
                        finishProteinformu = false;
                    }
                }

                    assProp2first++;
                    assProp2secon--;
                    if (iterRest) {
                        finishformu = false;
                    }

                    if (iterProtein) {
                        finishProteinformu = false;
                    }



            } else if ((scaleprop_first_groupAFeed > ordqtyAvailableA.get(0)) && (scaleprop_second_groupAFeed <= ordqtyAvailableA.get(1))) {

                if (iter1 == (formulationiter1.length - 1)) {

                    //pop up a dialog to ask if the user want to scale down
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);

                    alertBuilder.setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    double qtyscaled = 0.0;
                                    double qtyavailableGrp = 0.0;
                                    qtyavailableGrp = ordqtyAvailableA.get(0);
                                    qtyscaled = scaleprop_first_groupAFeed;
                                    reform = true;

                                    if (iterRest) {
                                        iter1 = 0;
                                        qtyTMix = Math.floor(qtyTMix * qtyavailableGrp / qtyscaled);

                                        while(!finishformu){

                                            if(groupA.size() > 1){
                                                formulateFeed(groupA, groupB);
                                                checkQtyScaledDown();

                                            }else{

                                                formulateFeed2(groupB, groupA);
                                                checkQtyScaledDown();
                                            }

                                        }

                                        if(finishformu) {
                                            setCommentArrForThreeFeed();
                                            storeResultToDb();
                                            storecalculatedAnalysis(Crude_proteintarget, noOfFomulation, calCalcium.get(0), calPhosphorus.get(0), calEnergy.get(0));
                                            if(formulate) {
                                                Intent intent = new Intent(getBaseContext(), SummaryActivity.class);
                                                intent.putExtra("formuNo", noOfFomulation);
                                                startActivity(intent);
                                            }
                                        }

                                    }

                                    if (iterProtein) {
                                        qtyTMix = Math.floor(qtyTMix * qtyavailableGrp / qtyscaled);
                                        reform = true;
                                        iter1 = 0;
                                        finishProteinformu = false;

                                        while(!finishProteinformu && !breakoff ){
                                            formulateProteinFeed(groupA, groupB);
                                            checkQtyScaledDown();
                                        }

                                        if(finishProteinformu) {
                                            setCommentArrForThreeFeed();
                                            storeResultToDb();
                                            storecalculatedAnalysis(Crude_proteintarget, noOfFomulation, calCalcium.get(0), calPhosphorus.get(0), calEnergy.get(0));


                                            if(formulate) {
                                                Intent intent = new Intent(getBaseContext(), SummaryActivity.class);
                                                intent.putExtra("formuNo", noOfFomulation);
                                                startActivity(intent);
                                            }

                                        }
//
                                    }
                                    alreadyDisplay = true;
//
                                }

                            });

                    alertBuilder.setNegativeButton("No", (dialog, which) -> {
                        alreadyDisplay = true;
                        dialog.cancel();
                        setCommentArrForThreeFeed();
                        storeResultToDb();
                        storecalculatedAnalysis(Crude_proteintarget, noOfFomulation, calCalcium.get(0), calPhosphorus.get(0), calEnergy.get(0));

                        Intent intent = new Intent(getBaseContext(), SummaryActivity.class);
                        intent.putExtra("formuNo", noOfFomulation);
                        startActivity(intent);

                    });

                    AlertDialog alertDialog = alertBuilder.create();
                    alertDialog.setTitle("OBSERVATION!!");
                    alertDialog.setMessage(String.format("The target quantity can not be reached. You will need the following quantity to achieve it: \n" +
                            "Get " + decimalFormat.format(scaleprop_first_groupAFeed - ordqtyAvailableA.get(0)) + quantityTypeSpinner.getSelectedItem().toString().toLowerCase() + " more of %s. \n" +
                            " Do you want to scale down to the next mixable quantity?", ordIngredientGrpA.get(0)));
                    alertDialog.show();


                }

                if (iter1 == (formulationiter1.length - 1) && alreadyDisplay ) {
                    breakoff = true;

                    double qtyscaled = 0.0;
                    double qtyavailableGrp = 0.0;
                    qtyavailableGrp = ordqtyAvailableA.get(0);
                    qtyscaled = scaleprop_first_groupAFeed;


                    if (iterRest) {
                        iter1 = 0;
                        qtyTMix = Math.floor(qtyTMix * qtyavailableGrp / qtyscaled);

                        while(!finishformu){

                            if(groupA.size() > 1){
                                formulateFeed(groupA, groupB);
                                checkQtyScaledDown();

                            }else{

                                formulateFeed2(groupB, groupA);
                                checkQtyScaledDown();
                            }

                        }

                        if(finishformu) {
                            setCommentArrForThreeFeed();
                            storeResultToDb();
                            storecalculatedAnalysis(Crude_proteintarget, noOfFomulation, calCalcium.get(0), calPhosphorus.get(0), calEnergy.get(0));
                            if(formulate) {
                                Intent intent = new Intent(getBaseContext(), SummaryActivity.class);
                                intent.putExtra("formuNo", noOfFomulation);
                                startActivity(intent);
                            }
                        }

                    }

                    if (iterProtein) {
                        qtyTMix = Math.floor(qtyTMix * qtyavailableGrp / qtyscaled);
                        while(!finishProteinformu && !breakoff ){
                            formulateProteinFeed(groupA, groupB);
                            checkQtyScaledDown();
                        }

                        if(finishProteinformu) {
                            setCommentArrForThreeFeed();
                            storeResultToDb();
                            storecalculatedAnalysis(Crude_proteintarget, noOfFomulation, calCalcium.get(0), calPhosphorus.get(0), calEnergy.get(0));


                            if(formulate) {
                                Intent intent = new Intent(getBaseContext(), SummaryActivity.class);
                                intent.putExtra("formuNo", noOfFomulation);
                                startActivity(intent);
                            }

                        }
//
                    }

                }

                // 2nd ingridient not appropriate
                if (assProp2first == 96) {
                    iter1++;
                    nextpropTwoFeed = true;
                    if (iterRest) {

                        finishformu = false;
                    }

                    if (iterProtein) {

                        finishProteinformu = false;
                    }
                }

                    assProp2first++;
                    assProp2secon--;
                    if (iterRest) {

                        finishformu = false;
                    }

                    if (iterProtein) {

                        finishProteinformu = false;
                    }



            }else if ((scaleprop_first_groupAFeed > ordqtyAvailableA.get(0)) && (scaleprop_second_groupAFeed > ordqtyAvailableA.get(1))){


                if(iter1 == (formulationiter1.length -1) && !alreadyDisplay) {
                    // popUpDialogTwofeed(double scalepropfirst,int qtyavailaA, String ingredienA, double scalepropsecond,int qtyavailaB, String ingredienB)
                    popUpDialogTwofeed( scaleprop_first_groupAFeed, ordqtyAvailableA.get(0),  ordIngredientGrpA.get(0),  scaleprop_second_groupAFeed, ordqtyAvailableA.get(1), ordIngredientGrpA.get(1));
                }


                if (iter1 == (formulationiter1.length - 1) && alreadyDisplay ) {
                    breakoff = true;

                    double qtyscaled = 0.0;
                    double qtyavailableGrp = 0.0;

                    if(ExtraGrpA >= ExtraGrpB) {
                        qtyavailableGrp = ordqtyAvailableA.get(0);
                        qtyscaled = scaleprop_first_groupAFeed;
                    }else{

                        qtyavailableGrp = ordqtyAvailableA.get(1);
                        qtyscaled = scaleprop_second_groupAFeed;

                    }


                        iter1 = 0;
                    if (iterRest) {
                        reform = true;
                        qtyTMix = Math.floor(qtyTMix * qtyavailableGrp / qtyscaled);

                        while(!finishformu){

                            if(groupA.size() > 1){
                                formulateFeed(groupA, groupB);
                                checkQtyScaledDown();

                            }else{

                                formulateFeed2(groupB, groupA);
                                checkQtyScaledDown();
                            }

                        }

                        if(finishformu) {
                            setCommentArrForThreeFeed();
                            storeResultToDb();
                            storecalculatedAnalysis(Crude_proteintarget, noOfFomulation, calCalcium.get(0), calPhosphorus.get(0), calEnergy.get(0));
                            if(formulate) {
                                Intent intent = new Intent(getBaseContext(), SummaryActivity.class);
                                intent.putExtra("formuNo", noOfFomulation);
                                startActivity(intent);
                            }
                        }

                    }

                    if (iterProtein) {
                        reform = true;
                        qtyTMix = Math.floor(qtyTMix * qtyavailableGrp / qtyscaled);
                        while(!finishProteinformu && !breakoff ){
                            formulateProteinFeed(groupA, groupB);
                            checkQtyScaledDown();
                        }

                        if(finishProteinformu) {
                            setCommentArrForThreeFeed();
                            storeResultToDb();
                            storecalculatedAnalysis(Crude_proteintarget, noOfFomulation, calCalcium.get(0), calPhosphorus.get(0), calEnergy.get(0));


                            if(formulate) {
                                Intent intent = new Intent(getBaseContext(), SummaryActivity.class);
                                intent.putExtra("formuNo", noOfFomulation);
                                startActivity(intent);
                            }

                        }
//
                    }

                }

                // 2nd ingridient not appropriate
                if (assProp2first == 96) {
                    iter1++;
                    nextpropTwoFeed = true;
                    if (iterRest) {

                        finishformu = false;
                    }

                    if (iterProtein) {

                        finishProteinformu = false;
                    }
                }

                assProp2first++;
                assProp2secon--;
                if (iterRest) {

                    finishformu = false;
                }

                if (iterProtein) {

                    finishProteinformu = false;
                }



            }

            if((scaleprop_first_groupAFeed <= ordqtyAvailableA.get(0)) || (scaleprop_second_groupAFeed <= ordqtyAvailableA.get(1))) {

                if (scaleprop_groupBFeed > ordqtyAvailableA.get(0)) {
                    if (iter1 == (formulationiter1.length - 1) && !alreadyDisplay) {
                        popUpDialogOnefeed(scaleprop_groupBFeed, ordqtyAvailableA.get(0), ordIngredientGrpA.get(0));
                    }

                    reform = true;

                    // 2nd ingridient not appropriate
                    if (assProp2secon == 96) {
                        iter1++;
                        nextpropTwoFeed = true;

                        if (iterRest) {
                            finishformu = false;
                        }

                        if (iterProtein) {
                            finishProteinformu = false;
                        }
                    }


                    assProp2first++;
                    assProp2secon--;

                    if (iterRest) {
                        finishformu = false;
                    }

                    if (iterProtein) {
                        finishProteinformu = false;
                    }

                    saveAlready = true;

                }
            }



            }else{
            if ((scaleprop_first_groupAFeed > ordqtyAvailableB.get(0)) && (scaleprop_second_groupAFeed <= ordqtyAvailableB.get(1))) {

                if (iter1 == (formulationiter1.length - 1)) {
                    //popUpDialogOnefeed(double scaleprop,int qtyavailaA, String ingredienA)
                    popUpDialogOnefeed(scaleprop_first_groupAFeed, ordqtyAvailableB.get(0),ordIngredientGrpB.get(0));

                }

                // 2nd ingridient not appropriate
                if (assProp2secon == 96) {
                    iter1++;
                    nextpropTwoFeed = true;

                    if (iterRest) {
                        finishformu = false;
                    }

                    if (iterProtein) {
                        finishProteinformu = false;
                    }
                }

                    assProp2first--;
                    assProp2secon++;
                    if (iterRest) {
                        finishformu = false;
                    }

                    if (iterProtein) {
                        finishProteinformu = false;
                    }



            }else if ((scaleprop_first_groupAFeed <= ordqtyAvailableB.get(0)) && (scaleprop_second_groupAFeed > ordqtyAvailableB.get(1))) {

                if (iter1 == (formulationiter1.length - 1) && !alreadyDisplay) {

                    popUpDialogOnefeed(scaleprop_second_groupAFeed, ordqtyAvailableB.get(1),ordIngredientGrpB.get(1));

                }

                if (iter1 == (formulationiter1.length - 1) && alreadyDisplay ) {
                    breakoff = true;

                    double qtyscaled = 0.0;
                    double qtyavailableGrp = 0.0;

                        qtyavailableGrp = ordqtyAvailableB.get(1);
                        qtyscaled = scaleprop_second_groupAFeed;



                    iter1 = 0;
                    if (iterRest) {
                        reform = true;
                        qtyTMix = Math.floor(qtyTMix * qtyavailableGrp / qtyscaled);

                        while(!finishformu){

                            if(groupA.size() > 1){
                                formulateFeed(groupA, groupB);
                                checkQtyScaledDown();

                            }else{

                                formulateFeed2(groupB, groupA);
                                checkQtyScaledDown();
                            }

                        }

                        if(finishformu) {
                            setCommentArrForThreeFeed();
                            storeResultToDb();
                            storecalculatedAnalysis(Crude_proteintarget, noOfFomulation, calCalcium.get(0), calPhosphorus.get(0), calEnergy.get(0));
                            if(formulate) {
                                Intent intent = new Intent(getBaseContext(), SummaryActivity.class);
                                intent.putExtra("formuNo", noOfFomulation);
                                startActivity(intent);
                            }
                        }

                    }

                    if (iterProtein) {
                        reform = true;
                        qtyTMix = Math.floor(qtyTMix * qtyavailableGrp / qtyscaled);
                        while(!finishProteinformu && !breakoff ){
                            formulateProteinFeed(groupA, groupB);
                            checkQtyScaledDown();
                        }

                        if(finishProteinformu) {
                            setCommentArrForThreeFeed();
                            storeResultToDb();
                            storecalculatedAnalysis(Crude_proteintarget, noOfFomulation, calCalcium.get(0), calPhosphorus.get(0), calEnergy.get(0));


                            if(formulate) {
                                Intent intent = new Intent(getBaseContext(), SummaryActivity.class);
                                intent.putExtra("formuNo", noOfFomulation);
                                startActivity(intent);
                            }

                        }
//
                    }

                }



                // 2nd ingridient not appropriate

                if (assProp2secon == 96) {
                    iter1++;
                    nextpropTwoFeed = true;

                    if (iterRest) {
                        finishformu = false;
                    }

                    if (iterProtein) {
                        finishProteinformu = false;
                    }
                }

                    assProp2first--;
                    assProp2secon++;
                    if (iterRest) {
                        finishformu = false;
                    }

                    if (iterProtein) {
                        finishProteinformu = false;
                    }



            }


            if ((scaleprop_first_groupAFeed > ordqtyAvailableB.get(0)) && (scaleprop_second_groupAFeed > ordqtyAvailableB.get(1))) {

                if(iter1 == (formulationiter1.length -1) && !alreadyDisplay) {
                    // popUpDialogTwofeed(double scalepropfirst,int qtyavailaA, String ingredienA, double scalepropsecond,int qtyavailaB, String ingredienB)
                    popUpDialogTwofeed( scaleprop_first_groupAFeed, ordqtyAvailableB.get(0),  ordIngredientGrpB.get(0),  scaleprop_second_groupAFeed, ordqtyAvailableB.get(1), ordIngredientGrpB.get(1));
                }


                if (iter1 == (formulationiter1.length - 1) && alreadyDisplay ) {
                    breakoff = true;

                    double qtyscaled = 0.0;
                    double qtyavailableGrp = 0.0;

                    if(ExtraGrpA >= ExtraGrpB) {
                        qtyavailableGrp = ordqtyAvailableB.get(0);
                        qtyscaled = scaleprop_first_groupAFeed;
                    }else{

                        qtyavailableGrp = ordqtyAvailableB.get(1);
                        qtyscaled = scaleprop_second_groupAFeed;

                    }


                    iter1 = 0;
                    if (iterRest) {
                        reform = true;
                        qtyTMix = Math.floor(qtyTMix * qtyavailableGrp / qtyscaled);

                        while(!finishformu){

                            if(groupA.size() > 1){
                                formulateFeed(groupA, groupB);
                                checkQtyScaledDown();

                            }else{

                                formulateFeed2(groupB, groupA);
                                checkQtyScaledDown();
                            }

                        }

                        if(finishformu) {
                            setCommentArrForThreeFeed();
                            storeResultToDb();
                            storecalculatedAnalysis(Crude_proteintarget, noOfFomulation, calCalcium.get(0), calPhosphorus.get(0), calEnergy.get(0));
                            if(formulate) {
                                Intent intent = new Intent(getBaseContext(), SummaryActivity.class);
                                intent.putExtra("formuNo", noOfFomulation);
                                startActivity(intent);
                            }
                        }

                    }

                    if (iterProtein) {
                        reform = true;
                        qtyTMix = Math.floor(qtyTMix * qtyavailableGrp / qtyscaled);
                        while(!finishProteinformu && !breakoff ){
                            formulateProteinFeed(groupA, groupB);
                            checkQtyScaledDown();
                        }

                        if(finishProteinformu) {
                            setCommentArrForThreeFeed();
                            storeResultToDb();
                            storecalculatedAnalysis(Crude_proteintarget, noOfFomulation, calCalcium.get(0), calPhosphorus.get(0), calEnergy.get(0));


                            if(formulate) {
                                Intent intent = new Intent(getBaseContext(), SummaryActivity.class);
                                intent.putExtra("formuNo", noOfFomulation);
                                startActivity(intent);
                            }

                        }
//
                    }

                }

                // 2nd ingridient not appropriate
                if (assProp2first == 96) {
                    iter1++;
                    nextpropTwoFeed = true;
                    if (iterRest) {

                        finishformu = false;
                    }

                    if (iterProtein) {

                        finishProteinformu = false;
                    }
                }

                assProp2first++;
                assProp2secon--;
                if (iterRest) {

                    finishformu = false;
                }

                if (iterProtein) {

                    finishProteinformu = false;
                }

            }

        }

        if((scaleprop_first_groupAFeed <= ordqtyAvailableB.get(0)) || (scaleprop_second_groupAFeed <= ordqtyAvailableB.get(1))){

            if(scaleprop_groupBFeed > ordqtyAvailableB.get(0)){
                if (iter1 == (formulationiter1.length - 1) && !alreadyDisplay) {
                    popUpDialogOnefeed(scaleprop_groupBFeed,ordqtyAvailableB.get(0),ordIngredientGrpA.get(0));
                }

                reform = true;

                // 2nd ingridient not appropriate
                if (assProp2secon == 96) {
                    iter1++;
                    nextpropTwoFeed = true;

                    if (iterRest) {
                        finishformu = false;
                    }

                    if (iterProtein) {
                        finishProteinformu = false;
                    }
                }


                assProp2first++;
                assProp2secon--;

                if (iterRest) {
                    finishformu = false;
                }

                if (iterProtein) {
                    finishProteinformu = false;
                }

                saveAlready = true;

            }
        }


    }

    private void popUpDialogOnefeed(double scaleprop,int qtyavailaA, String ingredienA){
        breakoff = true;

        //pop up a dialog to ask if the user want to scale down
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);

        alertBuilder.setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        double qtyscaled = 0.0;
                        double qtyavailableGrp = 0.0;
                        qtyavailableGrp = qtyavailaA;
                        qtyscaled = scaleprop;


                        if (iterRest) {
                            iter1 = 0;
                            qtyTMix = Math.floor(qtyTMix * qtyavailableGrp / qtyscaled);

                            while(!finishformu){

                                if(groupA.size() > 1){
                                    formulateFeed(groupA, groupB);
                                    checkQtyScaledDown();

                                }else{

                                    formulateFeed2(groupB, groupA);
                                    checkQtyScaledDown();
                                }

                            }

                            if(finishformu) {
                                setCommentArrForThreeFeed();
                                storeResultToDb();
                                storecalculatedAnalysis(Crude_proteintarget, noOfFomulation, calCalcium.get(0), calPhosphorus.get(0), calEnergy.get(0));
                                if(formulate) {
                                    Intent intent = new Intent(getBaseContext(), SummaryActivity.class);
                                    intent.putExtra("formuNo", noOfFomulation);
                                    startActivity(intent);
                                }
                            }

                        }

                        if (iterProtein) {
                            qtyTMix = Math.floor(qtyTMix * qtyavailableGrp / qtyscaled);
                            while(!finishProteinformu && !breakoff ){
                                formulateProteinFeed(groupA, groupB);
                                checkQtyScaledDown();
                            }

                            if(finishProteinformu) {
                                setCommentArrForThreeFeed();
                                storeResultToDb();
                                storecalculatedAnalysis(Crude_proteintarget, noOfFomulation, calCalcium.get(0), calPhosphorus.get(0), calEnergy.get(0));


                                if(formulate) {
                                    Intent intent = new Intent(getBaseContext(), SummaryActivity.class);
                                    intent.putExtra("formuNo", noOfFomulation);
                                    startActivity(intent);
                                }

                            }
//
                        }
                        alreadyDisplay = true;
//
                    }

                });

        alertBuilder.setNegativeButton("No", (dialog, which) -> {
            alreadyDisplay = true;
            dialog.cancel();
            setCommentArrForThreeFeed();
            storeResultToDb();
            storecalculatedAnalysis(Crude_proteintarget, noOfFomulation, calCalcium.get(0), calPhosphorus.get(0), calEnergy.get(0));

            Intent intent = new Intent(getBaseContext(), SummaryActivity.class);
            intent.putExtra("formuNo", noOfFomulation);
            startActivity(intent);

        });


            AlertDialog alertDialog = alertBuilder.create();
            alertDialog.setTitle("OBSERVATION!!");
            alertDialog.setMessage(String.format("The target quantity can not be reached. You will need the following quantity to achieve it: \n" +
                    "Get " + decimalFormat.format(scaleprop - qtyavailaA) + quantityTypeSpinner.getSelectedItem().toString().toLowerCase() + " more of %s. \n" +
                    " Do you want to scale down to the next mixable quantity?", ingredienA));

            alertDialog.show();
    }

    private void popUpDialogTwofeed(double scalepropfirst,int qtyavailaA, String ingredienA, double scalepropsecond,int qtyavailaB, String ingredienB){
        breakoff = true;

        //pop up a dialog to ask if the user want to scale down
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);

        alertBuilder.setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        double qtyscaled = 0.0;
                        double qtyavailableGrp = 0.0;

                        if(scalepropfirst <= scalepropsecond){
                            qtyavailableGrp = qtyavailaA;
                            qtyscaled = scalepropsecond;

                        }else{
                            qtyavailableGrp = ordqtyAvailableA.get(1);
                            qtyscaled = scalepropfirst;

                        }

//                        alreadyDisplay = true;


                        if (iterRest) {
                            iter1 = 0;
                            qtyTMix = Math.floor(qtyTMix * qtyavailableGrp / qtyscaled);

                            while(!finishformu){

                                if(groupA.size() > 1){
                                    formulateFeed(groupA, groupB);
                                    checkQtyScaledDown();

                                }else{

                                    formulateFeed2(groupB, groupA);
                                    checkQtyScaledDown();
                                }

                            }

                            if(finishformu) {
                                setCommentArrForThreeFeed();
                                storeResultToDb();
                                storecalculatedAnalysis(Crude_proteintarget, noOfFomulation, calCalcium.get(0), calPhosphorus.get(0), calEnergy.get(0));
                                if(formulate) {
                                    Intent intent = new Intent(getBaseContext(), SummaryActivity.class);
                                    intent.putExtra("formuNo", noOfFomulation);
                                    startActivity(intent);
                                }
                            }

                        }

                        if (iterProtein) {
                            qtyTMix = Math.floor(qtyTMix * qtyavailableGrp / qtyscaled);
                            while(!finishProteinformu && !breakoff ){
                                formulateProteinFeed(groupA, groupB);
                                checkQtyScaledDown();
                            }

                            if(finishProteinformu) {
                                setCommentArrForThreeFeed();
                                storeResultToDb();
                                storecalculatedAnalysis(Crude_proteintarget, noOfFomulation, calCalcium.get(0), calPhosphorus.get(0), calEnergy.get(0));


                                if(formulate) {
                                    Intent intent = new Intent(getBaseContext(), SummaryActivity.class);
                                    intent.putExtra("formuNo", noOfFomulation);
                                    startActivity(intent);
                                }

                            }
//
                        }
                        alreadyDisplay = true;
//
                    }

                });

        alertBuilder.setNegativeButton("No", (dialog, which) -> {
            alreadyDisplay = true;
            dialog.cancel();
            setCommentArrForThreeFeed();
            storeResultToDb();
            storecalculatedAnalysis(Crude_proteintarget, noOfFomulation, calCalcium.get(0), calPhosphorus.get(0), calEnergy.get(0));

            Intent intent = new Intent(getBaseContext(), SummaryActivity.class);
            intent.putExtra("formuNo", noOfFomulation);
            startActivity(intent);

        });


        AlertDialog alertDialog = alertBuilder.create();
        alertDialog.setTitle("OBSERVATION!!");
            alertDialog.setMessage("The target quantity can not be reached. You will need the following quantity to achieve it: \n " +
                    (decimalFormat.format(scalepropfirst - qtyavailaA) + quantityTypeSpinner.getSelectedItem().toString().toLowerCase() + " more of " + ingredienA) + "\n " + (decimalFormat.format(scalepropsecond - qtyavailaB) + quantityTypeSpinner.getSelectedItem().toString().toLowerCase() + " more of " + ingredienB) +
                    "\nDo you want to scale down to the next mixable quantity?");

        alertDialog.show();
    }


    private boolean calNewCPLevelFourFeed() {

        if(groupA.size() == 2){
            Double firstNutrient = (0.01 * assProp2first) * groupA.get(0);
            Double secondNutrient = (0.01 * assProp2secon) * groupA.get(1);
            Total2A = firstNutrient + secondNutrient;
        }

        if(groupB.size() == 2){
            Double firstNutrient = (0.01 * assProp2first) * groupB.get(0);
            Double secondNutrient = (0.01 * assProp2secon) * groupB.get(1);
            Total2B = firstNutrient + secondNutrient;
            return true;
        }

        if(groupA.size() == 3){
            Double firstNutrient = (0.01 * formulationiter3c[countProp]) * groupA.get(0);
            Double secondNutrient = (0.01 * formulationiter3b[countProp]) * groupA.get(1);
            Double thirdNutrient = (0.01 * formulationiter3a[countProp]) * groupA.get(2);
            Total3A = firstNutrient + secondNutrient + thirdNutrient;
            return true;
        }

        if(groupB.size() == 3){
            Double firstNutrient = (0.01 * formulationiter3c[countProp]) * groupB.get(0);
            Double secondNutrient = (0.01 * formulationiter3b[countProp]) * groupB.get(1);
            Double thirdNutrient = (0.01 * formulationiter3a[countProp]) * groupB.get(2);
            Total3B = firstNutrient + secondNutrient + thirdNutrient;
            return true;
        }

        return false;


    }

    private void calNewCPLevel() {

        if(groupA.size() == 2){
            Double firstNutrient = (0.01 * assProp2first) * groupA.get(0);
            Double secondNutrient = (0.01 * assProp2secon) * groupA.get(1);
            Total = firstNutrient + secondNutrient;
        }

        if(groupB.size() == 2){
            Double firstNutrient = (0.01 * assProp2first) * groupB.get(0);
            Double secondNutrient = (0.01 * assProp2secon) * groupB.get(1);
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

                ordIngredientGrpA.add(ingredientSelected.get(crudeProteinNutrient.indexOf(groupA.get(i))));
                ordqtyAvailableA.add(qtyselected.get(crudeProteinNutrient.indexOf(groupA.get(i))));
                forCalA.add(calciumNutrient.get(crudeProteinNutrient.indexOf(groupA.get(i))));
                forPhosA.add(phosphorusNutrient.get(crudeProteinNutrient.indexOf(groupA.get(i))));
                forenergyA.add(energyNutrient.get(crudeProteinNutrient.indexOf(groupA.get(i))));
                ordClassA.add(classIngredientSelected.get(crudeProteinNutrient.indexOf(groupA.get(i))));

            }

            ordIngredientGrpB.add(ingredientGrpB.get(0));
            ordqtyAvailableB.add(qtyselected.get(crudeProteinNutrient.indexOf(groupB.get(0))));
            forCalB.add(calciumNutrient.get(crudeProteinNutrient.indexOf(groupB.get(0))));
            forPhosB.add(phosphorusNutrient.get(crudeProteinNutrient.indexOf(groupB.get(0))));
            forenergyB.add(energyNutrient.get(crudeProteinNutrient.indexOf(groupB.get(0))));
            ordClassB.add(classIngredientSelected.get(crudeProteinNutrient.indexOf(groupB.get(0))));
        }

        if(groupB.size()>1){
            Collections.sort(groupB);
            for (int i=0; i<groupB.size(); i++){

                ordIngredientGrpB.add(ingredientSelected.get(crudeProteinNutrient.indexOf(groupB.get(i))));
                ordqtyAvailableB.add(qtyselected.get(crudeProteinNutrient.indexOf(groupB.get(i))));
                forCalB.add(calciumNutrient.get(crudeProteinNutrient.indexOf(groupB.get(i))));
                forPhosB.add(phosphorusNutrient.get(crudeProteinNutrient.indexOf(groupB.get(i))));
                forenergyB.add(energyNutrient.get(crudeProteinNutrient.indexOf(groupB.get(i))));
                ordClassB.add(classIngredientSelected.get(crudeProteinNutrient.indexOf(groupB.get(i))));
            }

            ordIngredientGrpA.add(ingredientGrpA.get(0));
            ordqtyAvailableA.add(qtyselected.get(crudeProteinNutrient.indexOf(groupA.get(0))));
            forCalA.add(calciumNutrient.get(crudeProteinNutrient.indexOf(groupA.get(0))));
            forPhosA.add(phosphorusNutrient.get(crudeProteinNutrient.indexOf(groupA.get(0))));
            forenergyA.add(energyNutrient.get(crudeProteinNutrient.indexOf(groupA.get(0))));
            ordClassA.add(classIngredientSelected.get(crudeProteinNutrient.indexOf(groupA.get(0))));
        }
    }

    private void reOrderGroupFour() {
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

                ordIngredientGrpA.add(ingredientSelected.get(crudeProteinNutrient.indexOf(groupA.get(i))));
                ordqtyAvailableA.add(qtyselected.get(crudeProteinNutrient.indexOf(groupA.get(i))));
                forCalA.add(calciumNutrient.get(crudeProteinNutrient.indexOf(groupA.get(i))));
                forPhosA.add(phosphorusNutrient.get(crudeProteinNutrient.indexOf(groupA.get(i))));
                forenergyA.add(energyNutrient.get(crudeProteinNutrient.indexOf(groupA.get(i))));
                ordClassA.add(classIngredientSelected.get(crudeProteinNutrient.indexOf(groupA.get(i))));

            }

            if(groupB.size() == 1){
                ordIngredientGrpB.add(ingredientGrpB.get(0));
                ordqtyAvailableB.add(qtyselected.get(crudeProteinNutrient.indexOf(groupB.get(0))));
                forCalB.add(calciumNutrient.get(crudeProteinNutrient.indexOf(groupB.get(0))));
                forPhosB.add(phosphorusNutrient.get(crudeProteinNutrient.indexOf(groupB.get(0))));
                forenergyB.add(energyNutrient.get(crudeProteinNutrient.indexOf(groupB.get(0))));
                ordClassB.add(classIngredientSelected.get(crudeProteinNutrient.indexOf(groupB.get(0))));
            }
        }

        if(groupB.size()>1){
            Collections.sort(groupB);
            for (int i=0; i<groupB.size(); i++){

                ordIngredientGrpB.add(ingredientSelected.get(crudeProteinNutrient.indexOf(groupB.get(i))));
                ordqtyAvailableB.add(qtyselected.get(crudeProteinNutrient.indexOf(groupB.get(i))));
                forCalB.add(calciumNutrient.get(crudeProteinNutrient.indexOf(groupB.get(i))));
                forPhosB.add(phosphorusNutrient.get(crudeProteinNutrient.indexOf(groupB.get(i))));
                forenergyB.add(energyNutrient.get(crudeProteinNutrient.indexOf(groupB.get(i))));
                ordClassB.add(classIngredientSelected.get(crudeProteinNutrient.indexOf(groupB.get(i))));
            }

            if(groupA.size() == 1 ){
                ordIngredientGrpA.add(ingredientGrpA.get(0));
                ordqtyAvailableA.add(qtyselected.get(crudeProteinNutrient.indexOf(groupA.get(0))));
                forCalA.add(calciumNutrient.get(crudeProteinNutrient.indexOf(groupA.get(0))));
                forPhosA.add(phosphorusNutrient.get(crudeProteinNutrient.indexOf(groupA.get(0))));
                forenergyA.add(energyNutrient.get(crudeProteinNutrient.indexOf(groupA.get(0))));
                ordClassA.add(classIngredientSelected.get(crudeProteinNutrient.indexOf(groupA.get(0))));
            }

        }
    }

    private void divClass(){
        groupA = new ArrayList <>();
        groupB = new ArrayList <>();
        comGroupA = new ArrayList <>();
        comGroupB = new ArrayList <>();
        ingredientGrpA = new ArrayList <>();
        ingredientGrpB = new ArrayList <>();

        groupB = new ArrayList <>();
        ingredientGrpB = new ArrayList <>();

        for (int i=0; i<classIngredientSelected.size(); i++){

            if((classIngredientSelected.get(i).equals("Animal")) || (classIngredientSelected.get(i).equals("Plant"))){

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

    private void divGroups() {
        groupA = new ArrayList <>();
        groupB = new ArrayList <>();
        comGroupA = new ArrayList <>();
        comGroupB = new ArrayList <>();
        ingredientGrpA = new ArrayList <>();
        ingredientGrpB = new ArrayList <>();

        groupB = new ArrayList <>();
        ingredientGrpB = new ArrayList <>();

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
        if((birdSelected.length() != 0) && (getQtyToMix.getText().toString().length() > 0)){
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
                        formulateTwoFeed();

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

//                String additionInformation = "To attain the recommended calcium (3.4%) and phosphorus level (0.32) level, kindly add up feed ingredient from the mineral based Ingredient(s). Below are listed some of the ingredient(s) that falls within this class.\n" +
//                        "1. Bone Meal   2. Oyester Shell\n" +
//                        "3.Limestone    4. Egg Shell meal\n" +
//                        "5. Periwinkle Shell    6. Dicalcium Phosphate";
//                Intent i = new Intent(CreateDietActivity.this, SummaryActivity.class);
//                i.putExtra("addition", additionInformation);
//                startActivity(i);


//                double Crude_protein = 8.5, Calcium = 3.4, Phosphorus = 0.32;
                // Check if the nutrient meet the requirement
                if((Collections.max(crudeProteinNutrient) >= Crude_proteintarget) && (Collections.min(crudeProteinNutrient) < Crude_proteintarget) ){

                    formulate = true;
                    if(formulate) {
//                        formulateProtein(Crude_proteintarget, birdSelected);

                        formulateTwoFeed();
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
            Toast.makeText(CreateDietActivity.this, "Please Select a bird and Quantity To mix", Toast.LENGTH_SHORT).show();
        }

    }

    public void conditionForTwoFeed(){
        if(scaledownToForm){
            if(qtyToAddGrpA > 0 && qtyToAddGrpB <=  0){
                Double newQty = 0.00;
                newQty = (qtyAvailableGrpA * qtyToMix)/scaledownA;
                qtyToMix = Math.floor(newQty);
                recalculate = true;
                maxConditionCheck = false;

            }else if(qtyToAddGrpB > 0 && qtyToAddGrpA <= 0){
                Double newQty = 0.00;
                newQty = (qtyAvailableGrpB * qtyToMix)/scaledownB;
                qtyToMix = Math.floor(newQty);
                recalculate = true;
                maxConditionCheck = false;
            }else if(qtyToAddGrpA > 0 && qtyToAddGrpB > 0){
                if (qtyToAddGrpA > qtyToAddGrpB){
                    Double newQty = 0.00;
                    newQty = (qtyAvailableGrpA * qtyToMix)/scaledownA;
                    qtyToMix = Math.floor(newQty);
                    recalculate = true;
                    minConditionCheck = false;

                } else if (qtyToAddGrpB > qtyToAddGrpA){
                    Double newQty = 0.00;
                    newQty = (qtyAvailableGrpB * qtyToMix)/scaledownB;
                    qtyToMix = Math.floor(newQty);
                    recalculate = true;
                    minConditionCheck = false;
                }
            }
//                formulateForTwoFeed();
            formulateProtein(Crude_proteintarget, birdSelected);

        }


    }

    private void formulateTwoFeed() {
        formulateProtein(Crude_proteintarget, birdSelected);

        restOthers();

    }

    public boolean storeFeedToDb(){
        //add quantity to mix into the database
        dbHelper.addQuantityToMix(noOfFomulation, qtyToMix);


        if(scaledownB <= qtyAvailableGrpB){
            commentArr.add("Appropriate");
        }else{
            commentArr.add("Get " +  decimalFormat.format(scaledownB - qtyAvailableGrpB) + quantityTypeSpinner.getSelectedItem().toString().toLowerCase() + " more");
        }

        if(scaledownA <= qtyAvailableGrpA){
            commentArr.add("Appropriate");
        }else{
            commentArr.add("Get " + decimalFormat.format(scaledownA - qtyAvailableGrpA) + quantityTypeSpinner.getSelectedItem().toString().toLowerCase() +" more");
        }

        addResultToDb();
        storecalculatedAnalysis(newCrude,noOfFomulation,newCal,newPhos,newEnergy);
        return true;
    }

    private void restOthers(){
        //Check the quantity available is enough to mix feed
        if(((qtyToAddGrpA > 0) || (qtyToAddGrpB > 0)) && !recalculate){

            if((qtyToAddGrpA <= 0) & qtyToAddGrpB > 0) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
//            alertBuilder.setMessage("OBSERVATION!!!");

                alertBuilder.setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                scaledownToForm = true;
                                answerDialog = true;
                                conditionForTwoFeed();
                                if (formulate){
                                boolean stored =  storeFeedToDb();
                                    Intent intent = new Intent(getBaseContext(), SummaryActivity.class);
                                    intent.putExtra("formuNo", noOfFomulation);
                                    startActivity(intent);
//                                    getBaseContext().startActivity(intent);
                                }
                            }

                        });

                alertBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        recalculate = false;
                        maxConditionCheck = true;
                        scaledownToForm = false;
                        answerDialog = true;
                        dialog.cancel();
//                    formulateForTwoFeed();

                    if (formulate){
                        boolean stored =  storeFeedToDb();
                        Intent intent = new Intent(getBaseContext(), SummaryActivity.class);
                        intent.putExtra("formuNo", noOfFomulation);
                        startActivity(intent);
//                        getBaseContext().startActivity(intent);
                    }

                    }
                });

                AlertDialog alertDialog = alertBuilder.create();
                alertDialog.setTitle("OBSERVATION!!");
                alertDialog.setMessage(String.format("The target quantity can not be reached. You will need the following quantity to achieve it: \n" +
                        "Get " + decimalFormat.format(scaledownB - qtyAvailableGrpB) + quantityTypeSpinner.getSelectedItem().toString().toLowerCase() + " more of %s. \n" +
                        " Do you want to scale down to the next mixable quantity?", ingredientB));
                alertDialog.show();
            } else if((qtyToAddGrpA > 0) & (qtyToAddGrpB <= 0)){

                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
                alertBuilder.setMessage("OBSERVATION!!!");

                alertBuilder.setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                recalculate = true;
                                minConditionCheck = false;
                                scaledownToForm = true;
                                recalculate = true;
                                answerDialog = true;
                                conditionForTwoFeed();
//                                Double newQty = 0.00;
//                                newQty = (qtyAvailableGrpA * qtyToMix)/scaledownA;
//                                qtyToMix = Math.floor(newQty);
//                                formulateForTwoFeed();
//
                                if (formulate){
                                boolean stored =  storeFeedToDb();
                                    Intent intent = new Intent(getBaseContext(), SummaryActivity.class);
                                    intent.putExtra("formuNo", noOfFomulation);
                                    startActivity(intent);
//                                    getBaseContext().startActivity(intent);
                                }

                            }
                        });

                alertBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        recalculate = false;
                        minConditionCheck = true;
                        scaledownToForm = false;
                        answerDialog = true;
                        dialog.cancel();

//
//                        formulateForTwoFeed();
//
//
                        if (formulate){
                        boolean stored =  storeFeedToDb();
                            Intent intent = new Intent(getBaseContext(), SummaryActivity.class);
                            intent.putExtra("formuNo", noOfFomulation);
                            startActivity(intent);
//                            getBaseContext().startActivity(intent);
                        }

                    }
                });
                AlertDialog alertDialog = alertBuilder.create();
                alertDialog.setTitle("OBSERVATION!!!");
                alertDialog.setMessage(String.format("The target quantity can not be reached. You will need the following quantity to achieve it: \n" +
                        "Get " +  decimalFormat.format(scaledownA - qtyAvailableGrpA) + quantityTypeSpinner.getSelectedItem().toString().toLowerCase() + " more of %s. \n" +
                        " Do you want to scale down to the next mixable quantity?", ingredientA));
                alertDialog.show();

            }else if((qtyToAddGrpA > 0) || (qtyToAddGrpB > 0)){

                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
                alertBuilder.setMessage("OBSERVATION!!!");

                alertBuilder.setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                scaledownToForm = true;
                                conditionForTwoFeed();
                                boolean stored =  storeFeedToDb();

                                if (formulate){
                                    Intent intent = new Intent(getBaseContext(), SummaryActivity.class);
                                    intent.putExtra("formuNo", noOfFomulation);
                                    startActivity(intent);
                                }

                            }
                        });

                alertBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        recalculate = false;
                        minConditionCheck = true;
                        scaledownToForm = false;
                        dialog.cancel();
//                        answerDialog = true;

                        if (formulate){
                            storeFeedToDb();
                            Intent intent = new Intent(getBaseContext(), SummaryActivity.class);
                            intent.putExtra("formuNo", noOfFomulation);
                            startActivity(intent);
                        }

                    }
                });
                AlertDialog alertDialog = alertBuilder.create();
                alertDialog.setTitle("OBSERVATION!!!");
                alertDialog.setMessage("The target quantity can not be reached. You will need the following quantity to achieve it: \n " +
                        (decimalFormat.format(scaledownA - qtyAvailableGrpA) + quantityTypeSpinner.getSelectedItem().toString().toLowerCase() + " more of " + ingredientA ) + "\n " + (decimalFormat.format(scaledownB - qtyAvailableGrpB) + quantityTypeSpinner.getSelectedItem().toString().toLowerCase() + " more of " + ingredientB) +
                        "\nDo you want to scale down to the next mixable quantity?");
                alertDialog.show();

            }
        } else {
            if(qtyToAddGrpA <= 0 && qtyToAddGrpB <=  0){
                formulate = true;

                if (formulate){
                    storeFeedToDb();
                    Intent intent = new Intent(getBaseContext(), SummaryActivity.class);
                    intent.putExtra("formuNo", noOfFomulation);
                    startActivity(intent);

                }
            }
            recalculate = recalculate || false;
            maxConditionCheck = true;
            answerDialog = true;
        }



    }

    public void addFormulatedIngredientToDb(String birdCat){

        for(int i=0; i<ingredientSelected.size(); i++){
            dbHelper.addIngredient(ingredientSelected.get(i),noOfFomulation, birdCat, qtyselected.get(i),
                    classIngredientSelected.get(i),calciumNutrient.get(i),crudeProteinNutrient.get(i),
                    phosphorusNutrient.get(i),energyNutrient.get(i));
        }

    }

    public boolean formulateProtein(Double targetProtein, String birdCat){
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


        double ingredientWithMaxCPValue = 0.0;
        double ingredientWithMinCPValue = 0.0;

        ingredientWithMaxCP = ingredientSelected.get(crudeProteinNutrient.indexOf(Collections.max(crudeProteinNutrient)));
        ingredientWithMinCP = ingredientSelected.get(crudeProteinNutrient.indexOf(Collections.min(crudeProteinNutrient)));

        ingredientA = ingredientWithMinCP;
        ingredientB = ingredientWithMaxCP;

        // store the ingredient selected
        newIngredient.add(ingredientWithMaxCP);
        newIngredient.add(ingredientWithMinCP);


        //   Toast.makeText(CreateDietActivity.this, "crop" + ingredientWithMaxCP, Toast.LENGTH_SHORT).show();
        ingredientWithMaxCPValue = Collections.max(crudeProteinNutrient);
        ingredientWithMinCPValue = Collections.min(crudeProteinNutrient);




        // Step 1 of the calculation
        double newIngredientWithMaxCPValue = Math.abs(targetProtein - ingredientWithMinCPValue);
        double newIngredientWithMinCPValue = Math.abs(targetProtein - ingredientWithMaxCPValue);

        double TotalValue = newIngredientWithMaxCPValue + newIngredientWithMinCPValue;

        DecimalFormat decimalFormat = new DecimalFormat("0.00");


        // Step 2 of the calculation
        double getPercentForIngreWithMaxValue = Math.round(((newIngredientWithMaxCPValue/TotalValue) * 100) * 100.0) /100.0;
        double getPercentForIngreWithMinValue = Math.round(((newIngredientWithMinCPValue/TotalValue) * 100) * 100.0) /100.0;


        // store proportion percent
        newProportion.add(getPercentForIngreWithMaxValue);
        newProportion.add(getPercentForIngreWithMinValue);

        // Step 3 of the calculation
        double NewCPForIngreWithMinValue = getPercentForIngreWithMinValue * (ingredientWithMinCPValue/100);
        double NewCPForIngreWithMaxValue = getPercentForIngreWithMaxValue * (ingredientWithMaxCPValue/100);

        //get the quantity to mix
        if(!recalculate){
            qtyToMix = 0.00;
            qtyToMix = Double.parseDouble(getQtyToMix.getText().toString());
            recalculate = false;
        }

        // Step 4 of of the calculation
        double qtyOfMixMinValueCP = (qtyToMix/100) * getPercentForIngreWithMinValue;
        double qtyOfMixMaxValueCP = (qtyToMix/100) * getPercentForIngreWithMaxValue;

        getNewProportionUnit.add(qtyOfMixMaxValueCP);
        getNewProportionUnit.add(qtyOfMixMinValueCP);

                //calculating calcium level
        double newCalciumLevel = ((getPercentForIngreWithMaxValue * maxCal) + (getPercentForIngreWithMinValue * minCal)) * 0.01;
        double newPhosphprusLevel = ((getPercentForIngreWithMaxValue * maxPhos) + (getPercentForIngreWithMinValue* minPhos)) * 0.01;
        double newEnergyLevel = ((getPercentForIngreWithMaxValue * maxEnergy) + (getPercentForIngreWithMinValue* minEnergy)) * 0.01;
        double newCrudeProteinLevel = NewCPForIngreWithMinValue + NewCPForIngreWithMaxValue;

        newCal = newCalciumLevel;
        newPhos = newPhosphprusLevel;
        newCrude = newCrudeProteinLevel;
        newEnergy = newEnergyLevel;


        //find the kg to add
        Double ExtraMin = qtyOfMixMinValueCP - QtySelectedForMinCP;
        Double ExtraMax = qtyOfMixMaxValueCP - QtySelectedForMaxCP;

        qtyToAddGrpA = ExtraMin;
        qtyToAddGrpB = ExtraMax;
        scaledownA = qtyOfMixMinValueCP;
        scaledownB = qtyOfMixMaxValueCP;
        qtyAvailableGrpA = QtySelectedForMinCP;
        qtyAvailableGrpB = QtySelectedForMaxCP;

        return true;



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
