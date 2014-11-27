package com.example.mattia.testproject.Model;
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
        //cambio del turno
        int active_pointer = players.indexOf(active);
        if( last_bowl != null ){
            if( active_pointer + 1 == Constant.number_player){
                active_pointer = 0;
            }
            else active_pointer++;
            active = players.get(active_pointer);
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
                int opponent_pointer = players.indexOf(active) + 1;
                //capiamo chi è il diretto avversario, il giocatore subito alla destra di quello attivo (di fronte nel caso di 2 giocatori)
                Player opponent;
                if( opponent_pointer == Constant.number_player){
                    opponent_pointer = 0;
                }
                opponent = players.get(opponent_pointer);
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
