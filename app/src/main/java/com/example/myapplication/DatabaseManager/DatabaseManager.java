package com.example.myapplication.DatabaseManager;

import java.sql.Connection;

public class DatabaseManager {
    private static DatabaseManager instance; // dung singleton
    private Connection connection;
    private ConSql conSql = new ConSql();

    public DatabaseManager() {
        openConnection();
    }

    public static synchronized DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public void openConnection() {
        connection = conSql.ConClass();
    }

    public Connection getConnection() {
        return connection;
    }

}

