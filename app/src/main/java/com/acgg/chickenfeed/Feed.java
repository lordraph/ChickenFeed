package com.acgg.chickenfeed;

/**
 * Created by Prof Ogunjuyigbe on 5/9/2018.
 */

public class Feed {
    private String name;
    private double dry_matter;
    private double crude_protein;
    private double ether_extract;
    private double crude_fibre;
    private double nitrogen;
    private double total_ash;
    private double me;
    private double calcium;
    private double phosphorus;
    private double lysine;
    private double methionine;
    private String group;

    public Feed(String name, double dry_matter, double crude_protein, double ether_extract, double crude_fibre, double nitrogen, double total_ash,
               double me, double calcium, double phosphorus, double lysine, double methionine, String group) {
        this.name = name;
        this.dry_matter = dry_matter;
        this.crude_protein = crude_protein;
        this.ether_extract = ether_extract;
        this.crude_fibre = crude_fibre;
        this.nitrogen = nitrogen;
        this.total_ash = total_ash;
        this.me = me;
        this.calcium = calcium;
        this.phosphorus = phosphorus;
        this.lysine = lysine;
        this.methionine = methionine;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getdry_matter() {
        return dry_matter;
    }

    public void setdry_matter(double dry_matter) {
        this.dry_matter = dry_matter;
    }

    public double getether_extract() {
        return ether_extract;
    }

    public void setether_extract(double ether_extract) {
        this.ether_extract = ether_extract;
    }
    public double getcrude_fibre() {
        return crude_fibre;
    }

    public void setcrude_fibre(double crude_fibre) {
        this.crude_fibre = crude_fibre;
    }
    public double getnitrogen() {
        return nitrogen;
    }

    public void setnitrogen(double nitrogen) {
        this.nitrogen = nitrogen;
    }
    public double gettotal_ash() {
        return total_ash;
    }

    public void settotal_ash(double total_ash) {
        this.total_ash = total_ash;
    }
    public double getme() {
        return me;
    }

    public void setme(double me) {
        this.me = me;
    }
    public double getcalcium() {
        return calcium;
    }

    public void setcalcium(double calcium) {
        this.calcium = calcium;
    }
    public double getphosphorus() {
        return phosphorus;
    }

    public void setphosphorus(double phosphorus) {
        this.phosphorus = phosphorus;

    }
    public double getlysine() {
        return lysine;
    }

    public void setlysine(double lysine) {
        this.lysine = lysine;

    }
    public double getmethionine() {
        return methionine;
    }

    public void setmethionine(double methionine) {
        this.methionine = methionine;

    }
    public double getcrude_protein() {
        return crude_protein;
    }

    public void setcrude_protein(double crude_protein) {
        this.crude_protein = crude_protein;
    }

    public String getgroup() {
        return group;
    }

    public void setgroup(String group) {
        this.group = group;
    }

}
