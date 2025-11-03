package com.biblioteca.Tech.model;

public class Livro {

    private int id;
    private String titulo;
    private String autor;
    private int ano;
    private boolean disponivel;

    public Livro(int id, String titulo, String autor, int ano, boolean disponivel) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.disponivel = disponivel;
    }

    public Livro(String titulo, String autor, int ano, boolean disponivel) {
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", ano=" + ano +
                ", disponivel=" + disponivel +
                '}';
    }
}
