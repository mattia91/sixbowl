package com.example.mattia.testproject;

import android.view.View.OnClickListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;


public class MainActivity extends Activity implements OnClickListener {

    private CoreActivity core = new CoreActivity(this); // ho dichiarato qusta nuova variabile

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        LinearLayout linLayout = new LinearLayout(this);
        linLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams linLayoutParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        setContentView(linLayout, linLayoutParam);

        // -------------------------------------------------------------------------------------------------------------------------------------------
        TableLayout tableLayout = new TableLayout(this);  // creo il layout della tabella

        TableRow tableRow = new TableRow(this);  // creo la prima riga della tabella

        int i = 0;
        for (; i < 6; i++) {
            Button button = new Button(this);  // creo il bottone che va nella prima cella dell aprima riga
            button.setId(5-i);
            button.setOnClickListener(this);
            button.setText("3");
            tableRow.addView(button,i);  // aggiungo il bottone alla riga
        }
        tableLayout.addView(tableRow); // aggiungo la riga alla tabella

        // creo primo tray
        tableRow = new TableRow(this);
        Button button = new Button(this);
        button.setEnabled(false);
        i=5;
        button.setId(i + 1);
        button.setText("0");
        tableRow.addView(button, 0);

        // creo 4 bottoni non utilizzabili giusto per creare  spazio centrale nella seconda riga
        for (int j = 1; j < 5; j++) {
            button = new Button(this);
            button.setVisibility(View.INVISIBLE);
            tableRow.addView(button, j);
        }

        // creo secondo tray
        button = new Button(this);
        i=6;
        button.setId(i + 1);
        button.setEnabled(false);
        button.setText("0");
        tableRow.addView(button, 5);
        tableLayout.addView(tableRow); // aggiungo la riga alla tabella

        tableRow = new TableRow(this);
        for (i = 0; i < 6; i++) {
            button = new Button(this);  // creo il bottone che va nella prima cella dell aprima riga
            button.setOnClickListener(this);
            button.setId(i+8);
            button.setText("3");
            tableRow.addView(button,i);  // aggiungo il bottone alla riga
        }
        tableLayout.addView(tableRow); // aggiungo la riga alla tabella

        // -----------------------------------------------------------------------------------------------------------------------------------------------


        RelativeLayout.LayoutParams centerTableParameters = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        centerTableParameters.addRule(RelativeLayout.CENTER_IN_PARENT);

        linLayout.addView(tableLayout, centerTableParameters);
        setContentView(linLayout);

    }

   // metodo eseguito quando un bottone viene premuto
    @Override
    public void onClick(View view) {

        core.interfaccia(view.getId(),view); // passa come parametri l' id del bottone e il bottone stesso

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
