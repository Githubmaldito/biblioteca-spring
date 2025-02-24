package com.ifpi.biblioteca.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ifpi.biblioteca.conexao.Conexao;
import com.ifpi.biblioteca.entidades.Usuario;

public class UserDB {
    public void cadastrarUsuario(Usuario usuario){
        String sql = "INSERT INTO USUARIOS (NOME, MATRICULA, EMAIL) VALUES (?, ?, ?)";

        PreparedStatement ps = null;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getMatricula());
            ps.setString(3, usuario.getEmail());
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listarUsuarios() {
        // Obtém a conexão com o banco de dados usando a classe Conexao
        try (Connection conn = Conexao.getConexao();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios")) {


            System.out.println("\n--- Lista de Usuários ---");

            //enquanto houver resultados na query
            while (rs.next()) {
            //pega as colunas do banco de dados
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String matricula = rs.getString("matricula");

                //e os exibe no terminal
                System.out.println("Nome: " + nome);
                System.out.println("E-mail: " + email);
                System.out.println("Matrícula: " + matricula);
                System.out.println("-----------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao listar usuários: " + e.getMessage());
        }
    }

        public void historicoEmprestimos(String matricula){
            try (
                Connection conn = Conexao.getConexao();
                Statement stmt = conn.createStatement();
            //ao invés de usar um preparedtatement, uso a matricula direto na query
                ResultSet rs = stmt.executeQuery("SELECT * FROM EMPRESTIMOS WHERE MATRICULA_USUARIO = '" + matricula + "'")) {

                System.out.println("\n---Empréstimos do usuário:---");
                while (rs.next()) {
                    String titulo = rs.getString("titulo_livro");
                    String matriculaUsuario = rs.getString("matricula_usuario");

                    System.out.println("Título: " + titulo);
                    System.out.println("Matrícula: " + matriculaUsuario);
                    System.out.println("-----------------------------");
                }
                
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Ocorreu um erro ao listar os empréstimos do usuário." + e.getMessage());
            }
        }
}
