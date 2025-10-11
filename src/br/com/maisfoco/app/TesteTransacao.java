package br.com.maisfoco.app;

import br.com.maisfoco.config.ConnectionFactory;
import br.com.maisfoco.dao.TransacaoDAO;
import br.com.maisfoco.model.Transacao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TesteTransacao {
    public static void main(String[] args) {
        try {
            Long idUsuario = ensureUsuario("Davi", "aluno@maisfoco.com", "123");
            Long idConta   = ensureConta(idUsuario);
            List<Long> cat = ensureCategorias();

            TransacaoDAO dao = new TransacaoDAO();
            Date hoje = new Date(System.currentTimeMillis());

            dao.insert(new Transacao(50.0,   "DESPESA", "Lanche",  hoje, idConta, idUsuario, cat.get(0)));
            dao.insert(new Transacao(8.5,    "DESPESA", "Onibus",  hoje, idConta, idUsuario, cat.get(1)));
            dao.insert(new Transacao(2000.0, "RECEITA", "Salario", hoje, idConta, idUsuario, cat.get(2)));
            dao.insert(new Transacao(30.0,   "DESPESA", "Cinema",  hoje, idConta, idUsuario, cat.get(3)));
            dao.insert(new Transacao(45.0,   "DESPESA", "Farmacia",hoje, idConta, idUsuario, cat.get(4)));

            for (Transacao t : dao.getAll()) System.out.println(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Long ensureUsuario(String nome, String email, String senha) {
        try (Connection con = ConnectionFactory.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(
                    "SELECT idUsuario FROM usuario WHERE LOWER(email)=LOWER(?)")) {
                ps.setString(1, email);
                try (ResultSet rs = ps.executeQuery()) { if (rs.next()) return rs.getLong(1); }
            }
            try (PreparedStatement ins = con.prepareStatement(
                    "INSERT INTO usuario (nome, email, senha) VALUES (?, ?, ?)", new String[]{"IDUSUARIO"})) {
                ins.setString(1, nome);
                ins.setString(2, email);
                ins.setString(3, senha);
                ins.executeUpdate();
                try (ResultSet rs = ins.getGeneratedKeys()) { if (rs.next()) return rs.getLong(1); }
            }
        } catch (Exception e) { System.out.println("[ensureUsuario] " + e.getMessage()); }
        return null;
    }

    private static Long ensureConta(Long idUsuario) {
        try (Connection con = ConnectionFactory.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(
                    "SELECT idConta FROM conta WHERE idUsuario = ? AND ROWNUM = 1")) {
                ps.setLong(1, idUsuario);
                try (ResultSet rs = ps.executeQuery()) { if (rs.next()) return rs.getLong(1); }
            }
            try (PreparedStatement ins = con.prepareStatement(
                    "INSERT INTO conta (nome, saldo, tipo, idUsuario) VALUES ('Conta Principal', 1000, 'CORRENTE', ?)",
                    new String[]{"IDCONTA"})) {
                ins.setLong(1, idUsuario);
                ins.executeUpdate();
                try (ResultSet rs = ins.getGeneratedKeys()) { if (rs.next()) return rs.getLong(1); }
            }
        } catch (Exception e) { System.out.println("[ensureConta] " + e.getMessage()); }
        return null;
    }

    private static List<Long> ensureCategorias() {
        List<Long> ids = new ArrayList<>();
        String[] nomes = {"Alimentacao","Transporte","Salario","Lazer","Saude"};
        try (Connection con = ConnectionFactory.getConnection()) {
            for (String n : nomes) {
                Long id = null;
                try (PreparedStatement ps = con.prepareStatement(
                        "SELECT idCategoria FROM categoria WHERE nome = ?")) {
                    ps.setString(1, n);
                    try (ResultSet rs = ps.executeQuery()) { if (rs.next()) id = rs.getLong(1); }
                }
                if (id == null) {
                    try (PreparedStatement ins = con.prepareStatement(
                            "INSERT INTO categoria (nome) VALUES (?)", new String[]{"IDCATEGORIA"})) {
                        ins.setString(1, n);
                        ins.executeUpdate();
                        try (ResultSet rs = ins.getGeneratedKeys()) { if (rs.next()) id = rs.getLong(1); }
                    }
                }
                ids.add(id);
            }
        } catch (Exception e) { System.out.println("[ensureCategorias] " + e.getMessage()); }
        return ids;
    }
}