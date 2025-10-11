package br.com.maisfoco.dao;

import br.com.maisfoco.config.ConnectionFactory;
import br.com.maisfoco.model.Divida;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DividaDAO {

    private static final String SQL_INSERT =
            "INSERT INTO dividas (nome, valorTotal, valorPago, status, idUsuario) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_ALL =
            "SELECT idDividas, nome, valorTotal, valorPago, status, idUsuario FROM dividas ORDER BY idDividas";

    public Long insert(Divida d) {
        System.out.println("[DividaDAO] insert");
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_INSERT, new String[]{"IDDIVIDAS"})) {
            ps.setString(1, d.getNome());
            ps.setDouble(2, d.getValorTotal());
            ps.setDouble(3, d.getValorPago());
            ps.setString(4, d.getStatus());
            ps.setLong(5, d.getIdUsuario());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    Long id = rs.getLong(1);
                    System.out.println("[DividaDAO] ok id=" + id);
                    return id;
                }
            }
        } catch (Exception e) {
            System.out.println("[DividaDAO] erro: " + e.getMessage());
        }
        return null;
    }

    public List<Divida> getAll() {
        System.out.println("[DividaDAO] getAll");
        List<Divida> lista = new ArrayList<>();
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Divida(
                        rs.getLong("IDDIVIDAS"),
                        rs.getString("NOME"),
                        rs.getDouble("VALORTOTAL"),
                        rs.getDouble("VALORPAGO"),
                        rs.getString("STATUS"),
                        rs.getLong("IDUSUARIO")
                ));
            }
            System.out.println("[DividaDAO] encontrados: " + lista.size());
        } catch (Exception e) {
            System.out.println("[DividaDAO] erro: " + e.getMessage());
        }
        return lista;
    }
}