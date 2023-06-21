package model;

public enum Shoe {
    FORMAL("Formal"),
    SPORT("Sport");
    private String stringValue;

    Shoe(String stringValue){
        this.stringValue = stringValue;
    }
}
