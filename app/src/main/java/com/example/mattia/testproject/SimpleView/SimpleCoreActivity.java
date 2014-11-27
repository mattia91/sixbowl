package com.example.mattia.testproject.SimpleView;

import android.app.Activity;
import android.graphics.Color;
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
import android.widget.TextView;
import com.example.mattia.testproject.Model.Bowl;
import com.example.mattia.testproject.R;
import com.example.mattia.testproject.Model.Board;
/**
 * Created by martino on 27/11/14.
 */

public class SimpleCoreActivity extends Activity implements View.OnClickListener {

    Board board = new Board();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout linLayout = new LinearLayout(this);
        linLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams linLayoutParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        setContentView(linLayout, linLayoutParam);
        TableLayout tableLayout = new TableLayout(this);  // creo il layout della tabella
        TableRow tableRow = new TableRow(this);  // creo la prima riga della tabella

        TextView giocatore2 = new TextView(this);
        TextView giocatore1 = new TextView(this);
        giocatore1.setText("player 1");
        giocatore1.setId(14);
        giocatore2.setText("player 2");
        giocatore1.setBackgroundColor(Color.YELLOW);
        giocatore2.setId(15);

        tableRow.addView(giocatore2);
        tableLayout.addView(tableRow);

        tableRow = new TableRow(this);
        for (int i = 0; i < 6; i++) {
            Button button = new Button(this);  // creo il bottone che va nella prima cella dell aprima riga
            button.setId(11 - i);
            button.setOnClickListener(this);
            button.setText(Integer.toString(board.getPlayers().get(1).getBowls().get(i).getNum_seeds()));
            tableRow.addView(button);  // aggiungo il bottone alla riga
        }

        tableLayout.addView(tableRow);

        tableRow = new TableRow(this);

        // creo tray di sinistra, del giocatore due
        Button button = new Button(this);
        button.setEnabled(false);
        button.setId(12);
        button.setText(Integer.toString(board.getPlayers().get(1).getTray().getSeeds()));
        tableRow.addView(button);

        // creo 4 bottoni non utilizzabili giusto per creare  spazio centrale nella seconda riga
        for (int j = 1; j < 5; j++) {
            button = new Button(this);
            button.setVisibility(View.INVISIBLE);
            tableRow.addView(button, j);
        }

        // creo secondo tray
        button = new Button(this);
        button.setId(13);
        button.setEnabled(false);
        button.setText(Integer.toString(board.getPlayers().get(0).getTray().getSeeds()));
        tableRow.addView(button);
        tableLayout.addView(tableRow); // aggiungo la riga alla tabella

        tableRow = new TableRow(this);

        for (int i = 0; i < 6; i++) {
            button = new Button(this);  // creo il bottone che va nella prima cella dell aprima riga
            button.setOnClickListener(this);
            button.setId(i);
            button.setText(Integer.toString(board.getPlayers().get(0).getBowls().get(i).getNum_seeds()));
            tableRow.addView(button);  // aggiungo il bottone alla riga
        }
        tableLayout.addView(tableRow); // aggiungo la riga alla tabella

        tableRow = new TableRow(this);
        tableRow.addView(giocatore1);
        tableLayout.addView(tableRow);

        // -----------------------------------------------------------------------------------------------------------------------------------------------
        RelativeLayout.LayoutParams centerTableParameters = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        centerTableParameters.addRule(RelativeLayout.CENTER_IN_PARENT);
        linLayout.addView(tableLayout, centerTableParameters);
        setContentView(linLayout);

    }

    public Bowl user_action(Bowl bowl){
        Bowl last_bowl = null;
        if( board.is_legal( bowl, board.getActive() ) ){
            board.setLast_active( board.getActive() );
            last_bowl = board.move_seeds(bowl);
            board.setActive( board.getRules().check_rules(last_bowl, board.getActive(), board.getPlayers(), board.getGame_mode()) );
            board.setEnd_of_game( board.getGame_over().gameOver(board.getLast_active(), board.getPlayers()) );
        }
        return last_bowl;
    }

    // metodo che dovrebbe aggiornare la view
    // con il primo ciclo scorro i primi sei bottoni e i due bottoni tray e modifico il Text del bottone
    private void updateView() {
        //aggiorno i bowls
        Button b = null;
        for (int i = 0; i < 12; i++) {
            b = (Button) this.findViewById(i);
            if(i < 6){
                b.setText(Integer.toString(board.getPlayers().get(0).getBowls().get(i).getNum_seeds()));
            }
            else b.setText(Integer.toString(board.getPlayers().get(1).getBowls().get(i-6).getNum_seeds()));
        }
        //aggiorno i tray
        b = (Button) this.findViewById(12);
        b.setText(Integer.toString(board.getPlayers().get(1).getTray().getSeeds()));
        b = (Button) this.findViewById(13);
        b.setText(Integer.toString(board.getPlayers().get(0).getTray().getSeeds()));


        TextView player1 = (TextView) this.findViewById(14);
        TextView player2 = (TextView) this.findViewById(15);

        if(board.getActive().equals(board.getPlayers().get(0))){
            player1.setBackgroundColor(Color.YELLOW);
            player2.setBackgroundColor(Color.WHITE);
        }
        else if(board.getActive().equals(board.getPlayers().get(1))){
            player2.setBackgroundColor(Color.YELLOW);
            player1.setBackgroundColor(Color.WHITE);
        }


    }

    // metodo eseguito quando un bottone viene premuto
    @Override
    public void onClick(View view) {
        Bowl pressed = new Bowl();
        if(view.getId() < 6 && board.getActive().equals(board.getPlayers().get(0))) {
            pressed = board.getActive().getBowls().get(view.getId());
            user_action(pressed);
        }
        else if(view.getId() >= 6 && board.getActive().equals(board.getPlayers().get(1))){
            pressed = board.getActive().getBowls().get(view.getId() - 6);
            user_action(pressed);
        }

            updateView();
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

