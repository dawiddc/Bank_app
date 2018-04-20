package com.example.java.operations;

import com.example.java.interests.AccountInterestState;
import com.example.java.products.Product;

public class CountInterests implements Operation {

    private final AccountInterestState state;
    private final Product product;

    public CountInterests(AccountInterestState state, Product product) {
        this.state = state;
        this.product = product;
    }

    @Override
    public void execute() {
        state.countInterests(product);
    }
}
