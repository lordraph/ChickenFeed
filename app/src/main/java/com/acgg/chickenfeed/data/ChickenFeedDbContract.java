package com.acgg.chickenfeed.data;

import android.provider.BaseColumns;

/**
 * Created by Prof Ogunjuyigbe on 5/8/2018.
 */

public class ChickenFeedDbContract {

    private ChickenFeedDbContract(){}

    public static final class FeedLibraryEntry implements BaseColumns{
        public static final String TABLE_NAME = "feed_library";
        private static final String COLUMN_FEED_INGRIDENT = "feed_ingridient";
        private static final String COLUMN_CLASS = "class";
        private static final String COLUMN_ENERGY = "energy";
        private static final String COLUMN_CALCIUM = "calcium";
        private static final String COLUMN_PHOSPHORUS = "phosphorus";
        private static final String COLUMN_CRUDE_PROTEIN = "crude_protein";

        public static final String SQL_CREATE_BUGS_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_FEED_INGRIDENT + " TEXT, " +
                COLUMN_CLASS + " TEXT, " +
                COLUMN_CRUDE_PROTEIN + " REAL, " +
                COLUMN_ENERGY + " INTEGER, " +
                COLUMN_CALCIUM + " REAL, " +
                COLUMN_PHOSPHORUS + " REAL" +");";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;

//       insert feed_ingridient,class,crude_protein,energy,calcium,phosphorus
        public static final String SQL_INSERT_ACHA = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingridient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Acha (grain)','Energy',9.71,3511,0.28,0.13)";

        public static final String SQL_INSERT_CASSAVA_PEEL_MEAL = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingridient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Cassava peel meal','Energy',5.33, 2807,0.65,0.25)";

        public static final String SQL_INSERT_CASSAVA_FLOUR = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingridient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Cassava flour','Energy',3.10,3090,0.16,0.37)";

        public static final String SQL_INSERT_CASSAVA_LEAF_MEAL = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingridient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Cassava leaf meal','Energy',23.79,2856,1.24,0.6)";

        public static final String SQL_INSERT_CASSAVA_GRIT = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingridient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Cassava grit(Gaari)','Energy',1.20,3367,0.2,0.03)";

        public static final String SQL_INSERT_CASSAVA_STARCH = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingridient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Cassava Starch','Energy',2.00,2693,0.00,0.01)";

        public static final String SQL_INSERT_COCOYAM = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingridient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Cocoyam, tuber','Energy',6.3,3604,0.00,0.00)";

        public static final String SQL_INSERT_SORGHUM_WHITE = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingridient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Sorghum (Guinea corn), white','Energy',8.00,3300,0.04,0.32)";

        public static final String SQL_INSERT_SORGHUM_RED = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingridient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Sorghum (Guinea corn), red','Energy',8.00,3300,0.04,0.32)";

        public static final String SQL_INSERT_MAIZE_WHITE = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingridient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Maize, white','Energy',7.87,3245,0.02,0.27)";

        public static final String SQL_INSERT_MAIZE_YELLOW = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingridient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Maize, yellow','Energy',8.80,3333,0.22,0.27)";

        public static final String SQL_INSERT_MILLET = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingridient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Millet','Energy',12.00,3440,0.05,0.30)";

        public static final String SQL_INSERT_PALMOIL = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingridient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Palm Oil','Energy',0.00,7710,6.0,0.00)";

        public static final String SQL_INSERT_RICE_GRAIN_ROUGH = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingridient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Rice grain, rough','Energy',7.30,2940,0.04,0.26)";

        public static final String SQL_INSERT_RICE_GRAIN_POLISH_BROKEN = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingridient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Rice grain, polished, broken','Energy',8.50,3472,0.32,0.34)";

        public static final String SQL_INSERT_RICE_GRAIN_POLISH_PARBOILED = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingridient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Rice grain, polished, parboiled','Energy',7.2,3482,0.13,0.16)";

        public static final String SQL_INSERT_REFINED_SOYA_OIL = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingridient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Refined soya oil','Energy',0.00,7800,0.00,0.00)";

        public static final String SQL_INSERT_RAW_SOYA_OIL = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingridient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Raw Soya oil','Energy',0.00,8370,0.00,0.00)";

        public static final String SQL_INSERT_SWEET_POTATO_UNPEELED = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingridient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Sweet potato tuber (unpeeled)','Energy',2.76,0.60,0.02,0.03)";

        public static final String SQL_INSERT_SWEET_POTATO_PEELED = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingridient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Sweet potato tuber (peeled)','Energy',5.8,3596,0.00,0.00)";

        public static final String SQL_INSERT_WHEAT_HARD_GRAIN = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingridient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Wheat ,hard, grain','Energy',13.50,3035,0.05,0.41)";

        public static final String SQL_INSERT_WHEAT_SOFT_GRAIN = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingridient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Wheat, soft, grain','Energy',11.80,3190,0.05,0.03)";

