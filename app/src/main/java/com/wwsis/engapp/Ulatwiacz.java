package com.wwsis.engapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class Ulatwiacz extends ActionBarActivity {

    public void SlowoZTableliDoTv(String nazwaTabeli, int numerWiersza, TextView nazwaTextView, String nazwaKolumny) {

        String a = " " + nazwaTabeli;
        int b = numerWiersza;
        TextView c = nazwaTextView;
        String d = nazwaKolumny;

        SQLiteDatabase db = openOrCreateDatabase("BazaDomrzeczowniki", MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM" + a, null);
        cursor.moveToPosition(b);
        c.setText(cursor.getString(cursor.getColumnIndex(d)));
        //klasa inicjujaca tej metody musi podac id TextView z ktorego bedzie kozystac
        //np. TextView DodanoInfo = (TextView) findViewById(R.id.DodanoInfo);
    }

    public void DodajDoPowtorek(EditText nazwaTv1, EditText nazwaTv2){

        //klasa inicjujaca tej metody musi podac id TextView z ktorego bedzie kozystac
        //np. TextView DodanoInfo = (TextView) findViewById(R.id.DodanoInfo);

        EditText a = nazwaTv1;
        EditText b = nazwaTv2;
            SQLiteDatabase db = openOrCreateDatabase("BazaDomrzeczowniki", MODE_PRIVATE, null);

            String powtENG = a.getText().toString();
            String powtPL = b.getText().toString();

            powtENG ="'"+powtENG+"'";
            powtPL ="'"+powtPL+"'";

           String zmienna = "''";
        if(powtENG.equals(zmienna) || powtPL.equals(zmienna)){
            TextView DodanoInfo = (TextView) findViewById(R.id.DodanoInfo);
            DodanoInfo.setText("Próbujesz dodać puste pole");
        }
        else{
            db.execSQL("INSERT INTO Powtorki VALUES ( " + powtENG + ", " + powtPL + ",null);");
            TextView DodanoInfo = (TextView) findViewById(R.id.DodanoInfo);
            DodanoInfo.setText("Dodałeś i działa ! ! !");
            nazwaTv1.setText("");
            nazwaTv2.setText("");
        }

        }

    }

