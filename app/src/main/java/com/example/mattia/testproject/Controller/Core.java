package com.example.mattia.testproject.Controller;

import com.example.mattia.testproject.Model.Bowl;
import com.example.mattia.testproject.Model.Constant;
import com.example.mattia.testproject.Model.Player;


/**
 * Created by mattia on 17/11/2014.
 */
public class Core {

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public Player getActive() {
        return active;
    }

    public void setActive(Player active) {
        this.active = active;
    }

    private Player players[];
    private Player active;

    public Core(){
        players = new Player[Constant.number_player];
        active = players[0];
    }

    public Bowl user_action(Bowl bowl){
        if( is_legal( bowl, active ) ){
            Bowl last_bowl = move_seeds(bowl);
            check_rules(last_bowl);
        }
        return null;
    }

    private Bowl move_seeds(Bowl bowl){

        //dichiariamo subito un oggetto bowl da ritornare
        Bowl return_bowl = null;
        //salviamo il numero di semi che ha il vaso passato
        int seeds = bowl.getNum_seeds();
        //azzeriamo il numero di semi nel vaso toccato, come da regole
        bowl.setNum_seeds(0);
        //salvo la posizione (come intero) del vaso che ho toccato sullo schermo
        int bowls_pointer = 0;
        for ( Bowl my_bowl : active.getBowls() ){
            if( my_bowl.equals(bowl) ){
                bowls_pointer = active.getBowls().indexOf(my_bowl);
            }
        }
        //creo un oggetto giocatore che varia in base a dove mi trovo con la semina, serve per non mettere semi nel tray dell'avversario (eseguirò un controllo)
        Player filling_bowls = active;
        //parte il ciclo di semina
        for( int e = 0 ; e < seeds ; e++ ){
            //condizione al contorno, player attivo
            if( ++bowls_pointer == Constant.num_bowls && filling_bowls.equals(active) ){
                bowls_pointer = 0;
                active.increment_tray(1);

                //determino chi è inattivo, mi serve per assegnare alla variabile filling_bowls il giocatore/i in uso
                Player opponent = new Player();
                for( int i = 0 ; i < Constant.number_player ; i++ ){
                    if( players[i].equals(active) && i + 1 < Constant.number_player){
                        opponent = players[i+1];
                    }
                    else{
                        opponent = players[0];
                    }
                }
                filling_bowls = opponent;
                //entrando in questo if mi trovo ad essere nel mio tray. Voglio che venga passato null.
                return_bowl = null;
            }
            //condizione al contorno, player non attivo
            else if( bowls_pointer == Constant.num_bowls && !filling_bowls.equals(active)){
                bowls_pointer = 0;
                //metto un seme nel vaso corrente
                filling_bowls.getBowls().get(bowls_pointer).increment(1);
                filling_bowls = active;
                return_bowl = filling_bowls.getBowls().get(bowls_pointer);
            }
            else {
                //metto un seme nel vaso corrente
                filling_bowls.getBowls().get(bowls_pointer).increment(1);
                return_bowl = filling_bowls.getBowls().get(bowls_pointer);
            }
        }
        return return_bowl;
    }

    private void check_rules(Bowl last_bowl){
        if(last_bowl != null){
            //salvo la posizione (come intero) del vaso che ho toccato sullo schermo
            int bowls_pointer = 0;
            for ( Bowl my_bowl : active.getBowls() ){
                if( my_bowl.equals(last_bowl) ){
                    bowls_pointer = active.getBowls().indexOf(my_bowl);
                }
            }
            if(!active.getBowls().contains(last_bowl)){
                //se finisco in una MIA bowl vuota, rubo tutti i semi dell'avversario nella bowl simmetrica
                if(last_bowl.getNum_seeds() == 1){
                    //determino chi è inattivo
                    Player opponent = new Player();
                    for( int i = 0 ; i < Constant.number_player ; i++ ){
                        if( players[i].equals(active) && i + 1 < Constant.number_player){
                            opponent = players[i+1];
                        }
                        else{
                            opponent = players[0];
                        }
                    }
                    //aggiorno il mio tray
                    active.increment_tray( opponent.getBowls().get(Constant.num_bowls - 1 - bowls_pointer).getNum_seeds() );
                    //imposto la bowl da cui ho rubato a zero semi
                    opponent.getBowls().get(Constant.num_bowls - 1 - bowls_pointer).setNum_seeds(0);
                }
            }
        }
    }

    private boolean is_legal(Bowl bowl,Player player){
        if (bowl.getNum_seeds()==0) return false;
        if (!player.getBowls().contains(bowl)) return false;
        return true;
    }

    public boolean end(){
        //TODO controlla i vasi del giocatore attivo, se sono vuoti procedura di fine

        //aggiorna le statistiche

        return false;
    }
}
