package model;

import base.model.BaseEntity;

import java.util.ArrayList;

public class ShoppingList extends BaseEntity<Integer> {
    private String name;
    private int amount;

    public ShoppingList(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public ShoppingList(Integer integer, String name, int amount) {
        super(integer);
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
