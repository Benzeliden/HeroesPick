package com.example.des.myapplication.pickerUtils;

import android.view.View;
import android.widget.TextView;

import com.example.des.myapplication.R;

import baseEngine.CastleModel;
import baseEngine.HeroModel;

public class CastlePickModel {

    public CastlePickModel(CastleModel source) {
        this.id = source.Id;
        this.castleName = source.CastleName;
        this.heroes = new HeroPickModel[source.getHeroCount()];
        int counter = 0;
        for (HeroModel model : source.getHeroList()) {
            heroes[counter] = new HeroPickModel(model);
            counter++;
        }
    }

    public void bind(View view) {
        TextView tvName = (TextView) view.findViewById(R.id.castleName);
        tvName.setText(castleName);
        TextView tvTitle = (TextView) view.findViewById(R.id.castleTitle);
        tvTitle.setText(getTitle());
    }

    public int id;

    public String castleName;

    public String getTitle() {
        return String.format("Selected %d / %d", getSelectedCount(), count());
    }

    //TODO: uncheck castles
    public boolean isChecked(){
        return true;
    }

    public int getSelectedCount() {
        int res = 0;
        for (HeroPickModel heroe : heroes) {
            if (heroe.isChecked()) {
                res++;
            }
        }
        return res;
    }

    public HeroPickModel[] heroes;

    public int count() {
        return heroes.length;
    }

    public HeroPickModel get(int pos) {
        return heroes[pos];
    }

}
