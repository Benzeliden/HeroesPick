package baseEngine;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PickModel {

    PickModel(int id, int castleId, boolean checked, String Name){
        this.Id = id;
        this.Checked = checked;
        this.Name = Name;
        this.CastleId = castleId;
    }

    public int Id;

    public boolean Checked;

    public String Name;

    public int CastleId;

}

class PickModelDbHelper extends SQLiteOpenHelper{

    public PickModelDbHelper(Context context) {
        super(context, EngineConsts.DATABASE_NAME, null, EngineConsts.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(EngineConsts.LOG_TAG, "On create database");
        String heroTableSql = String.format(
                "create table %s (%s integer primary key autoincrement, %s text, %s integer);",
                HeroesTableConsts.TABLE_NAME,
                HeroesTableConsts.PRIMARY_KEY,
                HeroesTableConsts.HERO_NAME,
                HeroesTableConsts.HERO_CASTLE_ID);

        Log.d(EngineConsts.LOG_TAG, heroTableSql);
        db.execSQL(heroTableSql);

        String insertSql = String.format("insert into %1$s (%2$s, %3$s) select 'Сорша', 1 " +
                        "union all select 'Ивор', 2 ",
                HeroesTableConsts.TABLE_NAME,
                HeroesTableConsts.HERO_NAME,
                HeroesTableConsts.HERO_CASTLE_ID);
        Log.d(EngineConsts.LOG_TAG, insertSql);
        db.execSQL(insertSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

