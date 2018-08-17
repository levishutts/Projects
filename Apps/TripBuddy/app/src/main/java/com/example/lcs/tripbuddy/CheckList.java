package com.example.lcs.tripbuddy;

import java.io.Serializable;

/**
 * Created by lcs on 7/19/2017.
 */

public class CheckList implements Serializable{
    private int MAX_NAMES = 20;
    private CheckName[] names;
    private int numNames = 0;

    public CheckList(){
        names = new CheckName[MAX_NAMES];
    }

    public CheckName[] getCheckList(){
        return names;
    }

    public void addName(String name){
        names[numNames] = new CheckName(name, true);
        numNames++;
    }
}
