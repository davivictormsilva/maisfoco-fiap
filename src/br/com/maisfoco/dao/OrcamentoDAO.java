package br.com.maisfoco.dao;

import br.com.maisfoco.factory.ConnectionFactory;
import br.com.maisfoco.model.Orcamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrcamentoDAO {
    public void insert(Orcamento o) {
        String sql = "INSERT INTO orcamento (idUsuario, idCategoria, mesReferencia, valorMensal, gastoAtual) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, o.getIdUsuario());
            ps.setLong(2, o.getIdCategoria());
            ps.setInt(3, o.getMesReferencia());
            ps.setDouble(4, o.getValorMensal());
            ps.setDouble(5, o.getGastoAtual() == null ? 0.0 : o.getGastoAtual());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro orcamento: " + e.getMessage());
        }
    }

    public List<Orcamento> getAll() {
        List<Orcamento> lista = new ArrayList<>();
        String sql = "SELECT idOrcamento, idUsuario, idCategoria, mesReferencia, valorMensal, gastoAtual FROM orcamento ORDER BY idOrcamento";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Orcamento o = new Orcamento();
                o.setIdOrcamento(rs.getLong("idOrcamento"));
                o.setIdUsuario(rs.getLong("idUsuario"));
                o.setIdCategoria(rs.getLong("idCategoria"));
                o.setMesReferencia(rs.getInt("mesReferencia"));
                o.setValorMensal(rs.getDouble("valorMensal"));
                o.setGastoAtual(rs.getDouble("gastoAtual"));
                lista.add(o);
            }
        } catch (SQLException e) {
            System.out.println("Erro orcamento: " + e.getMessage());
        }
        return lista;
    }

    public List<Orcamento> getByUsuarioMes(Long idUsuario, Integer mesReferencia) {
        List<Orcamento> lista = new ArrayList<>();
        String sql = "SELECT idOrcamento, idUsuario, idCategoria, mesReferencia, valorMensal, gastoAtual " +
                "FROM orcamento WHERE idUsuario = ? AND mesReferencia = ? ORDER BY idCategoria";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, idUsuario);
            ps.setInt(2, mesReferencia);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Orcamento o = new Orcamento();
                    o.setIdOrcamento(rs.getLong("idOrcamento"));
                    o.setIdUsuario(rs.getLong("idUsuario"));
                    o.setIdCategoria(rs.getLong("idCategoria"));
                    o.setMesReferencia(rs.getInt("mesReferencia"));
                    o.setValorMensal(rs.getDouble("valorMensal"));
                    o.setGastoAtual(rs.getDouble("gastoAtual"));
                    lista.add(o);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro orcamento: " + e.getMessage());
        }
        return lista;
    }

    public void atualizarGastoAtual(Long idOrcamento, Double novoGastoAtual) {
        String sql = "UPDATE orcamento SET gastoAtual = ? WHERE idOrcamento = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, novoGastoAtual);
            ps.setLong(2, idOrcamento);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro orcamento: " + e.getMessage());
        }
    }
}