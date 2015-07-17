package com.example.des.myapplication;

import android.view.View;
import android.widget.TextView;

import baseEngine.CastleModel;
import baseEngine.HeroModel;

public class CastlePickModel {

    private View convertView;

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

    public void updateBindings() {
        if (convertView == null) {
            return;
        }
        TextView tvName = (TextView) convertView.findViewById(R.id.castleName);
        tvName.setText(castleName);
        TextView tvTitle = (TextView) convertView.findViewById(R.id.castleTitle);
        tvTitle.setText(getTitle());
    }

    public void bind(View view) {
        convertView = view;
        updateBindings();
    }

    public int id;

    public String castleName;

    public String getTitle() {
        return String.format("Selected %d / %d", getSelectedCount(), count());
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

    public Finder getChecked(Finder finder) {
        for (HeroPickModel heroe : heroes) {
            if (heroe.isChecked()) {
                if (finder.counter > 0) {
                    finder.counter--;
                } else {
                    finder.result = heroe;
                    heroe.toogle();
                    break;
                }
            }
        }
        return finder;
    }

}
