package com.acgg.chickenfeed.summaryAdapter;

public class CalculatedAnalysis {
    private Double crudeProtein;
    private Double calcium;
    private Double phosphorus;
    private Double Energy;

    public CalculatedAnalysis(Double crudeProtein, Double calcium, Double phosphorus, Double energy) {
        this.crudeProtein = crudeProtein;
        this.calcium = calcium;
        this.phosphorus = phosphorus;
        Energy = energy;
    }

    public Double getCrudeProtein() {
        return crudeProtein;
    }

    public void setCrudeProtein(Double crudeProtein) {
        this.crudeProtein = crudeProtein;
    }

    public Double getCalcium() {
        return calcium;
    }

    public void setCalcium(Double calcium) {
        this.calcium = calcium;
    }

    public Double getPhosphorus() {
        return phosphorus;
    }

    public void setPhosphorus(Double phosphorus) {
        this.phosphorus = phosphorus;
    }

    public Double getEnergy() {
        return Energy;
    }

    public void setEnergy(Double energy) {
        Energy = energy;
    }
}
