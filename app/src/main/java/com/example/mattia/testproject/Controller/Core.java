package com.example.mattia.testproject.Controller;

import com.example.mattia.testproject.Model.Bowl;
import com.example.mattia.testproject.Model.Constant;
import com.example.mattia.testproject.Model.Player;

import java.util.ArrayList;


/**
 * Created by mattia on 17/11/2014.
 */
public class Core {

    private ArrayList<Player> players = new ArrayList<Player>();
    private Player active;
    private Rules rules = new Rules();



    //modalità di gioco, 1 indica modalità standard, si possono definire nuove regole, useremo altri numeri
    private int game_mode = 1;


    public Core(){
        //players = new Player[Constant.number_player];

        for( int e = 0 ; e < Constant.number_player ; e++ ){
            players.add(new Player());
        }

        active = players.get(0);
    }

    public Bowl user_action(Bowl bowl){
        if( is_legal( bowl, active ) ){
            Bowl last_bowl = move_seeds(bowl);
            active = rules.check_rules(last_bowl, active, players, game_mode);
        }
        return null;
    }

    public Bowl move_seeds(Bowl bowl){

        //dichiariamo subito un oggetto bowl da ritornare
        Bowl return_bowl = null;

        //salviamo il numero di semi che ha il vaso passato
        int seeds = bowl.getNum_seeds();
        //azzeriamo il numero di semi nel vaso toccato, come da regole
        bowl.setNum_seeds(0);
        //salvo la posizione (come intero) del vaso che ho selezionato
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

            bowls_pointer++;

            //condizione al contorno, player attivo
            if( bowls_pointer  == Constant.num_bowls && filling_bowls.equals(active) ){
                bowls_pointer = -1;//questo perché voglio che alla prossima iterazione del ciclo, lui parta dal vaso numro zero!! (nota che all'inizio di ogni tierazione incremento bowlspointer)
                active.increment_tray(1);

                //determino chi è inattivo, mi serve per assegnare alla variabile filling_bowls il giocatore/i in uso
                Player opponent = active;
                for( int i = 1 ; i < Constant.number_player ; i++ ){
                    if( players.get(i - 1).equals(active)){
                        if( i == Constant.number_player ){
                            opponent = players.get(0);
                        }
                        else{
                            opponent = players.get(i);
                        }
                    }
                }
                filling_bowls = opponent;

                //entrando in questo if mi trovo ad essere nel mio tray. Voglio che venga passato null.
                return_bowl = null;
            }
            //condizione al contorno, player non attivo
            else if( bowls_pointer  == Constant.num_bowls && !filling_bowls.equals(active)){
                bowls_pointer = 0;
                //metto un seme nel vaso corrente, lo 0 mio, poiché ho saltato il tray dell'opponente come da regolamento
                filling_bowls = active;
                filling_bowls.getBowls().get(bowls_pointer).increment(1);
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


//GETTERS AND SETTERS
    public ArrayList<Player> getPlayers() {
    return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
    public Player getActive() {
        return active;
    }

    public void setActive(Player active) {
        this.active = active;
    }
    public Rules getRules() {
        return rules;
    }

    public void setRules(Rules rules) {
        this.rules = rules;
    }

    public int getGame_mode() {
        return game_mode;
    }

    public void setGame_mode(int game_mode) {
        this.game_mode = game_mode;
    }
}
