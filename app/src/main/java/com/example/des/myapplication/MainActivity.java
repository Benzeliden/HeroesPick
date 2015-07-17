package com.example.des.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

import baseEngine.CastleModel;


public class MainActivity extends Activity implements View.OnClickListener {


    private String Log_tag = "app";

    private TextView labelMain;
    private int counter = 0;
    private ExpandableListView listView;
    private CustomExpandableAdapter adapter;
    private HeroesPickerHelper pickerHelper;
    private Boolean exit = false;
    private Handler primaryHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        primaryHandler = new Handler();

        pickerHelper = new HeroesPickerHelper(this);


        InitCheckboxMap();
        labelMain = (TextView) findViewById(R.id.labelTitle);
        labelMain.setOnClickListener(this);

        Button btnGo = (Button) findViewById(R.id.btnGo);
        btnGo.setOnClickListener(this);
    }

    private void InitCheckboxMap() {
        Map<Integer, CastleModel> set = pickerHelper.GetMap();
        adapter = new CustomExpandableAdapter(this, set);
        listView = (ExpandableListView) findViewById(R.id.mainListView);
        listView.setAdapter(adapter);
        listView.setOnChildClickListener(adapter);
        //android.R.layout.simple_expandable_list_item_2
    }

    private void doGo(View v) {
        //TODO: refactor this
        HeroPickModel result = pickerHelper.GetRandomPick(adapter);

        if (result == null) {
            Toast.makeText(v.getContext(), "Отметьте хотя бы 1 героя в списке!", Toast.LENGTH_SHORT).show();
        } else {
            //TODO: clear after pick bugged
            FragmentDialogResult dr = FragmentDialogResult.newInstance(result.name, String.format("Выбран герой %s, замок %s", result.name, result.castleName));
            dr.show(getFragmentManager(), "blala");
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
    public boolean onPrepareOptionsMenu(Menu menu) {

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
            Log.d("Settings clicked", "Settings clicked in menu");
            Toast.makeText(this, "HELLO!", Toast.LENGTH_SHORT).show();
            return true;
        }
        switch (id) {
            case R.id.action_settings:

                Log.d(Log_tag, "Settings clicked in menu");
                Toast.makeText(this, "HELLO!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_resetDb:
                Log.d(Log_tag, "ResetDB");
                pickerHelper.ResetDB();

                InitCheckboxMap();
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
