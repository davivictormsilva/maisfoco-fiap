package br.com.maisfoco.model;

import java.sql.Date;

public class Transacao {
    private Long id;
    private Double valor;
    private String tipo;
    private String descricao;
    private Date data;
    private Long idConta;
    private Long idUsuario;
    private Long idCategoria;

    public Transacao() {}
    public Transacao(Long id, Double valor, String tipo, String descricao, Date data, Long idConta, Long idUsuario, Long idCategoria) {
        this.id = id; this.valor = valor; this.tipo = tipo; this.descricao = descricao; this.data = data; this.idConta = idConta; this.idUsuario = idUsuario; this.idCategoria = idCategoria;
    }
    public Transacao(Double valor, String tipo, String descricao, Date data, Long idConta, Long idUsuario, Long idCategoria) {
        this.valor = valor; this.tipo = tipo; this.descricao = descricao; this.data = data; this.idConta = idConta; this.idUsuario = idUsuario; this.idCategoria = idCategoria;
    }

    public Long getId() { return id; }                 public void setId(Long id) { this.id = id; }
    public Double getValor() { return valor; }          public void setValor(Double valor) { this.valor = valor; }
    public String getTipo() { return tipo; }            public void setTipo(String tipo) { this.tipo = tipo; }
    public String getDescricao() { return descricao; }  public void setDescricao(String descricao) { this.descricao = descricao; }
    public Date getData() { return data; }              public void setData(Date data) { this.data = data; }
    public Long getIdConta() { return idConta; }        public void setIdConta(Long idConta) { this.idConta = idConta; }
    public Long getIdUsuario() { return idUsuario; }    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }
    public Long getIdCategoria() { return idCategoria; }public void setIdCategoria(Long idCategoria) { this.idCategoria = idCategoria; }

    @Override
    public String toString() {
        return "Transacao{id=" + id + ", valor=" + valor + ", tipo='" + tipo + "', descricao='" + descricao + "', data=" + data +
                ", idConta=" + idConta + ", idUsuario=" + idUsuario + ", idCategoria=" + idCategoria + "}";
    }
}