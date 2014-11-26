package com.example.mattia.testproject;

import android.view.View;
import android.widget.Button;

import com.example.mattia.testproject.Model.Board;
import com.example.mattia.testproject.Model.Bowl;

/**
 * Created by martino on 21/11/14.
 */
public class CoreActivity {

    Board board = new Board();

    MainActivity mainActivity;
    Bowl bowltemp=new Bowl();

    public CoreActivity(MainActivity mainActivity) {
        this.mainActivity=mainActivity;
    } // costruttore



    //METODI PUBBLICI DELLA CLASSE
    public Bowl user_action(Bowl bowl){
        if( board.is_legal( bowl, board.getActive() ) ){
            board.setLast_active( board.getActive() );
            Bowl last_bowl = board.move_seeds(bowl);
            board.setActive( board.getRules().check_rules(last_bowl, board.getActive(), board.getPlayers(), board.getGame_mode()) );
            board.setEnd_of_game( board.getGame_over().gameOver(board.getLast_active(), board.getPlayers()) );
        }
        return null;
    }

    // metodo che fa da interfaccia
    // ricevo come parametro l' id (i) del bottone premuto e il bottone stesso ( view )
    // dall' id prendo la bowl corrispondente

    public Bowl interfaccia(int i, View view){  // questa view passata come parametro no è tutta la view ma solo il bottone cliccato

        if((i>=0)&&(i<=5)){
            bowltemp=board.getPlayers().get(0).getBowls().get(i);
        }
        else if((i>=8) && (i<=13)){

           bowltemp = board.getPlayers().get(1).getBowls().get(i-8);

        }

        bowltemp = user_action(bowltemp);

        updateView(view); // richiamo il metodo che dovrebbe aggiornare la view

        return null;  // quando faccio la return la grafica non si aggiorna e l' appllicazione si blocca
    }


  // metodo che dovrebbe aggiornare la view
  // con il primo ciclo scorro i primi sei bottoni e i due bottoni tray e modifico il Text del bottone
    private void updateView(View view) {

        for(int i=0;i<8;i++) {
            Button b = (Button) mainActivity.findViewById(i);

            if(i==6) {
                b.setText(Integer.toString(board.getPlayers().get(0).getTray().getSeeds()));
            }
            else if (i==7) {
                b.setText(Integer.toString(board.getPlayers().get(1).getTray().getSeeds()));
            }
            else {
                 b.setText(Integer.toString(board.getPlayers().get(0).getBowls().get(i).getNum_seeds()));
            }
        }

      // aggiorno il Text degli altri sei
        for(int i=8;i<14;i++){
            int k=i;
            Button b=(Button)mainActivity.findViewById(k);
            b.setText(Integer.toString(board.getPlayers().get(1).getBowls().get(i-6-2).getNum_seeds()));
        }
    }



}
