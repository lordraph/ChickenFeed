package com.acgg.chickenfeed.data;

import android.provider.BaseColumns;

/**
 * Created by Tolulope Ogunjuyigbe on 5/8/2018.
 */

public class ChickenFeedDbContract {

    private ChickenFeedDbContract(){}

    public static final class FeedLibraryEntry implements BaseColumns{
        public static final String TABLE_NAME = "feed_library";
        private static final String COLUMN_FEED_INGREDIENT = "feed_ingredient";
        private static final String COLUMN_CLASS = "class";
        private static final String COLUMN_ENERGY = "energy";
        private static final String COLUMN_CALCIUM = "calcium";
        private static final String COLUMN_PHOSPHORUS = "phosphorus";
        private static final String COLUMN_CRUDE_PROTEIN = "crude_protein";

        public static final String SQL_CREATE_BUGS_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_FEED_INGREDIENT + " TEXT, " +
                COLUMN_CLASS + " TEXT, " +
                COLUMN_CRUDE_PROTEIN + " REAL, " +
                COLUMN_ENERGY + " INTEGER, " +
                COLUMN_CALCIUM + " REAL, " +
                COLUMN_PHOSPHORUS + " REAL" +");";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;

        //       insert feed_ingredient,class,crude_protein,energy,calcium,phosphorus
        public static final String SQL_INSERT_ACHA = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Acha (grain)','Energy',9.71,3511,0.28,0.13)";

        public static final String SQL_INSERT_CASSAVA_PEEL_MEAL = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Cassava peel meal','Energy',5.33, 2807,0.65,0.25)";

        public static final String SQL_INSERT_CASSAVA_FLOUR = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Cassava flour','Energy',3.10,3090,0.16,0.37)";

        public static final String SQL_INSERT_CASSAVA_LEAF_MEAL = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Cassava leaf meal','Energy',23.79,2856,1.24,0.6)";

        public static final String SQL_INSERT_CASSAVA_GRIT = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Cassava grit(Gaari)','Energy',1.20,3367,0.2,0.03)";

        public static final String SQL_INSERT_CASSAVA_STARCH = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Cassava Starch','Energy',2.00,2693,0.00,0.01)";

        public static final String SQL_INSERT_COCOYAM = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Cocoyam, tuber','Energy',6.3,3604,0.00,0.00)";

        public static final String SQL_INSERT_SORGHUM_WHITE = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Sorghum (Guinea corn), white','Energy',8.00,3300,0.04,0.32)";

        public static final String SQL_INSERT_SORGHUM_RED = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Sorghum (Guinea corn), red','Energy',8.00,3300,0.04,0.32)";

        public static final String SQL_INSERT_MAIZE_WHITE = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Maize, white','Energy',7.87,3245,0.02,0.27)";

        public static final String SQL_INSERT_MAIZE_YELLOW = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Maize, yellow','Energy',8.80,3333,0.22,0.27)";

        public static final String SQL_INSERT_MILLET = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Millet','Energy',12.00,3440,0.05,0.30)";

        public static final String SQL_INSERT_PALMOIL = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Palm Oil','Energy',0.00,7710,6.0,0.00)";

        public static final String SQL_INSERT_RICE_GRAIN_ROUGH = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Rice grain, rough','Energy',7.30,2940,0.04,0.26)";

        public static final String SQL_INSERT_RICE_GRAIN_POLISH_BROKEN = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Rice grain, polished, broken','Energy',8.50,3472,0.32,0.34)";

        public static final String SQL_INSERT_RICE_GRAIN_POLISH_PARBOILED = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Rice grain, polished, parboiled','Energy',7.2,3482,0.13,0.16)";

        public static final String SQL_INSERT_REFINED_SOYA_OIL = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Refined soya oil','Energy',0.00,7800,0.00,0.00)";

        public static final String SQL_INSERT_RAW_SOYA_OIL = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Raw Soya oil','Energy',0.00,8370,0.00,0.00)";

        public static final String SQL_INSERT_SWEET_POTATO_UNPEELED = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Sweet potato tuber (unpeeled)','Energy',2.76,0.60,0.02,0.03)";

        public static final String SQL_INSERT_SWEET_POTATO_PEELED = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Sweet potato tuber (peeled)','Energy',5.8,3596,0.00,0.00)";

        public static final String SQL_INSERT_WHEAT_HARD_GRAIN = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Wheat ,hard, grain','Energy',13.50,3035,0.05,0.41)";

        public static final String SQL_INSERT_WHEAT_SOFT_GRAIN = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Wheat, soft, grain','Energy',11.80,3190,0.05,0.03)";

