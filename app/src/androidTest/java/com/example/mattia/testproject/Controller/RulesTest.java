package com.example.mattia.testproject.Controller;

import com.example.mattia.testproject.Model.Bowl;
import com.example.mattia.testproject.Model.Player;
import com.example.mattia.testproject.Model.Tray;

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



        //     3 3 3 3 3 3
        //    0           1
        //     0 4 4 1 4 4

        // -------------------------   setto questa situazione  -----------------------------

       set_situation(giocatore1,giocatore2,0,4,4,1,4,4,1,3,3,3,3,3,3,0);

       rules.check_rules(giocatore1.getBowls().get(3),active,players,game_mode);
     // -----------------------------------------------------------------------------------------------

        // devo verificare che il numero di semi nel tray sia giusto
        //che la bowl da cui ho rubato sia vuota
        // e che il player active sia giusto

        //     3 3 0 3 3 3
        //    0           5
        //     0 4 4 0 4 4

        assertEquals(active, players.get(0));
        //player attivo
        assertEquals(0,giocatore1.getBowls().get(0).getNum_seeds());
        assertEquals(4,giocatore1.getBowls().get(1).getNum_seeds());
        assertEquals(4,giocatore1.getBowls().get(2).getNum_seeds());
        assertEquals(0,giocatore1.getBowls().get(3).getNum_seeds());
        assertEquals(4,giocatore1.getBowls().get(4).getNum_seeds());
        assertEquals(4,giocatore1.getBowls().get(5).getNum_seeds());

        assertEquals(5,giocatore1.getTray().getSeeds());

        //opponente (non attivo)
        assertEquals(3,giocatore2.getBowls().get(0).getNum_seeds());
        assertEquals(3,giocatore2.getBowls().get(1).getNum_seeds());
        assertEquals(0,giocatore2.getBowls().get(2).getNum_seeds());
        assertEquals(3,giocatore2.getBowls().get(3).getNum_seeds());
        assertEquals(3,giocatore2.getBowls().get(4).getNum_seeds());
        assertEquals(3,giocatore2.getBowls().get(5).getNum_seeds());

        assertEquals(0,giocatore2.getTray().getSeeds());
  // --------------------------------------------------------------------------------------------------------------------------
      // secondo test :  finiamo nel nostro try; passo null; devo restare lo stesso giocatore attivo


        //     3 3 0 3 3 3
        //    0           1
        //     0 4 0 0 4 4

        active = giocatore1;
        set_situation(giocatore1,giocatore2,0,4,0,0,4,4,1,3,3,0,3,3,3,0);

        rules.check_rules(null,active,players,game_mode);

        assertEquals(active, players.get(0));
        //player attivo
        assertEquals(0,giocatore1.getBowls().get(0).getNum_seeds());
        assertEquals(4,giocatore1.getBowls().get(1).getNum_seeds());
        assertEquals(0,giocatore1.getBowls().get(2).getNum_seeds());
        assertEquals(0,giocatore1.getBowls().get(3).getNum_seeds());
        assertEquals(4,giocatore1.getBowls().get(4).getNum_seeds());
        assertEquals(4,giocatore1.getBowls().get(5).getNum_seeds());

        assertEquals(1,giocatore1.getTray().getSeeds());

        //opponente (non attivo)
        assertEquals(3,giocatore2.getBowls().get(0).getNum_seeds());
        assertEquals(3,giocatore2.getBowls().get(1).getNum_seeds());
        assertEquals(0,giocatore2.getBowls().get(2).getNum_seeds());
        assertEquals(3,giocatore2.getBowls().get(3).getNum_seeds());
        assertEquals(3,giocatore2.getBowls().get(4).getNum_seeds());
        assertEquals(3,giocatore2.getBowls().get(5).getNum_seeds());

        assertEquals(0,giocatore2.getTray().getSeeds());

 // terzo test : giocatore attivo: giocatore1;  finisco nella bowl ( index=5) del giocatore 2; dovrebbe attivare giocatore 2

        active = giocatore1;

        //     3 3 3 3 3 4
        //    0           1
        //     3 3 3 3 0 4

        set_situation(giocatore1,giocatore2,3,3,3,3,0,4,1,3,3,3,3,3,3,4);

        rules.check_rules(giocatore2.getBowls().get(5),active,players,game_mode);

        assertEquals(active, players.get(1)); // verifico se viene cambiato il giocatore attivo
        //player attivo
        assertEquals(3,giocatore1.getBowls().get(0).getNum_seeds());
        assertEquals(3,giocatore1.getBowls().get(1).getNum_seeds());
        assertEquals(3,giocatore1.getBowls().get(2).getNum_seeds());
        assertEquals(3,giocatore1.getBowls().get(3).getNum_seeds());
        assertEquals(0,giocatore1.getBowls().get(4).getNum_seeds());
        assertEquals(4,giocatore1.getBowls().get(5).getNum_seeds());

        assertEquals(1,giocatore1.getTray().getSeeds());

        //opponente (non attivo)
        assertEquals(3,giocatore2.getBowls().get(0).getNum_seeds());
        assertEquals(3,giocatore2.getBowls().get(1).getNum_seeds());
        assertEquals(3,giocatore2.getBowls().get(2).getNum_seeds());
        assertEquals(3,giocatore2.getBowls().get(3).getNum_seeds());
        assertEquals(3,giocatore2.getBowls().get(4).getNum_seeds());
        assertEquals(4,giocatore2.getBowls().get(5).getNum_seeds());

        assertEquals(0,giocatore2.getTray().getSeeds());


     }

    // metodo privato per settare la configurazione
 private void set_situation(Player p1,Player p2,int b0,int b1,int b2 ,int b3,int b4, int b5, int t1, int b20, int b21, int b22, int b23, int b24, int b25, int t2){

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

     p1.setBowls(bowls);
     p1.setTray(tray);

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

     p2.setBowls(bowls);
     p2.setTray(tray);
 }


}