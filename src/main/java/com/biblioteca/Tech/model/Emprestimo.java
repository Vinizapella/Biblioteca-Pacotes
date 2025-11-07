package com.biblioteca.Tech.model;

import java.time.LocalDate;

public class Emprestimo {
    private Integer id;
    private Integer livroId;
    private String usuario;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public Emprestimo() {}

    public Emprestimo(Integer livroId, String usuario, LocalDate dataEmprestimo) {
        this.livroId = livroId;
        this.usuario = usuario;
        this.dataEmprestimo = dataEmprestimo;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getLivroId() { return livroId; }
    public void setLivroId(Integer livroId) { this.livroId = livroId; }
    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public LocalDate getDataEmprestimo() { return dataEmprestimo; }
    public void setDataEmprestimo(LocalDate dataEmprestimo) { this.dataEmprestimo = dataEmprestimo; }
    public LocalDate getDataDevolucao() { return dataDevolucao; }
    public void setDataDevolucao(LocalDate dataDevolucao) { this.dataDevolucao = dataDevolucao; }

    @Override
    public String toString() {
        return String.format("ID: %s | Livro ID: %d | Usuário: %s | Emprestado: %s | Devolução: %s",
                id == null ? "-" : id.toString(),
                livroId,
                usuario,
                dataEmprestimo != null ? dataEmprestimo.toString() : "-",
                dataDevolucao != null ? dataDevolucao.toString() : "-");
    }
}