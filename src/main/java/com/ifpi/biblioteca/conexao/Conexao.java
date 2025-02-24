package com.ifpi.biblioteca.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/banco";
    private static final String user = "root";
    private static final String password = "senha123";

    private static Connection conn;

    public static Connection getConexao() {
        try {
            return DriverManager.getConnection(url, user, password);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao conectar com o banco de dados", e);        
            //
        }
    }
}
