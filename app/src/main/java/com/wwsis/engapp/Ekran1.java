package com.wwsis.engapp;

import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.content.Context;


import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class Ekran1 extends ActionBarActivity {

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekran1);


        SQLiteDatabase db = openOrCreateDatabase("BazaDomrzeczowniki", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Domrzeczowniki (Angielski VARCHAR, Polski VARCHAR, id_DomRZ INTEGER PRIMARY KEY AUTOINCREMENT);");
        db.execSQL("DROP TABLE " + "Domrzeczowniki");
        db.execSQL("CREATE TABLE IF NOT EXISTS Domrzeczowniki (Angielski VARCHAR, Polski VARCHAR, id_DomRZ INTEGER PRIMARY KEY AUTOINCREMENT);");
        db.execSQL("INSERT INTO Domrzeczowniki VALUES ('garage', 'garaż', null);");
        db.execSQL("INSERT INTO Domrzeczowniki VALUES ('chair', 'krzesło', null);");
        db.execSQL("INSERT INTO Domrzeczowniki VALUES ('door', 'drzwi', null);");
        db.execSQL("INSERT INTO Domrzeczowniki VALUES ('lamp', 'lampa', null);");
        db.execSQL("INSERT INTO Domrzeczowniki VALUES ('carpet', 'dywan', null);");
        db.execSQL("INSERT INTO Domrzeczowniki VALUES ('floor', 'podloga', null);");
        db.execSQL("INSERT INTO Domrzeczowniki VALUES ('room', 'pokoj', null);");
        db.execSQL("INSERT INTO Domrzeczowniki VALUES ('desk', 'biorko', null);");
        db.execSQL("INSERT INTO Domrzeczowniki VALUES ('table', 'stol', null);");
        db.execSQL("INSERT INTO Domrzeczowniki VALUES ('kitchen', 'kuchnia', null);");
        Cursor cursor = db.rawQuery("SELECT * FROM Domrzeczowniki", null);
        cursor.moveToFirst();

// druga tabela do powtorek
        db.execSQL("CREATE TABLE IF NOT EXISTS Powtorki (Angielski1 VARCHAR, Polski1 VARCHAR, id_Powt INTEGER PRIMARY KEY AUTOINCREMENT);");
        Cursor cursor1 = db.rawQuery("SELECT * FROM Powtorki", null);
        cursor1.moveToFirst();

//db.close();
//metoda do czyszczenia tabeli - jak trzeba wyczyscic powtorki to trzeba ja odkomentowac i puscic program,
// program sie wysypie, ale po ponownym zakomentowaniu juz bedzie dzialal a tabela bedzie taka jak zadeklarowalismy na poczatku
// dropTable();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ekran1, menu);
        return true;
    }
    public void WlaczLekcje (View view)
    {
        startActivity(new Intent(Ekran1.this, LEKCJE.class));
    }
    public void WlaczPowtorki (View view)
    {
        startActivity(new Intent(Ekran1.this, POWTORKI.class));
    }
    public void noweSlowko (View view)
    {
        startActivity(new Intent(Ekran1.this, Nowe_slowka.class));
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
    //metoda do czyszczenia tabeli
    public void dropTable() {
        try {
            db = openOrCreateDatabase("BazaDomrzeczowniki", Context.MODE_PRIVATE, null);
            db.execSQL("DROP TABLE " + "Domrzeczowniki");
            db.execSQL("DROP TABLE " + "Powtorki");
            db.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error encountered while dropping.", Toast.LENGTH_LONG);
        }
    }
}
