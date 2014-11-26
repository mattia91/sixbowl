package com.example.mattia.testproject.Model;

import java.util.ArrayList;

/**
 * Created by martino on 21/11/14.
 */
public class Board {

    //OGGETTI ISTANZIATI
    private ArrayList<Player> players = new ArrayList<Player>();
    private Player active;
    private Player last_active;
    private Player winner;
    private Rules rules = new Rules();
    private EndingRoutine game_over = new EndingRoutine();
    /*possibili valori della variabile end_of_game
      -1 gioco non concluso
      -2 gioco concluso in pareggio
      +n indice del giocatore nell'array che ha vinto
     */
    //TODO nella view viene letto end_of_game tramite il getter, se diverso da -1 la partita è terminata
    private int end_of_game = -1;
    /* la variabile game_mode indica la modalità di gioco,
        1 indica modalità standard, si possono definire nuove regole,
        usando altri numeri
    */
    private int game_mode = 1;

    //METODI DELLA CLASSE
    public Bowl move_seeds(Bowl bowl){
        //dichiariamo subito un oggetto bowl da ritornare
        Bowl return_bowl = new Bowl();
        //salviamo il numero di semi che ha il vaso passato, la posizione (come intero) del vaso che ho selezionato e del giocatore attivo
        int seeds = bowl.getNum_seeds();
        int bowls_pointer = active.getBowls().indexOf(bowl);
        int active_pointer = players.indexOf(active);

        //creo un oggetto giocatore che varia in base a dove mi trovo con la semina, serve per non mettere semi nel tray dell'avversario (eseguirò un controllo)
        Player filling_bowls = active;
        //azzero il numero di semi contenuto nel vaso di partenza
        filling_bowls.getBowls().get(bowls_pointer++).setNum_seeds(0);

        //parte il ciclo di semina
        for( int e = 0 ; e < seeds ; e++ ){
            if( bowls_pointer == Constant.num_bowls  && filling_bowls.equals(active)) {
                filling_bowls.increment_tray(1);
                active_pointer++;
                if (active_pointer > Constant.number_player - 1) {
                    active_pointer = 0;
                }
                filling_bowls = players.get(active_pointer);
                bowls_pointer = 0;
                return_bowl = null;
            }
            else if( bowls_pointer == Constant.num_bowls  && !filling_bowls.equals(active) ){
                return_bowl = filling_bowls.getBowls().get(bowls_pointer - 1);
                bowls_pointer = 0;
                active_pointer++;
                if(active_pointer > Constant.number_player - 1){
                    active_pointer = 0;
                }
                filling_bowls = players.get(active_pointer);
                filling_bowls.getBowls().get(bowls_pointer++).increment(1);
            }
            else{
                return_bowl = filling_bowls.getBowls().get(bowls_pointer);
                filling_bowls.getBowls().get(bowls_pointer++).increment(1);
            }
        }
        return return_bowl;
    }
    public boolean is_legal(Bowl bowl,Player player){
        if (bowl.getNum_seeds()==0) return false;
        if (!player.getBowls().contains(bowl)) return false;
        return true;
    }

    //COSTRUTTORI
    public Board(){
        for( int e = 0 ; e < Constant.number_player ; e++ ){
            players.add(new Player());
        }
        active = players.get(0);
        last_active = active;
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
    public void setLast_active(Player last_active) {
        this.last_active = last_active;
    }
    public Player getWinner() {
        return winner;
    }
    public void setWinner(Player winner) {
        this.winner = winner;
    }
    public EndingRoutine getGame_over() {
        return game_over;
    }
    public void setGame_over(EndingRoutine game_over) {
        this.game_over = game_over;
    }
    public int getEnd_of_game() {
        return end_of_game;
    }
    public void setEnd_of_game(int end_of_game) {
        this.end_of_game = end_of_game;
    }
    public Player getLast_active() {
        return last_active;
    }


}
