package com.example.des.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import baseEngine.HeroesPickerHelper;
import baseEngine.PickModel;


public class MainActivity extends Activity implements View.OnClickListener {


    private String Log_tag = "app";

    private TextView labelMain;
    private int counter = 0;
    private ListView listView;
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
        Collection<PickModel> models = pickerHelper.GetModels();

        listView = (ListView) findViewById(R.id.mainListView);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        ArrayAdapter<PickModel> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_multiple_choice,
                models.toArray(new PickModel[models.size()]));

        listView.setAdapter(adapter);

    }

    private void doGo(View v) {
        List<Integer> itemIds = new ArrayList<>();
        List<Integer> positions = new ArrayList<>();

        SparseBooleanArray checkedItems = listView.getCheckedItemPositions();
        for (int i = 0; i < checkedItems.size(); i++) {
            if (checkedItems.valueAt(i)) {
                int position = checkedItems.keyAt(i);
                PickModel item = (PickModel)listView.getAdapter().getItem(position);

                positions.add(position);
                itemIds.add(item.Id);
            }
        }


        if (positions.size() == 0){
            Toast.makeText(v.getContext(), "Отметьте хотя бы 1 героя в списке!", Toast.LENGTH_SHORT).show();
        }
        else{
            int pos = pickerHelper.GetRandomElement(positions);
            listView.setItemChecked(pos, false);
            PickModel picked = (PickModel)listView.getAdapter().getItem(pos);

            FragmentDialogResult dr = FragmentDialogResult.newInstance(picked.Name, "Выбран герой " + picked.Name + ", замок " + picked.CastleName);
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
