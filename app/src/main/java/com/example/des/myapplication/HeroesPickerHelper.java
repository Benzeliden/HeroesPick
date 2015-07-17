package com.example.des.myapplication;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Random;

import baseEngine.CastleModel;
import baseEngine.DataProvider;
import baseEngine.HeroModel;

public class HeroesPickerHelper{

    private Collection<HeroModel> heroesList;

    private Map<Integer, CastleModel> set;

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

    public Collection<HeroModel> GetModels() {
        return heroesList;
    }

    public Map<Integer, CastleModel> GetMap(){
        return set;
    }


    public HeroPickModel GetRandomPick(CustomExpandableAdapter adapter){
        int c = adapter.getCheckedCount();
        if (c == 0){
            return null;
        }
        Finder f = new Finder(random.nextInt(c));

        HeroPickModel result = adapter.getChecked(f);

        return result;
    }

    public void ResetDB() {
        dataProvider.ResetDatabase();
        Init(dataProvider);
    }
}

class Finder{

    public Finder(int count) {
        counter = count;
    }

    public int counter;

    public HeroPickModel result;
}
