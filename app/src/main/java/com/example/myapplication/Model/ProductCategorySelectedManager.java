package com.example.myapplication.Model;

public class ProductCategorySelectedManager {
    private static ProductCategorySelectedManager instance;
    private int selectedCategoryId = -1;

    // Private constructor để ngăn việc tạo đối tượng từ bên ngoài
    private ProductCategorySelectedManager() {
    }

    public static synchronized ProductCategorySelectedManager getInstance() {
        if (instance == null) {
            instance = new ProductCategorySelectedManager();
        }
        return instance;
    }

    public int getSelectedCategoryId() {
        return selectedCategoryId;
    }

    public void setSelectedCategoryId(int categoryId) {
        selectedCategoryId = categoryId;
    }
}
