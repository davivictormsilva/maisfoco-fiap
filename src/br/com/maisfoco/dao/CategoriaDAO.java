package br.com.maisfoco.dao;

import br.com.maisfoco.factory.ConnectionFactory;
import br.com.maisfoco.model.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    private static final String SQL_INSERT =
            "INSERT INTO categoria (nome, cor_hex, icone) VALUES (?, ?, ?)";
    private static final String SQL_SELECT_ALL =
            "SELECT idCategoria, nome, cor_hex, icone FROM categoria ORDER BY idCategoria";

    public Long insert(Categoria c) {
        System.out.println("[CategoriaDAO] insert");
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_INSERT, new String[]{"IDCATEGORIA"})) {
            ps.setString(1, c.getNome());
            ps.setString(2, c.getCorHex());
            ps.setString(3, c.getIcone());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    Long id = rs.getLong(1);
                    System.out.println("[CategoriaDAO] ok id=" + id);
                    return id;
                }
            }
        } catch (Exception e) {
            System.out.println("[CategoriaDAO] erro: " + e.getMessage());
        }
        return null;
    }

    public List<Categoria> getAll() {
        System.out.println("[CategoriaDAO] getAll");
        List<Categoria> lista = new ArrayList<>();
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Categoria(
                        rs.getLong("IDCATEGORIA"),
                        rs.getString("NOME"),
                        rs.getString("COR_HEX"),
                        rs.getString("ICONE")
                ));
            }
            System.out.println("[CategoriaDAO] encontrados: " + lista.size());
        } catch (Exception e) {
            System.out.println("[CategoriaDAO] erro: " + e.getMessage());
        }
        return lista;
    }
}