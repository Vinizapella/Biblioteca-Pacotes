package com.biblioteca.Tech.view;

import com.biblioteca.Tech.model.Emprestimo;
import com.biblioteca.Tech.model.Livro;
import com.biblioteca.Tech.service.LivroService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class BibliotecaView {
    private final LivroService livroService;
    private final Scanner scanner = new Scanner(System.in);

    public BibliotecaView(LivroService livroService) {
        this.livroService = livroService;
    }

    public void mostrarMenu() {
        int opcao;
        do {
            System.out.println("\n=== Biblioteca ===");
            System.out.println("1 - Cadastrar livro");
            System.out.println("2 - Listar livros");
            System.out.println("3 - Registrar empréstimo");
            System.out.println("4 - Registrar devolução");
            System.out.println("5 - Listar empréstimos");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = capturarOpcao();
            try {
                switch (opcao) {
                    case 1 -> cadastrarLivro();
                    case 2 -> listarLivros();
                    case 3 -> registrarEmprestimo();
                    case 4 -> registrarDevolucao();
                    case 5 -> listarEmprestimos();
                    case 0 -> System.out.println("Saindo...");
                    default -> System.out.println("Opção inválida.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (opcao != 0);
    }

    public int capturarOpcao() {
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("Digite um número: ");
        }
        return scanner.nextInt();
    }

    private void cadastrarLivro() {
        scanner.nextLine(); // consumir newline
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Ano: ");
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("Ano inválido. Digite novamente: ");
        }
        int ano = scanner.nextInt();
        var livro = livroService.cadastrarLivro(titulo, autor, ano);
        System.out.println("Livro cadastrado: " + livro);
    }

    private void listarLivros() {
        List<Livro> livros = livroService.consultarTodos();
        System.out.println("\n--- Livros cadastrados ---");
        livros.forEach(System.out::println);
    }

    private void registrarEmprestimo() {
        System.out.print("ID do livro: ");
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("ID inválido. Digite novamente: ");
        }
        int livroId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Usuário: ");
        String usuario = scanner.nextLine();
        var emprestimo = livroService.registrarEmprestimo(livroId, usuario);
        System.out.println("Empréstimo registrado: " + emprestimo);
    }

    private void registrarDevolucao() {
        System.out.print("ID do empréstimo: ");
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("ID inválido. Digite novamente: ");
        }
        int emprestimoId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Data de devolução (YYYY-MM-DD) ou vazio para hoje: ");
        String s = scanner.nextLine().trim();
        LocalDate data;
        if (s.isEmpty()) data = LocalDate.now();
        else data = LocalDate.parse(s);
        livroService.registrarDevolucao(emprestimoId, data);
        System.out.println("Devolução registrada com sucesso.");
    }

    private void listarEmprestimos() {
        List<Emprestimo> lista = livroService.consultarTodosEmprestimos();
        System.out.println("\n--- Empréstimos ---");
        lista.forEach(System.out::println);
    }
}