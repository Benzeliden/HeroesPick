package com.example.des.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import baseEngine.HeroesPickerHelper;
import baseEngine.PickModel;


public class MainActivity extends Activity implements View.OnClickListener {


    private TextView labelMain;
    private int counter = 0;
    private LinearLayout checkboxLayout;
    private HeroesPickerHelper pickerHelper;
    private Boolean exit = false;

    private List<CheckBox> checkboxArray;
    private Handler primaryHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        primaryHandler = new Handler();

        pickerHelper = new HeroesPickerHelper(this);

        InitCheckboxMap();
        labelMain = (TextView)findViewById(R.id.labelTitle);
        labelMain.setOnClickListener(this);

        Button btnGo = (Button) findViewById(R.id.btnGo);
        btnGo.setOnClickListener(this);
    }

    private void InitCheckboxMap(){
        checkboxLayout = (LinearLayout) findViewById(R.id.layoutForDraw);
        int wrapContent = LinearLayout.LayoutParams.WRAP_CONTENT;
        LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(
                wrapContent, wrapContent);

        Collection<PickModel> models = pickerHelper.GetModels();
        checkboxArray = new ArrayList<CheckBox>();
        for (PickModel model : models) {

            CheckBox cb = new CheckBox(this);
            cb.setText(model.Name);
            cb.setId(model.Id);
            checkboxLayout.addView(cb, lParams);
            checkboxArray.add(cb);
        }

    }

    private void doGo(View v){

        List<Integer> listIds = new ArrayList<>();
        for (CheckBox cbox : checkboxArray){
            if (cbox.isChecked()){
                listIds.add(cbox.getId());
            }
        }

        PickModel picked = pickerHelper.GetRandomName(listIds);
        if (picked == null){
            Toast.makeText(v.getContext(), "Выберите хотя бы 1 героя!", Toast.LENGTH_SHORT).show();
        }
        else{
            FragmentDialogResult dr = FragmentDialogResult.newInstance(picked.Name, "Выбран герой " + picked.Name + ", замок " + picked.CastleId);
            dr.show(getFragmentManager(), "blala");

            CheckBox cb = (CheckBox)findViewById(picked.Id);
            cb.setChecked(false);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGo:
                doGo(v);
                break;
            case R.id.labelTitle:
                counter++;
                String info = "Clicked " + counter + " times";
                labelMain.setText(info);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Log.d("Settings clicked","Settings clicked in menu");
            Toast.makeText(this,"HELLO!", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
            System.exit(0);
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            primaryHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }

    }

}
