package com.example.lcs.tidepredictions;

/**
 * Created by lcs on 7/15/2017.
 */

public class TideItem {

    private String tideDate = null;
    private String day = null;
    private String time = null;
    private String tideFeet = null;
    private String tideCm = null;
    private String highLow = null;
    private String station = null;

    public TideItem(){

    }

    public TideItem(String station, String date, String day, String time, String pred_in_ft, String pred_in_cm, String highlow){
        this.tideDate = date;
        this.day = day;
        this.time = time;
        this.tideFeet = pred_in_ft;
        this.tideCm = pred_in_cm;
        this.highLow = highlow;
        this.station = station;
    }

    public void setDate(String tideDate) {
        this.tideDate = tideDate + " ";
    }

    public String getDate() {
        return tideDate;
    }

    public void setDay(String tideDay){this.day = tideDay;}

    public String getDay() {
        return day;
    }

    public void setTime(String tideTime){this.time = tideTime;}

    public String getTime(){return time;}

    public void setFeet(String tidePred) {this.tideFeet = tidePred;}

    public String getFeet(){return tideFeet;}

    public void setCm(String tidePred){
        float cm = Float.parseFloat(tidePred);
        //convert feet to cm
        cm /= .032808;
        this.tideCm = Float.toString(cm);
    }

    public String getCm(){return tideCm;}

    public void setHighLow(String tideHighLow){
        this.highLow = tideHighLow + ": ";
    }

    public String getHighLow(){return highLow;}

    public void setStation(String station){this.station = station;}

    public String getStation(){return station;}
}