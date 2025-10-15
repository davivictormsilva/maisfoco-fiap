package br.com.maisfoco.model;

import java.util.Date;

public class Divida {
    private Long idDivida;
    private String nome;
    private Double valorTotal;
    private Double valorPago;
    private Date dataVencimento;
    private String status;
    private Long idUsuario;
    private Long idConta;

    public Divida() {}

    public Divida(String nome, Double valorTotal, Double valorPago, Date dataVencimento, String status, Long idUsuario, Long idConta) {
        this.nome = nome;
        this.valorTotal = valorTotal;
        this.valorPago = valorPago;
        this.dataVencimento = dataVencimento;
        this.status = status;
        this.idUsuario = idUsuario;
        this.idConta = idConta;
    }

    public Long getIdDivida() { return idDivida; }
    public void setIdDivida(Long idDivida) { this.idDivida = idDivida; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Double getValorTotal() { return valorTotal; }
    public void setValorTotal(Double valorTotal) { this.valorTotal = valorTotal; }
    public Double getValorPago() { return valorPago; }
    public void setValorPago(Double valorPago) { this.valorPago = valorPago; }
    public Date getDataVencimento() { return dataVencimento; }
    public void setDataVencimento(Date dataVencimento) { this.dataVencimento = dataVencimento; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }
    public Long getIdConta() { return idConta; }
    public void setIdConta(Long idConta) { this.idConta = idConta; }

    public String toString() {
        return "Divida{id=" + idDivida + ", nome='" + nome + "', total=" + valorTotal + ", pago=" + valorPago +
                ", vencimento=" + dataVencimento + ", status='" + status + "', usuario=" + idUsuario + ", conta=" + idConta + "}";
    }
}