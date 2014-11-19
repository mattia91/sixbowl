package com.example.mattia.testproject.Controller;

import com.example.mattia.testproject.Model.Bowl;


import junit.framework.TestCase;

public class CoreTest extends TestCase {

    Core core = new Core();


    public void test_move_seeds(){


        /*
            Test effettuati :
            - mossa normale nella propria fila di vasi
            - mossa che termina nella fila dell'avversario
            - mossa che oltrepassa il tray dell'avversario e finisce nella nostra fila
         */



        //iniziamo ad esempio dal mio terzo vaso, condizione iniziale tutti i vasi con 3 semi e tray vuoto
        Bowl instance_bowl = core.getActive().getBowls().get(2);

        core.user_action(instance_bowl);


        //dopo la mossa appena compiuta mi aspetto questa situazione :
        //     3 3 3 3 3 3
        //    0           0
        //     3 3 0 4 4 4
        //verifichiamo

        //player attivo
        assertEquals(3, core.getActive().getBowls().get(0).getNum_seeds());
        assertEquals(3, core.getActive().getBowls().get(1).getNum_seeds());
        assertEquals(0, core.getActive().getBowls().get(2).getNum_seeds());
        assertEquals(4, core.getActive().getBowls().get(3).getNum_seeds());
        assertEquals(4, core.getActive().getBowls().get(4).getNum_seeds());
        assertEquals(4, core.getActive().getBowls().get(5).getNum_seeds());

        assertEquals(0, core.getActive().getTray().getSeeds());

        //opponente (non attivo)
        assertEquals(3, core.getPlayers().get(1).getBowls().get(0).getNum_seeds());
        assertEquals(3, core.getPlayers().get(1).getBowls().get(1).getNum_seeds());
        assertEquals(3, core.getPlayers().get(1).getBowls().get(2).getNum_seeds());
        assertEquals(3, core.getPlayers().get(1).getBowls().get(3).getNum_seeds());
        assertEquals(3, core.getPlayers().get(1).getBowls().get(4).getNum_seeds());
        assertEquals(3, core.getPlayers().get(1).getBowls().get(5).getNum_seeds());

        assertEquals(0, core.getPlayers().get(1).getTray().getSeeds());





        //reimpostiamo il giocatore attivo come prima
        core.setActive(core.getPlayers().get(0));
        instance_bowl = core.getActive().getBowls().get(3);

        //core.user_action(instance_bowl);
        core.user_action(instance_bowl);

        //dopo la mossa appena compiuta mi aspetto questa situazione :
       //     3 3 3 3 3 4
       //    0           1
       //     3 3 0 0 5 5
        //verifichiamo

        //player attivo
        assertEquals(3, core.getActive().getBowls().get(0).getNum_seeds());
        assertEquals(3, core.getActive().getBowls().get(1).getNum_seeds());
        assertEquals(0, core.getActive().getBowls().get(2).getNum_seeds());
        assertEquals(0, core.getActive().getBowls().get(3).getNum_seeds());
        assertEquals(5, core.getActive().getBowls().get(4).getNum_seeds());
        assertEquals(5, core.getActive().getBowls().get(5).getNum_seeds());

        assertEquals(1, core.getActive().getTray().getSeeds());

        //opponente (non attivo)
        assertEquals(4, core.getPlayers().get(1).getBowls().get(0).getNum_seeds());
        assertEquals(3, core.getPlayers().get(1).getBowls().get(1).getNum_seeds());
        assertEquals(3, core.getPlayers().get(1).getBowls().get(2).getNum_seeds());
        assertEquals(3, core.getPlayers().get(1).getBowls().get(3).getNum_seeds());
        assertEquals(3, core.getPlayers().get(1).getBowls().get(4).getNum_seeds());
        assertEquals(3, core.getPlayers().get(1).getBowls().get(5).getNum_seeds());

        assertEquals(0, core.getPlayers().get(1).getTray().getSeeds());




        //reimpostiamo il giocatore attivo come prima, testiamo ora che non accada nulla se il bowl passato contiene ZERO semi
        core.setActive(core.getPlayers().get(0));
        instance_bowl = core.getActive().getBowls().get(3);

        //core.user_action(instance_bowl);
        core.user_action(instance_bowl);

        //dopo la mossa appena compiuta mi aspetto questa situazione :
        //     3 3 3 3 3 4
        //    0           1
        //     3 3 0 0 5 5
        //verifichiamo

        //player attivo
        assertEquals(3, core.getActive().getBowls().get(0).getNum_seeds());
        assertEquals(3, core.getActive().getBowls().get(1).getNum_seeds());
        assertEquals(0, core.getActive().getBowls().get(2).getNum_seeds());
        assertEquals(0, core.getActive().getBowls().get(3).getNum_seeds());
        assertEquals(5, core.getActive().getBowls().get(4).getNum_seeds());
        assertEquals(5, core.getActive().getBowls().get(5).getNum_seeds());

        assertEquals(1, core.getActive().getTray().getSeeds());

        //opponente (non attivo)
        assertEquals(4, core.getPlayers().get(1).getBowls().get(0).getNum_seeds());
        assertEquals(3, core.getPlayers().get(1).getBowls().get(1).getNum_seeds());
        assertEquals(3, core.getPlayers().get(1).getBowls().get(2).getNum_seeds());
        assertEquals(3, core.getPlayers().get(1).getBowls().get(3).getNum_seeds());
        assertEquals(3, core.getPlayers().get(1).getBowls().get(4).getNum_seeds());
        assertEquals(3, core.getPlayers().get(1).getBowls().get(5).getNum_seeds());

        assertEquals(0, core.getPlayers().get(1).getTray().getSeeds());


        //reimpostiamo tutto :
        core = new Core();

        instance_bowl = core.getActive().getBowls().get(3);

        //core.user_action(instance_bowl);
        core.user_action(instance_bowl);

        //dopo la mossa appena compiuta mi aspetto questa situazione :
        //     3 3 3 3 3 3
        //    0           1
        //     3 3 3 0 4 4
        //verifichiamo

        //player attivo
        assertEquals(3, core.getActive().getBowls().get(0).getNum_seeds());
        assertEquals(3, core.getActive().getBowls().get(1).getNum_seeds());
        assertEquals(3, core.getActive().getBowls().get(2).getNum_seeds());
        assertEquals(0, core.getActive().getBowls().get(3).getNum_seeds());
        assertEquals(4, core.getActive().getBowls().get(4).getNum_seeds());
        assertEquals(4, core.getActive().getBowls().get(5).getNum_seeds());

        assertEquals(1, core.getActive().getTray().getSeeds());

        //opponente (non attivo)
        assertEquals(3, core.getPlayers().get(1).getBowls().get(0).getNum_seeds());
        assertEquals(3, core.getPlayers().get(1).getBowls().get(1).getNum_seeds());
        assertEquals(3, core.getPlayers().get(1).getBowls().get(2).getNum_seeds());
        assertEquals(3, core.getPlayers().get(1).getBowls().get(3).getNum_seeds());
        assertEquals(3, core.getPlayers().get(1).getBowls().get(4).getNum_seeds());
        assertEquals(3, core.getPlayers().get(1).getBowls().get(5).getNum_seeds());

        assertEquals(0, core.getPlayers().get(1).getTray().getSeeds());



        //ricominciamo da una situazione pulita :
        core = new Core();
        //impostiamo questa condizione :
        //     3 3 3 3 3 3
        //    0           0
        //     3 3 3 3 3 8

        core.getPlayers().get(0).getBowls().get(5).setNum_seeds(8);
        instance_bowl = core.getActive().getBowls().get(5);

        core.user_action(instance_bowl);

        //eseguiamo la mossa e come risultato dobbiamo avere :
        //     4 4 4 4 4 4
        //    0           1
        //     4 3 3 3 3 0
        //verifichiamo :

        //player attivo
        assertEquals(4, core.getActive().getBowls().get(0).getNum_seeds());
        assertEquals(3, core.getActive().getBowls().get(1).getNum_seeds());
        assertEquals(3, core.getActive().getBowls().get(2).getNum_seeds());
        assertEquals(4, core.getActive().getBowls().get(3).getNum_seeds());
        assertEquals(4, core.getActive().getBowls().get(4).getNum_seeds());
        assertEquals(0, core.getActive().getBowls().get(5).getNum_seeds());

        assertEquals(1, core.getActive().getTray().getSeeds());

        //opponente (non attivo)
        assertEquals(4, core.getPlayers().get(1).getBowls().get(0).getNum_seeds());
        assertEquals(4, core.getPlayers().get(1).getBowls().get(1).getNum_seeds());
        assertEquals(4, core.getPlayers().get(1).getBowls().get(2).getNum_seeds());
        assertEquals(4, core.getPlayers().get(1).getBowls().get(3).getNum_seeds());
        assertEquals(4, core.getPlayers().get(1).getBowls().get(4).getNum_seeds());
        assertEquals(4, core.getPlayers().get(1).getBowls().get(5).getNum_seeds());

        assertEquals(0, core.getPlayers().get(1).getTray().getSeeds());





    }

}