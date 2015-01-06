package com.example.mattia.testproject.Model;

/**
 * Created by mattia on 17/11/2014.
 */
public class Tray implements Tray_interface {

    private int seeds;

    public Tray(int initial_content){
        this.seeds = initial_content;
    }

    public int getSeeds() {
        return seeds;
    }
    @Override
    public void increase_seeds_count(int amount) {
        seeds += amount;
    }
}
