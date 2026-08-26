package com.jvm.mastery.module0.lab04;

public class ProductEntity extends BaseEntity<String> {

    private String productName;
    private double price;

    public ProductEntity(String skuCode, String productName, double price) {
        super(skuCode); // SKU code (String) đóng vai trò là ID
        this.productName = productName;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("Product[sku='%s', name='%s', price=%,.0f VND]", getId(), productName, price);
    }
}
