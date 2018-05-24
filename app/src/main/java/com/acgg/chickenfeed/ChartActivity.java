package com.acgg.chickenfeed;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.acgg.chickenfeed.data.ChickenFeedHelper;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ChartActivity extends AppCompatActivity  {

    ChickenFeedHelper dbhelper;
    float ingredientValues[] = {10.9f, 20f, 17f, 9f, 6f};
    int formNo;
    String ingredient [] = {"Acha", "Maize", "Yam", "Cocoyam", "Beans"};
    ArrayList<Double> proportionPercent, proportionUnit;
    ArrayList<String> ingredientName;
    DecimalFormat decimalFormat;
    List<PieEntry> entriesproportionUnit, entriespropotionPercent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        dbhelper = new ChickenFeedHelper(this);
        decimalFormat = new DecimalFormat("#");

        //get formulation No
        Intent intent = getIntent();
        formNo = intent.getIntExtra("chartformNo", 0);

        getproprotionPercent();
        setupPieChart1();
        setupPieChart2();


    }

    public void getproprotionPercent(){
        proportionPercent = new ArrayList <>();
        proportionUnit = new ArrayList <>();
        ingredientName = new ArrayList <>();
        entriesproportionUnit = new ArrayList<>();
        entriespropotionPercent = new ArrayList <>();

        Cursor cursor = dbhelper.getFormulationResult(formNo);

        while(cursor.moveToNext()){
            entriespropotionPercent.add(new PieEntry((float)(cursor.getDouble(3)),cursor.getString(1)));
            entriesproportionUnit.add(new PieEntry((float)(cursor.getDouble(4)),cursor.getString(1)));
//            ingredientName.add(cursor.getString(1));
//            proportionPercent.add(cursor.getDouble(3));
//            proportionUnit.add(cursor.getDouble(4));
        }
    }

    public void setupPieChart1() {

        //populating a list of entries
//        List<PieEntry> entries = new ArrayList<>();
//        for (int i = 0; i < ingredientName.size(); i++){
//            entries.add(new PieEntry((float)(proportionPercent.get(i)), ingredientName.get(i)));
//        }

        PieDataSet dataSet = new PieDataSet(entriespropotionPercent, "Ingredient Mixed(%)");
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        PieData data = new PieData(dataSet);

        //Get the chart
        PieChart chart1 = findViewById(R.id.proportion_100kg_chart);
        chart1.setUsePercentValues(false);
        chart1.getDescription().setEnabled(false);
        Description description = new Description();
        description.setText("Proportion Scaled Down");
        description.setTextColor(348502);
        description.setTextSize(10);
        chart1.setDescription(description);
        chart1.setRotationEnabled(true);
        chart1.setHoleRadius(25f);
        chart1.setData(data);
        chart1.animateY(1000, Easing.EasingOption.EaseInOutCubic);
        chart1.invalidate();

    }
    public void setupPieChart2() {
        //populating a list of entries
//        List<PieEntry> entries = new ArrayList<>();
//        for (int i = 0; i < ingredientValues.length; i++){
//            entries.add(new PieEntry(ingredientValues[i], ingredient[i]));
//        }

        PieDataSet dataSet = new PieDataSet(entriesproportionUnit, "Ingredient Mixed(kg)");
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        PieData data = new PieData(dataSet);

        //Get the chart
        PieChart chart2 = findViewById(R.id.proportionKg_chart);
        chart2.setUsePercentValues(false);
        chart2.getDescription().setEnabled(false);
        Description description = new Description();
        description.setText("Proportion Scaled Down");
        description.setTextColor(348502);
        description.setTextSize(10);
        chart2.setDescription(description);
        chart2.setRotationEnabled(true);
        chart2.setHoleRadius(25f);
        chart2.setData(data);
        chart2.animateY(1000, Easing.EasingOption.EaseInOutCubic);
        chart2.invalidate();
    }

}
