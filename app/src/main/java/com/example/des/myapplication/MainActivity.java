package com.example.des.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import baseEngine.DataProvider;
import baseEngine.PickModel;


public class MainActivity extends Activity implements View.OnClickListener {

    private TextView labelMain;
    private int counter = 0;
    private LinearLayout checkboxLayout;
    private DataProvider dataProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataProvider = new DataProvider();
        InitLabel();
        InitCheckboxMap();

        Button btnGo = (Button) findViewById(R.id.btnGo);
        btnGo.setOnClickListener(this);
    }

    private void InitLabel() {
        labelMain = (TextView)findViewById(R.id.labelTitle);
        Activity current = this;
        View.OnClickListener onCl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                String info = "Clicked " + counter + " times";
                labelMain.setText(info);
                Toast.makeText(v.getContext(), info, Toast.LENGTH_SHORT).show();
            }
        };

        labelMain.setOnClickListener(onCl);
    }

    private void InitCheckboxMap(){
        checkboxLayout = (LinearLayout) findViewById(R.id.layoutForDraw);
        int wrapContent = LinearLayout.LayoutParams.WRAP_CONTENT;
        LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(
                wrapContent, wrapContent);


        for (PickModel model : dataProvider.GetModels()) {

            CheckBox cb = new CheckBox(this);
            cb.setText(model.Name);
            cb.setId(model.Id);
            checkboxLayout.addView(cb, lParams);
        }

    }

    private void doGo(View v){
        Toast.makeText(v.getContext(), "To be continued...", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGo:
                doGo(v);
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
}
