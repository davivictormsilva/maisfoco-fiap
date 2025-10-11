package br.com.maisfoco.model;

public class Categoria {
    private Long id;
    private String nome;
    private String corHex;
    private String icone;

    public Categoria() {}
    public Categoria(Long id, String nome, String corHex, String icone) {
        this.id = id; this.nome = nome; this.corHex = corHex; this.icone = icone;
    }
    public Categoria(String nome, String corHex, String icone) {
        this.nome = nome; this.corHex = corHex; this.icone = icone;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCorHex() { return corHex; }
    public void setCorHex(String corHex) { this.corHex = corHex; }
    public String getIcone() { return icone; }
    public void setIcone(String icone) { this.icone = icone; }

    @Override
    public String toString() {
        return "Categoria{id=" + id + ", nome='" + nome + "', corHex='" + corHex + "', icone='" + icone + "'}";
    }
}