        public static final String SQL_INSERT_WATER_YAM_UNPEELED = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Water Yam, (unpeeled)','Energy',8.2,3248,0.00,0.00)";

        public static final String SQL_INSERT_WATER_YAM_PEELED = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Water Yam (peels only)','Energy',11.73,3009,0.38,0.1)";

        public static final String SQL_INSERT_YELLOW_YAM_UNPEELED = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Yellow Yam (unpeeled)','Energy',6.3,3315,0,0)";

        public static final String SQL_INSERT_YELLOW_YAM_PEELED = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Yellow Yam (peeled)','Energy',5.44,3387,5.19,0.13)";

        public static final String SQL_INSERT_YELLOW_YAM_PEELED_ONLY = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Yellow Yam (peels only)','Energy',7.42,3017,1.36,0.13)";

        public static final String SQL_INSERT_RIPED_PAWPAW_SEED = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Ripe pawpaw seeds','Energy',27.4,1308,0.59,0.26)";

        public static final String SQL_INSERT_WATER_LEAF_SEEDS = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Water leaf seeds','Energy',18.6,1367,0,0)";

        public static final String SQL_INSERT_RIPED_PAWPAW_FRUIT_PEELED = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Ripe pawpaw fruit (peeled)','Energy',6.55,3396,0.16,0.07)";

        public static final String SQL_INSERT_RIPED_PLANTAIN_FRUIT = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Ripe plantain fruit','Energy',4.12,1005,0.10,0.34)";

        public static final String SQL_INSERT_TOASTED_GROUNDNUT = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Toasted groundnut testa','Energy',18.98,912,0,0)";

        public static final String SQL_INSERT_SOYBEAN = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Soybean testa','Energy',16.63,2096,0.84,0.78)";

        public static final String SQL_INSERT_COWPEA = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Cowpea testa','Energy',16.97,1005,2.06,0.31)";

        public static final String SQL_INSERT_ORANGE_PULP = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Orange pulp','Energy',0,1049,0,0)";

        public static final String SQL_INSERT_MELON_FRUIT_PULP = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Melon fruit pulp','Energy',8.48,1148,0,0)";

        public static final String SQL_INSERT_ORANGE_PEELINGS = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Orange peelings','Energy',6.80,0,0,0)";

        public static final String SQL_INSERT_PLANTAIN_PEELS = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Plantain peels','Energy',14.03,1367,0.10,0.34)";

        public static final String SQL_INSERT_BITTER_LEAVES = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Bitter leaves','Energy',22.80,2947,0,0)";

        public static final String SQL_INSERT_YAM_FLOUR = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Yam flour sieviates','Energy',3.53,2115,0,0)";

        public static final String SQL_INSERT_PALM_OIL_SLUDGE = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Palm oil sludge','Energy',9.00,5680,0,0)";

        public static final String SQL_INSERT_EVAPORATED_PALM_OIL_ = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Evaporated palm oil sludge','Energy',4.30,4900,0,0)";

        public static final String SQL_INSERT_MAIZE_STARCH_RESIDUE = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Maize starch residue (Eeri, Yoruba?)','Energy',14.70,3305,0,0)";

        public static final String SQL_INSERT_AFRICAN_LOCUST = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('African locust bean seed','Plant',30.38,2370,0.37,0.28)";

        public static final String SQL_INSERT_AFRICAN_LOCUST_BEAN_SEED = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('African locust bean seed and pod powder material','Plant',19.28,2561,0.28,0.28)";

        public static final String SQL_INSERT_AFRICAN_LOCUST_BEAN_WHOLE_FRUIT = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('African locust bean whole fruit','Plant',12.71,2202,0.40,0.28)";

        public static final String SQL_INSERT_ALMOND_KERNEL = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Almond kernel','Plant',29.65,4598,0.50,0.68)";

        public static final String SQL_INSERT_BAMBARA_GROUNDNUT_DE = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Bambara groundnut (decorticated)','Plant',21.14,2661,0.90,0.76)";

        public static final String SQL_INSERT_BAMBARA_GROUNDNUT_UN = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Bambara groundnut (undecorticated)','Plant',17.58,2574,0.90,0.76)";

        public static final String SQL_INSERT_BREWER_YEAST = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Brewer’s Yeast','Plant',43.10,3049,0.13,1.56)";

        public static final String SQL_INSERT_CASHEW_UN = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Cashew nut (unextracted)','Plant',21.18,5494,0.03,0.88)";

        public static final String SQL_INSERT_CASHEW_EX = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Cashew nut (extracted)','Plant',36.04,3419,0.06,1.64)";

