package model;

public enum Shoe implements Product{
    FORMAL,
    SPORT;

    @Override
    public String getName() {
        return name();
    }
}
