package br.com.maisfoco.dao;

import br.com.maisfoco.factory.ConnectionFactory;
import br.com.maisfoco.model.Divida;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DividaDAO {
    public void insert(Divida d) {
        String sql = "INSERT INTO divida (nome, valorTotal, valorPago, dataVencimento, status, idUsuario, idConta) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, d.getNome());
            ps.setDouble(2, d.getValorTotal());
            ps.setDouble(3, d.getValorPago() == null ? 0.0 : d.getValorPago());
            ps.setDate(4, new java.sql.Date(d.getDataVencimento().getTime()));
            ps.setString(5, d.getStatus());
            ps.setLong(6, d.getIdUsuario());
            if (d.getIdConta() == null) ps.setNull(7, Types.NUMERIC); else ps.setLong(7, d.getIdConta());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro divida: " + e.getMessage());
        }
    }

    public List<Divida> getAll() {
        List<Divida> lista = new ArrayList<>();
        String sql = "SELECT idDivida, nome, valorTotal, valorPago, dataVencimento, status, idUsuario, idConta FROM divida ORDER BY idDivida";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Divida d = new Divida();
                d.setIdDivida(rs.getLong("idDivida"));
                d.setNome(rs.getString("nome"));
                d.setValorTotal(rs.getDouble("valorTotal"));
                d.setValorPago(rs.getDouble("valorPago"));
                d.setDataVencimento(new java.util.Date(rs.getDate("dataVencimento").getTime()));
                d.setStatus(rs.getString("status"));
                d.setIdUsuario(rs.getLong("idUsuario"));
                long idc = rs.getLong("idConta");
                d.setIdConta(rs.wasNull() ? null : idc);
                lista.add(d);
            }
        } catch (SQLException e) {
            System.out.println("Erro divida: " + e.getMessage());
        }
        return lista;
    }
}