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
        db.execSQL(ChickenFeedDbContract.CalculatedAnalysis.SQL_CREATE_CALCULATED_ANALYSIS_TABLE);


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
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_ORANGE_PULP);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_MELON_FRUIT_PULP);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_ORANGE_PEELINGS);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_PLANTAIN_PEELS);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_BITTER_LEAVES);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_YAM_FLOUR);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_PALM_OIL_SLUDGE);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_EVAPORATED_PALM_OIL_);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_MAIZE_STARCH_RESIDUE);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_AFRICAN_LOCUST);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_AFRICAN_LOCUST_BEAN_SEED);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_AFRICAN_LOCUST_BEAN_WHOLE_FRUIT);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_ALMOND_KERNEL);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_BAMBARA_GROUNDNUT_DE);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_BAMBARA_GROUNDNUT_UN);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_BREWER_YEAST);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_CASHEW_UN);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_CASHEW_EX);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_FULL_FAT);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_COTTON_SEED);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_GROUNDNUT_CAKE);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_GROUNDNUT_MEAL);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_PIGEON_PEA);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_ROSELLE);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_SESAME_SEED);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_SUNFLOWER);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_WATER_MELON_UN);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_WATER_MELON_SHELLED);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_BLOOD_MEAL);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_EARTHWORM);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_FEATHER);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_GARDEN_SNAIL);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_HOUSEFLY_MAGGOT);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_LOCAL_FISH_MEAL);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_TERMITE_MEAL);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_UNSKINNED_DRIED_TADPOLE);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_UNSKINNED_BOILED_TADPOLE);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_BREWER_WET_GRAIN );
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_BREWER_DRY_GRAIN);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_CORN_COBS);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_COTTON_SEED_HULLS);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_PALM_KERNEL_MEAL);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_POULTRY_MANURE_DRIED_CAGE);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_POULTRY_MANURE_DRIED_FLOOR);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_OKRA_DRIED_FRUIT);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_RICE_HULLS);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_RICE_BRAN);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_RICE_GRAIN_WITH_HULLS);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_RICE_GRAIN_HULL_REMOVED);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_RICE_OFFAL_UN);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_RICE_OFFAL_PA);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_SORGHUM_OFFAL);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_TOMATO_PULP);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_WHEAT_BRAN);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_WHEAT_OFFAL);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_OKRA);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_BONE_MEAL);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_DICALCIUM_PHOSPHATE);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_LIMESTONE);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_OYSTER_SHELL);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_PERIWINKLE);
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_INSERT_EGG_SHELL_MEAL);


// Inser initiate records
        db.execSQL(ChickenFeedDbContract.IngredientRecord.SQL_INSERT_RECORD);

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ChickenFeedDbContract.FeedLibraryEntry.SQL_DELETE_ENTRIES);
        onCreate(db);

    }
//       insert id, feed_ingredient,class,crude_protein,energy,calcium,phosphorus

    public Cursor getTable(){
        SQLiteDatabase db = this.getReadableDatabase();
        //        Query statement to query the database
        String selectQuery = "SELECT feed_ingredient FROM " + ChickenFeedDbContract.FeedLibraryEntry.TABLE_NAME;
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
                                        Double proportionUnit, Integer qtySpecified, String comment, Double crudeProtein,
                                        Double calcium, Double phosphorus, String ingredientClass
                                        ) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(ChickenFeedDbContract.ResultFormulatedRecord.COLUMN_FEED_INGREDIENT, feed_ingredient);
        contentValues.put(ChickenFeedDbContract.ResultFormulatedRecord.COLUMN_FORMULATION_NO, formu_no);
        contentValues.put(ChickenFeedDbContract.ResultFormulatedRecord.COLUMN_PROPORTION_PERCENT, proportionPercent);
        contentValues.put(ChickenFeedDbContract.ResultFormulatedRecord.COLUMN_PROPORTION_UNIT, proportionUnit);
        contentValues.put(ChickenFeedDbContract.ResultFormulatedRecord.COLUMN_QUANTITY_SPECIFIED, qtySpecified);
        contentValues.put(ChickenFeedDbContract.ResultFormulatedRecord.COLUMN_COMMENT, comment);
        contentValues.put(ChickenFeedDbContract.ResultFormulatedRecord.COLUMN_CRUDE_PROTEIN, crudeProtein);
        contentValues.put(ChickenFeedDbContract.ResultFormulatedRecord.COLUMN_CALCIUM, calcium);
        contentValues.put(ChickenFeedDbContract.ResultFormulatedRecord.COLUMN_PHOSPHORUS, phosphorus);
        contentValues.put(ChickenFeedDbContract.ResultFormulatedRecord.COLUMN_CLASS, ingredientClass);

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

    // public static final String SQL_CREATE_CALCULATED_ANALYSIS_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
    //                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
    //                COLUMN_CRUDE_PROTEIN + " REAL, " +
    //                COLUMN_FORMULATION_NO + " INTEGER, " +
    //                COLUMN_CALCIUM + " REAL, " +
    //                COLUMN_PHOSPHORUS + " REAL, " +
    //                COLUMN_ENERGY + " INTEGER"
    //                +");";
    //    }

    public boolean addCalculatedAnalysis(Double crudeProtein, Integer formu_no, Double calcium, Double phosphorus,
                                         Double energy){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(ChickenFeedDbContract.CalculatedAnalysis.COLUMN_CRUDE_PROTEIN,crudeProtein);
        contentValues.put(ChickenFeedDbContract.CalculatedAnalysis.COLUMN_FORMULATION_NO,formu_no);
        contentValues.put(ChickenFeedDbContract.CalculatedAnalysis.COLUMN_CALCIUM,calcium);
        contentValues.put(ChickenFeedDbContract.CalculatedAnalysis.COLUMN_PHOSPHORUS,phosphorus);
        contentValues.put(ChickenFeedDbContract.CalculatedAnalysis.COLUMN_ENERGY,energy);


        long checkifdata = db.insert(ChickenFeedDbContract.CalculatedAnalysis.TABLE_NAME,null,contentValues);
        db.close();

        if(checkifdata==-1){
            return false;
        }else{
            return true;
        }

    }

    public Cursor getCalculatedAnalysis(Integer formu){

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + ChickenFeedDbContract.CalculatedAnalysis.TABLE_NAME +
                " WHERE " + ChickenFeedDbContract.CalculatedAnalysis.COLUMN_FORMULATION_NO + "=?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(formu)});

        return cursor;
    }

}
