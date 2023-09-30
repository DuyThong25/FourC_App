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
                    "Select a.Id, a.Title, a.ProductCategoryID, a.Description, a.Detail, a.Price, a.PriceSale, a.ViewCount, b.Image " +
                            "From tb_Product a join tb_ProductImage b on a.Id = b.ProductID " +
                            "where b.IsDefault = 1;";
            Statement stm = connection.createStatement();
            ResultSet resultSet = stm.executeQuery(sqlStatement);
            while (resultSet.next()) {
                Product product = new Product();

                product.setIdProduct(resultSet.getInt(1));
                product.setTitleProduct(resultSet.getString(2));
                product.setIdProductCategory(resultSet.getInt(3));
                product.setDescriptionProduct(resultSet.getString(4));
                product.setDetailDescriptionProduct(resultSet.getString(5));
                product.setPriceProduct(resultSet.getDouble(6));
                product.setPriceSaleProduct(resultSet.getDouble(7));
                product.setViewCountProduct(resultSet.getInt(8));
                product.setImageProduct(resultSet.getString(9));

                productArrayList.add(product);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return productArrayList;
    }

//    public ArrayList<String> getAllImageProductDefault() {
//        ArrayList<String> productImageDefaultArrayList = new ArrayList<>();
//        try {
//            String sqlStatement =
//                            "Select b.Image " +
//                            "From tb_Product a join tb_ProductImage b on a.Id = b.ProductID " +
//                            "where b.IsDefault = 1;";
//            Statement stm = connection.createStatement();
//            ResultSet resultSet = stm.executeQuery(sqlStatement);
//            while (resultSet.next()) {
//                String url = resultSet.getString(1);
//                productImageDefaultArrayList.add(url);
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return productImageDefaultArrayList;
//    }
}
