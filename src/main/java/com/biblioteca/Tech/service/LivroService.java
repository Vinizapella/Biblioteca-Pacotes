package com.biblioteca.Tech.service;

import com.biblioteca.Tech.infrastructure.repository.EmprestimoRepository;
import com.biblioteca.Tech.model.Emprestimo;
import com.biblioteca.Tech.model.Livro;

import java.time.LocalDate;
import java.util.List;

public class LivroService {

    private final EmprestimoService emprestimoService;

    public LivroService(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    public Livro cadastrarLivro(String titulo, String autor, int ano) {
        Livro livro = new Livro(titulo, autor, ano);
        livro.setDisponivel(true);
        return com.biblioteca.Tech.infrastructure.repository.LivroRepository.inserir(livro);
    }

    public List<Livro> consultarTodos() {
        return com.biblioteca.Tech.infrastructure.repository.LivroRepository.findAll();
    }

    public Emprestimo registrarEmprestimo(int livroId, String usuario) {
        return emprestimoService.emprestarLivro(livroId, usuario);
    }

    public void registrarDevolucao(int emprestimoId, LocalDate dataDevolucao) {
        emprestimoService.devolverLivro(emprestimoId, dataDevolucao);
    }

    public List<Emprestimo> consultarTodosEmprestimos() {
        return EmprestimoRepository.findAll();
    }
}