package com.ifpi.biblioteca.DAO;
import com.ifpi.biblioteca.conexao.Conexao;
import com.ifpi.biblioteca.entidades.Livro;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class LivroDB {
    public void cadastrarLivro(Livro livro){
        String sql = "INSERT INTO LIVROS ( TITULO, AUTOR, EMPRESTIMO, FALSE) VALUES (?, ?, ?, ?)";

        PreparedStatement ps = null;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, livro.getAutor());
            ps.setString(2, livro.getTitulo());
            ps.setString(3, livro.getIsbn()); 
            ps.setBoolean(4, livro.getEmprestimo());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removerLivro(String isbn){
        String sql = "DELETE FROM LIVROS WHERE ISBN = ? AND EMPRESTIMO = 1";
        PreparedStatement ps = null;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1,  isbn);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Ocorreu um erro ao remover o livro", e);
        }
    }

    public void listarLivros(){
        String sql = "SELECT * FROM LIVROS";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                System.out.println("\n---------------------------------");
                System.out.println("Autor: " + rs.getString("AUTOR"));
                System.out.println("Titulo: " + rs.getString("TITULO"));
                System.out.println("ISBN: " + rs.getString("ISBN"));
                System.out.println("Emprestimo: " + rs.getBoolean("EMPRESTIMO"));
                System.out.println("------------------------------------");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void emprestarLivro(String titulo, String matricula, Date dataEmprestimo){
        String sql = "INSERT INTO EMPRESTIMOS (TITULO_LIVRO, MATRICULA_USUARIO, DATA_EMPRESTIMO) VALUES (?, ?, ?)";
        String sql2 = "UPDATE LIVROS SET EMPRESTIMO = ? WHERE TITULO = ?";
        String sql3 = "INSERT INTO EMPRESTIMOS SET DATA_EMPRESTIMO = STR_TO_DATE(?, '%d/%m/%Y')";

        PreparedStatement ps = null;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, titulo);
            ps.setString(2, matricula);
            ps.setDate(3, new java.sql.Date(dataEmprestimo.getTime()));
            ps.execute();

            ps = Conexao.getConexao().prepareStatement(sql2);
            ps.setInt(1, 1);
            ps.setString(2, titulo);
            ps.execute();

            ps = Conexao.getConexao().prepareStatement(sql3);
            ps.setString(1, dataEmprestimo.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void devolverLivro(String titulo){
        String sql = "UPDATE LIVROS SET EMPRESTIMO = ? WHERE TITULO = ?";
        String sql2 = "DELETE FROM EMPRESTIMOS WHERE TITULO_LIVRO = ?";

        PreparedStatement ps = null;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, 0);
            ps.setString(2, titulo);
            ps.execute();

            ps = Conexao.getConexao().prepareStatement(sql2);
            ps.setString(1, titulo);
            ps.execute();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

}
