package com.mrc.appcadernofiado.model;

import java.io.Serializable;
import java.util.Date;

public class Venda implements Serializable {

    private Long id;
    private Integer quantidade;
    private Integer notaFiscal;
    private Date dataVenda;
    private Cliente idCliente;
    private Produto idProduto;

    public Venda() {
    }

    public Venda(Integer quantidade, Date dataVenda, Cliente idCliente, Produto idProduto) {
        this.quantidade = quantidade;
        this.dataVenda = dataVenda;
        this.idCliente = idCliente;
        this.idProduto = idProduto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public Produto getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Produto idProduto) {
        this.idProduto = idProduto;
    }

    public Integer getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(Integer notaFiscal) {
        this.notaFiscal = notaFiscal;
    }


    @Override
    public String toString() {
        return "Nota Fiscal:" + notaFiscal;
    }

}
