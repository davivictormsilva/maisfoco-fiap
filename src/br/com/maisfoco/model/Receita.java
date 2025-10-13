package br.com.maisfoco.model;

import java.sql.Date;

public class Receita {
    private Long idReceita;
    private String descricao;
    private Double valor;
    private Date data;
    private Long idConta;
    private Long idUsuario;
    private Long idCategoria;

    public Receita() {}
    public Receita(String descricao, Double valor, Date data, Long idConta, Long idUsuario, Long idCategoria) {
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.idConta = idConta;
        this.idUsuario = idUsuario;
        this.idCategoria = idCategoria;
    }

    // getters/setters
    public Long getIdReceita() { return idReceita; }
    public void setIdReceita(Long idReceita) { this.idReceita = idReceita; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }
    public Date getData() { return data; }
    public void setData(Date data) { this.data = data; }
    public Long getIdConta() { return idConta; }
    public void setIdConta(Long idConta) { this.idConta = idConta; }
    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }
    public Long getIdCategoria() { return idCategoria; }
    public void setIdCategoria(Long idCategoria) { this.idCategoria = idCategoria; }

    @Override
    public String toString() {
        return "Receita{id=" + idReceita + ", desc='" + descricao + "', valor=" + valor +
                ", data=" + data + ", conta=" + idConta + ", usuario=" + idUsuario +
                ", categoria=" + idCategoria + "}";
    }
}