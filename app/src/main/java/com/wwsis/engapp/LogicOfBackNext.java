package com.wwsis.engapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;

import java.lang.reflect.InvocationTargetException;

public class LogicOfBackNext extends AppCompatActivity{

    TextView slowo = (TextView) findViewById(R.id.word);
    TextView licznik = (TextView) findViewById(R.id.licznik);
    EditText tlumaczenie = (EditText) findViewById(R.id.editTexty);

    int nr_slowka = 0;
    int numerSlowka;


 /*

            TextView slowo = (TextView) findViewById(R.id.word);
        TextView licznik = (TextView) findViewById(R.id.licznik);


            EditText tlumaczenie = (EditText) findViewById(R.id.editTexty);

*/
    public void backi(TextView slowo, TextView licznik, EditText tlumaczenie, int nr_slowka, int numerSlowka){

        SQLiteDatabase db = openOrCreateDatabase("BazaDomrzeczowniki", MODE_PRIVATE, null);
        try {
        Cursor cursor = db.rawQuery("SELECT * FROM Powtorki", null);
        int nrOfRows = cursor.getCount();

               this.nr_slowka=nr_slowka;

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
    catch (NullPointerException x) {
        System.out.println("Jakis problem z Invocation");
    }
    }
    public void next(TextView slowo, TextView licznik, EditText tlumaczenie, int nr_slowka, int numerSlowka) {
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
    /*

    public void doNext(int nr_slowka, int numerSlowka){
        next(slowo, licznik, tlumaczenie, nr_slowka, numerSlowka);
    }
*/
    public void doBack(){
        backi(slowo, licznik, tlumaczenie, nr_slowka, numerSlowka);
    }

}
