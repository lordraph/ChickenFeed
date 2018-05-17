package com.acgg.chickenfeed.summaryAdapter;

public class FormulatedDetails {
    private String ingredient;
    private String IngredientClass;
    private String commentFormulated;
    private Double crudeProteinFormulated;
    private Double calciumFormulated;
    private Double phosphorusFormulated;
    private Double proportion;
    private Double proportionUnit;
    private Integer qtySpecified;
    private Integer qtyAvailable;

    public FormulatedDetails(String ingredient, String ingredientClass, Double crudeProteinFormulated, Double calciumFormulated,
                             Double phosphorusFormulated, Integer qtySpecified,
                             String commentFormulated, Double proportion,
                             Double proportionUnit, Integer qtyAvailable)
    {
        this.ingredient = ingredient;
        this.IngredientClass = ingredientClass;
        this.crudeProteinFormulated = crudeProteinFormulated;
        this.calciumFormulated = calciumFormulated;
        this.phosphorusFormulated = phosphorusFormulated;
        this.qtySpecified = qtySpecified;
        this.commentFormulated = commentFormulated;
        this.proportion = proportion;
        this.proportionUnit = proportionUnit;
        this.qtyAvailable = qtyAvailable;

    }


    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getIngredientClass() {
        return IngredientClass;
    }

    public void setIngredientClass(String ingredientClass) {
        IngredientClass = ingredientClass;
    }

    public String getCommentFormulated() {
        return commentFormulated;
    }

    public void setCommentFormulated(String commentFormulated) {
        this.commentFormulated = commentFormulated;
    }

    public Double getCrudeProteinFormulated() {
        return crudeProteinFormulated;
    }

    public void setCrudeProteinFormulated(Double crudeProteinFormulated) {
        this.crudeProteinFormulated = crudeProteinFormulated;
    }

    public Double getCalciumFormulated() {
        return calciumFormulated;
    }

    public void setCalciumFormulated(Double calciumFormulated) {
        this.calciumFormulated = calciumFormulated;
    }

    public Double getPhosphorusFormulated() {
        return phosphorusFormulated;
    }

    public void setPhosphorusFormulated(Double phosphorusFormulated) {
        this.phosphorusFormulated = phosphorusFormulated;
    }

    public Double getProportion() {
        return proportion;
    }

    public void setProportion(Double proportion) {
        this.proportion = proportion;
    }

    public Double getProportionUnit() {
        return proportionUnit;
    }

    public void setProportionUnit(Double proportionUnit) {
        this.proportionUnit = proportionUnit;
    }

    public Integer getQtySpecified() {
        return qtySpecified;
    }

    public void setQtySpecified(Integer qtySpecified) {
        this.qtySpecified = qtySpecified;
    }

    public Integer getQtyAvailable() {
        return qtyAvailable;
    }

    public void setQtyAvailable(Integer qtyAvailable) {
        this.qtyAvailable = qtyAvailable;
    }
}
