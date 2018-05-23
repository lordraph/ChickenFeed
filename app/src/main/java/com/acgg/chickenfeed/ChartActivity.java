package com.acgg.chickenfeed;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class ChartActivity extends AppCompatActivity  {


    float ingredientValues[] = {10.9f, 20f, 17f, 9f, 6f};
    String ingredient [] = {"Acha", "Maize", "Yam", "Cocoyam", "Beans"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        setupPieChart1();
        setupPieChart2();


    }
    public void setupPieChart1() {
        //populating a list of entries
        List<PieEntry> entries = new ArrayList<>();
        for (int i = 0; i < ingredientValues.length; i++){
            entries.add(new PieEntry(ingredientValues[i], ingredient[i]));
        }

        PieDataSet dataSet = new PieDataSet(entries, "Ingredient Mixed");
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        PieData data = new PieData(dataSet);

        //Get the chart
        PieChart chart1 = findViewById(R.id.proportion_100kg_chart);
        chart1.setUsePercentValues(true);
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
        List<PieEntry> entries = new ArrayList<>();
        for (int i = 0; i < ingredientValues.length; i++){
            entries.add(new PieEntry(ingredientValues[i], ingredient[i]));
        }

        PieDataSet dataSet = new PieDataSet(entries, "Ingredient Mixed");
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        PieData data = new PieData(dataSet);

        //Get the chart
        PieChart chart2 = findViewById(R.id.proportionKg_chart);
        chart2.setUsePercentValues(true);
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
