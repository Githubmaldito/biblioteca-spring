package com.ifpi.biblioteca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;
import java.util.Scanner;
import com.ifpi.biblioteca.DAO.LivroDB;
import com.ifpi.biblioteca.DAO.UserDB;
import com.ifpi.biblioteca.entidades.Livro;
import com.ifpi.biblioteca.entidades.Usuario;

@SpringBootApplication
public class BibliotecaApplication {


    @Autowired
    private UserDB userDB;

    public static void main(String[] args) {
		SpringApplication.run(BibliotecaApplication.class, args);

		Scanner scanner = new Scanner(System.in);
        // variável que armazena a opção escolhida pelo usuário
        int opcao;// laço de repetição que exibe o menu enquanto o usuário não escolher a opção de
        // sair
        do {
            // exibe o menu com escolhas de opções
            System.out.println("\n1. Cadastrar livro");
            System.out.println("2. Cadastrar usuário");
            System.out.println("3. Listar livros");
            System.out.println("4. Listar usuários");
            System.out.println("5. Emprestar livro");
            System.out.println("6. Histório de empréstimos do usuário");
            System.out.println("7. Devolver livro");
            System.out.println("8. Remover livro");
            System.out.println("9. Remover usuário");
            System.out.println("10. Sair\n");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            // switch case que verifica a opção escolhida pelo usuário1
        
            switch (opcao) {
                // caso a opção seja 1 - cadastrar livro
                case 1:
                    System.out.print("Digite o título do livro: ");
                    // o usuario digita o título do livro
                    String titulo = scanner.nextLine();
                    System.out.print("Digite o autor do livro: ");
                    // e o autor
                    String autor = scanner.nextLine();

                    System.err.println("Digite o ISBN do livro: ");
                    String isbn = scanner.nextLine();
                    // cria um objeto do tipo livro com o título e autor digitados
                    // biblioteca.cadastrarLivro(new Livro(titulo, autor, false, isbn));
                    // executa a função cadastrar livro da classe Alexandria
                    // finaliza o case

                    //aqui o livro é cadastrado no banco de dados
                    new LivroDB().cadastrarLivro(new Livro(titulo, autor, false, isbn));
                    
                    break;

                case 2:
                    System.out.println("Digite o nome do usuário: ");
                    // receber o nome do usuario
                    String nome = scanner.nextLine();

                    System.out.println("Digite a matrícula do usuário: ");
                    // receber a matrícula do usuário
                    String matricula = scanner.nextLine();

                    System.out.println("Digite o e-mail do usuário: ");
                    String email = scanner.nextLine();

                    //historico.cadastrarUsuario(new Usuario(nome, email, matricula));
                    new UserDB().cadastrarUsuario(new Usuario(nome, email, matricula));
                    
                    
                    break;

                // caso a opção seja 3 - listar livros
                case 3:
                    // executa a função listar livros da classe Alexandria
                    //biblioteca.listarLivros();

                    //lista os livros que estão no BD
                    new LivroDB().listarLivros();
                    // finaliza o case
                    break;

                case 4:
                        // caso a opção seja 4 - listar usuários
                        //historico.listarUsuarios();
                        // executa a função listar usuários da classe Sabios

                        //lista os users que estão no BD
                        new UserDB().listarUsuarios();
                        // finaliza o case
                        break;

                case 5:
                    // caso a opção seja 5 - emprestar livro
                    System.out.print("Digite o título do livro para emprestar: \n");
                    // o usuário digita o título do livro
                    String tituloEmprestimo = scanner.nextLine();
                    System.out.print("Digite a matrícula do usuário para emprestar o livro: \n");
                    // o usuário digita a matrícula do usuário
                    String matriculaUsuario = scanner.nextLine();
                    //a data de empréstimo é adicionada automaticamente
                    Date dataEmprestimo = new Date(System.currentTimeMillis());
                    //isso converte a dataq qe tinha milissegundos para uma data normal
                    dataEmprestimo = Date.valueOf(dataEmprestimo.toLocalDate());
                    // a função emprestar livro da classe Alexandria é executada
                    // historico.emprestarLivro(tituloEmprestimo, matriculaUsuario, dataEmprestimo);

                    new LivroDB().emprestarLivro(tituloEmprestimo, matriculaUsuario, dataEmprestimo);
                    // finaliza o case

                    break;

                case 6:
                        // caso a opção seja 6 - histórico de empréstimos do usuário
                        System.out.print("Digite a matrícula do usuário para ver o histórico de empréstimos: \n");
                        // o usuário digita a matrícula do usuário
                        String matriculaHistorico = scanner.nextLine();
                        // a função histórico de empréstimos do usuário da classe Sabios é executada
                        // historico.historicoEmprestimos(matriculaHistorico);
                        // e o histórico de empréstimos do usuário é exibido
                        
                        new UserDB().historicoEmprestimos(matriculaHistorico); 
                        // finaliza o case
                        break;
                        
                case 7:
                    // caso a opção seja 7 - devolver livro
                    System.out.print("Digite o título do livro para devolver: \n");
                    // o usuário digita o título do livro
                    String tituloDevolucao = scanner.nextLine();
                    // a função devolver livro da classe Sabios é executada
                    // historico.devolverLivro(tituloDevolucao);

                    new LivroDB().devolverLivro(tituloDevolucao);
                    // finaliza o case
                    break;

                case 8:
                    //caso a opção seja 8 - remover livro
                    System.out.print("Digite o ISBN do livro para remover: \n");
                    //o usuário digita o título do livro
                    String idRemocao = scanner.nextLine();
                    //como a função de remover livro tem como parametro um objeto do tipo livro,
                    //é criado um objeto do tipo livro com o título digitado
                    new LivroDB().removerLivro(idRemocao);
                    // new LivroDB().removerLivro(livroRemocao); 
                    break;

                case 9:
                    //caso a opção seja 9 - remover usuário
                    System.out.print("Digite a matrícula do usuário para remover: \n");
                    //o usuário digita a matrícula do usuário
                    String usuarioMatricula = scanner.nextLine();
                    //a função remover usuário da classe Sabios é executada
                    //historico.removerUsuario(matriculaRemocao);
                    new UserDB().deletarUsuario(usuarioMatricula);
                    // finaliza o case
                    break;
                    
                case 10:
                    //caso o usuário escolha a opção 10 - sair
                    System.out.println("Saindo...");
                    // é exibida uma mensagem de saída e o programa é finalizado
                    break;
                default:
                    //caso o usuário escolha uma opção diferente das anteriores
                    System.out.println("Opção inválida!");
            }
            //o menu será exibido enquanto o usuário não escolher a opção de sair
        } while (opcao != 10);
        // fecha o scanner
        scanner.close();
    }
}


