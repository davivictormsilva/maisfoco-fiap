package br.com.maisfoco.dao;

import br.com.maisfoco.config.ConnectionFactory;
import br.com.maisfoco.model.Meta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MetaDAO {

    private static final String SQL_INSERT =
            "INSERT INTO metas (tipo, nome, valor_objetivo, valor_atual, data_limite, idUsuario) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_ALL =
            "SELECT idMetas, tipo, nome, valor_objetivo, valor_atual, data_limite, idUsuario FROM metas ORDER BY idMetas";

    public Long insert(Meta m) {
        System.out.println("[MetaDAO] insert");
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_INSERT, new String[]{"IDMETAS"})) {
            ps.setString(1, m.getTipo());
            ps.setString(2, m.getNome());
            ps.setDouble(3, m.getValorObjetivo());
            ps.setDouble(4, m.getValorAtual());
            ps.setDate(5, m.getDataLimite());
            ps.setLong(6, m.getIdUsuario());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    Long id = rs.getLong(1);
                    System.out.println("[MetaDAO] ok id=" + id);
                    return id;
                }
            }
        } catch (Exception e) {
            System.out.println("[MetaDAO] erro: " + e.getMessage());
        }
        return null;
    }

    public List<Meta> getAll() {
        System.out.println("[MetaDAO] getAll");
        List<Meta> lista = new ArrayList<>();
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Meta(
                        rs.getLong("IDMETAS"),
                        rs.getString("TIPO"),
                        rs.getString("NOME"),
                        rs.getDouble("VALOR_OBJETIVO"),
                        rs.getDouble("VALOR_ATUAL"),
                        rs.getDate("DATA_LIMITE"),
                        rs.getLong("IDUSUARIO")
                ));
            }
            System.out.println("[MetaDAO] encontrados: " + lista.size());
        } catch (Exception e) {
            System.out.println("[MetaDAO] erro: " + e.getMessage());
        }
        return lista;
    }
}