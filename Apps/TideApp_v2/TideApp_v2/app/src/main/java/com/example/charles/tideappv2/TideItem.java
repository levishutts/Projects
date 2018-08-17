package com.example.charles.tideappv2;

/**
 * Created by Charles on 7/17/2017.
 */

public class TideItem {
    private String station;
    private String date;
    private String day;
    private String time;
    private String pred_in_ft;
    private String pred_in_cm;
    private String highlow;

    public TideItem(){

    }

    public TideItem(String station, String date, String day, String time, String pred_in_ft, String pred_in_cm, String highlow){
        this.station = station;
        this.date = date;
        this.day = day;
        this.time = time;
        this.pred_in_ft = pred_in_ft;
        this.pred_in_cm = pred_in_cm;
        this.highlow = highlow;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPred_in_ft(String pred_in_ft) {
        this.pred_in_ft = pred_in_ft;
    }

    public void setPred_in_cm(String pred_in_cm) {
        this.pred_in_cm = pred_in_cm;
    }

    public void setHighlow(String highlow) {
        this.highlow = highlow;
    }

    public String getStation() {
        return station;
    }

    public String getDate() {
        return date;
    }

    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }

    public String getPred_in_ft() {
        return pred_in_ft;
    }

    public String getPred_in_cm() {
        return pred_in_cm;
    }

    public String getHighlow() {
        return highlow;
    }
    public String getDateFormatted(){
        String day = date.substring(date.length()-2);
        String month = date.substring(4,6);
        String year = date.substring(0,4);
        return (month + "/" + day + "/" + year);
    }
    public String getDayDate(){
        return(getDateFormatted()+" " + getDayFormatted());
    }
    public String getDayFormatted() {
        if (day==null){return "";}
        if (day.equals("Sun")) {
            return ("Sunday");
        } else if (day.equals("Mon")) {
            return ("Monday");
        } else if (day.equals("Tue")) {
            return ("Tuesday");
        } else if (day.equals("Wed")) {
            return ("Wednesday");
        } else if (day.equals("Thu")) {
            return ("Thursday");
        } else if (day.equals("Fri")) {
            return ("Friday");
        } else if (day.equals("Sat")) {
            return ("Saturday");
        } else {
            return ("");
        }
    }
    public String getTideFormatted(){
        if(highlow.equals("H")){
            return("High");
        }else if (highlow.equals("L")){
            return("Low");
        }else{
            return(highlow);
        }
    }
    public String getTideTime(){
        return(getTideFormatted()+": "+getTime());
    }
    public String getMeasurements(){
        return(pred_in_ft + ", " + pred_in_cm);
    }
}
