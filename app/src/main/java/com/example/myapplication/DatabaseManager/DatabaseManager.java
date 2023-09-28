package com.example.myapplication.DatabaseManager;

import java.sql.Connection;

public class DatabaseManager {
    private static DatabaseManager instance; // dùng singleton
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
        connection = conSql.conclass();
    }

    public Connection getConnection() {
        return connection;
    }

}

