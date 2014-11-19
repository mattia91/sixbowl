package com.example.mattia.testproject.Controller;

import com.example.mattia.testproject.Model.Bowl;
import com.example.mattia.testproject.Model.Constant;
import com.example.mattia.testproject.Model.Player;

import java.util.ArrayList;

/**
 * Created by martino on 19/11/14.
 */

public class Rules {
//questa classe contiene le regole del gioco, nel caso il gioco voglia essere esteso le nuove regole vanno aggiunte qui.
/*regole della versione base :

- se l'ultimo seme della mia fase di semina finisce nel MIO tray, ho a disposizione un'altra mossa : keep_active()
- se l'ultimo seme della mia fase di semina finisce in un MIO vaso vuoto, tutti i semi contenuti nel vaso simmetrico
    a quello su detto, appartenente all'avversario, vanno nel MIO tray : steal_seeds()

 */

//METODI PUBBLICI DELLA CLASSE
    public Player check_rules(Bowl last_bowl, Player active, ArrayList<Player> players, int game_mode){
        if( game_mode == 1){
            //attenzione, l'ordine è questo perché altrimenti rubo a me stesso o a un poveretto poiché ho già cambiato il turno
            steal_seeds(last_bowl, active, players);
            return keep_active(last_bowl, active, players);
        }
        return active;
    }

//METODI PRIVATI DELLA CLASSE
//DEFINIZIONE DELLE REGOLE, UNA PER UNA
    private Player keep_active(Bowl last_bowl, Player active, ArrayList<Player> players){
        //cambio del turno, questa macchinazione con il ciclo for serve per generalizzare il numero di giocatori da 2 a quanti ne voglio
        if( last_bowl != null ){
            for( int i = 1 ; i < Constant.number_player ; i++ ){
                if( players.get(i-1).equals(active) ){
                    active = players.get(i);
                    //salta quello dopo/ esce dal ciclo se imposto l'ultimo come attivo
                    //i++;
                    return active;
                }
                else if( i == Constant.number_player - 1 && players.get(i).equals(active)){
                    active = players.get(0);
                    return active;
                }
            }
        }
        //quanto segue è INUTILE, serve solo per capire cosa succede nel gioco, se ricevo last_bowl = null : mi trovo nel mio tray quindi resto io attivo
        //if( last_bowl == null){
            //il player active resta active
        //}

        return active;
    }

    private void steal_seeds(Bowl last_bowl, Player active, ArrayList<Player> players){

        if( last_bowl != null ){
            //controllo se il vaso finale del ciclo di semina è un vaso del player attivo ed era vuoto in partenza
            if( active.getBowls().contains(last_bowl) && last_bowl.getNum_seeds() == 1) {
                //rubo tutti i semi dell'avversario nella bowl simmetrica
                int bowls_pointer = active.getBowls().indexOf(last_bowl);

                //capiamo chi è il diretto avversario, il giocatore subito alla destra di quello attivo (di fronte nel caso di 2 giocatori ma usando il for è estendibile)
                Player opponent = active;
                for (int i = 1; i < Constant.number_player; i++) {
                    if (players.get(i - 1).equals(active)) {
                        opponent = players.get(i);
                        //salta quello dopo/ esce dal ciclo se imposto l'ultimo come opponent
                        i++;
                    }
                    else if (i == Constant.number_player - 1 && players.get(i).equals(active)) {
                        opponent = players.get(0);
                    }
                }
                //aggiorno il mio tray
                active.increment_tray(opponent.getBowls().get(Constant.num_bowls - 1 - bowls_pointer).getNum_seeds());
                active.increment_tray(1);
                //imposto la bowl da cui ho rubato a zero semi e anche la mia a zero
                opponent.getBowls().get(Constant.num_bowls - 1 - bowls_pointer).setNum_seeds(0);
                last_bowl.setNum_seeds(0);
            }
        }
    }

}
