package br.com.maisfoco.model;

import java.sql.Date;

public class Meta {
    private Long id;
    private String tipo;
    private String nome;
    private Double valorObjetivo;
    private Double valorAtual;
    private Date dataLimite;
    private Long idUsuario;

    public Meta() {}
    public Meta(Long id, String tipo, String nome, Double valorObjetivo, Double valorAtual, Date dataLimite, Long idUsuario) {
        this.id = id; this.tipo = tipo; this.nome = nome; this.valorObjetivo = valorObjetivo; this.valorAtual = valorAtual; this.dataLimite = dataLimite; this.idUsuario = idUsuario;
    }
    public Meta(String tipo, String nome, Double valorObjetivo, Double valorAtual, Date dataLimite, Long idUsuario) {
        this.tipo = tipo; this.nome = nome; this.valorObjetivo = valorObjetivo; this.valorAtual = valorAtual; this.dataLimite = dataLimite; this.idUsuario = idUsuario;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Double getValorObjetivo() { return valorObjetivo; }
    public void setValorObjetivo(Double valorObjetivo) { this.valorObjetivo = valorObjetivo; }
    public Double getValorAtual() { return valorAtual; }
    public void setValorAtual(Double valorAtual) { this.valorAtual = valorAtual; }
    public Date getDataLimite() { return dataLimite; }
    public void setDataLimite(Date dataLimite) { this.dataLimite = dataLimite; }
    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }

    @Override
    public String toString() {
        return "Meta{id=" + id + ", tipo='" + tipo + "', nome='" + nome + "', valorObjetivo=" + valorObjetivo + ", valorAtual=" + valorAtual + ", dataLimite=" + dataLimite + ", idUsuario=" + idUsuario + "}";
    }
}