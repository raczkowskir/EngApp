package com.wwsis.engapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;


public class POWTORKI extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_powtorki);


        SQLiteDatabase db = openOrCreateDatabase("BazaDomrzeczowniki", MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM Powtorki", null);
        int nrOfRows = cursor.getCount();

        TextView slowo = (TextView) findViewById(R.id.word);

        System.out.println("nr słowka "+nr_slowka+"   liczba wszystkich słówek "+nrOfRows);
        cursor.moveToPosition(nr_slowka);
        if (nrOfRows!=0)
        slowo.setText(cursor.getString(cursor.getColumnIndex("Angielski1")));
        else{
            slowo.setText("Nie ma powtorek");
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_powtorki, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.Clear) {
            SQLiteDatabase db = openOrCreateDatabase("BazaDomrzeczowniki", MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS Powtorki (Angielski1 VARCHAR, Polski1 VARCHAR, id_Powt INTEGER PRIMARY KEY AUTOINCREMENT);");
            db.execSQL("DROP TABLE " + "Powtorki");
            db.execSQL("CREATE TABLE IF NOT EXISTS Powtorki (Angielski1 VARCHAR, Polski1 VARCHAR, id_Powt INTEGER PRIMARY KEY AUTOINCREMENT);");

            System.out.println("Kibel czysty !!");

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    int nr_slowka = 0;

    public void nastepne(View view) {

        SQLiteDatabase db = openOrCreateDatabase("BazaDomrzeczowniki", MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM Powtorki", null);
        int nrOfRows = cursor.getCount();

        TextView slowo = (TextView) findViewById(R.id.word);

        try
        {
            if (nr_slowka < nrOfRows-1){
                nr_slowka++;
            }
            else
            {nr_slowka=0;}


            System.out.println("nr słowka "+nr_slowka+"   liczba wszystkich słówek "+nrOfRows);
            cursor.moveToPosition(nr_slowka);
            slowo.setText(cursor.getString(cursor.getColumnIndex("Angielski1")));

        }
        catch(IndexOutOfBoundsException e)
        {
          System.out.println("Wylapano wyjatek");
        }
    }

    public void poprawna(View view){

        String porownajA;
        EditText tlumaczenie = (EditText) findViewById(R.id.editTexty);

        SQLiteDatabase db = openOrCreateDatabase("BazaDomrzeczowniki", MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM Powtorki", null);
        int nrOfRows = cursor.getCount();
        cursor.moveToPosition(nr_slowka);
        porownajA = cursor.getString(cursor.getColumnIndex("Polski1"));
        if(nrOfRows != 0)
        tlumaczenie.setText(porownajA);
        else{
            Log.d("jak dziala","poprawnie - ale lista jest pusta");
        }

    }
    public void usun(View view){
        SQLiteDatabase db = openOrCreateDatabase("BazaDomrzeczowniki", MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM Powtorki", null);
        int nrOfRows = cursor.getCount();
        cursor.moveToPosition(nr_slowka);

        TextView aktualneSlowo = (TextView) findViewById(R.id.word);
        String powtENG = aktualneSlowo.getText().toString();

        String powtENG1 ="'"+ powtENG +"\"";

        int reszta = db.delete("Powtorki","Angielski1 = ? ",new String[] {powtENG});

        String resztaS = "" + reszta;
        Log.d("Reszta:", resztaS);
        Log.d("Aktualne slowo:"," "+ powtENG);
/*
        String powtENG = tv3.getText().toString();
        String powtPL = tv5.getText().toString();

        powtENG ="'"+powtENG+"'";
        powtPL ="'"+powtPL+"'";

        System.out.println(powtENG);
        db.execSQL("INSERT INTO Powtorki VALUES ( "+powtENG+", "+powtPL+",null);");
        Cursor cursor1 = db.rawQuery("SELECT * FROM Powtorki", null);
        cursor1.moveToFirst();
        */
    }

    public void sprawdzam(View view){
        try{
        EditText tlumaczenie = (EditText) findViewById(R.id.editTexty);
        TextView komentarz1 = (TextView) findViewById(R.id.komentarz);
        String porownajA;
        String porownajB;
        SQLiteDatabase db = openOrCreateDatabase("BazaDomrzeczowniki", MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM Powtorki", null);
        cursor.moveToPosition(nr_slowka);
        porownajA = cursor.getString(cursor.getColumnIndex("Polski1"));
        porownajB = tlumaczenie.getText().toString();

        if (porownajA.equals(porownajB)) {
            komentarz1.setText("DOBRZE!");
            komentarz1.setTextColor(Color.GREEN);
        } else {
            komentarz1.setText("ŹLE!");
            komentarz1.setTextColor(Color.RED);
        }
   }
        catch(IndexOutOfBoundsException N)
        {
            TextView komentarz1 = (TextView) findViewById(R.id.komentarz);
            komentarz1.setText("Lista jest pusta \n dodaj coś!");
            komentarz1.setTextColor(Color.BLUE);
        }
    }
}

