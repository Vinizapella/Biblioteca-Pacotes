package com.biblioteca.Tech.model;

import java.time.LocalDate;

public class Emprestimo {

    private int id;
    private int id_usuario;
    private int id_livro;
    private LocalDate data_emprestimo;
    private LocalDate data_devolucao;

    public Emprestimo(int id, int id_usuario, int id_livro, LocalDate data_emprestimo, LocalDate data_devolucao) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.id_livro = id_livro;
        this.data_emprestimo = data_emprestimo;
        this.data_devolucao = data_devolucao;
    }

    public Emprestimo(int id_usuario, int id_livro, LocalDate data_emprestimo, LocalDate data_devolucao) {
        this.id_usuario = id_usuario;
        this.id_livro = id_livro;
        this.data_emprestimo = data_emprestimo;
        this.data_devolucao = data_devolucao;
    }

    public LocalDate getData_emprestimo() {
        return data_emprestimo;
    }

    public void setData_emprestimo(LocalDate data_emprestimo) {
        this.data_emprestimo = data_emprestimo;
    }

    public LocalDate getData_devolucao() {
        return data_devolucao;
    }

    public void setData_devolucao(LocalDate data_devolucao) {
        this.data_devolucao = data_devolucao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Emprestimo{" +
                "id=" + id +
                ", id_usuario=" + id_usuario +
                ", id_livro=" + id_livro +
                ", data_emprestimo=" + data_emprestimo +
                ", data_devolucao=" + data_devolucao +
                '}';
    }
}
