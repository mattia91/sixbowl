package com.example.mattia.testproject;

import com.example.mattia.testproject.Model.Board;
import com.example.mattia.testproject.Model.Bowl;

/**
 * Created by martino on 21/11/14.
 */
public class CoreActivity {

    Board board = new Board();



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



}
