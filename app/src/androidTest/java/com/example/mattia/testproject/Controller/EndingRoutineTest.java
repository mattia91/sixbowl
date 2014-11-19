package com.example.mattia.testproject.Controller;

import junit.framework.TestCase;

import com.example.mattia.testproject.Model.Bowl;
import com.example.mattia.testproject.Model.Player;
import com.example.mattia.testproject.Model.Tray;

import java.util.ArrayList;


public class EndingRoutineTest extends TestCase {

    ArrayList<Player> players = new ArrayList<Player>();
    Player giocatore1 = new Player();
    Player giocatore2 = new Player();
    Player active = giocatore1;
    int winner;

    public void test_gameOver( ){

        players.add(giocatore1);
        players.add(giocatore2);

        EndingRoutine end = new EndingRoutine();

      // -------------------------   l' ultima mossa l' ha fatta player 1; vuìince il player 2 -------------------

        //     0 0 0 3 3 0
        //    19           11
        //     0 0 0 0 0 0

        set_situation(0,0,0,0,0,0,11,0,3,3,0,0,0,19);

        winner = end.gameOver(players.get(0),players);

        assertEquals(1,winner);
        assertEquals(3 + 3 + 19, players.get(1).getTray().getSeeds());

        // -------------------------   l' ultima mossa l' ha fatta player 1; vuìince il player 1 -------------------

        //     0 0 0 3 0 0
        //    14           19
        //     0 0 0 0 0 0

        set_situation(0,0,0,0,0,0,19,0,0,3,0,0,0,14);

        winner = end.gameOver(players.get(0),players);

        assertEquals(0,winner);
        assertEquals(3 + 14 , players.get(1).getTray().getSeeds());

        // -------------------------   l' ultima mossa l' ha fatta player 2; vuìince il player 1 -------------------

        //     0 0 0 0 0 0
        //    14           19
        //     0 0 0 0 3 0

        set_situation(0,0,0,0,3,0,19,0,0,0,0,0,0,14);

        winner=end.gameOver(players.get(1),players);

        assertEquals(0,winner);

        // -------------------------   l' ultima mossa l' ha fatta player 2; gioco non finito -------------------

        //     0 0 3 0 0 0
        //    14           19
        //     0 0 0 0 3 0

        set_situation(0,0,0,0,3,0,19,0,0,0,3,0,0,14);

        winner=end.gameOver(players.get(1),players);

        assertEquals(-1,winner);

        // -------------------------   l' ultima mossa l' ha fatta player 1; gioco finito in pareggio -------------------

        //     0 0 3 0 0 0
        //    15           18
        //     0 0 0 0 0 0

        set_situation(0,0,0,0,0,0,18,0,0,3,0,0,0,15);

        winner = end.gameOver(players.get(0),players);

        assertEquals(-2,winner);
        assertEquals( 15 + 3, players.get(1).getTray().getSeeds());
    }

    private void set_situation(int b0,int b1,int b2 ,int b3,int b4, int b5, int t1, int b20, int b21, int b22, int b23, int b24, int b25, int t2){

        ArrayList<Bowl> bowls=new ArrayList<Bowl>();
        for(int i=0;i<6;i++){
            bowls.add(new Bowl());
        }

        bowls.get(0).setNum_seeds(b0);
        bowls.get(1).setNum_seeds(b1);
        bowls.get(2).setNum_seeds(b2);
        bowls.get(3).setNum_seeds(b3);
        bowls.get(4).setNum_seeds(b4);
        bowls.get(5).setNum_seeds(b5);

        Tray tray = new Tray();
        tray.setSeeds(t1);

        giocatore1.setBowls(bowls);
        giocatore1.setTray(tray);

        bowls=new ArrayList<Bowl>();
        for(int i=0;i<6;i++){
            bowls.add(new Bowl());
        }

        bowls.get(0).setNum_seeds(b20);
        bowls.get(1).setNum_seeds(b21);
        bowls.get(2).setNum_seeds(b22);
        bowls.get(3).setNum_seeds(b23);
        bowls.get(4).setNum_seeds(b24);
        bowls.get(5).setNum_seeds(b25);

        tray = new Tray();
        tray.setSeeds(t2);

        giocatore2.setBowls(bowls);
        giocatore2.setTray(tray);
    }

}