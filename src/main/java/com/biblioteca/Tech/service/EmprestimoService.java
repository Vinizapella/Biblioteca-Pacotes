package com.biblioteca.Tech.service;

import com.biblioteca.Tech.infrastructure.repository.EmprestimoRepository;
import com.biblioteca.Tech.model.Emprestimo;
import com.biblioteca.Tech.model.Livro;


import java.time.LocalDate;

public class EmprestimoService {

    public EmprestimoService() {}

    public Emprestimo emprestarLivro(int livroId, String usuario) {
        Livro livro = com.biblioteca.Tech.infrastructure.repository.LivroRepository.findById(livroId);
        if (livro == null) throw new IllegalArgumentException("Livro não encontrado com id: " + livroId);
        if (!livro.isDisponivel()) throw new IllegalStateException("Livro não está disponível para empréstimo.");

        com.biblioteca.Tech.infrastructure.repository.LivroRepository.atualizarDisponibilidade(livroId, false);

        Emprestimo e = new Emprestimo(livroId, usuario, LocalDate.now());
        return EmprestimoRepository.inserir(e);
    }

    public void devolverLivro(int emprestimoId, LocalDate dataDevolucao) {
        var emprestimo = EmprestimoRepository.findById(emprestimoId);
        if (emprestimo == null) throw new IllegalArgumentException("Empréstimo não encontrado com id: " + emprestimoId);

        EmprestimoRepository.registrarDevolucao(emprestimoId, dataDevolucao);

        com.biblioteca.Tech.infrastructure.repository.LivroRepository.atualizarDisponibilidade(emprestimo.getLivroId(), true);
    }
}