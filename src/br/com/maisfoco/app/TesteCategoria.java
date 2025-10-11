package br.com.maisfoco.app;

import br.com.maisfoco.dao.CategoriaDAO;
import br.com.maisfoco.model.Categoria;

public class TesteCategoria {
    public static void main(String[] args) {
        CategoriaDAO dao = new CategoriaDAO();
        dao.insert(new Categoria("Alimentacao", "#FF5555", "food"));
        dao.insert(new Categoria("Transporte", "#FFA500", "bus"));
        dao.insert(new Categoria("Salario", "#00C853", "cash"));
        dao.insert(new Categoria("Lazer", "#2979FF", "game"));
        dao.insert(new Categoria("Saude", "#AA00FF", "heart"));
        for (Categoria c : dao.getAll()) System.out.println(c);
    }
}