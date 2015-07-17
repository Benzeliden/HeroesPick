package com.example.des.myapplication.pickerUtils;

import android.widget.CheckedTextView;

import baseEngine.HeroModel;

public class HeroPickModel {

    public HeroPickModel(HeroModel source) {
        id = source.Id;
        name = source.Name;
        castleName = source.CastleName;
        checked = true;
    }

    private boolean checked;

    public int id;

    public String name;

    public String castleName;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean val) {
        checked = val;
    }

    public void toogle() {
        setChecked(!checked);
    }

    public void bind(CheckedTextView v) {
        v.setChecked(checked);
        v.setText(name);
    }

    @Override
    public String toString() {

        return String.format("%s ( %s )", name, castleName);
    }
}
