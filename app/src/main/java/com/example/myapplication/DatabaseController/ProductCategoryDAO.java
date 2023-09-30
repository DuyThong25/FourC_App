package com.example.myapplication.DatabaseController;

import com.example.myapplication.DatabaseManager.DatabaseManager;
import com.example.myapplication.Model.ProductCategory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductCategoryDAO {
    Connection connection = DatabaseManager.getInstance().getConnection();

    public ArrayList<ProductCategory> getAllProductCategory() {
        ArrayList<ProductCategory> productCategories = new ArrayList<>();
        try {
            String sqlStatement =
                    "Select a.Id, a.Title " +
                            "From tb_ProductCategory a " +
                            "order by a.Title";
            Statement stm = connection.createStatement();
            ResultSet resultSet = stm.executeQuery(sqlStatement);
            while (resultSet.next()) {
                ProductCategory productCategory = new ProductCategory();
                productCategory.setProductCategoryID(resultSet.getInt(1));
                productCategory.setProductCategoryName(resultSet.getString(2));
                productCategories.add(productCategory);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return productCategories;
    }
}
