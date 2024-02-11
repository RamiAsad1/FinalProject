package com.example.project.Domain;

public class CartItemDomain {
    private String name;
    private String price;
    private int quantity;

    public CartItemDomain(String name, String price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void incrementQuantity() {
        quantity++;
    }

    public void decrementQuantity() {
        if (quantity > 1) {
            quantity--;
        }
    }
}
