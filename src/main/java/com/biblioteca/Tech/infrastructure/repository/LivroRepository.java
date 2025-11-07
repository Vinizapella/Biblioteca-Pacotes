package com.biblioteca.Tech.infrastructure.repository;

import com.biblioteca.Tech.infrastructure.Conexao;
import com.biblioteca.Tech.model.Livro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroRepository {

    public static Livro inserir(Livro livro) {
        String sql = "INSERT INTO livros(titulo, autor, ano, disponivel) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, livro.getTitulo());
            ps.setString(2, livro.getAutor());
            ps.setInt(3, livro.getAno());
            ps.setInt(4, livro.isDisponivel() ? 1 : 0);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    livro.setId(rs.getInt(1));
                }
            }
            return livro;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir livro: " + e.getMessage(), e);
        }
    }

    public static List<Livro> findAll() {
        String sql = "SELECT id, titulo, autor, ano, disponivel FROM livros";
        List<Livro> livros = new ArrayList<>();
        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Livro l = new Livro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getInt("ano"),
                        rs.getInt("disponivel") == 1
                );
                livros.add(l);
            }
            return livros;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar livros: " + e.getMessage(), e);
        }
    }

    public static Livro findById(int id) {
        String sql = "SELECT id, titulo, autor, ano, disponivel FROM livros WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Livro(
                            rs.getInt("id"),
                            rs.getString("titulo"),
                            rs.getString("autor"),
                            rs.getInt("ano"),
                            rs.getInt("disponivel") == 1
                    );
                }
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar livro por id: " + e.getMessage(), e);
        }
    }

    public static void atualizarDisponibilidade(int id, boolean disponivel) {
        String sql = "UPDATE livros SET disponivel = ? WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, disponivel ? 1 : 0);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar disponibilidade: " + e.getMessage(), e);
        }
    }
}