package com.example.charles.tideappv2;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * Created by Charles on 7/17/2017.
 */

public class TideHandler extends DefaultHandler {
    private ArrayList<TideItem> tidelist;
    private TideItem tide;
    String tideStation;
    private boolean isDate = false;
    private boolean isDay = false;
    private boolean isTime = false;
    private boolean isPredFt = false;
    private boolean isPredCm = false;
    private boolean isHighLow = false;

    public TideHandler(Stations station){
        switch (station){
            case ELKHORN:
                tideStation = "Elkhorn";
                break;
            case MONTEREY:
                tideStation = "Monterey";
                break;
            case SANTABARBARA:
                tideStation = "Santa Barbara";
                break;
        }
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        tidelist = new ArrayList<TideItem>();
        tide = new TideItem();
        tide.setStation(tideStation);

    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if(qName.equals("item")){
            tide = new TideItem();
            tide.setStation(tideStation);
            return;
        }else if(qName.equals("date")){
            isDate = true;
            return;
        }else if(qName.equals("day")){
            isDay = true;
            return;
        }else if(qName.equals("time")){
            isTime = true;
            return;
        }else if(qName.equals("pred_in_ft")){
            isPredFt = true;
            return;
        }else if(qName.equals("pred_in_cm")){
            isPredCm = true;
            return;
        }else if (qName.equals("highlow")){
            isHighLow = true;
            return;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if(qName.equals("item")){

            tidelist.add(tide);
            return;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        String s = new String(ch, start, length);
        if(isDate){
            String date = s.replace("/", "");
            tide.setDate(date);
            isDate = false;
        }else if(isDay){
            tide.setDay(s);
            isDay = false;
        }else if(isTime){
            tide.setTime(s);
            isTime = false;
        }else if(isPredFt){
            tide.setPred_in_ft(s);
            isPredFt = false;
        }else if(isPredCm){
            tide.setPred_in_cm(s);
            isPredCm = false;
        }else if(isHighLow){
            tide.setHighlow(s);
            isHighLow = false;
        }
    }

    public ArrayList<TideItem> getTidelist() {
        return (tidelist);
    }

}
