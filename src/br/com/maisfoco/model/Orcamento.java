package br.com.maisfoco.model;

public class Orcamento {
    private Long idOrcamento;
    private Long idUsuario;
    private Long idCategoria;
    private Integer mesReferencia;
    private Double valorMensal;
    private Double gastoAtual;

    public Orcamento() {}

    public Orcamento(Long idUsuario, Long idCategoria, Integer mesReferencia, Double valorMensal, Double gastoAtual) {
        this.idUsuario = idUsuario;
        this.idCategoria = idCategoria;
        this.mesReferencia = mesReferencia;
        this.valorMensal = valorMensal;
        this.gastoAtual = gastoAtual;
    }

    public Long getIdOrcamento() { return idOrcamento; }
    public void setIdOrcamento(Long idOrcamento) { this.idOrcamento = idOrcamento; }
    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }
    public Long getIdCategoria() { return idCategoria; }
    public void setIdCategoria(Long idCategoria) { this.idCategoria = idCategoria; }
    public Integer getMesReferencia() { return mesReferencia; }
    public void setMesReferencia(Integer mesReferencia) { this.mesReferencia = mesReferencia; }
    public Double getValorMensal() { return valorMensal; }
    public void setValorMensal(Double valorMensal) { this.valorMensal = valorMensal; }
    public Double getGastoAtual() { return gastoAtual; }
    public void setGastoAtual(Double gastoAtual) { this.gastoAtual = gastoAtual; }

    public String toString() {
        return "Orcamento{id=" + idOrcamento + ", usuario=" + idUsuario + ", categoria=" + idCategoria +
                ", mes=" + mesReferencia + ", valorMensal=" + valorMensal + ", gastoAtual=" + gastoAtual + "}";
    }
}