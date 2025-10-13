package br.com.maisfoco.app;

import br.com.maisfoco.factory.ConnectionFactory;
import br.com.maisfoco.dao.DividaDAO;
import br.com.maisfoco.model.Divida;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TesteDivida {
    public static void main(String[] args) {
        Long idUsuario = ensureUsuario("Davi", "aluno@maisfoco.com", "123");
        DividaDAO dao = new DividaDAO();
        dao.insert(new Divida("Cartao Nubank", 800.0, 100.0, "ABERTA", idUsuario));
        dao.insert(new Divida("Emprestimo", 1500.0, 0.0, "ATRASADA", idUsuario));
        dao.insert(new Divida("Financiamento", 50000.0, 5000.0, "ABERTA", idUsuario));
        dao.insert(new Divida("Cartao Itau", 1200.0, 200.0, "ABERTA", idUsuario));
        dao.insert(new Divida("Cartao Visa", 600.0, 600.0, "PAGA", idUsuario));
        for (var d : dao.getAll()) System.out.println(d);
    }

    private static Long ensureUsuario(String nome, String email, String senha) {
        try (Connection con = ConnectionFactory.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("SELECT idUsuario FROM usuario WHERE LOWER(email)=LOWER(?)")) {
                ps.setString(1, email);
                try (ResultSet rs = ps.executeQuery()) { if (rs.next()) return rs.getLong(1); }
            }
            try (PreparedStatement ins = con.prepareStatement(
                    "INSERT INTO usuario (nome, email, senha) VALUES (?, ?, ?)", new String[]{"IDUSUARIO"})) {
                ins.setString(1, nome); ins.setString(2, email); ins.setString(3, senha);
                ins.executeUpdate();
                try (ResultSet rs = ins.getGeneratedKeys()) { if (rs.next()) return rs.getLong(1); }
            }
        } catch (Exception e) { System.out.println("Erro usuario: " + e.getMessage()); }
        return null;
    }
}