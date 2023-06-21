package model;

public enum Electronics {

    TV("TV"),
    Radio("Radio");
    private String stringValue;

    Electronics(String stringValue){
        this.stringValue = stringValue;
    }
}