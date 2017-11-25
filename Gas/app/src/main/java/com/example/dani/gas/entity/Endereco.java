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
public class Endereco {

    private static final Logger LOG = Logger.getLogger(Endereco.class.getName());
    
    private Integer id;
    private String cidade;
    private Usuario usuario;
    private Integer cep;
    private String bairro;
    private String rua;
    private String numero;
    private String complemento;
    private String timestamp;

    public Endereco() {
    }

    public Endereco(Integer id) {
        this.id = id;
    }

    public Endereco(Integer id, String cidade, Usuario usuario, Integer cep, String bairro, String rua, String numero, String complemento, String timestamp) {
        this.id = id;
        this.cidade = cidade;
        this.usuario = usuario;
        this.cep = cep;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.timestamp = timestamp;
    }    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public Integer getCep() {
        return cep;
    }

    public void setCep(Integer cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Endereco{" + "id=" + id + ", cidade=" + cidade + ", cep=" + cep + ", bairro=" + bairro + ", rua=" + rua + ", numero=" + numero + ", complemento=" + complemento + ", timestamp=" + timestamp + '}';
    }
}
