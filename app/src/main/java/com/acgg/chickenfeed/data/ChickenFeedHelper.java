package com.acgg.chickenfeed.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ChickenFeedHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ChickenFeed.db";



    public ChickenFeedHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
//        Create feed library table
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_CREATE_BUGS_TABLE);
        db.execSQL(ChickenFeedDbContract.IngredientRecord.SQL_CREATE_INGREDIENT_TABLE);
        db.execSQL(ChickenFeedDbContract.FormulatedRecord.SQL_CREATE_FORMULATED_RECORD_TABLE);
        db.execSQL(ChickenFeedDbContract.ResultFormulatedRecord.SQL_CREATE_FORMULATED_RECORD_TABLE);


//        insert feed ingredient into table created
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_ACHA);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_CASSAVA_PEEL_MEAL);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_CASSAVA_FLOUR);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_CASSAVA_LEAF_MEAL);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_CASSAVA_GRIT);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_CASSAVA_STARCH);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_COCOYAM);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_SORGHUM_WHITE);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_SORGHUM_RED);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_MAIZE_WHITE);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_MAIZE_YELLOW);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_MILLET);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_PALMOIL);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_RICE_GRAIN_ROUGH);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_RICE_GRAIN_POLISH_BROKEN);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_RICE_GRAIN_POLISH_PARBOILED);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_REFINED_SOYA_OIL);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_RAW_SOYA_OIL);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_SWEET_POTATO_UNPEELED);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_SWEET_POTATO_PEELED);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_WHEAT_HARD_GRAIN);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_WHEAT_SOFT_GRAIN);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_WATER_YAM_UNPEELED);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_WATER_YAM_PEELED);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_YELLOW_YAM_UNPEELED);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_YELLOW_YAM_PEELED);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_YELLOW_YAM_PEELED_ONLY);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_RIPED_PAWPAW_SEED);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_WATER_LEAF_SEEDS);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_RIPED_PAWPAW_FRUIT_PEELED);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_RIPED_PLANTAIN_FRUIT);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_TOASTED_GROUNDNUT);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_SOYBEAN);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_COWPEA);

// Inser initiate records
        db.execSQL(ChickenFeedDbContract.IngredientRecord.SQL_INSERT_RECORD);

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_DELETE_ENTRIES);
        onCreate(db);

    }
//       insert id, feed_ingridient,class,crude_protein,energy,calcium,phosphorus

    public Cursor getTable(){
        SQLiteDatabase db = this.getReadableDatabase();
        //        Query statement to query the database
        String selectQuery = "SELECT feed_ingridient FROM " + ChickenFeedDbContract.FeedLibraryEntry.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery,null);
        return cursor;
    }

    public Cursor getFeedNutrients(){
        SQLiteDatabase db = this.getReadableDatabase();
        //        Query statement to query the database
        String selectQuery = "SELECT * FROM " + ChickenFeedDbContract.FeedLibraryEntry.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery,null);
        return cursor;
    }

    public Cursor getFormuRecord(){

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + ChickenFeedDbContract.IngredientRecord.TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }


   public boolean updateFomulatedRecord(Integer formu_no){

       SQLiteDatabase db = this.getWritableDatabase();

       ContentValues contentValues = new ContentValues();
       contentValues.put(ChickenFeedDbContract.IngredientRecord.COLUMN_FORMULATION,formu_no);

       long checkifdata = db.update(ChickenFeedDbContract.IngredientRecord.TABLE_NAME,contentValues,null,null);

       db.close();

       if(checkifdata==-1){
           return false;
       }else{
           return true;
       }

   }

//   add formulated ingredient to database

    public boolean addIngredient(String ingredient, Integer formu_no, String birdcat, Integer qtyformulateed, String classOfIngredient, Double calcium,
                                    Double crude_protein, Double Phosphorus, Integer energy){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(ChickenFeedDbContract.FormulatedRecord.COLUMN_FEED_INGREDIENT,ingredient);
        contentValues.put(ChickenFeedDbContract.FormulatedRecord.COLUMN_FORMULATION_NO,formu_no);
        contentValues.put(ChickenFeedDbContract.FormulatedRecord.COLUMN_BIRD_CATEGORY,birdcat);
        contentValues.put(ChickenFeedDbContract.FormulatedRecord.COLUMN_QUANTITY_FORMULATED,qtyformulateed);
        contentValues.put(ChickenFeedDbContract.FormulatedRecord.COLUMN_CLASS,classOfIngredient);
        contentValues.put(ChickenFeedDbContract.FormulatedRecord.COLUMN_CRUDE_PROTEIN,crude_protein);
        contentValues.put(ChickenFeedDbContract.FormulatedRecord.COLUMN_ENERGY,energy);
        contentValues.put(ChickenFeedDbContract.FormulatedRecord.COLUMN_CALCIUM,calcium);
        contentValues.put(ChickenFeedDbContract.FormulatedRecord.COLUMN_PHOSPHORUS,Phosphorus);


        long checkifdata = db.insert(ChickenFeedDbContract.FormulatedRecord.TABLE_NAME,null,contentValues);
        db.close();

        if(checkifdata==-1){
            return false;
        }else{
            return true;
        }

    }

    public Cursor getIngredient(Integer formu){

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + ChickenFeedDbContract.FormulatedRecord.TABLE_NAME +
                " WHERE " + ChickenFeedDbContract.FormulatedRecord.COLUMN_FORMULATION_NO + "=?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(formu)});

        return cursor;
    }



//    add result formulated to database

    public boolean addFormulationResult(String feed_ingredient, Integer formu_no, Double proportionPercent,
                                 Double proportionUnit, Integer qtySpecified, String comment) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(ChickenFeedDbContract.ResultFormulatedRecord.COLUMN_FEED_INGREDIENT, feed_ingredient);
        contentValues.put(ChickenFeedDbContract.ResultFormulatedRecord.COLUMN_FORMULATION_NO, formu_no);
        contentValues.put(ChickenFeedDbContract.ResultFormulatedRecord.COLUMN_PROPORTION_PERCENT, proportionPercent);
        contentValues.put(ChickenFeedDbContract.ResultFormulatedRecord.COLUMN_PROPORTION_UNIT, proportionUnit);
        contentValues.put(ChickenFeedDbContract.ResultFormulatedRecord.COLUMN_QUANTITY_SPECIFIED, qtySpecified);
        contentValues.put(ChickenFeedDbContract.ResultFormulatedRecord.COLUMN_COMMENT, comment);

        long checkifdata = db.insert(ChickenFeedDbContract.ResultFormulatedRecord.TABLE_NAME, null, contentValues);

        db.close();

        if (checkifdata == -1) {
            return false;
        } else {
            return true;
        }
    }

    //    add result formulated to database

    public Cursor getFormulationResult(Integer formu) {

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + ChickenFeedDbContract.ResultFormulatedRecord.TABLE_NAME +
                " WHERE " + ChickenFeedDbContract.ResultFormulatedRecord.COLUMN_FORMULATION_NO + "=?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(formu)});

        return cursor;
    }

}
