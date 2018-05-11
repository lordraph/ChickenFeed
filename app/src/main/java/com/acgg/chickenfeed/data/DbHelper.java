package com.acgg.chickenfeed.data;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.acgg.chickenfeed.R;
import com.anychart.anychart.Resource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Tolulope Ogunjuyigbe on 5/8/2018.
 */

public class DbHelper extends SQLiteOpenHelper {
    private static final String TAG = DbHelper.class.getSimpleName();

    private Resources mResources;
    private static final String DATABASE_NAME = "acgg.db";
    private static final int DATABASE_VERSION = 1;
    Context context;
    SQLiteDatabase db;

    public DbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mResources = context.getResources();
        db = this.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db){

        final String SQL_CREATE_BUGS_TABLE = "CREATE TABLE " + DbContract.FeedLibraryEntry.TABLE_NAME + "(" +
                DbContract.FeedLibraryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DbContract.FeedLibraryEntry.COLUMN_NAME + "TEXT NOT NULL, " +
                DbContract.FeedLibraryEntry.COLUMN_DRY_MATTER + "TEXT NOT NULL, " +
                DbContract.FeedLibraryEntry.COLUMN_CRUDE_PROTEIN + "TEXT NOT NULL, " +
                DbContract.FeedLibraryEntry.COLUMN_ETHER_EXTRACT + "TEXT NOT NULL, " +
                DbContract.FeedLibraryEntry.COLUMN_CRUDE_FIBRE + "TEXT NOT NULL, " +
                DbContract.FeedLibraryEntry.COLUMN_NITROGEN + "TEXT NOT NULL, " +
                DbContract.FeedLibraryEntry.COLUMN_TOTAL_ASH + "TEXT NOT NULL, " +
                DbContract.FeedLibraryEntry.COLUMN_ME + "TEXT NOT NULL, " +
                DbContract.FeedLibraryEntry.COLUMN_CALCIUM + "TEXT NOT NULL, " +
                DbContract.FeedLibraryEntry.COLUMN_PHOSPHORUS + "TEXT NOT NULL, " +
                DbContract.FeedLibraryEntry.COLUMN_LYSINE + "TEXT NOT NULL, " +
                DbContract.FeedLibraryEntry.COLUMN_METHIONINE + "TEXT NOT NULL, " +
                DbContract.FeedLibraryEntry.COLUMN_GROUP + "TEXT NOT NULL, " + ");";

        db.execSQL(SQL_CREATE_BUGS_TABLE);
        Log.d(TAG, "Database created successfully");

        try{
            readDataToDb(db);
        }catch(IOException e){
            e.printStackTrace();
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int il){

    }

    private void readDataToDb(SQLiteDatabase db) throws IOException, JSONException{
        final String FL_NAME = "name";
        final String FL_DRY_MATTER = "dry_matter";
        final String FL_CRUDE_PROTEIN = "crude_protein";
        final String FL_ETHER_EXTRACT = "ether_extract";
        final String FL_CRUDE_FIBRE = "crude_fibre";
        final String FL_NITROGEN = "nitrogen";
        final String FL_TOTAL_ASH = "total_ash";
        final String FL_ME = "me";
        final String FL_CALCIUM = "calcium";
        final String FL_PHOSPHORUS = "phosphorus";
        final String FL_LYSINE = "lysine";
        final String FL_METHIONINE = "methionine";
        final String FL_GROUP = "group";

        try{
            String jsonDataString = readJsonDataFromFile();
            JSONArray feedLibraryItemsJsonArray = new JSONArray(jsonDataString);

            for(int i = 0; i < feedLibraryItemsJsonArray.length(); ++i){

                String name;
                String dry_matter;
                String crude_protein;
                String ether_extract;
                String crude_fibre;
                String nitrogen;
                String total_ash;
                String me;
                String calcium;
                String phosphorus;
                String lysine;
                String methionine;
                String group;

                JSONObject feedLibraryItemObject = feedLibraryItemsJsonArray.getJSONObject(i);

                name = feedLibraryItemObject.getString(FL_NAME);
                dry_matter = feedLibraryItemObject.getString(FL_DRY_MATTER);
                crude_protein = feedLibraryItemObject.getString(FL_CRUDE_PROTEIN);
                ether_extract = feedLibraryItemObject.getString(FL_ETHER_EXTRACT);
                crude_fibre = feedLibraryItemObject.getString(FL_CRUDE_FIBRE);
                nitrogen = feedLibraryItemObject.getString(FL_NITROGEN);
                total_ash = feedLibraryItemObject.getString(FL_TOTAL_ASH);
                me = feedLibraryItemObject.getString(FL_ME);
                calcium = feedLibraryItemObject.getString(FL_CALCIUM);
                phosphorus = feedLibraryItemObject.getString(FL_PHOSPHORUS);
                lysine = feedLibraryItemObject.getString(FL_LYSINE);
                methionine = feedLibraryItemObject.getString(FL_METHIONINE);
                group = feedLibraryItemObject.getString(FL_GROUP);

                ContentValues feedLibraryValues = new ContentValues();

                feedLibraryValues.put(DbContract.FeedLibraryEntry.COLUMN_NAME, name);
                feedLibraryValues.put(DbContract.FeedLibraryEntry.COLUMN_DRY_MATTER, dry_matter);
                feedLibraryValues.put(DbContract.FeedLibraryEntry.COLUMN_CRUDE_PROTEIN, crude_protein);
                feedLibraryValues.put(DbContract.FeedLibraryEntry.COLUMN_ETHER_EXTRACT, ether_extract);
                feedLibraryValues.put(DbContract.FeedLibraryEntry.COLUMN_CRUDE_FIBRE, crude_fibre);
                feedLibraryValues.put(DbContract.FeedLibraryEntry.COLUMN_NITROGEN, nitrogen);
                feedLibraryValues.put(DbContract.FeedLibraryEntry.COLUMN_TOTAL_ASH, total_ash);
                feedLibraryValues.put(DbContract.FeedLibraryEntry.COLUMN_ME, me);
                feedLibraryValues.put(DbContract.FeedLibraryEntry.COLUMN_CALCIUM, calcium);
                feedLibraryValues.put(DbContract.FeedLibraryEntry.COLUMN_PHOSPHORUS, phosphorus);
                feedLibraryValues.put(DbContract.FeedLibraryEntry.COLUMN_LYSINE, lysine);
                feedLibraryValues.put(DbContract.FeedLibraryEntry.COLUMN_METHIONINE, methionine);
                feedLibraryValues.put(DbContract.FeedLibraryEntry.COLUMN_GROUP, group);

                db.insert(DbContract.FeedLibraryEntry.TABLE_NAME, null, feedLibraryValues);
                Log.d(TAG, "Inserted Successfully" + feedLibraryValues);
            }
        } catch (JSONException e){
            Log.e(TAG, e.getMessage(), e);
            e.printStackTrace();
        }

    }

    private String readJsonDataFromFile() throws IOException{
        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();

        try{
            String jsonDataString = null;
            inputStream = mResources.openRawResource(R.raw.feed_library);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream, "UTF-8"));
            while((jsonDataString = bufferedReader.readLine()) != null){
                builder.append(jsonDataString);
            }
        }
        finally {
            if (inputStream != null){
                inputStream.close();
            }
        }
        return new String(builder);
    }
}
