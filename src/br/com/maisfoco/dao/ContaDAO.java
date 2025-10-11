package br.com.maisfoco.dao;

import br.com.maisfoco.config.ConnectionFactory;
import br.com.maisfoco.model.Conta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContaDAO {

    private static final String SQL_INSERT =
            "INSERT INTO conta (nome, saldo, tipo, idUsuario) VALUES (?, ?, ?, ?)";
    private static final String SQL_SELECT_ALL =
            "SELECT idConta, nome, saldo, tipo, idUsuario FROM conta ORDER BY idConta";

    public Long insert(Conta c) {
        System.out.println("[ContaDAO] insert");
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_INSERT, new String[]{"IDCONTA"})) {
            ps.setString(1, c.getNome());
            ps.setDouble(2, c.getSaldo());
            ps.setString(3, c.getTipo());
            ps.setLong(4, c.getIdUsuario());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    Long id = rs.getLong(1);
                    System.out.println("[ContaDAO] ok id=" + id);
                    return id;
                }
            }
        } catch (Exception e) {
            System.out.println("[ContaDAO] erro: " + e.getMessage());
        }
        return null;
    }

    public List<Conta> getAll() {
        System.out.println("[ContaDAO] getAll");
        List<Conta> lista = new ArrayList<>();
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Conta(
                        rs.getLong("IDCONTA"),
                        rs.getString("NOME"),
                        rs.getString("TIPO"),
                        rs.getDouble("SALDO"),
                        rs.getLong("IDUSUARIO")
                ));
            }
            System.out.println("[ContaDAO] encontrados: " + lista.size());
        } catch (Exception e) {
            System.out.println("[ContaDAO] erro: " + e.getMessage());
        }
        return lista;
    }
}