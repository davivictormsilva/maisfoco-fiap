package br.com.maisfoco.app;

import br.com.maisfoco.factory.ConnectionFactory;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try (Connection c = ConnectionFactory.getConnection()) {
            System.out.println("Conectado");
        } catch (Exception e) {
            System.out.println("Erro de conexao: " + e.getMessage());
        }
    }
}