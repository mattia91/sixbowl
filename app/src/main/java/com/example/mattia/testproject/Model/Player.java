package com.example.mattia.testproject.Model;

import java.util.ArrayList;

/**
 * Created by mattia on 17/11/2014.
 */
public class Player {

    private ArrayList<Bowl> bowls= new ArrayList<Bowl>();
    private Tray tray;

   public Player(){

       for(int i=0;i<Constant.num_bowls;i++) {
          bowls.add(new Bowl());
       }
       tray=new Tray();
   }

    public ArrayList<Bowl> getBowls() {
        return bowls;
    }

    public void setBowls(ArrayList<Bowl> bowls) {
        this.bowls = bowls;
    }

    public Tray getTray() {
        return tray;
    }

    public void setTray(Tray tray) {
        this.tray = tray;
    }

    public void increment_tray(int amount){
        this.tray.setSeeds(this.tray.getSeeds()+amount);
    }
}
