package model;

public enum Electronics {

    TV("TV"),
    RADIO("Radio");
    private String stringValue;

    Electronics(String stringValue){
        this.stringValue = stringValue;
    }
}