package com.ifpi.biblioteca.entidades;

import com.ifpi.biblioteca.DAO.LivroDB;

public class InserirLivroCommand implements Command {
    private LivroDB livroDB; // Receiver
    private Livro livro;     // Dados do livro

    public InserirLivroCommand(LivroDB livroDB, Livro livro) {
        this.livroDB = livroDB;
        this.livro = livro;
    }

    @Override
    public void execute() {
        livroDB.cadastrarLivro(livro); // Executa a operação no banco de dados
    }
}