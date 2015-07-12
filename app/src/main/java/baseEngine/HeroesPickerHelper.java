package baseEngine;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class HeroesPickerHelper{

    private Collection<PickModel> heroesList;

    private Map<Integer, CastlePickModel> set;

    private Random random;
    private DataProvider dataProvider;

    public HeroesPickerHelper(Context context){
        dataProvider = new DataProvider(context);
        random = new Random();
        heroesList = new ArrayList<>();
        Init(dataProvider);
    }

    //TODO: use adapter to init
    protected void Init(DataProvider dataProvider){
        heroesList = dataProvider.GetModels();

        set = dataProvider.GetModelsGrouped();
    }

    public Collection<PickModel> GetModels() {
        return heroesList;
    }


    public Integer GetRandomElement(List<Integer> positions){
        int size = positions.size();
        if (size == 0){
            return null;
        }
        int index = random.nextInt(size);
        Integer position = positions.get(index);

        return position;
    }

    public void ResetDB() {
        dataProvider.ResetDatabase();
        Init(dataProvider);
    }
}
