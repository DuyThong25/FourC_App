package com.example.myapplication.Model;

public class ProductCategory {
    private int productCategoryID;
    private String productCategoryName;

    public ProductCategory() {
    }

    public ProductCategory(int productCategoryID, String productCategoryName) {
        this.productCategoryID = productCategoryID;
        this.productCategoryName = productCategoryName;
    }

    public int getProductCategoryID() {
        return productCategoryID;
    }

    public void setProductCategoryID(int productCategoryID) {
        this.productCategoryID = productCategoryID;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

}
