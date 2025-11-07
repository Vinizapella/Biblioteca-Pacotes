package com.biblioteca.Tech.model;

public class Livro {
    private Integer id;
    private String titulo;
    private String autor;
    private Integer ano;
    private boolean disponivel;

    public Livro() {}

    public Livro(Integer id, String titulo, String autor, Integer ano, boolean disponivel) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.disponivel = disponivel;
    }

    public Livro(String titulo, String autor, Integer ano) {
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.disponivel = true;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
    public Integer getAno() { return ano; }
    public void setAno(Integer ano) { this.ano = ano; }
    public boolean isDisponivel() { return disponivel; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }

    @Override
    public String toString() {
        return String.format("ID: %s | %s - %s (%d) | Disponível: %s",
                id == null ? "-" : id.toString(),
                titulo, autor, ano,
                disponivel ? "SIM" : "NÃO");
    }
}