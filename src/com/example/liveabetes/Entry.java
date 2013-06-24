package com.example.liveabetes;

public class Entry {
    
    //private variables
    int _id;
    String _timecode;
    String _value;
     
    // Empty constructor
    public Entry(){
         
    }
    // constructor
    public Entry(int id, String timecode, String _value){
        this._id = id;
        this._timecode = timecode;
        this._value = _value;
    }
     
    // constructor
    public Entry(String timecode, String _value){
        this._timecode = timecode;
        this._value = _value;
    }
    // getting ID
    public int getID(){
        return this._id;
    }
     
    // setting id
    public void setID(int id){
        this._id = id;
    }
     
    // getting timecode
    public String getTimecode(){
        return this._timecode;
    }
     
    // setting timecode
    public void setTimecode(String timecode){
        this._timecode = timecode;
    }
     
    // getting phone number
    public String getValue(){
        return this._value;
    }
     
    // setting phone number
    public void setValue(String value){
        this._value = value;
    }
}