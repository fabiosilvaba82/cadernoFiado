package com.mrc.appcadernofiado.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class VendaVO implements Serializable {
    private Long idVenda;
    private Long idCliente;
    private Long idProduto;
    private Integer quantidade;
    private Integer notaFiscal;
    private Date dataVenda;
    private String descricaoProd;
    private BigDecimal precoProd;

    public Integer getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(Integer notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    public Long getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Long idVenda) {
        this.idVenda = idVenda;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public String getDescricaoProd() {
        return descricaoProd;
    }

    public void setDescricaoProd(String descricaoProd) {
        this.descricaoProd = descricaoProd;
    }

    public BigDecimal getPrecoProd() {
        return precoProd;
    }

    public void setPrecoProd(BigDecimal precoProd) {
        this.precoProd = precoProd;
    }
}
