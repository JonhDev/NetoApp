package com.example.lista0404;

public class ShoppingCarInstance {

    private static ShoppingCarInstance instance;

    private int totalCount = 0;
    private int totalItems = 0;

    private ShoppingCarInstance() {}

    public static ShoppingCarInstance getShoppingCar() {
        if(instance == null) {
            instance = new ShoppingCarInstance();
        }
        return instance;
    }

    public void addPriceToTotal(int price) {
        totalCount += price;
        totalItems += 1;
    }

    public int getTotalAmount() {
        return totalCount;
    }

    public int getTotalItemsInShoppingCar() {
        return totalItems;
    }


}
