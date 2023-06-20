package model;

import base.model.BaseEntity;

import java.util.ArrayList;

public class ShoppingList extends BaseEntity<Integer> {
    private String name;
    private int amount;
    private int price;

    public ShoppingList(String name, int amount, int price) {
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

    public ShoppingList(Integer integer, String name, int amount, int price) {
        super(integer);
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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