        public static final String SQL_INSERT_FULL_FAT = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Full fat soya roasted/toasted','Plant',37,3100,0.20,0.50)";

        public static final String SQL_INSERT_COTTON_SEED = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Cotton seed, whole seeds with lint','Plant',23.00,19.0,0.19,0.16)";

        public static final String SQL_INSERT_GROUNDNUT_CAKE = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Groundnut cake','Plant',40,2640,0.20,0.20)";

        public static final String SQL_INSERT_GROUNDNUT_MEAL = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Groundnut meal','Plant',42.70,2550,0.20,0.20)";

        public static final String SQL_INSERT_PIGEON_PEA = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Pigeon pea','Plant',23.77,3064,1.29,2.80)";

        public static final String SQL_INSERT_ROSELLE = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Roselle (Zobo) seed  ','Plant',29.0,2500,0.45,0.20)";

        public static final String SQL_INSERT_SESAME_SEED = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Sesame seed','Plant',42.00,2255,2.00,1.30)";

        public static final String SQL_INSERT_SUNFLOWER = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Sunflower seed','Plant',41.0,2310,0.43,1.00)";

        public static final String SQL_INSERT_WATER_MELON_UN = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Water melon seed (unshelled)','Plant',24.36,1086,0.07,0.31)";

        public static final String SQL_INSERT_WATER_MELON_SHELLED = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Water melon seed (shelled)','Plant',34.48,3147,0.07,0.31)";

        public static final String SQL_INSERT_BLOOD_MEAL = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Blood meal','Animal',80.00,3220,0.28,0.22)";

        public static final String SQL_INSERT_EARTHWORM = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Earthworm (dried)','Animal',63,0,0.53,0.94)";

        public static final String SQL_INSERT_FEATHER= "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Feather (dried)','Animal',85.00,2880,0.20,0.70)";

        public static final String SQL_INSERT_GARDEN_SNAIL = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Garden snail (dried with shell)','Animal',66.8,0,1.13,0.15)";

        public static final String SQL_INSERT_HOUSEFLY_MAGGOT = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Housefly Maggot (dried)','Animal',60.0,0,0.20,0.20)";

        public static final String SQL_INSERT_LOCAL_FISH_MEAL = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Local fish meal','Animal',39.00,0,3.00,1.80)";

        public static final String SQL_INSERT_TERMITE_MEAL = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Termite meal','Animal',46.3,0,0.23,0.38)";

        public static final String SQL_INSERT_UNSKINNED_DRIED_TADPOLE = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Unskinned dried tadpole ','Animal',43.5,0,0.25,0.57)";

        public static final String SQL_INSERT_UNSKINNED_BOILED_TADPOLE = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Unskinned boiled tadpole meal','Animal',45.9,0,2.43,0.42)";

        public static final String SQL_INSERT_BREWER_WET_GRAIN = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Brewer’s wet grain','High Fibre/Byproduct',27.80,3320,0.16,0.65)";

        public static final String SQL_INSERT_BREWER_DRY_GRAIN = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Brewer’s dry grain','High Fibre/Byproduct',17.0,0,0.08,0.10)";

        public static final String SQL_INSERT_CORN_COBS = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Corn cobs (Dried, milled)','High Fibre/Byproduct',2.3,523,0.11,0.04)";

        public static final String SQL_INSERT_COTTON_SEED_HULLS = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Cotton seed hulls','High Fibre/Byproduct',4.00,0,0.14,0.09)";

        public static final String SQL_INSERT_PALM_KERNEL_MEAL = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Palm kernel meal','High Fibre/Byproduct',21.30,2500,0.40,0.50)";

        public static final String SQL_INSERT_POULTRY_MANURE_DRIED_CAGE = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Poultry manure, dried, cage','High Fibre/Byproduct',28.70,0,7.80,2.20)";

        public static final String SQL_INSERT_POULTRY_MANURE_DRIED_FLOOR = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Poultry manure, dried, floor','High Fibre/Byproduct',25.30,0,2.50,1.60)";

        public static final String SQL_INSERT_OKRA_DRIED_FRUIT = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Okra, (dried fruit)','High Fibre/Byproduct',14.63,1622,0.80,0.62)";

        public static final String SQL_INSERT_RICE_HULLS = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Rice hulls','High Fibre/Byproduct',3.00,720,0.04,0.10)";

        public static final String SQL_INSERT_RICE_BRAN = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Rice bran','High Fibre/Byproduct',13.50,2040,0.10,1.70)";

        public static final String SQL_INSERT_RICE_GRAIN_WITH_HULLS = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Rice grain with hulls','High Fibre/Byproduct',11.17,2986,0.45,0.10)";

