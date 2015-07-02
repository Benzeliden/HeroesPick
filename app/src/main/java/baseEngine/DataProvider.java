package baseEngine;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Collection;

public class DataProvider {

    private Context context;
    PickModelDbHelper dbHelper;

    public DataProvider(Context context) {
        this.context = context;
        dbHelper = new PickModelDbHelper(context);
    }

    public Collection<PickModel> GetModels() {
        Collection<PickModel> collection = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.query(HeroesTableConsts.TABLE_NAME, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            int colIndexId = c.getColumnIndex(HeroesTableConsts.PRIMARY_KEY);
            int nameColId = c.getColumnIndex(HeroesTableConsts.HERO_NAME);
            int castleIdColId = c.getColumnIndex(HeroesTableConsts.HERO_CASTLE_ID);
            do {
                PickModel readedModel = new PickModel(
                        c.getInt(colIndexId), c.getInt(castleIdColId), false, c.getString(nameColId)
                );
                collection.add(readedModel);
            } while (c.moveToNext());
        }
        c.close();
        dbHelper.close();
        //turn heroesSet.values();
        return collection;
    }
}


