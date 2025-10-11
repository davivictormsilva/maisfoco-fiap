package br.com.maisfoco.model;

public class Divida {
    private Long id;
    private String nome;
    private Double valorTotal;
    private Double valorPago;
    private String status;
    private Long idUsuario;

    public Divida() {}
    public Divida(Long id, String nome, Double valorTotal, Double valorPago, String status, Long idUsuario) {
        this.id = id; this.nome = nome; this.valorTotal = valorTotal; this.valorPago = valorPago; this.status = status; this.idUsuario = idUsuario;
    }
    public Divida(String nome, Double valorTotal, Double valorPago, String status, Long idUsuario) {
        this.nome = nome; this.valorTotal = valorTotal; this.valorPago = valorPago; this.status = status; this.idUsuario = idUsuario;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Double getValorTotal() { return valorTotal; }
    public void setValorTotal(Double valorTotal) { this.valorTotal = valorTotal; }
    public Double getValorPago() { return valorPago; }
    public void setValorPago(Double valorPago) { this.valorPago = valorPago; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }

    @Override
    public String toString() {
        return "Divida{id=" + id + ", nome='" + nome + "', valorTotal=" + valorTotal + ", valorPago=" + valorPago + ", status='" + status + "', idUsuario=" + idUsuario + "}";
    }
}