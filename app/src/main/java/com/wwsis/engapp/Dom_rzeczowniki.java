package com.wwsis.engapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Dom_rzeczowniki extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dom_rzeczowniki);

        TextView tv3 = (TextView) findViewById(R.id.textView3); //tv3 to słówko po angielsku
        TextView tv5 = (TextView) findViewById(R.id.textView5); //tv5 to słówko po polsku

        SQLiteDatabase db = openOrCreateDatabase("BazaDomrzeczowniki", MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM Domrzeczowniki", null);
        cursor.moveToFirst();
        tv3.setText(cursor.getString(cursor.getColumnIndex("Angielski")));

        tv5.setText(null);

        db.execSQL("CREATE TABLE IF NOT EXISTS Powtorki (Angielski1 VARCHAR, Polski1 VARCHAR);");
        Cursor cursor1 = db.rawQuery("SELECT * FROM Powtorki", null);
        cursor1.moveToFirst();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dom_rzeczowniki, menu);
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

    int nr_slowka = 0;

    public void Sprawdz (View view)
    {
        //int nr_slowka = 0;
        TextView tv5 = (TextView) findViewById(R.id.textView5);
        SQLiteDatabase db = openOrCreateDatabase("BazaDomrzeczowniki", MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM Domrzeczowniki", null);
        cursor.moveToPosition(nr_slowka);
        tv5.setText(cursor.getString(cursor.getColumnIndex("Polski")));
        //ustawiacz = 1;
        //db.close();
    }

    public void Pamietam (View view){
        TextView tv3 = (TextView) findViewById(R.id.textView3);
        TextView tv5 = (TextView) findViewById(R.id.textView5);
       if (nr_slowka!=9)
       {nr_slowka = nr_slowka + 1;}
        else
       {nr_slowka = 0;
           tv3.setTextColor(Color.BLUE);
       }
        SQLiteDatabase db = openOrCreateDatabase("BazaDomrzeczowniki", MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM Domrzeczowniki", null);
        cursor.moveToPosition(nr_slowka);
        tv3.setText(cursor.getString(cursor.getColumnIndex("Angielski")));
        tv5.setText(null);
        //db.close();

    }

    public void Dodaj (View view) {
        TextView tv3 = (TextView) findViewById(R.id.textView3);
        TextView tv5 = (TextView) findViewById(R.id.textView5);
        SQLiteDatabase db = openOrCreateDatabase("BazaDomrzeczowniki", MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM Domrzeczowniki", null);
        cursor.moveToPosition(nr_slowka);
        tv5.setText(cursor.getString(cursor.getColumnIndex("Polski")));

       String powtENG = tv3.getText().toString();
       String powtPL = tv5.getText().toString();

        powtENG ="'"+powtENG+"'";
        powtPL ="'"+powtPL+"'";

        System.out.println(powtENG);
        db.execSQL("INSERT INTO Powtorki VALUES ( "+powtENG+", "+powtPL+",null);");
        Cursor cursor1 = db.rawQuery("SELECT * FROM Powtorki", null);
        cursor1.moveToFirst();

    }

}
