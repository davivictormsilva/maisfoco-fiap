package br.com.maisfoco.dao;

import br.com.maisfoco.config.ConnectionFactory;
import br.com.maisfoco.model.Transacao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransacaoDAO {

    private static final String SQL_INSERT =
            "INSERT INTO transacao (valor, tipo, descricao, data, idConta, idUsuario, idCategoria) VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_SELECT_ALL =
            "SELECT idTransacao, valor, tipo, descricao, data, idConta, idUsuario, idCategoria FROM transacao ORDER BY idTransacao";

    public Long insert(Transacao t) {
        System.out.println("[TransacaoDAO] insert");
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_INSERT, new String[]{"IDTRANSACAO"})) {
            ps.setDouble(1, t.getValor());
            ps.setString(2, t.getTipo());
            ps.setString(3, t.getDescricao());
            ps.setDate(4, t.getData());
            ps.setLong(5, t.getIdConta());
            ps.setLong(6, t.getIdUsuario());
            ps.setLong(7, t.getIdCategoria());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    Long id = rs.getLong(1);
                    System.out.println("[TransacaoDAO] ok id=" + id);
                    return id;
                }
            }
        } catch (Exception e) {
            System.out.println("[TransacaoDAO] erro: " + e.getMessage());
        }
        return null;
    }

    public List<Transacao> getAll() {
        System.out.println("[TransacaoDAO] getAll");
        List<Transacao> lista = new ArrayList<>();
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Transacao(
                        rs.getLong("IDTRANSACAO"),
                        rs.getDouble("VALOR"),
                        rs.getString("TIPO"),
                        rs.getString("DESCRICAO"),
                        rs.getDate("DATA"),
                        rs.getLong("IDCONTA"),
                        rs.getLong("IDUSUARIO"),
                        rs.getLong("IDCATEGORIA")
                ));
            }
            System.out.println("[TransacaoDAO] encontrados: " + lista.size());
        } catch (Exception e) {
            System.out.println("[TransacaoDAO] erro: " + e.getMessage());
        }
        return lista;
    }
}