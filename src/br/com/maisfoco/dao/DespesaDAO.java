package br.com.maisfoco.dao;

import br.com.maisfoco.factory.ConnectionFactory;
import br.com.maisfoco.model.Despesa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DespesaDAO {
    private static final String SQL_INSERT =
            "INSERT INTO despesa (descricao, valor, data, idConta, idUsuario, idCategoria) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_ALL =
            "SELECT idDespesa, descricao, valor, data, idConta, idUsuario, idCategoria FROM despesa ORDER BY idDespesa";

    public void insert(Despesa d) {
        System.out.println("[DespesaDAO] insert");
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_INSERT, new String[]{"IDDESPESA"})) {
            ps.setString(1, d.getDescricao());
            ps.setDouble(2, d.getValor());
            ps.setDate(3, d.getData());
            ps.setLong(4, d.getIdConta());
            ps.setLong(5, d.getIdUsuario());
            ps.setLong(6, d.getIdCategoria());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    d.setIdDespesa(rs.getLong(1));
                    System.out.println("[DespesaDAO] ok id=" + d.getIdDespesa());
                }
            }
        } catch (Exception e) {
            System.out.println("[DespesaDAO] erro: " + e.getMessage());
        }
    }

    public List<Despesa> getAll() {
        System.out.println("[DespesaDAO] getAll");
        List<Despesa> lista = new ArrayList<>();
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Despesa d = new Despesa();
                d.setIdDespesa(rs.getLong("idDespesa"));
                d.setDescricao(rs.getString("descricao"));
                d.setValor(rs.getDouble("valor"));
                d.setData(rs.getDate("data"));
                d.setIdConta(rs.getLong("idConta"));
                d.setIdUsuario(rs.getLong("idUsuario"));
                d.setIdCategoria(rs.getLong("idCategoria"));
                lista.add(d);
            }
            System.out.println("[DespesaDAO] encontrados: " + lista.size());
        } catch (Exception e) {
            System.out.println("[DespesaDAO] erro: " + e.getMessage());
        }
        return lista;
    }
}