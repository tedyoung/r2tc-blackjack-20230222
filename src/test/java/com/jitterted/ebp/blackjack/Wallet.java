package com.jitterted.ebp.blackjack;

public class Wallet {

    private boolean isEmpty;

    public Wallet() {
        isEmpty = true;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void addMoney(int amount) {
        isEmpty = false;
    }

    public int balance() {
        return 0;
    }
}
