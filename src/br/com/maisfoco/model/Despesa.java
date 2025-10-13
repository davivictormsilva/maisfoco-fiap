package br.com.maisfoco.model;

import java.sql.Date;

public class Despesa {
    private Long idDespesa;
    private String descricao;
    private Double valor;
    private Date data;
    private Long idConta;
    private Long idUsuario;
    private Long idCategoria;

    public Despesa() {}
    public Despesa(String descricao, Double valor, Date data, Long idConta, Long idUsuario, Long idCategoria) {
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.idConta = idConta;
        this.idUsuario = idUsuario;
        this.idCategoria = idCategoria;
    }

    // getters/setters
    public Long getIdDespesa() { return idDespesa; }
    public void setIdDespesa(Long idDespesa) { this.idDespesa = idDespesa; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }
    public java.sql.Date getData() { return data; }
    public void setData(java.sql.Date data) { this.data = data; }
    public Long getIdConta() { return idConta; }
    public void setIdConta(Long idConta) { this.idConta = idConta; }
    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }
    public Long getIdCategoria() { return idCategoria; }
    public void setIdCategoria(Long idCategoria) { this.idCategoria = idCategoria; }

    @Override
    public String toString() {
        return "Despesa{id=" + idDespesa + ", desc='" + descricao + "', valor=" + valor +
                ", data=" + data + ", conta=" + idConta + ", usuario=" + idUsuario +
                ", categoria=" + idCategoria + "}";
    }
}