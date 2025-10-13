package br.com.maisfoco.dao;

import br.com.maisfoco.factory.ConnectionFactory;
import br.com.maisfoco.model.Receita;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReceitaDAO {
    private static final String SQL_INSERT =
            "INSERT INTO receita (descricao, valor, data, idConta, idUsuario, idCategoria) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_ALL =
            "SELECT idReceita, descricao, valor, data, idConta, idUsuario, idCategoria FROM receita ORDER BY idReceita";

    public void insert(Receita r) {
        System.out.println("[ReceitaDAO] insert");
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_INSERT, new String[]{"IDRECEITA"})) {
            ps.setString(1, r.getDescricao());
            ps.setDouble(2, r.getValor());
            ps.setDate(3, r.getData());
            ps.setLong(4, r.getIdConta());
            ps.setLong(5, r.getIdUsuario());
            ps.setLong(6, r.getIdCategoria());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    r.setIdReceita(rs.getLong(1));
                    System.out.println("[ReceitaDAO] ok id=" + r.getIdReceita());
                }
            }
        } catch (Exception e) {
            System.out.println("[ReceitaDAO] erro: " + e.getMessage());
        }
    }

    public List<Receita> getAll() {
        System.out.println("[ReceitaDAO] getAll");
        List<Receita> lista = new ArrayList<>();
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Receita r = new Receita();
                r.setIdReceita(rs.getLong("idReceita"));
                r.setDescricao(rs.getString("descricao"));
                r.setValor(rs.getDouble("valor"));
                r.setData(rs.getDate("data"));
                r.setIdConta(rs.getLong("idConta"));
                r.setIdUsuario(rs.getLong("idUsuario"));
                r.setIdCategoria(rs.getLong("idCategoria"));
                lista.add(r);
            }
            System.out.println("[ReceitaDAO] encontrados: " + lista.size());
        } catch (Exception e) {
            System.out.println("[ReceitaDAO] erro: " + e.getMessage());
        }
        return lista;
    }
}