        public static final String SQL_INSERT_WATER_YAM_UNPEELED = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingridient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Water Yam, (unpeeled)','Energy',8.2,3248,0.00,0.00)";

        public static final String SQL_INSERT_WATER_YAM_PEELED = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingridient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Water Yam (peels only)','Energy',11.73,3009,0.38,0.1)";

        public static final String SQL_INSERT_YELLOW_YAM_UNPEELED = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingridient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Yellow Yam (unpeeled)','Energy',6.3,3315,0,0)";

        public static final String SQL_INSERT_YELLOW_YAM_PEELED = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingridient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Yellow Yam (peeled)','Energy',5.44,3387,5.19,0.13)";

        public static final String SQL_INSERT_YELLOW_YAM_PEELED_ONLY = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingridient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Yellow Yam (peels only)','Energy',7.42,3017,1.36,0.13)";

        public static final String SQL_INSERT_RIPED_PAWPAW_SEED = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingridient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Ripe pawpaw seeds','Energy',27.4,1308,0.59,0.26)";

        public static final String SQL_INSERT_WATER_LEAF_SEEDS = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingridient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Water leaf seeds','Energy',18.6,1367,0,0)";

        public static final String SQL_INSERT_RIPED_PAWPAW_FRUIT_PEELED = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingridient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Ripe pawpaw fruit (peeled)','Energy',6.55,3396,0.16,0.07)";

        public static final String SQL_INSERT_RIPED_PLANTAIN_FRUIT = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingridient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Ripe plantain fruit','Energy',4.12,1005,0.10,0.34)";

        public static final String SQL_INSERT_TOASTED_GROUNDNUT = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingridient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Toasted groundnut testa','Energy',18.98,912,0,0)";

        public static final String SQL_INSERT_SOYBEAN = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingridient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Soybean testa','Energy',16.63,2096,0.84,0.78)";

        public static final String SQL_INSERT_COWPEA = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingridient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Cowpea testa','Energy',16.97,1005,2.06,0.31)";


    }

    public static final class IngredientRecord implements BaseColumns{
        public static final String TABLE_NAME = "ingredient_lib";
        public static final String COLUMN_FORMULATION = "formulation_no";
        public static final String SQL_CREATE_INGREDIENT_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_FORMULATION + " INTEGER" +");";

        public static final String SQL_INSERT_RECORD = "INSERT INTO " + TABLE_NAME + " " +
                "(formulation_no) VALUES " +
                "(0)";

    }


    public static final class FormulatedRecord implements BaseColumns{
        public static final String TABLE_NAME = "formulatedRecord_lib";
        public static final String COLUMN_FORMULATION_NO = "formulation_no";
        public static final String COLUMN_FEED_INGREDIENT = "feed_ingridient";
        public static final String COLUMN_CLASS = "class";
        public static final String COLUMN_ENERGY = "energy";
        public static final String COLUMN_CALCIUM = "calcium";
        public static final String COLUMN_PHOSPHORUS = "phosphorus";
        public static final String COLUMN_CRUDE_PROTEIN = "crude_protein";
        public static final String COLUMN_BIRD_CATEGORY = "bird_category";
        public static final String COLUMN_QUANTITY_FORMULATED = "quantity_formulated";

        public static final String SQL_CREATE_FORMULATED_RECORD_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_FEED_INGREDIENT + " TEXT, " +
                COLUMN_FORMULATION_NO + " INTEGER, " +
                COLUMN_BIRD_CATEGORY + " TEXT, " +
                COLUMN_QUANTITY_FORMULATED + " INTEGER, " +
                COLUMN_CLASS + " TEXT, " +
                COLUMN_CRUDE_PROTEIN + " REAL, " +
                COLUMN_ENERGY + " INTEGER, " +
                COLUMN_CALCIUM + " REAL, " +
                COLUMN_PHOSPHORUS + " REAL" +");";

    }


    public static final class ResultFormulatedRecord implements BaseColumns{
        public static final String TABLE_NAME = "resultFormulatedRecord_lib";
        public static final String COLUMN_FORMULATION_NO = "formulation_no";
        public static final String COLUMN_FEED_INGREDIENT = "feed_ingridient";
        public static final String COLUMN_PROPORTION_PERCENT= "proportion_percent";
        public static final String COLUMN_PROPORTION_UNIT = "proportion_unit";
        public static final String COLUMN_QUANTITY_SPECIFIED= "quantity_specified";
        public static final String COLUMN_COMMENT= "comment";

        public static final String SQL_CREATE_FORMULATED_RECORD_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_FEED_INGREDIENT + " TEXT, " +
                COLUMN_FORMULATION_NO + " INTEGER, " +
                COLUMN_PROPORTION_PERCENT + " REAL, " +
                COLUMN_PROPORTION_UNIT + " REAL, " +
                COLUMN_QUANTITY_SPECIFIED + " INTEGER, " +
                COLUMN_COMMENT + " TEXT" +");";
    }


}
