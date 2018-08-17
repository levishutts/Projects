package com.example.lcs.tripbuddy;

import java.io.Serializable;

/**
 * Created by lcs on 7/18/2017.
 */

public class CheckName implements Serializable{
    String name;
    boolean checked;

    CheckName(String name, boolean checked){
        this.name = name;
        this.checked = checked;
    }

    public String getName(){
        return this.name;
    }

    public boolean isChecked(){
        return this.checked;
    }

    public void setChecked(boolean check){
        this.checked = check;
    }
}
