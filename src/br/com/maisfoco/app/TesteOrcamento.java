package br.com.maisfoco.app;

import br.com.maisfoco.dao.OrcamentoDAO;
import br.com.maisfoco.model.Orcamento;
import br.com.maisfoco.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class TesteOrcamento {
    public static void main(String[] args) {
        Long userId = ensureUsuario("Davi", "aluno@maisfoco.com", "123");
        if (userId == null) {
            System.out.println("Nao foi possivel garantir o usuario");
            return;
        }
        Long catId = ensureCategoria("Alimentacao");
        if (catId == null) {
            System.out.println("Nao foi possivel garantir a categoria");
            return;
        }

        OrcamentoDAO dao = new OrcamentoDAO();
        dao.insert(new Orcamento(userId, catId, 10, 800.0, 0.0));
        dao.insert(new Orcamento(userId, catId, 11, 900.0, 0.0));

        List<Orcamento> todos = dao.getAll();
        for (Orcamento o : todos) System.out.println(o);

        List<Orcamento> doMes = dao.getByUsuarioMes(userId, 10);
        for (Orcamento o : doMes) System.out.println("Mes 10 -> " + o);
    }

    private static Long ensureUsuario(String nome, String email, String senha) {
        try (Connection con = ConnectionFactory.getConnection()) {
            try (PreparedStatement sel = con.prepareStatement("SELECT idUsuario FROM usuario WHERE LOWER(email)=LOWER(?)")) {
                sel.setString(1, email);
                try (ResultSet rs = sel.executeQuery()) {
                    if (rs.next()) return rs.getLong(1);
                }
            }
            try (PreparedStatement ins = con.prepareStatement("INSERT INTO usuario (nome, email, senha) VALUES (?, ?, ?)", new String[]{"IDUSUARIO"})) {
                ins.setString(1, nome);
                ins.setString(2, email);
                ins.setString(3, senha);
                ins.executeUpdate();
                try (ResultSet rs = ins.getGeneratedKeys()) {
                    if (rs.next()) return rs.getLong(1);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro usuario: " + e.getMessage());
        }
        return null;
    }

    private static Long ensureCategoria(String nome) {
        try (Connection con = ConnectionFactory.getConnection()) {
            try (PreparedStatement sel = con.prepareStatement("SELECT idCategoria FROM categoria WHERE LOWER(nome)=LOWER(?)")) {
                sel.setString(1, nome);
                try (ResultSet rs = sel.executeQuery()) {
                    if (rs.next()) return rs.getLong(1);
                }
            }
            try (PreparedStatement ins = con.prepareStatement("INSERT INTO categoria (nome, cor_hex, icone) VALUES (?, ?, ?)", new String[]{"IDCATEGORIA"})) {
                ins.setString(1, nome);
                ins.setString(2, "#ff6600");
                ins.setString(3, "bi-cash-coin");
                ins.executeUpdate();
                try (ResultSet rs = ins.getGeneratedKeys()) {
                    if (rs.next()) return rs.getLong(1);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro categoria: " + e.getMessage());
        }
        return null;
    }
}