package com.acgg.chickenfeed.data;

import android.provider.BaseColumns;

/**
 * Created by Prof Ogunjuyigbe on 5/8/2018.
 */

public class DbContract {
    public static final class FeedLibraryEntry implements BaseColumns{
        public static final String TABLE_NAME = "feed_library";
        public static final String COLUMN_DRY_MATTER = "dry_matter";
        public static final String COLUMN_CRUDE_PROTEIN = "crude_protein";
        public static final String COLUMN_ETHER_EXTRACT = "ether_extract";
        public static final String COLUMN_CRUDE_FIBRE = "crude_fibre";
        public static final String COLUMN_NITROGEN = "nitrogen";
        public static final String COLUMN_TOTAL_ASH = "total_ash";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_ME = "me";
        public static final String COLUMN_CALCIUM = "calcium";
        public static final String COLUMN_PHOSPHORUS = "phosphorus";
        public static final String COLUMN_LYSINE = "lysine";
        public static final String COLUMN_METHIONINE = "methionine";
        public static final String COLUMN_GROUP = "group";
    }
}
