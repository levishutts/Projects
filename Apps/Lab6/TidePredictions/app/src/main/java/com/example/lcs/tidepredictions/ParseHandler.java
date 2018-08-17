package com.example.lcs.tidepredictions;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * Created by lcs on 7/15/2017.
 */

public class ParseHandler extends DefaultHandler {
    private TideItems tideItems;
    private TideItem isItem;
    private boolean isDate = false;
    private boolean isDay = false;
    private boolean isTime = false;
    private boolean isPred = false;
    private boolean isHighLow = false;
    private String station;
    private ArrayList<TideItem> tideList;

    public TideItems getItems() {
        return tideItems;
    }

    public ParseHandler(Stations tideStation){
        switch (tideStation){
            case TILLAMOOK:
                station = "Tillamook";
                break;
            case EMPIRE:
                station = "Empire";
                break;
            case FLORENCE:
                station = "Florence";
                break;
        }
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();

        tideList = new ArrayList<>();
        isItem = new TideItem();
        isItem.setStation(station);
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startElement(String namespaceURI, String localName,
                             String qName, Attributes atts) throws SAXException {

        if (qName.equals("item")) {
            isItem = new TideItem();
            isItem.setStation(station);
        }
        else if (qName.equals("date")) {
            isDate = true;
        }
        else if (qName.equals("day")) {
            isDay = true;
        }
        else if (qName.equals("time")) {
            isTime = true;
        }
        else if (qName.equals("pred")) {
            isPred = true;
        }
        else if (qName.equals("highlow")){
            isHighLow = true;
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName,
                           String qName) throws SAXException
    {
        if (qName.equals("item")) {
            tideItems.add(isItem);
        }
    }

    @Override
    public void characters(char ch[], int start, int length)
    {
        String s = new String(ch, start, length);
        if (isDate) {
            isItem.setDate(s);
            isDate = false;
        }
        else if (isDay) {
            isItem.setDay(s);
            isDay = false;
        }
        else if (isTime) {
            isItem.setTime(s);
            isTime = false;
        }
        else if (isPred) {
            isItem.setFeet(s);
            isItem.setCm(s);
            isPred = false;
        }
        else if (isHighLow) {
            isItem.setHighLow(s);
            isHighLow = false;
        }
    }

    public ArrayList<TideItem> getTideList() {
        return (tideList);
    }
}