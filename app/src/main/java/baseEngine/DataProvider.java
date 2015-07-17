package baseEngine;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DataProvider {

    private String sqlSelect;

    private Context context;
    private PickModelDbHelper dbHelper;

    public DataProvider(Context context) {
        this.context = context;
        dbHelper = new PickModelDbHelper(context);

        sqlSelect = String.format(
                "select ct.%3$s as cid, ct.%5$s as cName, ht.%4$s as hid, ht.%6$s as hName" +
                        " from %1$s as ct inner join %2$s as ht on ct.%3$s = ht.%7$s" +
                        " order by cid",
                CastleTableConsts.T_NAME,
                HeroesTableConsts.TABLE_NAME,
                CastleTableConsts.P_KEY,
                HeroesTableConsts.PRIMARY_KEY,
                CastleTableConsts.C_NAME,
                HeroesTableConsts.HERO_NAME,
                HeroesTableConsts.HERO_CASTLE_ID);
    }

    public Collection<HeroModel> GetModels() {
        Collection<HeroModel> collection = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery(sqlSelect, null);
        if (c.moveToFirst()) {
            int colIndexId = c.getColumnIndex("hid");
            int nameColId = c.getColumnIndex("hName");
            int castleNameColId = c.getColumnIndex("cName");
            do {
                HeroModel readedModel = new HeroModel(
                        c.getInt(colIndexId), c.getString(castleNameColId), false, c.getString(nameColId)
                );
                collection.add(readedModel);
            } while (c.moveToNext());
        }
        c.close();
        dbHelper.close();

        return collection;
    }

    public Map<Integer, CastleModel> GetModelsGrouped() {
        Map<Integer, CastleModel> result = new HashMap<>();


        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor castleCursor = db.rawQuery(sqlSelect, null);

        if (castleCursor.moveToFirst()) {
            int castleIdCol = castleCursor.getColumnIndex("cid");
            int castleNameCol = castleCursor.getColumnIndex("cName");
            int heroIdCol = castleCursor.getColumnIndex("hid");
            int heroNameCol = castleCursor.getColumnIndex("hName");

            do {
                int castleId = castleCursor.getInt(castleIdCol);
                String castleName = castleCursor.getString(castleNameCol);
                if (!result.containsKey(castleId)) {
                    result.put(castleId,
                            new CastleModel(castleId, castleName));
                }

                CastleModel castle = result.get(castleId);


                HeroModel heroModel = new HeroModel(
                        castleCursor.getInt(heroIdCol),
                        castleName,
                        true,
                        castleCursor.getString(heroNameCol)
                );

                castle.addHero(heroModel);

            } while (castleCursor.moveToNext());
        }
        castleCursor.close();
        db.close();

        return result;
    }


    public void ResetDatabase() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dbHelper.ResetDB(db);
        db.close();
    }
}


