package com.example.myapplication.DatabaseController;

import com.example.myapplication.DatabaseManager.DatabaseManager;
import com.example.myapplication.Model.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

public class ProductDAO {
    Connection connection = DatabaseManager.getInstance().getConnection();

    public ArrayList<Product> getAllProduct() {
        ArrayList<Product> productArrayList = new ArrayList<>();
        try {
            String sqlStatement =
                    "Select a.Id, a.Title, a.ProductCategoryID, a.Description, a.Detail, a.Image, a.Price, a.PriceSale, a.ViewCount " +
                    "From tb_Product a;";
            Statement stm = connection.createStatement();
            ResultSet resultSet = stm.executeQuery(sqlStatement);
            while (resultSet.next()) {
                Product product = new Product();

                product.setIdProduct(resultSet.getInt(1));
                product.setTitleProduct(resultSet.getString(2));
                product.setIdProductCategory(resultSet.getInt(3));
                product.setDescriptionProduct(resultSet.getString(4));
                product.setDetailDescriptionProduct(resultSet.getString(5));
                product.setImageProduct(resultSet.getString(6));
                product.setPriceProduct(resultSet.getDouble(7));
                product.setPriceSaleProduct(resultSet.getDouble(8));
                product.setViewCountProduct(resultSet.getInt(9));

                productArrayList.add(product);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return productArrayList;
    }
}
