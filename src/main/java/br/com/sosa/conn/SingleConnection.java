package br.com.sosa.conn;

import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
    private static String url = "jdbc:postgresql://localhost:5432/posjava";
    private static String pass = "admin";
    private static String user = "postgres";
    private static Connection connection = null;

    static {
        conn();
    }

    public SingleConnection() {
        conn();
    }

    private static void conn() {
        try {
            if (connection == null) {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(url, user, pass);
                connection.setAutoCommit(false);
                System.out.println("Conectou com Sucesso!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
