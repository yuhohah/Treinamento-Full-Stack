package org.example.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String USUARIO = "postgres";
    private static final String SENHA = "123456";
    private static final String URL = "jdbc:postgresql://localhost:5432/Treinamento";
    private static final String DRIVER_CLASS = "com.postgresql.jdbc.Driver";

    private static Connection connection = null;

    private ConnectionFactory(){
        try {
            Class.forName(DRIVER_CLASS);
        }catch (ClassNotFoundException exception){
            exception.getMessage();
        }
    }

    public static Connection getConnection(){
        if(connection == null){
            try {
                connection = DriverManager.getConnection(URL, USUARIO, SENHA);
            }catch (SQLException exception){
                exception.getMessage();
            }
        }
        return connection;
    }
}
