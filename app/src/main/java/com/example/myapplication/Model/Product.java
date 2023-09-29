package com.example.myapplication.Model;

public class Product {
    private int idProduct;
    private String titleProduct;
    private int idProductCategory;
    private String descriptionProduct;
    private String detailDescriptionProduct;
    private String imageProduct;
    private Double priceProduct;
    private Double priceSaleProduct;
    private int viewCountProduct;

    public Product() {};
    public Product(int idProduct, String titleProduct, int idProductCategory, String descriptionProduct, String detailDescriptionProduct, String imageProduct, Double priceProduct, Double priceSaleProduct, int viewCountProduct) {
        this.idProduct = idProduct;
        this.titleProduct = titleProduct;
        this.idProductCategory = idProductCategory;
        this.descriptionProduct = descriptionProduct;
        this.detailDescriptionProduct = detailDescriptionProduct;
        this.imageProduct = imageProduct;
        this.priceProduct = priceProduct;
        this.priceSaleProduct = priceSaleProduct;
        this.viewCountProduct = viewCountProduct;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getTitleProduct() {
        return titleProduct;
    }

    public void setTitleProduct(String titleProduct) {
        this.titleProduct = titleProduct;
    }

    public int getIdProductCategory() {
        return idProductCategory;
    }

    public void setIdProductCategory(int idProductCategory) {
        this.idProductCategory = idProductCategory;
    }

    public String getDescriptionProduct() {
        return descriptionProduct;
    }

    public void setDescriptionProduct(String descriptionProduct) {
        this.descriptionProduct = descriptionProduct;
    }

    public String getDetailDescriptionProduct() {
        return detailDescriptionProduct;
    }

    public void setDetailDescriptionProduct(String detailDescriptionProduct) {
        this.detailDescriptionProduct = detailDescriptionProduct;
    }

    public String getImageProduct() {
        return imageProduct;
    }

    public void setImageProduct(String imageProduct) {
        this.imageProduct = imageProduct;
    }

    public Double getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(Double priceProduct) {
        this.priceProduct = priceProduct;
    }

    public Double getPriceSaleProduct() {
        return priceSaleProduct;
    }

    public void setPriceSaleProduct(Double priceSaleProduct) {
        this.priceSaleProduct = priceSaleProduct;
    }

    public int getViewCountProduct() {
        return viewCountProduct;
    }

    public void setViewCountProduct(int viewCountProduct) {
        this.viewCountProduct = viewCountProduct;
    }
}
