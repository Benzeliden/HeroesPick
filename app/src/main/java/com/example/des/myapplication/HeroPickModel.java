package com.example.des.myapplication;

import android.widget.CheckedTextView;

import baseEngine.HeroModel;

public class HeroPickModel {

    public HeroPickModel(HeroModel source) {
        id = source.Id;
        name = source.Name;
        castleName = source.CastleName;
        checked = true;
    }

    private void updateBinding() {
        if (bindedView != null) {
            bindedView.setChecked(checked);
        }
    }

    private CheckedTextView bindedView;

    private boolean checked;

    public int id;

    public String name;

    public String castleName;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean val) {
        checked = val;
        updateBinding();
    }

    public void toogle() {
        checked = !checked;
        updateBinding();
    }

    public void bind(CheckedTextView v) {
        v.setChecked(checked);
        v.setText(name);
        bindedView = v;
    }

    @Override
    public String toString() {

        return String.format("%s ( %s )", name, castleName);
    }
}
