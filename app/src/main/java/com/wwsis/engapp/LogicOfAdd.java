package com.wwsis.engapp;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;


public class LogicOfAdd extends AppCompatActivity {

    public void DodajDoPowtorek(EditText nazwaTv1, EditText nazwaTv2, String lista){

        //klasa inicjujaca tej metody musi podac id TextView z ktorego bedzie kozystac
        //np. TextView DodanoInfo = (TextView) findViewById(R.id.DodanoInfo);

            SQLiteDatabase db = openOrCreateDatabase("BazaDomrzeczowniki", MODE_PRIVATE, null);

            String powtENG = nazwaTv1.getText().toString();
            String powtPL = nazwaTv2.getText().toString();

            powtENG ="'"+powtENG+"'";
            powtPL ="'"+powtPL+"'";

           String zmienna = "''";
        if(powtENG.equals(zmienna) || powtPL.equals(zmienna)){
            TextView DodanoInfo = (TextView) findViewById(R.id.DodanoInfo);
            DodanoInfo.setText("Próbujesz dodać puste pole");
        }
        else{
            db.execSQL("INSERT INTO "+lista+" VALUES ( " + powtENG + ", " + powtPL + ",null);");
            TextView DodanoInfo = (TextView) findViewById(R.id.DodanoInfo);
            DodanoInfo.setText("Dodałeś i działa ! ! !");
            nazwaTv1.setText("");
            nazwaTv2.setText("");
        }

        }

    }

