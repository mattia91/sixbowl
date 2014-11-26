package com.example.mattia.testproject.Model;

import com.example.mattia.testproject.Model.Board;
import com.example.mattia.testproject.Model.Bowl;


import junit.framework.TestCase;

import java.util.ArrayList;

public class BoardTest extends TestCase {

    Board board = new Board();


    public void test_move_seeds(){


        /*
            Test effettuati :
            - mossa normale nella propria fila di vasi
            - mossa che termina nella fila dell'avversario
            - mossa che oltrepassa il tray dell'avversario e finisce nella nostra fila
         */



        //iniziamo ad esempio dal mio terzo vaso, condizione iniziale tutti i vasi con 3 semi e tray vuoto
        Bowl instance_bowl = board.getLast_active().getBowls().get(2);

        board.move_seeds(instance_bowl);

        //dopo la mossa appena compiuta mi aspetto questa situazione :
        //     3 3 3 3 3 3
        //    0           0
        //     3 3 0 4 4 4
        //verifichiamo

        //player attivo
        assertEquals(3, board.getLast_active().getBowls().get(0).getNum_seeds());
        assertEquals(3, board.getLast_active().getBowls().get(1).getNum_seeds());
        assertEquals(0, board.getLast_active().getBowls().get(2).getNum_seeds());
        assertEquals(4, board.getLast_active().getBowls().get(3).getNum_seeds());
        assertEquals(4, board.getLast_active().getBowls().get(4).getNum_seeds());
        assertEquals(4, board.getLast_active().getBowls().get(5).getNum_seeds());

        assertEquals(0, board.getLast_active().getTray().getSeeds());

        //opponente (non attivo)
        assertEquals(3, board.getPlayers().get(1).getBowls().get(0).getNum_seeds());
        assertEquals(3, board.getPlayers().get(1).getBowls().get(1).getNum_seeds());
        assertEquals(3, board.getPlayers().get(1).getBowls().get(2).getNum_seeds());
        assertEquals(3, board.getPlayers().get(1).getBowls().get(3).getNum_seeds());
        assertEquals(3, board.getPlayers().get(1).getBowls().get(4).getNum_seeds());
        assertEquals(3, board.getPlayers().get(1).getBowls().get(5).getNum_seeds());

        assertEquals(0, board.getPlayers().get(1).getTray().getSeeds());





        //reimpostiamo il giocatore attivo come prima
        board.setActive(board.getPlayers().get(0));
        instance_bowl = board.getLast_active().getBowls().get(3);

        //board.move_seeds(instance_bowl);
        board.move_seeds(instance_bowl);

        //dopo la mossa appena compiuta mi aspetto questa situazione :
        //     3 3 3 3 3 4
        //    0           1
        //     3 3 0 0 5 5
        //verifichiamo

        //player attivo
        assertEquals(3, board.getLast_active().getBowls().get(0).getNum_seeds());
        assertEquals(3, board.getLast_active().getBowls().get(1).getNum_seeds());
        assertEquals(0, board.getLast_active().getBowls().get(2).getNum_seeds());
        assertEquals(0, board.getLast_active().getBowls().get(3).getNum_seeds());
        assertEquals(5, board.getLast_active().getBowls().get(4).getNum_seeds());
        assertEquals(5, board.getLast_active().getBowls().get(5).getNum_seeds());

        assertEquals(1, board.getLast_active().getTray().getSeeds());

        //opponente (non attivo)
        assertEquals(4, board.getPlayers().get(1).getBowls().get(0).getNum_seeds());
        assertEquals(3, board.getPlayers().get(1).getBowls().get(1).getNum_seeds());
        assertEquals(3, board.getPlayers().get(1).getBowls().get(2).getNum_seeds());
        assertEquals(3, board.getPlayers().get(1).getBowls().get(3).getNum_seeds());
        assertEquals(3, board.getPlayers().get(1).getBowls().get(4).getNum_seeds());
        assertEquals(3, board.getPlayers().get(1).getBowls().get(5).getNum_seeds());

        assertEquals(0, board.getPlayers().get(1).getTray().getSeeds());




        //reimpostiamo il giocatore attivo come prima, testiamo ora che non accada nulla se il bowl passato contiene ZERO semi
        board.setActive(board.getPlayers().get(0));
        instance_bowl = board.getLast_active().getBowls().get(3);

        //board.move_seeds(instance_bowl);
        board.move_seeds(instance_bowl);

        //dopo la mossa appena compiuta mi aspetto questa situazione :
        //     3 3 3 3 3 4
        //    0           1
        //     3 3 0 0 5 5
        //verifichiamo

        //player attivo
        assertEquals(3, board.getLast_active().getBowls().get(0).getNum_seeds());
        assertEquals(3, board.getLast_active().getBowls().get(1).getNum_seeds());
        assertEquals(0, board.getLast_active().getBowls().get(2).getNum_seeds());
        assertEquals(0, board.getLast_active().getBowls().get(3).getNum_seeds());
        assertEquals(5, board.getLast_active().getBowls().get(4).getNum_seeds());
        assertEquals(5, board.getLast_active().getBowls().get(5).getNum_seeds());

        assertEquals(1, board.getLast_active().getTray().getSeeds());

        //opponente (non attivo)
        assertEquals(4, board.getPlayers().get(1).getBowls().get(0).getNum_seeds());
        assertEquals(3, board.getPlayers().get(1).getBowls().get(1).getNum_seeds());
        assertEquals(3, board.getPlayers().get(1).getBowls().get(2).getNum_seeds());
        assertEquals(3, board.getPlayers().get(1).getBowls().get(3).getNum_seeds());
        assertEquals(3, board.getPlayers().get(1).getBowls().get(4).getNum_seeds());
        assertEquals(3, board.getPlayers().get(1).getBowls().get(5).getNum_seeds());

        assertEquals(0, board.getPlayers().get(1).getTray().getSeeds());


        //reimpostiamo tutto :
        board = new Board();

        instance_bowl = board.getLast_active().getBowls().get(3);

        //board.move_seeds(instance_bowl);
        board.move_seeds(instance_bowl);

        //dopo la mossa appena compiuta mi aspetto questa situazione :
        //     3 3 3 3 3 3
        //    0           1
        //     3 3 3 0 4 4
        //verifichiamo

        //player attivo
        assertEquals(3, board.getLast_active().getBowls().get(0).getNum_seeds());
        assertEquals(3, board.getLast_active().getBowls().get(1).getNum_seeds());
        assertEquals(3, board.getLast_active().getBowls().get(2).getNum_seeds());
        assertEquals(0, board.getLast_active().getBowls().get(3).getNum_seeds());
        assertEquals(4, board.getLast_active().getBowls().get(4).getNum_seeds());
        assertEquals(4, board.getLast_active().getBowls().get(5).getNum_seeds());

        assertEquals(1, board.getLast_active().getTray().getSeeds());

        //opponente (non attivo)
        assertEquals(3, board.getPlayers().get(1).getBowls().get(0).getNum_seeds());
        assertEquals(3, board.getPlayers().get(1).getBowls().get(1).getNum_seeds());
        assertEquals(3, board.getPlayers().get(1).getBowls().get(2).getNum_seeds());
        assertEquals(3, board.getPlayers().get(1).getBowls().get(3).getNum_seeds());
        assertEquals(3, board.getPlayers().get(1).getBowls().get(4).getNum_seeds());
        assertEquals(3, board.getPlayers().get(1).getBowls().get(5).getNum_seeds());

        assertEquals(0, board.getPlayers().get(1).getTray().getSeeds());


//test giro completo su 2 giocatori :
        if(Constant.number_player == 2) {
            //ricominciamo da una situazione pulita :
            board = new Board();
            //impostiamo questa condizione :
            //     3 3 3 3 3 3
            //    0           0
            //     3 3 3 3 3 8

            board.getPlayers().get(0).getBowls().get(5).setNum_seeds(8);
            instance_bowl = board.getLast_active().getBowls().get(5);

            board.move_seeds(instance_bowl);

            //eseguiamo la mossa e come risultato dobbiamo avere :
            //     4 4 4 4 4 4
            //    0           1
            //     4 3 3 3 3 0
            //verifichiamo :

            //player attivo
            assertEquals(4, board.getLast_active().getBowls().get(0).getNum_seeds());
            assertEquals(3, board.getLast_active().getBowls().get(1).getNum_seeds());
            assertEquals(3, board.getLast_active().getBowls().get(2).getNum_seeds());
            assertEquals(3, board.getLast_active().getBowls().get(3).getNum_seeds());
            assertEquals(3, board.getLast_active().getBowls().get(4).getNum_seeds());
            assertEquals(0, board.getLast_active().getBowls().get(5).getNum_seeds());

            assertEquals(1, board.getLast_active().getTray().getSeeds());

            //opponente (non attivo)
            assertEquals(4, board.getPlayers().get(1).getBowls().get(0).getNum_seeds());
            assertEquals(4, board.getPlayers().get(1).getBowls().get(1).getNum_seeds());
            assertEquals(4, board.getPlayers().get(1).getBowls().get(2).getNum_seeds());
            assertEquals(4, board.getPlayers().get(1).getBowls().get(3).getNum_seeds());
            assertEquals(4, board.getPlayers().get(1).getBowls().get(4).getNum_seeds());
            assertEquals(4, board.getPlayers().get(1).getBowls().get(5).getNum_seeds());

            assertEquals(0, board.getPlayers().get(1).getTray().getSeeds());
        }



//TEST avanzati

        //prova con numero di giocatori pari a 50, ovviamente andrebbero riviste le regole per rendere il gioco più bello
        if(Constant.number_player == 50 && Constant.num_bowls == 6) {
            //ricominciamo da una situazione pulita :
            board = new Board();
            //impostiamo questa condizione :
            //50 giocatori tutti con 3 semi nei vasi a parte un vaso del giocatore attivo con 18 semi
            board.getPlayers().get(0).getBowls().get(0).setNum_seeds(300);
            instance_bowl = board.getLast_active().getBowls().get(0);
            board.move_seeds(instance_bowl);

            //eseguiamo la mossa e come risultato dobbiamo avere :
            // 0 semi, 4 in ogni altra bowl e 1 nel nostro tray
            //verifichiamo :

            assertEquals(0, board.getLast_active().getBowls().get(0).getNum_seeds());
            assertEquals(1, board.getLast_active().getTray().getSeeds());

            for (int e = 1; e < Constant.number_player; e++) {
                assertEquals(0, board.getPlayers().get(e).getTray().getSeeds());
                for (int i = 0; i < Constant.num_bowls; i++) {
                    assertEquals(4, board.getPlayers().get(e).getBowls().get(i).getNum_seeds());
                }
            }
        }




    }

}