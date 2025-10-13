package br.com.maisfoco.app;

import br.com.maisfoco.factory.ConnectionFactory;
import br.com.maisfoco.dao.MetaDAO;
import br.com.maisfoco.model.Meta;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TesteMeta {
    public static void main(String[] args) {
        Long idUsuario = ensureUsuario("Davi", "aluno@maisfoco.com", "123");
        MetaDAO dao = new MetaDAO();
        dao.insert(new Meta("ECONOMIA", "Reserva Emergencia", 3000.0, 500.0, Date.valueOf("2026-03-01"), idUsuario));
        dao.insert(new Meta("COMPRA", "Notebook", 5000.0, 800.0, Date.valueOf("2026-06-01"), idUsuario));
        dao.insert(new Meta("ECONOMIA", "Viagem", 4000.0, 1000.0, Date.valueOf("2026-12-15"), idUsuario));
        dao.insert(new Meta("OUTRO", "Curso", 1200.0, 200.0, Date.valueOf("2026-04-20"), idUsuario));
        dao.insert(new Meta("ECONOMIA", "Celular", 2500.0, 300.0, Date.valueOf("2026-05-10"), idUsuario));
        for (var m : dao.getAll()) System.out.println(m);
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