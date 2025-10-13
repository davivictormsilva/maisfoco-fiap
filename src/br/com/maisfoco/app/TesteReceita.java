package br.com.maisfoco.app;

import br.com.maisfoco.factory.ConnectionFactory;
import br.com.maisfoco.dao.ReceitaDAO;
import br.com.maisfoco.model.Receita;

import java.sql.*;
import java.util.List;

public class TesteReceita {
    public static void main(String[] args) {
        Long userId = ensureUsuario("Davi", "aluno@maisfoco.com", "123");
        if (userId == null) { System.out.println("Sem usuário."); return; }

        Long catId = ensureCategoria("Salario");
        Long contaId = ensureConta(userId, "Conta Principal", "CORRENTE", 0.0); // use tipos válidos do seu banco

        ReceitaDAO dao = new ReceitaDAO();
        dao.insert(new Receita("Salario", 2500.00, Date.valueOf("2025-10-01"), contaId, userId, catId));
        dao.insert(new Receita("Freela",  600.00,  Date.valueOf("2025-10-05"), contaId, userId, catId));
        dao.insert(new Receita("Rend. Poup.", 45.50, Date.valueOf("2025-10-10"), contaId, userId, catId));
        dao.insert(new Receita("Pix",     120.00, Date.valueOf("2025-10-12"), contaId, userId, catId));
        dao.insert(new Receita("Bonus",   300.00, Date.valueOf("2025-10-13"), contaId, userId, catId));

        List<Receita> lista = dao.getAll();
        for (Receita r : lista) System.out.println(r);
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
        } catch (Exception e) { System.out.println("ensureUsuario: " + e.getMessage()); }
        return null;
    }

    private static Long ensureCategoria(String nome) {
        try (Connection con = ConnectionFactory.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(
                    "SELECT idCategoria FROM categoria WHERE UPPER(nome)=UPPER(?)")) {
                ps.setString(1, nome);
                try (ResultSet rs = ps.executeQuery()) { if (rs.next()) return rs.getLong(1); }
            }
            try (PreparedStatement ins = con.prepareStatement(
                    "INSERT INTO categoria (nome) VALUES (?)", new String[]{"IDCATEGORIA"})) {
                ins.setString(1, nome);
                ins.executeUpdate();
                try (ResultSet rs = ins.getGeneratedKeys()) { if (rs.next()) return rs.getLong(1); }
            }
        } catch (Exception e) { System.out.println("ensureCategoria: " + e.getMessage()); }
        return null;
    }

    private static Long ensureConta(Long idUsuario, String nome, String tipo, double saldo) {
        try (Connection con = ConnectionFactory.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(
                    "SELECT idConta FROM conta WHERE idUsuario=? AND UPPER(nome)=UPPER(?)")) {
                ps.setLong(1, idUsuario);
                ps.setString(2, nome);
                try (ResultSet rs = ps.executeQuery()) { if (rs.next()) return rs.getLong(1); }
            }
            try (PreparedStatement ins = con.prepareStatement(
                    "INSERT INTO conta (nome, saldo, tipo, idUsuario) VALUES (?, ?, ?, ?)", new String[]{"IDCONTA"})) {
                ins.setString(1, nome);
                ins.setDouble(2, saldo);
                ins.setString(3, tipo);
                ins.setLong(4, idUsuario);
                ins.executeUpdate();
                try (ResultSet rs = ins.getGeneratedKeys()) { if (rs.next()) return rs.getLong(1); }
            }
        } catch (Exception e) { System.out.println("ensureConta: " + e.getMessage()); }
        return null;
    }
}