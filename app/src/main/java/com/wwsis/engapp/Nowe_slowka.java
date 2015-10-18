package com.wwsis.engapp;

import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class Nowe_slowka extends Ulatwiacz {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nowe_slowka);

        // TextView DodanoInfo = (TextView) findViewById(R.id.DodanoInfo);
        // SlowoZTableliDoTv("Powtorki",0, DodanoInfo,"Polski1");

}

    public void dodaj(View view) {

        EditText NowePL = (EditText) findViewById(R.id.NowePL);
        EditText NoweEng = (EditText) findViewById(R.id.NoweEng);
        TextView DodanoInfo = (TextView) findViewById(R.id.DodanoInfo);

        DodajDoPowtorek(NowePL, NoweEng);

        DodanoInfo.setText("Dodałeś i działa ! ! !");
    }
























    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nowe_slowka, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
