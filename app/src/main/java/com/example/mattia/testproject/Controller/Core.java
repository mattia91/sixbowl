package com.example.mattia.testproject.Controller;

import com.example.mattia.testproject.Model.Bowl;
import com.example.mattia.testproject.Model.Constant;
import com.example.mattia.testproject.Model.Player;

/**
 * Created by mattia on 17/11/2014.
 */
public class Core {

    private Player players[];
    private int turn;

    public Core(){
        players=new Player[Constant.number_player];
        turn=1;
    }

    public Bowl user_action(Player active_player, Player player, Bowl bowl){

        if (is_legal(bowl,active_player)){

            Bowl last_bowl = move_seeds(active_player, player, bowl);

            check_rules(active_player,player,bowl,turn);

            end();
        }

        return null;
    }

    private boolean is_legal(Bowl bowl,Player player){

        if (bowl.getNum_seeds()==0) return false;
        if (!player.getBowls().contains(bowl)) return false;

        return true;
    }


    private Bowl move_seeds(Player active_player, Player player, Bowl bowl){

        return null;
    }

    private void check_rules(Player active_player, Player player, Bowl bowl, int turn){

    }

    private void end(){
        //TODO controlla i vasi del giocatore attivo, se sono vuoti procedura di fine

        //aggiorna le statistiche
    }
}
