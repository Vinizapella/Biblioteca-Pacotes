package com.biblioteca.Tech.infrastructure.repository;

import com.biblioteca.Tech.infrastructure.Conexao;
import com.biblioteca.Tech.model.Emprestimo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoRepository {

    public static Emprestimo inserir(Emprestimo e) {
        String sql = "INSERT INTO emprestimos(livro_id, usuario, data_emprestimo) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, e.getLivroId());
            ps.setString(2, e.getUsuario());
            ps.setString(3, e.getDataEmprestimo().toString());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) e.setId(rs.getInt(1));
            }
            return e;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir empréstimo: " + ex.getMessage(), ex);
        }
    }

    public static List<Emprestimo> findAll() {
        String sql = "SELECT id, livro_id, usuario, data_emprestimo, data_devolucao FROM emprestimos";
        List<Emprestimo> list = new ArrayList<>();
        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Emprestimo e = new Emprestimo();
                e.setId(rs.getInt("id"));
                e.setLivroId(rs.getInt("livro_id"));
                e.setUsuario(rs.getString("usuario"));
                e.setDataEmprestimo(LocalDate.parse(rs.getString("data_emprestimo")));
                String dataDev = rs.getString("data_devolucao");
                if (dataDev != null) e.setDataDevolucao(LocalDate.parse(dataDev));
                list.add(e);
            }
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar empréstimos: " + ex.getMessage(), ex);
        }
    }

    public static Emprestimo findById(int id) {
        String sql = "SELECT id, livro_id, usuario, data_emprestimo, data_devolucao FROM emprestimos WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Emprestimo e = new Emprestimo();
                    e.setId(rs.getInt("id"));
                    e.setLivroId(rs.getInt("livro_id"));
                    e.setUsuario(rs.getString("usuario"));
                    e.setDataEmprestimo(LocalDate.parse(rs.getString("data_emprestimo")));
                    String dataDev = rs.getString("data_devolucao");
                    if (dataDev != null) e.setDataDevolucao(LocalDate.parse(dataDev));
                    return e;
                }
            }
            return null;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar empréstimo: " + ex.getMessage(), ex);
        }
    }

    public static void registrarDevolucao(int emprestimoId, LocalDate dataDevolucao) {
        String sql = "UPDATE emprestimos SET data_devolucao = ? WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dataDevolucao.toString());
            ps.setInt(2, emprestimoId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao registrar devolução: " + ex.getMessage(), ex);
        }
    }
}