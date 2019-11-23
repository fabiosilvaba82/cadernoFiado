package com.mrc.appcadernofiado.model;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Date;

public class Cliente implements Serializable {

    private Long id;
    private String nome;
    private String telefone;
    private Date dataCriacao;

    public Cliente() {
    }

    public Cliente(Long id) {
        this.id = id;
    }

    public Cliente(Long id, String nome, String telefone) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @NonNull
    @Override
    public String toString() {
        return id + " " + nome;
    }
}
