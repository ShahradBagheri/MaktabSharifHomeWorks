package model;

public enum Electronics implements Product{

    TV,
    RADIO;

    @Override
    public String getName() {
        return name();
    }
}