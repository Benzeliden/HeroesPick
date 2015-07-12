package baseEngine;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class PickModelDbHelper extends SQLiteOpenHelper {

    private Context context;

    public PickModelDbHelper(Context context) {
        super(context, EngineConsts.DATABASE_NAME, null, EngineConsts.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(EngineConsts.LOG_TAG, "On create database");

        String castleTableSql = String.format(
                "create table %s (%s integer primary key autoincrement, %s text);",
                CastleTableConsts.T_NAME,
                CastleTableConsts.P_KEY ,
                CastleTableConsts.C_NAME
        );

        db.execSQL(castleTableSql);

        String heroTableSql = String.format(
                "create table %1$s (%2$s integer primary key autoincrement, %3$s text, %4$s integer NOT NULL, FOREIGN KEY(%4$s) REFERENCES %5$s(%6$s));",
                HeroesTableConsts.TABLE_NAME,
                HeroesTableConsts.PRIMARY_KEY,
                HeroesTableConsts.HERO_NAME,
                HeroesTableConsts.HERO_CASTLE_ID,
                CastleTableConsts.T_NAME,
                CastleTableConsts.P_KEY);

        Log.d(EngineConsts.LOG_TAG,heroTableSql);
        db.execSQL(heroTableSql);

        FillDB(db);
    }

    private void FillDB(SQLiteDatabase db) {

        String jsonStr = ResourceHelper.loadJSONFromResource(context.getResources());
        try {
            JSONObject obj = new JSONObject(jsonStr);
            JSONArray arr = obj.getJSONArray("Models");

            ContentValues castleValues = new ContentValues();
            ContentValues heroValues = new ContentValues();

            for (int i = 0; i < arr.length(); i++) {
                JSONObject castleDefinition = arr.getJSONObject(i);
                castleValues.put(CastleTableConsts.C_NAME, castleDefinition.getString("castle"));
                long newCastleId = db.insert(CastleTableConsts.T_NAME, null, castleValues);

                JSONArray heroesArray = castleDefinition.getJSONArray("heroes");
                for (int j = 0; j < heroesArray.length(); j++) {
                    String heroName = heroesArray.getString(j);
                    heroValues.put(HeroesTableConsts.HERO_NAME, heroName);
                    heroValues.put(HeroesTableConsts.HERO_CASTLE_ID, newCastleId);
                    db.insert(HeroesTableConsts.TABLE_NAME, null, heroValues);
                }
            }
        } catch (JSONException e) {
            Log.e(EngineConsts.LOG_TAG, "Error when filling db from json");
            e.printStackTrace();
            return;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2){

        }
    }

    public void ResetDB(SQLiteDatabase db){
        db.execSQL("delete from " + HeroesTableConsts.TABLE_NAME);
        db.execSQL("delete from " + CastleTableConsts.T_NAME);
        FillDB(db);
    }

}
