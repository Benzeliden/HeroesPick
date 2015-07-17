package com.example.des.myapplication.pickerUtils;

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


    public GetRandomHeroResponce GetRandomPick(CustomExpandableAdapter adapter){
        GetRandomHeroResponce result = adapter.getChecked(random, PickMode.UNCHECK_PICKED);

        return result;
    }

    public void ResetDB() {
        dataProvider.ResetDatabase();
        Init(dataProvider);
    }
}