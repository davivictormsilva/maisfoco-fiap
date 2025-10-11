package br.com.maisfoco.app;

import br.com.maisfoco.dao.ContaDAO;
import br.com.maisfoco.model.Conta;
import br.com.maisfoco.config.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class TesteConta {
    public static void main(String[] args) {
        try {
            Long userId = ensureUsuario("Davi", "aluno@maisfoco.com", "123");
            ContaDAO dao = new ContaDAO();
            dao.insert(new Conta("Conta Principal", "CORRENTE", 1000.0, userId));
            dao.insert(new Conta("Poupanca", "POUPANCA", 250.0, userId));
            dao.insert(new Conta("Carteira", "OUTRO", 80.0, userId));
            dao.insert(new Conta("Cartao Visa", "CARTAO", 0.0, userId));
            dao.insert(new Conta("Caixinha Viagem", "OUTRO", 300.0, userId));
            List<Conta> contas = dao.getAll();
            for (Conta c : contas) System.out.println(c);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static Long ensureUsuario(String nome, String email, String senha) {
        try (Connection con = ConnectionFactory.getConnection()) {
            try (PreparedStatement sel = con.prepareStatement("SELECT idUsuario FROM usuario WHERE LOWER(email)=LOWER(?)")) {
                sel.setString(1, email);
                try (ResultSet rs = sel.executeQuery()) {
                    if (rs.next()) return rs.getLong(1);
                }
            }
            try (PreparedStatement ins = con.prepareStatement(
                    "INSERT INTO usuario (nome, email, senha) VALUES (?, ?, ?)", new String[]{"IDUSUARIO"})) {
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
}