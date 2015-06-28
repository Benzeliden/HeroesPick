package baseEngine;

import android.util.Log;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class DataProvider {

    private Map<Integer,PickModel> heroesSet;
    private Random random;

    public DataProvider(){
        this.heroesSet = new HashMap<Integer, PickModel>();
        random = new Random();

        heroesSet.put(1, new PickModel(1, 1, false, "Сорша"));
        heroesSet.put(2, new PickModel(2, 2, false, "Ивор"));
    }

    public Collection<PickModel> GetModels() {

        return heroesSet.values();
    }

    public String GetNameById(Integer id){
         return heroesSet.get(id).Name;
    }

    //TODO refactor this!
    public PickModel GetRandomName(List<Integer> ids){
        int size = ids.size();
        if (size == 0){
           return null;
        }
        int indx = random.nextInt(size);
        Integer id = ids.get(indx);

        if (!heroesSet.containsKey(id)){
            Log.e("Engine", "Heroes set doesn`t contain id " + id.toString()) ;
            return null;
        }

        return heroesSet.get(id);
    }
}
