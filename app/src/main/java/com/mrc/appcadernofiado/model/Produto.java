package com.mrc.appcadernofiado.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Produto implements Serializable {

    private Long id;
    private String descricao;
    private BigDecimal preco;

    public Produto(Long id) {
        this.id = id;
    }

    public Produto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return id + " " + descricao;
    }
}
