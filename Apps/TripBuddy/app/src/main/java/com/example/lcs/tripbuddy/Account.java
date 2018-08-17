package com.example.lcs.tripbuddy;

import java.io.Serializable;

/**
 * Created by lcs on 7/20/2017.
 */

public class Account implements Serializable{
    private String name;
    private float amount;

    Account(String n){
        this.name = n;
        this.amount = 0;
    }

    public void setName(String n){
        this.name = n;
    }

    public String getName(){
        return this.name;
    }

    public void addAmount(float n){
        this.amount += n;
    }

    public void subAmount(float n){
        this.amount -= n;
    }

    public void setAmount(float n){this.amount = n;}

    public float getAmount(){
        return this.amount;
    }
}
