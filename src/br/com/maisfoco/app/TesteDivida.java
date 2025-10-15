package br.com.maisfoco.app;

import br.com.maisfoco.dao.DividaDAO;
import br.com.maisfoco.model.Divida;
import br.com.maisfoco.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TesteDivida {
    public static void main(String[] args) {
        Long userId = ensureUsuario("Davi", "aluno@maisfoco.com", "123");
        if (userId == null) {
            System.out.println("Nao foi possivel garantir o usuario");
            return;
        }
        Long contaId = firstContaId(userId);

        DividaDAO dao = new DividaDAO();
        dao.insert(new Divida("Cartao Nubank", 1200.0, 0.0, data(2025, 11, 5), "PENDENTE", userId, contaId));
        dao.insert(new Divida("Parcela Notebook", 3500.0, 500.0, data(2025, 12, 10), "PENDENTE", userId, contaId));

        List<Divida> lista = dao.getAll();
        for (Divida d : lista) System.out.println(d);
    }

    private static Date data(int ano, int mes1a12, int dia) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, ano);
        c.set(Calendar.MONTH, mes1a12 - 1);
        c.set(Calendar.DAY_OF_MONTH, dia);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
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

    private static Long firstContaId(Long idUsuario) {
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT idConta FROM conta WHERE idUsuario = ? ORDER BY idConta FETCH FIRST 1 ROWS ONLY")) {
            ps.setLong(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getLong(1);
            }
        } catch (Exception e) {
            System.out.println("Erro conta: " + e.getMessage());
        }
        return null;
    }
}