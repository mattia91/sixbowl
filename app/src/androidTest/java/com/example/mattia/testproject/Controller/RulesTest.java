package com.example.mattia.testproject.Controller;

import com.example.mattia.testproject.Model.Bowl;
import com.example.mattia.testproject.Model.Player;

import junit.framework.TestCase;

import java.util.ArrayList;

public class RulesTest extends TestCase {


    public void test_check_rules( ){

        Rules rules = new Rules();

        //definizione delle variabili da passare al metodo per essere testato :
        ArrayList<Player> players = new ArrayList<Player>();
        Player giocatore1 = new Player();
        Player giocatore2 = new Player();
        Player active = giocatore1;
        players.add(giocatore1);
        players.add(giocatore2);
        int game_mode = 1;



        /*TODO : TEST DA ESEGUIRE : CONTROLLARE CHE IL TURNO VENGA CAMBIATO PASSANDO UN VASO,
          CHE IL TURNO NON  VENGA CAMBIATO PASSANDO NULL E CHE I SEMI VENGANO RUBATI
         */


        Bowl instance_bowl = core.getActive().getBowls().get(3);
        core.move_seeds(instance_bowl);

        //     3 3 3 3 3 3
        //    0           1
        //     3 3 3 0 4 4


        // devo verificare che il numero di semi nel tray sia giusto
        //che la bowl da cui ho rubato sia vuota
        // e che il player active sia giusto

        instance_bowl = core.getActive().getBowls().get(0);
        Bowl last_bowl=core.move_seeds(instance_bowl);

        //     3 3 3 3 3 3
        //    0           1
        //     0 4 4 1 4 4

        core.check_rules(last_bowl);

        //     3 3 3 0 3 3
        //    0           5
        //     0 4 4 0 4 4

        assertEquals(5, core.getActive().getTray().getSeeds());
        //assertEquals(0, core.getActive().getBowls().get(3).getNum_seeds());

        assertEquals(core.getActive(),core.getPlayers().get(0));
       /* assertEquals(0,core.getActive().getBowls().get(1).getNum_seeds());
        assertEquals(0,core.getActive().getBowls().get(3).getNum_seeds());
        assertEquals(0,core.getPlayers().get(1).getBowls().get(3).getNum_seeds()); */

    }


}