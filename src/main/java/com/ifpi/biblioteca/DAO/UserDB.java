package com.ifpi.biblioteca.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.ToDoubleBiFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ifpi.biblioteca.conexao.Conexao;
import com.ifpi.biblioteca.entidades.EmailUtil;
import com.ifpi.biblioteca.entidades.Usuario;

@Repository
public class UserDB {
    
    // public void cadastrarUsuario(Usuario usuario){
    //     String sql = "INSERT INTO USUARIOS (NOME, MATRICULA, EMAIL) VALUES (?, ?, ?)";

    //     PreparedStatement ps = null;
    //     try {
    //         ps = Conexao.getConexao().prepareStatement(sql);
    //         ps.setString(1, usuario.getNome());
    //         ps.setString(2, usuario.getMatricula());
    //         ps.setString(3, usuario.getEmail());
    //         ps.execute();

    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }

        
    // }

    public void cadastrarUsuario(Usuario usuario) {
        //String sql = "INSERT INTO USUARIOS (NOME, MATRICULA, EMAIL) VALUES (?, ?, ?)";

        // try (Connection conn = Conexao.getConexao();
        //      PreparedStatement ps = conn.prepareStatement(sql)) {

        //     ps.setString(1, usuario.getNome());
        //     ps.setString(2, usuario.getMatricula());
        //     ps.setString(3, usuario.getEmail());
        //     ps.execute();
        String sql = "INSERT INTO usuarios (nome, email, matricula) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getMatricula());
            stmt.executeUpdate();

            System.out.println("Usuário cadastrado com sucesso!");

            //envia um e-mail de confirmação
            //deve ser um endereço de email válido
            String assunto = "Bem-vindo ao Sistema de Biblioteca";
            String corpo = "Olá, " + usuario.getNome() + "!\n\n"
                    + "Seu cadastro foi realizado com sucesso.\n"
                    + "Agradecemos por se juntar a nós!\n\n"
                    + "Atenciosamente,\nEquipe da Biblioteca";

            EmailUtil.enviarEmail(usuario.getEmail(), assunto, corpo);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarUsuario(String matricula) {
        String sql = "DELETE FROM USUARIOS WHERE MATRICULA = ?";
        String nomeUsuario = "";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement("SELECT NOME FROM USUARIOS WHERE MATRICULA = ?")) {

            ps.setString(1, matricula);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    nomeUsuario = rs.getString("nome");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, matricula);
            ps.execute();

            System.out.println("\nUsuário " + nomeUsuario + " deletado com sucesso!");

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
                System.out.println("\n------------------------------");
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
                    String dataEmprestimo = rs.getString("data_emprestimo");

                    System.out.println("Título: " + titulo);
                    // Formata a data de empréstimo para o formato dd/MM/yyyy
                    String dataFormatada = dataEmprestimo.split(" ")[0];
                    String[] dataPartes = dataFormatada.split("-");
                    String dataFinal = dataPartes[2] + "/" + dataPartes[1] + "/" + dataPartes[0];
                    System.out.println("Data do empréstimo: " + dataFinal);
                    System.out.println("-----------------------------");
                }
                
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Ocorreu um erro ao listar os empréstimos do usuário." + e.getMessage());
            }
        }
}
