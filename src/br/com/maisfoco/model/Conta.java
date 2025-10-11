package br.com.maisfoco.model;

public class Conta {
    private Long id;
    private String nome;
    private String tipo;
    private Double saldo;
    private Long idUsuario;

    public Conta() {}
    public Conta(Long id, String nome, String tipo, Double saldo, Long idUsuario) {
        this.id = id; this.nome = nome; this.tipo = tipo; this.saldo = saldo; this.idUsuario = idUsuario;
    }
    public Conta(String nome, String tipo, Double saldo, Long idUsuario) {
        this.nome = nome; this.tipo = tipo; this.saldo = saldo; this.idUsuario = idUsuario;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public Double getSaldo() { return saldo; }
    public void setSaldo(Double saldo) { this.saldo = saldo; }
    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }

    @Override
    public String toString() {
        return "Conta{id=" + id + ", nome='" + nome + "', tipo='" + tipo + "', saldo=" + saldo + ", idUsuario=" + idUsuario + "}";
    }
}