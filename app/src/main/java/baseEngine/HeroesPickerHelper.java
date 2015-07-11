package baseEngine;

import android.content.Context;
import android.util.Log;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class HeroesPickerHelper{

    private Map<Integer,PickModel> heroesSet;

    private Random random;
    private DataProvider dataProvider;

    public HeroesPickerHelper(Context context){
        dataProvider = new DataProvider(context);
        random = new Random();
        heroesSet = new HashMap<>();
        Init(dataProvider);
    }

    //TODO: use adapter to init
    protected void Init(DataProvider dataProvider){
        Collection<PickModel> data = dataProvider.GetModels();
        heroesSet.clear();

        for(PickModel pm: data){
            heroesSet.put(pm.Id, pm);
        }
    }

    public Collection<PickModel> GetModels() {
        return heroesSet.values();
    }


    public String GetNameById(Integer id){
        return heroesSet.get(id).Name;
    }

    public PickModel GetRandomName(List<Integer> ids){
        int size = ids.size();
        if (size == 0){
            return null;
        }
        int index = random.nextInt(size);
        Integer id = ids.get(index);

        if (!heroesSet.containsKey(id)){
            Log.e(EngineConsts.LOG_TAG, "Heroes set doesn`t contain id " + id.toString()) ;
            return null;
        }

        return heroesSet.get(id);
    }

    public void ResetDB() {
        dataProvider.ResetDatabase();
        Init(dataProvider);
    }
}