        public static final String SQL_INSERT_RICE_GRAIN_HULL_REMOVED= "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Rice grain, hull removed (brown rice)','High Fibre/Byproduct',12.51,3463,0.15,0.36)";

        public static final String SQL_INSERT_RICE_OFFAL_UN = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Rice offal (unparboiled)','High Fibre/Byproduct',5.50,1300,0.13,0)";

        public static final String SQL_INSERT_RICE_OFFAL_PA = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Rice offal (parboiled)','High Fibre/Byproduct',3.50,1200,0.16,0)";

        public static final String SQL_INSERT_SORGHUM_OFFAL= "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Sorghum offal','High Fibre/Byproduct',9.00,2700,0.01,0.09)";

        public static final String SQL_INSERT_TOMATO_PULP = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Tomato pulp, dried','High Fibre/Byproduct',21.00,1760,0.40,0.57)";

        public static final String SQL_INSERT_WHEAT_BRAN = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Wheat bran','High Fibre/Byproduct',14.80,2320,0.14,1.17)";

        public static final String SQL_INSERT_WHEAT_OFFAL= "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Wheat offal','High Fibre/Byproduct',15.00,1870,0.10,0.30)";

        public static final String SQL_INSERT_OKRA = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Okra,(seeds from dried fruit)','High Fibre/Byproduct',19.66,1700,0.80,0.62)";

        public static final String SQL_INSERT_BONE_MEAL = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Bone meal','Minerals',0,0,32.00,12.5)";

        public static final String SQL_INSERT_DICALCIUM_PHOSPHATE = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Dicalcium phosphate','Minerals',0,0,22.00,0)";

        public static final String SQL_INSERT_LIMESTONE = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Limestone','Minerals',0,0,35.00,0)";

        public static final String SQL_INSERT_OYSTER_SHELL = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Oyster shell','Minerals',0,0,35.00,0)";

        public static final String SQL_INSERT_PERIWINKLE = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Periwinkle shell','Minerals',0,0,32.00,0)";

        public static final String SQL_INSERT_EGG_SHELL_MEAL = "INSERT INTO " + TABLE_NAME + " " +
                "(feed_ingredient,class,crude_protein,energy,calcium,phosphorus) VALUES " +
                "('Egg shell meal','Minerals',0,0,35.2,0.12)";

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
        public static final String COLUMN_FEED_INGREDIENT = "feed_ingredient";
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
        public static final String COLUMN_FEED_INGREDIENT = "feed_ingredient";
        public static final String COLUMN_PROPORTION_PERCENT= "proportion_percent";
        public static final String COLUMN_PROPORTION_UNIT = "proportion_unit";
        public static final String COLUMN_QUANTITY_SPECIFIED= "quantity_specified";
        public static final String COLUMN_COMMENT= "comment";
        public static final String COLUMN_CRUDE_PROTEIN = "crude_protein";
        public static final String COLUMN_CALCIUM= "calcium";
        public static final String COLUMN_PHOSPHORUS= "phosphorus";
        public static final String COLUMN_CLASS= "class";

        public static final String SQL_CREATE_FORMULATED_RECORD_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_FEED_INGREDIENT + " TEXT, " +
                COLUMN_FORMULATION_NO + " INTEGER, " +
                COLUMN_PROPORTION_PERCENT + " REAL, " +
                COLUMN_PROPORTION_UNIT + " REAL, " +
                COLUMN_QUANTITY_SPECIFIED + " INTEGER, " +
                COLUMN_COMMENT + " TEXT, "  +
                COLUMN_CRUDE_PROTEIN + " REAL, " +
                COLUMN_CALCIUM + " REAL, " +
                COLUMN_PHOSPHORUS + " REAL, " +
                COLUMN_CLASS + " TEXT"
                +");";
    }


    public static final class CalculatedAnalysis implements BaseColumns{
        public static final String TABLE_NAME = "calculatedAnalysisRecord_lib";
        public static final String COLUMN_CRUDE_PROTEIN = "crude_protein";
        public static final String COLUMN_FORMULATION_NO = "form_no";
        public static final String COLUMN_CALCIUM = "calcium";
        public static final String COLUMN_PHOSPHORUS= "phosphorus";
        public static final String COLUMN_ENERGY = "energy";

        public static final String SQL_CREATE_CALCULATED_ANALYSIS_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_CRUDE_PROTEIN + " REAL, " +
                COLUMN_FORMULATION_NO + " INTEGER, " +
                COLUMN_CALCIUM + " REAL, " +
                COLUMN_PHOSPHORUS + " REAL, " +
                COLUMN_ENERGY + " REAL"
                +");";
    }



}
