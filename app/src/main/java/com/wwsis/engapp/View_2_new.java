package com.wwsis.engapp;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class View_2_new extends LogicOfAdd {
    String lista = "Powtorki";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_2_new);
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
        if (id == R.id.Lista1) {
            lista = "Lista1";

        }

            return true;
        }

    public void dodaj(View view) {

        EditText NowePL = (EditText) findViewById(R.id.NowePL);
        EditText NoweEng = (EditText) findViewById(R.id.NoweEng);


        DodajDoPowtorek(NowePL, NoweEng, lista);


    }
}
