package com.example.myapplication.DatabaseController;

import com.example.myapplication.DatabaseManager.DatabaseManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDAO {
    Connection connection = DatabaseManager.getInstance().getConnection();

    public List<String> getAllProductCategory() {
        List<String> nameCategory = new ArrayList<>();
        try{
                String sqlStatement = "Select * From tb_ProductCategory;";
                Statement stm = connection.createStatement();
                ResultSet resultSet = stm.executeQuery(sqlStatement);
                while(resultSet.next()) {
                    nameCategory.add(resultSet.getString(2));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        return nameCategory;
    }
}
