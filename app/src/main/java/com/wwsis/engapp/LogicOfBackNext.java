package com.wwsis.engapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;

public class LogicOfBackNext extends AppCompatActivity{


    int nr_slowka = 0;
    int numerSlowka;

 /*   TextView slowo = (TextView) findViewById(R.id.word);
    TextView licznik = (TextView) findViewById(R.id.licznik);
     EditText tlumaczenie = (EditText) findViewById(R.id.editTexty);

            TextView slowo = (TextView) findViewById(R.id.word);
        TextView licznik = (TextView) findViewById(R.id.licznik);


            EditText tlumaczenie = (EditText) findViewById(R.id.editTexty);

*/
    public void back(TextView slowo, TextView licznik, EditText tlumaczenie){
        SQLiteDatabase db = openOrCreateDatabase("BazaDomrzeczowniki", MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM Powtorki", null);
        int nrOfRows = cursor.getCount();


        try {
            if (nr_slowka != 0) {
                nr_slowka--;
            } else {
                nr_slowka = nrOfRows -1;
                slowo.setTextColor(Color.BLUE);
            }
            numerSlowka = nr_slowka + 1;

            Log.i("nr słowka " + nr_slowka, "   liczba wszystkich słówek " + nrOfRows);
            String daneDoLicznika = numerSlowka + "/" + nrOfRows;

            cursor.moveToPosition(nr_slowka);
            slowo.setText(cursor.getString(cursor.getColumnIndex("Angielski1")));
            licznik.setText(daneDoLicznika);

            tlumaczenie.setText("");

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Wylapano wyjatek w cofaniu");
        }
    }
    public void next(TextView slowo, TextView licznik, EditText tlumaczenie) {
        SQLiteDatabase db = openOrCreateDatabase("BazaDomrzeczowniki", MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM Powtorki", null);
        int nrOfRows = cursor.getCount();


        try {
            if (nr_slowka < nrOfRows - 1) {
                nr_slowka++;
            } else {
                nr_slowka = 0;
                slowo.setTextColor(Color.BLUE);
            }
            numerSlowka = nr_slowka + 1;

            Log.i("nr słowka " + nr_slowka, "   liczba wszystkich słówek " + nrOfRows);
            String daneDoLicznika = numerSlowka + "/" + nrOfRows;

            cursor.moveToPosition(nr_slowka);
            slowo.setText(cursor.getString(cursor.getColumnIndex("Angielski1")));
            licznik.setText(daneDoLicznika);

            tlumaczenie.setText("");

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Wylapano wyjatek");
        }
    }
}
