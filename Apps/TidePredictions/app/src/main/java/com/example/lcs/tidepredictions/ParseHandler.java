package com.example.lcs.tidepredictions;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * Created by lcs on 7/15/2017.
 */

public class ParseHandler extends DefaultHandler {
    private ArrayList<TideItem> tidelist;
    private TideItem isItem;
    String station;
    private String currentDate = null;
    private boolean isDate = false;
    private boolean isDay = false;
    private boolean isTime = false;
    private boolean isPredFt = false;
    private boolean isPredCm = false;
    private boolean isHighLow = false;

    public ParseHandler(String location){
        station = location;
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        tidelist = new ArrayList<TideItem>();
        isItem = new TideItem();
        isItem.setStation(station);
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        super.startElement(namespaceURI, localName, qName, atts);
        if(qName.equals("item")){
            if(atts!= null && atts.getLength() == 1){
                currentDate = atts.getValue(0);
            }
        }
        else if(qName.equals("data")){
            isItem = new TideItem();
            isItem.setStation(station);
            isItem.setDate(currentDate);
        }
        else if(qName.equals("date")){
            isDate = true;
        }
        else if(qName.equals("day")){
            isDay = true;
        }
        else if(qName.equals("time")){
            isTime = true;
        }
        else if(qName.equals("pred")){
            isPredFt = true;
            isPredCm = true;
        }
        else if (qName.equals("type")){
            isHighLow = true;
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        super.endElement(namespaceURI, localName, qName);
        if(qName.equals("data")){
            tidelist.add(isItem);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        String s = new String(ch, start, length);
        if(isDate){
            isItem.setDate(s);
            isDate = false;
        }
        else if(isDay){
            isItem.setDay(s);
            isDay = false;
        }
        else if(isTime){
            isItem.setTime(s);
            isTime = false;
        }
        else if(isPredFt){
            isItem.setFeet(s);
            isPredFt = false;
        }
        else if(isPredCm){
            isItem.setCm(s);
            isPredCm = false;
        }
        else if(isHighLow){
            isItem.setHighLow(s);
            isHighLow = false;
        }
    }

    public ArrayList<TideItem> getTideList() {
        return (tidelist);
    }

}
