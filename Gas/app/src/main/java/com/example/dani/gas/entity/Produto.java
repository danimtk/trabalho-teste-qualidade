/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.dani.gas.entity;

import java.util.logging.Logger;

/**
 *
 * @author dani
 */
public class Produto {

    private static final Logger LOG = Logger.getLogger(Produto.class.getName());
    
    Integer id;
    Vendedor vendedor;
    String titulo;
    String descricao;
    String preco;
    String timestamp;

    public Produto() {
    }

    public Produto(Integer id) {
        this.id = id;
    }

    public Produto(Integer id, Vendedor vendedor, String titulo, String descricao, String preco, String timestamp) {
        this.id = id;
        this.vendedor = vendedor;
        this.titulo = titulo;
        this.descricao = descricao;
        this.preco = preco;
        this.timestamp = timestamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Produto{" + "id=" + id + ", vendedor=" + vendedor + ", titulo=" + titulo + ", descricao=" + descricao + ", preco=" + preco + ", timestamp=" + timestamp + '}';
    }
}
